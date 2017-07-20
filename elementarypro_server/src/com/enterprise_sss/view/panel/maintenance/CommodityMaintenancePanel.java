package com.enterprise_sss.view.panel.maintenance;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.enterprise_sss.action.CommodityMaintenancePanelAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.view.panel.DataMaintenancePanel;
import com.enterprise_sss.vo.TableVO;

/**
 * ��Ʒ��Ϣά�����
 * 
 * @author Administrator
 * 
 */
public class CommodityMaintenancePanel extends DataMaintenancePanel {

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

	public CommodityMaintenancePanel(String title, TableVO tvo) {
		super(title, tvo);
		init();
		initAction();
	}

	public JPanel buildNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(CommonComponent.buildLabel("������:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(comm_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("������:", null, null, null, null,
				null), new GBC(2, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(comm_bar_code = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 0)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("��Ʒ��:", null, null, null, null,
				null), new GBC(4, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(comm_name = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(5, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("ƴ������:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(comm_spell_code = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("���:", null, null, null, null,
				null), new GBC(2, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(comm_standard = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("��λ:", null, null, null, null,
				null), new GBC(4, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(comm_unit = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(5, 1).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("����:", null, null, null, null,
				null), new GBC(0, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(comm_producing_area = CommonComponent.buildTextField("txt",
				null, null, null, null, null, null, true, true), new GBC(1, 2)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("���:", null, null, null, null,
				null), new GBC(2, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(comm_sort = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(3, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("������:", null, null, null, null,
				null), new GBC(4, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(purchase_price = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(5, 2)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("���ۼ�:", null, null, null, null,
				null), new GBC(0, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(sale_price1 = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("��Ա��:", null, null, null, null,
				null), new GBC(2, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(sale_price2 = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("����ۼ�:", null, null, null, null,
				null), new GBC(4, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(lowest_sale_price = CommonComponent.buildTextField("txt",
				null, null, null, null, null, null, true, true), new GBC(5, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		return panel;
	}

	public void initAction() {
		CommodityMaintenancePanelAction act = new CommodityMaintenancePanelAction(
				this);
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
				comm_id.setText("" + table.getValueAt(row, 0));
				comm_bar_code.setText("" + table.getValueAt(row, 1));
				comm_name.setText("" + table.getValueAt(row, 2));
				comm_spell_code.setText("" + table.getValueAt(row, 3));
				comm_standard.setText("" + table.getValueAt(row, 4));
				comm_unit.setText("" + table.getValueAt(row, 5));
				comm_producing_area.setText("" + table.getValueAt(row, 6));
				comm_sort.setText("" + table.getValueAt(row, 7));
				purchase_price.setText("" + table.getValueAt(row, 8));
				sale_price1.setText("" + table.getValueAt(row, 9));
				sale_price2.setText("" + table.getValueAt(row, 10));
				lowest_sale_price.setText("" + table.getValueAt(row, 11));
			}
		});
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
