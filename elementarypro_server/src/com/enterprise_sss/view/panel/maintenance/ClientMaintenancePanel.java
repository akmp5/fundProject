package com.enterprise_sss.view.panel.maintenance;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.enterprise_sss.action.ClientMaintenancePanelAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.view.panel.DataMaintenancePanel;
import com.enterprise_sss.vo.TableVO;

/**
 * 客户信息维护面板
 * 
 * @author Administrator
 * 
 */
public class ClientMaintenancePanel extends DataMaintenancePanel {

	private JTextField clie_id; // 客户编号文本框

	private JTextField clie_spell_code; // 拼音编码文本框

	private JTextField clie_shortname; // 简称文本框

	private JTextField clie_name; // 名称文本框

	private JTextField clie_linkman; // 联系人文本框

	private JTextField clie_address; // 地址文本框

	private JTextField Clie_postcode; // 邮编文本框

	private JTextField Clie_tel; // 电话文本框

	private JTextField Clie_fax; // 传真文本框

	private JTextField Clie_bank; // 开户行文本框

	private JTextField Clie_iban; // 银行帐号文本框

	private JTextField Clie_sort; // 性质文本框

	private JTextField oper_id; // 业务员ＩＤ文本框

	private JTextField Clie_CreditLimt; // 授信额度文本框

	public ClientMaintenancePanel(String title, TableVO tvo) {
		super(title, tvo);
		init();
		initAction();
	}

	public JPanel buildNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(CommonComponent.buildLabel("客户编号:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(clie_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("拼音编码:", null, null, null, null,
				null), new GBC(2, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(clie_spell_code = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 0)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("简称:", null, null, null, null,
				null), new GBC(4, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(clie_shortname = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(5, 0)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("名称:", null, null, null, null,
				null), new GBC(6, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(clie_name = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(7, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("联系人:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(clie_linkman = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("地址:", null, null, null, null,
				null), new GBC(2, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(clie_address = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("邮编:", null, null, null, null,
				null), new GBC(4, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(Clie_postcode = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(5, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("电话:", null, null, null, null,
				null), new GBC(6, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(Clie_tel = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(7, 1).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("传真:", null, null, null, null,
				null), new GBC(0, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(Clie_fax = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("开户行:", null, null, null, null,
				null), new GBC(2, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(Clie_bank = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(3, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("银行帐号:", null, null, null, null,
				null), new GBC(4, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(Clie_iban = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(5, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("性质:", null, null, null, null,
				null), new GBC(6, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(Clie_sort = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(7, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("业务员ＩＤ:", null, null, null, null,
				null), new GBC(0, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 3).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("授信额度:", null, null, null, null,
				null), new GBC(2, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(Clie_CreditLimt = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		return panel;
	}

	public void initAction() {
		ClientMaintenancePanelAction act = new ClientMaintenancePanelAction(
				this);
		button_add.addActionListener(act);
		button_reset.addActionListener(act);
		button_del.addActionListener(act);
		button_modify.addActionListener(act);
		button_find.addActionListener(act);

		// 添加鼠标响应事件，用来处理Table组件的单击事件
		table.changeSelection(1, 1, false, false);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 单击某行，就将这行的值放到上面相应的文本框里面
				int row = table.getSelectedRow();
				clie_id.setText("" + table.getValueAt(row, 0));
				clie_spell_code.setText("" + table.getValueAt(row, 1));
				clie_shortname.setText("" + table.getValueAt(row, 2));
				clie_name.setText("" + table.getValueAt(row, 3));
				clie_linkman.setText("" + table.getValueAt(row, 4));
				clie_address.setText("" + table.getValueAt(row, 5));
				Clie_postcode.setText("" + table.getValueAt(row, 6));
				Clie_tel.setText("" + table.getValueAt(row, 7));
				Clie_fax.setText("" + table.getValueAt(row, 8));
				Clie_bank.setText("" + table.getValueAt(row, 9));
				Clie_iban.setText("" + table.getValueAt(row, 10));
				Clie_sort.setText("" + table.getValueAt(row, 11));
				oper_id.setText("" + table.getValueAt(row, 12));
				Clie_CreditLimt.setText("" + table.getValueAt(row, 13));
			}
		});
	}

	public void reset() {

		clie_id.setText("");
		clie_spell_code.setText("");
		clie_shortname.setText("");
		clie_name.setText("");
		clie_linkman.setText("");
		clie_address.setText("");
		Clie_postcode.setText("");
		Clie_tel.setText("");
		Clie_fax.setText("");
		Clie_bank.setText("");
		Clie_iban.setText("");
		Clie_sort.setText("");
		oper_id.setText("");
		Clie_CreditLimt.setText("");

	}

	public JTextField getClie_address() {
		return clie_address;
	}

	public JTextField getClie_bank() {
		return Clie_bank;
	}

	public JTextField getClie_CreditLimt() {
		return Clie_CreditLimt;
	}

	public JTextField getClie_fax() {
		return Clie_fax;
	}

	public JTextField getClie_iban() {
		return Clie_iban;
	}

	public JTextField getClie_id() {
		return clie_id;
	}

	public JTextField getClie_linkman() {
		return clie_linkman;
	}

	public JTextField getClie_name() {
		return clie_name;
	}

	public JTextField getClie_postcode() {
		return Clie_postcode;
	}

	public JTextField getClie_shortname() {
		return clie_shortname;
	}

	public JTextField getClie_sort() {
		return Clie_sort;
	}

	public JTextField getClie_spell_code() {
		return clie_spell_code;
	}

	public JTextField getClie_tel() {
		return Clie_tel;
	}

	public JTextField getOper_id() {
		return oper_id;
	}
}
