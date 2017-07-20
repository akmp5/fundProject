package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.socket.Receive;
import com.enterprise_sss.socket.Send;
import com.enterprise_sss.view.dialog.MoveApply;

public class MoveApplyAction implements ActionListener {
	private MoveApply ma;
	private int nowRow=-1;//当前选择行的下标
	
	public MoveApplyAction(MoveApply ma){
		this.ma=ma;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if("确定".equals(command)){
			nowRow=ma.getTable().getSelectedRow();//得到当前选择行的下标
			if(nowRow>=0 & ma.getNum().getText().matches("[0-9]+")){
					Vector apply=new Vector();
					apply.add(ma.getTable().getValueAt(nowRow, 0));//货物编号
					apply.add(ma.getNum().getText());//数量
					apply.add(new Date(System.currentTimeMillis()));//日期
					apply.add(ma.getRf().getClient().getText());//申请人
					String desc=ma.getDesc().getText();
					if(desc.length()==0)
						desc="无";
					apply.add(desc);//申请说明
					new Send(apply);//向服务器端发送申请
					if(new Receive().getData()!=null){
						JOptionPane.showMessageDialog(ma, "申请成功！");
					}else
						JOptionPane.showMessageDialog(ma, "申请失败！");
			}else
					JOptionPane.showMessageDialog(ma, "申请无效！");
		}else if("返回".equals(command)){
			ma.dispose();
		}
	}

}
