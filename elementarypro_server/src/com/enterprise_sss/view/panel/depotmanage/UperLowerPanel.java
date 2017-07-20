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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.enterprise_sss.control.depotmanagecontrol.UpLowControl;
import com.enterprise_sss.dao.depotmanagedao.UpLowDao;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.MyTableModel;

public class UperLowerPanel extends TransDepotBasePanel {

	public JTextField tf1,tf2,tf3,tf4,tf5,tf6;
	public JButton button1,button2,button3,button4;
	public Vector title;
	public Vector rowData;
	public Vector textData;
	
	public UperLowerPanel(){
		uppanel.setBorder(BorderFactory.createTitledBorder("库存上下限设定："));
		this.add(CommonComponent.buildSplitPane(JSplitPane.VERTICAL_SPLIT, true, true, initialPanel(), initialTable()));
	}
	
	public JPanel initialPanel() {
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label = new JLabel();
		
		panel1.setLayout(new GridBagLayout());
		panel1.add(new JLabel("仓库编号:"),new GBC(0,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf1 = new JTextField(15),new GBC(1,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(new JLabel("货物编号:"),new GBC(2,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf2 = new JTextField(15),new GBC(3,0).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(new JLabel("上限数量:"),new GBC(0,1).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf3 = new JTextField(15),new GBC(1,1).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(new JLabel("下限数量:"),new GBC(2,1).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf4 = new JTextField(15),new GBC(3,1).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(new JLabel("最佳存量:"),new GBC(0,2).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf5 = new JTextField(15),new GBC(1,2).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(new JLabel("备注:"),new GBC(2,2).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		panel1.add(tf6 = new JTextField(15),new GBC(3,2).setAnchor(GBC.WEST).setFill(GBC.BOTH).setInset(15));
		
		panel2.add(label); 
		panel2.add(button1 = CommonComponent.buildButton("确认设定", null, null, null, null, null, null, true));
		panel2.add(button2 = CommonComponent.buildButton("查询上下限", null, null, null, null, null, null, true));
		panel2.add(button3 = CommonComponent.buildButton("修改上下限", null, null, null, null, null, null, true));
		panel2.add(button4 = CommonComponent.buildButton("删除上下限", null, null, null, null, null, null, true));
		panel2.setBorder(BorderFactory.createEtchedBorder());
		
		rowData = new UpLowDao().upLowSelect();
		button2.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				rowData =  new UpLowDao().upLowSelect();
				mytablemodel = new MyTableModel(rowData, title);
				table.setModel(mytablemodel);
			}
		});
		
		button1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(new UpLowControl(UperLowerPanel.this).checkTextData()){
					boolean flag = false;
					textData = new Vector();
					textData.add(tf1.getText());
					textData.add(tf2.getText());
					textData.add(tf3.getText());
					textData.add(tf4.getText());
					textData.add(tf5.getText());
					textData.add(tf6.getText());
					
					
					flag = new UpLowDao(textData).upLowInsert();
					if(flag == true){
						JOptionPane.showMessageDialog(new JFrame(),"上下限添加成功！");
					}
				}
			}
		});
		
		button3.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
								
				int[] rows;
				rows = table.getSelectedRows();
				for(int i = 0;i<rows.length;i++){
					Vector textData = new Vector();
					for(int j=0;j<table.getColumnCount();j++){
						textData.add(table.getValueAt(rows[i], j));
					}
					if(new UpLowDao(textData).upLowUpdate()){
						JOptionPane.showMessageDialog(null, "第"+i+"行"+"更新成功！");
					}
				}
				
			}
		});
		
		button4.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				int[] rows;
				rows = table.getSelectedRows();
				for(int i = 0;i<rows.length;i++){
					Vector textData = new Vector();
					textData.add(table.getValueAt(rows[i], 0));
					if(new UpLowDao(textData).upLowDelete()){
						JOptionPane.showMessageDialog(null, "第"+i+"行"+"删除成功！");
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
		title.add("对照编号");
		title.add("仓库编号");
		title.add("货物编号");
		title.add("上限数量");
		title.add("下限数量");
		title.add("最佳存量");
		title.add("备注");
		
		mytablemodel = new MyTableModel(rowData, title);
		table.setModel(mytablemodel);
		table.setAutoCreateRowSorter(true);
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
	UperLowerPanel td = new UperLowerPanel();
	frame.getContentPane().add(td);
	frame.setSize(600,500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
}
	/**
	 * @param args
	 */


}
