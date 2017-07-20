package com.enterprise_sss.view.panel.maintenance;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.enterprise_sss.action.DepotMaintenancePanelAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.view.panel.DataMaintenancePanel;
import com.enterprise_sss.vo.TableVO;

/**
 * �ֿ���Ϣά�����
 * @author Administrator
 *
 */
public class DepotMaintenancePanel extends DataMaintenancePanel {

	private JTextField depo_id;      //�ֿ����ı���
	
	private JTextField depo_name;    //�ֿ����ı���
	
	private JTextField depo_sort;     //����ı���
	
	private JTextField depo_desc;     //��ע�ı���
	
	public DepotMaintenancePanel(String title,TableVO tvo){
		super(title,tvo);
		init();
		initAction();
	}
	
	public JTextField getDepo_desc() {
		return depo_desc;
	}

	public JTextField getDepo_id() {
		return depo_id;
	}

	public JTextField getDepo_name() {
		return depo_name;
	}

	public JTextField getDepo_sort() {
		return depo_sort;
	}

	public JPanel buildNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		panel.add(CommonComponent.buildLabel("�ֿ���:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(depo_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("�ֿ���:", null, null, null, null,
				null), new GBC(2, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(depo_name = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 0)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		panel.add(CommonComponent.buildLabel("���:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(depo_sort = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 1).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("��ע:", null, null, null, null,
				null), new GBC(2, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(depo_desc = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(3, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		
		return panel;
	}
	
	public void initAction(){
		DepotMaintenancePanelAction act = new DepotMaintenancePanelAction(this);
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
				depo_id.setText("" + table.getValueAt(row, 0));
				depo_name.setText("" + table.getValueAt(row, 1));
				depo_sort.setText("" + table.getValueAt(row, 2));
				depo_desc.setText("" + table.getValueAt(row, 3));
			}
		});
	}

	public void reset() {
		depo_id.setText("");
		depo_name.setText("");
		depo_sort.setText("");
		depo_desc.setText("");
	}
}
