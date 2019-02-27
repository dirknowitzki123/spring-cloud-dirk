package com.cloud.frame.core.base.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
@EnableJms
@PropertySource(value={"classpath:config/jms.properties"})
public class ActivemqConfig {

	@Value("${spring.activemq.user}")
	private String usrName;

	@Value("${spring.activemq.password}")
	private  String password;

	@Value("${spring.activemq.broker-url}")
	private  String brokerUrl;

	/**
	 * 配置Queue
	 * @return
	 */
	@Bean
	public Queue queue(){
		return new ActiveMQQueue("dirkTest.queue");
	}

	/**
	 * 配置Topic
	 * @return
	 */
	@Bean
	public Topic topic(){
		return new ActiveMQTopic("dirkTest.topic");
	}

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(usrName, password, brokerUrl);
	}

	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory){
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setConnectionFactory(connectionFactory);
		return bean;
	}

	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory){
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		//设置为发布订阅方式, 默认情况下使用的生产消费者方式
		bean.setPubSubDomain(true);
		bean.setConnectionFactory(connectionFactory);
		return bean;
	}
}
