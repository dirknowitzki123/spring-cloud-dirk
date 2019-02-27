package com.dirk.prod.controller.prod;

import com.dirk.prod.api.ProdServiceApi;
import com.dirk.prod.biz.IProdService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(ProdServiceApi.REQUEST_PREFIX)
public class ProdController implements ProdServiceApi{

	@Autowired
	private IProdService prodService;

	@RequestMapping(value = "/getProdList", method = RequestMethod.POST)
	public void getProdList(@RequestBody Map<String, Object> params) {
		prodService.getProdList(params);
	}
}
