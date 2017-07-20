package com.enterprise_sss.action.depotmanage;

import java.util.Vector;

import javax.swing.JRadioButton;

import com.enterprise_sss.dao.depotmanagedao.DepotManageDao;
import com.enterprise_sss.dao.salemanagedao.SaleManageDao;
import com.enterprise_sss.view.panel.depotmanage.QueryDepotPanel;

public class DepotQueryAction {
	JRadioButton[] rb =new JRadioButton[6];	
	Vector rowData = null;
	String string;
	public DepotQueryAction(QueryDepotPanel panle){
		this.rb = panle.rb;
		this.string = panle.string;
		receiveData();
	}	
	
	public void receiveData() {
		if(rb[0].isSelected()){
			rowData = new DepotManageDao().selectAll();
//			System.out.println(rowData);

		}
		if(rb[1].isSelected()){
//			if(!string.trim().isEmpty())
			if (string.trim().length() != 0)
			rowData = new DepotManageDao(string.trim()).selectClientId();
		}
		if(rb[2].isSelected()){
//			if(!string.trim().isEmpty())
			if (string.trim().length() != 0)
			rowData = new DepotManageDao(string.trim()).selectCommId();
		}
		
	}

	public Vector getRowData() {
		return rowData;
	}

	public void setRowData(Vector rowData) {
		this.rowData = rowData;
	}
}
