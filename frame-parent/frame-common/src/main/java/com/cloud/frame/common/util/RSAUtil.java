package com.cloud.frame.common.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {

	  public static String pubKeyEnc(String content, String pubKey)
	  {
	    try
	    {
	      KeyFactory keyf = KeyFactory.getInstance("RSA");
	      
	      InputStream is = new ByteArrayInputStream(pubKey.getBytes("utf-8"));
	      byte[] pubbytes = new byte[new Long(pubKey.length()).intValue()];
	      is.read(pubbytes);
	      X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decode(new String(pubbytes)));
	      PublicKey pkey = keyf.generatePublic(pubX509);

	      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	      cipher.init(1, pkey);
	      byte[] cipherText = cipher.doFinal(content.getBytes());
	      return Base64.encode(cipherText);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      throw new RuntimeException(e);
	    }
	  }
	  
	  public static String pubKeyDec(String ciphertext, String pubKey)
	  {
	    try
	    {
	      KeyFactory keyf = KeyFactory.getInstance("RSA");
	      

	      InputStream is = new ByteArrayInputStream(pubKey.getBytes("utf-8"));
	      byte[] pubbytes = new byte[new Long(pubKey.length()).intValue()];
	      is.read(pubbytes);
	      X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decode(new String(pubbytes)));
	      PublicKey pkey = keyf.generatePublic(pubX509);
	      

	      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	      cipher.init(2, pkey);
	      byte[] text = cipher.doFinal(Base64.decode(ciphertext));
	      
	      return new String(text, "UTF-8");
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      throw new RuntimeException(e);
	    }
	  }
}
