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
   * 从文件f中导入数据
   * 
   * @param f
   */
  public boolean importInfoVo(File f,String table_title) {
    boolean flag = false;
    if (f.getName().indexOf(".xls") > 0) {// 代表是xls文件
      try {
        // 根据选中的文件构造流对象读取文件中的数据
        FileInputStream input = new FileInputStream(f);
        // 获得操作excel文件的workbook对象
        Workbook wbk = Workbook.getWorkbook(input);
        // 获得excel文件中的某个sheet项
        Sheet sheet = wbk.getSheet(0);
        // 获得sheet对象中的所有行记录保存在vector对象中
        voSize = sheet.getRows();// get rows in excel file
        
        if (addValue(voSize, sheet, table_title)) {// 得到Vo，插入到数据库中
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
      JOptionPane.showMessageDialog(null, "该文件不是Excel文件");
    }
    return flag;
  }

  /**
   * 把Excel文件中的数据插入到数据库中
   * 
   * @param size
   * @param sheet
   * @return
   */
  private boolean addValue(int size, Sheet sheet, String table_title) {
    boolean flag = false;
    //User vo = null;

    for (int i = 2; i < size; i++) { // from 3th row to (size - 1)th row
      // 获得excel文件中某一行记录的所有单元格
      Cell[] ce = sheet.getRow(i);
      ImportInfoServer iis = new ImportInfoServer();
      /*
       * 依次获得单元格中的对应的数据项
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
