package com.enterprise_sss.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.enterprise_sss.action.ReliefAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.view.frame.RetailFrame;

/**
 * 班次结账并换班
 * @author yiguo
 *
 */
public class Relief extends JDialog{
	private RetailFrame rf;
	private JButton shift,back,exit;//换班，返回，退出
	
	public Relief(RetailFrame rf){
		this.rf=rf;
		init();
	}

	private void init() {
		initDialog();
		initAction();
		this.setVisible(true);
	}

	private void initDialog() {
		this.setTitle("班次结账");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//不可由用户调整大小
		this.setModal(true);//只能操作当前窗口
		this.setLayout(new BorderLayout());
		this.add(getCenter(),BorderLayout.CENTER);
		this.add(getSouth(),BorderLayout.SOUTH);
		
	}
	
	/**
	 * 中心面板
	 * @return
	 */
	private JPanel getCenter(){
		JPanel panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.add(CommonComponent.buildLabel("结帐金额：",null, null, null, null, null),new GBC(0,0));
		panel.add(CommonComponent.buildLabel(new Double(rf.getSum()).toString(),null, Color.red, new Font("宋体",Font.BOLD,30), null, null),new GBC(1,0));
		return panel;
	}
	
	/**
	 * 南面板
	 * @return
	 */
	private JPanel getSouth(){
		JPanel panel=new JPanel();
		panel.add(shift=CommonComponent.buildButton("换班",null, null, null, null, null, null, true));
		panel.add(back=CommonComponent.buildButton("返回",null, null, null, null, null, null, true));
		panel.add(exit=CommonComponent.buildButton("退出",null, null, null, null, null, null, true));
		return panel;
	}
	
	private void initAction() {
		ReliefAction ra=new ReliefAction(this);
		shift.addActionListener(ra);
		back.addActionListener(ra);
		exit.addActionListener(ra);
	}

	public RetailFrame getRf() {
		return rf;
	}
	
}
