package com.enterprise_sss.dao.sql;

public class SupplierDaoSqls {
	
	public static String sql_add = "insert into suppliers_bill values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static String sql_findMaxID = "select max(supp_id) from suppliers_bill";

	public static String sql_del = "delete suppliers_bill where supp_id = ?";

	public static String sql_find = "select * from suppliers_bill where supp_id = ?";

	public static String sql_modify = "update suppliers_bill set SUPP_SPELL_CODE = ?,SUPP_SHORTNAME = ?,SUPP_NAME = ?,SUPP_ADDRESS = ?,SUPP_POSTCODE = ?,SUPP_SORT = ?,SUPP_TEL = ?,SUPP_FAX = ?,SUPP_BANK = ?,SUPP_BANK_ACCOUNT = ?,SUPP_STORAGE_ADDRESS = ?,SUPP_STORAGE_TEL = ?,OPER_ID = ? where supp_id = ?";
	
	public static String sql_findAll = "select * from suppliers_bill";

	public static String sql_findType_one = "select * from suppliers_bill where supp_id = ?";
	
	public static String sql_findType_two = "select * from suppliers_bill where supp_name = ?";
	
	public static String sql_findType_three = "select * from suppliers_bill where oper_id = ?";
}
