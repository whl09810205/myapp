package com.whl.myapp.base.model;

import java.io.Serializable;

/**
 * @author hailong 返回对象
 *
 * @param <T>
 */
public class ResultData<T> implements Serializable {

	private static final long serialVersionUID = -4482293315927834476L;
	public T result;
	public Integer status;
	public String msg;
	public Boolean success;

	public ResultData() {

	}
	public ResultData(Integer status) {

	}

	public ResultData(Integer status, Boolean success) {
		this(status);
		this.success = success;
	}
	public ResultData(Integer status, Boolean success,String msg) {
		this(status,success);
		this.msg = msg;
	}
	public ResultData(Integer status, Boolean success,String msg,T result) {
		this(status,success,msg);
		this.result = result;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
