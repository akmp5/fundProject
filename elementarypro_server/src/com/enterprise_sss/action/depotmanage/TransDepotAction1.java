package com.enterprise_sss.action.depotmanage;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.enterprise_sss.dao.depotmanagedao.DepotManageDao;



public class TransDepotAction1  {
	
	Vector rowData = new Vector();
	boolean flag = false;
	boolean flag1 = false;
	boolean flag2 = false;
	
	public TransDepotAction1(Vector rowData) {
		this.rowData = rowData;
	}

	public void transDepot(){					
				if(new DepotManageDao(rowData).checkRowData()){
					flag = new DepotManageDao(rowData).InsertTransBill();
					
					if(new DepotManageDao(rowData).checkStock()){
						flag1 =new DepotManageDao(rowData).UpdateTransBill();
					}
					
					else flag2 = new DepotManageDao(rowData).InsertStock();
				}
			
			if (flag == true&&(flag1 == true||flag2 == true)){
				JOptionPane.showMessageDialog(new JFrame(), "调货成功！");
			}		
	}		

}
