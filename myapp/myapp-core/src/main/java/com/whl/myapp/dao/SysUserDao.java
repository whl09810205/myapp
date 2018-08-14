package com.whl.myapp.dao;



import com.whl.myapp.base.dao.BaseDao;
import com.whl.myapp.model.SysUser;

public interface SysUserDao extends BaseDao<SysUser>{
	SysUser findUserRole(int userId);

	SysUser findByUserName(String username);
}