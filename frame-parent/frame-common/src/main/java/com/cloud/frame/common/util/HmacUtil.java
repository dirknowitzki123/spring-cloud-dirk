package com.cloud.frame.common.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacUtil {

	/**
	 * HMACSHA256单向散列加密
	 * @param data 待加密数据
	 * @param key 加密key
	 * @return 加密失败返回null
	 */
	public static String encryptHMACSHA256(String data,String key) 	{
		try {
			return HMACSHA256(data.getBytes("UTF-8"),key.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * HMACSHA256单向散列加密
	 * @param data 待加密数据
	 * @param key 加密key
	 * @return
	 */
	public static String HMACSHA256(byte[] data, byte[] key) 	{
	      try  {
	         SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
	         Mac mac = Mac.getInstance("HmacSHA256");
	         mac.init(signingKey);
	         return byte2hex(mac.doFinal(data));
	      } catch (NoSuchAlgorithmException e) {
	         e.printStackTrace();
	      } catch (InvalidKeyException e) {
	        e.printStackTrace();
	      }
	      return null;
	} 
	
	/**
	 * 转换字节数组为十六进制字符串 
	 * @param b 字节数字
	 * @return 16进制字符串
	 */
	public static String byte2hex(byte[] b) {
	    StringBuilder hs = new StringBuilder();
	    String stmp;
	    for (int n = 0; b!=null && n < b.length; n++) {
	        stmp = Integer.toHexString(b[n] & 0XFF);
	        if (stmp.length() == 1)
	            hs.append('0');
	        hs.append(stmp);
	    }
	    return hs.toString();//.toUpperCase();
	}
	
	public static void main(String[] args) {
		System.out.println(encryptHMACSHA256("123456","12345678"));
	}
	
	
}
