package com.enterprise_sss.view.dialog.accountPAndR;

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;

import com.enterprise_sss.action.accountPAndR.AccountReceivedAction;
import com.enterprise_sss.action.accountPAndR.DepositPaymentAction;
import com.enterprise_sss.util.calendar.DataPicker;
import com.enterprise_sss.vo.TableVO;


/**
 * ��ѯ���տ���ϸdialog
 * @author yiguo
 *		���ڲ�ѯ��ҵ��ʷ���������ջصĿ���
 */
public class AccountReceived extends BaseAccount {
	public AccountReceived(String title,String tableTitle1, TableVO tvo1,String tableTitle2, TableVO tvo2){
		super(title,tableTitle1,tvo1,tableTitle2,tvo2);
	}
	
	public void especialStyle(){
		tableStyle();
	}
	
	public void tableStyle(){
		getTable1().getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new DataPicker().getDataPacker()));
		getTable1().getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(new DataPicker().getDataPacker()));
		
	}

	@Override
	public void initAction() {
		AccountReceivedAction dra=new AccountReceivedAction(this);
//		����ť��Ӽ����¼�
		for(int i=0;i<getButtons().size();i++){
			if(i==4){
				getButtons().get(i).addMouseListener(dra);		
			}else{
				getButtons().get(i).addActionListener(dra);		
			}
		}
//		����һ��Ӽ����¼�
		getTable1().addMouseListener(dra);
//		������ʽ�˵���Ӽ����¼�
		for(int i=0;i<3;i++)
			getJis().get(i).addActionListener(dra);
	}
	

}
