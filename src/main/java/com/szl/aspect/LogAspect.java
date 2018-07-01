package com.szl.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.szl.vo.ResultVo;

/**
 * 日志记录
 * @author szl
 * @data 2018年6月30日 下午5:33:05
 *
 */
@Aspect
@Component
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Pointcut("execution(* com.szl.controller.*.*(..))")
	public void log(){
		
	}
	
	@Before("log()")
	public void doBefore(JoinPoint joinPoint){
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		//url
		logger.info("url={}", request.getRequestURI());
		//method
		logger.info("method={}", request.getMethod());
		//ip
		logger.info("ip={}", request.getRemoteAddr());
		//类方法
		logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		//参数
		logger.info("args={}", joinPoint.getArgs());
	}
	
	@After("log()")
	public void doAfter(){
		
	}
	
	@AfterReturning(pointcut = "log()", returning = "object")
	public void doAfterReturning(Object object){
		if(object instanceof ResultVo){
			ResultVo resultVo = (ResultVo)object;
			if(resultVo.getData() != null){
				logger.info("response={}", resultVo.getData().toString());
			}
		}else{
			logger.info("response={}", object);
		}
	}
}
