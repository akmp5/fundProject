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
			JOptionPane.showMessageDialog(new JFrame(), "��Ʒ��Ų���Ϊ��!");
			return false;
		}
		// else if(panel.tf2.getText().toString().trim().isEmpty()){
		else if (panel.tf2.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "�ֿ��Ų���Ϊ��!");
			return false;
		}
		// else if(panel.tf3.getText().toString().trim().isEmpty()){
		else if (panel.tf3.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "������������Ϊ��!");
			return false;
		}
		// else if(panel.tf4.getText().toString().trim().isEmpty()){
		else if (panel.tf4.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "�������Ϊ��!");
			return false;
		}
		// else if(panel.tf5.getSelectedItem().toString().trim().isEmpty()){
		else if (panel.tf5.getSelectedItem().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "�Ƶ����ڲ���Ϊ��!");
			return false;
		}
		// else if(panel.tf6.getText().toString().trim().isEmpty()){
		else if (panel.tf6.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "�����˲���Ϊ��!");
			return false;
		}
		// else if(panel.tf7.getText().toString().trim().isEmpty()){
		else if (panel.tf7.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "�Ƶ��˲���Ϊ��!");
			return false;
		}

		else
			return true;

	}
}
