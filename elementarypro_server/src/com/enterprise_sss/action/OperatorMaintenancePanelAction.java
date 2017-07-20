package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.OperatorServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.panel.maintenance.OperatorMaintenancePanel;
import com.enterprise_sss.vo.OperatorVO;

public class OperatorMaintenancePanelAction implements ActionListener {

	private OperatorMaintenancePanel panel;
	
	private OperatorServer os = new OperatorServer();
	
	public OperatorMaintenancePanelAction(OperatorMaintenancePanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		String oper_id = panel.getOper_id().getText().trim();
		String oper_spell_code = panel.getOper_spell_code().getText().trim();
		String oper_name = panel.getOper_name().getText().trim();
		String sex = panel.getSex().getSelectedCheckbox().getLabel();
		String oper_tel = panel.getOper_tel().getText().trim();
		String oper_mobile_tel = panel.getOper_mobile_tel().getText().trim();
		String oper_address = panel.getOper_address().getText().trim();
		String oper_postcode = panel.getOper_postcode().getText().trim();
		String oper_ID_number = panel.getOper_ID_number().getText().trim();
		String oper_sort = panel.getOper_sort().getText().trim();
		
		if ("查询".equals(str)) {
			findAll();
		} else if ("重置".equals(str)) {
			panel.reset();
		} else {
			if (MethodUtil.isInt(oper_id)) {

				if (os.validateDate(oper_name) && os.validateDate(oper_ID_number)) {
					OperatorVO ovo = new OperatorVO(Integer.parseInt(oper_id),
							oper_spell_code, oper_name, sex,
							oper_tel, oper_mobile_tel, oper_address,
							oper_postcode,oper_ID_number,
							oper_sort);

					if ("添加".equals(str)) {
						os.add(ovo);
					} else if ("删除".equals(str)) {
						os.del(ovo);
					} else if ("修改".equals(str)) {
						os.modify(ovo);
					}
					panel.reset();
					findAll();
				} else {
					JOptionPane.showMessageDialog(null, "联系人姓名与身份证不能为空!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的数据!");
			}
		}
		os.close();
	}

	public void findAll(){
		Vector data = os.findAll();
		DefaultTableModel mode = (DefaultTableModel)panel.table.getModel();
		Vector title = new Vector();
		for (int i = 0;i<mode.getColumnCount();i++) {
			title.add(mode.getColumnName(i));
		}
		mode.setDataVector(data,title);
	}

}
