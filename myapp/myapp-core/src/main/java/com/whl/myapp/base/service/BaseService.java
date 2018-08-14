package com.whl.myapp.base.service;


import com.whl.myapp.base.model.ResultData;

public class BaseService<T> {
	private static final Integer STATUS_SUCCESS = 200;
	private static final Integer STATUS_FAILED = 500;
	public  ResultData<T> getSuccess(T result){
		return new ResultData<T>(STATUS_SUCCESS, true,null,result);
	}
	public  ResultData<T> getFail(T result){
		return new ResultData<T>(STATUS_FAILED, true,null,result);
	}
	

}
