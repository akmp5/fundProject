package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.SupplierServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.dialog.basic_data.SupplierDataDialog;
import com.enterprise_sss.vo.SupplierVO;

public class SupplierDataDialogAction implements ActionListener {

	private SupplierDataDialog dialog;

	public SupplierDataDialogAction(SupplierDataDialog dialog) {
		this.dialog = dialog;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		SupplierServer ss = new SupplierServer();
		if ("取消".equals(str)) {
			dialog.dispose();
		} else {
			String supp_id = dialog.getSupp_id().getText().trim();
			String supp_spell_code = dialog.getSupp_spell_code().getText()
					.trim();
			String supp_shortname = dialog.getSupp_shortname().getText().trim();
			String supp_name = dialog.getSupp_name().getText().trim();
			String supp_address = dialog.getSupp_address().getText().trim();
			String supp_postcode = dialog.getSupp_postcode().getText().trim();
			String supp_sort = dialog.getSupp_sort().getText().trim();
			String supp_tel = dialog.getSupp_tel().getText().trim();
			String supp_fax = dialog.getSupp_fax().getText().trim();
			String supp_bank = dialog.getSupp_bank().getText().trim();
			String supp_iban = dialog.getSupp_iban().getText().trim();
			String supp_storage_address = dialog.getSupp_storage_address()
					.getText().trim();
			String supp_storage_tel = dialog.getSupp_storage_tel().getText()
					.trim();
			String oper_id = dialog.getOper_id().getText().trim();
			if (MethodUtil.isInt(supp_id) && MethodUtil.isInt(oper_id)) {

				SupplierVO svo = new SupplierVO(Integer.parseInt(supp_id),
						supp_spell_code, supp_shortname, supp_name,
						supp_address, supp_postcode, supp_sort, supp_tel,
						supp_fax, supp_bank, supp_iban, supp_storage_address,
						supp_storage_tel, Integer.parseInt(oper_id));

				if ("添加".equals(str)) {
					ss.add(svo);
					dialog.reset(null);
					dialog.getSupp_id().setText("" + ss.findMaxID());
				} else if ("查询".equals(str)) {
					ss.find(svo);
				} else if ("删除".equals(str)) {
					ss.del(svo);
					dialog.reset(null);
					dialog.getSupp_id().setEditable(true);
					dialog.editable(false);
				} else if ("修改".equals(str)) {
					ss.modify(svo);
					dialog.reset(null);
					dialog.getSupp_id().setEditable(true);
					dialog.editable(false);
				}
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的数据!");
			}
		}
		ss.close();
	}

}
