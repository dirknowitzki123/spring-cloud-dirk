package com.cloud.frame.common.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * base64工具类
 * @author HeJian
 *
 */
public class Base64Util {

	/**
     * 编码
     *return
     */
    public static String encodeStr(String plainText){
        try {
			byte[] b=plainText.getBytes("UTF-8");
			Base64 base64=new Base64();
			b=base64.encode(b);
			String s=new String(b);
			return s;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
        
    }
    
    /**
     * 解码
     * 修改日期
     *return
     */
    public static String decodeStr(String encodeStr){
    	
        try {
			byte[] b=encodeStr.getBytes();
			Base64 base64=new Base64();
			b=base64.decode(b);
			String s=new String(b,"UTF-8");
			return s;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
    }
	
    public static void main(String[] args) {
    	String s = "ttxs%^7你好a123";
    	String encodeStr = encodeStr(s);
    	String decodeStr = decodeStr(encodeStr);
    	System.out.println(decodeStr);
		System.out.println(decodeStr);
	}
	
}
