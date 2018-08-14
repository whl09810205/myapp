package com.whl.myapp.base.dao;

import java.util.List;
import java.util.Map;

import com.whl.myapp.base.model.DataGrid;
import com.whl.myapp.base.model.ResultData;

/**
 *  方法名与mybatis 映射文件SQL ID对应
 */
public interface BaseDao<T> {
	
	void save(T t);
	
	void save(Map<String, Object> map);
	
	int delete(Object id);
	
	T get(Object id);
	
	
	//by primarykey
	int update(T t);
	int update(Map<String, Object> map);
	//by primarykey	
	int deleteBatch(Object[] id);

	List<T> queryList(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	///DataGrid dataGrid(Map<String, Object> map);

}
