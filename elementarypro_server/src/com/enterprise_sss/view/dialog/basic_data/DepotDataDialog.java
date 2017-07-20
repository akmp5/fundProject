package com.enterprise_sss.view.dialog.basic_data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.ButtonUI;

import com.enterprise_sss.action.DepotDataDialogAction;
import com.enterprise_sss.control.DepotServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.DepotVO;

public class DepotDataDialog extends JDialog {

	private int type;

	private String title;
	
	private JButton button;

	private JTextField depo_id; // 仓库编号文本框

	private JTextField depo_name; // 仓库名文本框

	private JTextField depo_sort; // 类别文本框

	private JTextField depo_desc; // 备注文本框

	public DepotDataDialog(int type, String title) {
		this.type = type;
		this.title = title;
		init();
	}

	public void init() {
		this.setSize(350, 300);
		this.setLayout(new GridBagLayout());
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle(title);

		this.add(CommonComponent.buildLabel("仓库编号:", null, null, null, null,
				null), new GBC(0, 0).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(depo_id = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 0).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("仓库名:", null, null, null, null,
				null), new GBC(0, 1).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(depo_name = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 1)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("类别:", null, null, null, null,
				null), new GBC(0, 2).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(depo_sort = CommonComponent.buildTextField("txt", null, null,
				null, null, null, null, true, true), new GBC(1, 2).setFill(
				GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		this.add(CommonComponent.buildLabel("备注:", null, null, null, null,
				null), new GBC(0, 3).setFill(GBC.CENTER).setAnchor(GBC.EAST)
				.setInset(5));
		this.add(depo_desc = CommonComponent.buildTextField("txt", null,
				null, null, null, null, null, true, true), new GBC(1, 3)
				.setFill(GBC.CENTER).setAnchor(GBC.WEST).setInset(5));
		
		if (type == 1) {
			this
					.add(button = buildButton("添加", null, null, null, null, null, null,
							true), new GBC(0, 4).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
			depo_id.setEditable(false);
			depo_id.setText("" + new DepotServer().findMaxID());
		}
		if (type == 2) {
			this
					.add(button = buildButton("查询", null, null, null, null, null, null,
							true), new GBC(0, 4).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
		}
		if (type == 3)
			this
					.add(button = buildButton("删除", null, null, null, null, null, null,
							true), new GBC(0, 4).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));
		if (type == 4)
			this
					.add(button = buildButton("修改", null, null, null, null, null, null,
							true), new GBC(0, 4).setFill(GBC.CENTER)
							.setAnchor(GBC.WEST).setInset(5));

		this.add(buildButton("取消", null, null, null, null, null, null, true),
				new GBC(1, 4).setFill(GBC.CENTER).setAnchor(GBC.WEST)
						.setInset(5));

		if (type == 3 || type == 4) {
			editable(false);
			if (type == 3) {
				button.setEnabled(false);
			}
			depo_id.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (MethodUtil.isInt(depo_id.getText())) {
						DepotVO v = new DepotVO();
						// System.out.println(comm_id.getText());
						v.setDepo_id(Integer.parseInt(depo_id.getText()));
						v = new DepotServer().find(v);
						if (v == null) {
							JOptionPane
									.showMessageDialog(null, "对不起，不存在该数据记录!");
							reset(null);
						} else {
							button.setEnabled(true);
							reset(v);
							editable(true);
							depo_id.setEditable(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "请输入正确的数据!");
						reset(null);
					}
					new DepotServer().close();
				}

			});
		}
		
		this.setVisible(true);
	}

	public JButton buildButton(String title, Color bgColor, ImageIcon icon,
			Color forColor, Font font, ButtonUI ui, Dimension size,
			boolean isEnable) {
		JButton button = CommonComponent.buildButton(title, bgColor, icon,
				forColor, font, ui, size, isEnable);
		button.addActionListener(new DepotDataDialogAction(this));
		return button;
	}
	
	public void editable(boolean b) {
		depo_name.setEditable(b);
		depo_sort.setEditable(b);
		depo_desc.setEditable(b);
	}

	public void reset(DepotVO v) {
		if (v == null) {
			depo_id.setText("");
			depo_name.setText("");
			depo_sort.setText("");
			depo_desc.setText("");
		} else {
			depo_id.setText("" + v.getDepo_id());
			depo_name.setText("" + v.getDepo_name());
			depo_sort.setText("" + v.getDepo_sort());
			depo_desc.setText("" + v.getDepo_desc());
			}
	}


	public JTextField getDepo_desc() {
		return depo_desc;
	}

	public JTextField getDepo_id() {
		return depo_id;
	}

	public JTextField getDepo_name() {
		return depo_name;
	}

	public JTextField getDepo_sort() {
		return depo_sort;
	}

}
