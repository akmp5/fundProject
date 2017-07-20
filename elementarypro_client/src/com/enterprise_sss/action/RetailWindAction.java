package com.enterprise_sss.action;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.enterprise_sss.socket.Receive;
import com.enterprise_sss.socket.Send;
import com.enterprise_sss.view.frame.RetailFrame;

/**
 * ���۹������¼�
 * @author yiguo
 *
 */
public class RetailWindAction extends WindowAdapter {
	private RetailFrame rf;//���۹�����
	
	public RetailWindAction(RetailFrame rf){
		this.rf=rf;
	}
	
	/**
	 * �����״α�Ϊ�ɼ�ʱ���á�
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		rf.getClient().setText(rf.getUserVO().getUser());
		rf.getCashier().setText(rf.getUserVO().getUser());
	}
	
	/**
	 * �û���ͼ�Ӵ��ڵ�ϵͳ�˵��йرմ���ʱ���á�
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		
		int i=JOptionPane.showConfirmDialog(null, "�Ƿ�ر�", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION);
		if(i==JOptionPane.YES_OPTION){
			//����������Ϣ�ύ������
			new Send(rf.getSaleBill());
//			new Send(rf.getSaleItems());
			Vector data = rf.getSaleItems();
			for (int n = 0; n < data.size(); n++) {
				new Send((Vector)data.get(n));
			}
			//�˳�ϵͳ
			System.exit(0);
		}
	}
	
	/**
	 * �� Window ����Ϊ� Window ʱ���á�
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		rf.getOper().setText("����״̬");
	}

	/**
	 * �������dispose�ر��¼�
	 */
	@Override
	public void windowClosed(WindowEvent e) {
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		rf.getOper().setText("������״̬");
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}


}
