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
 * 帐务管理Dialog
 * @author yiguo
 *
 */
public class Checkout extends JDialog {
	public JTabbedPane tab;
	private JButton setDate,check,gather;
	private  JXCGather jg;
	private GatherHistory jh;
	private int index;//初始化显示的面板索引
	public Checkout(int index){
		this.index=index;
		init();
	}
	
	
	private void init() {
		initDialog();
	}


	private void initDialog() {
		this.setTitle("账务管理");
		this.setResizable(false);//不能改变大小
		this.setModal(true);//只能操作当前窗口
		this.setSize(800, 600);
//		SubstanceLookAndFeel.setSkin(new MagmaSkin());
		this.getContentPane().setBackground(new Color(205,235,255));
		this.add(getTabPanel());
		this.setVisible(true);
	}
	
	/**
	 * 选项卡面板
	 * @return
	 */
	private JTabbedPane getTabPanel(){
		tab=new JTabbedPane(JTabbedPane.LEFT);
		tab.add("日 期 设 定", new SetDate());
		tab.add("进销存汇总", jg=new JXCGather());
		tab.add("历 史 查 询",jh= new GatherHistory());
		tab.add("结	     帐", new CheckoutOper(this));
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
