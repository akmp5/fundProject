package com.enterprise_sss.action.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.enterprise_sss.view.dialog.salemanage.OrderSaleDialog;
import com.enterprise_sss.view.dialog.salemanage.SaleBackDialog;
import com.enterprise_sss.view.dialog.salemanage.SaleDialog;
import com.enterprise_sss.view.frame.MainFrame;
import com.enterprise_sss.view.panel.depotmanage.CheckDepotPanel;
import com.enterprise_sss.view.panel.depotmanage.ProfitLossPanel;
import com.enterprise_sss.view.panel.depotmanage.QueryDepotPanel;
import com.enterprise_sss.view.panel.depotmanage.TransDepotBasePanel;
import com.enterprise_sss.view.panel.depotmanage.UpLowAlarmPanel;
import com.enterprise_sss.view.panel.depotmanage.UperLowerPanel;
import com.enterprise_sss.view.panel.salemanage.SaleQueryBasePanel;
import com.enterprise_sss.view.panel.salemanage.SaleTabbedPane;
import com.enterprise_sss.view.panel.salemanage.UpdateSalePanel;


public class ButtonAction implements ActionListener {
	MainFrame frame;
	public ButtonAction(MainFrame frame){
		this.frame = frame;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		if(str.equalsIgnoreCase("�������۶���")){
			OrderSaleDialog os = new OrderSaleDialog("���۶���");
		}
		if(str.equalsIgnoreCase("�������۵�")){
			SaleDialog os = new SaleDialog("���۵�");
		}
		if(str.equalsIgnoreCase("�����˻���")){
			SaleBackDialog os = new SaleBackDialog("�˻���");
		}
		if(str.equalsIgnoreCase("������ʷ�ۼ�")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UpdateSalePanel());
		}
		if(str.equalsIgnoreCase("���۲�ѯ")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new SaleTabbedPane());
		}
		if(str.equalsIgnoreCase("����ѯ")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new QueryDepotPanel());
		}
		if(str.equalsIgnoreCase("���ת��")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new TransDepotBasePanel());
		}
		if(str.equalsIgnoreCase("����̵�")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new CheckDepotPanel());
		}
		if(str.equalsIgnoreCase("������")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new ProfitLossPanel());
		}
		if(str.equalsIgnoreCase("�������趨")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UperLowerPanel());
		}
		if(str.equalsIgnoreCase("�����ޱ���")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UpLowAlarmPanel());
		}
	}

}
