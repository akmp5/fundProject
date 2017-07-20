package com.enterprise_sss.dao.sql;

public interface CheckoutSql {
	//查询商品清单表所有货物编号
	String SELE_COMM="select comm_id from commodity_bill";
	// 查询进销存汇总表的最大编号
	String SELE_LID_JG="select * from jxc_gather where jg_id=(select max(jg_id) from jxc_gather)";
	// 查询进销存汇总表最后一次结帐的记录
	String SELE_LAST_JG="select * from jxc_gather where jg_date=(select max(jg_date) from jxc_gather)";
	// 查询进销存汇总表所有的记录
	String SELE_JG="select * from jxc_gather";
	//为进销存汇总表添加一条记录,并指定编号和日期
	String INSERT_JG="insert into jxc_gather(jg_id,jg_date,comm_id,jg_desc)values(?,?,?,'无')";
//	String INSERT_JG="insert into jxc_gather values(?,?,?,0,0,0,0,0,0,0,0,0,0,'无')";
	
	
	//查询获得最后一次结帐的日期
	String LASTDATE="select max(jg_date) from (select * from jxc_gather " +
	"where last_checkout_amount is not null)";
	//查询获得已设定的本次结帐的日期
	String THISDATE="select max(jg_date) from (select * from jxc_gather " +
			"where last_checkout_amount is null)";
	
	
	
	//查询进销存汇总表上期结帐数量和金额(即查询last_checkout_amount不为空的、jg_date最大的本期结帐数量和金额)
	String LAST_GATHER="select now_checkout_amount,now_checkout_money from jxc_gather " +
			"where jg_date=(select max(jg_date) from (select * from jxc_gather " +
			"where last_checkout_amount is not null)) and jg_id=?";
	//指定时间段内指定商品的销售数量和金额
	String SALE_GATHER="select sum(si_amount),sum(sale_price) from sale_items si,sale_bill sb " +
			"where comm_id=? and si.sb_id=sb.sb_id and " +
			"(sale_date between ? and ?)";
	//指定时间段内指定商品的进货数量和金额
	String PIN_GATHER=" select sum(pii_amount),sum(purc_price) " +
			"from purchase_in_items pii,purchase_in_bill pib where comm_id=? and " +
			" pii.pii_id=pib.pib_id and"+
			"(pib_date between ? and ?)" ;
	//指定商品的实时库存数量和金额
	String STOCK_GATHER=" select amount,money from stock  where comm_id=?";
	//指定商品的实时库存数量和金额
	String LOSS_GATHER="  select rpl_amount,rpl_money from report_profit_loss " +
			"where comm_id=? and (rpl_date between ? and ?)";
	
	
	
	//更新进销存汇总表
	String UPDATE_JG="update jxc_gather set last_checkout_amount=?," +
			"last_checkout_money=?,out_amount=?,out_money=?,int_amount=?,int_money=?," +
			"spill_loss_amount=?,spill_loss_money=?,now_checkout_amount=?," +
			"now_checkout_money=? where jg_id=?";
}
