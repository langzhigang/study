package com.lzg.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lzg.dbhelper.DBContextHolder;

/**
 * 动态切换数据源的切面
 * @author lzg
 * @date 2016年1月22日
 */
@Order(0)
//声明这是一个组件
@Component
//声明这是一个切面Bean
@Aspect
public class DetermineDataSourceAspect {
	
	private final static Log log = LogFactory.getLog(DetermineDataSourceAspect.class);
	
	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	//@Pointcut("execution(* com.lzg.service..*(..))")
	//public void aspect(){}
	
	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
//	@Before("aspect()")
//	public void before(JoinPoint joinPoint){
//	}
	
	//配置后置通知,使用在方法aspect()上注册的切入点
//	@After("aspect()")
//	public void after(JoinPoint joinPoint){
//	}
	
	//配置环绕通知,使用在方法aspect()上注册的切入点
	@Around(value="execution(* com.lzg.service..*(..)) && @annotation(tra)")
	public Object around(JoinPoint joinPoint,Transactional tra){
		ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) joinPoint;
		if(tra.readOnly()){
			DBContextHolder.setDbType(DBContextHolder.DB_R);
		}
		log.debug("选择的数据源为：" + DBContextHolder.getDbType());
		try {
			return proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			throw new RuntimeException();
		}finally {
			DBContextHolder.clearDBType();
		}
	}
	
	//配置后置返回通知,使用在方法aspect()上注册的切入点
//	@AfterReturning("aspect()")
//	public void afterReturn(JoinPoint joinPoint){
//	}
	
	//配置抛出异常后通知,使用在方法aspect()上注册的切入点
//	@AfterThrowing(pointcut="aspect()", throwing="ex")
//	public void afterThrow(JoinPoint joinPoint, Exception ex){
//	}
}
