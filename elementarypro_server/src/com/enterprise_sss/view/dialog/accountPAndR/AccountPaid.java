package com.enterprise_sss.view.dialog.accountPAndR;

import javax.swing.DefaultCellEditor;

import com.enterprise_sss.action.accountPAndR.AccountPaidAction;
import com.enterprise_sss.util.calendar.DataPicker;
import com.enterprise_sss.vo.TableVO;

/**
 * 查询已付款明细dialog
 * @author yiguo
 *		用于查询企业历史的所有已经支付的款项
 */
public class AccountPaid extends BaseAccount {
	
	public AccountPaid(String title,String tableTitle1, TableVO tvo1,String tableTitle2, TableVO tvo2){
		super(title,tableTitle1,tvo1,tableTitle2,tvo2);
	}


	@Override
	public void initAction() {
		
		AccountPaidAction dra=new AccountPaidAction(this);
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
