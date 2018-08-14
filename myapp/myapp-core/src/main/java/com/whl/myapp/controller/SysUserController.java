package com.whl.myapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.whl.myapp.base.model.DataGrid;
import com.whl.myapp.service.SysUserService;
import com.whl.myapp.util.Query;


@Controller
public class SysUserController {
	@Autowired
	SysUserService sysUserService;
	@RequestMapping("/dataGrid")
	public DataGrid dataGrid(@RequestParam Map<String, Object> params){
		Query query = new Query(params);
		return sysUserService.dataGrid(query);
	}

}
