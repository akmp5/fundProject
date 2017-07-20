package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.PurchaseInItemServer;
import com.enterprise_sss.control.PurchaseInServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.dialog.PurchaseInItemDialog;
import com.enterprise_sss.view.panel.maintenance.PurchaseInPanel;
import com.enterprise_sss.vo.PurchaseInVO;
import com.enterprise_sss.vo.TableVO;

public class PurchaseInPanelAction implements ActionListener {

	private PurchaseInPanel panel;
	
	private PurchaseInServer ps = new PurchaseInServer();
	
	private PurchaseInItemServer piis = new PurchaseInItemServer();
	
	public PurchaseInPanelAction(PurchaseInPanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();

		String pib_id = panel.getPib_id().getText().trim();
		String supp_id = panel.getSupp_id().getText().trim();
		String pib_date = panel.getPib_date().getSelectedItem().toString();
		String oper_id = panel.getOper_id().getText().toString();
		String cbill = panel.getCbill().getText().toString();
		String inspector = panel.getInspector().getText().trim();
		String keeper = panel.getKeeper().getText().trim();
		String po_id = panel.getPo_id().getText().trim();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			d = df.parse(pib_date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (d == null)
			d = new java.util.Date();
		if ("��ѯ".equals(str)) {
			findAll();
		} else if ("����".equals(str)) {
			panel.reset();
		} else {
			if (MethodUtil.isInt(pib_id) && MethodUtil.isInt(supp_id)
					&& MethodUtil.isInt(oper_id) && MethodUtil.isInt(po_id)) {

				if (ps.validateDate(cbill) && ps.validateDate(keeper) && ps.validateDate(inspector)) {
					PurchaseInVO pvo = new PurchaseInVO(Integer
							.parseInt(pib_id), Integer.parseInt(supp_id),
							new Date(d.getTime()),Integer.parseInt(oper_id), cbill,inspector,keeper,
							Integer.parseInt(po_id));

					if ("���".equals(str)) {
						if (ps.add(pvo)){
							TableVO v = new TableVO();
							v.setTitle(panel.getTit());
							new PurchaseInItemDialog(2,"�ɹ�������ϸ",v,Integer.parseInt(pib_id));
						} else {
							JOptionPane.showMessageDialog(null, "�Բ����Ѵ��ڸñ�ţ�");
						}
					} else if ("ɾ��".equals(str)) {
						ps.del(pvo);
						piis.del(pvo.getPib_id());
					} else if ("�޸�".equals(str)) {
						if (po_id.equals(panel.getId()))
							ps.modify(pvo);
						else
							JOptionPane.showMessageDialog(null, "�Բ��𣬱�Ų����޸ģ�");
					}
					panel.reset();
					findAll();
				} else {
					JOptionPane.showMessageDialog(null, "�Ƶ��˲���Ϊ��!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "��������ȷ������!");
			}
		}
		ps.close();
		piis.close();
	}

	public void findAll() {
		Vector data = ps.findAll();
		DefaultTableModel mode = (DefaultTableModel) panel.table.getModel();
		Vector title = new Vector();
		for (int i = 0; i < mode.getColumnCount(); i++) {
			title.add(mode.getColumnName(i));
		}
		mode.setDataVector(data, title);
	}

}
