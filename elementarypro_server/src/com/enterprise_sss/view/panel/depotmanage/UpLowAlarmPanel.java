package com.enterprise_sss.view.panel.depotmanage;

import java.awt.BorderLayout;
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
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.dao.depotmanagedao.UpLowAlarmDao;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.MyTableModel;

public class UpLowAlarmPanel extends TransDepotBasePanel {

	private JButton button;
	private Vector title; 
	private MyTableModel myTableModel;
	
	public UpLowAlarmPanel(){
		uppanel.setBorder(BorderFactory.createTitledBorder("库存上下限报警："));
		this.add(CommonComponent.buildSplitPane(JSplitPane.VERTICAL_SPLIT, true, true, initialPanel(), initialTable()));
	}
	/**
	 * @param args
	 */
	public JPanel initialPanel() {
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label = new JLabel();
		
		
		panel2.add(button = CommonComponent.buildButton("生成报警", null, null, null, null, null, null, true));
		panel2.setBorder(BorderFactory.createEtchedBorder());
		button.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				rowData = new UpLowAlarmDao().alarmSelect();
//				if(rowData!= null){
//					System.out.println(rowData.get(0)+" "+rowData.get(1)+" "+rowData.get(2)+" "+rowData.get(3));
					myTableModel = new MyTableModel(rowData,title);
					table.setModel(myTableModel);
					
				
			}
		});
		
		uppanel.setLayout(new BorderLayout());
		uppanel.add(panel2,BorderLayout.CENTER);
		return uppanel;
	}	
		
	public JScrollPane initialTable(){
		
		title = new Vector();		
		title.add("仓库编号");
		title.add("货物编号");
		title.add("库存数量");
		title.add("上限数量");
		title.add("下限数量");
		title.add("最佳存量");
		title.add("报警状态");
		
		DefaultTableModel model = new DefaultTableModel(title,0);
		table.setModel(model);
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
	UpLowAlarmPanel td = new UpLowAlarmPanel();
	frame.getContentPane().add(td);
	frame.setSize(600,500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
}

}
