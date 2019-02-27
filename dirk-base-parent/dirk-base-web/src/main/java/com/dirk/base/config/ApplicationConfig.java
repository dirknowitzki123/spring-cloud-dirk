package com.dirk.base.config;

import com.cloud.frame.cache.config.RedisConfig;
import com.cloud.frame.core.base.activemq.config.ActivemqConfig;
import com.cloud.frame.core.base.kafka.config.KafkaConfig;
import com.cloud.frame.core.base.kafka.consumer.KafkaReceiver;
import com.cloud.frame.core.base.kafka.producer.KafkaSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={
		RedisConfig.class,
		ActivemqConfig.class
		//KafkaConfig.class
})
public class ApplicationConfig {

	/*@Bean
	public Producer producer(){
		return new Producer();
	} */

	@Bean
	public KafkaSender sender(){
		return new KafkaSender();
	}

	@Bean
	public KafkaReceiver receiver(){
		return new KafkaReceiver();
	}

}
