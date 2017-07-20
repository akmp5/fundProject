package com.enterprise_sss.view.panel.salemanage;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.enterprise_sss.dao.salemanagedao.SaleManageDao;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.MyTableModel;

public class SaleBackPanel extends SaleQueryBasePanel {
	
	JButton button;
	public SaleBackPanel(){
		
		uppanel.setBorder(BorderFactory.createTitledBorder("按条件查询："));
		tablepanel.setBorder(BorderFactory.createTitledBorder("查询结果"));		
	}
		
	public JPanel initialPanel() {
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label = new JLabel();
		
		panel1.setLayout(new GridLayout(3,2));
		panel1 = CommonComponent.buildRadioGroup(new String[]{"查询全部"}, null,new boolean[]{true,true,true,true,true,true}, 0);
		label.setText("输入要查找的内容：");
		panel2.add(label); 
		panel2.add(CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true));
		panel2.add(button = CommonComponent.buildButton("查询", null, null, null, null, null, null, true));
		
		rowData = new SaleManageDao().selectSaleBack();
		
		button.addMouseListener(new MouseAdapter(){			
			public void mouseClicked(MouseEvent e) {
				rowData = new SaleManageDao().selectSaleBack();
				System.out.println("退货单查询事件端测试："+rowData);
				if((rowData==null||rowData.isEmpty())){
					JOptionPane.showMessageDialog(new JFrame(),"未找到相关数据!");
				}
				else{
				mytablemodel =new MyTableModel(rowData,title); 
				table.setModel(mytablemodel);
				}
			 }
		});
		
		uppanel.setLayout(new GridLayout(2,1));
		uppanel.add(panel1);
		uppanel.add(panel2);
		return uppanel;
	}	
		
	public JScrollPane initialTable(){
		
		title.add("销售退货单编号");
		title.add("销售单编号");
		title.add("货物编号");
		title.add("退货数量");
		title.add("销售价");
		title.add("退货仓库编号");

		mytablemodel = new MyTableModel(rowData, title);
		table.setModel(mytablemodel);
		//table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablepanel = new JScrollPane(table);
		tablepanel.setViewportView(table);
		return tablepanel;
	}
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		JFrame frame = new JFrame("测试");		
//		SaleBackPanel sbp= new SaleBackPanel();
//		frame.getContentPane().add(sbp);
//		frame.setSize(500,500);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//	}

}
