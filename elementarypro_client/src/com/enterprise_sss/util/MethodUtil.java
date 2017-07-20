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

}
