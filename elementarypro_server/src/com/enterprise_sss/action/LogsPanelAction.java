package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.enterprise_sss.view.panel.LogsPanel;

public class LogsPanelAction implements ActionListener {

	private LogsPanel panel;
	
	public LogsPanelAction(LogsPanel panel){
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		String s = panel.getType().getSelectedCheckbox().getLabel();
		String path = panel.getPath1();
		if ("����".equals(str)) {
			panel.setN(0);
		} else if ("��һ��".equals(str)) {
			panel.setN(panel.getN() - 1);
		} else if ("��һ��".equals(str)) {
			panel.setN(panel.getN() + 1);
		} else if ("δ��".equals(str)) {
			if (s.equals("������־")) {
				panel.setN(panel.getCount1() - 1);
			} else {
				panel.setN(panel.getCount2() - 1);
				path = panel.getPath2();
			}
		}
		
		panel.setValues(path);
	}

}
