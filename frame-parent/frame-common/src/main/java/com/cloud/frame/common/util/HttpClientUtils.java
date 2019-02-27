package com.cloud.frame.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;


public class HttpClientUtils {
	
	 public static String doPost(String url,Map<String,String> map,String charset){  
	        HttpClient httpClient = null;  
	        HttpPost httpPost = null;  
	        String result = null;  
	        try{  
	            httpClient = new SSLClient();  
	            httpPost = new HttpPost(url);  
	            //设置参数  
	            List<NameValuePair> list = new ArrayList<NameValuePair>();  
	            Iterator iterator = map.entrySet().iterator();  
	            while(iterator.hasNext()){  
	                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
	                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
	            }  
	            if(list.size() > 0){  
	                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
	                httpPost.setEntity(entity);  
	            }  
	            HttpResponse response = httpClient.execute(httpPost);  
	            if(response != null){  
	                HttpEntity resEntity = response.getEntity();  
	                if(resEntity != null){  
	                    result = EntityUtils.toString(resEntity,charset);  
	                }  
	            }  
	        }catch(Exception ex){  
	            ex.printStackTrace();  
	        }  
	        return result;  
	    }
	 
	 public static String doJsonPost(String url,Map<String,Object> map,String charset){  
	        HttpClient httpClient = null;  
	        HttpPost httpPost = null;  
	        String result = null;  
	        try{  
	            httpClient = new SSLClient();  
	            httpPost = new HttpPost(url);
	            System.out.println(JSON.toJSONString(map));
	            //设置参数  
	    		StringEntity reqEntity= new StringEntity(JSON.toJSONString(map), ContentType.APPLICATION_JSON);
	    		httpPost.setEntity(reqEntity);
	    		httpPost.addHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
	    		//httpPost.addHeader("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBVVRIX0NFTlRFUiIsInN1YiI6IjE4NjE1NzIxNjM0IiwiYXVkIjoiQ0xJRU5UIiwiZXhwIjoxNTI1OTE4OTU2NDg0LCJuYmYiOjE1MjU5MTc3NTY0ODQsImlhdCI6MTUyNTkxNzc1NjQ4NCwianRpIjoiZTE0YjE0YmEzYWY1NGM4ZThjZGVmMTFiYmM4ZDU3ZWFzM3lnIiwiZ3JhIjoxNTI1OTIxMzU2NDg0LCJ0eXAiOiJVU0VSIn0=.34be5030d2a29ecfd048f82b92965c52c43668f9947f4e691ec78d2440bd144f");
	            HttpResponse response = httpClient.execute(httpPost);  
	            if(response != null){  
	                HttpEntity resEntity = response.getEntity();  
	                if(resEntity != null){  
	                	BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(resEntity);
	                	result = EntityUtils.toString(bufferedEntity, Charset.forName(charset));  
	                }
	                Header[] hearders = response.getAllHeaders();
	                if(hearders!=null){
	                	for(Header hear:hearders){
	                		if(hear.getName().equals("authorization")){
	                			System.out.println(hear.getValue());
	                		}
	                	}
	                }
	            }  
	        }catch(Exception ex){  
	            ex.printStackTrace();  
	        }  
	        return result;  
	    }
	 
	 public static void main(String[] args) {
		 Map<String,Object> objectMap = new HashMap<String, Object>();
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("params", objectMap);
		 objectMap.put("loginNo", "18615721634");
		 objectMap.put("loginType", "24200004");
		 objectMap.put("password", "123456");
		 objectMap.put("sourceOsType", "39300003");
		 doJsonPost("https://47.96.37.141:8443/bycx-passport-service/aSysLogin/cust/fpwd", map, "UTF-8");
	}

}
