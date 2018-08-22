package com.whl.myapp.config.shrio;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whl.myapp.base.model.ResultData;
import com.whl.myapp.model.SysUser;

/**
 * @author hailong 自定义filter 支持ajax登录
 *
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final Logger log = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);
	private static final String NOSESSION_URL = "/noSession";

	/**
	 * 未验证首页返回登录界面，其余返回nosession界面
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				return executeLogin(request, response);
			} else {
				return true;
			}
		} else {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			// 只有首页跳转到登录页，其余页面提示未登录
			if ("/".equals(WebUtils.getRequestUri(httpServletRequest))) {
				redirectToLogin(request, response);
			} else {
				WebUtils.issueRedirect(request, response, NOSESSION_URL);
			}
			return false;
		}
	}

	/**
	 * ajax请求直接返回json 非ajax请求返回登录界面
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		if (isAjax(request)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
			ResultData resultData = new ResultData(200, true, "登录成功！", sysUser.getNameZh());
			ObjectMapper objectMapper = new ObjectMapper();
			response.getWriter().write(objectMapper.writeValueAsString(resultData));
			// prevent the chain from continuing:
			return false;
		}
		issueSuccessRedirect(request, response);
		// prevent the chain from continuing:
		return false;
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		if (isAjax(request)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			ResultData resultData = new ResultData();
			resultData.setSuccess(false);
			ObjectMapper objectMapper = new ObjectMapper();
			if (UnknownAccountException.class.getName().equals(e.getClass().getName())) {
				resultData.setMsg("账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(e.getClass().getName())) {
				resultData.setMsg("密码错误");
			} else {
				resultData.setMsg(e.getMessage());
			}
			try {
				response.getWriter().write(objectMapper.writeValueAsString(resultData));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// prevent the chain from continuing:
			return false;
		}
		setFailureAttribute(request, e);
		// login failed, let request continue back to the login page:
		return true;
	}

	private boolean isAjax(ServletRequest request) {
		String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(header)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
