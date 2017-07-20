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
 * 总管理员界面
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
	 * 构造函数,用于实例化窗体,并接收登录窗体传过来的值
	 * 
	 * @param user
	 *            接收登录用户名
	 */
	public MainFrame(UserVO user) {
		this.user = user;
		DataUtil.name = user.getUser();
		init();
	}

	/**
	 * 初始化窗体
	 * 
	 */
	public void init() {
		// 设置窗体名称
		this.setTitle("企业进销存管理系统");
		// 设置窗体大小
		this.setSize(750, 600);
		// 设置窗体居中
		this.setLocationRelativeTo(null);
		// 设置窗体图标
		this.setIconImage(new ImageIcon("image/icon/JXCicon.png").getImage());
		// 设置窗体close方法
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		// 设置窗体不能改变大小
		// this.setResizable(false);
		// 设置窗体初始化为最大化
		this.setExtendedState(this.MAXIMIZED_BOTH);
		// 添加窗体事件
		this.addWindowListener(action);
		this.setJMenuBar(buildMenuBar());
		this.add(buildSplitPane());
		this.add(buildJToolBar(), BorderLayout.NORTH);
		// 设置窗体显示
		this.setVisible(true);
	}

	/**
	 * 初始化事件
	 * 
	 */
	public void initAction() {

	}
	
	/**
	 * buildJToolBar 导入 导出 等功能性菜单
	 * @return
	 */
	public JToolBar buildJToolBar() {
		JToolBar toolBar = new JToolBar();
		ImagePanel imagePanel = new ImagePanel("image/jpg/toolbar.jpg");
		imagePanel.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.add(exp = CommonComponent.buildButton("导出", null, new ImageIcon(
				"image/icon/export.png"), null, null, null, null, true),
				new GBC(0, 0).setAnchor(GBC.NORTH).setFill(GBC.BOTH).setInset(
						30, 5, 0, 0));
		panel.add(imp = CommonComponent.buildButton("导入", null, new ImageIcon(
				"image/icon/import.png"), null, null, null, null, true),
				new GBC(1, 0).setAnchor(GBC.NORTH).setFill(GBC.BOTH).setInset(
						30, 0, 0, 0));
		panel.add(home = CommonComponent.buildButton("主页", null, new ImageIcon(
				"image/icon/home.png"), null, null, null, null, true), new GBC(
				2, 0).setAnchor(GBC.NORTH).setFill(GBC.BOTH).setInset(30, 0, 0,
				0));
		panel.add(exit = CommonComponent.buildButton("退出", null, new ImageIcon(
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
				int i = JOptionPane.showConfirmDialog(null, "确定退出？", "系统提示",
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
	 * 创建分割面板的左面板
	 * 
	 * @return
	 */
	public JPanel buildLeftPanel() {
		return new JGroupPanel(this);
	}

	/**
	 * 创建分割面板的右面板
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
	 * 创建南部面板,即状态档
	 * 
	 * @return
	 */
	public JPanel buildSouthPanel() {

		return null;
	}

	/**
	 * 创建菜单条(调用CommonComponent类中的buildMenuBar公共方法)
	 * 
	 * @return
	 */
	public JMenuBar buildMenuBar() {
		Vector<JMenu> menuBar = new Vector<JMenu>();
		
		// 第一个菜单:资料管理
		Vector<JMenuItem> menu_one = new Vector<JMenuItem>();
		JMenu menu_one_commodity = CommonComponent.buildMenu("商品信息维护", "",
				null, null, null, true);
		menu_one_commodity.add(buildMenuItem("添加商品信息", "添加商品信息", null, null,
				'n', null, true));
		menu_one_commodity.add(buildMenuItem("修改商品信息", "修改商品信息", null, null,
				'n', null, true));
		menu_one_commodity.add(buildMenuItem("删除商品信息", "删除商品信息", null, null,
				'n', null, true));
		menu_one_commodity.add(buildMenuItem("查询商品信息", "查询商品信息", null, null,
				'n', null, true));
		menu_one.add(menu_one_commodity);

		JMenu menu_one_depot = CommonComponent.buildMenu("仓库信息维护", "", null,
				null, null, true);
		menu_one_depot.add(buildMenuItem("添加仓库信息", "添加仓库信息", null, null, 'n',
				null, true));
		menu_one_depot.add(buildMenuItem("修改仓库信息", "修改仓库信息", null, null, 'n',
				null, true));
		menu_one_depot.add(buildMenuItem("删除仓库信息", "删除仓库信息", null, null, 'n',
				null, true));
		menu_one_depot.add(buildMenuItem("查询仓库信息", "查询仓库信息", null, null, 'n',
				null, true));
		menu_one.add(menu_one_depot);

		JMenu menu_one_client = CommonComponent.buildMenu("客户信息维护", "", null,
				null, null, true);
		menu_one_client.add(buildMenuItem("添加客户信息", "添加客户信息", null, null, 'n',
				null, true));
		menu_one_client.add(buildMenuItem("修改客户信息", "修改客户信息", null, null, 'n',
				null, true));
		menu_one_client.add(buildMenuItem("删除客户信息", "删除客户信息", null, null, 'n',
				null, true));
		menu_one_client.add(buildMenuItem("查询客户信息", "查询客户信息", null, null, 'n',
				null, true));
		menu_one.add(menu_one_client);

		JMenu menu_one_supplier = CommonComponent.buildMenu("供货商信息维护", "",
				null, null, null, true);
		menu_one_supplier.add(buildMenuItem("添加供货商信息", "添加供货商信息", null, null,
				'n', null, true));
		menu_one_supplier.add(buildMenuItem("修改供货商信息", "修改供货商信息", null, null,
				'n', null, true));
		menu_one_supplier.add(buildMenuItem("删除供货商信息", "删除供货商信息", null, null,
				'n', null, true));
		menu_one_supplier.add(buildMenuItem("查询供货商信息", "查询供货商信息", null, null,
				'n', null, true));
		menu_one.add(menu_one_supplier);

		JMenu menu_one_operator = CommonComponent.buildMenu("业务员信息维护", "",
				null, null, null, true);
		menu_one_operator.add(buildMenuItem("添加业务员信息", "添加业务员信息", null, null,
				'n', null, true));
		menu_one_operator.add(buildMenuItem("修改业务员信息", "修改业务员信息", null, null,
				'n', null, true));
		menu_one_operator.add(buildMenuItem("删除业务员信息", "删除业务员信息", null, null,
				'n', null, true));
		menu_one_operator.add(buildMenuItem("查询业务员信息", "查询业务员信息", null, null,
				'n', null, true));
		menu_one.add(menu_one_operator);

		JMenu menu_one_purchase = CommonComponent.buildMenu("采购合同信息维护", "",
				null, null, null, true);
		menu_one_purchase.add(buildMenuItem("添加采购合同信息", "添加采购合同信息", null, null,
				'n', null, true));
		menu_one_purchase.add(buildMenuItem("修改采购合同信息", "修改采购合同信息", null, null,
				'n', null, true));
		menu_one_purchase.add(buildMenuItem("删除采购合同信息", "删除采购合同信息", null, null,
				'n', null, true));
		menu_one_purchase.add(buildMenuItem("查询采购合同信息", "查询采购合同信息", null, null,
				'n', null, true));
		menu_one.add(menu_one_purchase);

		menu_one
				.add(buildMenuItem("资料查询", "资料查询", null, null, 'n', null, true));

		// 第二个菜单:采购管理
		Vector<JMenuItem> menu_two = new Vector<JMenuItem>();
		menu_two.add(buildMenuItem("采购订单", "采购订单", null, null, 'n', null, true));
		menu_two.add(buildMenuItem("进货单", "进货单", null, null, 'n', null, true));
		menu_two.add(buildMenuItem("退货单", "进货单", null, null, 'n', null, true));
		// menu_two.add(buildMenuIt
		// em("进货价格调整","进货价格调整",null,null,'n',null,true));
		// menu_two.add(buildMenuItem("进货单位调整","进货单位调整",null,null,'n',null,true));
		// menu_two
		// .add(buildMenuItem("采购查询", "采购查询", null, null, 'n', null, true));

		// 第三个菜单:销售管理
		Vector<JMenuItem> menu_three = new Vector<JMenuItem>();
		menu_three.add(buildMenuItem("生成销售订单", "生成销售订单", null, null, 'n', null,
				true));
		menu_three.add(buildMenuItem("生成销售单", "生成销售单", null, null, 'n', null,
				true));
		menu_three.add(buildMenuItem("销售退货单", "销售退货单", null, null, 'n', null,
				true));
		menu_three.add(buildMenuItem("调整历史售价", "调整历史售价", null, null, 'n', null,
				true));
		menu_three.add(buildMenuItem("销售查询", "销售查询", null, null, 'n', null,
				true));

		// 第四个菜单:库存管理
		Vector<JMenuItem> menu_four = new Vector<JMenuItem>();
		menu_four
				.add(buildMenuItem("库存查询", "库存查询", null, null, 'n', null, true));
		menu_four
				.add(buildMenuItem("库存转库", "库存转库", null, null, 'n', null, true));
		menu_four
				.add(buildMenuItem("库存盘点", "库存盘点", null, null, 'n', null, true));
		menu_four
				.add(buildMenuItem("报损报溢", "报损报溢", null, null, 'n', null, true));
		menu_four.add(buildMenuItem("上下限设定", "上下限设定", null, null, 'n', null,
				true));
		menu_four.add(buildMenuItem("上下限报警", "上下限报警", null, null, 'n', null,
				true));

		// 第五个菜单:应付款管理
		Vector<JMenuItem> menu_five = new Vector<JMenuItem>();
		JMenu deposit_payment = CommonComponent.buildMenu("预付款管理", "", null,
				null, null, true);
		deposit_payment.add(buildMenuItem("添加预付款", "添加预付款", null, null, 'n',
				null, true));
		deposit_payment.add(buildMenuItem("修改预付款", "修改预付款", null, null, 'n',
				null, true));
		deposit_payment.add(buildMenuItem("删除预付款", "删除预付款", null, null, 'n',
				null, true));
		deposit_payment.add(buildMenuItem("查询预付款", "查询预付款", null, null, 'n',
				null, true));
		menu_five.add(deposit_payment);
		menu_five.add(buildMenuItem("应付款查询", "查询应付款明细", null, null, 'n', null,
				true));
		menu_five.add(buildMenuItem("应付款汇总", "应付款汇总", null, null, 'n', null,
				true));
		JMenu payment_manage = CommonComponent.buildMenu("付款单管理", "", null,
				null, null, true);
		payment_manage.add(buildMenuItem("添加付款单", "添加付款单", null, null, 'n',
				null, true));
		payment_manage.add(buildMenuItem("修改付款单", "修改付款单", null, null, 'n',
				null, true));
		payment_manage.add(buildMenuItem("删除付款单", "删除付款单", null, null, 'n',
				null, true));
		payment_manage.add(buildMenuItem("查询付款单", "查询付款单", null, null, 'n',
				null, true));
		menu_five.add(payment_manage);
		menu_five.add(buildMenuItem("已付款查询", "查询已付款明细", null, null, 'n', null,
				true));
		menu_five.add(buildMenuItem("付款单历史查询", "查询付款单历史", null, null, 'n',
				null, true));

		// 第六个菜单:应收款管理
		Vector<JMenuItem> menu_six = new Vector<JMenuItem>();
		JMenu deposit_receivable = CommonComponent.buildMenu("预收款管理", "", null,
				null, null, true);
		deposit_receivable.add(buildMenuItem("添加预收款", "添加预收款", null, null, 'n',
				null, true));
		deposit_receivable.add(buildMenuItem("修改预收款", "修改预收款", null, null, 'n',
				null, true));
		deposit_receivable.add(buildMenuItem("删除预收款", "删除预收款", null, null, 'n',
				null, true));
		deposit_receivable.add(buildMenuItem("查询预收款", "查询预收款", null, null, 'n',
				null, true));
		menu_six.add(deposit_receivable);
		menu_six.add(buildMenuItem("应收款查询", "查询应收款明细", null, null, 'n', null,
				true));
		menu_six.add(buildMenuItem("应收款汇总", "应收款汇总", null, null, 'n', null,
				true));
		JMenu receivable_manage = CommonComponent.buildMenu("收款单管理", "", null,
				null, null, true);
		receivable_manage.add(buildMenuItem("添加收款单", "添加收款单", null, null, 'n',
				null, true));
		receivable_manage.add(buildMenuItem("修改收款单", "修改收款单", null, null, 'n',
				null, true));
		receivable_manage.add(buildMenuItem("删除收款单", "删除收款单", null, null, 'n',
				null, true));
		receivable_manage.add(buildMenuItem("查询收款单", "查询收款单", null, null, 'n',
				null, true));
		menu_six.add(receivable_manage);
		menu_six.add(buildMenuItem("已收款查询", "查询已收款明细", null, null, 'n', null,
				true));
		menu_six.add(buildMenuItem("收款单历史查询", "查询收款单历史", null, null, 'n', null,
				true));

		// 第七个菜单:怅账管理
		Vector<JMenuItem> menu_seven = new Vector<JMenuItem>();
		 
		menu_seven.add(buildMenuItem("结帐日期设定", "设定结帐日期", null, null, 'n', null,
				true));
		menu_seven
				.add(buildMenuItem("结帐", "模拟结帐", null, null, 'n', null, true));
		menu_seven.add(buildMenuItem("进销存汇总", "进销存汇总", null, null, 'n', null,
				true));
		menu_seven.add(buildMenuItem("结帐历史查询", "查询历史结帐结果", null, null, 'n',
				null, true));

		// 第八个菜单:统计分析
		Vector<JMenuItem> menu_eight = new Vector<JMenuItem>();
		menu_eight.add(buildMenuItem("采购统计", "采购统计", null, null, 'n', null,
				true));
		menu_eight.add(buildMenuItem("销售统计", "销售统计", null, null, 'n', null,
				true));
		menu_eight.add(buildMenuItem("库存统计", "库存统计", null, null, 'n', null,
				true));

		// 第九个菜单:系统设置
		Vector<JMenuItem> menu_night = new Vector<JMenuItem>();
		menu_night.add(buildMenuItem("查看日志", "查看日志", null, null, 'n', null,
				true));
		menu_night.add(buildMenuItem("更改密码", "更改密码", null, null, 'n', null,
				true));

		menuBar.add(CommonComponent.buildMenu("资料维护", "资料信息维护", menu_one, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("采购管理", "采购管理", menu_two, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("销售管理", "销售管理", menu_three, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("库存管理", "库存管理", menu_four, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("统计分析", "统计分析", menu_eight, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("应付款管理", "应付款信息管理", menu_five,
				null, null, true));
		menuBar.add(CommonComponent.buildMenu("应收款管理", "应收款信息管理", menu_six,
				null, null, true));
		menuBar.add(CommonComponent.buildMenu("帐务管理", "帐务管理", menu_seven, null,
				null, true));
		menuBar.add(CommonComponent.buildMenu("系统设置", "系统设置", menu_night, null,
				null, true));
		//MenuBarUI background= new MenuBarUI();
		JMenuBar jb = CommonComponent.buildMenuBar(menuBar, Color.RED, null);
		jb.setBackground(Color.blue);
		
		return jb;
	}

	/**
	 * 创建菜单选项(调用CommonComponent类中的buildMenuItem公共方法)
	 * 
	 * @return
	 */
	public JMenuItem buildMenuItem(String title, String toolTipText,
			ImageIcon ic, Color bgColor, char mnemonic, KeyStroke ks,
			boolean isEnable) {
		JMenuItem item = CommonComponent.buildMenuItem(title, toolTipText, ic,
				bgColor, mnemonic, ks, isEnable);
		 Font font=new Font("微软雅黑",Font.PLAIN,14);
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
