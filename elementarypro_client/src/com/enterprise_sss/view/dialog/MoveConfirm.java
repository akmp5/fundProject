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
	private JTable table;//���������û������¼��
	private JButton confirm,back;//ȷ�ϣ�����
	private RetailFrame rf;//���۽���
	private Vector data;//�������������
	
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
		this.setTitle("���ȷ��");
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//�������û�������С
		this.setModal(true);//ֻ�ܲ�����ǰ����
		this.setLayout(new BorderLayout());
		this.add(getCenter(),BorderLayout.CENTER);
		this.add(getSouth(),BorderLayout.SOUTH);
		
	}
	
	/**
	 * ���Ĺ������
	 * @return
	 */
	private JScrollPane getCenter(){
		JScrollPane sp=new JScrollPane();
		sp.setViewportView(table=CommonComponent.buildTable(data, new RetailServer().getAppliedTitle()));
		return sp;
	}

	/**
	 * ����ť���
	 * @return
	 */
	private JPanel getSouth(){
		JPanel p=new JPanel();
		p.add(confirm=CommonComponent.buildButton("ȷ��", null, null, null, null, null, null, true));
		p.add(back=CommonComponent.buildButton("����", null, null, null, null, null, null, true));
		return p;
	}
	
	
	/**
	 * ����¼�
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
