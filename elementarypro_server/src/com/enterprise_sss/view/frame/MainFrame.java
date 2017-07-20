package com.enterprise_sss.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.MenuBarUI;

//import org.jvnet.substance.SubstanceLookAndFeel;
//import org.jvnet.substance.skin.MistAquaSkin;

import com.enterprise_sss.action.ImportExportAction;
import com.enterprise_sss.action.WindAction;
import com.enterprise_sss.action.menu.MainFrameMenuAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.ImagePanel;
import com.enterprise_sss.view.panel.JGroupPanel;
import com.enterprise_sss.view.panel.mainPanel;
import com.enterprise_sss.vo.UserVO;

/**
 * �ܹ���Ա����
 * 
 * @author Administrator
 * 
 */
public class MainFrame extends JFrame {

	private WindAction action = new WindAction(this);

	private JSplitPane pane;

	private UserVO user;

	// private JButton exp, imp;

	private JButton exp, imp, home, exit;

	/**
	 * ���캯��,����ʵ��������,�����յ�¼���崫������ֵ
	 * 
	 * @param user
	 *            ���յ�¼�û���
	 */
	public MainFrame(UserVO user) {
		this.user = user;
		DataUtil.name = user.getUser();
		init();
	}

	/**
	 * ��ʼ������
	 * 
	 */
	public void init() {
		// ���ô�������
		this.setTitle("��ҵ���������ϵͳ");
		// ���ô����С
		this.setSize(750, 600);
		// ���ô������
		this.setLocationRelativeTo(null);
		// ���ô���ͼ��
		this.setIconImage(new ImageIcon("image/icon/JXCicon.png").getImage());
		// ���ô���close����
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		// ���ô��岻�ܸı��С
		// this.setResizable(false);
		// ���ô����ʼ��Ϊ���
		this.setExtendedState(this.MAXIMIZED_BOTH);
		// ��Ӵ����¼�
		this.addWindowListener(action);
		this.setJMenuBar(buildMenuBar());
		this.add(buildSplitPane());
		this.add(buildJToolBar(), BorderLayout.NORTH);
		// ���ô�����ʾ
		this.setVisible(true);
	}

	/**
	 * ��ʼ���¼�
	 * 
	 */
	public void initAction() {

	}
	
	/**
	 * buildJToolBar ���� ���� �ȹ����Բ˵�
	 * @return
	 */
	public JToolBar buildJToolBar() {
		JToolBar toolBar = new JToolBar();
		ImagePanel imagePanel = new ImagePanel("image/jpg/toolbar.jpg");
		imagePanel.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.add(exp = CommonComponent.buildButton("����", null, new ImageIcon(
				"image/icon/export.png"), null, null, null, null, true),
				new GBC(0, 0).setAnchor(GBC.NORTH).setFill(GBC.BOTH).setInset(
						30, 5, 0, 0));
		panel.add(imp = CommonComponent.buildButton("����", null, new ImageIcon(
				"image/icon/import.png"), null, null, null, null, true),
				new GBC(1, 0).setAnchor(GBC.NORTH).setFill(GBC.BOTH).setInset(
						30, 0, 0, 0));
		panel.add(home = CommonComponent.buildButton("��ҳ", null, new ImageIcon(
				"image/icon/home.png"), null, null, null, null, true), new GBC(
				2, 0).setAnchor(GBC.NORTH).setFill(GBC.BOTH).setInset(30, 0, 0,
				0));
		panel.add(exit = CommonComponent.buildButton("�˳�", null, new ImageIcon(
				"image/icon/exit.png"), null, null, null, null, true),
				new GBC(3, 0).setAnchor(GBC.NORTH).setFill(GBC.BOTH).setInset(
						30, 0, 0, 5));
		panel.setOpaque(false);
		exp.addActionListener(new ImportExportAction(this));
		imp.addActionListener(new ImportExportAction(this));
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPane().remove(getPane().getRightComponent());
				getPane().setRightComponent(
						new mainPanel("image/jpg/main.jpg", MainFrame.this));
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = JOptionPane.showConfirmDialog(null, "ȷ���˳���", "ϵͳ��ʾ",
						JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});
		imagePanel.add(panel, BorderLayout.EAST);
		toolBar.add(imagePanel);
		toolBar.setBackground(new Color(50, 135, 192));
		toolBar.setPreferredSize(new Dimension(600, 80));
		return toolBar;
	}

	/**
	 * �����ָ����������
	 * 
	 * @return
	 */
	public JPanel buildLeftPanel() {
		return new JGroupPanel(this);
	}

	/**
	 * �����ָ����������
	 * 
	 * @return
	 */
	public JPanel buildRightPanel() {

		return new mainPanel("image/jpg/main.jpg",this);
	}

	public JSplitPane buildSplitPane() {

		pane = CommonComponent.buildSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, true, buildLeftPanel(), buildRightPanel());
		return pane;
	}

	public JSplitPane getPane() {
		return pane;
	}

	/**
	 * �����ϲ����,��״̬��
	 * 
	 * @return
	 */
	public JPanel buildSouthPanel() {

		return null;
	}

	/**
	 * �����˵���(����CommonComponent���е�buildMenuBar��������)
	 * 
	 * @return
	 */
	public JMenuBar buildMenuBar() {
		Vector<JMenu> menuBar = new Vector<JMenu>();
		
		// ��һ���˵�:���Ϲ���
		Vector<JMenuItem> menu_one = new Vector<JMenuItem>();
		JMenu menu_one_commodity = CommonComponent.buildMenu("��Ʒ��Ϣά��", "",
				null, null, null, true);
		menu_one_commodity.add(buildMenuItem("�����Ʒ��Ϣ", "�����Ʒ��Ϣ", null, null,
				'n', null, true));
		menu_one_commodity.add(buildMenuItem("�޸���Ʒ��Ϣ", "�޸���Ʒ��Ϣ", null, null,
				'n', null, true));
		menu_one_commodity.add(buildMenuItem("ɾ����Ʒ��Ϣ", "ɾ����Ʒ��Ϣ", null, null,
				'n', null, true));
		menu_one_commodity.add(buildMenuItem("��ѯ��Ʒ��Ϣ", "��ѯ��Ʒ��Ϣ", null, null,
				'n', null, true));
		menu_one.add(menu_one_commodity);

		JMenu menu_one_depot = CommonComponent.buildMenu("�ֿ���Ϣά��", "", null,
				null, null, true);
		menu_one_depot.add(buildMenuItem("��Ӳֿ���Ϣ", "��Ӳֿ���Ϣ", null, null, 'n',
				null, true));
		menu_one_depot.add(buildMenuItem("�޸Ĳֿ���Ϣ", "�޸Ĳֿ���Ϣ", null, null, 'n',
				null, true));
		menu_one_depot.add(buildMenuItem("ɾ���ֿ���Ϣ", "ɾ���ֿ���Ϣ", null, null, 'n',
				null, true));
		menu_one_depot.add(buildMenuItem("��ѯ�ֿ���Ϣ", "��ѯ�ֿ���Ϣ", null, null, 'n',
				null, true));
		menu_one.add(menu_one_depot);

		JMenu menu_one_client = CommonComponent.buildMenu("�ͻ���Ϣά��", "", null,
				null, null, true);
		menu_one_client.add(buildMenuItem("��ӿͻ���Ϣ", "��ӿͻ���Ϣ", null, null, 'n',
				null, true));
		menu_one_client.add(buildMenuItem("�޸Ŀͻ���Ϣ", "�޸Ŀͻ���Ϣ", null, null, 'n',
				null, true));
		menu_one_client.add(buildMenuItem("ɾ���ͻ���Ϣ", "ɾ���ͻ���Ϣ", null, null, 'n',
				null, true));
		menu_one_client.add(buildMenuItem("��ѯ�ͻ���Ϣ", "��ѯ�ͻ���Ϣ", null, null, 'n',
				null, true));
		menu_one.add(menu_one_client);

		JMenu menu_one_supplier = CommonComponent.buildMenu("��������Ϣά��", "",
				null, null, null, true);
		menu_one_supplier.add(buildMenuItem("��ӹ�������Ϣ", "��ӹ�������Ϣ", null, null,
				'n', null, true));
		menu_one_supplier.add(buildMenuItem("�޸Ĺ�������Ϣ", "�޸Ĺ�������Ϣ", null, null,
				'n', null, true));
		menu_one_supplier.add(buildMenuItem("ɾ����������Ϣ", "ɾ����������Ϣ", null, null,
				'n', null, true));
		menu_one_supplier.add(buildMenuItem("��ѯ��������Ϣ", "��ѯ��������Ϣ", null, null,
				'n', null, true));
		menu_one.add(menu_one_supplier);

		JMenu menu_one_operator = CommonComponent.buildMenu("ҵ��Ա��Ϣά��", "",
				null, null, null, true);
		menu_one_operator.add(buildMenuItem("���ҵ��Ա��Ϣ", "���ҵ��Ա��Ϣ", null, null,
				'n', null, true));
		menu_one_operator.add(buildMenuItem("�޸�ҵ��Ա��Ϣ", "�޸�ҵ��Ա��Ϣ", null, null,
				'n', null, true));
		menu_one_operator.add(buildMenuItem("ɾ��ҵ��Ա��Ϣ", "ɾ��ҵ��Ա��Ϣ", null, null,
				'n', null, true));
		menu_one_operator.add(buildMenuItem("��ѯҵ��Ա��Ϣ", "��ѯҵ��Ա��Ϣ", null, null,
				'n', null, true));
		menu_one.add(menu_one_operator);

		JMenu menu_one_purchase = CommonComponent.buildMenu("�ɹ���ͬ��Ϣά��", "",
				null, null, null, true);
		menu_one_purchase.add(buildMenuItem("��Ӳɹ���ͬ��Ϣ", "��Ӳɹ���ͬ��Ϣ", null, null,
				'n', null, true));
		menu_one_purchase.add(buildMenuItem("�޸Ĳɹ���ͬ��Ϣ", "�޸Ĳɹ���ͬ��Ϣ", null, null,
				'n', null, true));
		menu_one_purchase.add(buildMenuItem("ɾ���ɹ���ͬ��Ϣ", "ɾ���ɹ���ͬ��Ϣ", null, null,
				'n', null, true));
		menu_one_purchase.add(buildMenuItem("��ѯ�ɹ���ͬ��Ϣ", "��ѯ�ɹ���ͬ��Ϣ", null, null,
				'n', null, true));
		menu_one.add(menu_one_purchase);

		menu_one
				.add(buildMenuItem("���ϲ�ѯ", "���ϲ�ѯ", null, null, 'n', null, true));

		// �ڶ����˵�:�ɹ�����
		Vector<JMenuItem> menu_two = new Vector<JMenuItem>();
		menu_two.add(buildMenuItem("�ɹ�����", "�ɹ�����", null, null, 'n', null, true));
		menu_two.add(buildMenuItem("������", "������", null, null, 'n', null, true));
		menu_two.add(buildMenuItem("�˻���", "������", null, null, 'n', null, true));
		// menu_two.add(buildMenuIt
		// em("�����۸����","�����۸����",null,null,'n',null,true));
		// menu_two.add(buildMenuItem("������λ����","������λ����",null,null,'n',null,true));
		// menu_two
		// .add(buildMenuItem("�ɹ���ѯ", "�ɹ���ѯ", null, null, 'n', null, true));

		// �������˵�:���۹���
		Vector<JMenuItem> menu_three = new Vector<JMenuItem>();
		menu_three.add(buildMenuItem("�������۶���", "�������۶���", null, null, 'n', null,
				true));
		menu_three.add(buildMenuItem("�������۵�", "�������۵�", null, null, 'n', null,
				true));
		menu_three.add(buildMenuItem("�����˻���", "�����˻���", null, null, 'n', null,
				true));
		menu_three.add(buildMenuItem("������ʷ�ۼ�", "������ʷ�ۼ�", null, null, 'n', null,
				true));
		menu_three.add(buildMenuItem("���۲�ѯ", "���۲�ѯ", null, null, 'n', null,
				true));

		// ���ĸ��˵�:������
		Vector<JMenuItem> menu_four = new Vector<JMenuItem>();
		menu_four
				.add(buildMenuItem("����ѯ", "����ѯ", null, null, 'n', null, true));
		menu_four
				.add(buildMenuItem("���ת��", "���ת��", null, null, 'n', null, true));
		menu_four
				.add(buildMenuItem("����̵�", "����̵�", null, null, 'n', null, true));
		menu_four
				.add(buildMenuItem("������", "������", null, null, 'n', null, true));
		menu_four.add(buildMenuItem("�������趨", "�������趨", null, null, 'n', null,
				true));
		menu_four.add(buildMenuItem("�����ޱ���", "�����ޱ���", null, null, 'n', null,
				true));

		// ������˵�:Ӧ�������
		Vector<JMenuItem> menu_five = new Vector<JMenuItem>();
		JMenu deposit_payment = CommonComponent.buildMenu("Ԥ�������", "", null,
				null, null, true);
		deposit_payment.add(buildMenuItem("���Ԥ����", "���Ԥ����", null, null, 'n',
				null, true));
		deposit_payment.add(buildMenuItem("�޸�Ԥ����", "�޸�Ԥ����", null, null, 'n',
				null, true));
		deposit_payment.add(buildMenuItem("ɾ��Ԥ����", "ɾ��Ԥ����", null, null, 'n',
				null, true));
		deposit_payment.add(buildMenuItem("��ѯԤ����", "��ѯԤ����", null, null, 'n',
				null, true));
		menu_five.add(deposit_payment);
		menu_five.add(buildMenuItem("Ӧ�����ѯ", "��ѯӦ������ϸ", null, null, 'n', null,
				true));
		menu_five.add(buildMenuItem("Ӧ�������", "Ӧ�������", null, null, 'n', null,
				true));
		JMenu payment_manage = CommonComponent.buildMenu("�������", "", null,
				null, null, true);
		payment_manage.add(buildMenuItem("��Ӹ��", "��Ӹ��", null, null, 'n',
				null, true));
		payment_manage.add(buildMenuItem("�޸ĸ��", "�޸ĸ��", null, null, 'n',
				null, true));
		payment_manage.add(buildMenuItem("ɾ�����", "ɾ�����", null, null, 'n',
				null, true));
		payment_manage.add(buildMenuItem("��ѯ���", "��ѯ���", null, null, 'n',
				null, true));
		menu_five.add(payment_manage);
		menu_five.add(buildMenuItem("�Ѹ����ѯ", "��ѯ�Ѹ�����ϸ", null, null, 'n', null,
				true));
		menu_five.add(buildMenuItem("�����ʷ��ѯ", "��ѯ�����ʷ", null, null, 'n',
				null, true));

		// �������˵�:Ӧ�տ����
		Vector<JMenuItem> menu_six = new Vector<JMenuItem>();
		JMenu deposit_receivable = CommonComponent.buildMenu("Ԥ�տ����", "", null,
				null, null, true);
		deposit_receivable.add(buildMenuItem("���Ԥ�տ�", "���Ԥ�տ�", null, null, 'n',
				null, true));
		deposit_receivable.add(buildMenuItem("�޸�Ԥ�տ�", "�޸�Ԥ�տ�", null, null, 'n',
				null, true));
		deposit_receivable.add(buildMenuItem("ɾ��Ԥ�տ�", "ɾ��Ԥ�տ�", null, null, 'n',
				null, true));
		deposit_receivable.add(buildMenuItem("��ѯԤ�տ�", "��ѯԤ�տ�", null, null, 'n',
				null, true));
		menu_six.add(deposit_receivable);
		menu_six.add(buildMenuItem("Ӧ�տ��ѯ", "��ѯӦ�տ���ϸ", null, null, 'n', null,
				true));
		menu_six.add(buildMenuItem("Ӧ�տ����", "Ӧ�տ����", null, null, 'n', null,
				true));
		JMenu receivable_manage = CommonComponent.buildMenu("�տ����", "", null,
				null, null, true);
		receivable_manage.add(buildMenuItem("����տ", "����տ", null, null, 'n',
				null, true));
		receivable_manage.add(buildMenuItem("�޸��տ", "�޸��տ", null, null, 'n',
				null, true));
		receivable_manage.add(buildMenuItem("ɾ���տ", "ɾ���տ", null, null, 'n',
				null, true));
		receivable_manage.add(buildMenuItem("��ѯ�տ", "��ѯ�տ", null, null, 'n',
				null, true));
		menu_six.add(receivable_manage);
		menu_six.add(buildMenuItem("���տ��ѯ", "��ѯ���տ���ϸ", null, null, 'n', null,
				true));
		menu_six.add(buildMenuItem("�տ��ʷ��ѯ", "��ѯ�տ��ʷ", null, null, 'n', null,
				true));

		// ���߸��˵�:���˹���
		Vector<JMenuItem> menu_seven = new Vector<JMenuItem>();
		 
		menu_seven.add(buildMenuItem("���������趨", "�趨��������", null, null, 'n', null,
				true));
		menu_seven
				.add(buildMenuItem("����", "ģ�����", null, null, 'n', null, true));
		menu_seven.add(buildMenuItem("���������", "���������", null, null, 'n', null,
				true));
		menu_seven.add(buildMenuItem("������ʷ��ѯ", "��ѯ��ʷ���ʽ��", null, null, 'n',
				null, true));

		// �ڰ˸��˵�:ͳ�Ʒ���
		Vector<JMenuItem> menu_eight = new Vector<JMenuItem>();
		menu_eight.add(buildMenuItem("�ɹ�ͳ��", "�ɹ�ͳ��", null, null, 'n', null,
				true));
		menu_eight.add(buildMenuItem("����ͳ��", "����ͳ��", null, null, 'n', null,
				true));
		menu_eight.add(buildMenuItem("���ͳ��", "���ͳ��", null, null, 'n', null,
				true));

		// �ھŸ��˵�:ϵͳ����
		Vector<JMenuItem> menu_night = new Vector<JMenuItem>();
		menu_night.add(buildMenuItem("�鿴��־", "�鿴��־", null, null, 'n', null,
				true));
		menu_night.add(buildMenuItem("��������", "��������", null, null, 'n', null,
				true));

		menuBar.add(CommonComponent.buildMenu("����ά��", "������Ϣά��", menu_one, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("�ɹ�����", "�ɹ�����", menu_two, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("���۹���", "���۹���", menu_three, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("������", "������", menu_four, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("ͳ�Ʒ���", "ͳ�Ʒ���", menu_eight, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("Ӧ�������", "Ӧ������Ϣ����", menu_five,
				null, null, true));
		menuBar.add(CommonComponent.buildMenu("Ӧ�տ����", "Ӧ�տ���Ϣ����", menu_six,
				null, null, true));
		menuBar.add(CommonComponent.buildMenu("�������", "�������", menu_seven, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("ϵͳ����", "ϵͳ����", menu_night, null,
				null, true));
		//MenuBarUI background= new MenuBarUI();
		JMenuBar jb = CommonComponent.buildMenuBar(menuBar, Color.RED, null);
		jb.setBackground(Color.blue);
		
		return jb;
	}

	/**
	 * �����˵�ѡ��(����CommonComponent���е�buildMenuItem��������)
	 * 
	 * @return
	 */
	public JMenuItem buildMenuItem(String title, String toolTipText,
			ImageIcon ic, Color bgColor, char mnemonic, KeyStroke ks,
			boolean isEnable) {
		JMenuItem item = CommonComponent.buildMenuItem(title, toolTipText, ic,
				bgColor, mnemonic, ks, isEnable);
		 Font font=new Font("΢���ź�",Font.PLAIN,14);
		 item.setFont(font);
		item.addActionListener(new MainFrameMenuAction(this));
		return item;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	// public static void main(String[] args) {
	// SubstanceLookAndFeel.setSkin(new MistAquaSkin());
	// new MainFrame("ming");
	// }
}
