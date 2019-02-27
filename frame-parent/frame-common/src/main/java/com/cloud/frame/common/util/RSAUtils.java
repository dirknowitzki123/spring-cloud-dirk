package com.cloud.frame.common.util;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils {
	
	/** 加密算法RSA*/  
    public static final String KEY_ALGORITHM = "RSA";  
    /** 签名算法 */  
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";  
    /** 获取公钥的key*/  
    private static final String PUBLIC_KEY = "RSAPublicKey";  
    /** 获取私钥的key*/  
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    /** RSA密文初始大小*/  
    private  int RSA_KEYSIZE = 1024;
    /** RSA最大加密明文大小*/  
    private static  int MAX_ENCRYPT_BLOCK = 117;
    /** RSA最大解密密文大小 */  
    private static  int MAX_DECRYPT_BLOCK = 128;
    
    public RSAUtils(int keysize){
    	RSA_KEYSIZE = keysize;
    	MAX_DECRYPT_BLOCK = RSA_KEYSIZE/8;
    	MAX_ENCRYPT_BLOCK = RSA_KEYSIZE/8-11;
    }
    
    public static RSAUtils generate(int keysize){
    	return new RSAUtils(keysize);
    }
    
    public static RSAUtils generate(){
    	return new RSAUtils(1024);
    }
    
    /**
     * 生成密钥对(公钥和私钥)
     * @return
     * @throws Exception
     */
    public Map<String, String> genKeyPair() throws Exception {  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);  
        keyPairGen.initialize(RSA_KEYSIZE,new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        Map<String, String> keyMap = new HashMap<String, String>(2);  
        keyMap.put(PUBLIC_KEY, Base64.getEncoder().encodeToString(publicKey.getEncoded()));  
        keyMap.put(PRIVATE_KEY, Base64.getEncoder().encodeToString(privateKey.getEncoded()));  
        return keyMap;  
    }
    /**
     * 生成密钥对(公钥和私钥)
     * @param secureRandom
     * @return
     * @throws Exception
     */
    public Map<String, String> genKeyPair(SecureRandom secureRandom) throws Exception {  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);  
        keyPairGen.initialize(RSA_KEYSIZE,secureRandom);
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        Map<String, String> keyMap = new HashMap<String, String>(2);  
        keyMap.put(PUBLIC_KEY, Base64.getEncoder().encodeToString(publicKey.getEncoded()));  
        keyMap.put(PRIVATE_KEY, Base64.getEncoder().encodeToString(privateKey.getEncoded()));  
        return keyMap;  
    }
    
    /**
     * 用私钥对信息生成数字签名
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String sign(byte[] encryptedData, String privateKey) throws Exception {  
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initSign(privateK);  
        signature.update(encryptedData);  
        return Base64.getEncoder().encodeToString(signature.sign());  
    }
    /**
     * 校验数字签名
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] encryptedData, String publicKey, String sign)  
            throws Exception {  
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);  
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PublicKey publicK = keyFactory.generatePublic(keySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initVerify(publicK);  
        signature.update(encryptedData);  
        return signature.verify(Base64.getDecoder().decode(sign));  
    }
    
    /**
     * 私钥解密数据
     * @param encryptedData 已加密数据 
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)  
            throws Exception {  
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, privateK);  
        int inputLen = encryptedData.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段解密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_DECRYPT_BLOCK;  
        }  
        byte[] decryptedData = out.toByteArray();  
        out.close();  
        return decryptedData;  
    }
    /**
     * 公钥解密 
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)  
            throws Exception {  
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key publicK = keyFactory.generatePublic(x509KeySpec);  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, publicK);  
        int inputLen = encryptedData.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段解密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_DECRYPT_BLOCK;  
        }  
        byte[] decryptedData = out.toByteArray();  
        out.close();  
        return decryptedData;  
    }
    
    /**
     * 公钥加密
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码) 
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)  
            throws Exception {  
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key publicK = keyFactory.generatePublic(x509KeySpec);  
        // 对数据加密  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, publicK);  
        int inputLen = data.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段加密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(data, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_ENCRYPT_BLOCK;  
        }  
        byte[] encryptedData = out.toByteArray();  
        out.close();  
        return encryptedData;  
    }
    
    /**
     * 私钥加密 
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public byte[] encryptByPrivateKey(byte[] data, String privateKey)  
            throws Exception {  
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, privateK);  
        int inputLen = data.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段加密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(data, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_ENCRYPT_BLOCK;  
        }  
        byte[] encryptedData = out.toByteArray();  
        out.close();  
        return encryptedData;  
    }
    public static void main(String[] args) {
    	String json="{'loginNo':'18782220000', 'loginNo':'18782220000', 'loginNo':'18782220000', 'loginNo':'18782220000', 'password':'123456', 'loginType':'24200004'}";
    	String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMBIfz6ZPcoiIHtuTaq16AsN9FFktlrKWW+/Hq2gaLlezCwEmlQv1WpiwB8XwvbrmHg8dWnu1VjT8ruvgHPB817k5Vy/mfofkQrCFnS1T5/IcxQ4m7xD9pZi0uV/0xYgjmPOHGPvBo7Iy1+FNMLDc9r/HdOezmVyAJXo3LkXiXchAgMBAAECgYBEC2O8K06KcXk9NNOXTbhH8TA8fX9qsaDkwqWAm/tzXfCyww46LJNBiqCiYC5GYykZo4uJaVNmk9qaQIkcbc5JcXIJumy46pBrsEsMsBkjGjh5WW5bFP3cNdbsITZuPb1nHv9H/qfa3mGhtvk+F8sixZ995VSDinxxyexbHXfAAQJBAOO9aq1obA4G8qWn7LxowUv3rJz4jffv/OQXVs1a2g1tI8h8LA4p2gEvgaBNmMiSt9dufIeyEkSpBqjZx6ArgGECQQDYJLtFmt4U9/duCmzT1lIKytdLXsgtjLk+lnJwCPmItrLyvvXCfIYVzG8mvzGQGoITcBLz8CmB8IwWyv1eFG7BAkB0kJbxeukTpOq9b130cYm+YF6xWWcQ6H0AIhIDueSxypLuIuBJv5Id2Tr32b/BqZb/ZUXIDpTbH8iQ1CchDCMBAkEAmZzZX6mmJj7pIdLU72UFX9gzVMSi/gRib0HbSRaHygsWeQEQhs2bOgjWAjKl1eWRBqGDRs0rosbDuTtBAMkCwQJBALK5s7suF+X6evNNuuptvuVGpkhzKU6c0TMWYXlLPVjo70ufqpzYC68DZlxc0jz5fBu3T2bk+xvPvtQKY76XlR8=";
    	try {
    		byte[] bytes=RSAUtils.generate().encryptByPrivateKey(json.getBytes(), privateKey);
    		String loginToken=Base64.getEncoder().encodeToString(bytes);
    		System.out.println(loginToken);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
