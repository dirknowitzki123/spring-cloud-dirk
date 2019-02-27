package com.cloud.frame.cache.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

public class RedisUtil {

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 读取缓存
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		ValueOperations operations = redisTemplate.opsForValue();
		Object result = operations.get(key);
		return result;
	}

	/**
	 * 写入缓存
	 * @param key
	 * @param value
	 * @param expireTime 单位:秒
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations operations = redisTemplate.opsForValue();
			operations.set(key, value, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
