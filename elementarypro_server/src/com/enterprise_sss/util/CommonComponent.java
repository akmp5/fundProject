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
 * ���������
 * 
 * @author Wang ming 2009-10-12
 * @version 1.0
 * 
 */
public class CommonComponent {

	/**
	 * ������ť
	 * 
	 * @param title
	 *            ��ť����
	 * @param bgColor
	 *            ��ť������ɫ
	 * @param icon
	 *            ��ťͼ��
	 * @param forColor
	 *            ��ťǰ��ɫ
	 * @param font
	 *            ����������ʽ
	 * @param ui
	 *            ��ť���
	 * @param size
	 *            ��ť��С
	 * @param isEnable
	 *            ��ť�Ƿ����
	 * @return ���ذ�ť
	 */
	public static JButton buildButton(String title, Color bgColor,
			ImageIcon icon, Color forColor, Font font, ButtonUI ui,
			Dimension size, boolean isEnable) {
		JButton button = new JButton();
		if (title != null)
			button.setText(title); // ���ñ���
		if (bgColor != null)
			button.setBackground(bgColor); // ���ñ���ɫ
		if (icon != null)
			button.setVerticalTextPosition(JButton.BOTTOM);
			button.setHorizontalTextPosition(JButton.CENTER);
			button.setIcon(icon); // ����ͼ��
		if (forColor != null)
			button.setForeground(forColor); // ����ǰ��ɫ
		if (font != null)
			button.setFont(font); // ����������ʽ
		if (ui != null)
			button.setUI(ui); // ����������
		if (size != null)
			
			button.setSize(size); // ���������С
		button.setBorderPainted(false);
		 button.setUI(new WButtonUI());
		// button.setHorizontalAlignment(JButton.CENTER);
		// button.setVerticalAlignment(JButton.CENTER);
		button.setEnabled(isEnable); // ��������Ƿ����

		button.setToolTipText(title); // ���������ʾ��Ϣ
		return button;
	}

	/**
	 * ������ǩ���
	 * 
	 * @param title
	 * @param bgColor
	 * @param forColor
	 * @param font
	 * @param ui
	 * @param size
	 * @return ���ر�ǩ���
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
	 * �����ı������
	 * 
	 * @param type
	 *            �ı�������(JTextField,JPasswordField)
	 * @param value
	 * @param bgColor
	 * @param forColor
	 * @param font
	 * @param ui
	 * @param size
	 * @param isEditable
	 *            �ı����Ƿ�ɱ༭
	 * @param isEnable
	 * @return �����ı������
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
		textField.setEditable(isEditable); // ���ñ�����Ƿ�ɱ༭
		textField.setEnabled(isEnable);
		textField.setColumns(15); // ����JTextField����е�����

		return textField;
	}
	
	/**
	 * �����ı������
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
		textArea.setRows(rows); // ����JTextField����е�����
		textArea.setColumns(columns); // ����JTextField����е�����
		textArea.setWrapStyleWord(word);//���û��з�ʽ
		textArea.setEditable(isEditable); // ���ñ�����Ƿ�ɱ༭
		textArea.setEnabled(isEnable);// ���ñ�����Ƿ����
		textArea.setLineWrap(true);
		return textArea;
	}

	/**
	 * ���������б�����
	 * 
	 * @param items
	 *            ����ڲ���¼
	 * @param bgColor
	 * @param forColor
	 * @param font
	 * @param ui
	 * @param size
	 * @param isEditable
	 *            ����Ƿ���Ա༭
	 * @param isEnable
	 * @return ���������б�����
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
	 * �������������
	 * 
	 * @param value
	 * @param bgColor
	 * @param forColor
	 * @param font
	 * @param ui
	 * @param isScroll
	 *            �Ƿ���ֽ����ַ���
	 * @return ���ؽ��������
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
	 * �����˵���
	 * 
	 * @param menues
	 *            ���в˵�
	 * @param bgColor
	 * @param ui
	 * @return ���ز˵���
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
				menuBar.add(m); // ���˵�������Ӳ˵�
		}
		menuBar.setBackgroundImage("image/jpg/menubar.jpg");
		return menuBar;
	}

	/**
	 * ���������˵�
	 * 
	 * @param title
	 * @param toolTipText
	 *            ��ʾ����
	 * @param items
	 *            ���в˵���
	 * @param ic
	 *            ͼ��
	 * @param bgColor
	 * @param isEnable
	 * @return ���ض����˵�
	 */
	public static JMenu buildMenu(String title, String toolTipText,
			Vector<JMenuItem> items, ImageIcon ic, Color bgColor,
			boolean isEnable) {
		JMenu menu = new JMenu();
		 Font font=new Font("΢���ź�",Font.PLAIN,14);
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
					menu.add(m); // ���˵�����Ӳ˵���
				else
					menu.addSeparator(); // Ϊ�˵����Ӳ˵����ķָ���
			}
		}

		return menu;
	}

	/**
	 * �����˵���
	 * 
	 * @param title
	 * @param ic
	 * @param bgColor
	 * @param mnemonic
	 *            �󶨵��ȼ�
	 * @param ks
	 *            ���ȼ��󶨵Ŀ�ݼ�
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
			menuItem.setMnemonic(mnemonic); // �����ȼ�
			menuItem.setAccelerator(ks); // ���ȼ�
		}
		menuItem.setEnabled(isEnable);

		return menuItem;
	}

	/**
	 * ������ݲ˵�
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
					pm.addSeparator(); // Ϊ�˵����Ӳ˵����ķָ���
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
	 * ����������
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
	 * �����ָ�ְ�
	 * 
	 * @param splitStyle
	 *            �ָʽ
	 * @param isOneTouch
	 *            �Ƿ���и�����ť
	 * @param autoScrolls
	 *            �����϶�ʱ���Ʒ�ʽ ?
	 * @param partOne
	 * @param partTwo
	 * @return
	 */
	public static JSplitPane buildSplitPane(int splitStyle, boolean isOneTouch,
			boolean autoScrolls, Component partOne, Component partTwo) {
		JSplitPane sp = new JSplitPane();
		sp.setOrientation(splitStyle); // ���÷ָʽ
		sp.setOneTouchExpandable(isOneTouch); // �����Ƿ���и�����ť

		sp.setAutoscrolls(autoScrolls);
		if (partOne != null)
			sp.setLeftComponent(partOne); // ������������
		if (partTwo != null)
			sp.setRightComponent(partTwo); // ������������

		return sp;
	}

	/**
	 * ��ѡ��ť�˵���
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
	 * �������
	 * 
	 * @param data
	 *            �������
	 * @param title
	 *            ������
	 * @return
	 */
	public static JTable buildTable(Vector data, Vector title) {
		JTable table = null;
		// ����һ����DefaultTableModel�ഴ����,ֻ����дgetColumnClass����
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
	 * ������ѡ��ť���
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
	 * ������ѡ��
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
