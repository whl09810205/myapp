package com.whl.myapp.dao;



import org.apache.ibatis.annotations.Param;

import com.whl.myapp.base.dao.BaseDao;
import com.whl.myapp.model.SysUser;

public interface SysUserDao extends BaseDao<SysUser>{
	SysUser findUserRole(int userId);

	SysUser findByUserName(String username);

	void deleteRoleUser(@Param("sysRoleId")Integer sysRoleId,@Param("sysUserId")Integer sysUserId);
}