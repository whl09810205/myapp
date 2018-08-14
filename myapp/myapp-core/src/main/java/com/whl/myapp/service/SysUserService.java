package com.whl.myapp.service;

import java.util.Map;

import com.whl.myapp.base.model.DataGrid;
import com.whl.myapp.model.SysUser;

public interface SysUserService {

	SysUser findByUsername(String username);

	DataGrid dataGrid(Map<String, Object> params);
	

}
