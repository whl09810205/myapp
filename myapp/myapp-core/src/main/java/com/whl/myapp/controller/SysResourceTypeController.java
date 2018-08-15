package com.whl.myapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whl.myapp.model.SysResourceType;
import com.whl.myapp.service.SysResourceTypeService;

@Controller
@RequestMapping("/sysResourceType")
public class SysResourceTypeController {

	@Autowired
	SysResourceTypeService sysResourceTypeService;
	@RequestMapping("/comboxList")
	@ResponseBody
	public List<SysResourceType> manage(){
		return sysResourceTypeService.queryList();
	}
}
