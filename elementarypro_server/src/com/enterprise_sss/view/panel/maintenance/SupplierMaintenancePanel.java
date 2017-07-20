package com.enterprise_sss.view.panel.maintenance;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.enterprise_sss.action.SupplierMaintenancePanelAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.view.panel.DataMaintenancePanel;
import com.enterprise_sss.vo.TableVO;

/**
 * 供应商信息维护面板
 * 
 * @author Administrator
 * 
 */
public class SupplierMaintenancePanel extends DataMaintenancePanel {

	private JTextField supp_id; // 供货商编号文本框

	private JTextField supp_spell_code; // 拼音编码文本框

	private JTextField supp_shortname; // 简称文本框

	private JTextField supp_name; // 名称文本框

	private JTextField supp_address; // 地址文本框

	private JTextField supp_postcode; // 邮编文本框

	private JTextField supp_sort; // 类型文本框

	private JTextField supp_tel; // 电话文本框

	private JTextField supp_fax; // 传真文本框

	private JTextField supp_bank; // 开户行文本框

	private JTextField supp_iban; // 银行帐号文本框

	private JTextField supp_storage_address; // 库房地址文本框

	private JTextField supp_storage_tel; // 库房电话文本框

	private JTextField oper_id; // 业务员编号文本框

	public SupplierMaintenancePanel(String title, TableVO tvo) {
		super(title, tvo);
		init();
		initAction();
	}

	public JPanel buildNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(CommonComponent.buildLabel("供货商编号:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("拼音编码:", null, null, null, null,
				null), new GBC(2, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_spell_code = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 0)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("简称:", null, null, null, null,
				null), new GBC(4, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_shortname = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(5, 0)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("名称:", null, null, null, null,
				null), new GBC(6, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_name = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(7, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("地址:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_address = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("邮编:", null, null, null, null,
				null), new GBC(2, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_postcode = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("类型:", null, null, null, null,
				null), new GBC(4, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_sort = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(5, 1).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("电话:", null, null, null, null,
				null), new GBC(6, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_tel = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(7, 1).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("传真:", null, null, null, null,
				null), new GBC(0, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_fax = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("开户行:", null, null, null, null,
				null), new GBC(2, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_bank = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(3, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("银行帐号:", null, null, null, null,
				null), new GBC(4, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_iban = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(5, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("库房地址:", null, null, null, null,
				null), new GBC(6, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_storage_address = CommonComponent.buildTextField("txt",
				null, null, null, null, null, null, true, true), new GBC(7, 2)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("库房电话:", null, null, null, null,
				null), new GBC(0, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_storage_tel = CommonComponent.buildTextField("txt",
				null, null, null, null, null, null, true, true), new GBC(1, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("业务员编号:", null, null, null, null,
				null), new GBC(2, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(3, 3).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		return panel;
	}

	public void initAction() {
		SupplierMaintenancePanelAction act = new SupplierMaintenancePanelAction(this);
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
				supp_id.setText("" + table.getValueAt(row, 0));
				supp_spell_code.setText("" + table.getValueAt(row, 1));
				supp_shortname.setText("" + table.getValueAt(row, 2));
				supp_name.setText("" + table.getValueAt(row, 3));
				supp_address.setText("" + table.getValueAt(row, 4));
				supp_postcode.setText("" + table.getValueAt(row, 5));
				supp_sort.setText("" + table.getValueAt(row, 6));
				supp_tel.setText("" + table.getValueAt(row, 7));
				supp_fax.setText("" + table.getValueAt(row, 8));
				supp_bank.setText("" + table.getValueAt(row, 9));
				supp_iban.setText("" + table.getValueAt(row, 10));
				supp_storage_address.setText("" + table.getValueAt(row, 11));
				supp_storage_tel.setText("" + table.getValueAt(row, 12));
				oper_id.setText("" + table.getValueAt(row, 13));
			}
		});
	}
	
	public void reset() {
		supp_id.setText("");
		supp_spell_code.setText("");
		supp_shortname.setText("");
		supp_name.setText("");
		supp_address.setText("");
		supp_postcode.setText("");
		supp_sort.setText("");
		supp_tel.setText("");
		supp_fax.setText("");
		supp_bank.setText("");
		supp_iban.setText("");
		supp_storage_address.setText("");
		supp_storage_tel.setText("");
		oper_id.setText("");
	}

	public JTextField getOper_id() {
		return oper_id;
	}

	public JTextField getSupp_address() {
		return supp_address;
	}

	public JTextField getSupp_bank() {
		return supp_bank;
	}

	public JTextField getSupp_fax() {
		return supp_fax;
	}

	public JTextField getSupp_iban() {
		return supp_iban;
	}

	public JTextField getSupp_id() {
		return supp_id;
	}

	public JTextField getSupp_name() {
		return supp_name;
	}

	public JTextField getSupp_postcode() {
		return supp_postcode;
	}

	public JTextField getSupp_shortname() {
		return supp_shortname;
	}

	public JTextField getSupp_sort() {
		return supp_sort;
	}

	public JTextField getSupp_spell_code() {
		return supp_spell_code;
	}

	public JTextField getSupp_storage_address() {
		return supp_storage_address;
	}

	public JTextField getSupp_storage_tel() {
		return supp_storage_tel;
	}

	public JTextField getSupp_tel() {
		return supp_tel;
	}
}
