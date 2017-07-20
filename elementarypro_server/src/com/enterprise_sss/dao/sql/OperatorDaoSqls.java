package com.enterprise_sss.dao.sql;

public class OperatorDaoSqls {

	public static String sql_add = "insert into operator_bill values(?,?,?,?,?,?,?,?,?,?)";

	public static String sql_findMaxID = "select max(oper_id) from operator_bill";

	public static String sql_del = "delete operator_bill where oper_id = ?";

	public static String sql_find = "select * from operator_bill where oper_id = ?";

	public static String sql_modify = "update operator_bill set oper_spell_code = ?,oper_name = ?,OPER_SEX = ?,oper_tel = ?,oper_mobile_tel = ?,oper_address = ?,oper_postcode = ?,oper_ID_number = ?,oper_sort = ? where oper_id = ?";
	
	public static String sql_findAll = "select * from operator_bill";
	
	public static String sql_findType_one = "select * from operator_bill where oper_id = ?";
	
	public static String sql_findType_two = "select * from operator_bill where oper_name = ?";
	
	public static String sql_findType_three = "select * from operator_bill where oper_id_number = ?";
}
