package com.enterprise_sss.action.checkout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.CheckoutServer;
import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.view.dialog.checkout.Checkout;
import com.enterprise_sss.view.panel.checkout.CheckoutOper;
import com.enterprise_sss.view.panel.checkout.GatherHistory;
import com.enterprise_sss.view.panel.checkout.JXCGather;

public class CheckoutOperAction implements ActionListener {
	private CheckoutOper co;
	private CheckoutServer cs=new CheckoutServer();
	private Vector checkoutData=null;
	private Vector lastGather=cs.getLastGather();
	
	public CheckoutOperAction(CheckoutOper co){
		this.co=co;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("模拟结账".equals(command)){
					if(cs.getMaxDate()!=""){
						checkoutData=cs.getCheckout(cs.getMinDate(),cs.getMaxDate());
						((DefaultTableModel)co.getTable().getModel()).setDataVector(checkoutData, cs.getImitateTitle());
						co.getTable().revalidate();
						co.getFormal().setEnabled(true);
						co.getExport().setEnabled(true);
						co.getPrint().setEnabled(true);
				}else{
					JOptionPane.showMessageDialog(co, "请先设定日期！");
				}
		}else if("正式结账".equals(command)){
			if(cs.formalCheckout(lastGather,checkoutData))
				JOptionPane.showMessageDialog(co, "操作成功！");
				co.getFormal().setEnabled(false);
				((DefaultTableModel) co.getCh().getJg().getTable().getModel()).setDataVector(cs.getLastGather(),cs.getGatherTitle());
				((DefaultTableModel)co.getCh().getJh().getTable().getModel()).setDataVector(cs.getGather(),cs.getGatherTitle());
		}else if("打印".equals(command)) {
//			打印当前table1的数据
			try {
				co.getTable().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}else if("导出".equals(command)) {
//			导出当前table1的数据
			new ExportToExcel(co,"模拟结帐",cs.getImitateTitle(),checkoutData);
		} 
	}

}
