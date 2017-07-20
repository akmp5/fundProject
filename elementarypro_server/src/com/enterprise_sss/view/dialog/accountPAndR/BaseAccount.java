package com.enterprise_sss.view.dialog.accountPAndR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.action.accountPAndR.AccountPaidAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.ImagePanel;
import com.enterprise_sss.vo.TableVO;

public class BaseAccount extends JDialog {
	private String title,tableTitle1,tableTitle2;
	private TableVO tvo1,tvo2;
	private DefaultTableModel model1 =null;
	private DefaultTableModel model2 =null;
	private JTable table1,table2;
	private JPopupMenu jpp;
	private Vector<JMenuItem> jis;
	private Vector<JButton> buttons;
	
	public BaseAccount(){
		init();
	}
	
	/**
	 * Ӧ��/�����Dialog
	 * @param title	Dialog����
	 * @param tableTitle1	�м�������
	 * @param tvo1	�м�����
	 * @param tableTitle2	��������
	 * @param tvo2	������
	 */
	public BaseAccount(String title,String tableTitle1, TableVO tvo1,String tableTitle2, TableVO tvo2){
		this.title=title;
		this.tableTitle1=tableTitle1;
		this.tvo1=tvo1;
		this.tableTitle2=tableTitle2;
		this.tvo2=tvo2;
		init();
	}
	
	public void init() {
		initDialog();
	}
	
	public void initDialog() {
		this.setTitle(title);
		this.setSize(1000, 700);
		this.setLayout(new BorderLayout());
		this.add(getToolBar(),BorderLayout.NORTH);
		this.add(getCenterPanel(),BorderLayout.CENTER);
		this.add(getSouthPanel(),BorderLayout.SOUTH);
		this.setModal(true);//ֻ�ܲ�����ǰ����
		especialStyle();
		initAction();
		this.setVisible(true);

	}
	
	public JToolBar getToolBar(){
		buttons=new Vector<JButton>();
		buttons.add(CommonComponent.buildButton("��ѯȫ��", null, new ImageIcon("image/icon/find.png"), null, null, null, null, true));
		buttons.add(CommonComponent.buildButton("ɾ������", null, new ImageIcon("image/icon/delete.png"), null, null, null, null, true));
		buttons.get(1).setVisible(false);
		buttons.add(CommonComponent.buildButton("��ӿյ���", null, new ImageIcon("image/icon/add.png"), null, null, null, null, true));
		buttons.get(2).setVisible(false);
		buttons.add(CommonComponent.buildButton("�ύ����", null, new ImageIcon("image/icon/check.png"), null, null, null, null, true));
		buttons.get(3).setVisible(false);
		buttons.add(CommonComponent.buildButton("���ݹ���", null, new ImageIcon("image/icon/filter.png"), null, null, null, null, true));
		jpp=new JPopupMenu();
		jis=new Vector<JMenuItem>();
		
		jis.add(new JMenuItem("����ȼ�¼"));
		jis.add(new JMenuItem("�ϸ��¼�¼"));
		jis.add(new JMenuItem("���¼�¼"));
		for(int i=0;i<3;i++){
			jpp.add(jis.get(i));
		}
		buttons.add(CommonComponent.buildButton("����", null, new ImageIcon("image/icon/export1.png"), null, null, null, null, true));
		buttons.add(CommonComponent.buildButton("��ӡ", null, new ImageIcon("image/icon/printer.png"), null, null, null, null, true));
		buttons.add(CommonComponent.buildButton("�˳�", null, new ImageIcon("image/icon/close.png"), null, null, null, null, true));
		JToolBar tb=new JToolBar();
		ImagePanel imagePanel = new ImagePanel("image/jpg/toolbar.jpg");
		imagePanel.setLayout(new BorderLayout());
		tb.add(imagePanel);
		tb.setPreferredSize(new Dimension(600, 80));
		tb.setBackground(Color.BLUE);
		for(JButton b:buttons){
			tb.add(b);
		}
		
		return tb;
	}
	
	public JScrollPane getCenterPanel(){
		JScrollPane sp=new JScrollPane();
		sp.setBorder(BorderFactory.createTitledBorder(tableTitle1));
		model1=new DefaultTableModel(tvo1.getData(), tvo1.getTitle());
		table1=new JTable(model1){
//			public boolean isCellEditable(int row, int column){
//				return false;
//			}
		};
//		table1=CommonComponent.buildTable(tvo1.getData(), tvo1.getTitle());
		table1.setRowSelectionAllowed(true);
		// �����û��Ƿ�����϶���ͷ��������������С�
		table1.getTableHeader().setReorderingAllowed(false);
		sp.setViewportView(table1);
		return sp;
	}
	
	public JScrollPane getSouthPanel(){
		JScrollPane sp=new JScrollPane();
		sp.setBorder(BorderFactory.createTitledBorder(tableTitle2));
		model2=new DefaultTableModel(tvo2.getData(), tvo2.getTitle());
		table2=new JTable(model2);
		sp.setViewportView(table2);
//		sp.setViewportView(table2=CommonComponent.buildTable(tvo2.getData(), tvo2.getTitle()));
		return sp;
	}
	
	public void especialStyle(){
		
	}
	
	public void initAction(){
		
	}

	public Vector<JButton> getButtons() {
		return buttons;
	}

	public DefaultTableModel getModel1() {
		return model1;
	}

	public DefaultTableModel getModel2() {
		return model2;
	}

	public JTable getTable1() {
		return table1;
	}

	public JTable getTable2() {
		return table2;
	}

	public Vector<JMenuItem> getJis() {
		return jis;
	}

	public JPopupMenu getJpp() {
		return jpp;
	}
	
	
}
