package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.util.ExcelFileFilterImpl;
import com.enterprise_sss.util.ExportInfo;
import com.enterprise_sss.util.ImportInfo;
import com.enterprise_sss.view.dialog.ChooseTableDialog;
import com.enterprise_sss.view.frame.MainFrame;
import com.enterprise_sss.view.panel.DataMaintenancePanel;
import com.enterprise_sss.view.panel.FindPanel;

public class ImportExportAction implements ActionListener {

	private MainFrame frame;

	private JFileChooser fileChooser;

	private JTable tab;

	private int type;

	public ImportExportAction(MainFrame frame) {
		this.frame = frame;
	}

	public ImportExportAction(JTable table) {
		this.tab = table;
		this.type = 2;
	}

	public void actionPerformed(ActionEvent e) {
		if (type == 2) {
			DefaultTableModel mode = (DefaultTableModel) tab.getModel();

			fileChooser = new JFileChooser(); // 文件选择器

			String fileName = null;
			fileChooser.setCurrentDirectory(new File("."));// 指定文件保存位置
			fileChooser.addChoosableFileFilter(new ExcelFileFilterImpl("xls")); // 文件过滤

			File file = null;
			if (fileName == null) {
				int result = fileChooser.showSaveDialog(frame); // 显示文件保存对话框
				if (result == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile(); // 得到被选中文件对象
					fileName = file.getAbsolutePath(); // 获得文件绝对路径名
				} else {
					return;
				}
			}

			try {
				ExportInfo exportAction = new ExportInfo(); // 导出对象
				Vector data = getTableData(tab);// 得到所有员工信息
				Vector title = new Vector();
				for (int i = 0; i < mode.getColumnCount(); i++) {
					title.add(mode.getColumnName(i));
				}
				exportAction.writeExcelOfFile(fileName, data, title);

				// 记录到数据库中去
				String content = "导出信息 ";
				JOptionPane.showMessageDialog(null, "已导出到工程目录下，请刷新工程");
			} catch (Exception ex) {
				System.out.println("导出message: " + ex.getMessage());
			}
		} else {
			String str = e.getActionCommand();
			if ("导入".equals(str)) {
				ChooseTableDialog cd = new ChooseTableDialog();
				String table_title = cd.getTable_title();
				ImportInfo importInfo = new ImportInfo();
				fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("."));
				fileChooser
						.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 设置文件选择模式

				int result = fileChooser.showOpenDialog(frame); // 显示打开文件对话框
				File xlsFile = null;
				if (result == JFileChooser.APPROVE_OPTION) {
					xlsFile = fileChooser.getSelectedFile();
				} else {
					return;
				}

				if (importInfo.importInfoVo(xlsFile, table_title)) {

					// 记录到数据库中去
					String content = "导入员工信息 " + (importInfo.getVoSize() - 2)
							+ "条";

					JOptionPane.showMessageDialog(null, "导入成功," + content);
				}
			} else {
				JTable table = null;

				if (frame.getPane().getRightComponent() instanceof DataMaintenancePanel) {
					table = ((DataMaintenancePanel) frame.getPane()
							.getRightComponent()).table;
				} else if (frame.getPane().getRightComponent() instanceof FindPanel) {
					table = ((FindPanel) frame.getPane().getRightComponent()).table;
				}

				if (table != null) {
					DefaultTableModel mode = (DefaultTableModel) table
							.getModel();

					// if ("导出".equals(str)) {
					fileChooser = new JFileChooser(); // 文件选择器

					String fileName = null;
					fileChooser.setCurrentDirectory(new File("."));// 指定文件保存位置
					fileChooser.addChoosableFileFilter(new ExcelFileFilterImpl(
							"xls")); // 文件过滤

					File file = null;
					if (fileName == null) {
						int result = fileChooser.showSaveDialog(frame); // 显示文件保存对话框
						if (result == JFileChooser.APPROVE_OPTION) {
							file = fileChooser.getSelectedFile(); // 得到被选中文件对象
							fileName = file.getAbsolutePath(); // 获得文件绝对路径名
						} else {
							return;
						}
					}

					try {
						ExportInfo exportAction = new ExportInfo(); // 导出对象
						// String fileNameSave = file.getAbsolutePath() +
						// fileName;
						Vector data = getTableData(table);// 得到所有员工信息
						Vector title = new Vector();
						for (int i = 0; i < mode.getColumnCount(); i++) {
							title.add(mode.getColumnName(i));
						}
						exportAction.writeExcelOfFile(fileName, data, title);

						// 记录到数据库中去
						String content = "导出信息 ";
						JOptionPane.showMessageDialog(null, "已导出到工程目录下，请刷新工程");
					} catch (Exception ex) {
						System.out.println("导出message: " + ex.getMessage());
					}

				}

			}
		}
	}

	/**
	 * 获得JTable中的所有数据内容
	 * 
	 * @param table
	 * @return
	 */
	public Vector getTableData(JTable table) {
		int columns = table.getColumnCount(); // 获取JTable中列的数目
		int rows = table.getRowCount(); // 获取JTable中行的数目

		Vector datas = new Vector();
		for (int i = 0; i < rows; i++) {
			Vector data = new Vector();
			for (int j = 0; j < columns; j++) {
				data.add(table.getValueAt(i, j).toString()); // 获取JTable中指定行和列交叉单元格数据
			}
			datas.addElement(data);
		}
		return datas;
	}

}
