package com.szl.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.szl.common.ResultEnum;
import com.szl.vo.ResultVo;

/**
 * 统一异常处理
 * @author szl
 * @date 2018年6月30日 下午9:03:30
 *
 */
@RestControllerAdvice
public class GloabException {

	private static final Logger logger = LoggerFactory.getLogger(GloabException.class);
	@ExceptionHandler(value = Exception.class)
	public ResultVo handler(Exception e){
		if(e instanceof ThrowsException){
			ThrowsException throwsException = (ThrowsException) e;
			return ResultVo.get(throwsException.getCode(), throwsException.getMessage());
		}else{
			logger.error("【系统异常】{}", e);
			return ResultVo.get(ResultEnum.UNKNOWN_ERROR);
		}
	}
}
