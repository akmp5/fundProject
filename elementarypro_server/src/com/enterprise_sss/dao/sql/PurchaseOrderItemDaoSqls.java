package com.enterprise_sss.dao.sql;

public class PurchaseOrderItemDaoSqls {
	
	public static String sql_add = "insert into PURCHASE_ORDER_ITEMS values(?,?,?,?,?)";

	public static String sql_findMaxID = "select max(poi_id) from PURCHASE_ORDER_ITEMS";

	public static String sql_del = "delete PURCHASE_ORDER_ITEMS where poi_id = ?";

	public static String sql_find = "select * from PURCHASE_ORDER_ITEMS where poi_id = ?";
	
	public static String sql_findType = "select * from Purchase_order_items where po_id = ?";

	public static String sql_modify = "update PURCHASE_ORDER_ITEMS set po_id = ?,COM_ID = ?,POI_AMOUNT = ?,PURC_PRICE = ? where poi_id = ?";
	
	public static String sql_findAll = "select * from PURCHASE_ORDER_ITEMS";
	
	public static String sql_delType = "delete PURCHASE_ORDER_ITEMS where po_id = ?";
	
}
