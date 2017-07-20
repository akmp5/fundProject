package com.enterprise_sss.dao.sql;

public class ImportInfoSqls {

	public  String table_title;
	
	public  String sql_add_one;
	
	public  String sql_add_two;
	
	public  String sql_add_three;
	
	public  String sql_add_four;

	public  String sql_add_five;
	
	public  String sql_add_six;
	
	public  String sql_add_seven;
	
	public  String sql_add_eight;
	
	public  String sql_add_nine;
	
	public  String sql_add_ten;
	
	public  String sql_add_eleven;
	
	public  String sql_add_twelve;
	
	public  String sql_add_thirteen;
	
	public  String sql_add_fourteen;
	
	public  String sql_add_fifteen;
	
	public  String sql_add_sixteen;

	
	public void init(){
		
		sql_add_one = "insert into " + table_title + " values(?)";
		
		sql_add_two = "insert into " + table_title + " values(?,?)";
		
		sql_add_three = "insert into " + table_title + " values(?,?,?)";
		
		sql_add_four = "insert into " + table_title + " values(?,?,?,?)";

		sql_add_five = "insert into " + table_title + " values(?,?,?,?,?)";
		
		sql_add_six = "insert into " + table_title + " values(?,?,?,?,?,?)";
		
		sql_add_seven = "insert into " + table_title + " values(?,?,?,?,?,?,?)";
		
		sql_add_eight = "insert into " + table_title + " values(?,?,?,?,?,?,?,?)";
		
		sql_add_nine = "insert into " + table_title + " values(?,?,?,?,?,?,?,?,?)";
		
		sql_add_ten = "insert into " + table_title + " values(?,?,?,?,?,?,?,?,?,?)";
		
		sql_add_eleven = "insert into " + table_title + " values(?,?,?,?,?,?,?,?,?,?,?)";
		
		sql_add_twelve = "insert into " + table_title + " values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		sql_add_thirteen = "insert into " + table_title + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		sql_add_fourteen = "insert into " + table_title + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		sql_add_fifteen = "insert into " + table_title + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		sql_add_sixteen = "insert into " + table_title + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	}
	
	public  String getTable_title() {
		return table_title;
	}

	public  void setTable_title(String table_title) {
		this.table_title = table_title;
	}
}
