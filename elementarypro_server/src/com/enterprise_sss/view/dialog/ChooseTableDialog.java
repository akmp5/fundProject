package com.enterprise_sss.view.dialog;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.enterprise_sss.util.CommonComponent;

public class ChooseTableDialog {

	private String table_title;

	private JDialog dialog;

	private JComboBox comboBox;

	private JLabel label;

	public ChooseTableDialog() {
		init();
	}

	public void init() {
		dialog = new JDialog();
		dialog.setLayout(new FlowLayout());
		String[] items = new String[] { "CLIENT_BILL", "COMMODITY_BILL",
				"DEPOT_BILL", "DEPOT_BILL", "PURCHASE_CONTRACT",
				"SUPPLIERS_BILL", "PURCHASE_ORDER", "PURCHASE_IN_BILL",
				"PURCHASE_IN_ITEMS", "PURCHASE_ORDER_ITEMS" };
		comboBox = CommonComponent.buildComboBox(items, null, null, null, null,
				null, false, true);
		label = new JLabel("请选择表：");
		dialog.setSize(350, 70);
		dialog.setTitle("选择框");
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.add(label);
		dialog.add(comboBox);
		dialog.add(createButton("确定"));
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	public JButton createButton(String name) {
		JButton button = new JButton(name);
		if (name.equals("确定")) {
			button.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					table_title = comboBox.getSelectedItem().toString();
					dialog.dispose();
				}
			});
		}
		return button;
	}

	public String getTable_title() {
		return table_title;
	}

	public void setTable_title(String table_title) {
		this.table_title = table_title;
	}

}
