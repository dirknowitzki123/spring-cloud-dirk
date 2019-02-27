package com.cloud.frame.common.util;

import java.io.UnsupportedEncodingException;

/**
 * 
 * 
 */
public class CommonEncryptUtil {
	
	private CommonEncryptUtil(){
		
	}
	
	private static char[] comAry = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
			'A', 'B', 'C', 'D', 'E', 'F', 'G','H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
			'a', 'b','c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w','x', 'y', 'z', 
			'-', '_' };

	/**
	 * 字符编码64
	 * @param orignStr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeStr64(String orignStr) throws UnsupportedEncodingException {
		byte[] bs1 = orignStr.getBytes("utf-8");
		int len = orignStr.length() * 8 % 6 == 0 ? (orignStr.length() * 8 / 6) + 1
				: (orignStr.length() * 8 / 6) + 1 + 1;
		char[] sc1 = new char[len];
		int sc1Index = 0;
		// 剩余的bite位数
		int rem = 0;
		int remB = 0b00000000;
		for (int i = 0; i < bs1.length; i++) {
			byte b = bs1[i];
			if (rem == 6) {
				char decChar = comAry[remB];
				sc1[sc1Index++] = decChar;
				rem = 0;
				remB = 0b00000000;
			}
			// 将遗留的bite移位,为新取出的bite留出空间
			int dByte1 = remB << (6 - rem);
			int mask = 0b11111111 << (8 - (6 - rem));
			int dByte2 = (b & mask) >>> (8 - (6 - rem));
			int dByte = dByte1 | dByte2;
			char decChar = comAry[(0b00111111) & dByte];
			sc1[sc1Index++] = decChar;
			remB = (b & (0b11111111 >>> (6 - rem)));
			rem = 8 - (6 - rem);
		}
		if (rem != 0) {
			int lastByte = (remB << 6 - rem);
			sc1[sc1.length - 2] = comAry[0b11111111 & lastByte];
			// 最后一字节为校验位
			sc1[sc1.length - 1] = comAry[0b11111111 & (0b111111 >>> rem)];
		}
		return new String(sc1);
	}

	/**
	 * 解码
	 * 
	 * @param orignStr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeStr64(String orignStr) throws UnsupportedEncodingException {
		char[] charAry = orignStr.toCharArray();
		byte lastByte = transChar64(charAry[charAry.length - 1]);
		int zeroNum = 0;
		while ((lastByte & (1 << zeroNum)) > 0) {
			zeroNum++;
		}
		if (((charAry.length - 1) * 6 - zeroNum) % 8 != 0) {
			throw new RuntimeException("字符串长度不符合要求:" + orignStr);
		}
		int len = ((charAry.length - 1) * 6 - zeroNum) / 8;
		byte[] bc1 = new byte[len];
		int bc1Index = 0;
		int rem = 0;
		int remB = 0b00000000;
		for (int i = 0; i < charAry.length - 1; i++) {
			byte b = transChar64(charAry[i]);
			if (i == charAry.length - 2) {
				if (rem + (6 - zeroNum) != 8) {
					throw new RuntimeException("字符串不符合要求:" + orignStr);
				}
				bc1[bc1Index++] = (byte) ((remB << (6 - zeroNum)) | (b >>> zeroNum));
				break;
			}
			if (rem == 0) {
				rem = 6;
				remB = b;
			} else if (rem >= 2) {
				byte db1 = (byte) (remB << (8 - rem));
				byte mask = (byte) (0b111111 << (6 - (8 - rem)));
				byte db2 = (byte) ((b & mask) >>> (6 - (8 - rem)));
				bc1[bc1Index++] = (byte) (db1 | db2);
				rem = 6 - (8 - rem);
				remB = b & (0b111111 >> (6 - rem));
			} else {
				throw new RuntimeException("rem错误:" + rem);
			}
		}
		if (bc1Index != bc1.length) {
			throw new RuntimeException("转化错误:" + rem);
		}
		return new String(bc1, "utf-8");
	}

	private static byte transChar64(char oriCh) {
		if (oriCh >= 48 && oriCh <= 57) {
			return (byte) (oriCh - 48);
		} else if (oriCh >= 65 && oriCh <= 90) {
			return (byte) (oriCh - 65 + 10);
		} else if (oriCh >= 97 && oriCh <= 122) {
			return (byte) (oriCh - 97 + 10 + 26);
		} else if (oriCh == '-') {
			return 62;
		} else if (oriCh == '_') {
			return 63;
		} else {
			throw new RuntimeException("不在指定的字符区间内：" + oriCh);
		}
	}

}
