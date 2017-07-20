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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.MistAquaSkin;
import org.jvnet.substance.skin.OfficeSilver2007Skin;

import com.enterprise_sss.action.LoginAction;
import com.enterprise_sss.action.WindAction;
import com.enterprise_sss.control.LoginServer;
import com.enterprise_sss.socket.ObjectServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.ImagePanel;
import com.enterprise_sss.vo.UserVO;

/**
 * 登录界面
 * 
 * @author Wang ming 2009-10-12
 * @version 1.0
 * 
 */
public class Login extends JFrame {

	private JButton refer, cancel;

	private WindAction action = new WindAction(this);

	private JPanel cenJPanel; // 中心面板

	private JTextField userText, pwText; // 用户名文本框

	private JComboBox ComboBox; // 登录方式

	/**
	 * 构造函数
	 * 
	 */
	public Login() {
		// 设置皮肤方式
		SubstanceLookAndFeel.setSkin(new OfficeSilver2007Skin());
		ObjectServer os = new ObjectServer();
		Thread t = new Thread(os);
		t.start();
		init();
//		initAction();
	}

	/**
	 * 初始化整个界面
	 * 
	 */
	public void init() {
		ImagePanel panel = new ImagePanel("image/jpg/login_sever.jpg");
		// 添加中心面板
		panel.add(createCenJPanel());
		// 设置界面大小
		this.setSize(400, 286);
		// 设置界面标题
		this.setTitle("登录");
		// 设置界面退出方式
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		// 设置界面居中显示
		this.setLocationRelativeTo(null);
		// 添加窗体事件
		this.addWindowListener(action);
		// 设置界面不能随意改变大小
		this.add(panel);
		this.setResizable(false);
		//设置无边框
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
			 //添加“登录方式”标签
//			cenJPanel.add(CommonComponent.buildLabel("登录方式：", null, null, null,
//					null, null), new GBC(0, 0).setInset(10));
			 //添加Choice类，用来选择登录方式
//			cenJPanel.add(ComboBox = CommonComponent
//					.buildComboBox(new String[] { "总管理员", "采购管理员", "销售管理员",
//							"仓库管理员", "账务管理员" }, null, null, null, null, null,
//							false, true), new GBC(1, 0).setInset(10));
			// 添加“用户名”标签
			cenJPanel.add(CommonComponent.buildLabel("用户名：", null,new Color(99,105,106), new Font("幼圆",Font.BOLD,16),
					null, null), new GBC(0, 1).setInset(135,0,0,5).setAnchor(GBC.EAST).setFill(GBC.BOTH));
			// 添加“密码”标签
			cenJPanel.add(CommonComponent.buildLabel("密  码：", null,new Color(99,105,106), new Font("幼圆",Font.BOLD,16),
					null, null), new GBC(0, 2).setInset(19,0,0,5).setAnchor(GBC.EAST).setFill(GBC.BOTH));
			// 添加“用户”文本框
			cenJPanel.add(userText = CommonComponent.buildTextField("txt",
					null, null, null, null, null, null, true, true), new GBC(1,
					1).setInset(135,-32,0,21).setAnchor(GBC.WEST).setFill(GBC.BOTH));
			// 添加“密码”文本框
			cenJPanel.add(pwText = CommonComponent.buildTextField("pwd", null,
					null, null, null, null, null, true, true), new GBC(1, 2)
			.setInset(19,-32,0,21).setAnchor(GBC.WEST).setFill(GBC.BOTH));
			
			// 添加“确定”按钮
			cenJPanel.add(refer = CommonComponent.buildButton(null, null, new ImageIcon("image/icon/确定.png"),
					null, null, null, null, true), new GBC(0, 3).setInset(20));
			refer.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e) {
					refer.setIcon(new ImageIcon("image/icon/确定2.png"));
				 }
				public void mouseExited(MouseEvent e) {
					refer.setIcon(new ImageIcon("image/icon/确定.png"));
				}
				 public void mousePressed(MouseEvent e) {
					 refer.setIcon(new ImageIcon("image/icon/确定1.png"));
				 }
				 public void mouseClicked(MouseEvent e){
					 UserVO uvo = new UserVO();
						uvo.setUser(getUserText().getText().trim());
						uvo.setPassword(getPwText().getText().trim());
//						uvo.setLevel(login.getComboBox().getSelectedIndex());
						LoginServer ls = new LoginServer();
					 if (ls.isEmpty(uvo)) {
							if (ls.login(uvo)){
								ls.close();
								dispose();
								new MainFrame(uvo);
							} else {
								JOptionPane.showMessageDialog(null, "密码错误或者用户名不存在！");
							}
						} else {
							JOptionPane.showMessageDialog(null, "数据格式错误！！！！");
						}
				 }
			});
			// 添加“取消”按钮
			cenJPanel.add(cancel = CommonComponent.buildButton(null, null,
					new ImageIcon("image/icon/退出.png"), null, null, null, null, true), new GBC(1, 3)
			.setInset(20));
			cancel.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e) {
					cancel.setIcon(new ImageIcon("image/icon/退出2.png"));
				 }
				public void mouseExited(MouseEvent e) {
					cancel.setIcon(new ImageIcon("image/icon/退出.png"));
				}
				 public void mousePressed(MouseEvent e) {
					 cancel.setIcon(new ImageIcon("image/icon/退出1.png"));
				 }
				 public void mouseClicked(MouseEvent e){
					 System.exit(0);
				 }
			});
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
	 * 程序入口?
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
