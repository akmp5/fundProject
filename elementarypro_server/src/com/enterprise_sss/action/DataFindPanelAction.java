package com.enterprise_sss.action;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.ClientServer;
import com.enterprise_sss.control.CommodityServer;
import com.enterprise_sss.control.DepotServer;
import com.enterprise_sss.control.OperatorServer;
import com.enterprise_sss.control.PurchaseServer;
import com.enterprise_sss.control.SupplierServer;
import com.enterprise_sss.view.dialog.InputDialog;
import com.enterprise_sss.view.panel.DataFindPanel;

public class DataFindPanelAction implements ItemListener {

	private DataFindPanel panel;

	public DataFindPanelAction(DataFindPanel panel) {
		this.panel = panel;
	}

	public void itemStateChanged(ItemEvent e) {
		//String[] items;
		if (e.getStateChange() == ItemEvent.SELECTED) {
			DefaultTableModel mode = (DefaultTableModel) panel.table.getModel();
			String str = (String) e.getItem();
			Vector title = new Vector();
			Vector data = new Vector();
			if ("��ѯ�ͻ�����".equals(str)) {
				panel.setN(2);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "��������", "����Ų���", "��ҵ��Ա�ɣĲ���", "����ϵ�˲���",
						"�����ϼ�" };
				addData(items);
			} else if ("��ѯҵ��Ա����".equals(str)) {
				panel.setN(4);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "��������", "����Ų���", "����������", "�����֤��ѯ",
						"�����ϼ�" };
				addData(items);
			} else if ("��ѯ��Ʒ����".equals(str)) {
				panel.setN(3);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "��������", "����Ų���", "�����������", "��������",
						"�����ۼ�λ����", "�����ϼ�" };
				addData(items);
			} else if ("��ѯ�ֿ�����".equals(str)) {
				panel.setN(1);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "��������", "����Ų���", "��������", "�����ϼ�" };
				addData(items);
			} else if ("��ѯ����������".equals(str)) {
				panel.setN(5);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "��������", "����Ų���", "����ҵ���Ʋ���", "��ҵ��ԱID����",
						"�����ϼ�" };
				addData(items);
			} else if ("��ѯ�ɹ���ͬ����".equals(str)) {
				panel.setN(6);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "��������", "����Ų���", "����Ӧ�̱�Ų���", "����Ʒ��Ų���",
						"�����ۼ�λ����", "�����ϼ�" };
				addData(items);
			} else if ("�����ϼ�".equals(str) && panel.getType() == 0) {
				retu(mode, new String[] { "", "��ѯ��Ʒ����", "��ѯ�ֿ�����", "��ѯ�ɹ���ͬ����",
						"��ѯ�ͻ�����", "��ѯҵ��Ա����", "��ѯ����������" }, data, title);
				panel.setN(0);
			} else if (panel.getN() == 1) {
				DepotServer ds = new DepotServer();
				title.add("�ֿ���");
				title.add("�ֿ���");
				title.add("���");
				title.add("��ע");
				if ("��������".equals(str)) {
					panel.setType(3);
					panel.comboBox.removeAllItems();
					addData(new String[] { "�ܿ�", "�ֿ�", "�����ϼ�" });
				} else if ("��������".equals(str)) {
					panel.setType(1);
					find(str, mode, data, title, ds);
					panel.setType(0);
				} else if ("����Ų���".equals(str)) {
					panel.setType(2);
					find(str, mode, data, title, ds);
					panel.setType(0);
				} else if ("�����ϼ�".equals(str)) {
					retu(mode,
							new String[] { "��������", "����Ų���", "��������", "�����ϼ�" },
							data, title);
					panel.setType(1);
					find(str, mode, data, title, ds);
					panel.setType(0);
				} else if (panel.getType() == 3) {
					find(str, mode, data, title, ds);
				}
				ds.close();
			} else if (panel.getN() == 2) {
				ClientServer cs = new ClientServer();
				String trem = str;
				title.add("�ͻ����");
				title.add("ƴ������");
				title.add("���");
				title.add("����");
				title.add("��ϵ��");
				title.add("��ַ");
				title.add("�ʱ�");
				title.add("�绰");
				title.add("����");
				title.add("������");
				title.add("�����ʺ�");
				title.add("����");
				title.add("ҵ��Ա");
				title.add("���Ŷ��");
				if ("��������".equals(str)) {
					panel.setType(1);
					data = cs.findType(panel.getType(), trem);
					//System.out.println(data);
					mode.setDataVector(data, title);
				} else if ("����Ų���".equals(str)) {
					panel.setType(2);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = cs.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("��ҵ��Ա�ɣĲ���".equals(str)) {
					panel.setType(3);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = cs.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("����ϵ�˲���".equals(str)) {
					panel.setType(4);
					InputDialog in = new InputDialog(2);
					trem = in.getText().getText().trim();
					data = cs.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				}
				panel.setType(0);
				cs.close();
			} else if (panel.getN() == 3) {
				CommodityServer cs = new CommodityServer();
				String trem = str;
				title.add("������");
				title.add("������");
				title.add("��Ʒ��");
				title.add("ƴ������");
				title.add("���");
				title.add("��λ");
				title.add("����");
				title.add("���");
				title.add("������");
				title.add("���ۼ�");
				title.add("��Ա��");
				title.add("����ۼ�");
				if ("��������".equals(str)) {
					panel.setType(1);
					data = cs.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("����Ų���".equals(str)) {
					panel.setType(2);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = cs.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("�����������".equals(str)) {
					panel.setType(3);
					InputDialog in = new InputDialog(2);
					trem = in.getText().getText().trim();
					data = cs.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("��������".equals(str)) {
					panel.setType(4);
					panel.comboBox.removeAllItems();
					String[] ite = cs.findSort();
					addData(ite);
					panel.comboBox.addItem("�����ϼ�");
				} else if ("�����ϼ�".equals(str)) {
					retu(mode, new String[] { "��������", "����Ų���", "�����������",
							"��������", "�����ۼ�λ����", "�����ϼ�" }, data, title);
					panel.setType(1);
					data = cs.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("�����ۼ�λ����".equals(str)) {
					panel.setType(5);
					panel.comboBox.removeAllItems();
					String[] ite = new String[] { "0-30", "30-100", "100-500",
							"500-1000", "1000-", "�����ϼ�" };
					addData(ite);
				} else if (panel.getType() == 4) {
					data = cs.findType(panel.getType(), str, trem);
					mode.setDataVector(data, title);
				} else if (panel.getType() == 5) {
					String str1 = null, str2 = null;
					if (str.endsWith("-")) {
						str1 = str.substring(0, str.lastIndexOf("-"));
						str2 = "10000000";
					} else {
						if (str.contains("-")) {
							str2 = str.substring(str.lastIndexOf("-") + 1, str
									.length());
							str1 = str.substring(0, str.lastIndexOf("-"));
						}
					}
					data = cs.findType(panel.getType(), str1, str2);
					mode.setDataVector(data, title);
				}
				cs.close();
			} else if (panel.getN() == 4) {
				OperatorServer os = new OperatorServer();
				String trem = str;
				title.add("ҵ��Ա���");
				title.add("ƴ������");
				title.add("����");
				title.add("Ů");
				title.add("�绰");
				title.add("�ֻ�");
				title.add("��ַ");
				title.add("�ʱ�");
				title.add("���֤��");
				title.add("���");
				if ("��������".equals(str)) {
					panel.setType(1);
					data = os.findType(panel.getType(), str);
					mode.setDataVector(data, title);
				} else if ("����Ų���".equals(str)) {
					panel.setType(2);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = os.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("����������".equals(str)) {
					panel.setType(3);
					InputDialog in = new InputDialog(2);
					trem = in.getText().getText().trim();
					data = os.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("�����֤��ѯ".equals(str)) {
					panel.setType(4);
					InputDialog in = new InputDialog(3);
					trem = in.getText().getText().trim();
					data = os.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				}
				panel.setType(0);
				os.close();
			} else if (panel.getN() == 5) {
				SupplierServer ss = new SupplierServer();
				String trem = str;
				title.add("�����̱��");
				title.add("ƴ������");
				title.add("���");
				title.add("����");
				title.add("��ַ");
				title.add("�ʱ�");
				title.add("����");
				title.add("�绰");
				title.add("����");
				title.add("������");
				title.add("�����ʺ�");
				title.add("�ⷿ��ַ");
				title.add("�ⷿ�绰");
				title.add("ҵ��Ա���");
				if ("��������".equals(str)) {
					panel.setType(1);
					data = ss.findType(panel.getType(), str);
					mode.setDataVector(data, title);
				} else if ("����Ų���".equals(str)) {
					panel.setType(2);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = ss.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("����ҵ���Ʋ���".equals(str)) {
					panel.setType(3);
					InputDialog in = new InputDialog(2);
					trem = in.getText().getText().trim();
					data = ss.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("��ҵ��ԱID����".equals(str)) {
					panel.setType(4);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = ss.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				}
				panel.setType(0);
				ss.close();
			} else if (panel.getN() == 6) {
				PurchaseServer ps = new PurchaseServer();
				String trem = str;
				title.add("��ͬ���");
				title.add("�����̱��");
				title.add("������");
				title.add("����");
				title.add("���ʽ");
				title.add("����");
				title.add("ǩ������");
				title.add("��ͬ����");
				if ("��������".equals(str)) {
					panel.setType(1);
					data = ps.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("����Ų���".equals(str)) {
					panel.setType(2);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = ps.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("����Ӧ�̱�Ų���".equals(str)) {
					panel.setType(3);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = ps.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("����Ʒ��Ų���".equals(str)) {
					panel.setType(4);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = ps.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("�����ϼ�".equals(str)) {
					retu(mode, new String[] { "��������", "����Ų���", "����Ӧ�̱�Ų���",
							"����Ʒ��Ų���", "�����ۼ�λ����", "�����ϼ�" }, data, title);
					panel.setType(1);
					data = ps.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("�����ۼ�λ����".equals(str)) {
					panel.setType(5);
					panel.comboBox.removeAllItems();
					String[] ite = new String[] { "0-30", "30-100", "100-500",
							"500-1000", "1000-", "�����ϼ�" };
					addData(ite);
				} else if (panel.getType() == 5) {
					String str1 = null, str2 = null;
					if (str.endsWith("-")) {
						str1 = str.substring(0, str.lastIndexOf("-"));
						str2 = "10000000";
					} else {
						if (str.contains("-")) {
							str2 = str.substring(str.lastIndexOf("-") + 1, str
									.length());
							str1 = str.substring(0, str.lastIndexOf("-"));
						}
					}
					data = ps.findType(panel.getType(), str1, str2);
					mode.setDataVector(data, title);
				}
				ps.close();
			}
		}
	}

	public void addData(String[] items) {
		if (items != null) {
			if (items.length > 0)
				for (String item : items) {
					panel.comboBox.addItem(item);
				}
		}
	}

	public void retu(DefaultTableModel mode, String[] items, Vector data,
			Vector title) {
		panel.comboBox.removeAllItems();
		addData(items);
		panel.setType(0);
		mode.setDataVector(data, title);
	}

	public void find(String str, DefaultTableModel mode, Vector data,
			Vector title, DepotServer ds) {
		String trem = null;
		if (panel.getType() == 2) {
			InputDialog in = new InputDialog(1);
			trem = in.getText().getText().trim();
		} else {
			trem = str;
		}
		data = ds.findType(panel.getType(), trem);
		mode.setDataVector(data, title);
	}
}
