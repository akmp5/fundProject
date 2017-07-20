package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.ClientServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.dialog.basic_data.ClientDataDialog;
import com.enterprise_sss.vo.ClientVO;

public class ClientDataDialogAction implements ActionListener {

	private ClientDataDialog dialog;

	public ClientDataDialogAction(ClientDataDialog dialog) {
		this.dialog = dialog;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		ClientServer cs = new ClientServer();
		if ("取消".equals(str)) {
			dialog.dispose();
		} else {
			String clie_id = dialog.getClie_id().getText().trim();
			String clie_spell_code = dialog.getClie_spell_code().getText()
					.trim();
			String clie_shortname = dialog.getClie_shortname().getText().trim();
			String clie_name = dialog.getClie_name().getText().trim();
			String clie_linkman = dialog.getClie_linkman().getText().trim();
			String clie_address = dialog.getClie_address().getText().trim();
			String Clie_postcode = dialog.getClie_postcode().getText().trim();
			String Clie_tel = dialog.getClie_tel().getText().trim();
			String Clie_fax = dialog.getClie_fax().getText().trim();
			String Clie_bank = dialog.getClie_bank().getText().trim();
			String Clie_iban = dialog.getClie_iban().getText().trim();
			String Clie_sort = dialog.getClie_sort().getText().trim();
			String oper_id = dialog.getOper_id().getText().trim();
			String Clie_CreditLimt = dialog.getClie_CreditLimt().getText()
					.trim();
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
						dialog.reset(null);
						dialog.getClie_id().setText("" + cs.findMaxID());
					} else if ("查询".equals(str)) {
						cs.find(cvo);
					} else if ("删除".equals(str)) {
						cs.del(cvo);
						dialog.reset(null);
						dialog.getClie_id().setEditable(true);
						dialog.editable(false);
					} else if ("修改".equals(str)) {
						cs.modify(cvo);
						dialog.reset(null);
						dialog.getClie_id().setEditable(true);
						dialog.editable(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "联系人姓名不能为空!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的数据!");
			}
		}
		cs.close();
	}

}
