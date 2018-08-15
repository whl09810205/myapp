package com.whl.myapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whl.myapp.dao.SysResourceTypeDao;
import com.whl.myapp.model.SysResourceType;
import com.whl.myapp.service.SysResourceTypeService;

@Service
public class SysResourceTypeServiceImpl implements SysResourceTypeService {

	@Autowired
	private SysResourceTypeDao sysResourceTypeDao;
	@Override
	public List<SysResourceType> queryList() {
		return sysResourceTypeDao.queryList(null);
	}

}
