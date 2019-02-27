package com.cloud.frame.common.util;

import java.net.*;
import java.util.Enumeration;

public class IpUtil {
	//单网卡名称
	private static final String NETWORK_CARD = "eth0";
	//绑定网卡名称
	private static final String NETWORK_CARD_BAND = "bond0";
	//window系统
	private static final String OS_WINDOWS="windows";
	//linux系统
	private static final String OS_LINUX="linux";
	/**
	 * 
	* @MethodName: getLocalIP 
	* @description : 获得本机IPv4 IP
	* @return String
	 */
	public static String getLocalIP(){
		String osName=System.getProperty("os.name").toLowerCase();
		String ip="";
		if(osName.contains(OS_WINDOWS)){
			ip=getWindowsLocalIp();
		}else if(osName.contains(OS_LINUX)){
			ip=getLinuxLocalIP();
		}
		return ip;
	}
	/**
	 * 
	* @MethodName: getWindowsLocalIp 
	* @description : 获取windows下本地IP地址
	* @return String
	 */
	public static String getWindowsLocalIp(){
		InetAddress addr;
		String ip="";
		try {
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString(); 
		} catch (UnknownHostException e) {
		}
		return ip;	
	}
	/**
	 * 
	* @MethodName: getLinuxLocalIP 
	* @description : 获取Linux下本地IP地址
	* @return String
	 */
	public static String getLinuxLocalIP(){
		String ip = "";
        try{
            Enumeration<NetworkInterface> e1 = (Enumeration<NetworkInterface>)NetworkInterface.getNetworkInterfaces();
            while (e1.hasMoreElements()){
                NetworkInterface ni = e1.nextElement();
                if ((NETWORK_CARD.equals(ni.getName()))|| (NETWORK_CARD_BAND.equals(ni.getName()))){
                    Enumeration<InetAddress> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()){
                        InetAddress ia = e2.nextElement();
                        if (ia instanceof Inet6Address){
                            continue;
                        }
                        ip = ia.getHostAddress();
                    }
                    break;
                }else{
                    continue;
                }
            }
        }catch (SocketException e){
        }
        return ip;
	}
	/**
	 * 
	* @MethodName: getOsName 
	* @description : 获取操作系统名称
	* @return String
	 */
	public static String getOsName() {
		return System.getProperty("os.name").toLowerCase();
	}
}
