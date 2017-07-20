package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.PurchaseServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.dialog.basic_data.PurchaseDataDialog;
import com.enterprise_sss.vo.PurchaseVO;

public class PurchaseDataDialogAction implements ActionListener {

	private PurchaseDataDialog dialog;
	
	public PurchaseDataDialogAction(PurchaseDataDialog dialog){
		this.dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		PurchaseServer ps = new PurchaseServer();
		if ("取消".equals(str)) {
			dialog.dispose();
		} else {
			String pc_id = dialog.getPc_id().getText().trim();

			String supp_id = dialog.getSupp_id().getText().trim();

			String comm_id = dialog.getComm_id().getText().trim();

			String purc_price = dialog.getPurc_price().getText().trim();

			String pc_pay_method = dialog.getPc_pay_method().getSelectedItem().toString();

			String pc_pay_period = dialog.getPc_pay_period().getText().trim();

			String pc_date = dialog.getPc_date().getSelectedItem().toString();
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
			
			String pc_period = dialog.getPc_period().getText().trim();
			
			if (MethodUtil.isInt(pc_id) && MethodUtil.isInt(supp_id) &&MethodUtil.isInt(comm_id) && MethodUtil.isDouble(purc_price)) {

				if (ps.validateDate(pc_pay_period) && ps.validateDate(pc_period)) {
					PurchaseVO ovo = new PurchaseVO(Integer.parseInt(pc_id),
							Integer.parseInt(supp_id),Integer.parseInt(comm_id),new Double(purc_price),pc_pay_method,pc_pay_period,
							new Date(d.getTime()),pc_period);

					if ("添加".equals(str)) {
						ps.add(ovo);
						dialog.reset(null);
						dialog.getPc_id().setText("" + ps.findMaxID());
					} else if ("查询".equals(str)) {
						ps.find(ovo);
					} else if ("删除".equals(str)) {
						ps.del(ovo);
						dialog.reset(null);
						dialog.getPc_id().setEditable(true);
						dialog.editable(false);
					} else if ("修改".equals(str)) {
						ps.modify(ovo);
						dialog.reset(null);
						dialog.getPc_id().setEditable(true);
						dialog.editable(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "不可以存在空数据!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的数据!");
			}
		}
		ps.close();
	}

}
