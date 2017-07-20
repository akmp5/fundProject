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
 * ���Dialog
 * @author yiguo
 *
 */
public class FreeBill extends JDialog {
	private JTable table;//�ҵ���
	private JButton free,delete,back;//��ң�ɾ��������
	private RetailFrame rf;//���۽���
	
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
		this.setTitle("���");
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
		sp.setViewportView(table=CommonComponent.buildTable(rf.getHangBill(), new RetailServer().getHangBillTitle()));
		return sp;
	}

	/**
	 * �ϰ�ť���
	 * @return
	 */
	private JPanel getSouth(){
		JPanel p=new JPanel();
		p.add(free=CommonComponent.buildButton("���", null, null, null, null, null, null, true));
		p.add(delete=CommonComponent.buildButton("ɾ��", null, null, null, null, null, null, true));
		p.add(back=CommonComponent.buildButton("����", null, null, null, null, null, null, true));
		return p;
	}
	
	/**
	 * ����¼�
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
