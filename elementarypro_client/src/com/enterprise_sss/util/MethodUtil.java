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

}
