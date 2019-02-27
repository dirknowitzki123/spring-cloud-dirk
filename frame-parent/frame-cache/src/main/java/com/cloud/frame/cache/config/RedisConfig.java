package com.cloud.frame.cache.config;

import com.cloud.frame.cache.util.RedisUtil;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableCaching
@PropertySource(value={"classpath:config/cache.properties"})
public class RedisConfig /*extends CachingConfigurerSupport*/ {

	@Bean
	public RedisUtil redisUtil(){
		return new RedisUtil();
	}

}
