package com.dirk.base.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "dirk-base-service")
public interface BaseServiceApi{

	/** 请求前缀 **/
	//String REQUEST_PREFIX = "/user";

	/*@RequestMapping(value = "/prod/getProdList", method = RequestMethod.POST)
	void getProdList(@RequestBody Map<String, Object> params);*/
}
