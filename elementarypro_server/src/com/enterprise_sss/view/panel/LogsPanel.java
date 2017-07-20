package com.enterprise_sss.view.panel;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.enterprise_sss.action.LogsPanelAction;
import com.enterprise_sss.util.CommonComponent;

public class LogsPanel extends JPanel {

	private Checkbox box1, box2; // 性别选择框

	private CheckboxGroup type = null;

	private int n = 0, count1 = 0, count2 = 0;

	private String path1 = "logs/errors";

	private String path2 = "logs/messages";

	private JTextArea area = new JTextArea();

	public LogsPanel() {
		init();
		initAction();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		this.setToolTipText("查看日志");
		this.add(buildScrollPanel(), BorderLayout.CENTER);
		this.add(buildNorthPanel(), BorderLayout.NORTH);
		this.add(buildSouthPanel(), BorderLayout.SOUTH);
	}

	public JPanel buildNorthPanel() {
		JPanel panel = new JPanel();

		type = new CheckboxGroup();
		box1 = new Checkbox("错误日志", true, type);
		box2 = new Checkbox("操作日志", false, type);

		panel.add(box1);
		panel.add(box2);

		return panel;
	}

	/**
	 * @return
	 */
	public JScrollPane buildScrollPanel() {
		JScrollPane panel = new JScrollPane();
		area.setLineWrap(true);
		panel.setViewportView(area);
		n = 0;
		setValues(path1);
		return panel;
	}

	public JPanel buildSouthPanel() {
		JPanel panel = new JPanel();
		panel.add(buildButton("首条"));
		panel.add(buildButton("上一条"));
		panel.add(buildButton("下一条"));
		panel.add(buildButton("未条"));
		return panel;
	}

	public JButton buildButton(String str) {
		JButton button = CommonComponent.buildButton(str, null, null, null,
				null, null, null, true);
		button.addActionListener(new LogsPanelAction(this));
		return button;
	}

	public void initAction() {
		box1.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				n = 0;
				if (e.getStateChange() == ItemEvent.SELECTED) {
					setValues(path1);
				}
			}

		});
		
		box2.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				n = 0;
				if (e.getStateChange() == ItemEvent.SELECTED) {
					setValues(path2);
				}
			}

		});
	}

	public Checkbox getBox1() {
		return box1;
	}

	public Checkbox getBox2() {
		return box2;
	}

	public CheckboxGroup getType() {
		return type;
	}

	public void setValues(String fileName) {
		File file = new File(fileName);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (path1.equals(fileName))
				count1 = files.length;
			else
				count2 = files.length;
			if (n < files.length && n >= 0) {
				try {
					BufferedReader reader = new BufferedReader(new FileReader(
							files[n]));
					String s = null;
					area.setText("");
					while ((s = reader.readLine()) != null) {
						area.append(s + "\r\n");
					}
					reader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (n < 0) {
				JOptionPane.showMessageDialog(null, "对不起已到首条!!!");
				n = 0;
			} else if (n >= files.length) {
				JOptionPane.showMessageDialog(null, "对不起已到未条!!!");
				n = files.length - 1;
			}
		}
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getCount1() {
		return count1;
	}

	public int getCount2() {
		return count2;
	}

	public String getPath1() {
		return path1;
	}

	public String getPath2() {
		return path2;
	}

}
