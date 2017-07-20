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

			fileChooser = new JFileChooser(); // �ļ�ѡ����

			String fileName = null;
			fileChooser.setCurrentDirectory(new File("."));// ָ���ļ�����λ��
			fileChooser.addChoosableFileFilter(new ExcelFileFilterImpl("xls")); // �ļ�����

			File file = null;
			if (fileName == null) {
				int result = fileChooser.showSaveDialog(frame); // ��ʾ�ļ�����Ի���
				if (result == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile(); // �õ���ѡ���ļ�����
					fileName = file.getAbsolutePath(); // ����ļ�����·����
				} else {
					return;
				}
			}

			try {
				ExportInfo exportAction = new ExportInfo(); // ��������
				Vector data = getTableData(tab);// �õ�����Ա����Ϣ
				Vector title = new Vector();
				for (int i = 0; i < mode.getColumnCount(); i++) {
					title.add(mode.getColumnName(i));
				}
				exportAction.writeExcelOfFile(fileName, data, title);

				// ��¼�����ݿ���ȥ
				String content = "������Ϣ ";
				JOptionPane.showMessageDialog(null, "�ѵ���������Ŀ¼�£���ˢ�¹���");
			} catch (Exception ex) {
				System.out.println("����message: " + ex.getMessage());
			}
		} else {
			String str = e.getActionCommand();
			if ("����".equals(str)) {
				ChooseTableDialog cd = new ChooseTableDialog();
				String table_title = cd.getTable_title();
				ImportInfo importInfo = new ImportInfo();
				fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("."));
				fileChooser
						.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // �����ļ�ѡ��ģʽ

				int result = fileChooser.showOpenDialog(frame); // ��ʾ���ļ��Ի���
				File xlsFile = null;
				if (result == JFileChooser.APPROVE_OPTION) {
					xlsFile = fileChooser.getSelectedFile();
				} else {
					return;
				}

				if (importInfo.importInfoVo(xlsFile, table_title)) {

					// ��¼�����ݿ���ȥ
					String content = "����Ա����Ϣ " + (importInfo.getVoSize() - 2)
							+ "��";

					JOptionPane.showMessageDialog(null, "����ɹ�," + content);
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

					// if ("����".equals(str)) {
					fileChooser = new JFileChooser(); // �ļ�ѡ����

					String fileName = null;
					fileChooser.setCurrentDirectory(new File("."));// ָ���ļ�����λ��
					fileChooser.addChoosableFileFilter(new ExcelFileFilterImpl(
							"xls")); // �ļ�����

					File file = null;
					if (fileName == null) {
						int result = fileChooser.showSaveDialog(frame); // ��ʾ�ļ�����Ի���
						if (result == JFileChooser.APPROVE_OPTION) {
							file = fileChooser.getSelectedFile(); // �õ���ѡ���ļ�����
							fileName = file.getAbsolutePath(); // ����ļ�����·����
						} else {
							return;
						}
					}

					try {
						ExportInfo exportAction = new ExportInfo(); // ��������
						// String fileNameSave = file.getAbsolutePath() +
						// fileName;
						Vector data = getTableData(table);// �õ�����Ա����Ϣ
						Vector title = new Vector();
						for (int i = 0; i < mode.getColumnCount(); i++) {
							title.add(mode.getColumnName(i));
						}
						exportAction.writeExcelOfFile(fileName, data, title);

						// ��¼�����ݿ���ȥ
						String content = "������Ϣ ";
						JOptionPane.showMessageDialog(null, "�ѵ���������Ŀ¼�£���ˢ�¹���");
					} catch (Exception ex) {
						System.out.println("����message: " + ex.getMessage());
					}

				}

			}
		}
	}

	/**
	 * ���JTable�е�������������
	 * 
	 * @param table
	 * @return
	 */
	public Vector getTableData(JTable table) {
		int columns = table.getColumnCount(); // ��ȡJTable���е���Ŀ
		int rows = table.getRowCount(); // ��ȡJTable���е���Ŀ

		Vector datas = new Vector();
		for (int i = 0; i < rows; i++) {
			Vector data = new Vector();
			for (int j = 0; j < columns; j++) {
				data.add(table.getValueAt(i, j).toString()); // ��ȡJTable��ָ���к��н��浥Ԫ������
			}
			datas.addElement(data);
		}
		return datas;
	}

}
