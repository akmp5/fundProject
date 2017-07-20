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

import com.enterprise_sss.action.ClientDataDialogAction;
import com.enterprise_sss.control.ClientServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.ClientVO;

public class ClientDataDialog extends JDialog {

	private int type;

	private String title;

	private JButton button;

	private JTextField clie_id; // �ͻ�����ı���

	private JTextField clie_spell_code; // ƴ�������ı���

	private JTextField clie_shortname; // ����ı���

	private JTextField clie_name; // �����ı���

	private JTextField clie_linkman; // ��ϵ���ı���

	private JTextField clie_address; // ��ַ�ı���

	private JTextField Clie_postcode; // �ʱ��ı���

	private JTextField Clie_tel; // �绰�ı���

	private JTextField Clie_fax; // �����ı���

	private JTextField Clie_bank; // �������ı���

	private JTextField Clie_iban; // �����ʺ��ı���

	private JTextField Clie_sort; // �����ı���

	private JTextField oper_id; // ҵ��Ա�ɣ��ı���

	private JTextField Clie_CreditLimt; // ���Ŷ���ı���

	public ClientDataDialog(int type, String title) {
		this.type = type;
		this.title = title;
		init();
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

	public void init() {
		this.setSize(350, 550);
		this.setLayout(new GridBagLayout());
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle(title);

		this.add(CommonComponent.buildLabel("�ͻ����:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(clie_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("ƴ������:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(clie_spell_code = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("���:", null, null, null, null, null), new GBC(0, 2)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(clie_shortname = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 2)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("����:", null, null, null, null, null), new GBC(0, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(clie_name = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 3).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("��ϵ��:", null, null, null, null,
				null), new GBC(0, 4).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(clie_linkman = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 4)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("��ַ:", null, null, null, null, null), new GBC(0, 5)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(clie_address = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 5)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("�ʱ�:", null, null, null, null, null), new GBC(0, 6)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(Clie_postcode = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 6)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("�绰:", null, null, null, null, null), new GBC(0, 7)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(Clie_tel = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 7).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("����:", null, null, null, null, null), new GBC(0, 8)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		this.add(Clie_fax = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 8).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("������:", null, null, null, null,
				null), new GBC(0, 9).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(Clie_bank = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 9).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("�����ʺ�:", null, null, null, null,
				null), new GBC(0, 10).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(Clie_iban = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 10).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent
				.buildLabel("����:", null, null, null, null, null),
				new GBC(0, 11).setFill(GBC.CENTER).setAnchor(GBC.EAST)
						.setInset(5));
		this.add(Clie_sort = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 11).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("ҵ��Ա�ɣ�:", null, null, null, null,
				null), new GBC(0, 12).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(oper_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 12).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		this.add(CommonComponent.buildLabel("���Ŷ��:", null, null, null, null,
				null), new GBC(0, 13).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(Clie_CreditLimt = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 13)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		if (type == 1){
			this.add(button = buildButton("���", null, null, null, null, null,
					null, true), new GBC(0, 14).setFill(GBC.CENTER).setAnchor(
					GBC.WEST).setInset(5));
			clie_id.setEditable(false);
			clie_id.setText("" + new ClientServer().findMaxID());
		}
		if (type == 2) {
			this.add(button = buildButton("��ѯ", null, null, null, null, null,
					null, true), new GBC(0, 14).setFill(GBC.CENTER).setAnchor(
					GBC.WEST).setInset(5));
		}
		if (type == 3)
			this.add(button = buildButton("ɾ��", null, null, null, null, null,
					null, true), new GBC(0, 14).setFill(GBC.CENTER).setAnchor(
					GBC.WEST).setInset(5));
		if (type == 4)
			this.add(button = buildButton("�޸�", null, null, null, null, null,
					null, true), new GBC(0, 14).setFill(GBC.CENTER).setAnchor(
					GBC.WEST).setInset(5));

		this.add(buildButton("ȡ��", null, null, null, null, null, null, true),
				new GBC(1, 14).setFill(GBC.CENTER).setAnchor(GBC.WEST)
						.setInset(5));

		if (type == 3 || type == 4) {
			editable(false);
			if (type == 3) {
				button.setEnabled(false);
			}
			clie_id.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (MethodUtil.isInt(clie_id.getText())) {
						ClientVO v = new ClientVO();
						// System.out.println(comm_id.getText());
						v.setClie_id(Integer.parseInt(clie_id.getText()));
						v = new ClientServer().find(v);
						if (v == null) {
							JOptionPane
									.showMessageDialog(null, "�Բ��𣬲����ڸ����ݼ�¼!");
							reset(null);
						} else {
							button.setEnabled(true);
							reset(v);
							editable(true);
							clie_id.setEditable(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "��������ȷ������!");
						reset(null);
					}
					new ClientServer().close();
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
		button.addActionListener(new ClientDataDialogAction(this));
		return button;
	}

	public void editable(boolean b) {
		clie_spell_code.setEditable(b);
		clie_shortname.setEditable(b);
		clie_name.setEditable(b);
		clie_linkman.setEditable(b);
		clie_address.setEditable(b);
		Clie_postcode.setEditable(b);
		Clie_tel.setEditable(b);
		Clie_fax.setEditable(b);
		Clie_bank.setEditable(b);
		Clie_iban.setEditable(b);
		Clie_sort.setEditable(b);
		oper_id.setEditable(b);
		Clie_CreditLimt.setEditable(b);
	}

	public void reset(ClientVO v) {
		if (v == null) {
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
		} else {
			clie_id.setText("" + v.getClie_id());
			clie_spell_code.setText("" + v.getClie_spell_code());
			clie_shortname.setText("" + v.getClie_shortname());
			clie_name.setText("" + v.getClie_name());
			clie_linkman.setText("" + v.getClie_linkman());
			clie_address.setText("" + v.getClie_address());
			Clie_postcode.setText("" + v.getClie_postcode());
			Clie_tel.setText("" + v.getClie_tel());
			Clie_fax.setText("" + v.getClie_fax());
			Clie_bank.setText("" + v.getClie_bank());
			Clie_iban.setText("" + v.getClie_iban());
			Clie_sort.setText("" + v.getClie_sort());
			oper_id.setText("" + v.getOper_id());
			Clie_CreditLimt.setText("" + v.getClie_CreditLimt());
		}
	}

}
