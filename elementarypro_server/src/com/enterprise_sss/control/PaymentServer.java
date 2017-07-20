package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.DaoFactory;
import com.enterprise_sss.dao.sql.AccountSql;
import com.enterprise_sss.vo.AccountPayableVO;
import com.enterprise_sss.vo.DepositPaymentVO;


/**
 * 应付款数据服务类
 * 
 * @author yiguo
 * @version 1.0
 */
public class PaymentServer {
	/**
	 * 查询预付款表所有记录
	 * @return
	 */
	public Vector getDespositPayment(){
		return DaoFactory.getPaymentDao().findDepositPayment(AccountSql.SELE_DP);
	}
	/**
	 * 查询本年度预付款表所有记录
	 * @return
	 */
	public Vector getYearDespositPayment(){
		return DaoFactory.getPaymentDao().findDepositPayment(AccountSql.SELE_DP_TY);
	}
	/**
	 * 查询上个月预付款表所有记录
	 * @return
	 */
	public Vector getLastMonthDespositPayment(){
		return DaoFactory.getPaymentDao().findDepositPayment(AccountSql.SELE_DP_LM);
	}
	/**
	 * 查询本月预付款表所有记录
	 * @return
	 */
	public Vector getThisMonthDespositPayment(){
		return DaoFactory.getPaymentDao().findDepositPayment(AccountSql.SELE_DP_TM);
	}
	
	/**
	 * 删除预付款表指定编号的记录
	 * @return
	 */
	public boolean delDespositPayment(DepositPaymentVO vo){
		return DaoFactory.getPaymentDao().updateDepositPayment(vo,AccountSql.DEL_DP);
	}
	
	/**
	 * 添加预付款记录
	 * @return
	 */
	public boolean addDespositPayment(DepositPaymentVO vo){
		return DaoFactory.getPaymentDao().updateDepositPayment(vo,AccountSql.ADD_DP);
	}
	
	/**
	 * 修改预付款表指定编号的记录
	 * @return
	 */
	public boolean updateDespositPayment(DepositPaymentVO vo){
		return DaoFactory.getPaymentDao().updateDepositPayment(vo,AccountSql.UP_DP);
	}
	
	/**
	 * 查询应付款表所有记录
	 * @return
	 */
	public Vector getAccountPayable(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_AP);
	}
	/**
	 * 查询本年度应付款表记录
	 * @return
	 */
	public Vector getYearAccountPayable(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_AP_TY);
	}
	/**
	 * 查询上个月应付款表所有记录
	 * @return
	 */
	public Vector getLastMonthAccountPayable(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_AP_LM);
	}
	/**
	 * 查询本月应付款表所有记录
	 * @return
	 */
	public Vector getThisMonthAccountPayable(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_AP_TM);
	}
	
	/**
	 * 删除应付款表指定编号的记录
	 * @return
	 */
	public boolean delAccountPayable(AccountPayableVO vo){
		return DaoFactory.getPaymentDao().updateAccountPayable(vo, AccountSql.DEL_AP);
	}
	
	/**
	 * 添加应付款记录
	 * @return
	 */
	public boolean addAccountPayable(AccountPayableVO vo){
		return DaoFactory.getPaymentDao().updateAccountPayable(vo,AccountSql.ADD_AP);
		
	}
	
	/**
	 * 修改应付款表指定编号的记录
	 * @return
	 */
	public boolean updateAccountPayable(AccountPayableVO vo){
		return DaoFactory.getPaymentDao().updateAccountPayable(vo,AccountSql.UP_AP);
	}
	
	/**
	 * 查询已付款记录
	 * @return
	 */
	public Vector getAccountPaid(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_AP_PAID);
	}
	/**
	 * 查询本年度已付款记录
	 * @return
	 */
	public Vector getYearAccountPaid(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_APD_TY);
	}
	/**
	 * 查询上个月已付款记录
	 * @return
	 */
	public Vector getLastMonthAccountPaid(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_APD_LM);
	}
	/**
	 * 查询本月已付款记录
	 * @return
	 */
	public Vector getThisMonthAccountPaid(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_APD_TM);
	}
	
	/**
	 * 查询供货商表指定供货商编号记录
	 * @return
	 */
	public Vector getSuppliersBill(int supp_id){
		return DaoFactory.getPaymentDao().findSuppliersBill(supp_id);
	}
	
	/**
	 * 查询进货单表所有记录
	 * @return
	 */
	public Vector getPurchaseInBill(int pib_id ){
		return DaoFactory.getPaymentDao().findPurchaseInBill(pib_id);
	}
	
	/**
	 * 得到预付款表表头
	 * @return
	 */
	public Vector getDespositPaymentTitle(){
		Vector dptitle=new Vector();
		dptitle.add("预付款编号");
		dptitle.add("发票号");
		dptitle.add("填票日期");
		dptitle.add("供货商编号");
		dptitle.add("预付款总额");
		dptitle.add("付款日期");
		return dptitle;
	}
	
	/**
	 * 得到供货商表表头
	 * @return
	 */
	public Vector getSuppliersBillTitle(){
		Vector sbtitle=new Vector();
		sbtitle.add("供货商编号");
		sbtitle.add("拼音编码");
		sbtitle.add("简称");
		sbtitle.add("名称");
		sbtitle.add("地址");
		sbtitle.add("邮编");
		sbtitle.add("类型");
		sbtitle.add("电话");
		sbtitle.add("传真");
		sbtitle.add("开户行");
		sbtitle.add("银行帐号");
		sbtitle.add("库房地址");
		sbtitle.add("库房电话");
		sbtitle.add("业务员编号");
		return sbtitle;
	}
	
	/**
	 * 得到应付款表表头
	 * @return
	 */
	public Vector getAccountPayableTitle(){
		Vector aptitle=new Vector();
		aptitle.add("应付款编号");
		aptitle.add("发票号");
		aptitle.add("填票日期");
		aptitle.add("进货单编号");
		aptitle.add("货物编号");
		aptitle.add("供货商编号");
		aptitle.add("数量");
		aptitle.add("进货单价");
		aptitle.add("应付款金额");
		aptitle.add("付款日期");
		aptitle.add("详细说明");
		aptitle.add("状态");
		aptitle.add("(减)预付");
		return aptitle;
	}
	
	/**
	 * 得到进货单表表头
	 * @return
	 */
	public Vector getPurchaseInBillTitle(){
		Vector pibtitle=new Vector();
		pibtitle.add("进货单编号");
		pibtitle.add("供货商编号");
		pibtitle.add("进货日期");
		pibtitle.add("业务员编号");
		pibtitle.add("制单人");
		pibtitle.add("验收员");
		pibtitle.add("保管员");
		pibtitle.add("订单编号");
		return pibtitle;
	}
	
	
}
