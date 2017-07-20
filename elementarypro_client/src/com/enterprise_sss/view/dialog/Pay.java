package com.enterprise_sss.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.enterprise_sss.action.PayAction;
import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.view.frame.RetailFrame;

/**
 * 付款Dialog
 * @author Administrator
 *
 */
public class Pay extends JDialog {
	private JLabel due,spill,error;//应付金额，找零金额，错误提示
	private TextField real;//实付金额
	private JButton pay,back;//付款，返回
	private RetailFrame rf;//零售界面
	
	public Pay(RetailFrame rf){
		this.rf=rf;
		init();
	}

	private void init() {
		initDialog();
		initAction();
		this.setVisible(true);
	}

	private void initDialog() {
		this.setTitle("付款");
		this.setSize(300, 300);
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
		panel.add(CommonComponent.buildLabel("应付金额：",null, null, null, null, null),new GBC(0,0));
		panel.add(due=CommonComponent.buildLabel(rf.getAmount().getText(),null, Color.red, new Font("宋体",Font.BOLD,30), null, null),new GBC(1,0));
		panel.add(CommonComponent.buildLabel("实付金额：",null, null, null, null, null),new GBC(0,1));
		real=new TextField("0",6);
		real.setFont(new Font("宋体",Font.BOLD,30));
		panel.add(real,new GBC(1,1));
		panel.add(error=CommonComponent.buildLabel("",null, Color.red, null, null, null),new GBC(3,1));
		panel.add(CommonComponent.buildLabel("找零金额：",null, null, null, null, null),new GBC(0,2));
		panel.add(spill=CommonComponent.buildLabel("-"+rf.getAmount().getText(),null, Color.blue, new Font("宋体",Font.BOLD,30), null, null),new GBC(1,2));
		return panel;
	}
	
	/**
	 * 南面板
	 * @return
	 */
	private JPanel getSouth(){
		JPanel panel=new JPanel();
		panel.add(pay=CommonComponent.buildButton("付款",null, null, null, null, null, null, true));
		panel.add(back=CommonComponent.buildButton("返回",null, null, null, null, null, null, true));
		return panel;
	}
	
	private void initAction() {
		PayAction pa=new PayAction(this);
		pay.addActionListener(pa);
		back.addActionListener(pa);
		
		//为实付金额文本框添加文本改变事件
		real.addTextListener(new TextListener(){
//			@Override
			public void textValueChanged(TextEvent e) {
				if(getReal().getText().length()>0){
					/*
					 * 设置找零金额(实付金额-应付金额)可识别的格式有:0.0,.0,0.,0
					 * 只有实付金额不为空且格式正确时才可付款
					 */
					if(new RetailServer().isDouble(getReal().getText())){
						getError().setText("");
						double a=Double.parseDouble(getReal().getText());
						double b=Double.parseDouble(getDue().getText());
						getSpill().setText(new Double(a-b).toString());
						pay.setEnabled(true);
					}else{
						getError().setText("？");
						getSpill().setText("0");
						pay.setEnabled(false);
					}
				}else{
					getSpill().setText("0");
					pay.setEnabled(false);
				}
			}
		});
	}

	public JLabel getDue() {
		return due;
	}

	public TextField getReal() {
		return real;
	}
	

	public JLabel getError() {
		return error;
	}

	public JLabel getSpill() {
		return spill;
	}

	public RetailFrame getRf() {
		return rf;
	}

}
