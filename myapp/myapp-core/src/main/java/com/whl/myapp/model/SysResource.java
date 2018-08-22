package com.whl.myapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.whl.myapp.base.model.BaseModel;

public class SysResource extends BaseModel implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5449630034198499157L;

	private Integer id;

    private String name;
    

    private String url;

    private Integer pid;

    private Integer sysResourceTypeId;

    private String iconCls;

    private String remark;

    private Integer seq;
    
    private String perm;
    
    public String getPerm() {
		return perm;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	}

	private Set<SysResource> sysResources;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

	public Integer getSysResourceTypeId() {
		return sysResourceTypeId;
	}

	public void setSysResourceTypeId(Integer sysResourceTypeId) {
		this.sysResourceTypeId = sysResourceTypeId;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Set<SysResource> getSysResources() {
		return sysResources;
	}

	public void setSysResources(Set<SysResource> sysResources) {
		this.sysResources = sysResources;
	}

}