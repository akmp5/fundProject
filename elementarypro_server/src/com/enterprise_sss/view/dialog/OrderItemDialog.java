package com.enterprise_sss.view.dialog;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.vo.TableVO;

public abstract class OrderItemDialog extends JDialog {

	public JTable table;

	private TableVO tvo;

	private int type;

	private String title;

	public int id;

	public Vector<Integer> vdata = new Vector<Integer>();

	public Vector<Integer> del = new Vector<Integer>();
	
	public OrderItemDialog(int type, String title, TableVO tvo, int id) {
		this.type = type;
		this.tvo = tvo;
		this.title = title;
		this.id = id;
		if (this.tvo.getData() != null && this.tvo.getData().size() > 0) {
			for (int i = 0; i < this.tvo.getData().size(); i++) {
				vdata.add(Integer.parseInt(((Vector)tvo.getData().get(i)).get(0).toString()));
				//System.out.println(i);
			}
		}
		init();
	}

	public void init() {
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setTitle(title);
		this.add(buildCenterPanel(), BorderLayout.CENTER);
		this.add(buildSouthPanel(), BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public abstract JPanel buildSouthPanel();

	public JScrollPane buildCenterPanel() {
		JScrollPane scroll = new JScrollPane();
		table = CommonComponent.buildTable(tvo.getData(), tvo.getTitle());
		scroll.setViewportView(table);
		return scroll;
	}

	public Vector<Integer> getVdata() {
		return vdata;
	}

	public void setVdata(Vector<Integer> vdata) {
		this.vdata = vdata;
	}

	public Vector<Integer> getDel() {
		return del;
	}

	public void setDel(Vector<Integer> del) {
		this.del = del;
	}

}
