package com.enterprise_sss.control.depotmanagecontrol;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.enterprise_sss.view.panel.depotmanage.ProfitLossPanel;
import com.enterprise_sss.view.panel.depotmanage.UperLowerPanel;

public class UpLowControl {
	
	UperLowerPanel panel = new UperLowerPanel();
	
	public UpLowControl(UperLowerPanel panel){
		this.panel = panel; 
	}

	public boolean checkTextData(){
		
//		if(panel.tf1.getText().toString().trim().isEmpty()){
		if(panel.tf1.getText().toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "�ֿ��Ų���Ϊ��!");
			return false;
		}
//		else if(panel.tf2.getText().toString().trim().isEmpty()){
		else if(panel.tf2.getText().toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "�����Ų���Ϊ��!");			
			return false;
		}
//		else if(panel.tf3.getText().toString().trim().isEmpty()){
		else if(panel.tf3.getText().toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "������������Ϊ��!");			
			return false;
		}
//		else if(panel.tf4.getText().toString().trim().isEmpty()){
		else if(panel.tf4.getText().toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "������������Ϊ��!");			
			return false;
		}
//		else if(panel.tf5.getText().toString().trim().isEmpty()){
		else if(panel.tf5.getText().toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "��Ѵ�������Ϊ��!");			
			return false;
		}
//		else if(panel.tf6.getText().toString().trim().isEmpty()){
		if(panel.tf1.getText().toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "��ע����Ϊ��!");			
			return false;
		}
		else if(Integer.parseInt(panel.tf3.getText())<=Integer.parseInt(panel.tf4.getText())){
			JOptionPane.showMessageDialog(new JFrame(), "�����������������������!");
			return false;
		}
		else if(Integer.parseInt(panel.tf4.getText())>=Integer.parseInt(panel.tf5.getText()) ||Integer.parseInt(panel.tf5.getText())>=Integer.parseInt(panel.tf3.getText())){
			JOptionPane.showMessageDialog(new JFrame(), "��ѿ���������������޺�����֮��!");
			return false;
		}
		
		else
		return true;
		
		
	}
}
