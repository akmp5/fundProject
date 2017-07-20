package com.enterprise_sss.util;

/**
 * ����������
 * 
 * @author Wang ming 2009-10-12
 * @version 1.0
 */
public class MethodUtil {

	/**
	 * �洢������Ϣ
	 * 
	 * @param e
	 */
	public static void LogOper(Exception e) {
		LogOperator.writeErrorLog(e.getClass().getName());
		LogOperator.writeErrorLog(e.getMessage());
	}

	/**
	 * ��֤����String���͵������Ƿ�ΪNull
	 * 
	 * @param date
	 * @return Ϊ�շ���false,��Ϊ�շ���true
	 */
	public static boolean validateDate(String data) {
		if (data != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ��֤����String���͵����������Ƿ�������֤��ʽ
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
	 * ��֤����String���͵����������Ƿ�Ϊint��
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
	 * ��֤����String���͵����������Ƿ�Ϊdouble��
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
