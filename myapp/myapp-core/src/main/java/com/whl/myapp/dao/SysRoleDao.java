package com.whl.myapp.dao;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.whl.myapp.base.dao.BaseDao;
import com.whl.myapp.model.SysRole;

public interface SysRoleDao extends BaseDao<SysRole>{

	SysRole findByRoleName(String string);

	List<SysRole> findRoleResourceByUserId(Map<String, Object> params);

	SysRole getRoleResourceByRoleId(Integer id);

	void addResourcesForRole(@Param("resourceIds")Integer[] resourceIds,@Param("roleId") Integer roleId);
	
}