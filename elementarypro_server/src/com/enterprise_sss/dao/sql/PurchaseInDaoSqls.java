package com.enterprise_sss.dao.sql;

public class PurchaseInDaoSqls {

	public static String sql_add = "insert into PURCHASE_IN_BILL values(?,?,?,?,?,?,?,?)";

	public static String sql_del = "delete PURCHASE_IN_BILL where PIB_ID = ?";

	public static String sql_find = "select * from PURCHASE_IN_BILL where PIB_ID = ?";

	public static String sql_modify = "update PURCHASE_IN_BILL set SUPP_ID = ?,PIB_DATE = ?,OPER_ID = ?,CBILL = ?,INSPECTOR = ?,KEEPER= ?,PO_ID = ? where PIB_ID = ?";
	
	public static String sql_findAll = "select * from PURCHASE_IN_BILL";
	
}
