package com.enterprise_sss.view.dialog.basic_data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.ButtonUI;

import com.enterprise_sss.action.SupplierDataDialogAction;
import com.enterprise_sss.control.SupplierServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.SupplierVO;

public class SupplierDataDialog extends JDialog {

	private int type;

	private String title;
	
	private JButton button;

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

	public SupplierDataDialog(int type, String title) {
		this.type = type;
		this.title = title;
		init();
	}

	public void init() {
		this.setSize(350, 550);
		this.setLayout(new GridBagLayout());
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle(title);
		this.add(CommonComponent.buildLabel("供货商编号:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(supp_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("拼音编码:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(supp_spell_code = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("简称:", null, null, null, null, null), new GBC(0, 2)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(supp_shortname = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 2)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("名称:", null, null, null, null, null), new GBC(0, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(supp_name = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 3).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("地址:", null, null, null, null, null), new GBC(0, 4)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(supp_address = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 4)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("邮编:", null, null, null, null, null), new GBC(0, 5)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(supp_postcode = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 5)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("类型:", null, null, null, null, null), new GBC(0, 6)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(supp_sort = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 6).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("电话:", null, null, null, null, null), new GBC(0, 7)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(supp_tel = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 7).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("传真:", null, null, null, null, null), new GBC(0, 8)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(supp_fax = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 8).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("开户行:", null, null, null, null,
				null), new GBC(0, 9).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(supp_bank = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 9).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("银行帐号:", null, null, null, null,
				null), new GBC(0, 10).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(supp_iban = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 10).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("库房地址:", null, null, null, null,
				null), new GBC(0, 11).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(supp_storage_address = CommonComponent.buildTextField("txt",
				null, null, null, null, null, null, true, true), new GBC(1, 11)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("库房电话:", null, null, null, null,
				null), new GBC(0, 12).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(supp_storage_tel = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 12)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("业务员编号:", null, null, null, null,
				null), new GBC(0, 13).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(oper_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 13).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		if (type == 1){
			this
					.add(button = buildButton("添加", null, null, null, null, null, null,
							true), new GBC(0, 14).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
			supp_id.setEditable(false);
			supp_id.setText("" + new SupplierServer().findMaxID());
		}
		if (type == 2) {
			this
					.add(button = buildButton("查询", null, null, null, null, null, null,
							true), new GBC(0, 14).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
		}
		if (type == 3)
			this
					.add(button = buildButton("删除", null, null, null, null, null, null,
							true), new GBC(0, 14).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
		if (type == 4)
			this
					.add(button = buildButton("修改", null, null, null, null, null, null,
							true), new GBC(0, 14).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));

		this.add(buildButton("取消", null, null, null, null, null, null, true),
				new GBC(1, 14).setFill(GBC.CENTER).setAnchor(GBC.WEST)
						.setInset(5));

		if (type == 3 || type == 4) {
			editable(false);
			if (type == 3) {
				button.setEnabled(false);
			}
			supp_id.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (MethodUtil.isInt(supp_id.getText())) {
						SupplierVO v = new SupplierVO();
						// System.out.println(comm_id.getText());
						v.setSupp_id(Integer.parseInt(supp_id.getText()));
						v = new SupplierServer().find(v);
						if (v == null) {
							JOptionPane
									.showMessageDialog(null, "对不起，不存在该数据记录!");
							reset(null);
						} else {
							button.setEnabled(true);
							reset(v);
							editable(true);
							supp_id.setEditable(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "请输入正确的数据!");
						reset(null);
					}
					new SupplierServer().close();
				}

			});
		}
		
		this.setVisible(true);
	}

	public JButton buildButton(String title, Color bgColor, ImageIcon icon,
			Color forColor, Font font, ButtonUI ui, Dimension size,
			boolean isEnable) {
		JButton button = CommonComponent.buildButton(title, bgColor, icon,
				forColor, font, ui, size, isEnable);
		button.addActionListener(new SupplierDataDialogAction(this));
		return button;
	}

	public void editable(boolean b) {
		supp_spell_code.setEditable(b);
		supp_shortname.setEditable(b);
		supp_name.setEditable(b);
		supp_address.setEditable(b);
		supp_postcode.setEditable(b);
		supp_sort.setEditable(b);
		supp_tel.setEditable(b);
		supp_fax.setEditable(b);
		supp_bank.setEditable(b);
		supp_iban.setEditable(b);
		supp_storage_address.setEditable(b);
		supp_storage_tel.setEditable(b);
		oper_id.setEditable(b);
	}

	public void reset(SupplierVO v) {
		if (v == null) {
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
		} else {
			supp_id.setText("" + v.getSupp_id());
			supp_spell_code.setText("" + v.getSupp_spell_code());
			supp_shortname.setText("" + v.getSupp_shortname());
			supp_name.setText("" + v.getSupp_name());
			supp_address.setText("" + v.getSupp_address());
			supp_postcode.setText("" + v.getSupp_postcode());
			supp_sort.setText("" + v.getSupp_sort());
			supp_tel.setText("" + v.getSupp_tel());
			supp_fax.setText("" + v.getSupp_fax());
			supp_bank.setText("" + v.getSupp_bank());
			supp_iban.setText("" + v.getSupp_iban());
			supp_storage_address.setText("" + v.getSupp_storage_address());
			supp_storage_tel.setText("" + v.getSupp_storage_tel());
			oper_id.setText("" + v.getOper_id());
		}
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
