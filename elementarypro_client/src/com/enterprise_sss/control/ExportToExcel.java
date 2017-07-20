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
		JFileChooser fileChooser = new JFileChooser(); //�ļ�ѡ����

		String fileName = null;
		fileChooser.setCurrentDirectory(new File("."));// ָ���ļ�����λ��
		fileChooser.addChoosableFileFilter(new ExcelFileFilterImpl("xls")); //�ļ�����

		File file = null;
		if (fileName == null) {
			int result = fileChooser.showSaveDialog(parent); //��ʾ�ļ�����Ի���
			if (result == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile(); //�õ���ѡ���ļ�����
				fileName = file.getAbsolutePath(); //����ļ�����·����
			} else {
				return;
			}
		}

		try {
			String fileNameSave = file.getAbsolutePath() + fileName;
			writeExcelOfFile(fileName);//��ָ��excel�ļ���д����

			// ��¼�����ݿ���ȥ
			String content = "������Ϣ ";
			JOptionPane.showMessageDialog(null, "�ѵ���������Ŀ¼�£���ˢ�¹���");
		} catch (Exception ex) {
		}

	}
	
	 public void writeExcelOfFile(String fileName) {
		    WritableWorkbook excelModel = null; // Excle�ļ�д����������
		    try {
		      // ����Excel�ļ�д����������
		      File file = new File(fileName);
		      excelModel = Workbook.createWorkbook(file);

		      // ��������Excel�б������ò�����Excel
		      this.writeExcelFile(excelModel);
		    } catch (Exception e) {
		    } finally {
		      try {
		        excelModel.close(); // ����رմ���Excel�ĵ��Ķ���
		      } catch (WriteException e) {
		      } catch (IOException e) {
		      }
		    }
		  }
	 
	 /**
	   * д��Excel�ļ�
	   * @param excelModel
	   * @throws WriteOutExcelException
	   */
	  private void writeExcelFile(WritableWorkbook excelModel)
	      throws Exception {
	    try {
	      // ����ҳ��Sheet
	      WritableSheet ws = excelModel.createSheet(tableName, 0);

	      // ����ͷ
	      this.buildExlHead(ws);/* ���ñ��� */

	      // ÿһ����Ԫ����ʽ
	      WritableCellFormat callFormat = new WritableCellFormat();
	      callFormat.setAlignment(Alignment.CENTRE);
	      callFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	      callFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
	     
	      /*
	       * д������
	       * row������
	       * col������
	       */
	      for (int row = 0; row <data.size(); row++) {

	        	for(int col=0;col<((Vector)data.get(row)).size();col++ )
	        		ws.addCell(new Label(col, row+3,((Vector)data.get(row)).get(col).toString(),
	                  callFormat));
	        }

	      // д��Excel�ļ�
	      excelModel.write(); // �������д�ļ��ķ���

	    } catch (Exception ex) {
	    }
	  }


	  /**
	   * ����Excel����ı���ͷ��
	   * 
	   * @param sheet
	   *          WritableSheet
	   * @return WritableSheet
	   * @throws WriteException
	   */
	  public WritableSheet buildExlHead(WritableSheet sheet)
	      throws Exception {
	    try {
	   // ��������:
	   // ���úϲ��������ĸ�ʽ����ʼ�У���ʼ�У��ϲ��������ϲ�����)
		    sheet.mergeCells(0, 0, title.size()-1,1);

		    // ��������������Ϣ
		    WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 20,
		        WritableFont.NO_BOLD);
		    WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
		    // ���ñ�ͷ������ʾ
		    titleFormat.setAlignment(Alignment.CENTRE);
		    titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

		    sheet.addCell(new Label(0, 0, tableName// ���õı���
			        , titleFormat));

	      // �����б�ͷ��Ϣ
	      WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12,
	          WritableFont.NO_BOLD);
	      WritableCellFormat headFormat = new WritableCellFormat(headFont);
	      headFormat.setAlignment(Alignment.CENTRE);
	      headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	      headFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
	   // ����б�ͷ
	      for (int i = 0; i <title.size(); i++) {
	        sheet.addCell(new Label(i, 2,title.get(i).toString(), headFormat));
	      }
	    } catch (Exception e) {
	    }
	    return sheet;
	  }
	  
	  
	  /**
	   * �Զ����ļ�������
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
}
