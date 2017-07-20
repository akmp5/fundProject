package com.enterprise_sss.dao.sql;

public class PurchaseInItemSqls {

	public static String sql_add = "insert into PURCHASE_IN_ITEMS values(?,?,?,?,?)";

	public static String sql_findMaxID = "select max(pii_id) from PURCHASE_IN_ITEMS";

	public static String sql_del = "delete PURCHASE_IN_ITEMS where pii_id = ?";

	public static String sql_find = "select * from PURCHASE_IN_ITEMS where pii_id = ?";
	
	public static String sql_findType = "select * from PURCHASE_IN_ITEMS where pib_id = ?";

	public static String sql_modify = "update PURCHASE_IN_ITEMS set pib_id = ?,comm_id = ?,pii_amount = ?,purc_price = ? where pii_id = ?";
	
	public static String sql_findAll = "select * from PURCHASE_IN_ITEMS";
	
	public static String sql_delType = "delete PURCHASE_IN_ITEMS where pib_id = ?";
	
}
