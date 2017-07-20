package com.enterprise_sss.view.dialog.accountPAndR;

import javax.swing.DefaultCellEditor;

import com.enterprise_sss.action.accountPAndR.AccountPaidAction;
import com.enterprise_sss.util.calendar.DataPicker;
import com.enterprise_sss.vo.TableVO;

/**
 * ��ѯ�Ѹ�����ϸdialog
 * @author yiguo
 *		���ڲ�ѯ��ҵ��ʷ�������Ѿ�֧���Ŀ���
 */
public class AccountPaid extends BaseAccount {
	
	public AccountPaid(String title,String tableTitle1, TableVO tvo1,String tableTitle2, TableVO tvo2){
		super(title,tableTitle1,tvo1,tableTitle2,tvo2);
	}


	@Override
	public void initAction() {
		
		AccountPaidAction dra=new AccountPaidAction(this);
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
