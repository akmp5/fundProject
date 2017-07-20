package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.ImportInfoInter;
import com.enterprise_sss.dao.sql.ImportInfoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.MethodUtil;

public class ImportInfoDao implements ImportInfoInter {

	private ConnectionDB db = new ConnectionDB(); // 创建ConnectionDB对象,并实例化

	private Connection con = db.getConnection(); // 从链接池获得链接

	private PreparedStatement ps = null; // 创建PreparedStatement对象

	private ResultSet rs = null; // 创建ResultSet对象
	
	private ImportInfoSqls is = new ImportInfoSqls();

	public void add(String table_title, Vector vo) {
		String sql = null;
		is.setTable_title(table_title);
		is.init();
		switch (vo.size()) {
		case 1:
			sql = is.sql_add_one;
			break;
		case 2:
			sql = is.sql_add_two;
			break;
		case 3:
			sql = is.sql_add_three;
			break;
		case 4:
			sql = is.sql_add_four;
			break;
		case 5:
			sql = is.sql_add_five;
			break;
		case 6:
			sql = is.sql_add_six;
			break;
		case 7:
			sql = is.sql_add_seven;
			break;
		case 8:
			sql = is.sql_add_eight;
			break;
		case 9:
			sql = is.sql_add_nine;
			break;
		case 10:
			sql = is.sql_add_ten;
			break;
		case 11:
			sql = is.sql_add_eleven;
			break;
		case 12:
			sql = is.sql_add_twelve;
			break;
		case 13:
			sql = is.sql_add_thirteen;
			break;
		case 14:
			sql = is.sql_add_fourteen;
			break;
		case 15:
			sql = is.sql_add_fifteen;
			break;
		case 16:
			sql = is.sql_add_sixteen;
			break;
		}

		try {
			ps = con.prepareStatement(sql);
			//ps.setString(1, table_title);
			for (int i = 0; i < vo.size(); i++) {
				ps.setString(i+1, vo.get(i).toString());
			}
			ps.executeQuery();
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void close() {
		db.closeDB(con, ps, rs);
	}
}
