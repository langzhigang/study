package com.lzg.dbhelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据源切换类
 * @author lzg
 * @date 2016年1月22日
 */
public class DBContextHolder {
	/**
	 * 把 数据库类型放到threadLocal中 确保数据源对线程独立
	*/
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    private static final ThreadLocal<Boolean> existWrite = new ThreadLocal<>();
    static{
    	existWrite.set(false);
    }
    private final static Logger log = LoggerFactory.getLogger(DBContextHolder.class);   

	public static String DB_RW="readwritedb";	//和applicationContenx-dataSource 中动态数据源的配置key对应;
	public static String DB_R="readdb";
	
    public static String getDbType() {
        return contextHolder.get(); 
    }
    
    /**
     * 功能说明：设置本次操作的 数据源
     */
    public static void setDbType(String str) {
    	if(existWrite.get()==null || existWrite.get()){	//如果先存在写操作，后面的读操作也用RW库
    		log.debug("之前存在写操作，强制使用RW库");
    		return;
    	}
    	if(DB_RW.equals(str)){
    		existWrite.set(true);
    	}
        contextHolder.set(str);
    }

    /**
     * 清理连接类型
     */
    public static void clearDBType() {
        contextHolder.remove();
        existWrite.set(false);
    }
}
