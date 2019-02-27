package com.cloud.frame.core.base.exception;

public class ResultUtil {

	/**
	 * 操作成功的处理流程
	 * @param o
	 * @return
	 */
	public static Result getSuccess(Object o){
		Result result = new Result();
		result.setCode("0000");
		result.setMessage("成功");
		result.setData(o);
		return result;
	}

	/**
	 * 操作成功的处理流程(不需要返回数据的情况)
	 * @param o
	 * @return
	 */
	public static Result getSuccess(){
		return getSuccess(null);
	}
	/**
	 * 返回失败处理流程
	 * @param o
	 * @return
	 */
	public static Result getError(String code, String message, Object o){
		Result result = new Result();
		result.setCode(code);
		result.setMessage(message);
		result.setData(o);
		return result;
	}

	/**
	 * 返回失败处理流程(不需要返回数据的情况)
	 * @param
	 * @return
	 */
	public static Result getError(String code, String message){
		return getError(code, message, null);
	}
}
