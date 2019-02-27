package com.cloud.frame.common.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

	 public static String encrypt(String content, String strKey)
	  {
	    try
	    {
	      SecretKeySpec key = new SecretKeySpec(strKey.getBytes(), "AES");
	      byte[] byteContent = content.getBytes("utf-8");
	      

	      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	      
	      cipher.init(1, key);
	      return Base64.encode(cipher.doFinal(byteContent));
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      throw new RuntimeException(e);
	    }
	  }
	  
	  public static String decrypt(String ciphertext, String strKey)
	  {
	    try
	    {
	      SecretKeySpec key = new SecretKeySpec(strKey.getBytes(), "AES");
	      byte[] cipherbytes = Base64.decode(ciphertext);
	      
	      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	      cipher.init(2, key);
	      byte[] result = cipher.doFinal(cipherbytes);
	      return new String(result, "utf-8");
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      throw new RuntimeException(e);
	    }
	  }
}
