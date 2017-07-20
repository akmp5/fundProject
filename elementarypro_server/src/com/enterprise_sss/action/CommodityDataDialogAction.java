package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.CommodityServer;
import com.enterprise_sss.util.MethodUtil;

import com.enterprise_sss.view.dialog.basic_data.CommodityDataDialog;
import com.enterprise_sss.vo.CommodityVO;

public class CommodityDataDialogAction implements ActionListener {

	private CommodityDataDialog dialog;

	public CommodityDataDialogAction(CommodityDataDialog dialog) {
		this.dialog = dialog;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		CommodityServer cs = new CommodityServer();
		if ("取消".equals(str)) {
			dialog.dispose();
		} else {
			String id = dialog.getComm_id().getText().trim();
			String bar_code = dialog.getComm_bar_code().getText().trim();
			String name = dialog.getComm_name().getText().trim();
			String spell_code = dialog.getComm_spell_code().getText().trim();
			String standard = dialog.getComm_standard().getText().trim();
			String unit = dialog.getComm_unit().getText().trim();
			String producing_area = dialog.getComm_producing_area().getText()
					.trim();
			String sort = dialog.getComm_sort().getText().trim();
			String price = dialog.getPurchase_price().getText().trim();
			String sale_price1 = dialog.getSale_price1().getText().trim();
			String sale_price2 = dialog.getSale_price2().getText().trim();
			String lowest_sale_price = dialog.getLowest_sale_price().getText()
					.trim();
			if (MethodUtil.isInt(id) && MethodUtil.isDouble(price)
					&& MethodUtil.isDouble(sale_price1)
					&& MethodUtil.isDouble(sale_price2)
					&& MethodUtil.isDouble(lowest_sale_price)) {

				if (cs.validateDate(bar_code)) {
					CommodityVO cvo = new CommodityVO(Integer.parseInt(id),
							bar_code, name, spell_code, standard, unit,
							producing_area, sort, new Double(price),
							new Double(sale_price1), new Double(sale_price2),
							new Double(lowest_sale_price));

					if ("添加".equals(str)) {
						cs.add(cvo);
						dialog.reset();
						dialog.getComm_id().setText("" + cs.findMaxID());
					} else if ("查询".equals(str)) {
						cs.find(cvo);
					} else if ("删除".equals(str)) {
						cs.del(cvo);
						dialog.reset();
						dialog.getComm_id().setEditable(true);
						dialog.editable(false);
					} else if ("修改".equals(str)) {
						cs.modify(cvo);
						dialog.reset();
						dialog.getComm_id().setEditable(true);
						dialog.editable(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "条形码不能为空!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的数据!");
			}
		}
		cs.close();
	}

}
