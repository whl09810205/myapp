package com.whl.myapp.service;

import java.util.List;

import com.whl.myapp.base.model.ResultData;
import com.whl.myapp.base.model.Tree;
import com.whl.myapp.model.SysResource;

public interface SysResourceService {

	List<Tree> menuTree();

	List<SysResource> treeGrid();

	SysResource get(Integer id);

	List<Tree> resourceTree();

	ResultData<SysResource> save(SysResource sysResource);
	ResultData<SysResource> update(SysResource sysResource);

	ResultData<SysResource> delete(Integer id);

	ResultData<SysResource> getAllSysResources();

}
