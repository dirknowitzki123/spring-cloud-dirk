package com.dirk.base.controller.user;

import com.cloud.frame.cache.util.RedisUtil;
import com.cloud.frame.core.base.exception.*;
import com.cloud.frame.core.base.kafka.producer.KafkaSender;
import com.dirk.base.biz.UserService;
import com.dirk.prod.api.ProdServiceApi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.jms.Topic;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Map;

@RestController
public class UserController extends GlobalExceptionHandler {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisUtil redisUtil;

	//@Autowired
	//private Producer producer;

	@Autowired
	private Topic topic;

	@Autowired
	private Queue queue;

	//@Autowired
	//private JmsMessagingTemplate messagingTemplate;

	@Autowired
	private KafkaSender kafkaSender;

	@Value("${ribbon.ReadTimeout}")
	private String ribbonTime;

	@RequestMapping(value = "/user/test", method = RequestMethod.POST)
	public Result<Map<String, Object>> test(@RequestBody Map<String, Object> map) throws Exception{
		userService.test(map);
		map.put("ribbonTime", ribbonTime);
		return ResultUtil.getSuccess(map);
	}

	@GetMapping(value = "/gc/test")
	public String testGc() {
		List<GarbageCollectorMXBean> list = ManagementFactory.getGarbageCollectorMXBeans();
		StringBuffer sb = new StringBuffer();
		for (GarbageCollectorMXBean garbageCollectorMXBean : list) {
			sb.append(garbageCollectorMXBean.getName() + '\n');
		}
		return sb.toString();
	}

	/*@PostMapping("/testRedis")
	public Result<Map<String, Object>> testRedis(@RequestBody Map<String, Object> map) {
		//userService.test();
		redisUtil.set("key", "value3", 60L);
		if (map.get("userName") == null) {
			throw new BusinessException(ExceptionEnum.UserNameNullException.getCode(), ExceptionEnum.UserNameNullException.getMessage());
		}
		map.put("key", redisUtil.get("key"));
		return ResultUtil.getSuccess(map);
	}

	@PostMapping("/testActivemq")
	public Result<Map<String, Object>> testActivemq(@RequestBody Map<String, Object> map) throws Exception{
		//producer.sendMessage(topic, "Hello World1");
		//producer.sendMessage(queue, "Hello World2");
		messagingTemplate.convertAndSend(topic, "Hello World1");
		return ResultUtil.getSuccess(map);
	}

	@PostMapping("/testKafka")
	public Result<Map<String, Object>> testKafka(@RequestBody Map<String, Object> map) throws Exception{
		kafkaSender.send("dirkTest");
		return ResultUtil.getSuccess(map);
	}
*/

}
