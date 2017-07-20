package com.enterprise_sss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.ImportInfoServer;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ImportInfo {
  int voSize; // get rows in excel file

  /**
   * ���ļ�f�е�������
   * 
   * @param f
   */
  public boolean importInfoVo(File f,String table_title) {
    boolean flag = false;
    if (f.getName().indexOf(".xls") > 0) {// ������xls�ļ�
      try {
        // ����ѡ�е��ļ������������ȡ�ļ��е�����
        FileInputStream input = new FileInputStream(f);
        // ��ò���excel�ļ���workbook����
        Workbook wbk = Workbook.getWorkbook(input);
        // ���excel�ļ��е�ĳ��sheet��
        Sheet sheet = wbk.getSheet(0);
        // ���sheet�����е������м�¼������vector������
        voSize = sheet.getRows();// get rows in excel file
        
        if (addValue(voSize, sheet, table_title)) {// �õ�Vo�����뵽���ݿ���
          flag = true;
        }
      } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
      } catch (BiffException e1) {
        System.out.println(e1.getMessage());
      } catch (IOException e2) {
        System.out.println(e2.getMessage());
      }
    } else {
      JOptionPane.showMessageDialog(null, "���ļ�����Excel�ļ�");
    }
    return flag;
  }

  /**
   * ��Excel�ļ��е����ݲ��뵽���ݿ���
   * 
   * @param size
   * @param sheet
   * @return
   */
  private boolean addValue(int size, Sheet sheet, String table_title) {
    boolean flag = false;
    //User vo = null;

    for (int i = 2; i < size; i++) { // from 3th row to (size - 1)th row
      // ���excel�ļ���ĳһ�м�¼�����е�Ԫ��
      Cell[] ce = sheet.getRow(i);
      ImportInfoServer iis = new ImportInfoServer();
      /*
       * ���λ�õ�Ԫ���еĶ�Ӧ��������
       */
      Vector v = new Vector();
      int columnCount = sheet.getColumns();
      for (int k = 0; k < columnCount; k++) {
    	  v.add(ce[k].getContents());
      }
      iis.add(table_title, v);
      iis.close();
    }
    return flag;
  }

  public int getVoSize() {
    return voSize;
  }

  public void setVoSize(int voSize) {
    this.voSize = voSize;
  }
}
