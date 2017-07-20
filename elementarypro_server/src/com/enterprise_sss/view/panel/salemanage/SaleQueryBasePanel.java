package com.enterprise_sss.view.panel.salemanage;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import com.enterprise_sss.action.salemanage.SaleQueryAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.MyTableModel;


public class SaleQueryBasePanel extends JPanel{
	
		protected JScrollPane sp = null;
		protected JTable table = new JTable();
		protected JPanel uppanel = new JPanel();
		protected JScrollPane tablepanel = new JScrollPane();
		protected JButton button;
		protected Vector rowData = new Vector();
		protected  Vector title = new Vector();
		protected MyTableModel mytablemodel;
		public JTextField tf ;		
		public JRadioButton[] rb = new JRadioButton[6];
		public String string;
//		JRadioButton[] rb = null;
		
		public SaleQueryBasePanel(){
			
			this.setLayout(new BorderLayout());
			uppanel.setBorder(BorderFactory.createTitledBorder("按条件查询："));
			tablepanel.setBorder(BorderFactory.createTitledBorder("查询结果"));
			this.add(CommonComponent.buildSplitPane(JSplitPane.VERTICAL_SPLIT, true, true, initialPanel(), initialTable()));
//			this.add(initialPanel(),BorderLayout.NORTH);
//			this.add(initialTable(),BorderLayout.CENTER);
		
		}
			
		public JPanel initialPanel() {
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			JLabel label = new JLabel();
			ButtonGroup bg = new ButtonGroup();
			String[] str = new String[]{"查询全部","客户编号","销售日期","商品编号","商品拼音","业务员"};			
			JRadioButton jb1=null;
			for(int i=0;i<6;i++){
				if(i==0){
					jb1= new JRadioButton(str[i],true);
				}else{
					jb1= new JRadioButton(str[i],false);
				}		
			panel1.add(jb1); 	
			rb[i]=jb1;
			bg.add(rb[i]);
			}
			panel1.setBorder(BorderFactory.createEtchedBorder());
			label.setText("输入要查找的内容：");
			panel2.add(label); 
			panel2.add(tf = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true));
			panel2.add(button = CommonComponent.buildButton("查询", null, null, null, null, null, null, true));
			
			rowData = new SaleQueryAction(this).getRowData();
			
			button.addMouseListener(new MouseAdapter(){			
				public void mouseClicked(MouseEvent e) {
					string = tf.getText();
					rowData = new SaleQueryAction(SaleQueryBasePanel.this).getRowData();
					System.out.println("事件端测试："+rowData);
					if((rowData==null||rowData.isEmpty())){
						JOptionPane.showMessageDialog(new JFrame(),"未找到相关数据!");
					}
					else{
					mytablemodel =new MyTableModel(rowData,title); 
					table.setModel(mytablemodel);
					}
				 }
			});
			
//			System.out.println(sqa.getRowData());
//			rowData = sqa.getRowData();

			uppanel.setLayout(new GridLayout(2,1));
			uppanel.add(panel1);
			uppanel.add(panel2);
			return uppanel;
		}	
			
		public JScrollPane initialTable(){
			title.add("明细编号");
			title.add("销售单编号");
			title.add("客户编号");
			title.add("销售日期");
			title.add("业务员编号");
			title.add("制表人");
			title.add("保管员");
			title.add("销售订单编号");
			title.add("商品编号");
			title.add("商品名称");
			title.add("商品拼音");
			title.add("销售数量");
			title.add("销售价格");
			title.add("库编号");
//			System.out.println("table端测试："+rowData);
			mytablemodel = new MyTableModel(rowData, title);
			table.setModel(mytablemodel);
			//table.setAutoCreateRowSorter(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tablepanel = new JScrollPane(table);			
			tablepanel.setViewportView(table);
			//tablepanel.add(sp);
			return tablepanel;
		}
		
			
	/**
	 * @测试使用
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("测试");		
		SaleQueryBasePanel sqp = new SaleQueryBasePanel();
		frame.getContentPane().add(sqp);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

