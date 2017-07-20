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

		String po_id = panel.getPo_id().getText().trim(); // 订单编号
		String supp_id = panel.getSupp_id().getText().trim(); // 供货商编号
		String po_date = panel.getPo_date().getSelectedItem().toString(); // 订货日期
		String po_begin_date = panel.getPo_begin_date().getSelectedItem()
				.toString(); // 有效起日
		String po_end_date = panel.getPo_end_date().getSelectedItem()
				.toString(); // 有效止日
		String oper_id = panel.getOper_id().getText().trim(); // 业务员编号
		String cbill = panel.getCbill().getText().trim(); // 制单人

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
		if ("查询".equals(str)) {
			findAll();
		} else if ("重置".equals(str)) {
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

					if ("添加".equals(str)) {
						if (ps.add(ovo)){
							TableVO v = new TableVO();
							v.setTitle(panel.getTitle());
							new PurchaseOrderItemDialog(2,"采购订单明细",v,Integer.parseInt(po_id));
						} else {
							JOptionPane.showMessageDialog(null, "对不起，已存在该编号！");
						}
					} else if ("删除".equals(str)) {
						ps.del(ovo);
						pois.del(ovo.getPo_id());
					} else if ("修改".equals(str)) {
						if (po_id.equals(panel.getId()))
							ps.modify(ovo);
						else
							JOptionPane.showMessageDialog(null, "对不起，编号不能修改！");
					}
					panel.reset();
					findAll();
				} else {
					JOptionPane.showMessageDialog(null, "制单人不能为空!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的数据!");
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
