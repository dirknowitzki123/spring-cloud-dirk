package com.cloud.frame.common.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesCBCUtil {

	private static String encoding = "UTF-8";
	
	 // 加密
    public static String encrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        byte[] raw = sKey.getBytes(encodingFormat);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes(encodingFormat));//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码。
    }

    /**
	 * AES-128-CBC 加密
	 * @param key
	 * @param iv
	 * @param content
	 * @return
	 */
	public static String encrypt(String key, String iv, String content) {
		try {
			IvParameterSpec ivp = new IvParameterSpec(iv.getBytes(encoding));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(encoding), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivp);
			byte[] encrypted = cipher.doFinal(content.getBytes(encoding));

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
    
    
    // 解密
    public static String decrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
        try {
            byte[] raw = sKey.getBytes(encodingFormat);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes(encodingFormat));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,encodingFormat);
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
	 * AES-128-CBC 解密
	 * @param key
	 * @param iv
	 * @param content
	 * @return
	 */
	public static String decrypt(String key, String iv, String content) {
		try {
			IvParameterSpec ivp = new IvParameterSpec(iv.getBytes(encoding));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(encoding), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivp);
			byte[] original = cipher.doFinal(Base64.decodeBase64(content));

			return new String(original,encoding);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
    
    public static void main(String[] args) {
        String key = "1234567890123456"; // 128 bit key
        String initVector = "0000000000000000"; // 16 bytes IV
		try {
			String en = encrypt("asdfghjkl", "utf-8", key, initVector);
			System.out.println(en);
			System.out.println(decrypt(en, "utf-8", key, initVector));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
}
