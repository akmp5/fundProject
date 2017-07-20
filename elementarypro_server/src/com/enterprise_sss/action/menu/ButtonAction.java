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
		if(str.equalsIgnoreCase("生成销售订单")){
			OrderSaleDialog os = new OrderSaleDialog("销售订单");
		}
		if(str.equalsIgnoreCase("生成销售单")){
			SaleDialog os = new SaleDialog("销售单");
		}
		if(str.equalsIgnoreCase("销售退货单")){
			SaleBackDialog os = new SaleBackDialog("退货单");
		}
		if(str.equalsIgnoreCase("调整历史售价")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UpdateSalePanel());
		}
		if(str.equalsIgnoreCase("销售查询")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new SaleTabbedPane());
		}
		if(str.equalsIgnoreCase("库存查询")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new QueryDepotPanel());
		}
		if(str.equalsIgnoreCase("库存转库")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new TransDepotBasePanel());
		}
		if(str.equalsIgnoreCase("库存盘点")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new CheckDepotPanel());
		}
		if(str.equalsIgnoreCase("报损报溢")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new ProfitLossPanel());
		}
		if(str.equalsIgnoreCase("上下限设定")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UperLowerPanel());
		}
		if(str.equalsIgnoreCase("上下限报警")){
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UpLowAlarmPanel());
		}
	}

}
