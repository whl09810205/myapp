package com.whl.myapp.model;

import java.util.Date;
import java.util.Set;

import com.whl.myapp.base.model.BaseModel;

/**
 * @author hailong
 *
 */
public class SysRole  extends BaseModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2995409870983777393L;


	private Integer id;


    private String roleName;


    private Integer pid;


    private Integer seq;


    private String remark;


    private Date createAt;


    private Date updateAt;

    private Set<SysUser> sysUsers;
    
    private Set<SysResource> sysResources;
    
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }



    public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public Integer getPid() {
        return pid;
    }


    public void setPid(Integer pid) {
        this.pid = pid;
    }


    public Integer getSeq() {
        return seq;
    }


    public void setSeq(Integer seq) {
        this.seq = seq;
    }


    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }



  

	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}


	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}


	public Set<SysResource> getSysResources() {
		return sysResources;
	}


	public void setSysResources(Set<SysResource> sysResources) {
		this.sysResources = sysResources;
	}


	public Date getUpdateAt() {
        return updateAt;
    }


    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}