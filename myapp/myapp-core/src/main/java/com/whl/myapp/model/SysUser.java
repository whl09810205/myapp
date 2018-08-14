package com.whl.myapp.model;

import java.io.Serializable;
import java.util.Set;


import com.whl.myapp.base.model.BaseModel;

/**
 * @author hailong
 *
 */
public class SysUser  extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

    private String userName;

    private String passWord;

    private String nameZh;

    private String status;
    
    private static final String SALT="myapp" ;
    
    private Set<SysRole> sysRoles;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getNameZh() {
		return nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}
	
	public String getCredentialsSalt(){
		
		return this.userName+SALT;
	}
    
}