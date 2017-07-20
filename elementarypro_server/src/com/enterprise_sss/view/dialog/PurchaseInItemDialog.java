package com.enterprise_sss.view.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.ButtonUI;

import com.enterprise_sss.action.ImportExportAction;
import com.enterprise_sss.action.PurchaseInItemDialogAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.vo.TableVO;

public class PurchaseInItemDialog extends OrderItemDialog {
	
	private int count = 0;
	
	private JButton button;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public PurchaseInItemDialog(int type, String title, TableVO tvo, int id) {
		super(type, title, tvo, id);
	}

	public JPanel buildSouthPanel() {
		JPanel panel = new JPanel();
		panel.add(buildButton("+", null, null, null, null, null, null, true));
		panel.add(buildButton("-", null, null, null, null, null, null, true));
		panel.add(buildButton("ȷ��", null, null, null, null, null, null, true));
		panel.add(buildButton("ȡ��", null, null, null, null, null, null, true));
		ImportExportAction act = new ImportExportAction(table);
		button = CommonComponent.buildButton("����", null, null, null, null, null, null, true);
		panel.add(button);
		button.addActionListener(act);
		return panel;
	}
	
	private JButton buildButton(String title, Color bgColor, ImageIcon icon,
			Color forColor, Font font, ButtonUI ui, Dimension size,
			boolean isEnable) {
		JButton button = CommonComponent.buildButton(title, bgColor, icon,
				forColor, font, ui, size, isEnable);
		button.addActionListener(new PurchaseInItemDialogAction(this));
		return button;
	}
	
}
