package com.lzg.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lzg.entity.UserTest;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 
 * @author lzg
 * @date 2016年2月15日
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getUserList() {
		return new ModelAndView("/user/user-list");
	}
	
	/**
	 * 根据用户名获取用户对象
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/name/{name}", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户名获取用户对象", httpMethod = "GET", response = UserTest.class, notes = "根据用户名获取用户对象")
	public UserTest getUserByName(@ApiParam(required = true, name = "name", value = "用户名") @PathVariable String name) throws Exception{
		return null;
	}
}
