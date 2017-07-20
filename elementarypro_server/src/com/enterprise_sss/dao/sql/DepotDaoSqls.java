package com.enterprise_sss.dao.sql;

public class DepotDaoSqls {

	public static String sql_add = "insert into depot_bill values(?,?,?,?)";

	public static String sql_findMaxID = "select max(depo_id) from depot_bill";

	public static String sql_del = "delete depot_bill where depo_id = ?";

	public static String sql_find = "select * from depot_bill where depo_id = ?";

	public static String sql_modify = "update depot_bill set DEPO_NAME = ?,STOR_ID = ?,DEPO_DESC = ? where depo_id = ?";
	
	public static String sql_findAll = "select * from depot_bill";
	
	public static String sql_findType_one = "select * from depot_bill where depo_id = ?";
	
	public static String sql_findType_two = "select * from depot_bill where depo_desc = ?";
}
