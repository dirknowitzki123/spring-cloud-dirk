package com.cloud.frame.common.util;

import java.math.BigDecimal;

public class BigDecimalUtil {
	private static final int UP = 1;
	private static final int DOWN = 0;
	/**
	 * 精确decimal数据
	 * @param decimal 源数据
	 * @param digit 精确几位小数
	 * @param pickUpFn 向上或者向下截断取整 n=1 向上 n=0 向下 默认向上
	 * @return
	 */
	public static BigDecimal accurate(BigDecimal decimal,int digit,int pickUpFn) {
		int pickUp = BigDecimal.ROUND_HALF_UP;
		if(UP == pickUpFn) {
			pickUp = BigDecimal.ROUND_HALF_UP;
		}else if(DOWN == pickUpFn) {
			pickUp = BigDecimal.ROUND_HALF_DOWN;
		}
		return decimal.setScale(digit, pickUp);
	}
	/**
	 * 数字字符串转BigDecimal类型
	 * @param number 数字字符串
	 * @param digit 精确位数
	 * @param pickUpFn 取整方式 n=1 向上 n=0 向下 默认向上
	 * @return
	 */
	public static BigDecimal StrToDecimal(String number,int digit,int pickUpFn) {
		if(StringUtils.isDouble(number)) {
			return accurate(new BigDecimal(number), digit, pickUpFn);
		}
		return null;
	}
	/**
	 * 判断金额是否相等
	 * @param decimal1
	 * @param decimal2
	 * @return
	 */
	public static boolean compareDecimal(BigDecimal decimal1,BigDecimal decimal2) {
		return accurate(decimal1, 2, 1).equals(accurate(decimal2, 2, 1));
	}
	/**
	 * 判断金额字符串是否相等
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static boolean compareStrNum(String num1,String num2) {
		BigDecimal d1 = StrToDecimal(num1, 2, 1);
		BigDecimal d2 = StrToDecimal(num2, 2, 1);		
		return compareDecimal(d1,d2);
	}

}
