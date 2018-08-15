package com.whl.myapp.service;

import java.util.List;

import com.whl.myapp.base.model.ResultData;
import com.whl.myapp.base.model.Tree;
import com.whl.myapp.model.SysRole;

public interface SysRoleService {

	List<SysRole> treeGrid();

	SysRole get(Integer id);

	ResultData<SysRole> save(SysRole sysRole);

	ResultData<SysRole> delete(Integer id);

	List<Tree> tree();

	SysRole getRoleResourceByRoleId(Integer id);

	ResultData<SysRole> grant(Integer[] resourceIds, Integer roleId);

}
