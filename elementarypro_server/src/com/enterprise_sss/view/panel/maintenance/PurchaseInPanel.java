package com.enterprise_sss.view.panel.maintenance;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.enterprise_sss.action.PurchaseInPanelAction;
import com.enterprise_sss.control.PurchaseInItemServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.data.DataPicker;
import com.enterprise_sss.view.dialog.PurchaseInItemDialog;
import com.enterprise_sss.view.panel.DataMaintenancePanel;
import com.enterprise_sss.vo.TableVO;

public class PurchaseInPanel extends DataMaintenancePanel {

	private String id;
	
	private Vector tit = new Vector();
	
	private JTextField pib_id; // ���������

	private JTextField supp_id; // �����̱��

	private JComboBox pib_date; // ��������

	private JTextField oper_id; // ҵ��Ա���

	private JTextField cbill; // �Ƶ���

	private DataPicker dataPicker = new DataPicker();

	private JTextField inspector; // ����Ա

	private JTextField keeper; // ����Ա
	
	private JTextField po_id;   //�ɹ��������
	
	public PurchaseInPanel(String title, TableVO tvo){
		super(title, tvo);
		tit.add("����������");
		tit.add("���������");
		tit.add("������");
		tit.add("��������");
		tit.add("����");
		init();
		initAction();
	}
	
	public JPanel buildNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());

		panel.add(CommonComponent.buildLabel("���������:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(pib_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("�����̱��:", null, null, null, null,
				null), new GBC(2, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(supp_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(3, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("��������:", null, null, null, null,
				null), new GBC(4, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(pib_date = dataPicker.getDataPacker(), new GBC(5, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		panel.add(CommonComponent.buildLabel("ҵ��Ա���:", null, null, null, null,
				null), new GBC(6, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(oper_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(7, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

		panel.add(CommonComponent.buildLabel("�Ƶ���:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(cbill = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 1).setFill(
				GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		panel.add(CommonComponent.buildLabel("����Ա:", null, null, null, null,
				null), new GBC(2, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(inspector = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(3, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		panel.add(CommonComponent.buildLabel("����Ա:", null, null, null, null,
				null), new GBC(4, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(keeper = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(5, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));
		panel.add(CommonComponent.buildLabel("�ɹ��������:", null, null, null, null,
				null), new GBC(6, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		panel.add(po_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(7, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.EAST).setInset(5));

		return panel;
	}
	
	public void initAction(){
		PurchaseInPanelAction act = new PurchaseInPanelAction(this);
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
				id = "" + table.getValueAt(row, 0);
				pib_id.setText("" + table.getValueAt(row, 0));
				supp_id.setText("" + table.getValueAt(row, 1));
				pib_date.setSelectedItem("" + table.getValueAt(row, 2));
				oper_id.setText("" + table.getValueAt(row, 3));
				cbill.setText("" + table.getValueAt(row, 4));
				inspector.setText("" + table.getValueAt(row, 5));
				keeper.setText("" + table.getValueAt(row, 6));
				po_id.setText("" + table.getValueAt(row, 7));
				
				PurchaseInItemServer piis = new PurchaseInItemServer();
				Vector data = piis.findAll(new Integer(table.getValueAt(row, 0).toString()));
				TableVO v = new TableVO();
				piis.close();
				v.setTitle(tit);
				v.setData(data);
				new PurchaseInItemDialog(2,"�ɹ�������ϸ",v,new Integer(table.getValueAt(row, 0).toString()));
			}
		});
	}

	public void reset() {

		pib_id.setText("");
		supp_id.setText("");
		pib_date.setSelectedIndex(0);
		oper_id.setText("");
		cbill.setText("");
		inspector.setText("");
		keeper.setText("");
		po_id.setText("");

	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Vector getTit() {
		return tit;
	}

	public void setTit(Vector tit) {
		this.tit = tit;
	}

	public JTextField getCbill() {
		return cbill;
	}

	public JTextField getInspector() {
		return inspector;
	}

	public JTextField getKeeper() {
		return keeper;
	}

	public JTextField getOper_id() {
		return oper_id;
	}

	public JComboBox getPib_date() {
		return pib_date;
	}

	public JTextField getPib_id() {
		return pib_id;
	}

	public JTextField getPo_id() {
		return po_id;
	}

	public JTextField getSupp_id() {
		return supp_id;
	}

}
