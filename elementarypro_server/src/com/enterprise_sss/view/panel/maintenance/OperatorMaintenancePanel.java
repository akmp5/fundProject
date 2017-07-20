package com.enterprise_sss.view.panel.maintenance;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.enterprise_sss.action.OperatorMaintenancePanelAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.view.panel.DataMaintenancePanel;
import com.enterprise_sss.vo.TableVO;

/**
 * ҵ��Ա��Ϣά�����
 * @author Administrator
 *
 */
public class OperatorMaintenancePanel extends DataMaintenancePanel {

	private JTextField oper_id; // ҵ��Ա����ı���

	private JTextField oper_spell_code; // ƴ�������ı���

	private JTextField oper_name; // �����ı���

	private Checkbox box1, box2; // �Ա�ѡ���
	
	private CheckboxGroup sex = null;

	private JTextField oper_tel; // �绰�ı���

	private JTextField oper_mobile_tel; // �ֻ��ı���

	private JTextField oper_address; // ��ַ�ı���

	private JTextField oper_postcode; // �ʱ��ı���

	private JTextField oper_ID_number; // ���֤���ı���

	private JTextField oper_sort; // ����ı���
	
	public OperatorMaintenancePanel(String title,TableVO tvo){
		super(title,tvo);
		init();
		initAction();
	}
	
	public JPanel buildNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		panel.add(CommonComponent.buildLabel("ҵ��Ա���:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("ƴ������:", null, null, null, null,
				null), new GBC(2, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_spell_code = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 0)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		panel.add(CommonComponent.buildLabel("����:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_name = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 1).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("�Ա�:", null, null, null, null,
				null), new GBC(2, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		
		sex = new CheckboxGroup();
		box1 = new Checkbox("��", true, sex);
		box2 = new Checkbox("Ů", false, sex);
		panel.add(createPanel(), new GBC(3, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		panel.add(CommonComponent.buildLabel("�绰:", null, null, null, null,
				null), new GBC(0, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_tel = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 2)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("�ֻ�:", null, null, null, null,
				null), new GBC(2, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_mobile_tel = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(3, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("��ַ:", null, null, null, null,
				null), new GBC(0, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_address = CommonComponent.buildTextField("txt",
				null, null, null, null, null, null, true, true), new GBC(1, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("�ʱ�:", null, null, null, null,
				null), new GBC(2, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_postcode = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(3, 3).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		panel.add(CommonComponent.buildLabel("���֤��:", null, null, null, null,
				null), new GBC(0, 4).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_ID_number = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 4)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("���:", null, null, null, null,
				null), new GBC(2, 4).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_sort = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 4)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		return panel;
	}

	public void initAction(){
		OperatorMaintenancePanelAction act = new OperatorMaintenancePanelAction(this);
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
				oper_id.setText("" + table.getValueAt(row, 0));
				oper_spell_code.setText("" + table.getValueAt(row, 1));
				oper_name.setText("" + table.getValueAt(row, 2));
				String str = "" + table.getValueAt(row, 3);
				if ("��".equals(str)) {
					sex.setSelectedCheckbox(box1);
				} else {
					sex.setSelectedCheckbox(box2);
				}
				oper_tel.setText("" + table.getValueAt(row, 4));
				oper_mobile_tel.setText("" + table.getValueAt(row, 5));
				oper_address.setText("" + table.getValueAt(row, 6));
				oper_postcode.setText("" + table.getValueAt(row, 7));
				oper_ID_number.setText("" + table.getValueAt(row, 8));
				oper_sort.setText("" + table.getValueAt(row, 9));
			}
		});
	}
	
	public void reset() {
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
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();

		panel.add(box1);
		panel.add(box2);
		return panel;
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
