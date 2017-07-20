package com.enterprise_sss.view.panel.accountPAndR;

import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.calendar.DataPicker;
import com.enterprise_sss.view.panel.DataMaintenancePanel;
import com.enterprise_sss.vo.TableVO;


/**
 * ����������
 * @author Administrator
 *
 */
public class DepositManage extends DataMaintenancePanel{
	private JTextField invId,swapId,fund;
	private JComboBox invDate,fundDate;
	
	public DepositManage(String title, TableVO tvo){
		super(title,tvo);
		init();
	}
	
	@Override
	public JPanel buildNorthPanel() {
		JPanel panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.add(CommonComponent.buildLabel("��Ʊ��", null, null, null, null, null),new GBC(0,0).setInset(10));
		panel.add(invId=CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,0));
		
		panel.add(CommonComponent.buildLabel("��Ʊ����", null, null, null, null, null),new GBC(2,0).setInset(10));
		panel.add(invDate=new DataPicker().getDataPacker(),new GBC(3,0));
		
		panel.add(CommonComponent.buildLabel("�ͻ�/�����̱��", null, null, null, null, null),new GBC(4,0).setInset(10));
		panel.add(swapId=CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(5,0));
		
		panel.add(CommonComponent.buildLabel("���", null, null, null, null, null),new GBC(0,1).setInset(10));
		panel.add(fund=CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true),new GBC(1,1));
		
		panel.add(CommonComponent.buildLabel("��������", null, null, null, null, null),new GBC(2,1).setInset(10));
		panel.add(fundDate=new DataPicker().getDataPacker(),new GBC(3,1));
		
		return panel;
	}
	
	
}
