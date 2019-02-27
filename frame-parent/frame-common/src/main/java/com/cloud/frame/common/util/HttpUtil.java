package com.cloud.frame.common.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * http工具类
 * 
 */
public class HttpUtil {
	public static final String STATUS = "status";
	public static String getDomain(HttpServletRequest request){
		return request.getServerName();
	}
	
	public static String getHttpDomain(HttpServletRequest request){
		return request.getScheme() + "://" + request.getServerName();
	}
	
	public static String getContextHttpUri(HttpServletRequest request){
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
	
	public static String getRealPath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	public static String[] getParamsToArray(HttpServletRequest request) {
		String param = request.getQueryString();
		String[] arr = null;
		if(param != null) {
			arr = param.split("[&]");
		}
		
		return arr;
	}
	
	public static Map<String,String> getParamsToMap(HttpServletRequest request) {
		Map<String,String> map = null;
		
		String[] arr = getParamsToArray(request);
		if(arr != null && arr.length > 0) {
			String[] sbArr = null;
			map = new HashMap<String,String>();
			
			for(String temp : arr) {
				sbArr = temp.split("[=]");
				if(sbArr.length > 1) {
					map.put(sbArr[0], sbArr[1]);
				}
			}
		}
		
		return map;
	}
	
	public static String getParamsByKey(HttpServletRequest request,String key) {
		Map<String,String> map = getParamsToMap(request);
		if(map != null) {
			return map.get(key);
		}
		
		return null;
	}
	
}
