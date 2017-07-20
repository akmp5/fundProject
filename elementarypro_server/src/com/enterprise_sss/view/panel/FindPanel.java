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
 * ���в�ѯ���
 * 
 * @author Administrator
 * 
 */
public class FindPanel extends JPanel {

	public JTable table;          //������

	public JComboBox comboBox;     //���������б��

	private String[] items;      //�����б������(��ѯ��ʽ)
	
	private TableVO tvo;        //������ݡ���ͷ������(�ܵĲ�ѯ�������)
	
	private String title;      //��ѯ���ı���
	
	public FindPanel(){
		
	}

	public FindPanel(String title,String[] items,TableVO tvo) {
		this.title = title;
		this.items = items;
		this.tvo = tvo;
		init();
	}

	/**
	 * ��ʼ�����
	 *
	 */
	public void init() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.add(buildNorthPanel(), BorderLayout.NORTH);
		this.add(buildScroll(), BorderLayout.CENTER);
	}

	/**
	 * �����������
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
	 * �������
	 * @return
	 */
	public JScrollPane buildScroll() {
		JScrollPane scroll = new JScrollPane();
		table = CommonComponent.buildTable(tvo.getData(), tvo.getTitle());
		scroll.setViewportView(table);
		return scroll;
	}

}
