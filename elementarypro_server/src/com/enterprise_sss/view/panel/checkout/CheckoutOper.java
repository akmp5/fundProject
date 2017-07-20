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
 * ����
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
		this.add(CommonComponent.buildLabel("ע�⣺��ʽ���ʺ�������ݽ��޷����ģ�����ʽ����ǰ����ģ����ʣ��Բ鿴���������Ƿ���ȷ!", null, Color.red,null, null, null),BorderLayout.NORTH);
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
		bp.add(imitate=CommonComponent.buildButton("ģ�����", null, null, Color.BLUE, new Font("����",Font.BOLD,15), null, null,true));
		bp.add(formal=CommonComponent.buildButton("��ʽ����", null, null, Color.BLUE, new Font("����",Font.BOLD,15), null, null,false));
		bp.add(export=CommonComponent.buildButton("����", null, null, Color.BLUE, new Font("����",Font.BOLD,15), null, null,false));
		bp.add(print=CommonComponent.buildButton("��ӡ", null, null, Color.BLUE, new Font("����",Font.BOLD,15), null, null,false));
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
