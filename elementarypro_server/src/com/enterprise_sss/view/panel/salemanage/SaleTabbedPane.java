package com.enterprise_sss.view.panel.salemanage;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class SaleTabbedPane extends JTabbedPane{

	
	public SaleTabbedPane(){
		this.add("���۵���ѯ", new SaleQueryBasePanel());
		this.add("��������ѯ", new OrderSalePanel());
		this.add("�˻�����ѯ", new SaleBackPanel());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("����");		
		SaleTabbedPane stp= new SaleTabbedPane();
		frame.getContentPane().add(stp);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
