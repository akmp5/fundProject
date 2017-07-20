  package com.enterprise_sss.view.dialog.accountPAndR;

import java.util.Vector;

import javax.swing.JButton;

import com.enterprise_sss.action.accountPAndR.DepositPaymentAction;
import com.enterprise_sss.action.accountPAndR.ReceivableDetailAction;
import com.enterprise_sss.vo.TableVO;


/**
 * 查询应收款明细Dialog
 * @author yiguo
 *
 */
public class ReceivableDetail extends BaseAccount {
	public ReceivableDetail(String title,String tableTitle1, TableVO tvo1,String tableTitle2, TableVO tvo2){
		super(title,tableTitle1,tvo1,tableTitle2,tvo2);
	}
	

	@Override
	public void initAction() {
		ReceivableDetailAction dra=new ReceivableDetailAction(this);
//		给按钮添加监听事件
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
