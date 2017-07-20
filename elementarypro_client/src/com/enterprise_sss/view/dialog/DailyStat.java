package com.enterprise_sss.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.enterprise_sss.action.DailyStatAction;
import com.enterprise_sss.action.FreeBillAction;
import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.view.frame.RetailFrame;


/**
 * ÿ������ͳ��Dialog
 * @author yiguo
 *
 */
public class DailyStat extends JDialog {
	private JTable table;//ÿ������ͳ�Ʊ�
	private JButton export,print,back;//��������ӡ������
	private JLabel num,sum;//���������ܽ��
	private Vector statData;//ÿ������ͳ������
	private RetailFrame rf;//���۽���
	
	public DailyStat(RetailFrame rf){
		this.rf=rf;
		statData=new RetailServer().getStatData(rf.getComms(), rf.getSaleItems());
		init();
	}

	private void init() {
		initDialog();
		initAction();
		this.setVisible(true);
	}

	private void initDialog() {
		this.setTitle("ÿ������ͳ��");
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//�������û�������С
		this.setModal(true);//ֻ�ܲ�����ǰ����
		this.setLayout(new BorderLayout());
		this.add(getNorth(),BorderLayout.NORTH);
		this.add(getCenter(),BorderLayout.CENTER);
		this.add(getSouth(),BorderLayout.SOUTH);
		
	}
	
	/**
	 * ���Ĺ������
	 * @return
	 */
	private JScrollPane getCenter(){
		JScrollPane sp=new JScrollPane();
		sp.setViewportView(table=CommonComponent.buildTable(statData, new RetailServer().getStatTitle()));
		return sp;
	}

	/**
	 * ����ť���
	 * @return
	 */
	private JPanel getNorth(){
		JPanel p=new JPanel();
		p.add(export=CommonComponent.buildButton("����", null, null, null, null, null, null, true));
		p.add(print=CommonComponent.buildButton("��ӡ", null, null, null, null, null, null, true));
		p.add(back=CommonComponent.buildButton("����", null, null, null, null, null, null, true));
		return p;
	}
	
	/**
	 * �ϱ�ǩ���
	 * @return
	 */
	private JPanel getSouth(){
		JPanel p=new JPanel();
		p.add(CommonComponent.buildLabel("�ܼ�������",null, null, null, null, null));
		p.add(CommonComponent.buildLabel(new RetailServer().getSum(statData).get(0).toString()
				+"    ",null, null, null, null, null));
		p.add(CommonComponent.buildLabel("�ܼƽ�",null, null, null, null, null));
		p.add(CommonComponent.buildLabel(new RetailServer().getSum(statData).get(1).toString()
				,null, Color.red, null, null, null));
		return p;
	}
	
	/**
	 * ����¼�
	 */
	private void initAction() {
		DailyStatAction fba=new DailyStatAction(this);
		export.addActionListener(fba);
		print.addActionListener(fba);
		back.addActionListener(fba);
	}

	public JTable getTable() {
		return table;
	}

	public Vector getStatData() {
		return statData;
	}
	
}
