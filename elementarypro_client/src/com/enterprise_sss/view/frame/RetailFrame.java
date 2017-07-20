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
 * 零售界面
 * @author yiguo
 *---------------------
 *|工		具		栏|
 *|-------------------|	
 *|	单号标签面板	      |
 *|	销售单列表	      |
 *|	输入面板		      |
 *|-------------------|
 *|	状		态		栏|
 *---------------------
 */
public class RetailFrame extends JFrame {
	private RetailWindAction rwa = new RetailWindAction(this);//窗口事件监听
	private JLabel client,cashier,oper;//客户端店名，收银员，系统状态
	private UserVO userVO;//用户VO
	private JTable table1,table2;//销售回执单列表，商品清单列表
	private JLabel retailId,amount;//销售单号,总计金额
	private TextField commId;//货物编号
	private JScrollPane commsPane;
	private JTextField num;//购买数量
	private double sum=0;//实时销售额
	//班次结账,每日统计,调货申请,来货入库,挂单,解挂,付款,全清,打印,退出按钮
	private JButton reliefButt,dailyStatButt,moveApplyButt,validateButt,
		hangBillButt,freeBillButt,payButt,clearButt,printButt,exitButt,confirmButt;
	//商品基本信息与库存量集合,当日销售单，当日销售明细，回执单数据，
	//挂单集合,挂单明细（根椐下标一一对应挂单集合）
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
		this.setTitle("零售管理系统");
		// 设置窗体大小
		this.setSize(680, 550);
		// 设置窗体居中
		this.setLocationRelativeTo(null);
		// 设置窗体close方法
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		// 设置窗体初始化为最大化
		this.setExtendedState(this.MAXIMIZED_BOTH);
		// 添加窗体事件
		this.addWindowListener(rwa);
		this.setLayout(new BorderLayout());
		this.add(getToolBar(),BorderLayout.NORTH);
		this.add(getSplitPane(),BorderLayout.CENTER);
		this.add(getStatePanel(),BorderLayout.SOUTH);
		// 设置窗体显示
		this.setVisible(true);
	}
	
	/**
	 * 分割面板
	 * @return
	 */
	private JPanel getSplitPane(){
//		JSplitPane sp=CommonComponent.buildSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, true, getLeftPane(), getRightPane());
//		sp.setDividerLocation(880);//设置分隔条的位置。
		JPanel sp=new JPanel();
		sp.setLayout(new BorderLayout());
		sp.add(getLeftPane(),BorderLayout.CENTER);
//		sp.add(getRightPane(),BorderLayout.EAST);
		return sp;
	}
	
	/**
	 * 分割面板的左面板
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
	 * 左面板单号标签面板
	 * @return
	 */
	private JPanel getLabelPane(){
		JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(CommonComponent.buildLabel("单号：",null, null, null, null, null),BorderLayout.WEST);
		panel.add(retailId=CommonComponent.buildLabel("",null, Color.red, null, null, null));
		retailId.setText(new SimpleDateFormat("yyMMddHHmmss").format(new Date(System.currentTimeMillis())));
		return panel;
	}
	
	/**
	 * 左面板输入面板
	 * @return
	 */
	private JPanel getInputPane(){
		JPanel panel=new JPanel();
		panel.add(CommonComponent.buildLabel("商品编号：",null, null, null, null, null));
		commId=new TextField();
		commId.setFont(new Font("宋体",Font.BOLD,25));
		commId.setPreferredSize(new Dimension(200,35));
		panel.add(commId);
		panel.add(CommonComponent.buildLabel("数量：",null, null, null, null, null));
		panel.add(num=CommonComponent.buildTextField("txt", "", null, null, new Font("宋体",Font.BOLD,25), null, null, true, true));
		panel.add(CommonComponent.buildLabel("总计金额：",null, null, null, null, null));
		panel.add(amount=CommonComponent.buildLabel("0",null, Color.red, new Font("宋体",Font.BOLD,30), null, null));
		panel.add(confirmButt=CommonComponent.buildButton("确定", null, null, null, null, null, null, true));
		
		return panel;
	}
	
	/**
	 * 左中心面板
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
	 * 左面板销售单列表面板
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
//	 * 左面板商品清单列表面板
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
//	 * 分割面板的右面板(快捷菜单列表)
//	 * @return
//	 */
//	private JScrollPane getRightPane(){
//		JScrollPane sp=new JScrollPane();
//		Vector data=new Vector();
//		Vector<String> title=new Vector<String>();
//		title.add("功能");
//		title.add("快捷键");
//		DefaultTableModel model=new DefaultTableModel(data,title);
//		table2=new JTable(model);
//		table2.setGridColor(Color.yellow);
//		sp.setViewportView(table2);
//		sp.setSize(0, 500);
//		return sp;
//	}
	
	/**
	 * 工具栏
	 * @return
	 */
	private JToolBar getToolBar(){
		Vector<Component> tbb=new Vector<Component>();
		tbb.add(reliefButt=CommonComponent.buildButton("班次结账", null, null, null, new Font("宋体",Font.BOLD,15), null, null, true));
		tbb.add(dailyStatButt=CommonComponent.buildButton("每日统计", null, null, null, new Font("宋体",Font.BOLD,15), null, null, true));
		tbb.add(moveApplyButt=CommonComponent.buildButton("调货申请", null, null, null, new Font("宋体",Font.BOLD,15), null, null, true));
		tbb.add(validateButt=CommonComponent.buildButton("来货入库", null, null, null, new Font("宋体",Font.BOLD,15), null, null, true));
		tbb.add(hangBillButt=CommonComponent.buildButton("挂单", null, null, null, new Font("宋体",Font.BOLD,15), null, null, true));
		tbb.add(freeBillButt=CommonComponent.buildButton("解挂", null, null, null, new Font("宋体",Font.BOLD,15), null, null, true));
		tbb.add(clearButt=CommonComponent.buildButton("全清", null, null, null, new Font("宋体",Font.BOLD,15), null, null, true));
		tbb.add(payButt=CommonComponent.buildButton("付款", null, null, null, new Font("宋体",Font.BOLD,15), null, null, true));
		tbb.add(printButt=CommonComponent.buildButton("打印", null, null, null, new Font("宋体",Font.BOLD,15), null, null, true));
		tbb.add(exitButt=CommonComponent.buildButton("退出", null, null, null, new Font("宋体",Font.BOLD,15), null, null, true));
		JToolBar tb=CommonComponent.buildToolBar(tbb, Color.orange, null);
		return tb;
	}
	
	/**
	 * 状态栏
	 * @return
	 */
	private JPanel getStatePanel(){
		JPanel state=new JPanel();
		state.setLayout(new GridBagLayout());
		state.add(CommonComponent.buildLabel("客户端：", null, Color.blue, null, null, null),new GBC(0,0));
		state.add(client=CommonComponent.buildLabel("", null, Color.blue, null, null, null),new GBC(1,0).setInset(5, 5, 5, 150));
		state.add(CommonComponent.buildLabel("收银员：", null, Color.blue, null, null, null),new GBC(2,0));
		state.add(cashier=CommonComponent.buildLabel("", null, Color.blue, null, null, null),new GBC(3,0).setInset(5, 5, 5, 150));
		state.add(CommonComponent.buildLabel("系统状态：", null, Color.blue, null, null, null),new GBC(4,0));
		state.add(oper=CommonComponent.buildLabel("", null, Color.blue, null, null, null),new GBC(5,0).setInset(5, 5, 5, 150));
		state.add(new TimeLabel());//系统当前日期时间标签
		return state;
	}

	private void initAction() {
		RetailAction ra=new RetailAction(this);//操作事件监听
		RetailKeyAction rka=new RetailKeyAction(this);//键盘事件监听
//		RetailTextAction rta=new RetailTextAction(this);//文本框事件监听
		
		//为窗体添加键盘事件
		this.addKeyListener(rka);
		
		//为按钮添加键盘事件
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
		
		
//		//为货物编号文本框添加文本事件
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
		vo.setUser("易果");
		Vector comms=new Vector();
		Vector row=new Vector();
		row.add(11);
		row.add("11111");
		row.add("可口可乐");
		row.add("kekoukele");
		row.add("350ml");
		row.add("瓶");
		row.add("湖南");
		row.add("饮料");
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
		row.add("青岛啤酒");
		row.add("kekoukele");
		row.add("350ml");
		row.add("瓶");
		row.add("湖南");
		row.add("饮料");
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
		row.add("旺旺雪饼");
		row.add("kekoukele");
		row.add("350ml");
		row.add("瓶");
		row.add("湖南");
		row.add("饮料");
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
