package com.whl.myapp.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数 
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	private int page=1;// 页数，默认第一页
	private int rows=10;// 每页显示记录数,默认10
    public Query(Map<String, Object> params){
        this.putAll(params);
        //防止SQL注入
        try{
        	String sort = params.get("sort").toString();
            String order = params.get("order").toString();
            this.put("sort", SQLFilter.sqlInject(sort));
            this.put("order", SQLFilter.sqlInject(order));
        }catch(Exception e){
        }
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}

   
}
