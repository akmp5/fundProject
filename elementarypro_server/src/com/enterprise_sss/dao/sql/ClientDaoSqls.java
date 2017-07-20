package com.enterprise_sss.dao.sql;

public class ClientDaoSqls {

	public static String sql_add = "insert into client_bill values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static String sql_findMaxID = "select max(clie_id) from client_bill";

	public static String sql_del = "delete client_bill where clie_id = ?";

	public static String sql_find = "select * from client_bill where clie_id = ?";

	public static String sql_modify = "update client_bill set clie_spell_code = ?,clie_shortname = ?,clie_name = ?,clie_linkman = ?,clie_address = ?,Clie_postcode = ?,Clie_tel = ?,Clie_fax = ?,Clie_bank = ?,Clie_bank_account = ?,Clie_sort = ?,oper_id = ?,Clie_CreditLimt = ? where clie_id = ?";
	
	public static String sql_findAll = "select * from client_bill";
	
	public static String sql_findType_one = "select * from client_bill where clie_id = ?";

	public static String sql_findType_two = "select * from client_bill where oper_id = ?";
	
	public static String sql_findType_three = "select * from client_bill where clie_linkman = ?";
	
}
