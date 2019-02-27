package com.cloud.frame.common.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThirdPartyServiceCustomLogger {
	/**
	 * 调用第三方接口输出日志
	 */
	private static Logger thirdPartyServiceLogger = null;
	/**
	 * 业务操作日志输出
	 */
	private static Logger busiOperaLogger = null;
	/**
	 * 异常日志输出
	 */
	private static Logger errorLogger = null;

	private static ExecutorService executorService = Executors.newFixedThreadPool(10);

	static {
		loadLogger();
	}

	public ThirdPartyServiceCustomLogger() {
		super();
	}

	/**
	 * 装载系统使用的log
	 */
	static void loadLogger() {
		thirdPartyServiceLogger = LoggerFactory.getLogger("thirdPartyServiceLogger");
	}

	// 自定义的输出类型
	public static void thirdPartyServiceLogInfo(ThirdPartyServiceLog msg) {
		try {
			thirdPartyServiceLogger.info(StringUtils.unescapeJava(JSON.toJSONString(msg, true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
