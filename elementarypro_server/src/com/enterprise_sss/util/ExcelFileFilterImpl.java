package com.enterprise_sss.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * �Զ����ļ�������
 * @author zhou
 *
 */
public class ExcelFileFilterImpl extends FileFilter  {
	
	/** �ļ������� */
	String ext = null;
	
	/**
	 * ���캯��
	 * @param argExt
	 */
	public ExcelFileFilterImpl(String argExt) {
		this.ext = argExt;
	}
	
	/**
	 * �����ļ���������ʾ�ļ�
	 * @param argFile File
	 */

	public boolean accept(File file) {
		boolean flag = false;
		if (file.getName().endsWith(".xls")) {
			flag = true;
		}
		return flag;
	}
	/**
	 * ���ݲ�ͬ��������ʾ�ļ��ļ�������ʾ����Ϣ
	 */
	public String getDescription() {
		if(ext.equals("xls")) {
			return "Microsoft Office Excel ������(*.xls)";
		}
		return "";
	}

}