package com.enterprise_sss.view.dialog.basic_data;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ButtonUI;

import com.enterprise_sss.action.OperatorDataDialogAction;
import com.enterprise_sss.control.OperatorServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.OperatorVO;

public class OperatorDataDialog extends JDialog {
	
	private int type;
	
	private String title;
	
	private JButton button;
	
	private JTextField oper_id; // 业务员编号文本框

	private JTextField oper_spell_code; // 拼音编码文本框

	private JTextField oper_name; // 姓名文本框

	private Checkbox box1, box2; // 性别选择框
	
	private CheckboxGroup sex = null;

	private JTextField oper_tel; // 电话文本框

	private JTextField oper_mobile_tel; // 手机文本框

	private JTextField oper_address; // 地址文本框

	private JTextField oper_postcode; // 邮编文本框

	private JTextField oper_ID_number; // 身份证号文本框

	private JTextField oper_sort; // 类别文本框
	
	public OperatorDataDialog(int type,String title){
		this.type = type;
		this.title = title;
		init();
	}
	
	public void init(){
		this.setSize(350, 500);
		this.setLayout(new GridBagLayout());
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle(title);
		
		this.add(CommonComponent.buildLabel("业务员编号:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(oper_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("拼音编码:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(oper_spell_code = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("姓名:", null, null, null, null,
				null), new GBC(0, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(oper_name = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("性别:", null, null, null, null,
				null), new GBC(0, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		sex = new CheckboxGroup();
		box1 = new Checkbox("男", true, sex);
		box2 = new Checkbox("女", false, sex);
		this.add(createPanel(), new GBC(1, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("电话:", null, null, null, null,
				null), new GBC(0, 4).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(oper_tel = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 4)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("手机:", null, null, null, null,
				null), new GBC(0, 5).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(oper_mobile_tel = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 5).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("地址:", null, null, null, null,
				null), new GBC(0, 6).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(oper_address = CommonComponent.buildTextField("txt",
				null, null, null, null, null, null, true, true), new GBC(1, 6)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("邮编:", null, null, null, null,
				null), new GBC(0, 7).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(oper_postcode = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 7).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("身份证号:", null, null, null, null,
				null), new GBC(0, 8).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(oper_ID_number = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 8)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("类别:", null, null, null, null,
				null), new GBC(0, 9).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(oper_sort = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 9)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		
		if (type == 1){
			this
					.add(button = buildButton("添加", null, null, null, null, null, null,
							true), new GBC(0, 10).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
			oper_id.setEditable(false);
			oper_id.setText("" + new OperatorServer().findMaxID());
		}
		if (type == 2) {
			this
					.add(button = buildButton("查询", null, null, null, null, null, null,
							true), new GBC(0, 10).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
		}
		if (type == 3)
			this
					.add(button = buildButton("删除", null, null, null, null, null, null,
							true), new GBC(0, 10).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
		if (type == 4)
			this
					.add(button = buildButton("修改", null, null, null, null, null, null,
							true), new GBC(0, 10).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));

		this.add(buildButton("取消", null, null, null, null, null, null, true),
				new GBC(1, 10).setFill(GBC.CENTER).setAnchor(GBC.WEST)
						.setInset(5));
		
		if (type == 3 || type == 4) {
			editable(false);
			if (type == 3) {
				button.setEnabled(false);
			}
			oper_id.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (MethodUtil.isInt(oper_id.getText())) {
						OperatorVO v = new OperatorVO();
						// System.out.println(comm_id.getText());
						v.setOper_id(Integer.parseInt(oper_id.getText()));
						v = new OperatorServer().find(v);
						if (v == null) {
							JOptionPane
									.showMessageDialog(null, "对不起，不存在该数据记录!");
							reset(null);
						} else {
							button.setEnabled(true);
							reset(v);
							editable(true);
							oper_id.setEditable(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "请输入正确的数据!");
						reset(null);
					}
					new OperatorServer().close();
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
		button.addActionListener(new OperatorDataDialogAction(this));
		return button;
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();

		panel.add(box1);
		panel.add(box2);
		return panel;
	}
	
	public void editable(boolean b) {
		oper_spell_code.setEditable(b);
		oper_name.setEditable(b);
		oper_tel.setEditable(b);
		oper_mobile_tel.setEditable(b);
		oper_address.setEditable(b);
		oper_postcode.setEditable(b);
		oper_ID_number.setEditable(b);
		oper_sort.setEditable(b);
	}

	public void reset(OperatorVO v) {
		if (v == null) {
			oper_id.setText("");
			oper_spell_code.setText("");
			oper_name.setText("");
			oper_tel.setText("");
			sex.setSelectedCheckbox(box1);
			oper_mobile_tel.setText("");
			oper_address.setText("");
			oper_postcode.setText("");
			oper_ID_number.setText("");
			oper_sort.setText("");
		} else {
			oper_id.setText("" + v.getOper_id());
			oper_spell_code.setText("" + v.getOper_spell_code());
			oper_name.setText("" + v.getOper_name());
			oper_tel.setText("" + v.getOper_tel());
			String s = v.getSex();
			if ("男".equals(s)) {
				sex.setSelectedCheckbox(box1);
			} else {
				sex.setSelectedCheckbox(box2);
			}
			oper_mobile_tel.setText("" + v.getOper_mobile_tel());
			oper_address.setText("" + v.getOper_address());
			oper_postcode.setText("" + v.getOper_postcode());
			oper_ID_number.setText("" + v.getOper_ID_number());
			oper_sort.setText("" + v.getOper_sort());
		}
	}

	public Checkbox getBox1() {
		return box1;
	}

	public Checkbox getBox2() {
		return box2;
	}

	public JTextField getOper_address() {
		return oper_address;
	}

	public JTextField getOper_id() {
		return oper_id;
	}

	public JTextField getOper_ID_number() {
		return oper_ID_number;
	}

	public JTextField getOper_mobile_tel() {
		return oper_mobile_tel;
	}

	public JTextField getOper_name() {
		return oper_name;
	}

	public JTextField getOper_postcode() {
		return oper_postcode;
	}

	public JTextField getOper_sort() {
		return oper_sort;
	}

	public JTextField getOper_spell_code() {
		return oper_spell_code;
	}

	public JTextField getOper_tel() {
		return oper_tel;
	}

	public CheckboxGroup getSex() {
		return sex;
	}
	
}
