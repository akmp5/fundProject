package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.PurchaseOrderItemServer;
import com.enterprise_sss.control.PurchaseOrderServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.dialog.PurchaseOrderItemDialog;
import com.enterprise_sss.view.panel.maintenance.PurchaseOrderPanel;
import com.enterprise_sss.vo.PurchaseOrderVO;
import com.enterprise_sss.vo.TableVO;

public class PurchaseOrderPanelAction implements ActionListener {

	private PurchaseOrderPanel panel;

	private PurchaseOrderServer ps = new PurchaseOrderServer();
	
	private PurchaseOrderItemServer pois = new PurchaseOrderItemServer();

	public PurchaseOrderPanelAction(PurchaseOrderPanel panel) {
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();

		String po_id = panel.getPo_id().getText().trim(); // �������
		String supp_id = panel.getSupp_id().getText().trim(); // �����̱��
		String po_date = panel.getPo_date().getSelectedItem().toString(); // ��������
		String po_begin_date = panel.getPo_begin_date().getSelectedItem()
				.toString(); // ��Ч����
		String po_end_date = panel.getPo_end_date().getSelectedItem()
				.toString(); // ��Чֹ��
		String oper_id = panel.getOper_id().getText().trim(); // ҵ��Ա���
		String cbill = panel.getCbill().getText().trim(); // �Ƶ���

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date pd = null, pbd = null, ped = null;
		try {
			pd = df.parse(po_date);
			pbd = df.parse(po_begin_date);
			ped = df.parse(po_end_date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (pd == null)
			pd = new java.util.Date();
		if (pbd == null)
			pbd = new java.util.Date();
		if (ped == null)
			ped = new java.util.Date();
		if ("��ѯ".equals(str)) {
			findAll();
		} else if ("����".equals(str)) {
			panel.reset();
		} else {
			if (MethodUtil.isInt(po_id) && MethodUtil.isInt(supp_id)
					&& MethodUtil.isInt(oper_id)) {

				if (ps.validateDate(cbill)) {
					PurchaseOrderVO ovo = new PurchaseOrderVO(Integer
							.parseInt(po_id), Integer.parseInt(supp_id),
							new Date(pd.getTime()), new Date(pbd.getTime()),
							new Date(ped.getTime()), Integer.parseInt(oper_id),
							cbill);

					if ("���".equals(str)) {
						if (ps.add(ovo)){
							TableVO v = new TableVO();
							v.setTitle(panel.getTitle());
							new PurchaseOrderItemDialog(2,"�ɹ�������ϸ",v,Integer.parseInt(po_id));
						} else {
							JOptionPane.showMessageDialog(null, "�Բ����Ѵ��ڸñ�ţ�");
						}
					} else if ("ɾ��".equals(str)) {
						ps.del(ovo);
						pois.del(ovo.getPo_id());
					} else if ("�޸�".equals(str)) {
						if (po_id.equals(panel.getId()))
							ps.modify(ovo);
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
		pois.close();

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
