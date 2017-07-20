package com.enterprise_sss.dao.sql;

public class ThreadDaoSqls {
	
	public static String sql_login = "select c.*,s.amount,s.money,s.depo_id from stock s,commodity_bill c where s.depo_id in(select depo_id from depot_bill where stor_id in(select stor_id from user_bill where user_name = ?)) and s.comm_id = c.comm_id";
	
	public static String sql_apply = "insert into cargo_transfer_apply values(q_cargo_transfer_apply.nextval,?,?,?,?,?,?,'未调')";
	
	public static String sql_stor_id = "select stor_id from user_bill where user_name = ?";
	
	public static String sql_apply_confirm = "select * from cargo_transfer_apply where stor_id in(select stor_id from user_bill where user_name = ?) and CTA_ISREPLY = '未调'";
	
	public static String sql_apply_modify = "update cargo_transfer_apply set cta_isReply = '已调' where cta_id = ?";
}
