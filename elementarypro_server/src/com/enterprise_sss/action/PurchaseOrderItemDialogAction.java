package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.PurchaseOrderItemServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.dialog.PurchaseOrderItemDialog;
import com.enterprise_sss.vo.PurchaseOrderItemVO;

public class PurchaseOrderItemDialogAction implements ActionListener {

	private PurchaseOrderItemDialog dialog;

	public PurchaseOrderItemDialogAction(PurchaseOrderItemDialog dialog) {
		this.dialog = dialog;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		PurchaseOrderItemServer pois = new PurchaseOrderItemServer();
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
				PurchaseOrderItemVO pvo = new PurchaseOrderItemVO();
				pvo.setPoi_id(dialog.del.get(i));
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
				if (MethodUtil.isInt(comm_id) && MethodUtil.isInt(poi_amount)
						&& MethodUtil.isDouble(purc_price)
						&& Integer.parseInt(comm_id) != 0
						&& Integer.parseInt(poi_amount) != 0
						&& new Double(purc_price) != 0) {
					PurchaseOrderItemVO vo = new PurchaseOrderItemVO(Integer
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
