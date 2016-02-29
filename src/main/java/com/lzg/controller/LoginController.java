package com.lzg.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lzg.annotation.DisableAuth;
import com.lzg.dbhelper.DBContextHolder;
import com.lzg.entity.UserTest;
import com.lzg.util.UUIDUtil;

/**
 * 
 * @author lzg
 * @date 2016年2月15日
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {
	
	private final static Logger log = LoggerFactory.getLogger(DBContextHolder.class); 
	
	@DisableAuth
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginView() {
		return new ModelAndView("login/login");
	}
	
	@DisableAuth
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String,Object> login(@RequestBody UserTest user,HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> result = new HashMap<>();
		
		String userName = user.getUserName();
		String password = user.getPassword();
		
		log.debug("用户:{}开始登录",userName);
		
		if(!"1".equals(password)){
			log.debug("用户:{}登录失败,原因是用户名或密码错误",userName);
			result.put("code",100);
			result.put("msg", "登录失败");
			return result;
		}
		
		HttpSession session = request.getSession();
		String loginToken = UUIDUtil.getUUIDString();
		long millis = System.currentTimeMillis();
		session.setAttribute("login-token",loginToken+"-"+millis);
		
		result.put("code",200);
		result.put("msg", "登录成功");
		log.debug("用户:{}登录成功",user.getUserName());
		return result;
	}
}
