package com.lzg.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
}
