package com.whl.myapp.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whl.myapp.base.model.ResultData;
import com.whl.myapp.base.model.Tree;
import com.whl.myapp.model.SysRole;
import com.whl.myapp.service.SysRoleService;


@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
	@Autowired
	SysRoleService sysRoleService;
	
	@RequiresPermissions("role:manage")
	@RequestMapping("/manage")
	public String manage(){
		return "sysrole/sysRoleManage";
	}
	@RequiresPermissions("role:treeGrid")
	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<SysRole> treeGrid(){
		return sysRoleService.treeGrid();
	}
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(){
		return sysRoleService.tree();
	}
	@RequiresPermissions("role:add")
	@RequestMapping("/addPage")
	public String addPage(Integer pid,ModelMap modelMap){
		SysRole sysRole = new SysRole();
		sysRole.setPid(pid);
		modelMap.addAttribute("sysRole", sysRole);
		return "sysrole/sysRoleForm";
	}
	@RequiresPermissions("role:edit")
	@RequestMapping("/editPage")
	public String editPage(Integer id,ModelMap modelMap){
		SysRole sysRole = sysRoleService.get(id);
		modelMap.addAttribute("sysRole",sysRole);
		return "sysrole/sysRoleForm";
	}
	@RequiresPermissions("role:grantPage")
	@RequestMapping("/grantPage")
	public String grantPage(Integer id,ModelMap modelMap){
		SysRole sysRole = sysRoleService.getRoleResourceByRoleId(id);
		modelMap.addAttribute("sysRole",sysRole);
		return "sysrole/sysRoleGrant";
	}
	@ResponseBody
	@RequiresPermissions("role:grant")
	@RequestMapping("/grant")
	public ResultData<SysRole> grant(Integer[] resourceIds,Integer roleId){
		return sysRoleService.grant(resourceIds,roleId);
	}
	@ResponseBody
	@RequiresPermissions("role:save")
	@RequestMapping("/save")
	public ResultData<SysRole> save(SysRole sysRole){
		return sysRoleService.save(sysRole);
	}
	@RequiresPermissions("role:delete")
	@RequestMapping("/delete")
	public ResultData<SysRole> delete(Integer id){
		return sysRoleService.delete(id);
	}

}
