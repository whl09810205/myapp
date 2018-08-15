package com.whl.myapp.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.whl.myapp.base.model.ResultData;
import com.whl.myapp.base.model.Tree;
import com.whl.myapp.base.service.BaseService;
import com.whl.myapp.dao.SysResourceDao;
import com.whl.myapp.dao.SysRoleDao;
import com.whl.myapp.dao.SysUserDao;
import com.whl.myapp.model.SysRole;
import com.whl.myapp.model.SysUser;
import com.whl.myapp.service.SysRoleService;
@Service
public class SysRoleServiceImpl extends BaseService<SysRole> implements SysRoleService {

	@Autowired
	SysRoleDao sysRoleDao;
	@Autowired
	SysResourceDao sysResourceDao;
	@Autowired
	SysUserDao sysUserDao;
	@Override
	public List<SysRole> treeGrid() {
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", sysUser.getId());
		return sysRoleDao.findRoleResourceByUserId(params);
	}
	@Override
	public SysRole get(Integer id) {
		return sysRoleDao.get(id);
	}
	@Override
	public ResultData<SysRole> save(SysRole sysRole) {
		if (null == sysRole.getId()) {
			sysRoleDao.save(sysRole);
			return getSaveSuccess(sysRole);
		}else {
			sysRoleDao.update(sysRole);
			return getUpdateSuccess(sysRole);
		}
		
	}
	@Override
	public ResultData<SysRole> delete(Integer sysRoleId) {
		//清除resource、user关联关系
		sysResourceDao.deleteResourceRole(null, sysRoleId);
		sysUserDao.deleteRoleUser(sysRoleId,null);
		sysRoleDao.delete(sysRoleId);
		return getDelSuccess(null);
	}
	@Override
	public List<Tree> tree() {
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", sysUser.getId());
		List<Tree> roleTrees = new ArrayList<Tree>();
		List<SysRole> sysRoles = sysRoleDao.findRoleResourceByUserId(params);
		sysRoles.forEach((sysRole)->{
			Tree tree = new Tree();
			tree.setId(sysRole.getId().toString());
			tree.setText(sysRole.getRoleName());
			if (null != sysRole.getPid()) {
				tree.setPid(sysRole.getPid().toString());
			}
			roleTrees.add(tree);
		});
		return roleTrees;
	}
	@Override
	public SysRole getRoleResourceByRoleId(Integer id) {
		return sysRoleDao.getRoleResourceByRoleId(id);
	}
	@Override
	public ResultData<SysRole> grant(Integer[] resourceIds, Integer roleId) {
		if (null != resourceIds && resourceIds.length>0) {
			sysRoleDao.addResourcesForRole(resourceIds,roleId);
		}
		return getSaveSuccess(null);
	}

}
