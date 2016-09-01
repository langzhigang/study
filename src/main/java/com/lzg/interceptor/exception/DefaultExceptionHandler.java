package com.lzg.interceptor.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 * 
 * @author lzg
 * @date 2016年3月29日
 */
public class DefaultExceptionHandler implements HandlerExceptionResolver {
	private static Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		/* 使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常 */
		FastJsonJsonView view = new FastJsonJsonView();
		Map<String, Object> attributes = new HashMap<String, Object>();
		if (ex instanceof BussinessException) {
			attributes.put("code", ((BussinessException) ex).getCode());
		} else {
			attributes.put("code", "500"); // 所有出错的状态码都用500
		}
		attributes.put("msg", ex.getMessage());
		view.setAttributesMap(attributes);
		mv.setView(view);
		log.info("-------------全局异常处理,msg: {} , exception: {} ", ex.getMessage(), ex);
		return mv;
	}
}
