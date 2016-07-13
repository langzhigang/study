package com.lzg.controller;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.lzg.annotation.DisableAuth;
import com.lzg.entity.UserTest;
import com.lzg.service.ITestService;
import com.lzg.service.IUserServiceTest;
import com.lzg.util.UUIDUtil;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 
 * @author lzg
 * @date 2016年2月15日
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

	private final static Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IUserServiceTest userServiceTest;

	@DisableAuth
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ApiOperation(value = "跳转到登录页面")
	public ModelAndView loginView() {
		return new ModelAndView("login/login");
	}

	@Autowired
	ITestService testService;

	@DisableAuth
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "登录逻辑处理")
	public Map<String, Object> login(@RequestBody UserTest user, HttpServletRequest request,
			HttpServletResponse response) {
		testService.a();

		// 用户输入的验证码的值
		String kaptchaExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// 校验验证码是否正确
		String kaptchaReceived = user.getValiCode();
		if (kaptchaReceived == null || !kaptchaReceived.equals(kaptchaExpected)) {
			System.out.println("验证码错误");
		}

		Map<String, Object> result = new HashMap<>();
		String userName = user.getUserName();
		String password = user.getPassword();

		log.debug("用户:{}开始登录", userName);

		user = userServiceTest.validate(userName, password);

		if (user == null) {
			log.debug("用户:{}登录失败,原因是用户名或密码错误", userName);
			result.put("code", 100);
			result.put("msg", "登录失败");
			return result;
		}

		HttpSession session = request.getSession();
		String loginToken = UUIDUtil.getUUIDString();
		long millis = System.currentTimeMillis();
		session.setAttribute("login-token", loginToken + "-" + millis);

		result.put("code", 200);
		result.put("msg", "登录成功");
		log.debug("用户:{}登录成功", user.getUserName());

		return result;
	}

	/**
	 * loginCheck:ajax异步校验登录请求. <br/>
	 * 
	 * @author chenzhou1025@126.com
	 * @param request
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param kaptchaReceived
	 *            验证码
	 * @return 校验结果
	 * @since 2013-12-10
	 */
	@RequestMapping(value = "check", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest request,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "kaptcha", required = true) String kaptchaReceived) {
		// 用户输入的验证码的值
		String kaptchaExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// 校验验证码是否正确
		if (kaptchaReceived == null || !kaptchaReceived.equals(kaptchaExpected)) {
			return "kaptcha_error";// 返回验证码错误
		}
		// 校验用户名密码
		// ……
		// ……
		return "success"; // 校验通过返回成功
	}

	private Producer captchaProducer = null;

	@Autowired
	public void setCaptchaProducer(Producer captchaProducer) {
		this.captchaProducer = captchaProducer;
	}

	@DisableAuth
	@RequestMapping("/kaptcha.jpg")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getSession().getId());
		// Set to expire far in the past.
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		// return a jpeg
		response.setContentType("image/jpeg");

		// create the text for the image
		String capText = captchaProducer.createText();

		// store the text in the session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		// create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);

		ServletOutputStream out = response.getOutputStream();

		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
}
