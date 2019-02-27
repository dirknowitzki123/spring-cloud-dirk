package com.cloud.frame.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.*;

/**
 * web工具类
 * @author HeJian
 *
 */
public class WebUtil {
	
	/**
	 * 获取客户端真实IP地址
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }
	
	/**
	 * 获取所有参数
	 * @param request
	 * @return
	 */
	public static Map<String,String> getAllParam(HttpServletRequest request){
		Enumeration<String> enu = request.getParameterNames();
		if(enu==null ){
			return null;
		}
		if( !enu.hasMoreElements()) return null;
		Map<String,String> params = new HashMap<String,String>();
		while(enu.hasMoreElements()){
			String key = enu.nextElement();
			String value = request.getParameter(key);
			params.put(key, value);
		}
		return params;
	}
	
	/**
	 * 获取请求的json数据
	 * @param request
	 * @return
	 */
	public static String getJsonData(HttpServletRequest request){
		BufferedReader br =null;
		try{
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			String jsondata = sb.toString();
			return jsondata;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 参数排序 返回示例: abc=首部&abd=456&dda=789
	 * @param params
	 * @return
	 */
	public static String paramSort(Map<String,String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
	    StringBuilder sb = new StringBuilder();
	    String key = null;
	    String value = null;
	    for (int i = 0; i < keys.size(); i++) {
	    	key = (String)keys.get(i);
	    	value = (String)params.get(key);
	    	value= (value==null || value.trim().length()==0)? "" : value;
	    	sb.append(key).append("=").append(value);
	    	if (i < keys.size() - 1) { 
	    		sb.append("&");
	    	}
	    }
	    return sb.toString();
	}
	
	/**
	 * 响应json,返回json字符串
	 * @param obj
	 */
	public static String writeJson(HttpServletResponse resp ,Object obj){
		String json = JSONUtil.toJson(obj);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		resp.setContentLength(json.getBytes(Charset.forName("utf-8")).length);
		try {
			PrintWriter writer = resp.getWriter();
			writer.print(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public static void main(String[] args) {
		Map<String,String> pMap = new HashMap<String,String>();
		pMap.put("abc", "首部");
		pMap.put("dda", "789");
		pMap.put("abd", null);
		String paramSort = paramSort(pMap);
		System.out.println(paramSort);
		
		long currentTimeMillis = System.currentTimeMillis();
		long time = new Date().getTime();
		System.out.println(currentTimeMillis);
		System.out.println(time);
		
		
	}
}
