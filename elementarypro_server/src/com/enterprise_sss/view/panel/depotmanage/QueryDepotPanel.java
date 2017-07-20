package com.enterprise_sss.view.panel.depotmanage;

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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.enterprise_sss.action.depotmanage.DepotQueryAction;
import com.enterprise_sss.action.salemanage.SaleQueryAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.MyTableModel;
import com.enterprise_sss.view.panel.salemanage.SaleQueryBasePanel;

public class QueryDepotPanel extends JPanel{

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
	
		public QueryDepotPanel(){
			
			this.setLayout(new BorderLayout());
			uppanel.setBorder(BorderFactory.createTitledBorder("����ѯ��"));
			tablepanel.setBorder(BorderFactory.createTitledBorder("��ѯ���"));
			this.add(CommonComponent.buildSplitPane(JSplitPane.VERTICAL_SPLIT, true, true, initialPanel(), initialTable()));
//			this.add(initialPanel(),BorderLayout.NORTH);
//			this.add(initialTable(),BorderLayout.CENTER);			
		}		
		
		private JPanel initialPanel() {
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			JLabel label = new JLabel();
			ButtonGroup bg = new ButtonGroup();
			
			String[] str = new String[]{"��ѯȫ��","�ֿ���","��Ʒ���"};			
			JRadioButton jb1=null;
			for(int i=0;i<3;i++){
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
			label.setText("����Ҫ���ҵ����ݣ�");
			panel2.add(label); 
			panel2.add(tf = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true));
			panel2.add(button = CommonComponent.buildButton("��ѯ", null, null, null, null, null, null, true));
			
			rowData = new DepotQueryAction(this).getRowData();
			
			button.addMouseListener(new MouseAdapter(){			
				public void mouseClicked(MouseEvent e) {
					string = tf.getText();
					rowData = new DepotQueryAction(QueryDepotPanel.this).getRowData();
					System.out.println("�¼��˲��ԣ�"+rowData);
					if((rowData==null||rowData.isEmpty())){
						JOptionPane.showMessageDialog(new JFrame(),"δ�ҵ��������!");
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
			
		private JScrollPane initialTable(){
			
			title.add("�ֿ���");
			title.add("�ֿ���");
			title.add("�ŵ���");
			title.add("��ע");
			title.add("�ŵ���");
			title.add("�ŵ��ַ");
			title.add("��ע");
			title.add("��Ʒ���");
			title.add("��Ʒ����");
			title.add("��Ʒ���");
			
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("����");		
		QueryDepotPanel osp = new QueryDepotPanel();
		frame.getContentPane().add(osp);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
