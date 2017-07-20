package com.enterprise_sss.dao.sql;

public class PurchaseDaoSqls {

	public static String sql_add = "insert into purchase_contract values(?,?,?,?,?,?,?,?)";

	public static String sql_findMaxID = "select max(pc_id) from purchase_contract";

	public static String sql_del = "delete purchase_contract where pc_id = ?";

	public static String sql_find = "select * from purchase_contract where pc_id = ?";

	public static String sql_modify = "update purchase_contract set supp_id = ?,comm_id = ?,pur_price = ?,PC_PAY_METHOD = ?,PC_PAY_PERIOD = ?,PC_DATE = ?,PC_PERIOD = ? where pc_id = ?";
	
	public static String sql_findAll = "select * from purchase_contract";
	
	public static String sql_findType_one = "select * from purchase_contract where pc_id = ?";
	
	public static String sql_findType_two = "select * from purchase_contract where supp_id = ?";
	
	public static String sql_findType_three = "select * from purchase_contract where comm_id = ?";
	
	public static String sql_findType_four = "select * from purchase_contract where pur_price > ? and pur_price < ?";
}
