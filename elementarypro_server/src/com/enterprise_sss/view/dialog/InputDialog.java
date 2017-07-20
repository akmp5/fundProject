package com.enterprise_sss.view.dialog;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.enterprise_sss.util.MethodUtil;

public class InputDialog {

	private JTextField text;

	private JLabel label;

	private JDialog dialog;

	private int type;

	public InputDialog(int type) {
		this.type = type;
		init();
	}

	public void init() {
		dialog = new JDialog();
		dialog.setLayout(new FlowLayout());
		text = new JTextField(18);
		if (type == 1)
			label = new JLabel("请输入编号：");
		else
			label = new JLabel("请输入数据：");
		dialog.setSize(300, 70);
		dialog.setTitle("输入框");
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.add(label);
		dialog.add(text);
		dialog.add(createButton("确定"));
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	public JButton createButton(String name) {
		JButton button = new JButton(name);
		if (name.equals("确定")) {
			button.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					String data = text.getText().trim();
					boolean b = false;
					if (type == 1) {
						if (MethodUtil.isInt(data)) {
							b = true;
						} else {
							b = false;
						}
					} else if (type == 2) {
						if (data == null || "".equals(data)) {
							b = false;
						} else {
							b = true;
						}
					} else if (type == 3) {
						if (MethodUtil.validateIDNumber(data)) {
							b = true;
						}
					}
					if (b) {
						dialog.dispose();
					} else {
						JOptionPane.showMessageDialog(null,
						"对不起，数据格式错误，请输入正确数据格式！");
					}
				}
			});
		}
		return button;
	}

	public JTextField getText() {
		return text;
	}
}
