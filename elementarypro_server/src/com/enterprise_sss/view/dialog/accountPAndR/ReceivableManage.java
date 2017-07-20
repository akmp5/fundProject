package com.enterprise_sss.view.dialog.accountPAndR;

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;

import com.enterprise_sss.action.accountPAndR.ReceivableManageAction;
import com.enterprise_sss.util.calendar.DataPicker;
import com.enterprise_sss.vo.TableVO;

/**
 * 收款单Dialog
 * @author yiguo
 *
 */
public class ReceivableManage extends BaseAccount {
	
	public ReceivableManage(String title,String tableTitle1, TableVO tvo1,String tableTitle2, TableVO tvo2){
		super(title,tableTitle1,tvo1,tableTitle2,tvo2);
		
	}

	public void especialStyle(){
		getButtons().get(1).setVisible(true);
		getButtons().get(2).setVisible(true);
		getButtons().get(3).setVisible(true);
		tableStyle();
	}
	
	public void tableStyle(){
		getTable1().getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new DataPicker().getDataPacker()));
		getTable1().getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(new DataPicker().getDataPacker()));
		
	}
	
	
	@Override
	public void initAction() {
		ReceivableManageAction dra=new ReceivableManageAction(this);
		//给按钮添加监听事件
		for(int i=0;i<getButtons().size();i++){
			if(i==4){
				getButtons().get(i).addMouseListener(dra);		
			}else{
				getButtons().get(i).addActionListener(dra);		
			}
		}
//		给表一添加监听事件
		getTable1().addMouseListener(dra);
//		给弹出式菜单添加监听事件
		for(int i=0;i<3;i++)
			getJis().get(i).addActionListener(dra);
	}
	
}
