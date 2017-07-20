package com.enterprise_sss.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.enterprise_sss.action.MoveApplyAction;
import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.view.frame.RetailFrame;

/**
 * 调货申请Dialog
 * @author yiguo
 *
 */
public class MoveApply extends JDialog {
	private RetailFrame rf;
	private JTable table;//商品信息表
	private JButton confirm,back;//确定，返回
	private JTextField num,desc;//申请数量,申请说明
	
	public MoveApply(RetailFrame rf){
		this.rf=rf;
		init();
	}

	private void init() {
		initDialog();
		initAction();
		this.setVisible(true);
	}

	private void initDialog() {
		this.setTitle("调货申请");
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);//居中显示
		this.setResizable(false);//不可由用户调整大小
		this.setModal(true);//只能操作当前窗口
		this.setLayout(new BorderLayout());
		this.add(getNorth(),BorderLayout.NORTH);
		this.add(getCenter(),BorderLayout.CENTER);
		this.add(getSouth(),BorderLayout.SOUTH);
		
	}
	
	/**
	 * 北面板
	 * @return
	 */
	private JPanel getNorth(){
		JPanel panel=new JPanel();
		panel.add(CommonComponent.buildLabel("申请数量：",null, null, null, null, null));
		panel.add(num=CommonComponent.buildTextField("txt", "0", null, null, null, null,null, true, true));
		num.setColumns(8);
		panel.add(CommonComponent.buildLabel("申请说明：",null, null, null, null, null));
		panel.add(desc=CommonComponent.buildTextField("txt", "", null, null, null, null, null, true, true));
		desc.setColumns(8);
		return panel;
	}
	
	/**
	 * 中心滚动面板
	 * @return
	 */
	private JScrollPane getCenter(){
		JScrollPane sp=new JScrollPane();
		sp.setViewportView(table=CommonComponent.buildTable(new RetailServer().getApplyData(rf.getComms()), new RetailServer().getApplyTitle()));
		return sp;
	}
	
	/**
	 * 南面板
	 * @return
	 */
	private JPanel getSouth(){
		JPanel panel=new JPanel();
		panel.add(confirm=CommonComponent.buildButton("确定",null, null, null, null, null, null, true));
		panel.add(back=CommonComponent.buildButton("返回",null, null, null, null, null, null, true));
		return panel;
	}
	
	private void initAction() {
		MoveApplyAction ra=new MoveApplyAction(this);
		confirm.addActionListener(ra);
		back.addActionListener(ra);
	}

	public JTable getTable() {
		return table;
	}

	public JTextField getNum() {
		return num;
	}

	public JTextField getDesc() {
		return desc;
	}

	public RetailFrame getRf() {
		return rf;
	}
}
