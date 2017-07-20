package com.enterprise_sss.control.salemanagecontrol;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.enterprise_sss.dao.salemanagedao.SaleBackDialogDao;
import com.enterprise_sss.dao.salemanagedao.SaleDialogDao;

public class SaleBackControl {
	Vector textData;
	public SaleBackControl(Vector textData) {
		this.textData = textData;
	}
	
	public boolean formateCheck(){
		if(textData.get(0).toString().trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "���۵���Ų���Ϊ��!");
			return false;
		}
		else if(textData.get(1).toString().trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "�����Ų���Ϊ��!");			
			return false;
		}
		else if(textData.get(2).toString().trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "�˻���������Ϊ��!");			
			return false;
		}
		else if(textData.get(3).toString().trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "���ۼ۲���Ϊ��!");			
			return false;
		}
		else if(textData.get(4).toString().trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "�˻��ֿ��Ų���Ϊ��!");			
			return false;
		}

		else
		return true;
		
	}
	
	public void rowDataCheck(){

		boolean runflag = false;
		boolean runflag1 = false;
		if(new SaleBackDialogDao(textData).checkTextData()==true){
					
			runflag = new SaleBackDialogDao(textData).updateTextData();
			runflag1 = new SaleBackDialogDao(textData).updateStock();
		}
		
		if (runflag == true&&runflag1 == true){
			JOptionPane.showMessageDialog(new JFrame(), "�˻��ɹ���");
		}
	}
}
