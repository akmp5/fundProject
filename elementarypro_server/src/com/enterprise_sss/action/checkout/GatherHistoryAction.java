package com.enterprise_sss.action.checkout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.JTable;

import com.enterprise_sss.control.CheckoutServer;
import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.view.panel.checkout.GatherHistory;

public class GatherHistoryAction implements ActionListener {
	private GatherHistory gh;
	private CheckoutServer cs=new CheckoutServer();
	
	public GatherHistoryAction(GatherHistory gh){
		this.gh=gh;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("导出".equals(command)){
			//导出当前table1的数据
			System.out.println(cs.getGatherTitle());
			System.out.println(cs.getGather());
			new ExportToExcel(gh,"历史进销存汇总",cs.getGatherTitle(),cs.getGather());
		
		}else if("打印".equals(command)){
//			打印当前table1的数据
			try {
				gh.getTable().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}	
		
		}
	}

}
