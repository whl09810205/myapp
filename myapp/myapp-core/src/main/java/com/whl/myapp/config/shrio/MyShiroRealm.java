package com.whl.myapp.config.shrio;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.whl.myapp.model.SysResource;
import com.whl.myapp.model.SysRole;
import com.whl.myapp.model.SysUser;
import com.whl.myapp.service.SysUserService;

public class MyShiroRealm extends AuthorizingRealm{

	@Autowired
	@Lazy//解决@cacheable注解失效问题
	SysUserService sysUserService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser  = (SysUser)principals.getPrimaryPrincipal();
        for(SysRole role:sysUser.getSysRoles()){
            authorizationInfo.addRole(role.getRoleName());
            for(SysResource resource:role.getSysResources()){
                authorizationInfo.addStringPermission(resource.getPerm());
            }
        }
        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		 System.out.println("认证配置-->MyShiroRealm.doGetAuthenticationInfo()");
	        //获取用户的输入的账号.
	        String username = (String)token.getPrincipal();
	        System.out.println(token.getCredentials());
	        //通过username从数据库中查找 User对象，如果找到，没找到.
	        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	        SysUser sysUser = sysUserService.findByUsername(username);
	        System.out.println("----->>userInfo="+sysUser);
	        if(sysUser == null){
	            return null;
	        }
	        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	        		sysUser, //用户名
	        		sysUser.getPassWord(), //密码
	                ByteSource.Util.bytes(sysUser.getCredentialsSalt()),
	                getName()  //realm name
	        );
	        return authenticationInfo;
	    }

}
