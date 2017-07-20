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
		
		if("����".equals(command)){
			//������ǰtable1������
			System.out.println(cs.getGatherTitle());
			System.out.println(cs.getGather());
			new ExportToExcel(gh,"��ʷ���������",cs.getGatherTitle(),cs.getGather());
		
		}else if("��ӡ".equals(command)){
//			��ӡ��ǰtable1������
			try {
				gh.getTable().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}	
		
		}
	}

}
