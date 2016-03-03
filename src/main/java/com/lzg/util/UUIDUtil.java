package com.lzg.util;

import java.util.UUID;

/**
 * 
 * @author lzg
 * @date 2016年2月15日
 */
public class UUIDUtil {
	public static String getUUIDString(){
		return UUID.randomUUID().toString();
	}
}