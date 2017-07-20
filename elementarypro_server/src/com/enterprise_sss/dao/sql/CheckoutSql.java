package com.enterprise_sss.dao.sql;

public interface CheckoutSql {
	//��ѯ��Ʒ�嵥�����л�����
	String SELE_COMM="select comm_id from commodity_bill";
	// ��ѯ��������ܱ�������
	String SELE_LID_JG="select * from jxc_gather where jg_id=(select max(jg_id) from jxc_gather)";
	// ��ѯ��������ܱ����һ�ν��ʵļ�¼
	String SELE_LAST_JG="select * from jxc_gather where jg_date=(select max(jg_date) from jxc_gather)";
	// ��ѯ��������ܱ����еļ�¼
	String SELE_JG="select * from jxc_gather";
	//Ϊ��������ܱ����һ����¼,��ָ����ź�����
	String INSERT_JG="insert into jxc_gather(jg_id,jg_date,comm_id,jg_desc)values(?,?,?,'��')";
//	String INSERT_JG="insert into jxc_gather values(?,?,?,0,0,0,0,0,0,0,0,0,0,'��')";
	
	
	//��ѯ������һ�ν��ʵ�����
	String LASTDATE="select max(jg_date) from (select * from jxc_gather " +
	"where last_checkout_amount is not null)";
	//��ѯ������趨�ı��ν��ʵ�����
	String THISDATE="select max(jg_date) from (select * from jxc_gather " +
			"where last_checkout_amount is null)";
	
	
	
	//��ѯ��������ܱ����ڽ��������ͽ��(����ѯlast_checkout_amount��Ϊ�յġ�jg_date���ı��ڽ��������ͽ��)
	String LAST_GATHER="select now_checkout_amount,now_checkout_money from jxc_gather " +
			"where jg_date=(select max(jg_date) from (select * from jxc_gather " +
			"where last_checkout_amount is not null)) and jg_id=?";
	//ָ��ʱ�����ָ����Ʒ�����������ͽ��
	String SALE_GATHER="select sum(si_amount),sum(sale_price) from sale_items si,sale_bill sb " +
			"where comm_id=? and si.sb_id=sb.sb_id and " +
			"(sale_date between ? and ?)";
	//ָ��ʱ�����ָ����Ʒ�Ľ��������ͽ��
	String PIN_GATHER=" select sum(pii_amount),sum(purc_price) " +
			"from purchase_in_items pii,purchase_in_bill pib where comm_id=? and " +
			" pii.pii_id=pib.pib_id and"+
			"(pib_date between ? and ?)" ;
	//ָ����Ʒ��ʵʱ��������ͽ��
	String STOCK_GATHER=" select amount,money from stock  where comm_id=?";
	//ָ����Ʒ��ʵʱ��������ͽ��
	String LOSS_GATHER="  select rpl_amount,rpl_money from report_profit_loss " +
			"where comm_id=? and (rpl_date between ? and ?)";
	
	
	
	//���½�������ܱ�
	String UPDATE_JG="update jxc_gather set last_checkout_amount=?," +
			"last_checkout_money=?,out_amount=?,out_money=?,int_amount=?,int_money=?," +
			"spill_loss_amount=?,spill_loss_money=?,now_checkout_amount=?," +
			"now_checkout_money=? where jg_id=?";
}
