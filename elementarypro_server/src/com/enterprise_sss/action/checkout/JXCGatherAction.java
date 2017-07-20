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
		
		if("����".equals(command)){
			//������ǰtable1������
			new ExportToExcel(jg,"���������",cs.getGatherTitle(),cs.getLastGather());
		
		}else if("��ӡ".equals(command)){
//			��ӡ��ǰtable1������
			try {
				jg.getTable().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}	
			
		}
	}

}
