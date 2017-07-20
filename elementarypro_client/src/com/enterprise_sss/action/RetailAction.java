package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.socket.Receive;
import com.enterprise_sss.socket.Send;
import com.enterprise_sss.view.dialog.DailyStat;
import com.enterprise_sss.view.dialog.FreeBill;
import com.enterprise_sss.view.dialog.MoveApply;
import com.enterprise_sss.view.dialog.MoveConfirm;
import com.enterprise_sss.view.dialog.Pay;
import com.enterprise_sss.view.dialog.Relief;
import com.enterprise_sss.view.frame.RetailFrame;

public class RetailAction implements ActionListener {
	private RetailFrame rf;
	private RetailServer rs=new RetailServer();//零售服务类
	private int index=0;//当前回执单记录数
	private int in=0;//指定编号的商品信息所在集合的下标
	private Vector row;//临时存放单条记录
	
	public RetailAction(RetailFrame rf){
		this.rf=rf;
	}
	
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if("确定".equals(command)){
			in=rs.getComm(rf.getComms(), rf.getCommId().getText());
			/*
			 * 当商品信息存在且数量有效时
			 * 往回执单添加记录并更新显示
			 */
			System.out.println(in);
			if(in>=0 & rs.isDouble(rf.getNum().getText())){
				System.out.println("aaa");
				row=new Vector();
				row.add(++index);//序号
				row.add(rf.getCommId().getText());//商品编号
				row.add(((Vector)rf.getComms().get(in)).get(2));//商品名称
				row.add(((Vector)rf.getComms().get(in)).get(5));//单位
				row.add(((Vector)rf.getComms().get(in)).get(9));//单价
				row.add(rf.getNum().getText());//数量
				row.add(Integer.parseInt(rf.getNum().getText())*Double.parseDouble
						(((Vector)rf.getComms().get(in)).get(9).toString()));//金额=数量*单价
				rf.getData().add(row);

			}
		}else if("班次结账".equals(command)){
			//结账Dialog
			new Relief(rf);
			
		}else if("每日统计".equals(command)){
			//统计Dialog
			if(rf.getSaleItems().size()>0){
				new DailyStat(rf);
			}else
				JOptionPane.showMessageDialog(rf, "无统计！");
			
		}else if("调货申请".equals(command)){
			//调货申请Dialog
			new MoveApply(rf);
			
		}else if("来货入库".equals(command)){
			//向服务器发送确认请求，并获得申请数据
			Vector client=new Vector();
			client.add(rf.getClient().getText());
			new Send(client);
			Vector applied=new Receive().getData();
			System.out.println(applied);
			if(applied.size()>=0){
				//来货入库Dialog
				new MoveConfirm(rf,applied);
			}else
				JOptionPane.showMessageDialog(rf, "暂无申请数据！");
		}else if("挂单".equals(command)){
			/*
			 * 当回执单不为空时，可挂单：
			 * 1 将回执单号添加到rf.getHangBill()集合
			 * 2 将回执单详细信息添加到rf.getHangItems()集合
			 * 3 清空回执单
			 */
			if(rf.getData().size()>0){
				Vector row=new Vector();
				row.add(rf.getRetailId().getText());//单号
				String names="";
				for(int i=0;i<rf.getData().size();i++){
					names=names+" "+((Vector)rf.getData().get(i)).get(2);
				}
				row.add(names);//商品名字符串
				rf.getHangBill().add(row);
				rf.getHangItems().add(rf.getData());
				rf.setData(new Vector());
				index=0;
				
				JOptionPane.showMessageDialog(rf, "挂单成功！");
			}
			
		}else if("解挂".equals(command)){
			/*
			 * 有挂单则跳转到解挂Dialog
			 * 否则提示无挂单
			 */
			if(rf.getHangBill().size()>0){
				
				//解挂Dialog
				new FreeBill(rf);
			}else
				JOptionPane.showMessageDialog(rf, "无挂单！");
			
		}else if("付款".equals(command)){
			//付款Dialog
			if(rf.getData().size()>0){
				new Pay(rf);
				if(rf.getData().size()<=0){
					index=0;
				}
			}else{
				JOptionPane.showMessageDialog(rf, "无单可付！");
			}
		}else if("全清".equals(command)){
			/*
			 * 清空回执列表
			 */
			rf.setData(new Vector());
			index=0;
			
		}else if("打印".equals(command)){
			if(rf.getTable1().getModel().getRowCount()>0){
				try {
					rf.getTable1().print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
			
		}else if("退出".equals(command)){
			int i=JOptionPane.showConfirmDialog(null, "是否关闭", "系统提示", JOptionPane.YES_NO_OPTION);
			if(i==JOptionPane.YES_OPTION){
				//将日销售信息提交服务器
				new Send(rf.getSaleBill());
				Vector data = rf.getSaleItems();
				for (int n = 0; n < data.size(); n++) {
					new Send((Vector)data.get(n));
				}
			System.exit(0);
			}
		}
		/*
		 * 更新回执单和总计金额rf.getAmount()的值
		 */
		((DefaultTableModel)rf.getTable1().getModel()).setDataVector(rf.getData(), rs.getReceiptTitle());
		rf.getTable1().revalidate();
		if(rf.getData().size()>0){
			
			Double money=0d;
			for(int i=0;i<rf.getData().size();i++)
				money=money+Double.parseDouble(((Vector)rf.getData().get(i)).get(6).toString());
			rf.getAmount().setText(money.toString());
		}else
			rf.getAmount().setText("0");
	}

}
