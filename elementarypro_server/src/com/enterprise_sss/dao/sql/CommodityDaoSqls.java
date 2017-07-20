package com.enterprise_sss.dao.sql;

public class CommodityDaoSqls {

	public static String sql_add = "insert into commodity_bill values(?,?,?,?,?,?,?,?,?,?,?,?)";

	public static String sql_findMaxID = "select max(comm_id) from commodity_bill";

	public static String sql_del = "delete commodity_bill where comm_id = ?";

	public static String sql_find = "select * from commodity_bill where comm_id = ?";

	public static String sql_findBar_code = "select * from commodity_bill where comm_bar_code = ? and comm_id <> ?";

	public static String sql_modify = "update commodity_bill set comm_bar_code = ?,"
			+ "comm_name = ?,comm_spell_code = ?,comm_standard = ?,comm_unit = ?,comm_producing_area = ?,"
			+ "comm_sort = ?,purchase_price = ?,sale_price1 = ?,sale_price2 = ?,lowest_sale_price = ? where comm_id = ?";
	
	public static String sql_findAll = "select * from commodity_bill";
	
	public static String sql_findType_one = "select * from commodity_bill where comm_id = ?";
	
	public static String sql_findType_two = "select * from commodity_bill where comm_bar_code = ?";
	
	public static String sql_findType_three = "select * from commodity_bill where comm_sort = ?";
	
	public static String sql_findType_four = "select * from commodity_bill where sale_price2 > ? and sale_price2 <= ?";
	
	public static String sql_findType_five = "select * from commodity_bill where sale_price1 > ? and sale_price1 <= ?";
	
	public static String sql_find_sort = "select comm_sort from commodity_bill group by comm_sort";
}
