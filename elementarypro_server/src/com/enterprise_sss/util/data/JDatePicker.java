package com.enterprise_sss.util.data;

import java.io.*;
import java.text.*;
import java.util.Date;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.plaf.metal.*;

import com.sun.java.swing.plaf.motif.*;
import com.sun.java.swing.plaf.windows.*;

/**
 * @version 1.0
 */
public class JDatePicker extends JComboBox implements Serializable {
	/**
   * 
   */
	private static final long serialVersionUID = 1L;
	/**
	 * 日期格式类型
	 */
	public static final int STYLE_CN_DATE = 0;
	public static final int STYLE_CN_DATE1 = 1;
	public static final int STYLE_CN_DATETIME = 2;
	public static final int STYLE_CN_DATETIME1 = 3;
	/**
	 * 日期格式类型
	 */
	private int formatStyle = STYLE_CN_DATE;
	/**
	 * 当前设置日期格式
	 */
	private SimpleDateFormat dateFormat = null;

	/**
	 * 只有一个值的ComboBoxModel
	 */
	private SingleObjectComboBoxModel model = new SingleObjectComboBoxModel();
	JDateDocument dateDocument = null;

	/**
	 * 构造式
	 */
	public JDatePicker() throws UnsupportedOperationException {
		this(STYLE_CN_DATE);
	}

	public JDatePicker(int formatStyle) throws UnsupportedOperationException {
		this(formatStyle, new Date());
	}

	public JDatePicker(int formatStyle, Date initialDatetime)
			throws UnsupportedOperationException {

		this.setStyle(formatStyle);
		// 设置可编辑
		this.setEditable(true);
		// 设置编辑器属性(只能输入正确日期)
		JTextField textField = ((JTextField) getEditor().getEditorComponent());
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		dateDocument = new JDateDocument(textField, this.dateFormat);
		textField.setDocument(dateDocument);
		// 设置Model为单值Model
		this.setModel(model);
		// 设置当前选择日期
		this.setSelectedItem(initialDatetime == null ? new Date()
				: initialDatetime);
	}

	/**
	 * 设置日期格式 STYLE_CN_DATE STYLE_CN_DATE1 STYLE_CN_DATETIME STYLE_CN_DATETIME1
	 * 
	 * @param formatStyle
	 *            int
	 */
	public void setStyle(int formatStyle) throws UnsupportedOperationException {
		this.formatStyle = formatStyle;
		dateFormat = getDateFormat(formatStyle);
		model.setDateFormat(dateFormat);
		if (dateDocument != null) {
			dateDocument.setDateFormat(dateFormat);
		}
	}

	/**
	 * 取得指定类型的日期格式
	 * 
	 * @param formatStyle
	 *            int
	 * @return SimpleDateFormat
	 * @throws UnsupportedOperationException
	 */
	private static SimpleDateFormat getDateFormat(int formatStyle)
			throws UnsupportedOperationException {
		switch (formatStyle) {
		case STYLE_CN_DATE:
			 return new SimpleDateFormat("yyyy-MM-dd");
//			return new SimpleDateFormat("dd-MM-yyyy");
		case STYLE_CN_DATE1:
			return new SimpleDateFormat("yyyy/MM/dd");
		case STYLE_CN_DATETIME:
			return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		case STYLE_CN_DATETIME1:
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		default:
			throw new UnsupportedOperationException(
					"invalid formatStyle parameter!");
		}
	}

	/**
	 * 取得日期格式 STYLE_CN_DATE STYLE_CN_DATE1 STYLE_CN_DATETIME STYLE_CN_DATETIME1
	 * 
	 * @return int
	 */
	public int getStyle() {
		return formatStyle;
	}

	/**
	 * 取得当前选择的日期
	 * 
	 * @return Date
	 */
	public Date getSelectedDate() throws ParseException {
		return dateFormat.parse(getSelectedItem().toString());
	}

	/**
	 * 设置当前选择的日期
	 * 
	 * @return Date
	 */
	public void setSelectedDate(Date date) throws ParseException {
		this.setSelectedItem(dateFormat.format(date));
	}

	public void setSelectedItem(Object anObject) {
		model.setSelectedItem(anObject);
		super.setSelectedItem(anObject);
	}

	/**
	 * <p>
	 * Title: JDatePicker
	 * </p>
	 * <p>
	 * Description: DatePopup 选择框弹出的日期选择面板
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2004
	 * </p>
	 * <p>
	 * Company:
	 * </p>
	 * 
	 * @author <a href="mailto:sunkingxie@hotmail.com"'>Sunking</a>
	 * @version 1.0
	 */
	class DatePopup extends BasicComboPopup implements ChangeListener {
		/**
       * 
       */
		private static final long serialVersionUID = 1L;
		JCalendarPanel calendarPanel = null;

		public DatePopup(JComboBox box) {
			super(box);
			setLayout(new BorderLayout());
			calendarPanel = new JCalendarPanel();
			calendarPanel.addDateChangeListener(this);
			add(calendarPanel, BorderLayout.CENTER);
			setBorder(BorderFactory.createEmptyBorder());
		}

		/**
		 * 显示弹出面板
		 */
		protected void firePropertyChange(String propertyName, Object oldValue,
				Object newValue) {
			if (propertyName.equals("visible")) {
				if (oldValue.equals(Boolean.FALSE)
						&& newValue.equals(Boolean.TRUE)) { // SHOW
					try {
						String strDate = comboBox.getSelectedItem().toString();
						Date selectionDate = dateFormat.parse(strDate);
						calendarPanel.setSelectedDate(selectionDate);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else if (oldValue.equals(Boolean.TRUE)
						&& newValue.equals(Boolean.FALSE)) { // HIDE
				}
			}
			super.firePropertyChange(propertyName, oldValue, newValue);
		}

		public void stateChanged(ChangeEvent e) {
			Date selectedDate = (Date) e.getSource();
			String strDate = dateFormat.format(selectedDate);
			if (comboBox.isEditable() && comboBox.getEditor() != null) {
				comboBox.configureEditor(comboBox.getEditor(), strDate);
			}
			comboBox.setSelectedItem(strDate);
			comboBox.setPopupVisible(false);
		}
	}

	/**
	 * 更新UI
	 */
	public void updateUI() {
		ComboBoxUI cui = (ComboBoxUI) UIManager.getUI(this);
		if (cui instanceof MetalComboBoxUI) {
			cui = new MetalDateComboBoxUI();
		} else if (cui instanceof MotifComboBoxUI) {
			cui = new MotifDateComboBoxUI();
		} else {
			cui = new WindowsDateComboBoxUI();
		}
		setUI(cui);
	}

	// UI Inner classes -- one for each supported Look and Feel
	/**
	 * <p>
	 * Title: OpenSwing
	 * </p>
	 * <p>
	 * Description: MetalDateComboBoxUI
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
	class MetalDateComboBoxUI extends MetalComboBoxUI {
		protected ComboPopup createPopup() {
			return new DatePopup(comboBox);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: OpenSwing
	 * </p>
	 * <p>
	 * Description: WindowsDateComboBoxUI
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
	class WindowsDateComboBoxUI extends WindowsComboBoxUI {
		protected ComboPopup createPopup() {
			return new DatePopup(comboBox);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: OpenSwing
	 * </p>
	 * <p>
	 * Description: MotifDateComboBoxUI
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
	class MotifDateComboBoxUI extends MotifComboBoxUI {
		/**
       * 
       */
		private static final long serialVersionUID = 1L;

		protected ComboPopup createPopup() {
			return new DatePopup(comboBox);
		}
	}

	/**
	 * 测试JDatePicker
	 */
	public static void main(String args[]) {

		JFrame f = new JFrame("JDatePicker Demo");
		JPanel c = new JPanel();
		c.add(new JLabel("From:"));
		JDatePicker datePickerFrom = new JDatePicker(
				JDatePicker.STYLE_CN_DATETIME);
		c.add(datePickerFrom);
		c.add(new JLabel("To:"));
		Date d = new Date();
		d.setTime(d.getTime() + 10000000000L);
		JDatePicker datePickerTo = new JDatePicker(JDatePicker.STYLE_CN_DATE, d);
		datePickerTo.setEditable(false);
		c.add(datePickerTo);
		f.getContentPane().add(c, BorderLayout.NORTH);

		f.getContentPane().add(new JDatePicker(), BorderLayout.SOUTH);

		final JTable table = new JTable(20, 10);
		JComboBox editor = new JDatePicker();
		editor.setBorder(null);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setDefaultEditor(Object.class, new DefaultCellEditor(editor));
		JScrollPane sp = new JScrollPane(table);
		f.getContentPane().add(sp, BorderLayout.CENTER);

		// f.setSize(600, 400);
		f.setVisible(true);
	}
}
