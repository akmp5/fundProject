package com.enterprise_sss.action.checkout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.JTable;

import com.enterprise_sss.control.CheckoutServer;
import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.view.panel.checkout.JXCGather;

public class JXCGatherAction implements ActionListener {
	private JXCGather jg;
	private CheckoutServer cs=new CheckoutServer();
	public JXCGatherAction(JXCGather jg){
		this.jg=jg;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("导出".equals(command)){
			//导出当前table1的数据
			new ExportToExcel(jg,"进销存汇总",cs.getGatherTitle(),cs.getLastGather());
		
		}else if("打印".equals(command)){
//			打印当前table1的数据
			try {
				jg.getTable().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}	
			
		}
	}

}
