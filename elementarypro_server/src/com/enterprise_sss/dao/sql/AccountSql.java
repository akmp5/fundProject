package com.enterprise_sss.dao.sql;

public interface AccountSql {
	 //预付款相关操作SQL
	String SELE_DP="select * from deposit_payment";
	String SELE_DP_TM="select * from deposit_payment " +
		"where trunc(dp_date,'MONTH')=trunc(sysdate,'MONTH')";
	String SELE_DP_TY="select * from deposit_payment  " +
		"where trunc(dp_date,'year')=trunc(sysdate,'year')";
	String SELE_DP_LM="select * from deposit_payment  " +
		"where add_months(trunc(dp_date,'month'),1)=trunc(sysdate,'month')";
	String DEL_DP="delete deposit_payment where dp_id=?";
	String ADD_DP="insert into deposit_payment (dp_id,dp_inv_date,dp_date)values(?,sysdate,sysdate)";
	String UP_DP="update deposit_payment set dp_inv=?,dp_inv_date=?," +
			"supp_id=?,dp_money=?,dp_date=? where dp_id=?";
	
	 //应付款相关操作SQL
	String SELE_AP="select * from account_payable";
	String SELE_AP_TM="select * from account_payable  " +
			"where trunc(ap_date,'MONTH')=trunc(sysdate,'MONTH')";
	String SELE_AP_TY="select * from account_payable  " +
			"where trunc(ap_date,'year')=trunc(sysdate,'year')";
	String SELE_AP_LM="select * from account_payable  " +
			"where add_months(trunc(ap_date,'month'),1)=trunc(sysdate,'month')";
	String DEL_AP="delete account_payable where ap_id=?";
	String ADD_AP="insert into account_payable (ap_id,ap_inv_date,ap_date,ap_desc,state)values(?,sysdate,sysdate,'无','未付款')";
	String UP_AP="update account_payable set ap_inv=?,ap_inv_date=?,pib_id=?,comm_id =?,supp_id=?," +
			"ap_comm_amount =?,ap_purchase_price =?,ap_money=?,ap_date=?,ap_desc =?,state=?,dp_id=? where ap_id=?";

	
	 //已付款相关操作SQL
	String SELE_AP_PAID="select * from account_payable where state='已付款'";
	String SELE_APD_TM="select * from account_payable  " +
				"where trunc(ap_date,'MONTH')=trunc(sysdate,'MONTH') and state='已付款'";
	String SELE_APD_TY="select * from account_payable  " +
				"where trunc(ap_date,'year')=trunc(sysdate,'year') and state='已付款'";
	String SELE_APD_LM="select * from account_payable  " +
			"where add_months(trunc(ap_date,'month'),1)=trunc(sysdate,'month') and state='已付款'";
	
	//查询指定编号进货单SQL
	String SELE_PIB="select * from purchase_in_bill where pib_id =?";
	//查询指定编号供货商SQL
	String SELE_SB="select * from suppliers_bill where supp_id=? ";
	
//	预收款相关操作SQL
	String SELE_DR="select * from deposit_received";
	String SELE_DR_TM="select * from deposit_received " +
			"where trunc(dr_date,'MONTH')=trunc(sysdate,'MONTH')";
	String SELE_DR_TY="select * from deposit_received  " +
			"where trunc(dr_date,'year')=trunc(sysdate,'year')";
	String SELE_DR_LM="select * from deposit_received  " +
			"where add_months(trunc(dr_date,'month'),1)=trunc(sysdate,'month')";
	String DEL_DR="delete deposit_received where dr_id=?";
	String ADD_DR="insert into deposit_received (dr_id,dr_inv_date,dr_date)values(?,sysdate,sysdate)";
	String UP_DR="update deposit_received set dr_inv=?,dr_inv_date=?," +
			"clie_id=?,cr_money=?,dr_date=? where dr_id=?";
	
//	应收款相关操作SQL
	String SELE_AR="select * from account_receivable";
	String SELE_AR_TM="select * from account_receivable  " +
			"where trunc(ar_date,'MONTH')=trunc(sysdate,'MONTH')";
	String SELE_AR_TY="select * from account_receivable  " +
			"where trunc(ar_date,'year')=trunc(sysdate,'year')";
	String SELE_AR_LM="select * from account_receivable  " +
			"where add_months(trunc(ar_date,'month'),1)=trunc(sysdate,'month')";
	String DEL_AR="delete account_receivable where ar_id=?";
	String ADD_AR="insert into account_receivable (ar_id,ar_inv_date,ar_date,ar_desc,state)values(?,sysdate,sysdate,'无','未收款')";
	String UP_AR="update account_receivable set ar_inv=?,ar_inv_date=?,sb_id=?,comm_id =?,clie_id=?," +
			"ar_comm_amount =?,ar_sale_price =?,ar_money=?,ar_date=?,ar_desc =?,state=?,dr_id=? where ar_id=?";
		
//	已收款相关操作SQL
	String SELE_AR_RECEIVED="select * from account_receivable where state='已收款'";
	String SELE_ARD_TM="select * from account_receivable  " +
			"where trunc(ar_date,'MONTH')=trunc(sysdate,'MONTH') and state='已收款'";
	String SELE_ARD_TY="select * from account_receivable  " +
			"where trunc(ar_date,'year')=trunc(sysdate,'year') and state='已收款'";
	String SELE_ARD_LM="select * from account_receivable  " +
			"where add_months(trunc(ar_date,'month'),1)=trunc(sysdate,'month') and state='已收款'";
	
//	查询指定编号客户SQL
	String SELE_CB="select * from client_bill where clie_id=?";
//	查询指定编号销售单SQL
	String SELE_SALE="select * from sale_bill where sb_id=?";
	
}
