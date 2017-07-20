package com.enterprise_sss.action.salemanage;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.enterprise_sss.dao.salemanagedao.SaleManageDao;
import com.enterprise_sss.view.panel.salemanage.SaleQueryBasePanel;

public class SaleQueryAction {

	JRadioButton[] rb = new JRadioButton[6];

	Vector rowData = null;

	String string;

	public SaleQueryAction(SaleQueryBasePanel panle) {
		this.rb = panle.rb;
		this.string = panle.string;
		receiveData();
	}

	public void receiveData() {
		if (rb[0].isSelected()) {
			rowData = new SaleManageDao().selectAll();
			// System.out.println(rowData);

		}
		if (rb[1].isSelected()) {
			// if(!string.trim().isEmpty())
			if (string.trim().length() != 0)
				rowData = new SaleManageDao(string.trim()).selectClientId();
		}
		if (rb[2].isSelected()) {
			// if(!string.trim().isEmpty())
			if (string.trim().length() != 0)
				rowData = new SaleManageDao(string.trim()).selectSaleDate();
		}
		if (rb[3].isSelected()) {
			// if (!string.trim().isEmpty())
			if (string.trim().length() != 0)
				rowData = new SaleManageDao(string.trim()).selectCommId();
		}
		if (rb[4].isSelected()) {
			// if (!string.trim().isEmpty())
			if (string.trim().length() != 0)
				rowData = new SaleManageDao(string.trim()).selectCommSpell();
		}
		if (rb[5].isSelected()) {
			// if (!string.trim().isEmpty())
			if (string.trim().length() != 0)
				rowData = new SaleManageDao(string.trim()).selectOprId();
		}
	}

	public Vector getRowData() {
		return rowData;
	}

	public void setRowData(Vector rowData) {
		this.rowData = rowData;
	}

}
