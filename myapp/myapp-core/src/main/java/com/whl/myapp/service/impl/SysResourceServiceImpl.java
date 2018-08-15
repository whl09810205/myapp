package com.whl.myapp.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whl.myapp.base.model.ResultData;
import com.whl.myapp.base.model.Tree;
import com.whl.myapp.base.service.BaseService;
import com.whl.myapp.dao.SysResourceDao;
import com.whl.myapp.dao.SysRoleDao;
import com.whl.myapp.dao.SysUserDao;
import com.whl.myapp.model.SysResource;
import com.whl.myapp.model.SysRole;
import com.whl.myapp.model.SysUser;
import com.whl.myapp.service.SysResourceService;

@Service
public class SysResourceServiceImpl extends BaseService<SysResource> implements SysResourceService {

	@Autowired
	SysResourceDao sysResourceDao;
	@Autowired
	SysUserDao sysUserDao;
	@Autowired
	SysRoleDao sysRoleDao;

	@Override
	public List<Tree> menuTree() {
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", sysUser.getId());
		params.put("sysresourcetype", 0);
		return sysResourceToTrees(params);
	}

	@Override
	public List<SysResource> treeGrid() {
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", sysUser.getId());
		return sysResourceDao.findUserResource(params);
	}

	@Override
	public SysResource get(Integer id) {
		
		return sysResourceDao.get(id);
	}

	@Override
	public List<Tree> resourceTree() {
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", sysUser.getId());
		return sysResourceToTrees(params);
	}
	private List<Tree> sysResourceToTrees(Map<String, Object> params){
		List<Tree> menuTree = new ArrayList<Tree>();
		List<SysResource> menuResources = sysResourceDao.findUserResource(params);
		menuResources.forEach(menu -> {
			Tree tree = new Tree();
			tree.setId(menu.getId().toString());
			if (menu.getPid() != null) {
				tree.setPid(menu.getPid().toString());
			}
			tree.setText(menu.getName());
			tree.setIconCls(menu.getIconCls());
			Map<String, Object> attr = new HashMap<String, Object>();
			attr.put("url", menu.getUrl());
			tree.setAttributes(attr);
			menuTree.add(tree);
		});
		return menuTree;
	}

	@Override
	public ResultData<SysResource> save(SysResource sysResource) {
		if(null == sysResource.getId()){
			sysResourceDao.save(sysResource);
			//为当前用户角色添加当前资源
			SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
			Set<SysRole> sysRoles = sysUserDao.findUserRole(sysUser.getId()).getSysRoles();
			sysRoles.forEach((role)->sysResourceDao.addResourceForRole(role.getId(),sysResource.getId()));
		}else {
			sysResourceDao.update(sysResource);
		}
			
		return getSuccess(sysResource);
	}
	@Override
	public ResultData<SysResource> update(SysResource sysResource) {
		sysResourceDao.update(sysResource);
		return getSuccess(sysResource);
	}

	@Override
	public ResultData<SysResource> delete(Integer sysResourceId) {
		sysResourceDao.deleteResourceRole(sysResourceId, null);
		sysResourceDao.delete(sysResourceId);
		return getSuccess(null);
	}

	@Override
	public ResultData<SysResource> getAllSysResources() {
		Reflections reflections = new Reflections("com.whl.myapp.controller",new TypeAnnotationsScanner(),new SubTypesScanner());
		Set<Class<?>> classesSet = reflections.getTypesAnnotatedWith(Controller.class);
		for(Class<?> cls : classesSet){
			RequestMapping clsRequestMapping = (RequestMapping) cls.getAnnotation(RequestMapping.class);
			String clsPath = "";
			if(null != clsRequestMapping){
				clsPath = clsRequestMapping.value()[0];
			}
			for(Method method : cls.getMethods()){
				RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
				RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
				if(null != requestMapping){
					String methodPath = requestMapping.value()[0];
					String url = clsPath+methodPath;
					if(null != requiresPermissions){//权限不为空，则保存到资源列表
						String perm = requiresPermissions.value()[0];
						SysResource sysResource = sysResourceDao.findByUrl(url);
						if (null == sysResource) {
							sysResource = new SysResource();
							sysResource.setUrl(url);
							sysResource.setName(url);
							sysResource.setPerm(perm);
							sysResource.setSysResourceTypeId(1);//默认为功能
							sysResourceDao.save(sysResource);							
							SysRole sysRole = sysRoleDao.findByRoleName("SUPERADMIN");
							sysResourceDao.addResourceForRole(sysRole.getId(), sysResource.getId());
						}else {
							sysResource.setPerm(perm);
							sysResourceDao.update(sysResource);
						}
					}
				}
			}
		}
		return getSuccess(null);
	}}
