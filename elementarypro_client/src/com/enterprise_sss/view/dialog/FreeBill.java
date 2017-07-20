package com.enterprise_sss.view.dialog;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.enterprise_sss.action.FreeBillAction;
import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.view.frame.RetailFrame;

/**
 * 解挂Dialog
 * @author yiguo
 *
 */
public class FreeBill extends JDialog {
	private JTable table;//挂单表
	private JButton free,delete,back;//解挂，删除，返回
	private RetailFrame rf;//零售界面
	
	public FreeBill(RetailFrame rf){
		this.rf=rf;
		init();
	}

	private void init() {
		initDialog();
		initAction();
		this.setVisible(true);
	}

	private void initDialog() {
		this.setTitle("解挂");
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//不可由用户调整大小
		this.setModal(true);//只能操作当前窗口
		this.setLayout(new BorderLayout());
		this.add(getCenter(),BorderLayout.CENTER);
		this.add(getSouth(),BorderLayout.SOUTH);
		
	}
	
	/**
	 * 中心滚动面板
	 * @return
	 */
	private JScrollPane getCenter(){
		JScrollPane sp=new JScrollPane();
		sp.setViewportView(table=CommonComponent.buildTable(rf.getHangBill(), new RetailServer().getHangBillTitle()));
		return sp;
	}

	/**
	 * 南按钮面板
	 * @return
	 */
	private JPanel getSouth(){
		JPanel p=new JPanel();
		p.add(free=CommonComponent.buildButton("解挂", null, null, null, null, null, null, true));
		p.add(delete=CommonComponent.buildButton("删除", null, null, null, null, null, null, true));
		p.add(back=CommonComponent.buildButton("返回", null, null, null, null, null, null, true));
		return p;
	}
	
	/**
	 * 添加事件
	 */
	private void initAction() {
		FreeBillAction fba=new FreeBillAction(this);
		free.addActionListener(fba);
		delete.addActionListener(fba);
		back.addActionListener(fba);
	}
	
	public JTable getTable() {
		return table;
	}

	public RetailFrame getRf() {
		return rf;
	}
}
