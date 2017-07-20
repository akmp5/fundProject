package com.enterprise_sss.action.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.enterprise_sss.control.salemanagecontrol.OrderSaleDialogControl;
import com.enterprise_sss.control.salemanagecontrol.SaleBackControl;
import com.enterprise_sss.control.salemanagecontrol.SaleDialogControl;
import com.enterprise_sss.view.dialog.salemanage.OrderSaleDialog;
import com.enterprise_sss.view.dialog.salemanage.SaleBackDialog;
import com.enterprise_sss.view.dialog.salemanage.SaleDialog;

public class DialogAction implements ActionListener{
	
	OrderSaleDialog dialog = new OrderSaleDialog();
	SaleBackDialog dialog1 = new SaleBackDialog();
	SaleDialog dialog2 = new SaleDialog();
	public Vector textData;
	public Vector rowData;
	private TableModel model;
	

	
	public DialogAction(OrderSaleDialog dialog){
		this.dialog = dialog;
	}
		
	public DialogAction(SaleBackDialog dialog1){
		this.dialog = dialog1;
		this.dialog2 = dialog1;
		this.dialog1 = dialog1;
	}
	
	public DialogAction(SaleDialog dialog2){
		this.dialog2 = dialog2;
		this.dialog = this.dialog2;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		//给提交按钮添加事件
		if (str.equalsIgnoreCase("提交")){
			
			//基于同一个按钮给销售订单增加事件
			if(dialog.getTitle().equalsIgnoreCase("销售订单")){
				JFrame frame=new JFrame();
				int i = JOptionPane.showConfirmDialog(frame, "确定提交信息？", "提交确认", JOptionPane.OK_CANCEL_OPTION);
				if(i==JOptionPane.OK_OPTION){
//					System.out.println(dialog.tf1.getText());
					textData = new Vector(6);
					textData.add(dialog.tf1.getText());
					try {
						textData.add(new java.sql.Date(dialog.tf2.getSelectedDate().getTime()));
						textData.add(new java.sql.Date(dialog.tf3.getSelectedDate().getTime()));
						textData.add(new java.sql.Date(dialog.tf4.getSelectedDate().getTime()));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
					textData.add(dialog.tf5.getText());
					textData.add(dialog.tf6.getText());

					rowData = new Vector(dialog.table.getColumnCount()*dialog.table.getRowCount());
					model = dialog.table.getModel();
					for (int n=0;n<dialog.table.getRowCount();n++){
						for(int m =0;m<dialog.table.getColumnCount();m++)
							rowData.add(model.getValueAt(n, m));
					}
//					System.out.println(rowData.get(0));
//					System.out.println(rowData.size());
					OrderSaleDialogControl osdc = new OrderSaleDialogControl(rowData,textData,dialog.table);
					if(osdc.formateCheck()){
						osdc.rowDataCheck();
						
					}
					
				}
				else{
					frame.dispose();
				}
			}
			
			//基于同一个按钮给销售单添加事件
			else if(dialog2.getTitle().equalsIgnoreCase("销售单")){
				JFrame frame=new JFrame();
				int i = JOptionPane.showConfirmDialog(frame, "确定提交信息？", "提交确认", JOptionPane.OK_CANCEL_OPTION);
				if(i==JOptionPane.OK_OPTION){
//					System.out.println(dialog.tf1.getText());
					textData = new Vector(6);
					textData.add(dialog2.tf1.getText());
					try {
						textData.add(new java.sql.Date(dialog2.tf2.getSelectedDate().getTime()));

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					textData.add(dialog2.tf3.getText());
					textData.add(dialog2.tf4.getText());
					textData.add(dialog2.tf5.getText());
					textData.add(dialog2.tf6.getText());

					rowData = new Vector(dialog2.table.getColumnCount()*dialog2.table.getRowCount());
					model = dialog2.table.getModel();
					for (int n=0;n<dialog2.table.getRowCount();n++){
						for(int m =0;m<dialog2.table.getColumnCount();m++)
							rowData.add(model.getValueAt(n, m));
					}
//					System.out.println(rowData.get(0));
//					System.out.println(rowData.size());
					SaleDialogControl sdc = new SaleDialogControl(rowData,textData,dialog2.table);
					if(sdc.formateCheck()){
						sdc.rowDataCheck();
						
					}
					
				}
				else{
					frame.dispose();
				}
			}
			
			//基于同一个按钮给退货单添加事件
			else if(dialog1.getTitle().equalsIgnoreCase("退货单")){
				JFrame frame=new JFrame();
				int i = JOptionPane.showConfirmDialog(frame, "确定提交信息？", "提交确认", JOptionPane.OK_CANCEL_OPTION);
				if(i==JOptionPane.OK_OPTION){
//					System.out.println(dialog.tf1.getText());
					textData = new Vector(5);
					textData.add(dialog1.tf1.getText());
					textData.add(dialog1.tf2.getText());
					textData.add(dialog1.tf3.getText());
					textData.add(dialog1.tf4.getText());
					textData.add(dialog1.tf5.getText());

					SaleBackControl sbc = new SaleBackControl(textData);
					if(sbc.formateCheck()){
						sbc.rowDataCheck();						
				    }				
				}				
			}
			
		}
		
		//给增加行按钮添加事件
		if(str.equalsIgnoreCase("增加行")){
			DefaultTableModel model = (DefaultTableModel)dialog.table.getModel();
			model.addRow(new Vector());
		}
		
		//给删除行按钮添加事件
		if(str.equalsIgnoreCase("删除行")){
			DefaultTableModel model = (DefaultTableModel)dialog.table.getModel();
			if(dialog.table.getRowCount()>1 && dialog.table.getRowCount()>dialog.table.getSelectedRowCount()){
				int[] rowsNum;int[] flag = new int[1];
				rowsNum = dialog.table.getSelectedRows();
				flag[0]=-1;
				flag[0]=dialog.table.getSelectedRow();
				if(flag[0]!= -1){
					for(int j=rowsNum.length;j>0;j--)
						model.removeRow(rowsNum[j-1]);
				}else
					{model.removeRow(dialog.table.getRowCount()-1);
					}
			}
		}
		
		//给退出按钮添加事件
		if(str.equalsIgnoreCase("退出")){
			dialog.dispose();
			
		}
	}

}
