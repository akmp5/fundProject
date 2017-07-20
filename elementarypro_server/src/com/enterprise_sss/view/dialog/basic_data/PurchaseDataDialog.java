package com.enterprise_sss.view.dialog.basic_data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.ButtonUI;

import com.enterprise_sss.action.PurchaseDataDialogAction;
import com.enterprise_sss.control.PurchaseServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.util.data.DataPicker;
import com.enterprise_sss.vo.PurchaseVO;

public class PurchaseDataDialog extends JDialog {
	
	private int type;
	
	private String title;
	
	private JButton button;
	
	private JTextField pc_id; // 合同编号

	private JTextField supp_id; // 供货商编号

	private JTextField comm_id; // 货物编号

	private JTextField purc_price; // 进价

	private JComboBox pc_pay_method; // 付款方式

	private JTextField pc_pay_period; // 帐期

	private JComboBox pc_date; // 签订日期

	private DataPicker dataPicker = new DataPicker();

	private JTextField pc_period; // 合同期限
	
	public PurchaseDataDialog(int type,String title){
		this.type = type;
		this.title = title;
		init();
	}
	
	public void init(){
		this.setSize(350, 400);
		this.setLayout(new GridBagLayout());
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle(title);
		
		this.add(CommonComponent.buildLabel("合同编号:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(pc_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("供货商编号:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(supp_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 1).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("货物编号:", null, null, null, null,
				null), new GBC(0, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(comm_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("进价:", null, null, null, null,
				null), new GBC(0, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(purc_price = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("付款方式:", null, null, null, null,
				null), new GBC(0, 4).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(pc_pay_method = CommonComponent.buildComboBox(new String[] {
				"工商银行", "建设银行", "邮政银行", "现金" }, null, null, null, null, null,
				false, true), new GBC(1, 4).setFill(GBC.CENTER).setAnchor(
				GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("帐期:", null, null, null, null,
				null), new GBC(0, 5).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(pc_pay_period = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 5)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("签订日期:", null, null, null, null,
				null), new GBC(0, 6).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(pc_date = dataPicker.getDataPacker(), new GBC(1, 6).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("合同期限:", null, null, null, null,
				null), new GBC(0, 7).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(pc_period = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 7).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		if (type == 1){
			this
					.add(button = buildButton("添加", null, null, null, null, null, null,
							true), new GBC(0, 8).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
			pc_id.setEditable(false);
			pc_id.setText("" + new PurchaseServer().findMaxID());
		}
		if (type == 2) {
			this
					.add(button = buildButton("查询", null, null, null, null, null, null,
							true), new GBC(0, 8).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
		}
		if (type == 3)
			this
					.add(button = buildButton("删除", null, null, null, null, null, null,
							true), new GBC(0, 8).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
		if (type == 4)
			this
					.add(button = buildButton("修改", null, null, null, null, null, null,
							true), new GBC(0, 8).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));

		this.add(buildButton("取消", null, null, null, null, null, null, true),
				new GBC(1, 8).setFill(GBC.CENTER).setAnchor(GBC.WEST)
						.setInset(5));
		
		if (type == 3 || type == 4) {
			editable(false);
			if (type == 3) {
				button.setEnabled(false);
			}
			pc_id.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (MethodUtil.isInt(pc_id.getText())) {
						PurchaseVO v = new PurchaseVO();
						// System.out.println(comm_id.getText());
						v.setPc_id(Integer.parseInt(pc_id.getText()));
						v = new PurchaseServer().find(v);
						if (v == null) {
							JOptionPane
									.showMessageDialog(null, "对不起，不存在该数据记录!");
							reset(null);
						} else {
							button.setEnabled(true);
							reset(v);
							editable(true);
							pc_id.setEditable(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "请输入正确的数据!");
						reset(null);
					}
					new PurchaseServer().close();
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
		button.addActionListener(new PurchaseDataDialogAction(this));
		return button;
	}
	
	public void editable(boolean b) {
		//pc_id.setEditable(b);
		supp_id.setEditable(b);
		comm_id.setEditable(b);
		purc_price.setEditable(b);
		pc_pay_method.setEditable(b);
		pc_pay_period.setEditable(b);
		pc_date.setEditable(b);
		pc_period.setEditable(b);
	}

	public void reset(PurchaseVO v) {
		if (v == null) {
			pc_id.setText("");
			supp_id.setText("");
			comm_id.setText("");
			purc_price.setText("");
			pc_pay_method.setSelectedIndex(0);
			pc_pay_period.setText("");
			pc_date.setSelectedIndex(0);
			pc_period.setText("");
		} else {
			pc_id.setText("" + v.getPc_id());
			supp_id.setText("" + v.getSupp_id());
			comm_id.setText("" + v.getComm_id());
			purc_price.setText("" + v.getPurc_price());
			pc_pay_method.setSelectedItem(v.getPc_pay_method());
			pc_pay_period.setText("" + v.getPc_pay_period());
			pc_date.setSelectedItem(v.getPc_date());
			pc_period.setText("" + v.getPc_period());
		}
	}

	public JTextField getComm_id() {
		return comm_id;
	}

	public DataPicker getDataPicker() {
		return dataPicker;
	}

	public JComboBox getPc_date() {
		return pc_date;
	}

	public JTextField getPc_id() {
		return pc_id;
	}

	public JComboBox getPc_pay_method() {
		return pc_pay_method;
	}

	public JTextField getPc_pay_period() {
		return pc_pay_period;
	}

	public JTextField getPc_period() {
		return pc_period;
	}

	public JTextField getPurc_price() {
		return purc_price;
	}

	public JTextField getSupp_id() {
		return supp_id;
	}
	
}
