package com.enterprise_sss.dao.sql;

public class LoginDaoSqls {
	
	public static String sql_login = "select * from user_bill where user_name = ? and user_password = ? and popedom_id = ?";
	
	public static String sql_modifyPw = "update user_bill set user_password = ? where user_name = ? and popedom_id = ?";
}
