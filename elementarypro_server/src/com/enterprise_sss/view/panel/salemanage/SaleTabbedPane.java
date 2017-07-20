package com.enterprise_sss.view.panel.salemanage;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class SaleTabbedPane extends JTabbedPane{

	
	public SaleTabbedPane(){
		this.add("销售单查询", new SaleQueryBasePanel());
		this.add("订货单查询", new OrderSalePanel());
		this.add("退货单查询", new SaleBackPanel());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("测试");		
		SaleTabbedPane stp= new SaleTabbedPane();
		frame.getContentPane().add(stp);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
