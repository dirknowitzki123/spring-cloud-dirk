package com.cloud.frame.common.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;


/**
 * 加密  解密工具
 * @author wl
 *
 */
public class Md5Utils {
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
	        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 对STR  MD5加密
	 * @param str 需要加密的str
	 * @return 加密后的数据
	 */
	public static String encode(String str){
		try {
			if(!StringUtils.isEmpty(str)){
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				byte[] encodeBase64 = Base64.encodeBase64(md5.digest(str.getBytes("utf-8")));
				String encodeStr = new String(encodeBase64);
				return encodeStr;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 对str 进行16位MD5加密
	 * @param str
	 * @return
	 */
	public static String encode16(String str) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            System.out.println("MD5(" + str + ",32) = " + result);
            System.out.println("MD5(" + str + ",16) = " + buf.toString().substring(8, 24));
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
	
	/**
	 * 对str 进行32位MD5加密
	 * @param str
	 * @return
	 */
	public static String encode32(String str) {
		MessageDigest md5 = null;
		StringBuffer buf = new StringBuffer("");  
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update((str).getBytes("UTF-8"));  
			byte b[] = md5.digest();  
			int i;  
			for(int offset=0; offset<b.length; offset++){  
			    i = b[offset];  
			    if(i<0){  
			        i+=256;  
			    }  
			    if(i<16){  
			        buf.append("0");  
			    }  
			    buf.append(Integer.toHexString(i));  
			}  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString();  
	}
	
	private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * md5散列签名
     * @param origin
     * @param charsetname
     * @return
     */
    public static String hash(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }
    
    
	public static void main(String[] args) {
		String h="asdf";
		System.out.println(encode(h));
		System.out.println(hash(h,"UTF-8"));
	}
}
