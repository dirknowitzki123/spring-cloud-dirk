package com.cloud.frame.core.base.exception;

import com.netflix.client.ClientException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.thoughtworks.xstream.core.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public Object handleBusinessException(Exception e){
		if (e instanceof BusinessException){
			BusinessException businessException = (BusinessException) e;
			return ResultUtil.getError(businessException.getCode(), businessException.getMessage());
		} else {
			logger.info("系统异常:"+e);
			e.printStackTrace();
			return ResultUtil.getError(ExceptionEnum.ERROR.getCode(), ExceptionEnum.ERROR.getMessage());
		}
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Object handleException(Exception e){
		e.printStackTrace();
		return ResultUtil.getError(ExceptionEnum.ERROR.getCode(), ExceptionEnum.ERROR.getMessage());
	}

	/*@ExceptionHandler(value = HystrixRuntimeException.class)
	@ResponseBody
	public Object handleHystrixRuntimeException(Exception e){
		return ResultUtil.getError(ExceptionEnum.HystrixException.getCode(),
					ExceptionEnum.HystrixException.getMessage());
	}*/
}
