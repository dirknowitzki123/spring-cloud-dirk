package com.cloud.frame.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.spec.KeySpec;
import java.util.List;


/**
 * 文件上传下载信息加解密
 * @author hejian
 *
 */
public class FileEncryUtil {

	/**
	 * 把传输的数据加密
	 * @return
	 * @throws Exception 
	 */
	public static String encryptData(List<String> datas) {
		String boud = "----------boud";
		StringBuffer sb = new StringBuffer();
		for(String str : datas){
			String item = FileEncryUtil.encryptStr(str);
			item = item + boud;
			sb.append(item);
		}
		if(sb.length()>0){
			sb.delete(sb.length()-boud.length(),sb.length());
		}
		String param = FileEncryUtil.encryptStr(sb.toString());
		return param;
	}
	

	/** 
	 * 字符串加密 
	 * @param passWord 加密密码 
	 * @return 
	 * @throws Exception 
	 */  
	public static String encryptStr(String passWord) {
		try{
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");  
		    KeySpec keySpec = new PBEKeySpec("123123123".toCharArray());  
		    SecretKey secretKey = keyFactory.generateSecret(keySpec);  
		    PBEParameterSpec parameterSpec = new PBEParameterSpec(new byte[]{1,2,3,4,5,6,7,8},1000);  
		    Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");  
		    cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);  
		    byte passEn[] = cipher.doFinal(passWord.getBytes());  
		    return bytesToHexString(passEn);  
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	    
	}
	  
	/** 
	 * 字符串解密 
	 * @param passWordEn 加密后的密码 
	 * @return 
	 * @throws Exception 
	 */  
	public static String decryptStr(String passWordEn) throws Exception {  
		try{
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");  
		    KeySpec keySpec = new PBEKeySpec("123123123".toCharArray());  
		    SecretKey secretKey = keyFactory.generateSecret(keySpec);  
		    PBEParameterSpec parameterSpec = new PBEParameterSpec(new byte[]{1,2,3,4,5,6,7,8},1000);  
		    Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");  
		    cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);  
		    byte[] passDec = cipher.doFinal(hexStringToBytes(passWordEn));  
			return new String(passDec);  
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	   
	}  
	  
	public static String bytesToHexString(byte[] src) {  
	    StringBuilder stringBuilder = new StringBuilder("");  
	    if (src == null || src.length <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < src.length; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v);  
	        if (hv.length() < 2) {  
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv);  
	    }  
	    return stringBuilder.toString();  
	}  

	public static byte[] hexStringToBytes(String hexString) {  
	    if (hexString == null || hexString.equals("")) {  
	        return null;  
	    }
	    hexString = hexString.toUpperCase();  
	    int length = hexString.length() / 2;  
	    char[] hexChars = hexString.toCharArray();  
	    byte[] d = new byte[length];  
	    for (int i = 0; i < length; i++){  
	        int pos = i * 2;  
	        d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
	    }
	    return d;  
	}  

	private static byte charToByte(char c) {  
	    return (byte)"0123456789ABCDEF".indexOf(c);  
	}
	
}

