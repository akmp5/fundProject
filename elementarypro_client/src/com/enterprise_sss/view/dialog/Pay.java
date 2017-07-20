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
 * ����Dialog
 * @author Administrator
 *
 */
public class Pay extends JDialog {
	private JLabel due,spill,error;//Ӧ���������������ʾ
	private TextField real;//ʵ�����
	private JButton pay,back;//�������
	private RetailFrame rf;//���۽���
	
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
		this.setTitle("����");
		this.setSize(300, 300);
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
		panel.add(CommonComponent.buildLabel("Ӧ����",null, null, null, null, null),new GBC(0,0));
		panel.add(due=CommonComponent.buildLabel(rf.getAmount().getText(),null, Color.red, new Font("����",Font.BOLD,30), null, null),new GBC(1,0));
		panel.add(CommonComponent.buildLabel("ʵ����",null, null, null, null, null),new GBC(0,1));
		real=new TextField("0",6);
		real.setFont(new Font("����",Font.BOLD,30));
		panel.add(real,new GBC(1,1));
		panel.add(error=CommonComponent.buildLabel("",null, Color.red, null, null, null),new GBC(3,1));
		panel.add(CommonComponent.buildLabel("�����",null, null, null, null, null),new GBC(0,2));
		panel.add(spill=CommonComponent.buildLabel("-"+rf.getAmount().getText(),null, Color.blue, new Font("����",Font.BOLD,30), null, null),new GBC(1,2));
		return panel;
	}
	
	/**
	 * �����
	 * @return
	 */
	private JPanel getSouth(){
		JPanel panel=new JPanel();
		panel.add(pay=CommonComponent.buildButton("����",null, null, null, null, null, null, true));
		panel.add(back=CommonComponent.buildButton("����",null, null, null, null, null, null, true));
		return panel;
	}
	
	private void initAction() {
		PayAction pa=new PayAction(this);
		pay.addActionListener(pa);
		back.addActionListener(pa);
		
		//Ϊʵ������ı�������ı��ı��¼�
		real.addTextListener(new TextListener(){
//			@Override
			public void textValueChanged(TextEvent e) {
				if(getReal().getText().length()>0){
					/*
					 * ����������(ʵ�����-Ӧ�����)��ʶ��ĸ�ʽ��:0.0,.0,0.,0
					 * ֻ��ʵ����Ϊ���Ҹ�ʽ��ȷʱ�ſɸ���
					 */
					if(new RetailServer().isDouble(getReal().getText())){
						getError().setText("");
						double a=Double.parseDouble(getReal().getText());
						double b=Double.parseDouble(getDue().getText());
						getSpill().setText(new Double(a-b).toString());
						pay.setEnabled(true);
					}else{
						getError().setText("��");
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
