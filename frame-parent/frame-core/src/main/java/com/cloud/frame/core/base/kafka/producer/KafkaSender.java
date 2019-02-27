package com.cloud.frame.core.base.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.cloud.frame.common.util.StringUtils;
import com.cloud.frame.core.base.kafka.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class KafkaSender {
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void send(String topicName){
		Message message = new Message();
		message.setId(StringUtils.getUUID());
		message.setMsg("This is first Kafka message");
		message.setSendTime(new Date());
		kafkaTemplate.send(topicName, JSON.toJSONString(message));
	}
}
