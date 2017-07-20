package com.enterprise_sss.control;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExportToExcel {
	private String tableName;
	private Vector title;
	private Vector data;
	private Container parent;
	
	public ExportToExcel (){
		
	}
	public ExportToExcel (Container parent,String tableName,Vector title,Vector data){
		this.parent=parent;
		this.tableName=tableName;
		this.title=title;
		this.data=data;
		export();
	}
	public void export(){
		JFileChooser fileChooser = new JFileChooser(); //文件选择器

		String fileName = null;
		fileChooser.setCurrentDirectory(new File("."));// 指定文件保存位置
		fileChooser.addChoosableFileFilter(new ExcelFileFilterImpl("xls")); //文件过滤

		File file = null;
		if (fileName == null) {
			int result = fileChooser.showSaveDialog(parent); //显示文件保存对话框
			if (result == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile(); //得到被选中文件对象
				fileName = file.getAbsolutePath(); //获得文件绝对路径名
			} else {
				return;
			}
		}

		try {
			String fileNameSave = file.getAbsolutePath() + fileName;
			writeExcelOfFile(fileName);//在指定excel文件中写数据

			// 记录到数据库中去
			String content = "导出信息 ";
			JOptionPane.showMessageDialog(null, "已导出到工程目录下，请刷新工程");
		} catch (Exception ex) {
		}

	}
	
	 public void writeExcelOfFile(String fileName) {
		    WritableWorkbook excelModel = null; // Excle文件写出操作对象
		    try {
		      // 创建Excel文件写出操作对象
		      File file = new File(fileName);
		      excelModel = Workbook.createWorkbook(file);

		      // 调用生成Excel列表方法设置并生成Excel
		      this.writeExcelFile(excelModel);
		    } catch (Exception e) {
		    } finally {
		      try {
		        excelModel.close(); // 必须关闭创建Excel文档的对象
		      } catch (WriteException e) {
		      } catch (IOException e) {
		      }
		    }
		  }
	 
	 /**
	   * 写出Excel文件
	   * @param excelModel
	   * @throws WriteOutExcelException
	   */
	  private void writeExcelFile(WritableWorkbook excelModel)
	      throws Exception {
	    try {
	      // 设置页面Sheet
	      WritableSheet ws = excelModel.createSheet(tableName, 0);

	      // 设置头
	      this.buildExlHead(ws);/* 设置标题 */

	      // 每一个单元的样式
	      WritableCellFormat callFormat = new WritableCellFormat();
	      callFormat.setAlignment(Alignment.CENTRE);
	      callFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	      callFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
	     
	      /*
	       * 写表数据
	       * row控制行
	       * col控制行
	       */
	      for (int row = 0; row <data.size(); row++) {

	        	for(int col=0;col<((Vector)data.get(row)).size();col++ )
	        		ws.addCell(new Label(col, row+3,((Vector)data.get(row)).get(col).toString(),
	                  callFormat));
	        }

	      // 写出Excel文件
	      excelModel.write(); // 必须调用写文件的方法

	    } catch (Exception ex) {
	    }
	  }


	  /**
	   * 生成Excel报表的报表头部
	   * 
	   * @param sheet
	   *          WritableSheet
	   * @return WritableSheet
	   * @throws WriteException
	   */
	  public WritableSheet buildExlHead(WritableSheet sheet)
	      throws Exception {
	    try {
	   // 创建标题:
	   // 设置合并标题栏的格式（起始列，起始行，合并列数，合并行数)
		    sheet.mergeCells(0, 0, title.size()-1,1);

		    // 定义标题字体等信息
		    WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 20,
		        WritableFont.NO_BOLD);
		    WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
		    // 设置表头居中显示
		    titleFormat.setAlignment(Alignment.CENTRE);
		    titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

		    sheet.addCell(new Label(0, 0, tableName// 设置的标题
			        , titleFormat));

	      // 设置列表头信息
	      WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12,
	          WritableFont.NO_BOLD);
	      WritableCellFormat headFormat = new WritableCellFormat(headFont);
	      headFormat.setAlignment(Alignment.CENTRE);
	      headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	      headFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
	   // 添加列表头
	      for (int i = 0; i <title.size(); i++) {
	        sheet.addCell(new Label(i, 2,title.get(i).toString(), headFormat));
	      }
	    } catch (Exception e) {
	    }
	    return sheet;
	  }
	  
	  
	  /**
	   * 自定义文件过滤器
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
}
