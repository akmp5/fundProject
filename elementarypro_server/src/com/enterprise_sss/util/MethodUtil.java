package com.enterprise_sss.util;

/**
 * 方法公共类
 * 
 * @author Wang ming 2009-10-12
 * @version 1.0
 */
public class MethodUtil {

	/**
	 * 存储错误信息
	 * 
	 * @param e
	 */
	public static void LogOper(Exception e) {
		LogOperator.writeErrorLog(e.getClass().getName());
		LogOperator.writeErrorLog(e.getMessage());
	}

	/**
	 * 验证单个String类型的数据是否为Null
	 * 
	 * @param date
	 * @return 为空返回false,不为空返回true
	 */
	public static boolean validateDate(String data) {
		if (data != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 验证单个String类型的数据类型是否符合身份证格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean validateIDNumber(String data){
		if (isInt(data)) {
			if (data.length() == 18) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 验证单个String类型的数据类型是否为int型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str) {
		if (str.matches("[0-9]+"))
			return true;
		else
			return false;
	}

	/**
	 * 验证单个String类型的数据类型是否为double型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		if (str.endsWith(".")) {
			return false;
		} else {
			if (str.contains(".")) {
				String str2 = str.substring(str.lastIndexOf(".") + 1, str
						.length());
				String str1 = str.substring(0, str.lastIndexOf("."));
				if (isInt(str1) && isInt(str2)) {
					return true;
				} else {
					return false;
				}
			} else {
				if (isInt(str)) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
}
