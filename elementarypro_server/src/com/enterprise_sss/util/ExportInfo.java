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
    WritableWorkbook excelModel = null; // Excle�ļ�д����������
    try {
      // ����Excel�ļ�д����������
      File file = new File(fileName);
      excelModel = Workbook.createWorkbook(file);

      // ��������Excel�б������ò�����Excel
      this.writeExcelFile(excelModel, vectorData, vectorTitle);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        excelModel.close(); // ����رմ���Excel�ĵ��Ķ���
      } catch (WriteException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * д��Excel�ļ�
   * 
   * @param excelModel
   * @param argVectorData
   *          Ҫ����excel�ļ�������
   * @throws WriteOutExcelException
   */
  private void writeExcelFile(WritableWorkbook excelModel, Vector argVectorData, Vector infoTitle)
      throws Exception {
    try {
      /* ���ñ��� */
      String title = "XXXX��";

      // ����ҳ��Sheet
      WritableSheet ws = excelModel.createSheet(title, 0);

      // ����ͷ
      this.buildExlHead(ws, title,infoTitle);

      // ÿһ����Ԫ������ʽ
      WritableCellFormat callFormat = new WritableCellFormat();
      callFormat.setAlignment(Alignment.CENTRE);
      callFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
      callFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
      // �õ�����Ա����Vo
//      Iterator dataIterator = argVectorData.iterator();
      int rowNum = 2; // ֵ��ʼ�к�
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

      // д��Excel�ļ�
      excelModel.write(); // �������д�ļ��ķ���

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
   * ����Excel�������
   * 
   * @param sheet
   *          Excel���������Sheet
   * @param argTitle
   *          ��������
   * @param colunmUnite
   *          �ϲ�����
   * @param rowUnite
   *          �ϲ�����
   * @return WritableSheet
   * @throws WriteException
   */
  protected WritableSheet setExcelTitle(WritableSheet sheet, String argTitle,
      int colunmUnite, int rowUnite) throws WriteException {
    // ���úϲ��������ĸ�ʽ
    sheet.mergeCells(0, 0, colunmUnite, rowUnite);

    // ��������������Ϣ
    WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 20,
        WritableFont.NO_BOLD);
    WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
    // ���ñ�ͷ������ʾ
    titleFormat.setAlignment(Alignment.CENTRE);
    titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

    Label title = new Label(0, 0, argTitle// ���õı���
        , titleFormat);
    sheet.addCell(title);
    return sheet;
  }

  /**
   * ����Ա����ϢExcel����ı���ͷ��
   * 
   * @param sheet
   *          WritableSheet
   * @return WritableSheet
   * @throws WriteException
   */
  public WritableSheet buildExlHead(WritableSheet sheet, String argTitle,Vector infoTitle)
      throws Exception {
    try {
      // ��������
      setExcelTitle(sheet, argTitle, infoTitle.size()-1, 0);

      // �����б�ͷ��Ϣ
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
