package com.dirk.base.biz.impl;

import com.alibaba.fastjson.JSON;
import com.cloud.frame.core.base.exception.BusinessException;
import com.cloud.frame.core.base.exception.ExceptionEnum;
import com.dirk.base.biz.UserService;
import com.dirk.prod.api.ProdServiceApi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ProdServiceApi prodService;

	@Override
	//@HystrixCommand(fallbackMethod = "handleError")
	public void test(Map<String, Object> params) throws Exception{
		prodService.getProdList(params);
		//throw new BusinessException(ExceptionEnum.HystrixException.getCode(), ExceptionEnum.HystrixException.getMessage());
	}

	/*private void handleError(Map<String, Object> params) throws Exception {
		System.out.println("come into Hystrix method");
		System.out.println("params is: "+JSON.toJSONString(params));
		throw new Exception(ExceptionEnum.HystrixException.getMessage());
	}*/
}
