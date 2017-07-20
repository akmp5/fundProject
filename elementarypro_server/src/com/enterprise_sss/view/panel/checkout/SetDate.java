package com.enterprise_sss.view.panel.checkout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.enterprise_sss.action.checkout.SetDateAction;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.ImagePanel;
import com.enterprise_sss.util.calendar.DataPicker;

/**
 * ���������趨���
 * @author yiguo
 *
 */
public class SetDate extends JPanel{
	private JButton confirm;
	private JComboBox date;

	public JComboBox getDate() {
		return date;
	}

	public SetDate(){
		this.setLayout(new BorderLayout());
		this.add(getImagePanel());
		initAction();
	}
	
	private JPanel getImagePanel(){
		JPanel panel=new ImagePanel("image/jpg/date3.jpg");
		panel.add(CommonComponent.buildLabel("�����趨��", null, Color.blue, new Font("����",Font.BOLD,15), null, null));
		panel.add(date=new DataPicker().getDataPacker());
		panel.add(confirm=CommonComponent.buildButton("ȷ��", null, null, null, null, null, null, true));
		return panel;
	}
	
	private void initAction() {
		SetDateAction sda=new SetDateAction(this);
		confirm.addActionListener(sda);
	}
}
