package com.whl.myapp.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whl.myapp.Application;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class ServiceTests {

	@Autowired
	SysUserService sysUserService;
	@Autowired
	SysResourceService sysResourceService;
	/**
	 * 单元测试 - 新增用户
	 * @throws JsonProcessingException 
	 */
//	@Test
//	public void sysResourceDaoTest() throws JsonProcessingException{
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("page", 1);
//		params.put("rows", 10);
//		ObjectMapper objectMapper = new ObjectMapper();
//		System.out.println(objectMapper.writeValueAsString(sysUserService.dataGrid(params)));
//	}
	@Test
	public void testsysResourceService(){
		sysResourceService.getAllSysResources();
	}
}
