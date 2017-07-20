package com.enterprise_sss.action;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.view.panel.PurchaseFindPanel;

public class PurchaseFindPanelAction implements ItemListener {

	private PurchaseFindPanel panel;
	
	public PurchaseFindPanelAction(PurchaseFindPanel panel){
		this.panel = panel;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			DefaultTableModel mode = (DefaultTableModel) panel.table.getModel();
			String str = (String) e.getItem();
			Vector title = new Vector();
			Vector data = new Vector();
		}
	}

}
