package com.enterprise_sss.view.dialog.checkout;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.enterprise_sss.view.panel.checkout.CheckoutOper;
import com.enterprise_sss.view.panel.checkout.GatherHistory;
import com.enterprise_sss.view.panel.checkout.JXCGather;
import com.enterprise_sss.view.panel.checkout.SetDate;

/**
 * �������Dialog
 * @author yiguo
 *
 */
public class Checkout extends JDialog {
	public JTabbedPane tab;
	private JButton setDate,check,gather;
	private  JXCGather jg;
	private GatherHistory jh;
	private int index;//��ʼ����ʾ���������
	public Checkout(int index){
		this.index=index;
		init();
	}
	
	
	private void init() {
		initDialog();
	}


	private void initDialog() {
		this.setTitle("�������");
		this.setResizable(false);//���ܸı��С
		this.setModal(true);//ֻ�ܲ�����ǰ����
		this.setSize(800, 600);
//		SubstanceLookAndFeel.setSkin(new MagmaSkin());
		this.getContentPane().setBackground(new Color(205,235,255));
		this.add(getTabPanel());
		this.setVisible(true);
	}
	
	/**
	 * ѡ����
	 * @return
	 */
	private JTabbedPane getTabPanel(){
		tab=new JTabbedPane(JTabbedPane.LEFT);
		tab.add("�� �� �� ��", new SetDate());
		tab.add("���������", jg=new JXCGather());
		tab.add("�� ʷ �� ѯ",jh= new GatherHistory());
		tab.add("��	     ��", new CheckoutOper(this));
		tab.setSelectedIndex(index);
		tab.setBackground(new Color(205,235,255));
		return tab;
	}


	public JXCGather getJg() {
		return jg;
	}


	public GatherHistory getJh() {
		return jh;
	}

}
