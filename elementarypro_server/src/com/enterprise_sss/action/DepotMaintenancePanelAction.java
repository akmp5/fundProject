package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.DepotServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.panel.maintenance.DepotMaintenancePanel;
import com.enterprise_sss.vo.DepotVO;

public class DepotMaintenancePanelAction implements ActionListener {

	private DepotMaintenancePanel panel;

	private DepotServer ds = new DepotServer();

	public DepotMaintenancePanelAction(DepotMaintenancePanel panel) {
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		String depo_id = panel.getDepo_id().getText().trim();
		String depo_name = panel.getDepo_name().getText().trim();
		String depo_sort = panel.getDepo_sort().getText().trim();
		String depo_desc = panel.getDepo_desc().getText().trim();

		if ("查询".equals(str)) {
			findAll();
		} else if ("重置".equals(str)) {
			panel.reset();
		} else {
			if (MethodUtil.isInt(depo_id) && MethodUtil.isInt(depo_sort)) {

				DepotVO dvo = new DepotVO(Integer.parseInt(depo_id), depo_name,
						Integer.parseInt(depo_sort), depo_desc);

				if ("添加".equals(str)) {
					ds.add(dvo);
				} else if ("删除".equals(str)) {
					ds.del(dvo);
				} else if ("修改".equals(str)) {
					ds.modify(dvo);
				}
				panel.reset();
				findAll();
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的数据!");
			}
		}
		ds.close();
	}

	public void findAll() {
		Vector data = ds.findAll();
		DefaultTableModel mode = (DefaultTableModel) panel.table.getModel();
		Vector title = new Vector();
		for (int i = 0; i < mode.getColumnCount(); i++) {
			title.add(mode.getColumnName(i));
		}
		mode.setDataVector(data, title);
	}

}
