package com.enterprise_sss.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceButtonUI;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.skin.FieldOfWheatSkin;
import org.jvnet.substance.skin.MangoSkin;
import org.jvnet.substance.skin.MistAquaSkin;
import org.jvnet.substance.skin.ModerateSkin;
import org.jvnet.substance.skin.NebulaBrickWallSkin;
import org.jvnet.substance.skin.NebulaSkin;
import org.jvnet.substance.skin.OfficeBlue2007Skin;
import org.jvnet.substance.skin.OfficeSilver2007Skin;
import org.jvnet.substance.skin.SaharaSkin;
import org.jvnet.substance.skin.SubstanceAbstractSkin;

import com.enterprise_sss.action.RetailAction;
import com.enterprise_sss.action.RetailKeyAction;
import com.enterprise_sss.action.RetailTextAction;
import com.enterprise_sss.action.RetailWindAction;
import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.TimeLabel;
import com.enterprise_sss.vo.UserVO;

/**
 * ���۽���
 * @author yiguo
 *---------------------
 *|��		��		��|
 *|-------------------|	
 *|	���ű�ǩ���	      |
 *|	���۵��б�	      |
 *|	�������		      |
 *|-------------------|
 *|	״		̬		��|
 *---------------------
 */
public class RetailFrame extends JFrame {
	private RetailWindAction rwa = new RetailWindAction(this);//�����¼�����
	private JLabel client,cashier,oper;//�ͻ��˵���������Ա��ϵͳ״̬
	private UserVO userVO;//�û�VO
	private JTable table1,table2;//���ۻ�ִ���б���Ʒ�嵥�б�
	private JLabel retailId,amount;//���۵���,�ܼƽ��
	private TextField commId;//������
	private JScrollPane commsPane;
	private JTextField num;//��������
	private double sum=0;//ʵʱ���۶�
	//��ν���,ÿ��ͳ��,��������,�������,�ҵ�,���,����,ȫ��,��ӡ,�˳���ť
	private JButton reliefButt,dailyStatButt,moveApplyButt,validateButt,
		hangBillButt,freeBillButt,payButt,clearButt,printButt,exitButt,confirmButt;
	//��Ʒ������Ϣ����������,�������۵�������������ϸ����ִ�����ݣ�
	//�ҵ�����,�ҵ���ϸ������±�һһ��Ӧ�ҵ����ϣ�
	private Vector comms,saleBill=new Vector(),saleItems=new Vector(),
		data=new Vector(),hangBill=new Vector(),hangItems=new Vector();
	

	public RetailFrame(UserVO userVO,Vector comms){
		this.userVO=userVO;
		this.comms=comms;
		init();
	}
	
	private void init() {
		initFrame();
		initAction();
	}

	private void initFrame() {
		SubstanceLookAndFeel.setSkin(new FieldOfWheatSkin());
		this.setTitle("���۹���ϵͳ");
		// ���ô����С
		this.setSize(680, 550);
		// ���ô������
		this.setLocationRelativeTo(null);
		// ���ô���close����
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		// ���ô����ʼ��Ϊ���
		this.setExtendedState(this.MAXIMIZED_BOTH);
		// ��Ӵ����¼�
		this.addWindowListener(rwa);
		this.setLayout(new BorderLayout());
		this.add(getToolBar(),BorderLayout.NORTH);
		this.add(getSplitPane(),BorderLayout.CENTER);
		this.add(getStatePanel(),BorderLayout.SOUTH);
		// ���ô�����ʾ
		this.setVisible(true);
	}
	
	/**
	 * �ָ����
	 * @return
	 */
	private JPanel getSplitPane(){
//		JSplitPane sp=CommonComponent.buildSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, true, getLeftPane(), getRightPane());
//		sp.setDividerLocation(880);//���÷ָ�����λ�á�
		JPanel sp=new JPanel();
		sp.setLayout(new BorderLayout());
		sp.add(getLeftPane(),BorderLayout.CENTER);
//		sp.add(getRightPane(),BorderLayout.EAST);
		return sp;
	}
	
	/**
	 * �ָ����������
	 * @return
	 */
	private JPanel getLeftPane(){
		JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(getLabelPane(),BorderLayout.NORTH);
		panel.add(getInputPane(),BorderLayout.SOUTH);
		panel.add(getCenterPane());
		return panel;
	}
	
	/**
	 * ����嵥�ű�ǩ���
	 * @return
	 */
	private JPanel getLabelPane(){
		JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(CommonComponent.buildLabel("���ţ�",null, null, null, null, null),BorderLayout.WEST);
		panel.add(retailId=CommonComponent.buildLabel("",null, Color.red, null, null, null));
		retailId.setText(new SimpleDateFormat("yyMMddHHmmss").format(new Date(System.currentTimeMillis())));
		return panel;
	}
	
	/**
	 * ������������
	 * @return
	 */
	private JPanel getInputPane(){
		JPanel panel=new JPanel();
		panel.add(CommonComponent.buildLabel("��Ʒ��ţ�",null, null, null, null, null));
		commId=new TextField();
		commId.setFont(new Font("����",Font.BOLD,25));
		commId.setPreferredSize(new Dimension(200,35));
		panel.add(commId);
		panel.add(CommonComponent.buildLabel("������",null, null, null, null, null));
		panel.add(num=CommonComponent.buildTextField("txt", "", null, null, new Font("����",Font.BOLD,25), null, null, true, true));
		panel.add(CommonComponent.buildLabel("�ܼƽ�",null, null, null, null, null));
		panel.add(amount=CommonComponent.buildLabel("0",null, Color.red, new Font("����",Font.BOLD,30), null, null));
		panel.add(confirmButt=CommonComponent.buildButton("ȷ��", null, null, null, null, null, null, true));
		
		return panel;
	}
	
	/**
	 * ���������
	 * @return
	 */
	public JPanel getCenterPane(){
		JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(getScrollPane());
//		panel.add(getCommsPane(),BorderLayout.SOUTH);
//		commsPane.setVisible(false);
		return panel;
	}
	

	/**
	 * ��������۵��б����
	 * @return
	 */
	private JScrollPane getScrollPane(){
		JScrollPane sp=new JScrollPane();
		Vector data=new Vector();
		DefaultTableModel model=new DefaultTableModel(data,new RetailServer().getReceiptTitle());
		table1=new JTable(model);
		table1.setGridColor(Color.yellow);
		sp.setViewportView(table1);
		return sp;
	}
	
//	/**
//	 * �������Ʒ�嵥�б����
//	 * @return
//	 */
//	public JScrollPane getCommsPane(){
//		commsPane=new JScrollPane();
//		Vector data=new Vector();
//		DefaultTableModel model=new DefaultTableModel(new Vector(),new RetailServer().getApplyTitle());
//		table2=new JTable(model);
//		table2.setGridColor(Color.yellow);
//		commsPane.setViewportView(table2);
//		return commsPane;
//	}
	
//	/**
//	 * �ָ����������(��ݲ˵��б�)
//	 * @return
//	 */
//	private JScrollPane getRightPane(){
//		JScrollPane sp=new JScrollPane();
//		Vector data=new Vector();
//		Vector<String> title=new Vector<String>();
//		title.add("����");
//		title.add("��ݼ�");
//		DefaultTableModel model=new DefaultTableModel(data,title);
//		table2=new JTable(model);
//		table2.setGridColor(Color.yellow);
//		sp.setViewportView(table2);
//		sp.setSize(0, 500);
//		return sp;
//	}
	
	/**
	 * ������
	 * @return
	 */
	private JToolBar getToolBar(){
		Vector<Component> tbb=new Vector<Component>();
		tbb.add(reliefButt=CommonComponent.buildButton("��ν���", null, null, null, new Font("����",Font.BOLD,15), null, null, true));
		tbb.add(dailyStatButt=CommonComponent.buildButton("ÿ��ͳ��", null, null, null, new Font("����",Font.BOLD,15), null, null, true));
		tbb.add(moveApplyButt=CommonComponent.buildButton("��������", null, null, null, new Font("����",Font.BOLD,15), null, null, true));
		tbb.add(validateButt=CommonComponent.buildButton("�������", null, null, null, new Font("����",Font.BOLD,15), null, null, true));
		tbb.add(hangBillButt=CommonComponent.buildButton("�ҵ�", null, null, null, new Font("����",Font.BOLD,15), null, null, true));
		tbb.add(freeBillButt=CommonComponent.buildButton("���", null, null, null, new Font("����",Font.BOLD,15), null, null, true));
		tbb.add(clearButt=CommonComponent.buildButton("ȫ��", null, null, null, new Font("����",Font.BOLD,15), null, null, true));
		tbb.add(payButt=CommonComponent.buildButton("����", null, null, null, new Font("����",Font.BOLD,15), null, null, true));
		tbb.add(printButt=CommonComponent.buildButton("��ӡ", null, null, null, new Font("����",Font.BOLD,15), null, null, true));
		tbb.add(exitButt=CommonComponent.buildButton("�˳�", null, null, null, new Font("����",Font.BOLD,15), null, null, true));
		JToolBar tb=CommonComponent.buildToolBar(tbb, Color.orange, null);
		return tb;
	}
	
	/**
	 * ״̬��
	 * @return
	 */
	private JPanel getStatePanel(){
		JPanel state=new JPanel();
		state.setLayout(new GridBagLayout());
		state.add(CommonComponent.buildLabel("�ͻ��ˣ�", null, Color.blue, null, null, null),new GBC(0,0));
		state.add(client=CommonComponent.buildLabel("", null, Color.blue, null, null, null),new GBC(1,0).setInset(5, 5, 5, 150));
		state.add(CommonComponent.buildLabel("����Ա��", null, Color.blue, null, null, null),new GBC(2,0));
		state.add(cashier=CommonComponent.buildLabel("", null, Color.blue, null, null, null),new GBC(3,0).setInset(5, 5, 5, 150));
		state.add(CommonComponent.buildLabel("ϵͳ״̬��", null, Color.blue, null, null, null),new GBC(4,0));
		state.add(oper=CommonComponent.buildLabel("", null, Color.blue, null, null, null),new GBC(5,0).setInset(5, 5, 5, 150));
		state.add(new TimeLabel());//ϵͳ��ǰ����ʱ���ǩ
		return state;
	}

	private void initAction() {
		RetailAction ra=new RetailAction(this);//�����¼�����
		RetailKeyAction rka=new RetailKeyAction(this);//�����¼�����
//		RetailTextAction rta=new RetailTextAction(this);//�ı����¼�����
		
		//Ϊ������Ӽ����¼�
		this.addKeyListener(rka);
		
		//Ϊ��ť��Ӽ����¼�
		confirmButt.addActionListener(ra);
		reliefButt.addActionListener(ra);
		dailyStatButt.addActionListener(ra);
		moveApplyButt.addActionListener(ra);
		validateButt.addActionListener(ra);
		hangBillButt.addActionListener(ra);
		freeBillButt.addActionListener(ra);
		clearButt.addActionListener(ra);
		payButt.addActionListener(ra);
		printButt.addActionListener(ra);
		exitButt.addActionListener(ra);
		
		
//		//Ϊ�������ı�������ı��¼�
//		commId.addTextListener(rta);
	}


	public JLabel getClient() {
		return client;
	}

	public JLabel getCashier() {
		return cashier;
	}

	public JLabel getOper() {
		return oper;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public JLabel getRetailId() {
		return retailId;
	}

	public void setRetailId(JLabel retailId) {
		this.retailId = retailId;
	}

	public JLabel getAmount() {
		return amount;
	}

	public void setAmount(JLabel amount) {
		this.amount = amount;
	}

	public Vector getComms() {
		return comms;
	}

	public JTable getTable1() {
		return table1;
	}

	public JTable getTable2() {
		return table2;
	}
	
	public TextField getCommId() {
		return commId;
	}

	public JTextField getNum() {
		return num;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public Vector getSaleBill() {
		return saleBill;
	}

	public Vector getSaleItems() {
		return saleItems;
	}

	public Vector getData() {
		return data;
	}

	public void setData(Vector data) {
		this.data = data;
	}
	
	public Vector getHangBill() {
		return hangBill;
	}

	public Vector getHangItems() {
		return hangItems;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserVO vo=new UserVO();
		vo.setUser("�׹�");
		Vector comms=new Vector();
		Vector row=new Vector();
		row.add(11);
		row.add("11111");
		row.add("�ɿڿ���");
		row.add("kekoukele");
		row.add("350ml");
		row.add("ƿ");
		row.add("����");
		row.add("����");
		row.add(2.5);
		row.add(3.5);
		row.add(3.2);
		row.add(2.8);
		row.add(500);
		row.add(2000);
		row.add(1);
		comms.add(row);
		row=new Vector();
		row.add(2);
		row.add("11111");
		row.add("�ൺơ��");
		row.add("kekoukele");
		row.add("350ml");
		row.add("ƿ");
		row.add("����");
		row.add("����");
		row.add(2.5);
		row.add(3.5);
		row.add(3.2);
		row.add(2.8);
		row.add(500);
		row.add(2000);
		row.add(1);
		comms.add(row);
		row=new Vector();
		row.add(3);
		row.add("11111");
		row.add("����ѩ��");
		row.add("kekoukele");
		row.add("350ml");
		row.add("ƿ");
		row.add("����");
		row.add("����");
		row.add(2.5);
		row.add(3.5);
		row.add(3.2);
		row.add(2.8);
		row.add(500);
		row.add(2000);
		row.add(1);
		comms.add(row);
		new RetailFrame(vo,comms);
	}

}
