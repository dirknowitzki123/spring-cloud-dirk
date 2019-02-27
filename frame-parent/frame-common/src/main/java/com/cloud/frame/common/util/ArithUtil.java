package com.cloud.frame.common.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * <p>decribing : 数字计算工具类</p>
 * <p>copyright : Copyright @ 2012 hansy</p>
 * <p>company   : hansy</p>
 * <p>time      : 2013-7-31</p>
 *
 * @author jfeng
 * @version v1.0
 */
public class ArithUtil {

    //这个类不能实例化
    private ArithUtil() {
    }

    /**
	 * 判断输入字符串是否为数字
	 * 
	 * @param in
	 * @return
	 */
	public static boolean isInteger(String in) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(in.trim());
		if (!isNum.matches() || in == null || in.trim().length() == 0) {
			return false;
		}
		return true;
	}

   
    /**
     * 判断d1是否不大于d2
     * @param d1
     * @param d2
     * @return boolean  false:大于;true:不大于
     */
    public static boolean isDoubleNotGreater(double d1, double d2) {
        boolean bn = false;
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));

        if (b1.compareTo(b2) <= 0)
            bn = true;
        else
            bn = false;

        return bn;
    }

    /**
     * 判断d1是否不小于d2
     * @param d1
     * @param d2
     * @return boolean  false:大于;true:不大于
     */
    public static boolean isDoubleNotLess(double d1, double d2) {
        boolean bn = false;
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));

        if (b1.compareTo(b2) >= 0)
            bn = true;
        else
            bn = false;

        return bn;
    }

    /**
     * 比较两个double型数据是否相等
     * @param d1
     * @param d2
     * @return boolean  false:不等，true:相等
     */
    public static boolean isDoubleEqual(double d1, double d2) {
        boolean bn = false;
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));

        if (b1.compareTo(b2) == 0)
            bn = true;
        else
            bn = false;

        return bn;
    }

    /**
     * 提供精确的加法运算
     * @param d1
     * @param d2
     * @return double 两个参数的和
     */
    public static double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算
     * @param d1	被减数
     * @param d2	减数
     * @return double 两个参数的差
     */
    public static double sub(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();
    }


    /**
     * 提供精确的乘法运算
     * @param d1	被乘数
     * @param d2	乘数
     * @return double 两个参数的积
     */
    public static double mul(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     * @param d1	被除数
     * @param d2	除数
     * @param scale	精确到小数点以后位数
     * @return		double 两个参数的商
     */
    public static double div(double d1, double d2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时,保留小数点后两位，以后的四舍五入
     * @param d1
     * @param d2
     * @return
     */
    public static double div(double d1, double d2){
    	BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    
    /**
     * 提供精确的小数位四舍五入处理
     * @param d		需要四舍五入的数字
     * @param scale	小数点后保留位数
     * @return		double 四舍五入后的结果
     */
    public static double round(double d, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(d));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 精确的向上取整进位
     * @param d		需要向上取整进位的数字
     * @return		double 向上取整进位后的结果
     */
    public static double roundUp(double d) {
//        if (scale < 0) {
//            throw new IllegalArgumentException(
//                    "The scale must be a positive integer or zero");
//        }
        BigDecimal b = new BigDecimal(Double.toString(d));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 0, BigDecimal.ROUND_UP).doubleValue();
    }
}