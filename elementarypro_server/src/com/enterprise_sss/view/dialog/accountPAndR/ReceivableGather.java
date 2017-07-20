package com.enterprise_sss.view.dialog.accountPAndR;

import java.util.Vector;

import javax.swing.JButton;

import com.enterprise_sss.action.accountPAndR.DepositPaymentAction;
import com.enterprise_sss.action.accountPAndR.ReceivableGatherAction;
import com.enterprise_sss.vo.TableVO;


/**
 * Ӧ�տ����Dialog
 * @author yiguo
 *
 */
public class ReceivableGather extends BaseAccount {
	public ReceivableGather(String title,String tableTitle1, TableVO tvo1,String tableTitle2, TableVO tvo2){
		super(title,tableTitle1,tvo1,tableTitle2,tvo2);
	}
	

	@Override
	public void initAction() {
		ReceivableGatherAction dra=new ReceivableGatherAction(this);
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
