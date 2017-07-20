package com.enterprise_sss.control;

import java.util.Vector;

import sun.misc.Cleaner;

import com.enterprise_sss.dao.factory.DaoFactory;
import com.enterprise_sss.dao.sql.AccountSql;
import com.enterprise_sss.vo.AccountPayableVO;
import com.enterprise_sss.vo.AccountReceivableVO;
import com.enterprise_sss.vo.DepositReceivableVO;

/**
 * 应收款数据服务类
 * 
 * @author yiguo
 * @version 1.0
 */
public class ReceivableServer {

	/**
	 * 查询预收款表所有记录
	 * @return
	 */
	public Vector getDespositReceivable(){
		return DaoFactory.getReceivableDao().findDepositReceivable(AccountSql.SELE_DR);
	}
	
	/**
	 * 查询本年度预收款表所有记录
	 * @return
	 */
	public Vector getYearDespositReceivable(){
		return DaoFactory.getReceivableDao().findDepositReceivable(AccountSql.SELE_DR_TY);
	}
	/**
	 * 查询上个月预收款表所有记录
	 * @return
	 */
	public Vector getLastMonthDespositReceivable(){
		return DaoFactory.getReceivableDao().findDepositReceivable(AccountSql.SELE_DR_LM);
	}
	/**
	 * 查询本月预收款表所有记录
	 * @return
	 */
	public Vector getThisMonthDespositReceivable(){
		return DaoFactory.getReceivableDao().findDepositReceivable(AccountSql.SELE_DR_TM);
	}
	
	/**
	 * 删除预收款表指定编号记录
	 * @return
	 */
	public boolean delDespositReceivable(DepositReceivableVO vo){
		return DaoFactory.getReceivableDao().updateDepositReceivable(vo, AccountSql.DEL_DR);
	}
	
	/**
	 * 添加预收款表指定编号记录
	 * @return
	 */
	public boolean addDespositReceivable(DepositReceivableVO vo){
		return DaoFactory.getReceivableDao().updateDepositReceivable(vo, AccountSql.ADD_DR);
	}
	
	/**
	 * 修改预收款表指定编号记录
	 * @return
	 */
	public boolean updateDespositReceivable(DepositReceivableVO vo){
		return DaoFactory.getReceivableDao().updateDepositReceivable(vo, AccountSql.UP_DR);
	}
	
	/**
	 * 查询应收款表所有记录
	 * @return
	 */
	public Vector getAccountReceivable(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_AR);
	}
	
	/**
	 * 查询本年度应收款表记录
	 * @return
	 */
	public Vector getYearAccountReceivable(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_AR_TY);
	}
	/**
	 * 查询上个月应收款表所有记录
	 * @return
	 */
	public Vector getLastMonthAccountReceivable(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_AR_LM);
	}
	/**
	 * 查询本月应收款表所有记录
	 * @return
	 */
	public Vector getThisMonthAccountReceivable(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_AR_TM);
	}
	
	/**
	 * 删除应收款表指定编号的记录
	 * @return
	 */
	public boolean delAccountReceivable(AccountReceivableVO vo){
		return DaoFactory.getReceivableDao().updateAccountReceivable(vo, AccountSql.DEL_AR);
	}
	
	/**
	 * 添加应收款记录
	 * @return
	 */
	public boolean addAccountReceivable(AccountReceivableVO vo){
		return DaoFactory.getReceivableDao().updateAccountReceivable(vo,AccountSql.ADD_AR);
		
	}
	
	/**
	 * 修改应收款表指定编号的记录
	 * @return
	 */
	public boolean updateAccountReceivable(AccountReceivableVO vo){
		return DaoFactory.getReceivableDao().updateAccountReceivable(vo,AccountSql.UP_AR);
	}
	
	/**
	 * 查询已收款表已收款记录
	 * @return
	 */
	public Vector getAccountReceived(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_AR_RECEIVED);
	}
	
	/**
	 * 查询本年度已收款记录
	 * @return
	 */
	public Vector getYearAccountReceived(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_ARD_TY);
	}
	/**
	 * 查询上个月已收款记录
	 * @return
	 */
	public Vector getLastMonthAccountReceived(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_ARD_LM);
	}
	/**
	 * 查询本月已收款记录
	 * @return
	 */
	public Vector getThisMonthAccountReceived(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_ARD_TM);
	}
	
	/**
	 * 查询客户表指定客户编号的记录
	 * @return
	 */
	public Vector getClientBill(int clie_id){
		return DaoFactory.getReceivableDao().findClientBill(clie_id);
	}
	
	/**
	 * 查询销售单表指定销售单编号的记录
	 * @return
	 */
	public Vector getSaleBill(int sb_id){
		return DaoFactory.getReceivableDao().findSaleBill(sb_id);
	}
	
	/**
	 * 得到预收款表表头
	 * @return
	 */
	public Vector getDespositReceivableTitle(){
		Vector drtitle=new Vector();
		drtitle.add("预收款编号");
		drtitle.add("发票号");
		drtitle.add("填票日期");
		drtitle.add("客户编号");
		drtitle.add("预收款总额");
		drtitle.add("收款日期");
		return drtitle;
	}
	
	/**
	 * 得到客户表表头
	 * @return
	 */
	public Vector getClientBillTitle(){
		Vector cbtitle=new Vector();
		cbtitle.add("客户编号");
		cbtitle.add("拼音编码");
		cbtitle.add("简称");
		cbtitle.add("名称");
		cbtitle.add("联系人");
		cbtitle.add("地址");
		cbtitle.add("邮编");
		cbtitle.add("电话");
		cbtitle.add("传真");
		cbtitle.add("开户行");
		cbtitle.add("银行帐号");
		cbtitle.add("性质");
		cbtitle.add("业务员编号");
		cbtitle.add("授信额度");
		return cbtitle;
	}
	
	/**
	 * 得到应收款表表头
	 * @return
	 */
	public Vector getAccountReceivableTitle(){
		Vector artitle=new Vector();
		artitle.add("应收款编号");
		artitle.add("发票号");
		artitle.add("填票日期");
		artitle.add("销售单编号");
		artitle.add("货物编号");
		artitle.add("客户编号");
		artitle.add("数量");
		artitle.add("销售价");
		artitle.add("应收款金额");
		artitle.add("收款日期");
		artitle.add("详细说明");
		artitle.add("状态");
		artitle.add("(减)预收");
		return artitle;
	}
	
	/**
	 * 得到销售单表表头
	 * @return
	 */
	public Vector getSaleBillTitle(){
		Vector aotitle=new Vector();
		aotitle.add("销售单编号");
		aotitle.add("客户编号");
		aotitle.add("销售日期");
		aotitle.add("业务员编号");
		aotitle.add("制表人");
		aotitle.add("保管员");
		aotitle.add("销售订单编号");
		return aotitle;
	}
	
}
