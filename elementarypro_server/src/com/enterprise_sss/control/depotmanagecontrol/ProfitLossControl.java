package com.enterprise_sss.control.depotmanagecontrol;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.enterprise_sss.view.panel.depotmanage.ProfitLossPanel;

public class ProfitLossControl {

	ProfitLossPanel panel = new ProfitLossPanel();

	public ProfitLossControl(ProfitLossPanel panel) {
		this.panel = panel;
	}

	public boolean checkTextData() {

		// if(panel.tf1.getText().toString().trim().isEmpty()){
		if (panel.tf1.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "商品编号不能为空!");
			return false;
		}
		// else if(panel.tf2.getText().toString().trim().isEmpty()){
		else if (panel.tf2.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "仓库编号不能为空!");
			return false;
		}
		// else if(panel.tf3.getText().toString().trim().isEmpty()){
		else if (panel.tf3.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "货物数量不能为空!");
			return false;
		}
		// else if(panel.tf4.getText().toString().trim().isEmpty()){
		else if (panel.tf4.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "货物金额不能为空!");
			return false;
		}
		// else if(panel.tf5.getSelectedItem().toString().trim().isEmpty()){
		else if (panel.tf5.getSelectedItem().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "制单日期不能为空!");
			return false;
		}
		// else if(panel.tf6.getText().toString().trim().isEmpty()){
		else if (panel.tf6.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "责任人不能为空!");
			return false;
		}
		// else if(panel.tf7.getText().toString().trim().isEmpty()){
		else if (panel.tf7.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "制单人不能为空!");
			return false;
		}

		else
			return true;

	}
}
