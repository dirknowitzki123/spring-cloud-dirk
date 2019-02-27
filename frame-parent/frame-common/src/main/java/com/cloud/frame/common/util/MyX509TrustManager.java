package com.cloud.frame.common.util;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class MyX509TrustManager implements X509TrustManager {

	@Override  
    public void checkClientTrusted(X509Certificate[] chain, String authType)  
            throws CertificateException {  
          
          
    }  
  
    @Override  
    public void checkServerTrusted(X509Certificate[] chain, String authType)  
            throws CertificateException {  
          
          
    }  
  
    @Override  
    public X509Certificate[] getAcceptedIssuers() {  
  
        return null;  
    } 

    public static SSLSocketFactory getSSFactory() throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException{  
        TrustManager[] tm = { new MyX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
        sslContext.init(null, tm, new java.security.SecureRandom());  
        SSLSocketFactory ssf = sslContext.getSocketFactory();  
        return  ssf;  
    }  
}
