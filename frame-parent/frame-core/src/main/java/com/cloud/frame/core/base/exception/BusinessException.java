package com.cloud.frame.core.base.exception;

public class BusinessException extends RuntimeException {

	/* 返回值*/
	private String code;
	/* 返回消息*/
	private String message;
	/* 异常类型*/
	private Throwable exception;

	public BusinessException(String message){
		super(message);
		this.message = message;
	}

	public BusinessException(String code, String message){
		super();
		this.code = code;
		this.message = message;
	}

	public BusinessException(String code, String message, Throwable exception){
		super();
		this.code = code;
		this.message = message;
		this.exception = exception;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}
}
