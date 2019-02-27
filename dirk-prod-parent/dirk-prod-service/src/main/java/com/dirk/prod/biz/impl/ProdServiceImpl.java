package com.dirk.prod.biz.impl;

import com.dirk.prod.biz.IProdService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProdServiceImpl implements IProdService {

	@Override
	public void getProdList(Map<String, Object> params) {
		System.out.println("come-into prod biz");
		System.out.println("value is:"+params.get("key"));
	}
}
