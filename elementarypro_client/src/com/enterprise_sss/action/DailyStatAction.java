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
		if("导出".equals(command)){
			new ExportToExcel(ps,"日销售统计",ps.getStatData(),new RetailServer().getStatTitle());
		}else if("打印".equals(command)){
			try {
				ps.getTable().print();
			} catch (PrinterException e1) {
				JOptionPane.showMessageDialog(ps, "打印失败！");
			}
		}else if("返回".equals(command)){
			ps.dispose();
		}
	}

}
