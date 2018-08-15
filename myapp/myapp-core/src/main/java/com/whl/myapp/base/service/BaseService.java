package com.whl.myapp.base.service;


import com.whl.myapp.base.model.ResultData;

public class BaseService<T> {
	private static final Integer STATUS_SUCCESS = 200;
	private static final Integer STATUS_FAILED = 500;
	private static final String SAVE_SUCCESS = "保存成功！";
	private static final String SAVE_FAIL = "保存成功！";
	private static final String UPDATE_SUCCESS = "更新成功！";
	private static final String UPDATE_FAIL = "更新失败！";
	private static final String DELETE_SUCCESS = "删除成功！";
	private static final String DELETE_FAIL = "删除失败！";
	public  ResultData<T> getSuccess(T result){
		return new ResultData<T>(STATUS_SUCCESS, true,null,result);
	}
	public  ResultData<T> getFail(T result){
		return new ResultData<T>(STATUS_FAILED, true,null,result);
	}
	public  ResultData<T> getSaveSuccess(T result){
		return new ResultData<T>(STATUS_SUCCESS, true,SAVE_SUCCESS,result);
	}
	public  ResultData<T> getSaveFail(T result){
		return new ResultData<T>(STATUS_FAILED, true,SAVE_FAIL,result);
	}
	public  ResultData<T> getUpdateSuccess(T result){
		return new ResultData<T>(STATUS_SUCCESS, true,UPDATE_SUCCESS,result);
	}
	public  ResultData<T> getUpdateFail(T result){
		return new ResultData<T>(STATUS_FAILED, true,UPDATE_FAIL,result);
	}
	public  ResultData<T> getDelSuccess(T result){
		return new ResultData<T>(STATUS_SUCCESS, true,DELETE_SUCCESS,result);
	}
	public  ResultData<T> getDelFail(T result){
		return new ResultData<T>(STATUS_FAILED, true,DELETE_FAIL,result);
	}
	

}
