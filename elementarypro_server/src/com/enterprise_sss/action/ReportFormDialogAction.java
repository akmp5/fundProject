package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import org.jfree.chart.ChartPanel;

import com.enterprise_sss.view.dialog.testJFreeChar.BarChart;
import com.enterprise_sss.view.dialog.testJFreeChar.IndividualAnalysChar;
import com.enterprise_sss.view.dialog.testJFreeChar.ReportFormDialog;

public class ReportFormDialogAction implements ActionListener {

	private ReportFormDialog dialog;
	
	public ReportFormDialogAction(ReportFormDialog dialog){
		this.dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		String title = dialog.getTitle();
		ChartPanel panel = null;
		Vector data = dialog.getData();
		if ("±ý×´Í¼".equals(str)) {
			dialog.getCharPanel().removeAll();
			panel = new ChartPanel(new IndividualAnalysChar().getPieChart(title,data));
			dialog.getCharPanel().add(panel);
		} else if ("ÖùÐÎÍ¼".equals(str)) {
			dialog.getCharPanel().removeAll();
			panel = new ChartPanel(new BarChart().getBarChart(title,data));
			dialog.getCharPanel().add(panel);
		}
		dialog.updateUI();
	}

}
