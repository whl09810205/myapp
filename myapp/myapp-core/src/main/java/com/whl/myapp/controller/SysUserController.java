package com.whl.myapp.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whl.myapp.base.model.DataGrid;
import com.whl.myapp.service.SysUserService;
import com.whl.myapp.util.Query;


@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	@Autowired
	SysUserService sysUserService;
	@RequiresPermissions("user:manage")
	@RequestMapping("/manage")
	public String manage(){
		return "sysuser/sysUserManage";
	}
	@ResponseBody
	@RequiresPermissions("user:dataGrid")
	@RequestMapping("/dataGrid")
	public DataGrid dataGrid(@RequestParam Map<String, Object> params){
		Query query = new Query(params);
		return sysUserService.dataGrid(query);
	}

}
