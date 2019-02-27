package com.cloud.frame.core.base.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaReceiver {

	@KafkaListener(topics = {"dirkTest"})
	public void listen(ConsumerRecord<String, Object> record){
		log.info("----------------- receiving message ---------");
		Object kafkaMessage = record.value();
		//if (kafkaMessage.isPresent()) {

		//	Object message = kafkaMessage.get();

			log.info("----------------- record =" + record);
			log.info("------------------ message =" + kafkaMessage);
		//}
	}

}
