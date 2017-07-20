package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.OperatorServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.dialog.basic_data.OperatorDataDialog;
import com.enterprise_sss.vo.OperatorVO;

public class OperatorDataDialogAction implements ActionListener {

	private OperatorDataDialog dialog;
	
	public OperatorDataDialogAction(OperatorDataDialog dialog){
		this.dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		OperatorServer os = new OperatorServer();
		if ("ȡ��".equals(str)) {
			dialog.dispose();
		} else {
			String oper_id = dialog.getOper_id().getText().trim();
			String oper_spell_code = dialog.getOper_spell_code().getText().trim();
			String oper_name = dialog.getOper_name().getText().trim();
			String sex = dialog.getSex().getSelectedCheckbox().getLabel();
			String oper_tel = dialog.getOper_tel().getText().trim();
			String oper_mobile_tel = dialog.getOper_mobile_tel().getText().trim();
			String oper_address = dialog.getOper_address().getText().trim();
			String oper_postcode = dialog.getOper_postcode().getText().trim();
			String oper_ID_number = dialog.getOper_ID_number().getText().trim();
			String oper_sort = dialog.getOper_sort().getText().trim();
			
			if (MethodUtil.isInt(oper_id)) {

				if (os.validateDate(oper_name) && os.validateDate(oper_ID_number)) {
					OperatorVO ovo = new OperatorVO(Integer.parseInt(oper_id),
							oper_spell_code, oper_name, sex,
							oper_tel, oper_mobile_tel, oper_address,
							oper_postcode,oper_ID_number,
							oper_sort);

					if ("���".equals(str)) {
						os.add(ovo);
						dialog.reset(null);
						dialog.getOper_id().setText("" + os.findMaxID());
					} else if ("��ѯ".equals(str)) {
						os.find(ovo);
					} else if ("ɾ��".equals(str)) {
						os.del(ovo);
						dialog.reset(null);
						dialog.getOper_id().setEditable(true);
						dialog.editable(false);
					} else if ("�޸�".equals(str)) {
						os.modify(ovo);
						dialog.reset(null);
						dialog.getOper_id().setEditable(true);
						dialog.editable(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "��ϵ�����������֤����Ϊ��!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "��������ȷ������!");
			}
		}
		os.close();
	}

}
