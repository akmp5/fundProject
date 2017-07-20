package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.view.dialog.FreeBill;

public class FreeBillAction implements ActionListener {
	private FreeBill fb;
	private RetailServer rs=new RetailServer();//零售服务类
	private int nowRow=-1;//选定行索引(第一行索引为0)
	
	public FreeBillAction(FreeBill fb){
		this.fb=fb;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		nowRow=fb.getTable().getSelectedRow();//得到选定行
		if("解挂".equals(command)){
			/*
			 *1,将对应的挂单明细退回回执单
			 *2,移除选择的挂单和对应的挂单明细并返回
			 */
			
			if(nowRow>=0){
//				Vector data=(Vector)fb.getRf().getHangItems().get(nowRow);
			fb.getRf().setData((Vector)fb.getRf().getHangItems().get(nowRow));
			
			fb.getRf().getHangBill().remove(nowRow);
			fb.getRf().getHangItems().remove(nowRow);
			fb.dispose();
			}
			
		}else if("删除".equals(command)){
			/*
			 *移除选择的挂单和对新显示应的挂单明细并重
			 */
			if(nowRow>=0){
				fb.getRf().getHangBill().remove(nowRow);
				fb.getRf().getHangItems().remove(nowRow);
				((DefaultTableModel)fb.getTable().getModel()).setDataVector(fb.getRf().getHangBill(), rs.getHangBillTitle());
				fb.getTable().revalidate();
			}
		}else if("返回".equals(command)){
			
			fb.dispose();
		}
	}

}
