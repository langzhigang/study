package com.lzg.dbhelper;

import org.apache.log4j.Logger;

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

	private static Logger log = Logger.getLogger(DBContextHolder.class);

	public static String DB_RW="readwritedb";	//和applicationContenx-dataSource 中动态数据源的配置key对应;
	public static String DB_R="readdb";
	
    public static String getDbType() {
    	String db = contextHolder.get();
        if (db == null) {
            db = DB_RW;// 默认是读写库
        }
//        log.debug("动态选定的数据库是:"+db);
        return db; 
    }
    
    /**
     * 功能说明：设置本次操作的 数据源
     */
    public static void setDbType(String str) {
//    	log.debug("动态设定的数据库为:"+str);
        contextHolder.set(str);
    }

    /**
     * 清理连接类型
     */
    public static void clearDBType() {
        contextHolder.remove();
    }
}
