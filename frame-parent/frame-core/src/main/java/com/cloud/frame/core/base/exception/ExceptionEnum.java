package com.cloud.frame.core.base.exception;

public enum ExceptionEnum {

	ERROR("9999", "失败"),

	UnknowException("9998", "未知异常"),
	UserNameNullException("9997", "UserName为空"),
	HystrixException("9996", "HystrixRuntimeException")

	;

	private String code;

	private String message;

	ExceptionEnum(String code, String message){
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
