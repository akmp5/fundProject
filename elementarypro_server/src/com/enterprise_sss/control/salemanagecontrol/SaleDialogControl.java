package com.enterprise_sss.control.salemanagecontrol;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.enterprise_sss.dao.salemanagedao.SaleDialogDao;

public class SaleDialogControl {
	Vector rowData;
	Vector textData;
	JTable table;
	Vector rowData1;
	
	public SaleDialogControl(){
		
	}
	
	
	public SaleDialogControl(Vector rowData,Vector textData,JTable table){
		this.rowData = rowData;
		this.textData = textData;
		this.table = table;
	}
	
	public boolean formateCheck(){
		if(textData.get(0).toString().trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "客户编号不能为空!");
			return false;
		}
		else if(textData.get(1).toString().trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "销售日期不能为空!");			
			return false;
		}
		else if(textData.get(2).toString().trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "业务员编号不能为空!");			
			return false;
		}
		else if(textData.get(3).toString().trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "制表人不能为空!");			
			return false;
		}
		else if(textData.get(4).toString().trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "保管员不能为空!");			
			return false;
		}
		else if(textData.get(5).toString().trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "销售订单编号不能为空!");			
			return false;
		}
		
		else if(rowData.get(0)==null||rowData.get(1).toString().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "商品编号不能为空!");			
			return false;
		}
		else if(rowData.get(1)==null||rowData.get(2).toString().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "销售数量不能为空!");			
			return false;
		}	
		else if(rowData.get(2)==null||rowData.get(2).toString().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "销售价格不能为空!");			
			return false;
		}
		else if(rowData.get(3)==null||rowData.get(2).toString().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "库编号不能为空!");			
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
		boolean flag2,flag3 = false;
		boolean checkflag = false;
		
		int i;
		for(i =0;i<table.getRowCount();i++){			
			for(int j =0;j<table.getColumnCount();j++){
				if(rowData.get(j+i*table.getColumnCount())==null||rowData.get(j+i*table.getColumnCount()).toString().isEmpty()){
					flag = false;
				}				
			}
			if(flag == true){			
				if(flag1 == true){
					runflag = new SaleDialogDao(textData).updateTextData();
					flag1 = false;
				}
				if(runflag == true){
					rowData1 = new Vector();
					for(int j=0;j<table.getColumnCount();j++){
						rowData1.add(rowData.get(j+i*table.getColumnCount()));
					}		
					if(checkflag =new SaleDialogDao(rowData1).checkRowData()){
					
						flag2 = new SaleDialogDao(rowData1).updateRowData();
						flag3 = new SaleDialogDao(rowData1).updateStock();						
						if(flag2 == true && flag3 == true){
							runflag1 = true;
						}
					}
				}
			}
			flag=true;
		}
		if (runflag == true&& runflag1 == true){
			JOptionPane.showMessageDialog(new JFrame(), "添加成功！");
		}
	}
}
