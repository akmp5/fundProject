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

import com.enterprise_sss.action.CommodityDataDialogAction;
import com.enterprise_sss.control.CommodityServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.CommodityVO;

public class CommodityDataDialog extends JDialog {

	private int type;

	private String title;
	
	private JButton button;

	private JTextField comm_id; // �������ı���

	private JTextField comm_bar_code; // �������ı���

	private JTextField comm_name; // ��Ʒ���ı���

	private JTextField comm_spell_code; // ƴ�������ı���

	private JTextField comm_standard; // ����ı���

	private JTextField comm_unit; // ��λ�ı���

	private JTextField comm_producing_area; // �����ı���

	private JTextField comm_sort; // ����ı���

	private JTextField purchase_price; // �������ı���

	private JTextField sale_price1; // ���ۼ��ı���

	private JTextField sale_price2; // ��Ա���ı���

	private JTextField lowest_sale_price; // ����ۼ��ı���

	public CommodityDataDialog(int type, String title) {
		this.type = type;
		this.title = title;
		init();
	}

	public void init() {
		this.setSize(350, 500);
		this.setLayout(new GridBagLayout());
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle(title);
		this.add(CommonComponent.buildLabel("������:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(comm_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("������:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(comm_bar_code = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("��Ʒ��:", null, null, null, null,
				null), new GBC(0, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(comm_name = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("ƴ������:", null, null, null, null,
				null), new GBC(0, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(comm_spell_code = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("���:", null, null, null, null, null), new GBC(0, 4)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(comm_standard = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 4)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("��λ:", null, null, null, null, null), new GBC(0, 5)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(comm_unit = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 5).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("����:", null, null, null, null, null), new GBC(0, 6)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(comm_producing_area = CommonComponent.buildTextField("txt",
				null, null, null, null, null, null, true, true), new GBC(1, 6)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("���:", null, null, null, null, null), new GBC(0, 7)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(comm_sort = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 7).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("������:", null, null, null, null,
				null), new GBC(0, 8).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(purchase_price = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 8)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("���ۼ�:", null, null, null, null,
				null), new GBC(0, 9).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(sale_price1 = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 9)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("��Ա��:", null, null, null, null,
				null), new GBC(0, 10).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(sale_price2 = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 10)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("����ۼ�:", null, null, null, null,
				null), new GBC(0, 11).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(lowest_sale_price = CommonComponent.buildTextField("txt",
				null, null, null, null, null, null, true, true), new GBC(1, 11)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		if (type == 1) {
			this
					.add(button = buildButton("���", null, null, null, null, null, null,
							true), new GBC(0, 12).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
			comm_id.setEditable(false);
			comm_id.setText("" + new CommodityServer().findMaxID());
		}
		if (type == 2) {
			this
					.add(button = buildButton("��ѯ", null, null, null, null, null, null,
							true), new GBC(0, 12).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
		}
		if (type == 3) {
			this
					.add(button = buildButton("ɾ��", null, null, null, null, null, null,
							true), new GBC(0, 12).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));

		}
		if (type == 4)
			this
					.add(button = buildButton("�޸�", null, null, null, null, null, null,
							true), new GBC(0, 12).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
		this.add(buildButton("ȡ��", null, null, null, null, null, null, true),
				new GBC(1, 12).setFill(GBC.CENTER).setAnchor(GBC.WEST)
						.setInset(5));
		
		if (type == 3 || type == 4) {
			editable(false);
			if (type == 3){
				button.setEnabled(false);
			}
			comm_id.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (MethodUtil.isInt(comm_id.getText())) {
						CommodityVO v = new CommodityVO();
						//System.out.println(comm_id.getText());
						v.setComm_id(Integer.parseInt(comm_id.getText()));
						v = new CommodityServer().find(v);
						if (v == null) {
							JOptionPane.showMessageDialog(null, "�Բ��𣬲����ڸ����ݼ�¼!");
							reset();
						} else {
							button.setEnabled(true);
							comm_bar_code.setText(v.getComm_bar_code());
							comm_name.setText(v.getComm_name());
							comm_spell_code.setText(v.getComm_spell_code());
							comm_standard.setText(v.getComm_standard());
							comm_unit.setText(v.getComm_unit());
							comm_producing_area.setText(v.getComm_producing_area());
							comm_sort.setText(v.getComm_sort());
							purchase_price.setText("" + v.getPurchase_price());
							sale_price1.setText("" + v.getSale_price1());
							sale_price2.setText("" + v.getSale_price2());
							lowest_sale_price.setText("" + v.getLowest_sale_price());
							editable(true);
							comm_id.setEditable(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "��������ȷ������!");
						reset();
					}
					new CommodityServer().close();
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
		button.addActionListener(new CommodityDataDialogAction(this));
		return button;
	}
	
	public void initAction(){
		
		
	}
	
	public void editable(boolean b){
		comm_bar_code.setEditable(b);
		comm_name.setEditable(b);
		comm_spell_code.setEditable(b);
		comm_standard.setEditable(b);
		comm_unit.setEditable(b);
		comm_producing_area.setEditable(b);
		comm_sort.setEditable(b);
		purchase_price.setEditable(b);
		sale_price1.setEditable(b);
		sale_price2.setEditable(b);
		lowest_sale_price.setEditable(b);
	}
	
	public void reset() {
		this.getComm_id().setText("");
		this.getComm_bar_code().setText("");
		this.getComm_name().setText("");
		this.getComm_spell_code().setText("");
		this.getComm_standard().setText("");
		this.getComm_unit().setText("");
		this.getComm_producing_area().setText("");
		this.getComm_sort().setText("");
		this.getPurchase_price().setText("");
		this.getSale_price1().setText("");
		this.getSale_price2().setText("");
		this.getLowest_sale_price().setText("");
	}

	public JTextField getComm_bar_code() {
		return comm_bar_code;
	}

	public JTextField getComm_id() {
		return comm_id;
	}

	public JTextField getComm_name() {
		return comm_name;
	}

	public JTextField getComm_producing_area() {
		return comm_producing_area;
	}

	public JTextField getComm_sort() {
		return comm_sort;
	}

	public JTextField getComm_spell_code() {
		return comm_spell_code;
	}

	public JTextField getComm_standard() {
		return comm_standard;
	}

	public JTextField getComm_unit() {
		return comm_unit;
	}

	public JTextField getLowest_sale_price() {
		return lowest_sale_price;
	}

	public JTextField getPurchase_price() {
		return purchase_price;
	}

	public JTextField getSale_price1() {
		return sale_price1;
	}

	public JTextField getSale_price2() {
		return sale_price2;
	}

}
