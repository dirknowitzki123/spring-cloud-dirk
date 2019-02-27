package com.cloud.frame.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Properties;

/**
 * 获取资源工具,编码为UTF-8
 * @author HeJian
 *
 */
public class SourceUtil {
	private static final Logger logger = LoggerFactory.getLogger(SourceUtil.class);
	
	/**
	 * 获取资源
	 * @param classpath
	 * @return
	 */
	public static URL getSource(String classpath){
		try {
			URL url = ResourceUtils.getURL(classpath);
			return url;
		} catch (FileNotFoundException e) {
			logger.error("资源:"+classpath+"不存在!");
			e.printStackTrace();
			throw new RuntimeException("资源:"+classpath+"不存在!");
		}
	}
	
	/**
	 * 获取类路径下的properties文件,
	 * @param classpaths 
	 * @return
	 */
	public static Properties getProperties(String... classpaths){
		if(classpaths==null || classpaths.length==0) return null;
		Properties propers = new Properties();
		for(String classpath : classpaths){
			if(classpath==null || classpath.trim().length()==0) continue;
			URL source = getSource(classpath);
			Properties proper = new Properties();
			try(InputStreamReader reader = new InputStreamReader(source.openStream(), "UTF-8");) {
				proper.load(reader);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("加载资源:"+classpath+"失败!");
			}
			propers.putAll(proper);
		}
		return propers;
	}
	
	/**
	 * 获取properties中的键值对值
	 * @param key
	 * @param classpaths
	 * @return
	 */
	public static String get(String key, String... classpaths){
		Properties properties = getProperties(classpaths);
		String rs = properties.getProperty(key);
		return rs;
	}
	
	/**
	 * 获取properties中的boolean键值对值
	 * @param key
	 * @param classpaths
	 * @return
	 */
	public static Boolean getBool(String key, String... classpaths){
		String rs =  get(key,classpaths);
		if(rs==null || rs.trim().length()==0) return null;
		return Boolean.valueOf(rs);
	}
	
	/**
	 * 获取properties中的int键值对值
	 * @param key
	 * @param classpaths
	 * @return
	 */
	public static Integer getInt(String key, String... classpaths){
		String rs =  get(key,classpaths);
		if(rs==null || rs.trim().length()==0) return null;
		return Integer.valueOf(rs);
	}
	
	/**
	 * 获取properties中的BigDecimal键值对值
	 * @param key
	 * @param classpaths
	 * @return
	 */
	public static BigDecimal getBigDecimal(String key, String... classpaths){
		String rs =  get(key,classpaths);
		if(rs==null || rs.trim().length()==0) return null;
		return new BigDecimal(rs);
	}
	
	
}
