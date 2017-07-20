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
 * ��ν��˲�����
 * @author yiguo
 *
 */
public class Relief extends JDialog{
	private RetailFrame rf;
	private JButton shift,back,exit;//���࣬���أ��˳�
	
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
		this.setTitle("��ν���");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//�������û�������С
		this.setModal(true);//ֻ�ܲ�����ǰ����
		this.setLayout(new BorderLayout());
		this.add(getCenter(),BorderLayout.CENTER);
		this.add(getSouth(),BorderLayout.SOUTH);
		
	}
	
	/**
	 * �������
	 * @return
	 */
	private JPanel getCenter(){
		JPanel panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.add(CommonComponent.buildLabel("���ʽ�",null, null, null, null, null),new GBC(0,0));
		panel.add(CommonComponent.buildLabel(new Double(rf.getSum()).toString(),null, Color.red, new Font("����",Font.BOLD,30), null, null),new GBC(1,0));
		return panel;
	}
	
	/**
	 * �����
	 * @return
	 */
	private JPanel getSouth(){
		JPanel panel=new JPanel();
		panel.add(shift=CommonComponent.buildButton("����",null, null, null, null, null, null, true));
		panel.add(back=CommonComponent.buildButton("����",null, null, null, null, null, null, true));
		panel.add(exit=CommonComponent.buildButton("�˳�",null, null, null, null, null, null, true));
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
