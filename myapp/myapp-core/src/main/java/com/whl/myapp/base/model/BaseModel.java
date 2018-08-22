package com.whl.myapp.base.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class BaseModel{


	protected Date createAt;
	
	protected Date updateAt;
	
	
	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public Date getUpdateAt() {
		return updateAt;
	}


	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}


	@Override
	public String toString() {
		return  ReflectionToStringBuilder.toString(this);
	}

}
