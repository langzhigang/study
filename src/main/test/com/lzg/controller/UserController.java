package com.lzg.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzg.entity.User;
import com.lzg.service.IUserService;

@RestController
@RequestMapping(value = "/login")
public class UserController {

	@Autowired
	IUserService userService;

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestBody User user,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", 1);
		return map;
	}
}
