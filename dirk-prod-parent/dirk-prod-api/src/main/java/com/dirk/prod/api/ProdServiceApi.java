package com.dirk.prod.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "dirk-prod-service")
public interface ProdServiceApi {

	String REQUEST_PREFIX = "/prod";

	@RequestMapping(value = REQUEST_PREFIX + "/getProdList", method = RequestMethod.POST)
	void getProdList(@RequestBody Map<String, Object> params);
}
