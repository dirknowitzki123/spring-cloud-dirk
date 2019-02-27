package com.cloud.frame.core.base.exception;

public class Result<T> {
	/* 返回值*/
	private String code;
	/* 返回消息*/
	private String message;
	/* 返回数据*/
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
