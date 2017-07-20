package com.enterprise_sss.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

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

public class ExportInfo {
	
  public void writeExcelOfFile(String fileName, Vector vectorData,
      Vector vectorTitle) {
    WritableWorkbook excelModel = null; // Excle文件写出操作对象
    try {
      // 创建Excel文件写出操作对象
      File file = new File(fileName);
      excelModel = Workbook.createWorkbook(file);

      // 调用生成Excel列表方法设置并生成Excel
      this.writeExcelFile(excelModel, vectorData, vectorTitle);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        excelModel.close(); // 必须关闭创建Excel文档的对象
      } catch (WriteException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 写出Excel文件
   * 
   * @param excelModel
   * @param argVectorData
   *          要生成excel文件的数据
   * @throws WriteOutExcelException
   */
  private void writeExcelFile(WritableWorkbook excelModel, Vector argVectorData, Vector infoTitle)
      throws Exception {
    try {
      /* 设置标题 */
      String title = "XXXX表";

      // 设置页面Sheet
      WritableSheet ws = excelModel.createSheet(title, 0);

      // 设置头
      this.buildExlHead(ws, title,infoTitle);

      // 每一个单元个的样式
      WritableCellFormat callFormat = new WritableCellFormat();
      callFormat.setAlignment(Alignment.CENTRE);
      callFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
      callFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
      // 得到所有员工，Vo
//      Iterator dataIterator = argVectorData.iterator();
      int rowNum = 2; // 值起始行号
      for (int k = 0; k < argVectorData.size(); k++) {
        Vector v = (Vector)argVectorData.get(k);
 
        for (int i = 0, n = v.size(); i < n; i++) {
          if (v.elementAt(i) == null) {
            v.setElementAt("", i);
          }
          ws.addCell(new Label(i, rowNum, v.elementAt(i).toString(),
                  callFormat));
        }
        rowNum++;
      }

      // 写出Excel文件
      excelModel.write(); // 必须调用写文件的方法

    } catch (FileNotFoundException fne) {
      fne.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (WriteException wex) {
      wex.printStackTrace();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * 设置Excel报表标题
   * 
   * @param sheet
   *          Excel操作对象的Sheet
   * @param argTitle
   *          标题名称
   * @param colunmUnite
   *          合并列数
   * @param rowUnite
   *          合并行数
   * @return WritableSheet
   * @throws WriteException
   */
  protected WritableSheet setExcelTitle(WritableSheet sheet, String argTitle,
      int colunmUnite, int rowUnite) throws WriteException {
    // 设置合并标题栏的格式
    sheet.mergeCells(0, 0, colunmUnite, rowUnite);

    // 定义标题字体等信息
    WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 20,
        WritableFont.NO_BOLD);
    WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
    // 设置表头居中显示
    titleFormat.setAlignment(Alignment.CENTRE);
    titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

    Label title = new Label(0, 0, argTitle// 设置的标题
        , titleFormat);
    sheet.addCell(title);
    return sheet;
  }

  /**
   * 生成员工信息Excel报表的报表头部
   * 
   * @param sheet
   *          WritableSheet
   * @return WritableSheet
   * @throws WriteException
   */
  public WritableSheet buildExlHead(WritableSheet sheet, String argTitle,Vector infoTitle)
      throws Exception {
    try {
      // 创建标题
      setExcelTitle(sheet, argTitle, infoTitle.size()-1, 0);

      // 设置列表头信息
      WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12,
          WritableFont.NO_BOLD);
      WritableCellFormat headFormat = new WritableCellFormat(headFont);
      headFormat.setAlignment(Alignment.CENTRE);
      headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
      headFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
     
      for (int i = 0; i < infoTitle.size(); i++) {
        sheet.mergeCells(i, 1, i, 1);
        Label headLabel = new Label(i, 1, (String)infoTitle.get(i), headFormat);
        sheet.addCell(headLabel);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sheet;
  }
}
