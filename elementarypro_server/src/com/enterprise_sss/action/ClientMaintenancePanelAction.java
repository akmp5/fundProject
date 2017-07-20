package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.ClientServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.panel.maintenance.ClientMaintenancePanel;
import com.enterprise_sss.vo.ClientVO;

public class ClientMaintenancePanelAction implements ActionListener {

	private ClientMaintenancePanel panel;
	
	private ClientServer cs = new ClientServer();
	
	public ClientMaintenancePanelAction(ClientMaintenancePanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		String clie_id = panel.getClie_id().getText().trim();
		String clie_spell_code = panel.getClie_spell_code().getText()
				.trim();
		String clie_shortname = panel.getClie_shortname().getText().trim();
		String clie_name = panel.getClie_name().getText().trim();
		String clie_linkman = panel.getClie_linkman().getText().trim();
		String clie_address = panel.getClie_address().getText().trim();
		String Clie_postcode = panel.getClie_postcode().getText().trim();
		String Clie_tel = panel.getClie_tel().getText().trim();
		String Clie_fax = panel.getClie_fax().getText().trim();
		String Clie_bank = panel.getClie_bank().getText().trim();
		String Clie_iban = panel.getClie_iban().getText().trim();
		String Clie_sort = panel.getClie_sort().getText().trim();
		String oper_id = panel.getOper_id().getText().trim();
		String Clie_CreditLimt = panel.getClie_CreditLimt().getText()
				.trim();
		if ("查询".equals(str)) {
			findAll();
		} else if ("重置".equals(str)) {
			panel.reset();
		} else {
			if (MethodUtil.isInt(clie_id) && MethodUtil.isInt(oper_id)) {

				if (cs.validateDate(clie_linkman)) {
					ClientVO cvo = new ClientVO(Integer.parseInt(clie_id),
							clie_spell_code, clie_shortname, clie_name,
							clie_linkman, clie_address, Clie_postcode,
							Clie_tel, Clie_fax, Clie_bank, Clie_iban,
							Clie_sort, Integer.parseInt(oper_id),
							Clie_CreditLimt);

					if ("添加".equals(str)) {
						cs.add(cvo);
					} else if ("删除".equals(str)) {
						cs.del(cvo);
					} else if ("修改".equals(str)) {
						cs.modify(cvo);
					}
					panel.reset();
					findAll();
				} else {
					JOptionPane.showMessageDialog(null, "联系人姓名不能为空!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的数据!");
			}
		}
		cs.close();
	}

	public void findAll(){
		Vector data = cs.findAll();
		DefaultTableModel mode = (DefaultTableModel)panel.table.getModel();
		Vector title = new Vector();
		for (int i = 0;i<mode.getColumnCount();i++) {
			title.add(mode.getColumnName(i));
		}
		mode.setDataVector(data,title);
	}
}
