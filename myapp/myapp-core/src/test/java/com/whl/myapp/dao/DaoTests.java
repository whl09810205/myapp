package com.whl.myapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.whl.myapp.model.SysResource;
import com.whl.myapp.model.SysUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTests {

	@Autowired
	SysUserDao sysUserDao;
	@Autowired
	SysResourceDao sysResourceDao;

	/**
	 * 单元测试 - 新增用户
	 */
//	@Test
//	public void userDaoTestSave() {
//		SysUser sysUser = new SysUser();
//		sysUser.setUserName("admin");
//		sysUser.setPassWord("a1baab6678572fdc86b422b6840f0ae0");
//		sysUserDao.save(sysUser);
//		// 验证新增用户
//		Assert.assertNotNull(sysUser.getId());
//		System.out.println("=========="+sysUserDao.findUserRole(1));
//		sysUser =sysUserDao.findUserRole(1);
//		System.out.println("=====");
//	}
	@Test
	public void sysResourceDaoTest(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", 1);
		List<SysResource> sysList = sysResourceDao.findUserResource(params);
		System.out.println(sysList);
	}
}
