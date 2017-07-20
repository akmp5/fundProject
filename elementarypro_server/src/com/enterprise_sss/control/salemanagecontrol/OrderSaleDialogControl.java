package com.enterprise_sss.control.salemanagecontrol;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.enterprise_sss.dao.salemanagedao.OrderSaleDialogDao;

public class OrderSaleDialogControl {
	Vector rowData;
	Vector textData;
	JTable table;
	Vector rowData1;
	
	public OrderSaleDialogControl(){
		
	}
	
	
	public OrderSaleDialogControl(Vector rowData,Vector textData,JTable table){
		this.rowData = rowData;
		this.textData = textData;
		this.table = table;
	}
	
	public boolean formateCheck(){
//		if(textData.get(0).toString().trim().isEmpty()){
		if(textData.get(0).toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "�ͻ���Ų���Ϊ��!");
			return false;
		}
//		else if(textData.get(1).toString().trim().isEmpty()){
		else if(textData.get(1).toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "���۶������ڲ���Ϊ��!");			
			return false;
		}
//		else if(textData.get(2).toString().trim().isEmpty()){
		else if(textData.get(2).toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "��Ч���ղ���Ϊ��!");			
			return false;
		}
//		else if(textData.get(3).toString().trim().isEmpty()){
		else if(textData.get(3).toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "��Чֹ�ղ���Ϊ��!");			
			return false;
		}
//		else if(textData.get(4).toString().trim().isEmpty()){
		else if(textData.get(4).toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "ҵ��Ա��Ų���Ϊ��!");			
			return false;
		}
//		else if(textData.get(5).toString().trim().isEmpty()){
		else if(textData.get(5).toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "�Ƶ��˲���Ϊ��!");			
			return false;
		}
		
//		else if(rowData.get(0)==null||rowData.get(0).toString().isEmpty()){
		else if(rowData.get(0) == null || rowData.get(0).toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "�����Ų���Ϊ��!");			
			return false;
		}
//		else if(rowData.get(1)==null||rowData.get(1).toString().isEmpty()){
		else if(rowData.get(1) == null || rowData.get(0).toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "������������Ϊ��!");			
			return false;
		}
//		else if(rowData.get(2)==null||rowData.get(2).toString().isEmpty()){
		else if(rowData.get(2) == null || rowData.get(0).toString().trim().length() != 0){
			JOptionPane.showMessageDialog(new JFrame(), "���ۼ۲���Ϊ��!");			
			return false;
		}	
		else
		return true;
		
	}
	
	public void rowDataCheck(){
		boolean flag = true;
		boolean flag1 =true;
		boolean runflag = false;
		boolean runflag1 = false;
		boolean flag2 = false;
		int i;
		for(i =0;i<table.getRowCount();i++){			
			for(int j =0;j<table.getColumnCount();j++){
				if(rowData.get(j+i*table.getColumnCount())==null||rowData.get(j+i*table.getColumnCount()).toString().isEmpty()){
					flag = false;
				}				
			}
			if(flag == true){
				if(flag1 == true){
					runflag = new OrderSaleDialogDao(textData).updateTextData();
					flag1 = false;
				}
				if(runflag == true){
					rowData1 = new Vector();
					for(int j=0;j<table.getColumnCount();j++){
						rowData1.add(rowData.get(j+i*table.getColumnCount()));
					}
					flag2 = new OrderSaleDialogDao(rowData1).updateRowData();
					if(flag2 == true){
						runflag1 = true;
					}
				}
			}
			flag=true;
		}
		if (runflag == true&& runflag1 == true){
			JOptionPane.showMessageDialog(new JFrame(), "��ӳɹ���");
		}
	}
}
	
	
	
