package com.enterprise_sss.view.panel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.vo.TableVO;

/**
 * 所有查询面板
 * 
 * @author Administrator
 * 
 */
public class FindPanel extends JPanel {

	public JTable table;          //定义表格

	public JComboBox comboBox;     //定义下拉列表框

	private String[] items;      //下拉列表框内容(查询方式)
	
	private TableVO tvo;        //表格数据、表头、类型(总的查询面板类型)
	
	private String title;      //查询面板的标题
	
	public FindPanel(){
		
	}

	public FindPanel(String title,String[] items,TableVO tvo) {
		this.title = title;
		this.items = items;
		this.tvo = tvo;
		init();
	}

	/**
	 * 初始化面板
	 *
	 */
	public void init() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.add(buildNorthPanel(), BorderLayout.NORTH);
		this.add(buildScroll(), BorderLayout.CENTER);
	}

	/**
	 * 创建北面面板
	 * @return
	 */
	public JPanel buildNorthPanel() {
		JPanel panel = new JPanel();
		comboBox = CommonComponent.buildComboBox(items, null, null, null, null,
				null, false, true);
		panel.add(comboBox);
		return panel;
	}

	/**
	 * 创建表格
	 * @return
	 */
	public JScrollPane buildScroll() {
		JScrollPane scroll = new JScrollPane();
		table = CommonComponent.buildTable(tvo.getData(), tvo.getTitle());
		scroll.setViewportView(table);
		return scroll;
	}

}
