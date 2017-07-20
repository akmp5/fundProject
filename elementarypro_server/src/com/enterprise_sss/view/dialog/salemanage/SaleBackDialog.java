package com.enterprise_sss.view.dialog.salemanage;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.text.DateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.enterprise_sss.action.menu.DialogAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;


public class SaleBackDialog extends SaleDialog{
	private JScrollPane sp = null;
	private JTable table = null;
	private JPanel uppanel = new JPanel();
	private JPanel buttonpanel = new JPanel();
	public JTextField tf1,tf2,tf3,tf4,tf5;
	
	public SaleBackDialog(){
		
	}
	
	public SaleBackDialog(String title){
		this.setTitle(title);
		initialDialog();
		this.setVisible(true);
	}
	
	public void initialDialog() {
		Container con = this.getContentPane();
		con.add(getPanel());
		this.setSize(650, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public JPanel getPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		uppanel.setBorder(BorderFactory.createTitledBorder("退售单信息"));
		buttonpanel.setBorder(BorderFactory.createEtchedBorder());
		
		panel.add(initialPanel(),BorderLayout.CENTER);
		panel.add(initialButton(),BorderLayout.SOUTH);
//		panel.setLayout(new GridBagLayout());
//		panel.add(initialPanel(),new GBC(0,0).setFill(GBC.BOTH).setAnchor(GBC.CENTER).setInset(5));
//		panel.add(initialButton(),new GBC(0,1).setFill(GBC.BOTH).setAnchor(GBC.CENTER).setInset(5));
		return panel;
	}
	
	public JPanel initialPanel() {
		
		uppanel.setLayout(new GridBagLayout());
		uppanel.add(CommonComponent.buildLabel("销售单编号：", null, null, null, null, null),new GBC(0,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(15));
		uppanel.add(tf1 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(15));
		uppanel.add(CommonComponent.buildLabel("货物编号：", null, null, null, null, null),new GBC(2,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(15));
		uppanel.add(tf2 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(3,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(15));
		uppanel.add(CommonComponent.buildLabel("退货数量：", null, null, null, null, null),new GBC(0,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(15));
		uppanel.add(tf3 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(15));
		uppanel.add(CommonComponent.buildLabel("销售价：", null, null, null, null, null),new GBC(2,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(15));
		uppanel.add(tf4 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(3,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(15));
		uppanel.add(CommonComponent.buildLabel("退货仓库编号：", null, null, null, null, null),new GBC(0,2).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(15));
		uppanel.add(tf5 = CommonComponent.buildTextField("txt", "1", null, null, null, null, null, false, true),new GBC(1,2).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(15));
		
		return uppanel;
	}	
		
	
	public JPanel initialButton(){
		JButton subButton,exitButton;
		DialogAction da = new DialogAction(this);
		buttonpanel.setLayout(new GridBagLayout());
		buttonpanel.add(subButton = CommonComponent.buildButton("提交", null, null, null, null, null, null, true),new GBC(0,4).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		buttonpanel.add(exitButton = CommonComponent.buildButton("退出", null, null, null, null, null, null, true),new GBC(1,4).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		subButton.addActionListener(da);
		exitButton.addActionListener(da);
		
		return buttonpanel;
		
	}	
	
	public JPanel initialTable(){
		return null;	
	}
	
	
	public static void main(String[] args){
		SaleBackDialog sd = new SaleBackDialog("退货单");
		String d =DateFormat.getDateInstance().format(System.currentTimeMillis());
		System.out.println(d);
	}
}
