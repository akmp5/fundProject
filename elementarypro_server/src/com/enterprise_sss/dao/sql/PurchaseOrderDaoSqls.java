package com.enterprise_sss.dao.sql;

public class PurchaseOrderDaoSqls {

	public static String sql_add = "insert into purchase_order values(?,?,?,?,?,?,?)";

	public static String sql_del = "delete purchase_order where po_id = ?";

	public static String sql_find = "select * from purchase_order where po_id = ?";

	public static String sql_modify = "update purchase_order set supp_id = ?,po_date = ?,po_begin_date = ?,po_end_date = ?,oper_id = ?,cbill = ? where po_id = ?";
	
	public static String sql_findAll = "select * from purchase_order";
	
}
