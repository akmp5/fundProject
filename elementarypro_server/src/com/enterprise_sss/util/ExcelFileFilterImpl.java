package com.enterprise_sss.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * 自定义文件过滤器
 * @author zhou
 *
 */
public class ExcelFileFilterImpl extends FileFilter  {
	
	/** 文件类型名 */
	String ext = null;
	
	/**
	 * 构造函数
	 * @param argExt
	 */
	public ExcelFileFilterImpl(String argExt) {
		this.ext = argExt;
	}
	
	/**
	 * 根据文件类型来显示文件
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
	 * 根据不同类型来显示文件文件类型提示框信息
	 */
	public String getDescription() {
		if(ext.equals("xls")) {
			return "Microsoft Office Excel 工作薄(*.xls)";
		}
		return "";
	}

}