package com.enterprise_sss.view.panel.accountPAndR;

import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.calendar.DataPicker;
import com.enterprise_sss.view.panel.DataMaintenancePanel;
import com.enterprise_sss.vo.TableVO;


/**
 * 帐单管理面板
 * @author Administrator
 *
 */
public class AccountManage extends DataMaintenancePanel {
	private JTextField invId,billId,commId,swapId,commAmount,commPrice,fund,deposit;
	private JComboBox invDate,fundDate,state;
	private JTextArea detail;
	public AccountManage(String title, TableVO tvo){
		super(title,tvo);
		init();
	}
	
	@Override
	public JPanel buildNorthPanel() {
		JPanel panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		DataPicker picker=new DataPicker();//创建日历对像
		
		panel.add(CommonComponent.buildLabel("发票号", null, null, null, null, null),new GBC(0,0));
		panel.add(invId=CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,0));
		
		panel.add(CommonComponent.buildLabel("填票日期", null, null, null, null, null),new GBC(4,0));
		panel.add(invDate=picker.getDataPacker(),new GBC(5,0));//添加日历组件
		
		panel.add(CommonComponent.buildLabel("销售/进货单编号", null, null, null, null, null),new GBC(2,0));
		panel.add(billId=CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(3,0));
		
		panel.add(CommonComponent.buildLabel("货物编号", null, null, null, null, null),new GBC(0,1));
		panel.add(commId=CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,1));
		
		panel.add(CommonComponent.buildLabel("客户/供货商编号", null, null, null, null, null),new GBC(2,1));
		panel.add(swapId=CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(3,1));
		
		panel.add(CommonComponent.buildLabel("货物数量", null, null, null, null, null),new GBC(0,2));
		panel.add(commAmount=CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,2));
		
		panel.add(CommonComponent.buildLabel("货物单价", null, null, null, null, null),new GBC(2,2));
		panel.add(commPrice=CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(3,2));
		
		panel.add(CommonComponent.buildLabel("划款金额", null, null, null, null, null),new GBC(0,3));
		panel.add(fund=CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,3));
		
		panel.add(CommonComponent.buildLabel("划款日期", null, null, null, null, null),new GBC(4,1));
		panel.add(fundDate=picker.getDataPacker(),new GBC(5,1));
		
		panel.add(CommonComponent.buildLabel("详细说明", null, null, null, null, null),new GBC(4,3));
		JScrollPane sb=new JScrollPane();
		sb.setViewportView(CommonComponent.buildTextArea(null, null, null, null, null, 2, 12, false, true, true));
		panel.add(sb,new GBC(5,3));
//		panel.add(detail=CommonComponent.buildTextArea(null, null, null, null, null, 2, 15, false, true, true),new GBC(5,3));
		
		panel.add(CommonComponent.buildLabel("状态", null, null, null, null, null),new GBC(4,2));
		panel.add(state=CommonComponent.buildComboBox(new String[]{"未划款                     ","已划款"}, null, null, null, null, null, false, true),new GBC(5,2));
		
		panel.add(CommonComponent.buildLabel("减预收", null, null, null, null, null),new GBC(2,3));
		panel.add(deposit=CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(3,3));
		
		
		
		return panel;
	}

}
