package com.enterprise_sss.view.dialog;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import com.enterprise_sss.action.RefPWDialogAction;
import com.enterprise_sss.view.frame.MainFrame;

public class RefPWDialog extends JDialog {
	
	private MainFrame frame;
	
	private JPasswordField pw,pw1;
	
	public RefPWDialog(MainFrame frame){
		this.frame = frame;
		init();
	}
	
	public void init(){
		pw=createPW(10);
		pw1=createPW(10);
		this.setLayout(new FlowLayout());
		this.setSize(200, 120);
		this.setTitle("修改密码");
		this.add(createLabel("请输入新密码："));
		this.add(pw);
		this.add(createLabel("重新输入密码："));
		this.add(pw1);
		this.add(createButton("确定"));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}
	
	public JButton createButton(String name){
		JButton button=new JButton(name);
		button.addActionListener(new RefPWDialogAction(this));
		return button;
	}
	
	public JPasswordField createPW(int n){
		JPasswordField pass=new JPasswordField(n);
		pass.setEchoChar('*');
		return pass;
	}
	
	public JLabel createLabel(String name){
		JLabel label=new JLabel(name);
		return label;
		
	}
	
	public RefPWDialog(){
		init();
	}

	public JPasswordField getPw() {
		return pw;
	}

	public JPasswordField getPw1() {
		return pw1;
	}

	public MainFrame getFrame() {
		return frame;
	}

}
