package com.enterprise_sss.view.panel.checkout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.action.checkout.GatherHistoryAction;
import com.enterprise_sss.action.checkout.JXCGatherAction;
import com.enterprise_sss.control.CheckoutServer;
import com.enterprise_sss.util.CommonComponent;

/**
 * 进销存汇总历史查询
 * @author yiguo
 *
 */
public class GatherHistory extends JPanel {
	private JTable table;
	private JButton export,print;
	
	public GatherHistory(){
		this.setLayout(new BorderLayout());
		this.add(getNorthPanel(),BorderLayout.NORTH);
		this.add(getButtonPanel(),BorderLayout.SOUTH);
		this.add(getCenterPanel());
		initAction();
	}
	
	private JPanel getNorthPanel(){
		JPanel north=new JPanel();
		north.add(CommonComponent.buildLabel("历史结帐的结果", null, Color.blue, new Font("宋体",Font.BOLD|Font.ITALIC,30), null, null),BorderLayout.CENTER);
		return north;
	}
	
	private JScrollPane getCenterPanel(){
		JScrollPane center=new JScrollPane();
		DefaultTableModel model=new DefaultTableModel(new CheckoutServer().getGather(),new CheckoutServer().getGatherTitle());
		table=new JTable(model);
		center.setViewportView(table);
		return center;
	}
	
	public JPanel getButtonPanel(){
		JPanel bp=new JPanel();
		bp.add(export=CommonComponent.buildButton("导出", null, null, Color.BLUE, new Font("宋体",Font.BOLD,15), null, null,true));
		bp.add(print=CommonComponent.buildButton("打印", null, null, Color.BLUE, new Font("宋体",Font.BOLD,15), null, null,true));
		return bp;
	}

	public JTable getTable() {
		return table;
	}
	
	public void initAction(){
		GatherHistoryAction jga=new GatherHistoryAction(this);
		export.addActionListener(jga);
		print.addActionListener(jga);
	}
}
