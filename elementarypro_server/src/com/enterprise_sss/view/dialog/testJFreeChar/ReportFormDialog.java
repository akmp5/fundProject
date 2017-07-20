package com.enterprise_sss.view.dialog.testJFreeChar;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import com.enterprise_sss.action.ReportFormDialogAction;
import com.enterprise_sss.control.ReportFormDaoServer;
import com.enterprise_sss.util.CommonComponent;

public class ReportFormDialog extends JPanel {
	
	private  JPanel charPanel;
	private  JPanel panel;
	private Vector data;
	private Vector comboBoxData;
	private String title;
	private int type;
	private JComboBox comboBox;
	
	public ReportFormDialog(){
		init();
	}
	
	public ReportFormDialog(int type, String title, Vector comboBoxData,Vector data){
		this.type = type;
		this.comboBoxData = comboBoxData;
		this.data = data;
		this.title = title;
		init();
	}
	
	public ReportFormDialog(String title,Vector data){
		this.data = data;
		this.title = title;
		init();
	}
	
	public static void main(String[] args) {
		new ReportFormDialog();
	}
	
	public void init(){
		this.setTitle(title);
		
		panel = new JPanel(new BorderLayout());
		if (type == 3) {
			panel.add(buildNorthPanel(),BorderLayout.NORTH);
		}
		panel.add(addButton(), BorderLayout.SOUTH);
		panel.add(addCharPanel());
		this.add(panel);
	
	}
	
	public JPanel buildNorthPanel(){
		JPanel panel = new JPanel();
		String[] items = new String[comboBoxData.size()];
		for (int i = 0; i < comboBoxData.size(); i++) {
			items[i] = comboBoxData.get(i).toString();
		}
		comboBox = CommonComponent.buildComboBox(items, null, null, null, null, null, false, true);
		panel.add(new JLabel("²Ö¿âÃû³Æ:"));
		panel.add(comboBox);
		comboBox.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				String str = e.getItem().toString();
				ChartPanel panel = null;
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ReportFormDaoServer rs = new ReportFormDaoServer();
					data = rs.find(3, str);
					rs.close();
					charPanel.removeAll();
					panel = new ChartPanel(new BarChart().getBarChart(title,data));
					charPanel.add(panel);
					updateUI();
				}
				
			}
			
		});
		return panel;
	}
	
	 public JPanel addButton(){
		    JPanel panel2=new JPanel();
		    panel2.setSize(100, 50);
	    	JButton btn1=new JButton("±ý×´Í¼");
	    	JButton btn2=new JButton("ÖùÐÎÍ¼");
	    	btn1.addActionListener(new ReportFormDialogAction(this));
	    	btn2.addActionListener(new ReportFormDialogAction(this));
	    	panel2.add(btn1);
	    	panel2.add(btn2);
	    	return panel2;
	    }
	 public JPanel addCharPanel(){
		 charPanel=new JPanel();
		 ChartPanel cPanel=new ChartPanel(new IndividualAnalysChar().getPieChart(title,data));//±ý×´Í¼
		 charPanel.add(cPanel);
		 return charPanel;
	 }

	public JPanel getCharPanel() {
		return charPanel;
	}
	public void setCharPanel(JPanel charPanel) {
		this.charPanel = charPanel;
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Vector getData() {
		return data;
	}

	public void setData(Vector data) {
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

