package com.dirk.prod.config;

import com.cloud.frame.cache.config.RedisConfig;
import com.cloud.frame.core.base.activemq.config.ActivemqConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={
		RedisConfig.class,
		ActivemqConfig.class
})
public class ApplicationConfig {

	/*@Bean
	public Producer producer(){
		return new Producer();
	} */

	//消费者不需要在此定义，在服务中定义


}
