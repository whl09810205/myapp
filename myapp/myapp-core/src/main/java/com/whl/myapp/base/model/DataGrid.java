package com.whl.myapp.base.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.github.pagehelper.Page;
public class DataGrid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2523259604638051363L;

	private List<?> rows;
	private Long total = 0L;

	public DataGrid() {
	}

	public DataGrid(Page<?> page) {
		this.rows = page.getResult();
		this.total = page.getTotal();
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	@Override
	public String toString(){
		return ReflectionToStringBuilder.toString(this);
	}
}
