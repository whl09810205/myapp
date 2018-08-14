package com.whl.myapp.controller;


import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whl.myapp.base.model.ResultData;
import com.whl.myapp.base.model.Tree;
import com.whl.myapp.model.SysResource;
import com.whl.myapp.service.SysResourceService;

@Controller
@RequestMapping("/sysResource")
public class SysResourceController {

	@Autowired
	SysResourceService sysResourceService;
	@RequiresPermissions("resource:manage")
	@RequestMapping("/manage")
	public String manage(){
		return "sysresource/manage";
	}
	/**
	 * 权限treeGrid
	 */
	@RequiresPermissions("resource:treeGrid")
	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<SysResource> treeGrid(){
		return sysResourceService.treeGrid();
	}
	/**
	 * 个人菜单树
	 * @return
	 */
	@RequestMapping("/menuTree")
	@ResponseBody
	public List<Tree> menuTree(){
		return sysResourceService.menuTree();
	}
	/**
	 * 个人权限树
	 * @return
	 */
	@RequestMapping("/resourceTree")
	@ResponseBody
	public List<Tree> resourceTree(){
		return sysResourceService.resourceTree();
	}
	@RequiresPermissions("resource:add")
	@RequestMapping("/addPage")
	public String addPage(ModelMap modelMap){
		modelMap.put("sysResource", new SysResource());
		return "sysresource/form";
	}
	@RequiresPermissions("resource:edit")
	@RequestMapping("/editPage")
	public String editPage(Integer id,ModelMap modelMap){
		SysResource sysResource = sysResourceService.get(id);
		modelMap.put("sysResource", sysResource);
		return "sysresource/form";
	}
	@RequiresPermissions("resource:save")
	@RequestMapping(value="/save",method =RequestMethod.POST )
	@ResponseBody
	public ResultData<SysResource> save(SysResource sysResource){
		return sysResourceService.save(sysResource);
	}
	@RequiresPermissions("resource::delete")
	@RequestMapping("/delete")
	@ResponseBody
	public ResultData<SysResource> delete(Integer id){
		return sysResourceService.delete(id);
	}
	//@RequiresRoles("admin")
	@RequiresPermissions("resource:getAllSysResources")
	@RequestMapping("/getAllSysResources")
	@ResponseBody
	public ResultData getAllSysResources(){
		return sysResourceService.getAllSysResources();
	}
	
}
