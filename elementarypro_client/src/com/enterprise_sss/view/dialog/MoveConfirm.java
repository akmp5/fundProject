package com.enterprise_sss.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.enterprise_sss.action.MoveConfirmAction;
import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.view.frame.RetailFrame;

public class MoveConfirm extends JDialog {
	private JTable table;//货物申请表（没到货记录）
	private JButton confirm,back;//确认，返回
	private RetailFrame rf;//零售界面
	private Vector data;//货物申请表数据
	
	public MoveConfirm(RetailFrame rf,Vector data){
		this.rf=rf;
		this.data=data;
		init();
	}

	private void init() {
		initDialog();
		initAction();
		this.setVisible(true);
	}

	private void initDialog() {
		this.setTitle("入货确认");
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
		sp.setViewportView(table=CommonComponent.buildTable(data, new RetailServer().getAppliedTitle()));
		return sp;
	}

	/**
	 * 北按钮面板
	 * @return
	 */
	private JPanel getSouth(){
		JPanel p=new JPanel();
		p.add(confirm=CommonComponent.buildButton("确认", null, null, null, null, null, null, true));
		p.add(back=CommonComponent.buildButton("返回", null, null, null, null, null, null, true));
		return p;
	}
	
	
	/**
	 * 添加事件
	 */
	private void initAction() {
		MoveConfirmAction fba=new MoveConfirmAction(this);
		confirm.addActionListener(fba);
		back.addActionListener(fba);
	}

	public JTable getTable() {
		return table;
	}

	public Vector getData() {
		return data;
	}
	
	
}
