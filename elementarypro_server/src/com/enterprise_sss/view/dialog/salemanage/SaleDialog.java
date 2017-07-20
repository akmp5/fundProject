package com.enterprise_sss.view.dialog.salemanage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.enterprise_sss.action.menu.DialogAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.go.calendar.JDatePicker;


public class SaleDialog extends OrderSaleDialog{
	
	public JTextField tf3,tf4;
	
	public SaleDialog(){
		super();
	}	
	
	public SaleDialog(String title){
		this.setTitle(title);
		this.setModal(true);
		initialDialog();
		this.setVisible(true);
	}
	


	public JPanel getPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		uppanel.setBorder(BorderFactory.createTitledBorder("销售单信息"));
		tablepanel.setBorder(BorderFactory.createTitledBorder("销售单明细"));
		buttonpanel.setBorder(BorderFactory.createEtchedBorder());
		
		panel.add(initialPanel(),BorderLayout.NORTH);
		panel.add(initialTable(),BorderLayout.CENTER);
		panel.add(initialButton(),BorderLayout.SOUTH);

		return panel;
	}
	
	public JPanel initialPanel() {
		
		uppanel.setLayout(new GridBagLayout());
		uppanel.add(CommonComponent.buildLabel("客户编号：", null, null, null, null, null),new GBC(0,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(tf1 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(CommonComponent.buildLabel("销售日期：", null, null, null, null, null),new GBC(2,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(tf2 = new JDatePicker(),new GBC(3,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(CommonComponent.buildLabel("业务员编号：", null, null, null, null, null),new GBC(0,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(tf3 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(CommonComponent.buildLabel("制表人：", null, null, null, null, null),new GBC(2,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(tf4 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(3,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(CommonComponent.buildLabel("保管员：", null, null, null, null, null),new GBC(0,2).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(tf5 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,2).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(CommonComponent.buildLabel("销售订单编号：", null, null, null, null, null),new GBC(2,2).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(tf6 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(3,2).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		return uppanel;
	}	
		
	public JPanel initialTable(){
		JComboBox cb ;
		Vector title = new Vector();
		title.add("商品编号");
		title.add("销售数量");
		title.add("销售价格");
		title.add("库编号");
		
		model = new DefaultTableModel(title,18);
		table.setModel(model);
		TableColumnModel columnModel = table.getColumnModel();
		cb = new JComboBox();
		cb.addItem(1);
		columnModel.getColumn(3).setCellEditor(new DefaultCellEditor(cb));
		
		//table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		sp = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(550,300));
		tablepanel.setLayout(new GridBagLayout());
		tablepanel.add(sp,new GBC(0,3).setFill(GBC.BOTH).setAnchor(GBC.CENTER).setInset(5));
		return tablepanel;
	}
	
	public JPanel initialButton(){
		JButton subButton,exitButton,addButton,delButton;
		DialogAction da = new DialogAction(this);
		buttonpanel.setLayout(new GridBagLayout());
		buttonpanel.add(subButton = CommonComponent.buildButton("提交", null, null, null, null, null, null, true),new GBC(0,4).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		buttonpanel.add(exitButton = CommonComponent.buildButton("退出", null, null, null, null, null, null, true),new GBC(3,4).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		buttonpanel.add(addButton = CommonComponent.buildButton("增加行", null, null, null, null, null, null, true),new GBC(1,4).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		buttonpanel.add(delButton = CommonComponent.buildButton("删除行", null, null, null, null, null, null, true),new GBC(2,4).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		subButton.addActionListener(da);
		exitButton.addActionListener(da);
		addButton.addActionListener(da);
		delButton.addActionListener(da);
		
		return buttonpanel;
		
	}

	public static void main(String[] args){
		SaleDialog sd = new SaleDialog("销售单");
		String d =DateFormat.getDateInstance().format(System.currentTimeMillis());
		System.out.println(d);
	}
}
