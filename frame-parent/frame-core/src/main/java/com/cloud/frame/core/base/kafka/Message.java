package com.cloud.frame.core.base.kafka;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
	private String id;
	private String msg;
	private Date sendTime;
}
