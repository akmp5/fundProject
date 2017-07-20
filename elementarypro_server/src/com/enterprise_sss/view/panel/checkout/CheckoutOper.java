package com.enterprise_sss.view.panel.checkout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.action.checkout.CheckoutOperAction;
import com.enterprise_sss.control.CheckoutServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.view.dialog.checkout.Checkout;

/**
 * 结帐
 * @author yiguo
 *
 */
public class CheckoutOper extends JPanel {
private JButton imitate,formal,export,print;
private JTable table;
private Checkout ch;
	
	public CheckoutOper(Checkout ch){
		this.ch=ch;
		this.setLayout(new BorderLayout());
		this.add(CommonComponent.buildLabel("注意：正式结帐后汇总数据将无法更改，在正式结帐前请先模拟结帐，以查看结帐数据是否正确!", null, Color.red,null, null, null),BorderLayout.NORTH);
		this.add(getScrollPane(),BorderLayout.CENTER);
		this.add(getButtonPanel(),BorderLayout.SOUTH);
		initAction();
	}
	
	public JScrollPane getScrollPane(){
		JScrollPane sp=new JScrollPane();
		DefaultTableModel model=new DefaultTableModel(new Vector(),new CheckoutServer().getImitateTitle());
		table=new JTable(model);
		sp.setViewportView(table);
		return sp;
	}
	
	public JPanel getButtonPanel(){
		JPanel bp=new JPanel();
		bp.add(imitate=CommonComponent.buildButton("模拟结账", null, null, Color.BLUE, new Font("宋体",Font.BOLD,15), null, null,true));
		bp.add(formal=CommonComponent.buildButton("正式结账", null, null, Color.BLUE, new Font("宋体",Font.BOLD,15), null, null,false));
		bp.add(export=CommonComponent.buildButton("导出", null, null, Color.BLUE, new Font("宋体",Font.BOLD,15), null, null,false));
		bp.add(print=CommonComponent.buildButton("打印", null, null, Color.BLUE, new Font("宋体",Font.BOLD,15), null, null,false));
		return bp;
	}
	
	private void initAction() {
		CheckoutOperAction coa=new CheckoutOperAction(this);
		imitate.addActionListener(coa);
		formal.addActionListener(coa);
		export.addActionListener(coa);
		print.addActionListener(coa);
	}



	public JButton getFormal() {
		return formal;
	}
	
	public void setFormal(JButton formal) {
		this.formal = formal;
	}

	public JTable getTable() {
		return table;
	}

	public Checkout getCh() {
		return ch;
	}

	public JButton getExport() {
		return export;
	}

	public JButton getPrint() {
		return print;
	}

}
