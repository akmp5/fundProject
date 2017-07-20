package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.DepotServer;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.dialog.basic_data.DepotDataDialog;
import com.enterprise_sss.vo.DepotVO;

public class DepotDataDialogAction implements ActionListener {

	private DepotDataDialog dialog;
	
	public DepotDataDialogAction(DepotDataDialog dialog){
		this.dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		DepotServer ds = new DepotServer();
		if ("取消".equals(str)) {
			dialog.dispose();
		} else {
			String depo_id = dialog.getDepo_id().getText().trim();
			String depo_name = dialog.getDepo_name().getText()
					.trim();
			String depo_sort = dialog.getDepo_sort().getText().trim();
			String depo_desc = dialog.getDepo_desc().getText().trim();
			if (MethodUtil.isInt(depo_id) && MethodUtil.isInt(depo_sort)) {

					DepotVO dvo = new DepotVO(Integer.parseInt(depo_id),
							depo_name, Integer.parseInt(depo_sort),
							depo_desc);

					if ("添加".equals(str)) {
						ds.add(dvo);
						dialog.reset(null);
						dialog.getDepo_id().setText("" + ds.findMaxID());
					} else if ("查询".equals(str)) {
						ds.find(dvo);
					} else if ("删除".equals(str)) {
						ds.del(dvo);
						dialog.reset(null);
						dialog.getDepo_id().setEditable(true);
						dialog.editable(false);
					} else if ("修改".equals(str)) {
						ds.modify(dvo);
						dialog.reset(null);
						dialog.getDepo_id().setEditable(true);
						dialog.editable(false);
					}
				
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的数据!");
			}
		}
		ds.close();
	}

}
