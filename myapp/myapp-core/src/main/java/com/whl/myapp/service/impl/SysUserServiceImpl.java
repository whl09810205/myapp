package com.whl.myapp.service.impl;

import java.util.Map;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.whl.myapp.base.model.DataGrid;
import com.whl.myapp.dao.SysUserDao;
import com.whl.myapp.model.SysUser;
import com.whl.myapp.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	SysUserDao sysUserDao;
	@Autowired
	RedisTemplate redisTemplate;

	@Override
	public SysUser findByUsername(String username) {
		return sysUserDao.findByUserName(username);
	}

	public static void main(String[] args) {
		SysUser user = new SysUserServiceImpl().findByUsername(null);
		ByteSource salt = ByteSource.Util.bytes(user.getCredentialsSalt());
		String newPs = new SimpleHash("MD5", "123456", salt, 2).toHex();
		System.out.println(newPs);
	}

	@Override
	 @Cacheable(value="dataGrid")
	public DataGrid dataGrid(Map<String, Object> params) {
		DataGrid dataGrid = new DataGrid(PageHelper.startPage(params).doSelectPage(()->sysUserDao.queryList(params)));
		return dataGrid;
	}
}
