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
			if ("查询客户资料".equals(str)) {
				panel.setN(2);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "查找所有", "按编号查找", "按业务员ＩＤ查找", "按联系人查找",
						"返回上级" };
				addData(items);
			} else if ("查询业务员资料".equals(str)) {
				panel.setN(4);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "查找所有", "按编号查找", "按姓名查找", "按身份证查询",
						"返回上级" };
				addData(items);
			} else if ("查询商品资料".equals(str)) {
				panel.setN(3);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "查找所有", "按编号查找", "按条形码查找", "按类别查找",
						"按零售价位查找", "返回上级" };
				addData(items);
			} else if ("查询仓库资料".equals(str)) {
				panel.setN(1);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "查找所有", "按编号查找", "按类别查找", "返回上级" };
				addData(items);
			} else if ("查询供货商资料".equals(str)) {
				panel.setN(5);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "查找所有", "按编号查找", "按企业名称查找", "按业务员ID查找",
						"返回上级" };
				addData(items);
			} else if ("查询采购合同资料".equals(str)) {
				panel.setN(6);
				panel.comboBox.removeAllItems();
				String[] items = new String[] { "查找所有", "按编号查找", "按供应商编号查找", "按商品编号查找",
						"按进价价位查找", "返回上级" };
				addData(items);
			} else if ("返回上级".equals(str) && panel.getType() == 0) {
				retu(mode, new String[] { "", "查询商品资料", "查询仓库资料", "查询采购合同资料",
						"查询客户资料", "查询业务员资料", "查询供货商资料" }, data, title);
				panel.setN(0);
			} else if (panel.getN() == 1) {
				DepotServer ds = new DepotServer();
				title.add("仓库编号");
				title.add("仓库名");
				title.add("类别");
				title.add("备注");
				if ("按类别查找".equals(str)) {
					panel.setType(3);
					panel.comboBox.removeAllItems();
					addData(new String[] { "总库", "分库", "返回上级" });
				} else if ("查找所有".equals(str)) {
					panel.setType(1);
					find(str, mode, data, title, ds);
					panel.setType(0);
				} else if ("按编号查找".equals(str)) {
					panel.setType(2);
					find(str, mode, data, title, ds);
					panel.setType(0);
				} else if ("返回上级".equals(str)) {
					retu(mode,
							new String[] { "查找所有", "按编号查找", "按类别查找", "返回上级" },
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
				title.add("客户编号");
				title.add("拼音编码");
				title.add("简称");
				title.add("名称");
				title.add("联系人");
				title.add("地址");
				title.add("邮编");
				title.add("电话");
				title.add("传真");
				title.add("开户行");
				title.add("银行帐号");
				title.add("性质");
				title.add("业务员");
				title.add("授信额度");
				if ("查找所有".equals(str)) {
					panel.setType(1);
					data = cs.findType(panel.getType(), trem);
					//System.out.println(data);
					mode.setDataVector(data, title);
				} else if ("按编号查找".equals(str)) {
					panel.setType(2);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = cs.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("按业务员ＩＤ查找".equals(str)) {
					panel.setType(3);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = cs.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("按联系人查找".equals(str)) {
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
				title.add("货物编号");
				title.add("条形码");
				title.add("商品名");
				title.add("拼音编码");
				title.add("规格");
				title.add("单位");
				title.add("产地");
				title.add("类别");
				title.add("进货价");
				title.add("零售价");
				title.add("会员价");
				title.add("最低售价");
				if ("查找所有".equals(str)) {
					panel.setType(1);
					data = cs.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("按编号查找".equals(str)) {
					panel.setType(2);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = cs.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("按条形码查找".equals(str)) {
					panel.setType(3);
					InputDialog in = new InputDialog(2);
					trem = in.getText().getText().trim();
					data = cs.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("按类别查找".equals(str)) {
					panel.setType(4);
					panel.comboBox.removeAllItems();
					String[] ite = cs.findSort();
					addData(ite);
					panel.comboBox.addItem("返回上级");
				} else if ("返回上级".equals(str)) {
					retu(mode, new String[] { "查找所有", "按编号查找", "按条形码查找",
							"按类别查找", "按零售价位查找", "返回上级" }, data, title);
					panel.setType(1);
					data = cs.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("按零售价位查找".equals(str)) {
					panel.setType(5);
					panel.comboBox.removeAllItems();
					String[] ite = new String[] { "0-30", "30-100", "100-500",
							"500-1000", "1000-", "返回上级" };
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
				title.add("业务员编号");
				title.add("拼音编码");
				title.add("姓名");
				title.add("女");
				title.add("电话");
				title.add("手机");
				title.add("地址");
				title.add("邮编");
				title.add("身份证号");
				title.add("类别");
				if ("查找所有".equals(str)) {
					panel.setType(1);
					data = os.findType(panel.getType(), str);
					mode.setDataVector(data, title);
				} else if ("按编号查找".equals(str)) {
					panel.setType(2);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = os.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("按姓名查找".equals(str)) {
					panel.setType(3);
					InputDialog in = new InputDialog(2);
					trem = in.getText().getText().trim();
					data = os.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("按身份证查询".equals(str)) {
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
				title.add("供货商编号");
				title.add("拼音编码");
				title.add("简称");
				title.add("名称");
				title.add("地址");
				title.add("邮编");
				title.add("类型");
				title.add("电话");
				title.add("传真");
				title.add("开户行");
				title.add("银行帐号");
				title.add("库房地址");
				title.add("库房电话");
				title.add("业务员编号");
				if ("查找所有".equals(str)) {
					panel.setType(1);
					data = ss.findType(panel.getType(), str);
					mode.setDataVector(data, title);
				} else if ("按编号查找".equals(str)) {
					panel.setType(2);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = ss.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("按企业名称查找".equals(str)) {
					panel.setType(3);
					InputDialog in = new InputDialog(2);
					trem = in.getText().getText().trim();
					data = ss.findType(panel.getType(), trem);
					mode.setDataVector(data, title);
				} else if ("按业务员ID查找".equals(str)) {
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
				title.add("合同编号");
				title.add("供货商编号");
				title.add("货物编号");
				title.add("进价");
				title.add("付款方式");
				title.add("帐期");
				title.add("签订日期");
				title.add("合同期限");
				if ("查找所有".equals(str)) {
					panel.setType(1);
					data = ps.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("按编号查找".equals(str)) {
					panel.setType(2);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = ps.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("按供应商编号查找".equals(str)) {
					panel.setType(3);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = ps.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("按商品编号查找".equals(str)) {
					panel.setType(4);
					InputDialog in = new InputDialog(1);
					trem = in.getText().getText().trim();
					data = ps.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("返回上级".equals(str)) {
					retu(mode, new String[] { "查找所有", "按编号查找", "按供应商编号查找",
							"按商品编号查找", "按进价价位查找", "返回上级" }, data, title);
					panel.setType(1);
					data = ps.findType(panel.getType(), trem, str);
					mode.setDataVector(data, title);
					panel.setType(0);
				} else if ("按进价价位查找".equals(str)) {
					panel.setType(5);
					panel.comboBox.removeAllItems();
					String[] ite = new String[] { "0-30", "30-100", "100-500",
							"500-1000", "1000-", "返回上级" };
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
