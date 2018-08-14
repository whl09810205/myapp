package com.whl.myapp.dao;



import com.whl.myapp.base.dao.BaseDao;
import com.whl.myapp.model.SysRole;

public interface SysRoleDao extends BaseDao<SysRole>{

	SysRole findByRoleName(String string);
	
}