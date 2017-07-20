package com.enterprise_sss.view.panel;

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
 * 类似QQ界面的组群管理面板
 * 
 * @author Caicai
 * 
 */
public class JGroupPanel extends JPanel {

	private MainFrame frame;

	/* 用来管理组的三个容器 */
	private JPanel pNorth = new JPanel();

	private JPanel pCenter = new JPanel();

	private JPanel pSouth = new JPanel();
	
	private PaymentServer ps=new PaymentServer();
	
	private ReceivableServer rs=new ReceivableServer();

	/* 当前全部组的集合 */
	private ArrayList groupList = new ArrayList();

	/* 是否已禁止添加组件 */
	private boolean forbidFlag = false;

	/* 当前激活的组 */
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
			if ("资料管理".equals(str)) {
				items = new String[]{"","查询商品资料","查询仓库资料","查询采购合同资料","查询客户资料","查询业务员资料","查询供货商资料"};
				frame.getPane().remove(frame.getPane().getRightComponent());
				frame.getPane().setRightComponent(new DataFindPanel("资料查询", items, tvo));
			} else if ("采购管理".equals(str)) {
				title.add("订单编号");
				title.add("供货商编号");
				title.add("订货日期");
				title.add("有效起日");
				title.add("有效止日");
				title.add("业务员编号");
				title.add("制单人");
				tvo.setTitle(title);
				frame.getPane().remove(frame.getPane().getRightComponent());
				frame.getPane().setRightComponent(new PurchaseOrderPanel(str,tvo));
			} else if ("销售管理".equals(str)) {
				frame.getPane().remove(frame.getPane().getRightComponent());
				frame.getPane().setRightComponent(new SaleTabbedPane());
			} else if ("库存管理".equals(str)) {
				frame.getPane().remove(frame.getPane().getRightComponent());
				frame.getPane().setRightComponent(new QueryDepotPanel());
			} else if ("应付款管理".equals(str)) {
				TableVO aptable=new TableVO(0,ps.getAccountPayable(),ps.getAccountPayableTitle());
				TableVO pibtable=new TableVO(0,new Vector(),ps.getPurchaseInBillTitle());
				PaymentDetail dp=new PaymentDetail("应付款查询","应付款列表",aptable,"进货单详细信息",pibtable);
			} else if ("应收款管理".equals(str)) {
				TableVO artable=new TableVO(0,rs.getAccountReceivable(),rs.getAccountReceivableTitle());
				TableVO aotable=new TableVO(0,new Vector(),rs.getSaleBillTitle());
				ReceivableDetail dp=new ReceivableDetail("应收款查询","应收款列表",artable,"销售单详细信息",aotable);
			} else if ("统计分析".equals(str)) {
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
		String ListButtonicon ="image/icon/button.png";/*设置左侧下拉菜单button图片*/
		insertGroup(0, "资料管理");
		addMember(0, buildJButton("商品资料维护", ListButtonicon));
		addMember(0, buildJButton("供货商资料维护", ListButtonicon));
		addMember(0, buildJButton("客户资料维护", ListButtonicon));
		addMember(0, buildJButton("业务员信息维护", ListButtonicon));
		addMember(0, buildJButton("仓库信息维护", ListButtonicon));
		addMember(0, buildJButton("采购合同管理", ListButtonicon));
		addMember(0, buildJButton("资料查询", ListButtonicon));
		getGroup(0).setMemberGap(3, 0);


		insertGroup(1, "应付款管理");
		addMember(1, buildJButton("预付款管理", ListButtonicon));
		addMember(1, buildJButton("应付款汇总", ListButtonicon));
		addMember(1, buildJButton("付款单", ListButtonicon));
		addMember(1, buildJButton("已付款明细", ListButtonicon));
		addMember(1, buildJButton("应付款查询", ListButtonicon));
		getGroup(1).setMemberGap(3, 0);

		insertGroup(2, "应收款管理");
		addMember(2, buildJButton("预收款管理", ListButtonicon));
		addMember(2, buildJButton("应收款汇总", ListButtonicon));
		addMember(2, buildJButton("收款单", ListButtonicon));
		addMember(2, buildJButton("已收款明细", ListButtonicon));
		addMember(2, buildJButton("应收款查询", ListButtonicon));
		getGroup(2).setMemberGap(3, 0);

		insertGroup(3, "帐务管理");
		addMember(3, buildJButton("结帐日期设定", ListButtonicon));
		addMember(3, buildJButton("结帐", ListButtonicon));
		addMember(3, buildJButton("进销存汇总", ListButtonicon));
		addMember(3, buildJButton("历史查询", ListButtonicon));
		getGroup(3).setMemberGap(3, 0);

		insertGroup(4, "统计分析");
		addMember(4, buildJButton("采购统计", ListButtonicon));
		addMember(4, buildJButton("销售统计", ListButtonicon));
		addMember(4, buildJButton("库存统计", ListButtonicon));
		getGroup(4).setMemberGap(3, 0);

		expandGroup(1);
		hasCreateDefaultGroup = true;
	}

	/**
	 * 构造工具栏按钮的公用方法
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
		button.setIconTextGap(-35);//设置背景图片与文字间的间距
		button.setBorderPainted(false);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setToolTipText(name);
/*		button.setRolloverEnabled(true);
		button.setBorderPainted(true);*/
		//设置button文字显示位置
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setContentAreaFilled(false);//设置button不画背景
//		button.setRolloverEnabled(false);
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setOpaque(false); //设置背景透明  
//		button.setBorder(new EmptyBorder(0,0,0,0));
//		button.setBorder(new MetalBorders.ButtonBorder());
//		button.setBorderPainted(false);
		button.setBounds(90, 50, 200, 50);
		button.setForeground(new Color(255,255,255));//设置字体颜色
		button .setFont(new java.awt.Font("SimHei", Font.ITALIC, 15));//设置button 字体颜色
//		button.setRolloverIcon(new ImageIcon("image/icon/buttonBg.png"));//设置鼠标进来之后JButton的背景图片  
//		button.setPressedIcon(new ImageIcon("image/icon/buttonBg.png"));//设置鼠标点击之后JButton的背景图片  
		// 添加按钮事件
		button.addActionListener(new JGroupPanelAction(this.frame));
		return button;
	}

	/**
	 * 展开组
	 * 
	 * @param name
	 *            String 组名
	 */
	public void expandGroup(String name) {
		for (int i = getGroupCount() - 1; i >= 0; i--) {
			if (getGroupName(i).equals(name)) {
				expandGroup(i);
			}
		}
	}

	/**
	 * 展开组
	 * 
	 * @param index
	 *            int 组的顺序号
	 */
	public void expandGroup(int index) {
		expandGroup(getGroup(index));
	}

	/**
	 * 展开组
	 * 
	 * @param group
	 *            JGroupContainer 组
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
	 * 收缩组
	 * 
	 * @param name
	 *            String 组名
	 */
	public void collapseGroup(String name) {
		for (int i = getGroupCount() - 1; i >= 0; i--) {
			if (getGroupName(i).equals(name)) {
				collapseGroup(i);
			}
		}
	}

	/**
	 * 收缩组
	 * 
	 * @param index
	 *            int 组的顺序号
	 */
	public void collapseGroup(int index) {
		collapseGroup(getGroup(index));
	}

	/**
	 * 收缩组
	 * 
	 * @param group
	 *            JGroupContainer 组
	 */
	protected void collapseGroup(JGroupContainer group) {
		if (group == activeGroup) {
			activeGroup.collapse();
			activeGroup = null;
		}
	}

	/**
	 * 添加组
	 * 
	 * @param name
	 *            String 组名
	 */
	public void addGroup(String name) {
		this.insertGroup(getGroupCount(), name);
	}

	/**
	 * 插入一个组
	 * 
	 * @param index
	 *            int 顺序号
	 * @param name
	 *            String 组名
	 * @param bg
	 *            Color 背景色
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
	 * 插入一个组
	 * 
	 * @param index
	 *            int 顺序号
	 * @param name
	 *            String 组名
	 */
	public void insertGroup(int index, String name) {
		insertGroup(index, name, UIManager.getColor("Desktop.background"));
	}

	/**
	 * 插入一个组
	 * 
	 * @param p
	 *            JPanel 目标面板
	 * @param index
	 *            int 顺序号
	 * @param name
	 *            String 组名
	 * 
	 * 
	 * /** 插入一个组
	 * @param p
	 *            JPanel 目标面板
	 * @param index
	 *            int 顺序号
	 * @param name
	 *            String 组名
	 * @return JGroupContainer
	 */
	private JGroupContainer insertGroup(JPanel p, int index, String name,
			Color bg) {
		JGroupContainer group = new JGroupContainer(name, bg);
		p.add(group);
		return group;
	}

	/**
	 * 删除一个组
	 * 
	 * @param index
	 *            int 顺序号
	 */
	public void removeGroup(int index) {
		JGroupContainer c = (JGroupContainer) groupList.get(index);
		c.getParent().remove(c);
		c.getTitleButton().removeActionListener(al);
	}

	/**
	 * 删除一个组
	 * 
	 * @param name
	 *            String 组名
	 */
	public void removeGroup(String name) {
		for (int i = getGroupCount() - 1; i >= 0; i--) {
			if (getGroupName(i).equals(name)) {
				this.removeGroup(i);
			}
		}
	}

	/**
	 * 设置组名
	 * 
	 * @param index
	 *            int 顺序号
	 * @param name
	 *            String 组名
	 */
	public void setGroupName(int index, String name) {
		this.getGroup(index).setName(name);
	}

	/**
	 * 取得组名
	 * 
	 * @param groupIndex
	 *            int 顺序号
	 * @return String 组名
	 */
	public String getGroupName(int groupIndex) {
		return getGroup(groupIndex).getName();
	}

	/**
	 * 取得全部组名
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
	 * 取得当前组的总数
	 * 
	 * @return int
	 */
	public int getGroupCount() {
		return groupList.size();
	}

	/**
	 * 往组中添加成员组件
	 * 
	 * @param groupIndex
	 *            int 组的顺序号
	 * @param member
	 *            Component 成员组件
	 */
	public void addMember(int groupIndex, Component member) {
		getGroup(groupIndex).addMember(getGroup(groupIndex).getMemberCount(),
				member);
	}

	/**
	 * 往组中插入成员组件
	 * 
	 * @param groupIndex
	 *            int 组的顺序号
	 * @param memberIndex
	 *            int 插入的顺序号
	 * @param member
	 *            Component 成员组件
	 */
	public void insertMember(int groupIndex, int memberIndex, Component member) {
		getGroup(groupIndex).addMember(memberIndex, member);
	}

	/**
	 * 从组中移除成员组件
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
	 * 取得成员组件
	 * 
	 * @param groupIndex
	 *            int 组的顺序号
	 * @param memberIndex
	 *            int 成员组件的顺序号
	 * @return Component 成员组件
	 */
	public Component getMember(int groupIndex, int memberIndex) {
		return getGroup(groupIndex).getMember(memberIndex);
	}

	/**
	 * 取得全部成员组件
	 * 
	 * @param groupIndex
	 *            int 组的顺序号
	 * @return Component[] 全部成员组件
	 */
	public Component[] getMembers(int groupIndex) {
		return getGroup(groupIndex).getMembers();
	}

	/**
	 * 取得成员组件的总数
	 * 
	 * @param groupIndex
	 *            int 组的顺序号
	 * @return int 总数
	 */
	public int getMemberCount(int groupIndex) {
		return getGroup(groupIndex).getMemberCount();
	}

	/**
	 * 取得组
	 * 
	 * @param index
	 *            int 组的顺序号
	 * @return JGroupContainer 组
	 */
	protected JGroupContainer getGroup(int index) {
		return (JGroupContainer) groupList.get(index);
	}

	/**
	 * 覆写的addImpl方法,禁止再向JGroupPane中添加组件
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
	 * Description: 组面板布局管理器
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
	 * Description: 组
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
		 *            String 组名
		 * @param background
		 *            Color 成员组件所在面板背景色
		 */
		public JGroupContainer(String name, Color background) {	
/*	        ImageIcon topPanelIcon = new ImageIcon("image/icon/buttonMenu.png");
			  BackgroundPanel topPanel = new BackgroundPanel(topPanelIcon);
		      Dimension topPanelDimension = new Dimension(topPanelIcon.getIconWidth(), topPanelIcon.getIconHeight());
		      topPanel.setPreferredSize(topPanelDimension);// 设置面板最佳大小
*/			Color buttonColor = new Color(255,255,255);//背影颜色随便设任意值,只起占位作用。  
			bttGroupTitle.setText(name);
			bttGroupTitle.setFocusable(false);
			bttGroupTitle.setIcon(new ImageIcon("image/icon/buttonMenu.png"));
			pMembers.setLayout(new GroupLayout(0, 0));
			bttGroupTitle.setHorizontalTextPosition(JButton.CENTER);
			bttGroupTitle.setVerticalTextPosition(JButton.BOTTOM);
			bttGroupTitle.setIconTextGap(-35);//设置背景图片与文字间的间距
			bttGroupTitle .setFont(new java.awt.Font("SimHei", Font.BOLD, 17));//设置button 字体颜色
			bttGroupTitle.setForeground(new Color(189,220,239));//设置字体颜色
			bttGroupTitle.setRolloverEnabled(false);
			bttGroupTitle.setFocusPainted(false);
			bttGroupTitle.setContentAreaFilled(false);//设置button不画背景
			bttGroupTitle.setBorderPainted(false);	
			bttGroupTitle.setBackground(buttonColor);  
			bttGroupTitle.setOpaque(false); //设置背景透明 
			bttGroupTitle.setMargin(new Insets(0,0,0,0));
			this.setLayout(new BorderLayout(0,0));
			this.add(bttGroupTitle, BorderLayout.NORTH);//更替按钮背景图片
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
		 * 设置间距
		 * 
		 * @param hgap
		 *            int 横间距
		 * @param vgap
		 *            int 竖间距
		 */
		public void setMemberGap(int hgap, int vgap) {
			pMembers.setLayout(new GroupLayout(hgap, vgap));
		}

		/**
		 * 取得组的标题按钮
		 * 
		 * @return JButton
		 */
		public JButton getTitleButton() {
			return bttGroupTitle;
		}

		/**
		 * 取得组的成员组件面板
		 * 
		 * @return JPanel
		 */
		public JPanel getMembersContainer() {
			return pMembers;
		}

		/**
		 * 收缩组
		 */
		public void collapse() {
			sp.setVisible(false);
			this.revalidate();
		}

		/**
		 * 展开组
		 */
		public void expand() {
			sp.setVisible(true);
			this.revalidate();
		}

		/**
		 * 设置组名
		 * 
		 * @param name
		 *            String 组名
		 */
		public void setName(String name) {
			bttGroupTitle.setText(name);
		}

		/**
		 * 取得组名
		 * 
		 * @return String
		 */
		public String getName() {
			return bttGroupTitle.getText();
		}

		/**
		 * 添加一个成员组件
		 * 
		 * @param index
		 *            int 顺序号
		 * @param c
		 *            Component 成员组件
		 */
		public void addMember(int index, Component c) {
			pMembers.add(c, index);
			pMembers.doLayout();
		}

		/**
		 * 删除一个成员组件
		 * 
		 * @param index
		 *            int 顺序号
		 */
		public void removeMember(int index) {
			pMembers.remove(index);
			pMembers.doLayout();
		}

		/**
		 * 取得一个成员组件
		 * 
		 * @param index
		 *            int 顺序号
		 * @return Component 成员组件
		 */
		public Component getMember(int index) {
			return pMembers.getComponent(index);
		}

		/**
		 * 取得全部成员组件
		 * 
		 * @return Component[] 成员组件
		 */
		public Component[] getMembers() {
			Component coms[] = new Component[getMemberCount()];
			for (int i = 0; i < coms.length; i++) {
				coms[i] = pMembers.getComponent(i);
			}
			return coms;
		}

		/**
		 * 取得成员组件总数
		 * 
		 * @return int 总数
		 */
		public int getMemberCount() {
			return pMembers.getComponentCount();
		}

		/**
		 * 重写的toString方法
		 * 
		 * @return String
		 */
		public String toString() {
			return getName();
		}
	}

	/**
	 * /** 测试程序
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
