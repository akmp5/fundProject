package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.CommodityServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.panel.maintenance.CommodityMaintenancePanel;
import com.enterprise_sss.vo.CommodityVO;

public class CommodityMaintenancePanelAction implements ActionListener {

	private CommodityMaintenancePanel panel;
	
	private CommodityServer cs = new CommodityServer();
	
	public CommodityMaintenancePanelAction(CommodityMaintenancePanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		String id = panel.getComm_id().getText().trim();
		String bar_code = panel.getComm_bar_code().getText().trim();
		String name = panel.getComm_name().getText().trim();
		String spell_code = panel.getComm_spell_code().getText().trim();
		String standard = panel.getComm_standard().getText().trim();
		String unit = panel.getComm_unit().getText().trim();
		String producing_area = panel.getComm_producing_area().getText()
				.trim();
		String sort = panel.getComm_sort().getText().trim();
		String price = panel.getPurchase_price().getText().trim();
		String sale_price1 = panel.getSale_price1().getText().trim();
		String sale_price2 = panel.getSale_price2().getText().trim();
		String lowest_sale_price = panel.getLowest_sale_price().getText()
				.trim();
		if ("查询".equals(str)) {
			findAll();
		} else if ("重置".equals(str)) {
			panel.reset();
		} else {
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
					}else if ("删除".equals(str)) {
						cs.del(cvo);
					} else if ("修改".equals(str)) {
						cs.modify(cvo);
					}
					panel.reset();
					findAll();
				} else {
					JOptionPane.showMessageDialog(null, "条形码不能为空!");
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
