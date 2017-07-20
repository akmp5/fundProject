package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.PurchaseInItemServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.dialog.PurchaseInItemDialog;
import com.enterprise_sss.vo.PurchaseInItemVO;

public class PurchaseInItemDialogAction implements ActionListener {

	private PurchaseInItemDialog dialog;
	
	public PurchaseInItemDialogAction(PurchaseInItemDialog dialog){
		this.dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		PurchaseInItemServer pois = new PurchaseInItemServer();
		String poi_id = null;
		String po_id = null;
		String comm_id = null;
		String poi_amount = null;
		String purc_price = null;
		Vector data = new Vector();
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0);
		data.add(0.0);
		DefaultTableModel mode = (DefaultTableModel) dialog.table.getModel();
		if ("+".equals(str)) {
			int n = pois.findMaxID();
			data.set(0, dialog.getCount() + n);
			data.set(1, dialog.id);
			mode.addRow(data);
			dialog.vdata.add(dialog.getCount() + n);
			dialog.setCount(dialog.getCount() + 1);
		} else if ("-".equals(str)) {
			int rowCount = mode.getRowCount() - 1;
			if (rowCount >= 0) {
				dialog.del.add(new Integer(dialog.table.getValueAt(rowCount, 0)
						.toString()));
				mode.removeRow(rowCount);
				mode.setRowCount(rowCount);
				dialog.vdata.remove(dialog.vdata.size() - 1);
				dialog.setCount(dialog.getCount() - 1);
			}
		} else if ("确定".equals(str)) {
			for (int i = 0; i < dialog.del.size(); i++) {
				PurchaseInItemVO pvo = new PurchaseInItemVO();
				pvo.setPii_id(dialog.del.get(i));
				pois.del(pvo);
			}
			for (int i = 0; i < dialog.table.getRowCount(); i++) {
				dialog.table.setValueAt(dialog.vdata.get(i
						+ (dialog.vdata.size() - dialog.table.getRowCount())),
						i, 0);
				dialog.table.setValueAt(dialog.id, i, 1);
			}
			boolean b = false;
			for (int i = 0; i < dialog.table.getRowCount(); i++) {
				poi_id = "" + dialog.table.getValueAt(i, 0);
				po_id = "" + dialog.table.getValueAt(i, 1);
				comm_id = "" + dialog.table.getValueAt(i, 2);
				poi_amount = "" + dialog.table.getValueAt(i, 3);
				purc_price = "" + dialog.table.getValueAt(i, 4);
				String poi_amount_temp = null;
				if (poi_amount != null && poi_amount.length() > 0) {
					if (poi_amount.substring(0, 1).equals("-")) {
						poi_amount_temp = poi_amount.substring(1, poi_amount.length());
					}
				}
				if (poi_amount_temp == null) {
					poi_amount_temp = poi_amount;
				}
				if (MethodUtil.isInt(comm_id) && MethodUtil.isInt(poi_amount_temp)
						&& MethodUtil.isDouble(purc_price)
						&& Integer.parseInt(comm_id) != 0
						&& Integer.parseInt(poi_amount_temp) != 0
						&& new Double(purc_price) != 0) {
					PurchaseInItemVO vo = new PurchaseInItemVO(Integer
							.parseInt(poi_id), Integer.parseInt(po_id), Integer
							.parseInt(comm_id), Integer.parseInt(poi_amount),
							new Double(purc_price));
					pois.add(vo);
				}
			}
			dialog.dispose();
		} else if ("取消".equals(str)) {
			dialog.dispose();
		}
		pois.close();
	}

}
