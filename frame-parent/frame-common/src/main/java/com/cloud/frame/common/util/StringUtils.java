package com.cloud.frame.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 */
public class StringUtils {

	public static final String NULL_STR = "null";

	/**
	 * 是否 是空字符串或 "null"字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0 || NULL_STR.equalsIgnoreCase(str.trim());
	}

	/**
	 * 是否 不是空字符串或 "null"字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && str.trim().length() > 0 && !NULL_STR.equalsIgnoreCase(str.trim());
	}

	/**
	 * 把逗号隔开的字符串转成LIST
	 * 
	 * @param orgStr
	 *            String orgStr = "ID10002,ID10003,ID10004,ID10005,ID10006";
	 * @return
	 */
	public static List<String> strSplit(String orgStr) {

		if (isEmpty(orgStr)) {
			return null;
		}
		String[] orgStrs = orgStr.split(",");
		return Arrays.asList(orgStrs);


	}

	/**
	 * 把separator分隔的字符串转成LIST
	 * 
	 * @param orgStr
	 *            要分隔的字符串
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static List<String> strSplit(String orgStr, String separator) {

		if (isEmpty(orgStr)) {
			return null;
		}
		String[] orgStrs = orgStr.split(separator);
		return Arrays.asList(orgStrs);


	}

	/**
	 * 生成UUID
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID() + RandomUtil.generateString(4).toLowerCase();
		// 去掉“-”符号
		return uuid.replaceAll("-", "");
	}

	/**
	 * 
	 * @param prefix
	 *            格式化出来的String的抬头
	 * @param no
	 *            在传入的这个no数字的基础上+1
	 * @return
	 */
	private static final String STR_FORMAT = "000000";

	public static String getFormtStr(String prefix, int no) {
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		return prefix + df.format(++no);
	}

	/**
	 * List转逗号分隔字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String listToString(List<?> list) {
		String str = "";
		int len = (list != null ? list.size() : 0);
		if (len > 0) {
			StringBuilder sb = new StringBuilder();
			for (Object obj : list) {
				sb.append(obj).append(",");
			}

			str = sb.substring(0, sb.length() - 1);
		}

		return str;
	}

	/**
	 * List转separator分隔字符串
	 * 
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String listToString(List<?> list, String separator) {
		if (isEmpty(separator)) {
			return listToString(list);
		}

		String str = "";
		int len = (list != null ? list.size() : 0);
		if (len > 0) {
			StringBuilder sb = new StringBuilder();
			for (Object obj : list) {
				sb.append(obj).append(separator);
			}

			str = sb.substring(0, sb.length() - separator.length());
		}

		return str;
	}

	/**
	 * 数组转逗号分隔字符串
	 * 
	 * @param ig
	 * @return
	 */
	public static String converToString(String[] ig) {
		String str = "";
		int len = (ig != null ? ig.length : 0);
		if (len > 0) {
			StringBuilder sb = new StringBuilder();
			for (String t : ig) {
				sb.append(t).append(",");
			}

			str = sb.substring(0, sb.length() - 1);
		}

		return str;
	}

	/**
	 * 数组转separator分隔字符串
	 * 
	 * @param ig
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static String converToString(String[] ig, String separator) {
		if (isEmpty(separator)) {
			return converToString(ig);
		}

		String str = "";
		int len = (ig != null ? ig.length : 0);
		if (len > 0) {
			StringBuilder sb = new StringBuilder();
			for (String t : ig) {
				sb.append(t).append(separator);
			}

			str = sb.substring(0, sb.length() - separator.length());
		}

		return str;
	}

	public static String[] arrayTrim(String[] array) {
		if (array == null || array.length == 0)
			return array;
		List<String> list = new ArrayList<String>(Arrays.asList(array));
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String next = it.next();
			if (isEmpty(next))
				it.remove();
		}
		String[] rs = new String[list.size()];
		list.toArray(rs);
		return rs;
	}

	/**
	 * 转义字符转换成java
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String unescapeJava(String str) throws IOException {
		Writer out = new StringWriter();
		if (str != null) {
			int sz = str.length();
			StringBuilder unicode = new StringBuilder(4);
			boolean hadSlash = false;
			boolean inUnicode = false;

			for (int i = 0; i < sz; ++i) {
				char ch = str.charAt(i);
				if (inUnicode) {
					unicode.append(ch);
					if (unicode.length() == 4) {
						try {
							int nfe = Integer.parseInt(unicode.toString(), 16);
							out.write((char) nfe);
							unicode.setLength(0);
							inUnicode = false;
							hadSlash = false;
						} catch (NumberFormatException var9) {
						}
					}
				} else if (hadSlash) {
					hadSlash = false;
					switch (ch) {
					case '\"':
						out.write(34);
						break;
					case '\'':
						out.write(39);
						break;
					case '\\':
						out.write(92);
						break;
					case 'b':
						out.write(8);
						break;
					case 'f':
						out.write(12);
						break;
					case 'n':
						out.write(10);
						break;
					case 'r':
						out.write(13);
						break;
					case 't':
						out.write(9);
						break;
					case 'u':
						inUnicode = true;
						break;
					default:
						out.write(ch);
					}
				} else if (ch == 92) {
					hadSlash = true;
				} else {
					out.write(ch);
				}
			}

			if (hadSlash) {
				out.write(92);
			}

		}
		return out.toString();
	}

	/**
	 * 验证字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	/**
	 * 判断字符串是否是浮点类型
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[-//+]?//d+(//.//d*)?|//.//d+$");
		return pattern.matcher(str).matches();
	}
	/**
	 * 将json对象转为实体对象
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static Object JsonStrToObj(String jsonStr,Class objClass) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper.readValue(jsonStr,objClass);

	}
	/**
	 * 将字符串转为boolean类型，如果字符串不是true或者false均返回false
	 * @Title: valueOf
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param s
	 * @return
	 * create by HuangHanLun
	 */
	public static Boolean valueOf(String s) {
		if(null != s) {
			if("true".equalsIgnoreCase(s)) {
				return true;
			}else {
				return false;
			}
		}
		return false;	    
	}
	
	/**
	 * object 对象 转字符串
	 * @Title: obj2String
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param obj
	 * @return 参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String obj2String(Object obj) {
		if(obj == null) {
			return "";
		}
		if(obj instanceof String) {
			return obj.toString().trim();
		}else {
			return (obj+"").trim();
		}
	}
	

}
