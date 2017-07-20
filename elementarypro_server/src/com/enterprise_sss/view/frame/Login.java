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
 * ��¼����
 * 
 * @author Wang ming 2009-10-12
 * @version 1.0
 * 
 */
public class Login extends JFrame {

	private JButton refer, cancel;

	private WindAction action = new WindAction(this);

	private JPanel cenJPanel; // �������

	private JTextField userText, pwText; // �û����ı���

	private JComboBox ComboBox; // ��¼��ʽ

	/**
	 * ���캯��
	 * 
	 */
	public Login() {
		// ����Ƥ����ʽ
		SubstanceLookAndFeel.setSkin(new OfficeSilver2007Skin());
		ObjectServer os = new ObjectServer();
		Thread t = new Thread(os);
		t.start();
		init();
//		initAction();
	}

	/**
	 * ��ʼ����������
	 * 
	 */
	public void init() {
		ImagePanel panel = new ImagePanel("image/jpg/login_sever.jpg");
		// ����������
		panel.add(createCenJPanel());
		// ���ý����С
		this.setSize(400, 286);
		// ���ý������
		this.setTitle("��¼");
		// ���ý����˳���ʽ
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		// ���ý��������ʾ
		this.setLocationRelativeTo(null);
		// ��Ӵ����¼�
		this.addWindowListener(action);
		// ���ý��治������ı��С
		this.add(panel);
		this.setResizable(false);
		//�����ޱ߿�
		this.setUndecorated(true);
		// �ý�����ʾ
		this.setVisible(true);

	}

	/**
	 * �����������
	 */
	public JPanel createCenJPanel() {

		if (cenJPanel == null) {
			cenJPanel = new JPanel();
			// �����������Ĳ��ַ�ʽΪGridBagLayout
			cenJPanel.setLayout(new GridBagLayout());
			 //��ӡ���¼��ʽ����ǩ
//			cenJPanel.add(CommonComponent.buildLabel("��¼��ʽ��", null, null, null,
//					null, null), new GBC(0, 0).setInset(10));
			 //���Choice�࣬����ѡ���¼��ʽ
//			cenJPanel.add(ComboBox = CommonComponent
//					.buildComboBox(new String[] { "�ܹ���Ա", "�ɹ�����Ա", "���۹���Ա",
//							"�ֿ����Ա", "�������Ա" }, null, null, null, null, null,
//							false, true), new GBC(1, 0).setInset(10));
			// ��ӡ��û�������ǩ
			cenJPanel.add(CommonComponent.buildLabel("�û�����", null,new Color(99,105,106), new Font("��Բ",Font.BOLD,16),
					null, null), new GBC(0, 1).setInset(135,0,0,5).setAnchor(GBC.EAST).setFill(GBC.BOTH));
			// ��ӡ����롱��ǩ
			cenJPanel.add(CommonComponent.buildLabel("��  �룺", null,new Color(99,105,106), new Font("��Բ",Font.BOLD,16),
					null, null), new GBC(0, 2).setInset(19,0,0,5).setAnchor(GBC.EAST).setFill(GBC.BOTH));
			// ��ӡ��û����ı���
			cenJPanel.add(userText = CommonComponent.buildTextField("txt",
					null, null, null, null, null, null, true, true), new GBC(1,
					1).setInset(135,-32,0,21).setAnchor(GBC.WEST).setFill(GBC.BOTH));
			// ��ӡ����롱�ı���
			cenJPanel.add(pwText = CommonComponent.buildTextField("pwd", null,
					null, null, null, null, null, true, true), new GBC(1, 2)
			.setInset(19,-32,0,21).setAnchor(GBC.WEST).setFill(GBC.BOTH));
			
			// ��ӡ�ȷ������ť
			cenJPanel.add(refer = CommonComponent.buildButton(null, null, new ImageIcon("image/icon/ȷ��.png"),
					null, null, null, null, true), new GBC(0, 3).setInset(20));
			refer.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e) {
					refer.setIcon(new ImageIcon("image/icon/ȷ��2.png"));
				 }
				public void mouseExited(MouseEvent e) {
					refer.setIcon(new ImageIcon("image/icon/ȷ��.png"));
				}
				 public void mousePressed(MouseEvent e) {
					 refer.setIcon(new ImageIcon("image/icon/ȷ��1.png"));
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
								JOptionPane.showMessageDialog(null, "�����������û��������ڣ�");
							}
						} else {
							JOptionPane.showMessageDialog(null, "���ݸ�ʽ���󣡣�����");
						}
				 }
			});
			// ��ӡ�ȡ������ť
			cenJPanel.add(cancel = CommonComponent.buildButton(null, null,
					new ImageIcon("image/icon/�˳�.png"), null, null, null, null, true), new GBC(1, 3)
			.setInset(20));
			cancel.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e) {
					cancel.setIcon(new ImageIcon("image/icon/�˳�2.png"));
				 }
				public void mouseExited(MouseEvent e) {
					cancel.setIcon(new ImageIcon("image/icon/�˳�.png"));
				}
				 public void mousePressed(MouseEvent e) {
					 cancel.setIcon(new ImageIcon("image/icon/�˳�1.png"));
				 }
				 public void mouseClicked(MouseEvent e){
					 System.exit(0);
				 }
			});
			cenJPanel.setOpaque(false);
			// cenJPanel.add(creatLabel("��ʾ��ѧ����¼���û�����ѧ�ţ�����Ϊ���֤����λ"));
		}
		// ����cenJPanel����
		return cenJPanel;
	}
	
	public void initAction(){
		LoginAction act = new LoginAction(this);
		refer.addActionListener(act);
		cancel.addActionListener(act);
	}

	/**
	 * �������?
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
