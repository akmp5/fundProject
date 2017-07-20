package com.enterprise_sss.view.panel.depotmanage;

	
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.ParseException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.action.depotmanage.TransDepotAction;
import com.enterprise_sss.action.depotmanage.TransDepotAction1;
import com.enterprise_sss.dao.depotmanagedao.DepotManageDao;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.MyTableModel;
import com.go.calendar.JDatePicker;

	public class TransDepotBasePanel extends JPanel{

			protected JTable table = new JTable();
			protected JPanel uppanel = new JPanel();
			protected JScrollPane tablepanel = new JScrollPane();
			protected JSplitPane spp =null;
			public JTextField tf1,tf2,tf3,tf5,tf6;
			public JDatePicker tf4;
			protected MyTableModel mytablemodel;
			protected Vector title = new Vector();
			private Vector title1 = new Vector();
			public Vector rowData = new Vector();
			public Vector textData = new Vector();
			protected JScrollPane sp;
			
			public TransDepotBasePanel(){
				
				this.setLayout(new BorderLayout());
				uppanel.setBorder(BorderFactory.createTitledBorder("输入调库信息："));
				tablepanel.setBorder(BorderFactory.createTitledBorder("查询结果"));
				this.add(CommonComponent.buildSplitPane(JSplitPane.VERTICAL_SPLIT, true, true, initialPanel(), initialTable()));
//				this.add(initialPanel(),BorderLayout.NORTH);
//				this.add(initialTable(),BorderLayout.CENTER);			
			}		
			
			public JPanel initialPanel() {
				JPanel panel1 = new JPanel();
				JPanel panel2 = new JPanel();
				JLabel label = new JLabel();
				JButton subButton,QueryButton,QueryButton1;
				final JButton subButton1;
				
				
				panel1.setLayout(new GridBagLayout());
				panel1.add(new JLabel("门店编号:"),new GBC(0,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
				panel1.add(tf1 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,0).setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
				panel1.add(new JLabel("商品编号:"),new GBC(2,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
				panel1.add(tf2 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(3,0).setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
				panel1.add(new JLabel("调货数量:"),new GBC(0,1).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
				panel1.add(tf3 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,1).setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
				panel1.add(new JLabel("日期:"),new GBC(2,1).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
				panel1.add(tf4 = new JDatePicker(),new GBC(3,1).setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
				panel1.add(new JLabel("调拨状态:"),new GBC(0,2).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
				panel1.add(tf5 = CommonComponent.buildTextField("txt", "已调拨", null, null, null, null, null, false, true),new GBC(1,2).setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
				panel1.add(new JLabel("制单人:"),new GBC(2,2).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
				panel1.add(tf6 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(3,2).setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));

				rowData = new DepotManageDao().selectTrans();
//				System.out.println(rowData);
				panel2.add(label); 
				panel2.add(subButton = CommonComponent.buildButton("确认调货", null, null, null, null, null, null, true));
				panel2.add(QueryButton = CommonComponent.buildButton("查询调货", null, null, null, null, null, null, true));
				panel2.add(QueryButton1 = CommonComponent.buildButton("查询调货申请", null, null, null, null, null, null, true));
				panel2.add(subButton1 = CommonComponent.buildButton("由申请调货", null, null, null, null, null, null, false));
				panel2.setBorder(BorderFactory.createEtchedBorder());
				
				QueryButton.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
						subButton1.setEnabled(false);
						rowData = new DepotManageDao().selectTrans();
						mytablemodel = new MyTableModel(rowData, title);
						table.setModel(mytablemodel);
					}		
				});
				
				QueryButton1.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
						subButton1.setEnabled(true);
						rowData = new DepotManageDao().selectApply();
						mytablemodel = new MyTableModel(rowData, title1);
						table.setModel(mytablemodel);
					}		
				});
								
				subButton1.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(!tf6.getText().trim().isEmpty()){
							try{
								int row = table.getSelectedRow();
								rowData = new Vector();							
								rowData.add(table.getValueAt(row, 1).toString());				
								rowData.add(table.getValueAt(row, 2).toString());
								rowData.add(table.getValueAt(row, 3).toString());
								try {
									rowData.add(new java.sql.Date(tf4.getSelectedDate().getTime()));
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								rowData.add(tf5.getText());
								rowData.add(tf6.getText());
//								System.out.println(rowData.get(0)+" "+ rowData.get(1)+"  "+rowData.get(2) +" "+rowData.get(3) +" "+ rowData.get(4)+" "+rowData.get(5));
								new TransDepotAction1(rowData).transDepot();
								String string;
								string = table.getValueAt(row, 0).toString();
								if(!new DepotManageDao(string).applyUpdate()){					
									JOptionPane.showMessageDialog(null, "调货失败！");
								}
							}catch(Exception e2){
								JOptionPane.showMessageDialog(null, "请选择要调货的一行记录！");
							}
							}
							else 
								JOptionPane.showMessageDialog(null,"制单人不能为空！");
					}
					
				});
				
				
				TransDepotAction tda = new TransDepotAction(this);			
					subButton.addActionListener(tda);
			
				
				uppanel.setLayout(new BorderLayout());
				uppanel.add(panel1,BorderLayout.NORTH);
				uppanel.add(panel2,BorderLayout.CENTER);
				return uppanel;
			}	
				
			public JScrollPane initialTable(){
								
				title.add("调拨单编号");
				title.add("门店编号");
				title.add("商品编号");
				title.add("调货数量");
				title.add("日期");
				title.add("调拨状态");
				title.add("制单人");
				
				title1.add("申请编号");
				title1.add("门店编号");
				title1.add("货物编号");
				title1.add("申请数量");
				title1.add("日期");
				title1.add("申请人");
				title1.add("申请说明");
				title1.add("回复状态");
				
				mytablemodel = new MyTableModel(rowData, title);
				table.setModel(mytablemodel);
				table.setAutoCreateRowSorter(true);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tablepanel = new JScrollPane(table);
				tablepanel.setViewportView(table);
				return tablepanel;
			}
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			JFrame frame = new JFrame("测试");		
			TransDepotBasePanel td = new TransDepotBasePanel();
			frame.getContentPane().add(td);
			frame.setSize(600,500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}

}
