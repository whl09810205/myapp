package com.whl.myapp.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.whl.myapp.base.dao.BaseDao;
import com.whl.myapp.model.SysResource;


public interface SysResourceDao extends BaseDao<SysResource>{
	List<SysResource> findUserResource(Map<String, Object> params);

	void addResourceForRole(@Param("sysRoleId")Integer sysRoleId, @Param("sysResourceId")Integer sysResourceId);

	void deleteResourceRole(@Param("sysResourceId")Integer sysResourceId);

	SysResource findByUrl(String url);
}