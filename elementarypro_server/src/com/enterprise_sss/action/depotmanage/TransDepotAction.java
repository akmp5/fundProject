package com.enterprise_sss.action.depotmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.enterprise_sss.control.depotmanagecontrol.TransDepotControl;
import com.enterprise_sss.dao.depotmanagedao.DepotManageDao;
import com.enterprise_sss.view.panel.depotmanage.TransDepotBasePanel;


public class TransDepotAction implements ActionListener {
	
	public TransDepotBasePanel panel;
	
	public TransDepotAction(TransDepotBasePanel panel) {
		this.panel = panel;
	}

	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		boolean flag = false;
		boolean flag1 = false;
		boolean flag2 = false;
		
		if(str.equalsIgnoreCase("确认调货")){
			if(new TransDepotControl(panel).formateCheck()){
				if(new DepotManageDao(panel).checkRowData()){
					flag = new DepotManageDao(panel).InsertTransBill();
					
					if(new DepotManageDao(panel).checkStock()){
						flag1 =new DepotManageDao(panel).UpdateTransBill();
					}
					
					else flag2 = new DepotManageDao(panel).InsertStock();
				}
			}
			if (flag == true&&(flag1 == true||flag2 == true)){
				JOptionPane.showMessageDialog(new JFrame(), "调货成功！");
			}
		}
		
	}

	public Vector getRowData() {
		
		return panel.rowData;
	}

	public void setRowData(Vector rowData) {
		panel.rowData = rowData;
	}
	
	
	

}
