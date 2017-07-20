package com.enterprise_sss.view.panel.depotmanage;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.dao.depotmanagedao.CheckDepotDao;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.MyTableModel;

public class CheckDepotPanel extends TransDepotBasePanel {

	public JTextField tf1,tf2;
	private Vector rowData;
	private Vector title = new Vector();
	private JTable table = new JTable();
	int amount;
	private JButton button;
	public MyTableModel myTableModel = new MyTableModel();
	
	public CheckDepotPanel(){
		uppanel.setBorder(BorderFactory.createTitledBorder("输入要盘点的商品信息："));
		this.add(CommonComponent.buildSplitPane(JSplitPane.VERTICAL_SPLIT, true, true, initialPanel(), initialTable()));
	}
	
	public JPanel initialPanel(){
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label = new JLabel();
		
		panel1.setLayout(new GridBagLayout());
		panel1.add(new JLabel("商品编号:"),new GBC(0,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf1 = new JTextField(15),new GBC(1,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(new JLabel("商品数量:"),new GBC(2,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf2 = new JTextField(15),new GBC(3,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		
		
		panel2.add(label); 
		panel2.add(button = CommonComponent.buildButton("盘盈盘亏", null, null, null, null, null, null, true));
		panel2.setBorder(BorderFactory.createEtchedBorder());
		
		button.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e) {
				 if(new CheckDepotDao(tf1.getText()).checkComm()){
					 amount = new CheckDepotDao(tf1.getText()).getCommAmount();
					 rowData = new Vector();
					 Vector row = new Vector();
					 row.add(tf1.getText());
					 row.add(amount);System.out.println(Integer.parseInt(tf1.getText()));
					 row.add(amount-Integer.parseInt(tf2.getText()));
					 rowData.add(row);
					 System.out.println(title.get(0)+" "+title.get(1)+" "+title.get(2));
					 myTableModel = new MyTableModel(rowData,title);
					 table.setModel(myTableModel);
				 }
			 }
		});
		
		uppanel.setLayout(new BorderLayout());
		uppanel.add(panel1,BorderLayout.NORTH);
		uppanel.add(panel2,BorderLayout.CENTER);
		return uppanel;
		
		
	}
	
	public JScrollPane initialTable(){
		title = new Vector();	
		title.add("库存商品编号");
		title.add("库存商品数量");
		title.add("盈亏状况");
		
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel(title,1);
		table.setModel(model);
		//table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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
		CheckDepotPanel td = new CheckDepotPanel();
		frame.getContentPane().add(td);
		frame.setSize(600,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
