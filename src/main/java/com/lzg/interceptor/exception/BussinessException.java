package com.lzg.interceptor.exception;

/**
 * 自定义业务异常
 * 
 * @author lzg
 * @date 2016年5月20日
 */
public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = -4688328529498604983L;

	protected String code;

	public BussinessException() {
	}

	public BussinessException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
