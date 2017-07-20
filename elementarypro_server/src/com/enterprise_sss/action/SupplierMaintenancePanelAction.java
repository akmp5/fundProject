package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.SupplierServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.panel.maintenance.SupplierMaintenancePanel;
import com.enterprise_sss.vo.SupplierVO;

public class SupplierMaintenancePanelAction implements ActionListener {

	private SupplierMaintenancePanel panel;
	
	private SupplierServer ss = new SupplierServer();
	
	public SupplierMaintenancePanelAction(SupplierMaintenancePanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		String supp_id = panel.getSupp_id().getText().trim();
		String supp_spell_code = panel.getSupp_spell_code().getText()
				.trim();
		String supp_shortname = panel.getSupp_shortname().getText().trim();
		String supp_name = panel.getSupp_name().getText().trim();
		String supp_address = panel.getSupp_address().getText().trim();
		String supp_postcode = panel.getSupp_postcode().getText().trim();
		String supp_sort = panel.getSupp_sort().getText().trim();
		String supp_tel = panel.getSupp_tel().getText().trim();
		String supp_fax = panel.getSupp_fax().getText().trim();
		String supp_bank = panel.getSupp_bank().getText().trim();
		String supp_iban = panel.getSupp_iban().getText().trim();
		String supp_storage_address = panel.getSupp_storage_address()
				.getText().trim();
		String supp_storage_tel = panel.getSupp_storage_tel().getText()
				.trim();
		String oper_id = panel.getOper_id().getText().trim();
		
		if ("查询".equals(str)) {
			findAll();
		} else if ("重置".equals(str)) {
			panel.reset();
		} else {
			if (MethodUtil.isInt(supp_id) && MethodUtil.isInt(oper_id)) {

				SupplierVO svo = new SupplierVO(Integer.parseInt(supp_id),
						supp_spell_code, supp_shortname, supp_name,
						supp_address, supp_postcode, supp_sort, supp_tel,
						supp_fax, supp_bank, supp_iban, supp_storage_address,
						supp_storage_tel, Integer.parseInt(oper_id));

				if ("添加".equals(str)) {
					ss.add(svo);
				} else if ("删除".equals(str)) {
					ss.del(svo);
				} else if ("修改".equals(str)) {
					ss.modify(svo);
				}
				panel.reset();
				findAll();
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的数据!");
			}
		}
		ss.close();
	}

	public void findAll(){
		Vector data = ss.findAll();
		DefaultTableModel mode = (DefaultTableModel)panel.table.getModel();
		Vector title = new Vector();
		for (int i = 0;i<mode.getColumnCount();i++) {
			title.add(mode.getColumnName(i));
		}
		mode.setDataVector(data,title);
	}

}
