package com.enterprise_sss.view.dialog.salemanage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceButtonUI;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.BaseButtonShaper;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.skin.BusinessBlackSteelSkin;
import org.jvnet.substance.skin.BusinessBlueSteelSkin;
import org.jvnet.substance.skin.BusinessSkin;
import org.jvnet.substance.skin.ChallengerDeepSkin;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.skin.EbonyHighContrastSkin;
import org.jvnet.substance.skin.EmeraldDuskSkin;
import org.jvnet.substance.skin.FieldOfWheatSkin;
import org.jvnet.substance.skin.FindingNemoSkin;
import org.jvnet.substance.skin.GreenMagicSkin;
import org.jvnet.substance.skin.MagmaSkin;
import org.jvnet.substance.skin.MistAquaSkin;
import org.jvnet.substance.skin.MistSilverSkin;
import org.jvnet.substance.skin.NebulaBrickWallSkin;
import org.jvnet.substance.skin.OfficeBlue2007Skin;
import org.jvnet.substance.skin.OfficeSilver2007Skin;
import org.jvnet.substance.skin.RavenGraphiteGlassSkin;
import org.jvnet.substance.skin.RavenGraphiteSkin;
import org.jvnet.substance.skin.SaharaSkin;
import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceSkin;

import com.enterprise_sss.action.menu.DialogAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.go.calendar.JDatePicker;


public class OrderSaleDialog extends JDialog{
	protected JScrollPane sp = null;
	public JTable table = new JTable();
	protected JPanel uppanel = new JPanel();
	protected JPanel tablepanel = new JPanel();
	protected JPanel buttonpanel = new JPanel();
	public JTextField tf1,tf5,tf6;
	public JDatePicker tf2,tf3,tf4;
	protected DefaultTableModel model;
	
	public OrderSaleDialog(){
		
	}
	
	public OrderSaleDialog(String title){
		this.setTitle(title);
		this.setModal(true);
		initialDialog();
		this.setVisible(true);
	}
	
	public void initialDialog() {
		Container con = this.getContentPane();
		con.add(getPanel());
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public JPanel getPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		uppanel.setBorder(BorderFactory.createTitledBorder("订单信息"));
		tablepanel.setBorder(BorderFactory.createTitledBorder("订单明细"));
		buttonpanel.setBorder(BorderFactory.createEtchedBorder());
		
		panel.add(initialPanel(),BorderLayout.NORTH);
		panel.add(initialTable(),BorderLayout.CENTER);
		panel.add(initialButton(),BorderLayout.SOUTH);

		return panel;
	}
	
	public JPanel initialPanel() {

		uppanel.setLayout(new GridBagLayout());
		uppanel.add(CommonComponent.buildLabel("客户编号：", null, null, null, null, null),new GBC(0,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(tf1 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(CommonComponent.buildLabel("销售订单日期：", null, null, null, null, null),new GBC(2,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));	
		uppanel.add(tf2 = new JDatePicker(),new GBC(3,0).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(CommonComponent.buildLabel("有效起日：", null, null, null, null, null),new GBC(0,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));	
		uppanel.add(tf3 = new JDatePicker(),new GBC(1,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(CommonComponent.buildLabel("有效止日：", null, null, null, null, null),new GBC(2,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(tf4 = new JDatePicker(),new GBC(3,1).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(CommonComponent.buildLabel("业务员编号：", null, null, null, null, null),new GBC(0,2).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(tf5 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,2).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(CommonComponent.buildLabel("制单人：", null, null, null, null, null),new GBC(2,2).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		uppanel.add(tf6 = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(3,2).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));

		return uppanel;
	}	
		
	public JPanel initialTable(){
		
		Vector title = new Vector();
		title.add("货物编号");
		title.add("预定数量");
		title.add("销售价");
					
		model = new DefaultTableModel(title,18);
		table.setModel(model);
		//table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		sp = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(550,300));
		tablepanel.setLayout(new GridBagLayout());
		tablepanel.add(sp,new GBC(0,3).setFill(GBC.BOTH).setAnchor(GBC.CENTER).setInset(5));
//		tablepanel.setForeground(Color.yellow);
		tablepanel.setBackground(Color.black);
		return tablepanel;
	}
	
	public JPanel initialButton(){
		JButton subButton,exitButton,addButton,delButton;
		DialogAction da = new DialogAction(this);
		buttonpanel.setLayout(new GridBagLayout());
		buttonpanel.add(subButton = CommonComponent.buildButton("提交", null,null, Color.blue, null, null, null, true),new GBC(0,4).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		buttonpanel.add(exitButton = CommonComponent.buildButton("退出", null, null, Color.red, null, null, null, true),new GBC(3,4).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		buttonpanel.add(addButton = CommonComponent.buildButton("增加行", null, null, null, null, null, null, true),new GBC(1,4).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		buttonpanel.add(delButton = CommonComponent.buildButton("删除行", null, null, null, null, null, null, true),new GBC(2,4).setFill(GBC.BOTH).setAnchor(GBC.WEST).setInset(5));
		subButton.addActionListener(da);
		exitButton.addActionListener(da);
		addButton.addActionListener(da);
		delButton.addActionListener(da);
//		SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
//		SubstanceLookAndFeel.setSkin(new MistSilverSkin());
		return buttonpanel;
		
	}	
	

	public static void main(String[] args){
		OrderSaleDialog os = new OrderSaleDialog("销售订单");
		String d =DateFormat.getDateTimeInstance(1,2).format(System.currentTimeMillis());
		System.out.println(d);
	}
}
