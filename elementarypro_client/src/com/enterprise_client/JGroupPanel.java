import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalBorders;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.MistAquaSkin;

import com.enterprise_sss.action.JGroupPanelAction;
import com.enterprise_sss.control.PaymentServer;
import com.enterprise_sss.control.ReceivableServer;
import com.enterprise_sss.control.ReportFormDaoServer;
import com.enterprise_sss.ui.BackgroundPanel;
import com.enterprise_sss.view.dialog.accountPAndR.PaymentDetail;
import com.enterprise_sss.view.dialog.accountPAndR.ReceivableDetail;
import com.enterprise_sss.view.dialog.testJFreeChar.ReportFormDialog;
import com.enterprise_sss.view.frame.MainFrame;
import com.enterprise_sss.view.panel.depotmanage.QueryDepotPanel;
import com.enterprise_sss.view.panel.maintenance.PurchaseOrderPanel;
import com.enterprise_sss.view.panel.salemanage.SaleTabbedPane;
import com.enterprise_sss.vo.TableVO;

/**
 * ����QQ�������Ⱥ�������
 * 
 * @author Caicai
 * 
 */
public class JGroupPanel extends JPanel {

	private MainFrame frame;

	/* ������������������� */
	private JPanel pNorth = new JPanel();

	private JPanel pCenter = new JPanel();

	private JPanel pSouth = new JPanel();
	
	private PaymentServer ps=new PaymentServer();
	
	private ReceivableServer rs=new ReceivableServer();

	/* ��ǰȫ����ļ��� */
	private ArrayList groupList = new ArrayList();

	/* �Ƿ��ѽ�ֹ������ */
	private boolean forbidFlag = false;

	/* ��ǰ������� */
	private JGroupContainer activeGroup = null;

	transient ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String str = e.getActionCommand();
			JButton bttTitle = (JButton) e.getSource();
			expandGroup((JGroupContainer) bttTitle.getParent());
			TableVO tvo = new TableVO();
			Vector title = new Vector();
			String[] items = null;
			String name= null;
			if ("���Ϲ���".equals(str)) {
				items = new String[]{"","��ѯ��Ʒ����","��ѯ�ֿ�����","��ѯ�ɹ���ͬ����","��ѯ�ͻ�����","��ѯҵ��Ա����","��ѯ����������"};
				frame.getPane().remove(frame.getPane().getRightComponent());
				frame.getPane().setRightComponent(new DataFindPanel("���ϲ�ѯ", items, tvo));
			} else if ("�ɹ�����".equals(str)) {
				title.add("�������");
				title.add("�����̱��");
				title.add("��������");
				title.add("��Ч����");
				title.add("��Чֹ��");
				title.add("ҵ��Ա���");
				title.add("�Ƶ���");
				tvo.setTitle(title);
				frame.getPane().remove(frame.getPane().getRightComponent());
				frame.getPane().setRightComponent(new PurchaseOrderPanel(str,tvo));
			} else if ("���۹���".equals(str)) {
				frame.getPane().remove(frame.getPane().getRightComponent());
				frame.getPane().setRightComponent(new SaleTabbedPane());
			} else if ("������".equals(str)) {
				frame.getPane().remove(frame.getPane().getRightComponent());
				frame.getPane().setRightComponent(new QueryDepotPanel());
			} else if ("Ӧ�������".equals(str)) {
				TableVO aptable=new TableVO(0,ps.getAccountPayable(),ps.getAccountPayableTitle());
				TableVO pibtable=new TableVO(0,new Vector(),ps.getPurchaseInBillTitle());
				PaymentDetail dp=new PaymentDetail("Ӧ�����ѯ","Ӧ�����б�",aptable,"��������ϸ��Ϣ",pibtable);
			} else if ("Ӧ�տ����".equals(str)) {
				TableVO artable=new TableVO(0,rs.getAccountReceivable(),rs.getAccountReceivableTitle());
				TableVO aotable=new TableVO(0,new Vector(),rs.getSaleBillTitle());
				ReceivableDetail dp=new ReceivableDetail("Ӧ�տ��ѯ","Ӧ�տ��б�",artable,"���۵���ϸ��Ϣ",aotable);
			} else if ("ͳ�Ʒ���".equals(str)) {
				ReportFormDaoServer rs = new ReportFormDaoServer();
				Vector datas = rs.find(1,str);
				frame.getPane().remove(frame.getPane().getRightComponent());
				frame.getPane().setRightComponent(new ReportFormDialog(str,datas));
				rs.close();
			}
		}
	};

	private boolean hasCreateDefaultGroup = false;

	public JGroupPanel() {
		initComponents();
		createDefaultGroup();
	}

	public JGroupPanel(MainFrame frame) {
		this.frame = frame;
		initComponents();
		createDefaultGroup();
	}

	private void initComponents() {
		this.setLayout(new BorderLayout(0,0));
		this.add(pNorth, BorderLayout.NORTH);
		this.add(pCenter, BorderLayout.CENTER);
		this.add(pSouth, BorderLayout.SOUTH);
		pNorth.setLayout(new GroupLayout());
		pCenter.setLayout(new BorderLayout());
		pSouth.setLayout(new GroupLayout());
		forbidFlag = true;
	}

	private void createDefaultGroup() {
		String ListButtonicon ="image/icon/button.png";/*������������˵�buttonͼƬ*/
		insertGroup(0, "���Ϲ���");
		addMember(0, buildJButton("��Ʒ����ά��", ListButtonicon));
		addMember(0, buildJButton("����������ά��", ListButtonicon));
		addMember(0, buildJButton("�ͻ�����ά��", ListButtonicon));
		addMember(0, buildJButton("ҵ��Ա��Ϣά��", ListButtonicon));
		addMember(0, buildJButton("�ֿ���Ϣά��", ListButtonicon));
		addMember(0, buildJButton("�ɹ���ͬ����", ListButtonicon));
		addMember(0, buildJButton("���ϲ�ѯ", ListButtonicon));
		getGroup(0).setMemberGap(3, 0);


		insertGroup(1, "Ӧ�������");
		addMember(1, buildJButton("Ԥ�������", ListButtonicon));
		addMember(1, buildJButton("Ӧ�������", ListButtonicon));
		addMember(1, buildJButton("���", ListButtonicon));
		addMember(1, buildJButton("�Ѹ�����ϸ", ListButtonicon));
		addMember(1, buildJButton("Ӧ�����ѯ", ListButtonicon));
		getGroup(1).setMemberGap(3, 0);

		insertGroup(2, "Ӧ�տ����");
		addMember(2, buildJButton("Ԥ�տ����", ListButtonicon));
		addMember(2, buildJButton("Ӧ�տ����", ListButtonicon));
		addMember(2, buildJButton("�տ", ListButtonicon));
		addMember(2, buildJButton("���տ���ϸ", ListButtonicon));
		addMember(2, buildJButton("Ӧ�տ��ѯ", ListButtonicon));
		getGroup(2).setMemberGap(3, 0);

		insertGroup(3, "�������");
		addMember(3, buildJButton("���������趨", ListButtonicon));
		addMember(3, buildJButton("����", ListButtonicon));
		addMember(3, buildJButton("���������", ListButtonicon));
		addMember(3, buildJButton("��ʷ��ѯ", ListButtonicon));
		getGroup(3).setMemberGap(3, 0);

		insertGroup(4, "ͳ�Ʒ���");
		addMember(4, buildJButton("�ɹ�ͳ��", ListButtonicon));
		addMember(4, buildJButton("����ͳ��", ListButtonicon));
		addMember(4, buildJButton("���ͳ��", ListButtonicon));
		getGroup(4).setMemberGap(3, 0);

		expandGroup(1);
		hasCreateDefaultGroup = true;
	}

	/**
	 * ���칤������ť�Ĺ��÷���
	 * 
	 * @return
	 */
	public JButton buildJButton(String name, String icons) {
		JButton button;
		if (icons != null){
			button = new JButton(name,new ImageIcon(icons));
			//button.setIcon(new ImageIcon(icons));
		}else{
		    button = new JButton(name);	
		}
		button.setIcon(new ImageIcon(icons));
		button.setToolTipText(name);
		button.setIconTextGap(-35);//���ñ���ͼƬ�����ּ�ļ��
		button.setBorderPainted(false);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setToolTipText(name);
/*		button.setRolloverEnabled(true);
		button.setBorderPainted(true);*/
		//����button������ʾλ��
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setContentAreaFilled(false);//����button��������
		button.setRolloverEnabled(false);
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setOpaque(false); //���ñ���͸��  
		button.setBorder(new EmptyBorder(0,0,0,0));
		button.setBorder(new MetalBorders.ButtonBorder());
		button.setBorderPainted(false);
		button.setBounds(90, 50, 200, 50);
		button.setForeground(new Color(255,255,255));//����������ɫ
		button .setFont(new java.awt.Font("SimHei", Font.ITALIC, 15));//����button ������ɫ
		button.setRolloverIcon(new ImageIcon("image/icon/buttonBg.png"));//����������֮��JButton�ı���ͼƬ  
		button.setPressedIcon(new ImageIcon("image/icon/buttonBg.png"));//���������֮��JButton�ı���ͼƬ  
		// ��Ӱ�ť�¼�
		button.addActionListener(new JGroupPanelAction(this.frame));
		return button;
	}

	/**
	 * չ����
	 * 
	 * @param name
	 *            String ����
	 */
	public void expandGroup(String name) {
		for (int i = getGroupCount() - 1; i >= 0; i--) {
			if (getGroupName(i).equals(name)) {
				expandGroup(i);
			}
		}
	}

	/**
	 * չ����
	 * 
	 * @param index
	 *            int ���˳���
	 */
	public void expandGroup(int index) {
		expandGroup(getGroup(index));
	}

	/**
	 * չ����
	 * 
	 * @param group
	 *            JGroupContainer ��
	 */
	protected void expandGroup(JGroupContainer group) {
		pNorth.removeAll();
		pCenter.removeAll();
		pSouth.removeAll();
		boolean hasAddCenter = false;
		for (int i = 0; i < groupList.size(); i++) {
			Component c = (Component) groupList.get(i);
			if (hasAddCenter) {
				pSouth.add(c);
			} else if (c == group) {
				pCenter.add(c, BorderLayout.CENTER);
				hasAddCenter = true;
			} else {
				pNorth.add(c);
			}
		}
		if (activeGroup != null) {
			activeGroup.collapse();
		}
		activeGroup = group;
		activeGroup.expand();
		pNorth.doLayout();
		pCenter.doLayout();
		pSouth.doLayout();
		doLayout();
	}

	/**
	 * ������
	 * 
	 * @param name
	 *            String ����
	 */
	public void collapseGroup(String name) {
		for (int i = getGroupCount() - 1; i >= 0; i--) {
			if (getGroupName(i).equals(name)) {
				collapseGroup(i);
			}
		}
	}

	/**
	 * ������
	 * 
	 * @param index
	 *            int ���˳���
	 */
	public void collapseGroup(int index) {
		collapseGroup(getGroup(index));
	}

	/**
	 * ������
	 * 
	 * @param group
	 *            JGroupContainer ��
	 */
	protected void collapseGroup(JGroupContainer group) {
		if (group == activeGroup) {
			activeGroup.collapse();
			activeGroup = null;
		}
	}

	/**
	 * �����
	 * 
	 * @param name
	 *            String ����
	 */
	public void addGroup(String name) {
		this.insertGroup(getGroupCount(), name);
	}

	/**
	 * ����һ����
	 * 
	 * @param index
	 *            int ˳���
	 * @param name
	 *            String ����
	 * @param bg
	 *            Color ����ɫ
	 */
	public void insertGroup(int index, String name, Color bg) {
		if (index < 0 || index > groupList.size()) {
			throw new ArrayIndexOutOfBoundsException("index:" + index
					+ " >count:" + groupList.size());
		}
		if (hasCreateDefaultGroup) {
			while (getGroupCount() > 0) {
				removeGroup(0);
			}
			hasCreateDefaultGroup = false;
		}
		int countNorth = pNorth.getComponentCount();
		int countCenter = pCenter.getComponentCount();
		int countSouth = pSouth.getComponentCount();
		JGroupContainer group;
		if (index <= countNorth) {
			group = insertGroup(pNorth, index, name, bg);
		} else if (index <= countNorth + countCenter) {
			group = insertGroup(pCenter, index - countNorth, name, bg);
		} else if (index <= countNorth + countCenter + countSouth) {
			group = insertGroup(pSouth, index - countNorth - countCenter, name,
					bg);
		} else {
			group = insertGroup(pSouth, countSouth, name, bg);
		}
		group.getTitleButton().addActionListener(al);
		groupList.add(index, group);

	}

	/**
	 * ����һ����
	 * 
	 * @param index
	 *            int ˳���
	 * @param name
	 *            String ����
	 */
	public void insertGroup(int index, String name) {
		insertGroup(index, name, UIManager.getColor("Desktop.background"));
	}

	/**
	 * ����һ����
	 * 
	 * @param p
	 *            JPanel Ŀ�����
	 * @param index
	 *            int ˳���
	 * @param name
	 *            String ����
	 * 
	 * 
	 * /** ����һ����
	 * @param p
	 *            JPanel Ŀ�����
	 * @param index
	 *            int ˳���
	 * @param name
	 *            String ����
	 * @return JGroupContainer
	 */
	private JGroupContainer insertGroup(JPanel p, int index, String name,
			Color bg) {
		JGroupContainer group = new JGroupContainer(name, bg);
		p.add(group);
		return group;
	}

	/**
	 * ɾ��һ����
	 * 
	 * @param index
	 *            int ˳���
	 */
	public void removeGroup(int index) {
		JGroupContainer c = (JGroupContainer) groupList.get(index);
		c.getParent().remove(c);
		c.getTitleButton().removeActionListener(al);
	}

	/**
	 * ɾ��һ����
	 * 
	 * @param name
	 *            String ����
	 */
	public void removeGroup(String name) {
		for (int i = getGroupCount() - 1; i >= 0; i--) {
			if (getGroupName(i).equals(name)) {
				this.removeGroup(i);
			}
		}
	}

	/**
	 * ��������
	 * 
	 * @param index
	 *            int ˳���
	 * @param name
	 *            String ����
	 */
	public void setGroupName(int index, String name) {
		this.getGroup(index).setName(name);
	}

	/**
	 * ȡ������
	 * 
	 * @param groupIndex
	 *            int ˳���
	 * @return String ����
	 */
	public String getGroupName(int groupIndex) {
		return getGroup(groupIndex).getName();
	}

	/**
	 * ȡ��ȫ������
	 * 
	 * @return String[]
	 */
	public String[] getGroupNames() {
		String sResult[] = new String[getGroupCount()];
		for (int i = 0; i < getGroupCount(); i++) {
			sResult[i] = getGroupName(i);
		}
		return sResult;
	}

	/**
	 * ȡ�õ�ǰ�������
	 * 
	 * @return int
	 */
	public int getGroupCount() {
		return groupList.size();
	}

	/**
	 * ��������ӳ�Ա���
	 * 
	 * @param groupIndex
	 *            int ���˳���
	 * @param member
	 *            Component ��Ա���
	 */
	public void addMember(int groupIndex, Component member) {
		getGroup(groupIndex).addMember(getGroup(groupIndex).getMemberCount(),
				member);
	}

	/**
	 * �����в����Ա���
	 * 
	 * @param groupIndex
	 *            int ���˳���
	 * @param memberIndex
	 *            int �����˳���
	 * @param member
	 *            Component ��Ա���
	 */
	public void insertMember(int groupIndex, int memberIndex, Component member) {
		getGroup(groupIndex).addMember(memberIndex, member);
	}

	/**
	 * �������Ƴ���Ա���
	 * 
	 * @param groupIndex
	 *            int
	 * @param memberIndex
	 *            int
	 */
	public void removeMember(int groupIndex, int memberIndex) {
		getGroup(groupIndex).removeMember(memberIndex);
	}

	/**
	 * ȡ�ó�Ա���
	 * 
	 * @param groupIndex
	 *            int ���˳���
	 * @param memberIndex
	 *            int ��Ա�����˳���
	 * @return Component ��Ա���
	 */
	public Component getMember(int groupIndex, int memberIndex) {
		return getGroup(groupIndex).getMember(memberIndex);
	}

	/**
	 * ȡ��ȫ����Ա���
	 * 
	 * @param groupIndex
	 *            int ���˳���
	 * @return Component[] ȫ����Ա���
	 */
	public Component[] getMembers(int groupIndex) {
		return getGroup(groupIndex).getMembers();
	}

	/**
	 * ȡ�ó�Ա���������
	 * 
	 * @param groupIndex
	 *            int ���˳���
	 * @return int ����
	 */
	public int getMemberCount(int groupIndex) {
		return getGroup(groupIndex).getMemberCount();
	}

	/**
	 * ȡ����
	 * 
	 * @param index
	 *            int ���˳���
	 * @return JGroupContainer ��
	 */
	protected JGroupContainer getGroup(int index) {
		return (JGroupContainer) groupList.get(index);
	}

	/**
	 * ��д��addImpl����,��ֹ����JGroupPane��������
	 * 
	 * @param comp
	 *            Component
	 * @param constraints
	 *            Object
	 * @param index
	 *            int
	 */
	protected void addImpl(Component comp, Object constraints, int index) {
		if (forbidFlag) {
			if (!(comp instanceof JGroupContainer)) {
				throw new UnsupportedOperationException(
						"JGroupPane can't add component!");
			}
		} else {
			super.addImpl(comp, constraints, index);
		}
	}

	/**
	 * <p>
	 * Title: OpenSwing
	 * </p>
	 * <p>
	 * Description: ����岼�ֹ�����
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2004
	 * </p>
	 * <p>
	 * Company:
	 * </p>
	 * 
	 * @author <a href="mailto:sunkingxie@hotmail.com">SunKing</a>
	 * @version 1.0
	 */
	class GroupLayout implements LayoutManager, java.io.Serializable {
		int vgap = 0;

		int hgap = 0;

		public GroupLayout() {
		}

		public GroupLayout(int hg, int vg) {
			this.hgap = hg;
			this.vgap = vg;
		}

		public void addLayoutComponent(String name, Component comp) {
		}

		public void removeLayoutComponent(Component comp) {
		}

		public Dimension preferredLayoutSize(Container parent) {
			synchronized (parent.getTreeLock()) {
				Insets insets = parent.getInsets();
				int ncomponents = parent.getComponentCount();
				int w = 0;
				int h = 0;
				for (int i = 0; i < ncomponents; i++) {
					Component comp = parent.getComponent(i);
					Dimension d = comp.getPreferredSize();
					if (w < d.width) {
						w = d.width;
					}
					h += d.height + vgap;
				}
				return new Dimension(insets.left + insets.right + w + 2 * hgap,
						insets.top + insets.bottom + h + 2 * vgap);
			}
		}

		public Dimension minimumLayoutSize(Container parent) {
			return preferredLayoutSize(parent);
		}

		public void layoutContainer(Container parent) {
			synchronized (parent.getTreeLock()) {
				Insets insets = parent.getInsets();
				int ncomponents = parent.getComponentCount();
				if (ncomponents == 0) {
					return;
				}
				int y = insets.top + vgap;
				for (int c = 0; c < ncomponents; c++) {
					int h = parent.getComponent(c).getPreferredSize().height;
					parent.getComponent(c).setBounds(
							insets.left + hgap,
							y,
							parent.getWidth() - insets.left - insets.right - 2
									* hgap, h);
					y += h + vgap;
				}
			}
		}

		public String toString() {
			return getClass().getName();
		}
	}

	/**
	 * <p>
	 * Title: OpenSwing
	 * </p>
	 * <p>
	 * Description: ��
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2004
	 * </p>
	 * <p>
	 * Company:
	 * </p>
	 * 
	 * @author <a href="mailto:sunkingxie@hotmail.com">SunKing</a>
	 * @version 1.0
	 */
	class JGroupContainer extends JPanel {
		private JButton bttGroupTitle = new JButton(new ImageIcon());

		private JPanel pMembers = new JPanel();

		private JScrollPane sp;

		public JGroupContainer() {
			this("");
		}

		public JGroupContainer(String name) {
			this(name, UIManager.getColor("Desktop.background"));
		}

		/**
		 * @param name
		 *            String ����
		 * @param background
		 *            Color ��Ա���������屳��ɫ
		 */
		public JGroupContainer(String name, Color background) {	
/*	        ImageIcon topPanelIcon = new ImageIcon("image/icon/buttonMenu.png");
			  BackgroundPanel topPanel = new BackgroundPanel(topPanelIcon);
		      Dimension topPanelDimension = new Dimension(topPanelIcon.getIconWidth(), topPanelIcon.getIconHeight());
		      topPanel.setPreferredSize(topPanelDimension);// ���������Ѵ�С
*/			Color buttonColor = new Color(255,255,255);//��Ӱ��ɫ���������ֵ,ֻ��ռλ���á�  
			bttGroupTitle.setText(name);
			bttGroupTitle.setFocusable(false);
			bttGroupTitle.setIcon(new ImageIcon("image/icon/buttonMenu.png"));
			pMembers.setLayout(new GroupLayout(0, 0));
			bttGroupTitle.setHorizontalTextPosition(JButton.CENTER);
			bttGroupTitle.setVerticalTextPosition(JButton.BOTTOM);
			bttGroupTitle.setIconTextGap(-35);//���ñ���ͼƬ�����ּ�ļ��
			bttGroupTitle .setFont(new java.awt.Font("SimHei", Font.BOLD, 17));//����button ������ɫ
			bttGroupTitle.setForeground(new Color(189,220,239));//����������ɫ
			bttGroupTitle.setRolloverEnabled(false);
			bttGroupTitle.setFocusPainted(false);
			bttGroupTitle.setContentAreaFilled(false);//����button��������
			bttGroupTitle.setBorderPainted(false);	
			bttGroupTitle.setBackground(buttonColor);  
			bttGroupTitle.setOpaque(false); //���ñ���͸�� 
			bttGroupTitle.setMargin(new Insets(0,0,0,0));
			this.setLayout(new BorderLayout(0,0));
			this.add(bttGroupTitle, BorderLayout.NORTH);//���水ť����ͼƬ
			pMembers.setBackground(buttonColor);		
			Color thumbColor = UIManager.getColor("ScrollBar.thumb");
			Color trackColor = UIManager.getColor("ScrollBar.track");
			Color trackHighlightColor = UIManager.getColor("ScrollBar.trackHighlight");

			UIManager.put("ScrollBar.thumb", background);
			UIManager.put("ScrollBar.track", background);
			UIManager.put("ScrollBar.trackHighlight", background);
			sp = new JScrollPane(pMembers);
			sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			this.add(sp, BorderLayout.CENTER);
			collapse();
			UIManager.put("ScrollBar.thumb", thumbColor);
			UIManager.put("ScrollBar.track", trackColor);
			UIManager.put("ScrollBar.trackHighlight", trackHighlightColor);
		

		}

		/**
		 * ���ü��
		 * 
		 * @param hgap
		 *            int ����
		 * @param vgap
		 *            int �����
		 */
		public void setMemberGap(int hgap, int vgap) {
			pMembers.setLayout(new GroupLayout(hgap, vgap));
		}

		/**
		 * ȡ����ı��ⰴť
		 * 
		 * @return JButton
		 */
		public JButton getTitleButton() {
			return bttGroupTitle;
		}

		/**
		 * ȡ����ĳ�Ա������
		 * 
		 * @return JPanel
		 */
		public JPanel getMembersContainer() {
			return pMembers;
		}

		/**
		 * ������
		 */
		public void collapse() {
			sp.setVisible(false);
			this.revalidate();
		}

		/**
		 * չ����
		 */
		public void expand() {
			sp.setVisible(true);
			this.revalidate();
		}

		/**
		 * ��������
		 * 
		 * @param name
		 *            String ����
		 */
		public void setName(String name) {
			bttGroupTitle.setText(name);
		}

		/**
		 * ȡ������
		 * 
		 * @return String
		 */
		public String getName() {
			return bttGroupTitle.getText();
		}

		/**
		 * ���һ����Ա���
		 * 
		 * @param index
		 *            int ˳���
		 * @param c
		 *            Component ��Ա���
		 */
		public void addMember(int index, Component c) {
			pMembers.add(c, index);
			pMembers.doLayout();
		}

		/**
		 * ɾ��һ����Ա���
		 * 
		 * @param index
		 *            int ˳���
		 */
		public void removeMember(int index) {
			pMembers.remove(index);
			pMembers.doLayout();
		}

		/**
		 * ȡ��һ����Ա���
		 * 
		 * @param index
		 *            int ˳���
		 * @return Component ��Ա���
		 */
		public Component getMember(int index) {
			return pMembers.getComponent(index);
		}

		/**
		 * ȡ��ȫ����Ա���
		 * 
		 * @return Component[] ��Ա���
		 */
		public Component[] getMembers() {
			Component coms[] = new Component[getMemberCount()];
			for (int i = 0; i < coms.length; i++) {
				coms[i] = pMembers.getComponent(i);
			}
			return coms;
		}

		/**
		 * ȡ�ó�Ա�������
		 * 
		 * @return int ����
		 */
		public int getMemberCount() {
			return pMembers.getComponentCount();
		}

		/**
		 * ��д��toString����
		 * 
		 * @return String
		 */
		public String toString() {
			return getName();
		}
	}

	/**
	 * /** ���Գ���
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		SubstanceLookAndFeel.setSkin(new MistAquaSkin());
		JFrame frame = new JFrame("JGroupPanel Demo");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JGroupPanel(), BorderLayout.CENTER);
		frame.setSize(150, 600);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(d.width - frame.getSize().width - 10, 10);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
}
