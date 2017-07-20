package com.enterprise_sss.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ��־�����࣬�洢������־��Ϣ
 * @author Wang ming 2009-10-12
 * @version 1.0
 */
public class LogOperator {

	/**
	 * �洢����������Ϣ
	 * @param message
	 * @param name
	 * @param oper
	 */
	public static void writeMessageLog(String message, String name, String oper) {
		/**
		 * ��ȡ����,��ʽ:YYYY-MM-DD
		 */
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = df.format(date);

		/**
		 * ��ȡ�洢·��
		 */
		String filePath = "logs/messages/Message_" + strDate + ".log";
		File file = new File(filePath);

		/**
		 * ��ȡ�洢��Ϣ
		 */
		StringBuffer sb = new StringBuffer();
		sb.append(strDate);
		sb.append(" ");
		sb.append(name);
		sb.append(" ");
		sb.append(oper);
		sb.append(" ");
		sb.append(message);

		try {
			/**
			 * �����־�ļ�����,���������Ϣȡȥ
			 * �ٺ����ڵ���Ϣһ��洢
			 */
			if (file.exists()) {
				BufferedReader read = new BufferedReader(new FileReader(file));
				String str = null;
				while ((str = read.readLine()) != null) {
					sb.append("\r\n" + str);
				}
				read.close();
			}
			Writer out = new BufferedWriter(new FileWriter(file));
			out.write(sb.toString());
			out.close();
		} catch (Exception e) {
			MethodUtil.LogOper(e);
		}
	}

	/**
	 * �洢������Ϣ
	 * @param errorMessage
	 */
	public static void writeErrorLog(String errorMessage) {
		/**
		 * ��ȡ����,��ʽ:YYYY-MM-DD
		 */
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = df.format(date);

		/**
		 * ��ȡ�洢·��
		 */
		String filePath = "logs/errors/Error_" + strDate + ".log";
		File file = new File(filePath);

		/**
		 * ��ȡ�洢��Ϣ
		 */
		StringBuffer sb = new StringBuffer();
		sb.append(strDate);
		sb.append(" ");
		sb.append(errorMessage);

		try {
			if (file.exists()) {
				/**
				 * �����־�ļ�����,���������Ϣȡȥ
				 * �ٺ����ڵ���Ϣһ��洢
				 */
				BufferedReader read = new BufferedReader(new FileReader(file));
				String str = null;
				while ((str = read.readLine()) != null) {
					sb.append("\r\n" + str);
				}
				read.close();
			}
			Writer out = new BufferedWriter(new FileWriter(file));
			out.write(sb.toString());
			out.close();
		} catch (Exception e) {
			MethodUtil.LogOper(e);
		}
	}

}
