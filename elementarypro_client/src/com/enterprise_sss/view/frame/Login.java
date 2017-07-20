package com.enterprise_sss.view.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.FieldOfWheatSkin;
import org.jvnet.substance.skin.MistAquaSkin;

import com.enterprise_sss.action.LoginAction;
import com.enterprise_sss.action.LoginWindAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.ImagePanel;

/**
 * 登录界面
 * 
 * @author Wang ming 2009-10-12
 * @version 1.0
 * 
 */
public class Login extends JFrame {

	private JButton refer, cancel;

	private LoginWindAction action = new LoginWindAction(this);

	private JPanel cenJPanel; // 中心面板

	private JTextField userText, pwText; // 用户名文本框

	private JComboBox ComboBox; // 登录方式

	/**
	 * 构造函数
	 * 
	 */
	public Login() {
		// 设置皮肤方式
		SubstanceLookAndFeel.setSkin(new MistAquaSkin());
		init();
		initAction();
	}

	/**
	 * 初始化整个界面
	 * 
	 */
	public void init() {
		SubstanceLookAndFeel.setSkin(new FieldOfWheatSkin());
		ImagePanel panel = new ImagePanel("image/jpg/login1.jpg");
		// 添加中心面板
		panel.add(createCenJPanel());
		// 设置界面大小
		this.setSize(400, 284);
		// 设置界面标题
		this.setTitle("登录");
		// 设置界面退出方式
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		// 设置界面居中显示
		this.setLocationRelativeTo(null);
		// 添加窗体事件
		this.addWindowListener(action);
		this.add(panel);
		// 设置界面不能随意改变大小
		this.setResizable(false);
		this.setUndecorated(true);
		// 让界面显示
		this.setVisible(true);

	}

	/**
	 * 创建中心面板
	 */
	public JPanel createCenJPanel() {

		if (cenJPanel == null) {
			cenJPanel = new JPanel();
			// 设置中心面板的布局方式为GridBagLayout
			cenJPanel.setLayout(new GridBagLayout());
			// 添加“登录方式”标签
			cenJPanel.add(CommonComponent.buildLabel("登录方式：", null, null, new Font("",Font.PLAIN,12),
					null, null), new GBC(0, 10).setInset(118,-5,0,15).setAnchor(GBC.EAST).setFill(GBC.BOTH));
			// 添加Choice类，用来选择登录方式
			cenJPanel.add(ComboBox = CommonComponent
					.buildComboBox(new String[] { "分店管理员", "采购管理员", "销售管理员",
							"仓库管理员", "账务管理员" }, null, null, null, null, null,
							false, true), new GBC(1, 10).setInset(118,-32,0,17).setAnchor(GBC.WEST).setFill(GBC.BOTH));
			// 添加“用户名”标签
			cenJPanel.add(CommonComponent.buildLabel("用户名：", null, new Color(242,149,62), new Font("",Font.PLAIN,12),
					null, null), new GBC(0, 11).setInset(11,0,0,5).setAnchor(GBC.EAST).setFill(GBC.BOTH));
			// 添加“密码”标签
			cenJPanel.add(CommonComponent.buildLabel("密  码：", null, new Color(242,149,62), new Font("",Font.PLAIN,12),
					null, null), new GBC(0, 12).setInset(10,0,5,5).setAnchor(GBC.EAST).setFill(GBC.BOTH));
			// 添加“用户”文本框
			cenJPanel.add(userText = CommonComponent.buildTextField("txt",
					null, null, null, null, null, null, true, true), new GBC(1,
					11).setInset(11,-32,0,17).setAnchor(GBC.WEST).setFill(GBC.BOTH));
			// 添加“密码”文本框
			cenJPanel.add(pwText = CommonComponent.buildTextField("pwd", null,
					null, null, null, null, null, true, true), new GBC(1, 12)
					.setInset(10,-32,5,17).setAnchor(GBC.WEST).setFill(GBC.BOTH));
			// 添加“确定”按钮
			cenJPanel.add(refer = CommonComponent.buildButton("确定", null, null,
					null, null, null, null, true), new GBC(0, 13).setInset(10));

//			refer.addMouseListener(new MouseAdapter(){
//				public void mouseEntered(MouseEvent e) {
//					refer.setIcon(new ImageIcon("image/icon/确定2.png"));
//				 }
//				public void mouseExited(MouseEvent e) {
//					refer.setIcon(new ImageIcon("image/icon/确定.png"));
//				}
//				 public void mousePressed(MouseEvent e) {
//					 refer.setIcon(new ImageIcon("image/icon/确定1.png"));
//				 }
//			});
			
			// 添加“取消”按钮
			cenJPanel.add(cancel = CommonComponent.buildButton("取消", null,
					null, null, null, null, null, true), new GBC(1, 13)
			.setInset(10));
//			cancel.addMouseListener(new MouseAdapter(){
//				public void mouseEntered(MouseEvent e) {
//					cancel.setIcon(new ImageIcon("image/icon/退出2.png"));
//				 }
//				public void mouseExited(MouseEvent e) {
//					cancel.setIcon(new ImageIcon("image/icon/退出.png"));
//				}
//				 public void mousePressed(MouseEvent e) {
//					 cancel.setIcon(new ImageIcon("image/icon/退出1.png"));
//				 }
//				 public void mouseClicked(MouseEvent e){
//					 System.exit(0);
//				 }
//			});
			cenJPanel.setOpaque(false);
			// cenJPanel.add(creatLabel("提示：学生登录，用户名是学号，密码为身份证后五位"));
		}
		// 返回cenJPanel对象
		return cenJPanel;
	}
	
	public void initAction(){
		LoginAction act = new LoginAction(this);
		refer.addActionListener(act);
		cancel.addActionListener(act);
	}

	/**
	 * 程序入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Login();
	}

	public JComboBox getComboBox() {
		return ComboBox;
	}

	public JTextField getPwText() {
		return pwText;
	}

	public JTextField getUserText() {
		return userText;
	}

}
