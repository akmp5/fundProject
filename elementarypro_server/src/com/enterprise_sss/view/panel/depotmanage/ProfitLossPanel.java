package com.enterprise_sss.view.panel.depotmanage;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
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

import com.enterprise_sss.control.depotmanagecontrol.ProfitLossControl;
import com.enterprise_sss.dao.depotmanagedao.ProfitLossDao;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.MyTableModel;
import com.go.calendar.JDatePicker;

public class ProfitLossPanel extends TransDepotBasePanel {

	public JTextField tf1,tf2,tf3,tf4,tf6,tf7;
	public JDatePicker tf5;
	public JButton button1,button2;
	public Vector title;
	public Vector rowData;
	public Vector textData;
	
	public ProfitLossPanel(){
		uppanel.setBorder(BorderFactory.createTitledBorder("输入相关损溢信息："));
		this.add(CommonComponent.buildSplitPane(JSplitPane.VERTICAL_SPLIT, true, true, initialPanel(), initialTable()));
	}
	public JPanel initialPanel() {
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label = new JLabel();
		
		panel1.setLayout(new GridBagLayout());
		panel1.add(new JLabel("商品编号:"),new GBC(0,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf1 = new JTextField(15),new GBC(1,0).setAnchor(GBC.WEST).setFill(GBC.CENTER).setInset(15));
		panel1.add(new JLabel("仓库编号:"),new GBC(2,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf2 = new JTextField(15),new GBC(3,0).setAnchor(GBC.WEST).setFill(GBC.CENTER).setInset(15));
		panel1.add(label = new JLabel("货物数量:"),new GBC(0,1).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf3 = new JTextField(15),new GBC(1,1).setAnchor(GBC.WEST).setFill(GBC.CENTER).setInset(15));
		panel1.add(new JLabel("货物金额:"),new GBC(2,1).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf4 = new JTextField(15),new GBC(3,1).setAnchor(GBC.WEST).setFill(GBC.CENTER).setInset(15));
		panel1.add(new JLabel("制单日期:"),new GBC(0,2).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf5 = new JDatePicker(),new GBC(1,2).setAnchor(GBC.WEST).setFill(GBC.CENTER).setInset(15));
		panel1.add(new JLabel("责任人:"),new GBC(2,2).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf6 = new JTextField(15),new GBC(3,2).setAnchor(GBC.WEST).setFill(GBC.CENTER).setInset(15));
		panel1.add(new JLabel("制单人:"),new GBC(0,3).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf7 = new JTextField(15),new GBC(1,3).setAnchor(GBC.WEST).setFill(GBC.CENTER).setInset(15));
		
		label.setToolTipText("[损(负)溢(正)]");
		panel2.add(button1 = CommonComponent.buildButton("确认填单", null, null, null, null, null, null, true));
		panel2.add(button2 = CommonComponent.buildButton("查询损溢单", null, null, null, null, null, null, true));
		panel2.setBorder(BorderFactory.createEtchedBorder());
		rowData = new ProfitLossDao().proLossSelect();
		button2.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				rowData = new ProfitLossDao().proLossSelect();
				mytablemodel = new MyTableModel(rowData, title);
				table.setModel(mytablemodel);
			}
		});
		
		button1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(new ProfitLossControl(ProfitLossPanel.this).checkTextData()){
					boolean flag = false;
					textData = new Vector();
					textData.add(tf1.getText());
					textData.add(tf2.getText());
					textData.add(tf3.getText());
					textData.add(tf4.getText());
					try {
						textData.add(new java.sql.Date(tf5.getSelectedDate().getTime()));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textData.add(tf6.getText());
					textData.add(tf7.getText());
					
					flag = new ProfitLossDao(textData).profitLossInsert();
					if(flag == true){
						JOptionPane.showMessageDialog(new JFrame(),"损溢单添加成功！");
					}
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
		title.add("报损报溢编号");
		title.add("商品编号");
		title.add("仓库编号");
		title.add("货物数量");
		title.add("货物金额");
		title.add("制单日期");
		title.add("责任人");
		title.add("制单人");
		
		mytablemodel = new MyTableModel(rowData, title);
		table.setModel(mytablemodel);
//		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);		
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
		ProfitLossPanel td = new ProfitLossPanel();
		frame.getContentPane().add(td);
		frame.setSize(600,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
