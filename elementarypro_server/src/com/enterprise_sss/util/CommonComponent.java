package com.enterprise_sss.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.LabelUI;
import javax.swing.plaf.MenuBarUI;
import javax.swing.plaf.PopupMenuUI;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.plaf.TextUI;
import javax.swing.plaf.ToolBarUI;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.ui.WButtonUI;
import com.enterprise_sss.ui.WJMenuBar;

/**
 * 公用组件类
 * 
 * @author Wang ming 2009-10-12
 * @version 1.0
 * 
 */
public class CommonComponent {

	/**
	 * 创建按钮
	 * 
	 * @param title
	 *            按钮标题
	 * @param bgColor
	 *            按钮背景颜色
	 * @param icon
	 *            按钮图标
	 * @param forColor
	 *            按钮前景色
	 * @param font
	 *            标题字体样式
	 * @param ui
	 *            按钮风格
	 * @param size
	 *            按钮大小
	 * @param isEnable
	 *            按钮是否可用
	 * @return 返回按钮
	 */
	public static JButton buildButton(String title, Color bgColor,
			ImageIcon icon, Color forColor, Font font, ButtonUI ui,
			Dimension size, boolean isEnable) {
		JButton button = new JButton();
		if (title != null)
			button.setText(title); // 设置标题
		if (bgColor != null)
			button.setBackground(bgColor); // 设置背景色
		if (icon != null)
			button.setVerticalTextPosition(JButton.BOTTOM);
			button.setHorizontalTextPosition(JButton.CENTER);
			button.setIcon(icon); // 设置图标
		if (forColor != null)
			button.setForeground(forColor); // 设置前景色
		if (font != null)
			button.setFont(font); // 设置字体样式
		if (ui != null)
			button.setUI(ui); // 设置组件风格
		if (size != null)
			
			button.setSize(size); // 设置组件大小
		button.setBorderPainted(false);
		 button.setUI(new WButtonUI());
		// button.setHorizontalAlignment(JButton.CENTER);
		// button.setVerticalAlignment(JButton.CENTER);
		button.setEnabled(isEnable); // 设置组件是否可用

		button.setToolTipText(title); // 设置组件提示信息
		return button;
	}

	/**
	 * 创建标签组件
	 * 
	 * @param title
	 * @param bgColor
	 * @param forColor
	 * @param font
	 * @param ui
	 * @param size
	 * @return 返回标签组件
	 */
	public static JLabel buildLabel(String title, Color bgColor,
			Color forColor, Font font, LabelUI ui, Dimension size) {
		JLabel label = new JLabel();

		if (title != null)
			label.setText(title);
		if (bgColor != null)
			label.setBackground(bgColor);
		if (forColor != null)
			label.setForeground(forColor);
		if (font != null)
			label.setFont(font);
		if (ui != null)
			label.setUI(ui);
		if (size != null)
			label.setSize(size);

		return label;
	}

	/**
	 * 创建文本框组件
	 * 
	 * @param type
	 *            文本框类型(JTextField,JPasswordField)
	 * @param value
	 * @param bgColor
	 * @param forColor
	 * @param font
	 * @param ui
	 * @param size
	 * @param isEditable
	 *            文本框是否可编辑
	 * @param isEnable
	 * @return 返回文本框组件
	 */
	public static JTextField buildTextField(String type, String value,
			Color bgColor, Color forColor, Font font, TextUI ui,
			Dimension size, boolean isEditable, boolean isEnable) {
		JTextField textField = null;

		if (type.equalsIgnoreCase("txt"))
			textField = new JTextField();
		else if (type.equalsIgnoreCase("pwd"))
			textField = new JPasswordField();

		if (value != null)
			textField.setText(value);
		if (bgColor != null)
			textField.setBackground(bgColor);
		if (forColor != null)
			textField.setForeground(forColor);
		if (font != null)
			textField.setFont(font);
		if (ui != null)
			textField.setUI(ui);
		if (size != null)
			textField.setSize(size);
		textField.setEditable(isEditable); // 设置本组件是否可编辑
		textField.setEnabled(isEnable);
		textField.setColumns(15); // 设置JTextField组件中的列数

		return textField;
	}
	
	/**
	 * 创建文本域组件
	 * @param value
	 * @param bgColor
	 * @param forColor
	 * @param font
	 * @param ui
	 * @param rows
	 * @param columns
	 * @param word
	 * @param isEditable
	 * @param isEnable
	 * @return
	 */
	public static JTextArea buildTextArea(String value,
			Color bgColor, Color forColor, Font font, TextUI ui,
			int rows,int columns,boolean word, boolean isEditable,
			boolean isEnable){
		JTextArea textArea=new JTextArea();
		if (value != null)
			textArea.setText(value);
		if (bgColor != null)
			textArea.setBackground(bgColor);
		if (forColor != null)
			textArea.setForeground(forColor);
		if (font != null)
			textArea.setFont(font);
		if (ui != null)
			textArea.setUI(ui);
		textArea.setRows(rows); // 设置JTextField组件中的行数
		textArea.setColumns(columns); // 设置JTextField组件中的列数
		textArea.setWrapStyleWord(word);//设置换行方式
		textArea.setEditable(isEditable); // 设置本组件是否可编辑
		textArea.setEnabled(isEnable);// 设置本组件是否可用
		textArea.setLineWrap(true);
		return textArea;
	}

	/**
	 * 创建下拉列表框组件
	 * 
	 * @param items
	 *            组件内部记录
	 * @param bgColor
	 * @param forColor
	 * @param font
	 * @param ui
	 * @param size
	 * @param isEditable
	 *            组件是否可以编辑
	 * @param isEnable
	 * @return 返回下拉列表框组件
	 */
	public static JComboBox buildComboBox(String[] items, Color bgColor,
			Color forColor, Font font, ComboBoxUI ui, Dimension size,
			boolean isEditable, boolean isEnable) {
		JComboBox comboBox = new JComboBox();

		if (items != null) {
			if (items.length > 0)
				for (String item : items)
					comboBox.addItem(item);
		}

		if (bgColor != null)
			comboBox.setBackground(bgColor);
		if (forColor != null)
			comboBox.setForeground(forColor);
		if (font != null)
			comboBox.setFont(font);
		if (ui != null)
			comboBox.setUI(ui);
		if (size != null)
			comboBox.setSize(size);
		comboBox.setEditable(isEditable);
		comboBox.setEnabled(isEnable);
		// comboBox.setSelectedIndex(0);

		return comboBox;
	}

	/**
	 * 创建进度条组件
	 * 
	 * @param value
	 * @param bgColor
	 * @param forColor
	 * @param font
	 * @param ui
	 * @param isScroll
	 *            是否呈现进度字符串
	 * @return 返回进度条组件
	 */
	public static JProgressBar buildProgressBar(int value, Color bgColor,
			Color forColor, Font font, ProgressBarUI ui, boolean isScroll) {
		JProgressBar pb = new JProgressBar();

		pb.setValue(value);
		if (bgColor != null)
			pb.setBackground(bgColor);
		if (forColor != null)
			pb.setForeground(forColor);
		if (font != null)
			pb.setFont(font);
		if (ui != null)
			pb.setUI(ui);

		// pb.setIndeterminate(isScroll);
		pb.setStringPainted(isScroll);

		return pb;
	}

	/**
	 * 产生菜单条
	 * 
	 * @param menues
	 *            所有菜单
	 * @param bgColor
	 * @param ui
	 * @return 返回菜单条
	 */
	public static JMenuBar buildMenuBar(Vector<JMenu> menues, Color bgColor,
			MenuBarUI ui) {
		
		WJMenuBar menuBar = new WJMenuBar();
		menuBar.setBackground(new Color(29, 97, 142));
		if (bgColor != null)
			menuBar.setBackground(bgColor);
		if (ui != null)
			menuBar.setUI(ui);

		if (menues != null) {
			for (JMenu m : menues)
				menuBar.add(m); // 往菜单条中添加菜单
		}
		menuBar.setBackgroundImage("image/jpg/menubar.jpg");
		return menuBar;
	}

	/**
	 * 创建顶级菜单
	 * 
	 * @param title
	 * @param toolTipText
	 *            提示文字
	 * @param items
	 *            所有菜单项
	 * @param ic
	 *            图标
	 * @param bgColor
	 * @param isEnable
	 * @return 返回顶级菜单
	 */
	public static JMenu buildMenu(String title, String toolTipText,
			Vector<JMenuItem> items, ImageIcon ic, Color bgColor,
			boolean isEnable) {
		JMenu menu = new JMenu();
		 Font font=new Font("微软雅黑",Font.PLAIN,14);
		menu.setPreferredSize(new Dimension(80,35));
		menu.setBackground(new Color(29, 97, 142));
		menu.setFont(font);
		if (title != null)
			menu.setText(title);
		if (toolTipText != null)
			menu.setToolTipText(toolTipText);
		if (ic != null)
			menu.setIcon(ic);
		if (bgColor != null)
			menu.setBackground(bgColor);

		menu.setEnabled(isEnable);

		if (items != null) {
			for (JMenuItem m : items) {
				if (m != null)
					menu.add(m); // 往菜单中添加菜单项
				else
					menu.addSeparator(); // 为菜单增加菜单项间的分割线
			}
		}

		return menu;
	}

	/**
	 * 产生菜单项
	 * 
	 * @param title
	 * @param ic
	 * @param bgColor
	 * @param mnemonic
	 *            绑定的热键
	 * @param ks
	 *            与热键绑定的快捷键
	 * @param isEnable
	 * @return
	 */
	public static JMenuItem buildMenuItem(String title, String toolTipText,
			ImageIcon ic, Color bgColor, char mnemonic, KeyStroke ks,
			boolean isEnable) {
		JMenuItem menuItem = new JMenuItem();

		if (title != null)
			menuItem.setText(title);
		if (toolTipText != null)
			menuItem.setToolTipText(toolTipText);
		if (ic != null)
			menuItem.setIcon(ic);
		if (bgColor != null)
			menuItem.setBackground(bgColor);
		if (ks != null) {
			menuItem.setMnemonic(mnemonic); // 设置热键
			menuItem.setAccelerator(ks); // 绑定热键
		}
		menuItem.setEnabled(isEnable);

		return menuItem;
	}

	/**
	 * 创建快捷菜单
	 * 
	 * @param items
	 * @param bgColor
	 * @param ui
	 * @param isEnable
	 * @return
	 */
	public static JPopupMenu buildPopupMenu(Vector items, Color bgColor,
			PopupMenuUI ui, boolean isEnable) {
		JPopupMenu pm = new JPopupMenu();
		if (items != null) {
			for (Object m : items) {
				if (m != null)
					pm.add((JComponent) m);
				else
					pm.addSeparator(); // 为菜单增加菜单项间的分割线
			}
		}

		if (bgColor != null)
			pm.setBackground(bgColor);
		if (ui != null)
			pm.setUI(ui);

		pm.setEnabled(isEnable);
		return pm;
	}

	/**
	 * 产生工具条
	 * 
	 * @param components
	 * @param bgColor
	 * @param ui
	 * @return
	 */
	public static JToolBar buildToolBar(Vector<Component> components,
			Color bgColor, ToolBarUI ui) {
		JToolBar tb = new JToolBar();

		if (bgColor != null)
			tb.setBackground(bgColor);
		if (ui != null)
			tb.setUI(ui);

		for (Component com : components)
			tb.add(com);

		return tb;
	}

	/**
	 * 创建分割分板
	 * 
	 * @param splitStyle
	 *            分割方式
	 * @param isOneTouch
	 *            是否具有辅助按钮
	 * @param autoScrolls
	 *            设置拖动时绘制方式 ?
	 * @param partOne
	 * @param partTwo
	 * @return
	 */
	public static JSplitPane buildSplitPane(int splitStyle, boolean isOneTouch,
			boolean autoScrolls, Component partOne, Component partTwo) {
		JSplitPane sp = new JSplitPane();
		sp.setOrientation(splitStyle); // 设置分割方式
		sp.setOneTouchExpandable(isOneTouch); // 设置是否具有辅助按钮

		sp.setAutoscrolls(autoScrolls);
		if (partOne != null)
			sp.setLeftComponent(partOne); // 添加左面板内容
		if (partTwo != null)
			sp.setRightComponent(partTwo); // 添加右面板内容

		return sp;
	}

	/**
	 * 单选按钮菜单项
	 * 
	 * @param title
	 * @param bgColor
	 * @param isEnable
	 * @return
	 */
	public static JRadioButtonMenuItem buildRadioItem(String title,
			Color bgColor, boolean isEnable) {
		JRadioButtonMenuItem rb = new JRadioButtonMenuItem();
		if (title != null)
			rb.setText(title);
		if (bgColor != null)
			rb.setBackground(bgColor);

		rb.setEnabled(isEnable);
		return rb;
	}

	/**
	 * 创建表格
	 * 
	 * @param data
	 *            表格数据
	 * @param title
	 *            表格标题
	 * @return
	 */
	public static JTable buildTable(Vector data, Vector title) {
		JTable table = null;
		// 方法一，用DefaultTableModel类创建表单,只用重写getColumnClass方法
		DefaultTableModel mode = new DefaultTableModel(data, title){
			public Class<?> getColumnClass(int columnIndex) {
				return ((Vector)dataVector.get(0)).get(columnIndex).getClass();
			}
		};
		table = new JTable(mode);
		// table.setPreferredScrollableViewportSize(new Dimension(600, 600));

		return table;
	}

	/**
	 * 创建单选按钮面板
	 * 
	 * @param names
	 * @param icones
	 * @param isEnables
	 * @param selectIndex
	 * @return
	 */
	public static JPanel buildRadioGroup(String[] names, ImageIcon[] icones,
			boolean[] isEnables, int selectIndex) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());

		ButtonGroup bg = new ButtonGroup();

		if (names != null) {
			int i = 0;
			for (String name : names) {
				JRadioButton rb = new JRadioButton(name);
				if (icones != null)
					rb.setIcon(icones[i]);
				if (i == selectIndex)
					rb.setSelected(true);
				rb.setEnabled(isEnables[i]);
				i++;

				bg.add(rb);

				panel.add(rb);
			}
		}

		return panel;
	}

	/**
	 * 创建复选框
	 * 
	 * @param name
	 * @return
	 */
	public static JCheckBox buildCheckBox(String name) {
		JCheckBox checkBox = new JCheckBox();
		if (name != null)
			checkBox.setText(name);
		return checkBox;
	}
}
