package com.lzg.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lzg.annotation.DisableAuth;
import com.lzg.dbhelper.DBContextHolder;

/**
 * 
 * @author lzg
 * @date 2016年2月15日
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	//定义一个全局的记录器，通过LoggerFactory获取  
    private final static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("进入LoginInterceptor方法");
		
		HandlerMethod method = (HandlerMethod) handler;
		// 判断是否鉴权
		DisableAuth auth = method.getMethod().getAnnotation(DisableAuth.class);
		if (auth != null) {	//有DisableAuth注解，说明不用判断是否登录
			return true;
		}
		
		HttpSession session = request.getSession();
		String loginToken = (String) session.getAttribute("login-token");
		if(StringUtils.isBlank(loginToken)){
			log.debug("没有loginToken,跳转到登录页面");
			response.sendRedirect(request.getContextPath()+"/login/login");
			return false;
		}
		log.debug("登录验证通过");
		log.debug("LoginInterceptor方法完成");
		return true;
	}
}
