package com.enterprise_sss.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.enterprise_sss.action.DailyStatAction;
import com.enterprise_sss.action.FreeBillAction;
import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.view.frame.RetailFrame;


/**
 * 每日销售统计Dialog
 * @author yiguo
 *
 */
public class DailyStat extends JDialog {
	private JTable table;//每日销售统计表
	private JButton export,print,back;//导出，打印，返回
	private JLabel num,sum;//总数量，总金额
	private Vector statData;//每日销售统计数据
	private RetailFrame rf;//零售界面
	
	public DailyStat(RetailFrame rf){
		this.rf=rf;
		statData=new RetailServer().getStatData(rf.getComms(), rf.getSaleItems());
		init();
	}

	private void init() {
		initDialog();
		initAction();
		this.setVisible(true);
	}

	private void initDialog() {
		this.setTitle("每日销售统计");
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
	 * 中心滚动面板
	 * @return
	 */
	private JScrollPane getCenter(){
		JScrollPane sp=new JScrollPane();
		sp.setViewportView(table=CommonComponent.buildTable(statData, new RetailServer().getStatTitle()));
		return sp;
	}

	/**
	 * 北按钮面板
	 * @return
	 */
	private JPanel getNorth(){
		JPanel p=new JPanel();
		p.add(export=CommonComponent.buildButton("导出", null, null, null, null, null, null, true));
		p.add(print=CommonComponent.buildButton("打印", null, null, null, null, null, null, true));
		p.add(back=CommonComponent.buildButton("返回", null, null, null, null, null, null, true));
		return p;
	}
	
	/**
	 * 南标签面板
	 * @return
	 */
	private JPanel getSouth(){
		JPanel p=new JPanel();
		p.add(CommonComponent.buildLabel("总计数量：",null, null, null, null, null));
		p.add(CommonComponent.buildLabel(new RetailServer().getSum(statData).get(0).toString()
				+"    ",null, null, null, null, null));
		p.add(CommonComponent.buildLabel("总计金额：",null, null, null, null, null));
		p.add(CommonComponent.buildLabel(new RetailServer().getSum(statData).get(1).toString()
				,null, Color.red, null, null, null));
		return p;
	}
	
	/**
	 * 添加事件
	 */
	private void initAction() {
		DailyStatAction fba=new DailyStatAction(this);
		export.addActionListener(fba);
		print.addActionListener(fba);
		back.addActionListener(fba);
	}

	public JTable getTable() {
		return table;
	}

	public Vector getStatData() {
		return statData;
	}
	
}
