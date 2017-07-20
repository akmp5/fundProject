package com.enterprise_sss.view.panel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.vo.TableVO;

/**
 * 所有维护面板的父类
 * 
 * @author Administrator
 * 
 */
public abstract class DataMaintenancePanel extends JPanel {

	public JTable table;

	private String title;

	private TableVO tvo;
	
	public JButton button_add,button_del,button_modify,button_find,button_reset;

	public DataMaintenancePanel() {

	}

	public DataMaintenancePanel(String title, TableVO tvo) {
		this.title = title;
		this.tvo = tvo;
	}

	public void init() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.add(buildCenterPanel(), BorderLayout.CENTER);
		this.add(buildSouthPanel(), BorderLayout.SOUTH);
		this.add(buildNorthPanel(), BorderLayout.NORTH);
	}

	public JScrollPane buildCenterPanel() {
		JScrollPane scroll = new JScrollPane();
		table = CommonComponent.buildTable(tvo.getData(), tvo.getTitle());
		scroll.setViewportView(table);
		return scroll;
	}

	public JPanel buildSouthPanel() {
		JPanel panel = new JPanel();
		panel.add(button_find = CommonComponent.buildButton("查询", null, null, null, null, null, null, true));
		panel.add(button_add = CommonComponent.buildButton("添加", null, null, null, null, null, null, true));
		panel.add(button_del = CommonComponent.buildButton("删除", null, null, null, null, null, null, true));
		panel.add(button_modify = CommonComponent.buildButton("修改", null, null, null, null, null, null, true));
		panel.add(button_reset = CommonComponent.buildButton("重置", null, null, null, null, null, null, true));
		return panel;
	}

//	public JButton buildButton(String title, Color bgColor, ImageIcon icon,
//			Color forColor, Font font, ButtonUI ui, Dimension size,
//			boolean isEnable) {
//		JButton button = CommonComponent.buildButton(title, bgColor, icon,
//				forColor, font, ui, size, isEnable);
//
//		return button;
//	}

	public abstract JPanel buildNorthPanel();

}
