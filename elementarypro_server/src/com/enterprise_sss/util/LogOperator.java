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
 * 日志工具类，存储各类日志信息
 * @author Wang ming 2009-10-12
 * @version 1.0
 */
public class LogOperator {

	/**
	 * 存储正常操作信息
	 * @param message
	 * @param name
	 * @param oper
	 */
	public static void writeMessageLog(String message, String name, String oper) {
		/**
		 * 获取日期,格式:YYYY-MM-DD
		 */
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = df.format(date);

		/**
		 * 获取存储路径
		 */
		String filePath = "logs/messages/Message_" + strDate + ".log";
		File file = new File(filePath);

		/**
		 * 获取存储信息
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
			 * 如果日志文件存在,则将里面的信息取去
			 * 再和现在的信息一起存储
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
	 * 存储错误信息
	 * @param errorMessage
	 */
	public static void writeErrorLog(String errorMessage) {
		/**
		 * 获取日期,格式:YYYY-MM-DD
		 */
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = df.format(date);

		/**
		 * 获取存储路径
		 */
		String filePath = "logs/errors/Error_" + strDate + ".log";
		File file = new File(filePath);

		/**
		 * 获取存储信息
		 */
		StringBuffer sb = new StringBuffer();
		sb.append(strDate);
		sb.append(" ");
		sb.append(errorMessage);

		try {
			if (file.exists()) {
				/**
				 * 如果日志文件存在,则将里面的信息取去
				 * 再和现在的信息一起存储
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
