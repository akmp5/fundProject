package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.view.dialog.DailyStat;

public class DailyStatAction implements ActionListener {
	private DailyStat ps;
	
	public DailyStatAction(DailyStat ps){
		this.ps=ps;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if("����".equals(command)){
			new ExportToExcel(ps,"������ͳ��",ps.getStatData(),new RetailServer().getStatTitle());
		}else if("��ӡ".equals(command)){
			try {
				ps.getTable().print();
			} catch (PrinterException e1) {
				JOptionPane.showMessageDialog(ps, "��ӡʧ�ܣ�");
			}
		}else if("����".equals(command)){
			ps.dispose();
		}
	}

}
