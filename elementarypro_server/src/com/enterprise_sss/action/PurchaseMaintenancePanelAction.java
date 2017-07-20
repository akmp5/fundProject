package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.PurchaseServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.panel.maintenance.PurchaseMaintenancePanel;
import com.enterprise_sss.vo.PurchaseVO;

public class PurchaseMaintenancePanelAction implements ActionListener {

	private PurchaseMaintenancePanel panel;
	
	private PurchaseServer ps = new PurchaseServer();
	
	public PurchaseMaintenancePanelAction(PurchaseMaintenancePanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		String pc_id = panel.getPc_id().getText().trim();

		String supp_id = panel.getSupp_id().getText().trim();

		String comm_id = panel.getComm_id().getText().trim();

		String purc_price = panel.getPurc_price().getText().trim();

		String pc_pay_method = panel.getPc_pay_method().getSelectedItem().toString();

		String pc_pay_period = panel.getPc_pay_period().getText().trim();

		String pc_date = panel.getPc_date().getSelectedItem().toString();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			d = df.parse(pc_date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (d == null) {
			d = new java.util.Date();
		}
		
		String pc_period = panel.getPc_period().getText().trim();
		
		if ("查询".equals(str)) {
			findAll();
		} else if ("重置".equals(str)) {
			panel.reset();
		} else {
			if (MethodUtil.isInt(pc_id) && MethodUtil.isInt(supp_id) &&MethodUtil.isInt(comm_id) && MethodUtil.isDouble(purc_price)) {

				if (ps.validateDate(pc_pay_period) && ps.validateDate(pc_period)) {
					PurchaseVO ovo = new PurchaseVO(Integer.parseInt(pc_id),
							Integer.parseInt(supp_id),Integer.parseInt(comm_id),new Double(purc_price),pc_pay_method,pc_pay_period,
							new Date(d.getTime()),pc_period);

					if ("添加".equals(str)) {
						ps.add(ovo);
					} else if ("删除".equals(str)) {
						ps.del(ovo);
					} else if ("修改".equals(str)) {
						ps.modify(ovo);
					}
					panel.reset();
					findAll();
				} else {
					JOptionPane.showMessageDialog(null, "不可以存在空数据!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的数据!");
			}
		}
		ps.close();
	}

	public void findAll(){
		Vector data = ps.findAll();
		DefaultTableModel mode = (DefaultTableModel)panel.table.getModel();
		Vector title = new Vector();
		for (int i = 0;i<mode.getColumnCount();i++) {
			title.add(mode.getColumnName(i));
		}
		mode.setDataVector(data,title);
	}

}
