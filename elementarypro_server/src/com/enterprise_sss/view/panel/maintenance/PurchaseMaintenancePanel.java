package com.enterprise_sss.view.panel.maintenance;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.enterprise_sss.action.PurchaseMaintenancePanelAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.data.DataPicker;
import com.enterprise_sss.view.panel.DataMaintenancePanel;
import com.enterprise_sss.vo.TableVO;

/**
 * �ɹ���ͬά�����
 * 
 * @author Administrator
 * 
 */
public class PurchaseMaintenancePanel extends DataMaintenancePanel {

	private JTextField pc_id; // ��ͬ���

	private JTextField supp_id; // �����̱��

	private JTextField comm_id; // ������

	private JTextField purc_price; // ����

	private JComboBox pc_pay_method; // ���ʽ

	private JTextField pc_pay_period; // ����

	private JComboBox pc_date; // ǩ������

	private DataPicker dataPicker = new DataPicker();

	private JTextField pc_period; // ��ͬ����

	public PurchaseMaintenancePanel(String title, TableVO tvo) {
		super(title, tvo);
		init();
		initAction();
	}

	public JPanel buildNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());

		panel.add(CommonComponent.buildLabel("��ͬ���:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(pc_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("�����̱��:", null, null, null, null,
				null), new GBC(2, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(3, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("������:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(comm_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 1).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("����:", null, null, null, null,
				null), new GBC(2, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(purc_price = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("���ʽ:", null, null, null, null,
				null), new GBC(0, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(pc_pay_method = CommonComponent.buildComboBox(new String[] {
				"֧Ʊ", "�ֽ�" }, null, null, null, null, null,
				false, true), new GBC(1, 2).setFill(GBC.CENTER).setAnchor(
				GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("����:", null, null, null, null,
				null), new GBC(2, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(pc_pay_period = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 2)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("ǩ������:", null, null, null, null,
				null), new GBC(0, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(pc_date = dataPicker.getDataPacker(), new GBC(1, 3).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("��ͬ����:", null, null, null, null,
				null), new GBC(2, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(pc_period = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(3, 3).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		return panel;
	}

	public void initAction() {
		PurchaseMaintenancePanelAction act = new PurchaseMaintenancePanelAction(this);
		button_add.addActionListener(act);
		button_reset.addActionListener(act);
		button_del.addActionListener(act);
		button_modify.addActionListener(act);
		button_find.addActionListener(act);
		
		// ��������Ӧ�¼�����������Table����ĵ����¼�
		table.changeSelection(1, 1, false, false);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// ����ĳ�У��ͽ����е�ֵ�ŵ�������Ӧ���ı�������
				int row = table.getSelectedRow();
				pc_id.setText("" + table.getValueAt(row, 0));
				supp_id.setText("" + table.getValueAt(row, 1));
				comm_id.setText("" + table.getValueAt(row, 2));
				purc_price.setText("" + table.getValueAt(row, 3));
				pc_pay_method.setSelectedItem("" + table.getValueAt(row, 4));
				pc_pay_period.setText("" + table.getValueAt(row, 5));
				pc_date.setSelectedItem("" + table.getValueAt(row, 6));
				pc_period.setText("" + table.getValueAt(row, 7));
			}
		});
	}
	
	public void reset() {
		pc_id.setText("");
		supp_id.setText("");
		comm_id.setText("");
		purc_price.setText("");
		pc_pay_method.setSelectedIndex(0);
		pc_pay_period.setText("");
		pc_date.setSelectedIndex(0);
		pc_period.setText("");
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
