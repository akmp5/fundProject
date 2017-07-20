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
 * ��¼����
 * 
 * @author Wang ming 2009-10-12
 * @version 1.0
 * 
 */
public class Login extends JFrame {

	private JButton refer, cancel;

	private LoginWindAction action = new LoginWindAction(this);

	private JPanel cenJPanel; // �������

	private JTextField userText, pwText; // �û����ı���

	private JComboBox ComboBox; // ��¼��ʽ

	/**
	 * ���캯��
	 * 
	 */
	public Login() {
		// ����Ƥ����ʽ
		SubstanceLookAndFeel.setSkin(new MistAquaSkin());
		init();
		initAction();
	}

	/**
	 * ��ʼ����������
	 * 
	 */
	public void init() {
		SubstanceLookAndFeel.setSkin(new FieldOfWheatSkin());
		ImagePanel panel = new ImagePanel("image/jpg/login1.jpg");
		// ����������
		panel.add(createCenJPanel());
		// ���ý����С
		this.setSize(400, 284);
		// ���ý������
		this.setTitle("��¼");
		// ���ý����˳���ʽ
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		// ���ý��������ʾ
		this.setLocationRelativeTo(null);
		// ��Ӵ����¼�
		this.addWindowListener(action);
		this.add(panel);
		// ���ý��治������ı��С
		this.setResizable(false);
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
			// ��ӡ���¼��ʽ����ǩ
			cenJPanel.add(CommonComponent.buildLabel("��¼��ʽ��", null, null, new Font("",Font.PLAIN,12),
					null, null), new GBC(0, 10).setInset(118,-5,0,15).setAnchor(GBC.EAST).setFill(GBC.BOTH));
			// ���Choice�࣬����ѡ���¼��ʽ
			cenJPanel.add(ComboBox = CommonComponent
					.buildComboBox(new String[] { "�ֵ����Ա", "�ɹ�����Ա", "���۹���Ա",
							"�ֿ����Ա", "�������Ա" }, null, null, null, null, null,
							false, true), new GBC(1, 10).setInset(118,-32,0,17).setAnchor(GBC.WEST).setFill(GBC.BOTH));
			// ��ӡ��û�������ǩ
			cenJPanel.add(CommonComponent.buildLabel("�û�����", null, new Color(242,149,62), new Font("",Font.PLAIN,12),
					null, null), new GBC(0, 11).setInset(11,0,0,5).setAnchor(GBC.EAST).setFill(GBC.BOTH));
			// ��ӡ����롱��ǩ
			cenJPanel.add(CommonComponent.buildLabel("��  �룺", null, new Color(242,149,62), new Font("",Font.PLAIN,12),
					null, null), new GBC(0, 12).setInset(10,0,5,5).setAnchor(GBC.EAST).setFill(GBC.BOTH));
			// ��ӡ��û����ı���
			cenJPanel.add(userText = CommonComponent.buildTextField("txt",
					null, null, null, null, null, null, true, true), new GBC(1,
					11).setInset(11,-32,0,17).setAnchor(GBC.WEST).setFill(GBC.BOTH));
			// ��ӡ����롱�ı���
			cenJPanel.add(pwText = CommonComponent.buildTextField("pwd", null,
					null, null, null, null, null, true, true), new GBC(1, 12)
					.setInset(10,-32,5,17).setAnchor(GBC.WEST).setFill(GBC.BOTH));
			// ��ӡ�ȷ������ť
			cenJPanel.add(refer = CommonComponent.buildButton("ȷ��", null, null,
					null, null, null, null, true), new GBC(0, 13).setInset(10));

//			refer.addMouseListener(new MouseAdapter(){
//				public void mouseEntered(MouseEvent e) {
//					refer.setIcon(new ImageIcon("image/icon/ȷ��2.png"));
//				 }
//				public void mouseExited(MouseEvent e) {
//					refer.setIcon(new ImageIcon("image/icon/ȷ��.png"));
//				}
//				 public void mousePressed(MouseEvent e) {
//					 refer.setIcon(new ImageIcon("image/icon/ȷ��1.png"));
//				 }
//			});
			
			// ��ӡ�ȡ������ť
			cenJPanel.add(cancel = CommonComponent.buildButton("ȡ��", null,
					null, null, null, null, null, true), new GBC(1, 13)
			.setInset(10));
//			cancel.addMouseListener(new MouseAdapter(){
//				public void mouseEntered(MouseEvent e) {
//					cancel.setIcon(new ImageIcon("image/icon/�˳�2.png"));
//				 }
//				public void mouseExited(MouseEvent e) {
//					cancel.setIcon(new ImageIcon("image/icon/�˳�.png"));
//				}
//				 public void mousePressed(MouseEvent e) {
//					 cancel.setIcon(new ImageIcon("image/icon/�˳�1.png"));
//				 }
//				 public void mouseClicked(MouseEvent e){
//					 System.exit(0);
//				 }
//			});
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
	 * �������
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
