package com.enterprise_sss.control.depotmanagecontrol;

import java.text.ParseException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.enterprise_sss.view.panel.depotmanage.TransDepotBasePanel;

public class TransDepotControl {
	public Vector textData = new Vector();
	public TransDepotBasePanel panel;
	
	public TransDepotControl(TransDepotBasePanel panel) {
		this.panel = panel;
	}

	public boolean formateCheck(){	
	
		textData.add(panel.tf1.getText());
		textData.add(panel.tf2.getText());
		textData.add(panel.tf3.getText());
		try {
			textData.add(new java.sql.Date(panel.tf4.getSelectedDate().getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textData.add(panel.tf5.getText());
		textData.add(panel.tf6.getText());
		
//		if(textData.get(0).toString().trim().isEmpty()){
		if (textData.get(0).toString().trim().length() != 0) {
			JOptionPane.showMessageDialog(new JFrame(), "门店编号不能为空!");
			return false;
		}		
//		if(textData.get(1).toString().trim().isEmpty()){
		if (textData.get(1).toString().trim().length() != 0) {
			JOptionPane.showMessageDialog(new JFrame(), "商品编号不能为空!");
			return false;
		}		
//		if(textData.get(2).toString().trim().isEmpty()){
		if (textData.get(2).toString().trim().length() != 0) {
			JOptionPane.showMessageDialog(new JFrame(), "调货数量不能为空!");
			return false;
		}		
//		if(textData.get(3).toString().trim().isEmpty()){
		if (textData.get(3).toString().trim().length() != 0) {
			JOptionPane.showMessageDialog(new JFrame(), "日期不能为空!");
			return false;
		}		
//		if(textData.get(4).toString().trim().isEmpty()){
		if (textData.get(4).toString().trim().length() != 0) {
			JOptionPane.showMessageDialog(new JFrame(), "调拨状态不能为空!");
			return false;
		}		
//		if(textData.get(5).toString().trim().isEmpty()){
		if (textData.get(5).toString().trim().length() != 0) {
			JOptionPane.showMessageDialog(new JFrame(), "制单人不能为空!");
			return false;
		}
		
		return true;
		
	}
}
