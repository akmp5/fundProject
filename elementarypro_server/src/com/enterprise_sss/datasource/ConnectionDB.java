package com.enterprise_sss.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.enterprise_sss.util.MethodUtil;

/**
 * 数据库连接类
 * @author Wang ming 2009-09-28
 * @version 1.0
 */
public class ConnectionDB {

	private DBConnectionManager db=DBConnectionManager.getDM();
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection() {
		Connection con = db.getConnection("oracle");
		return con;
	}

	/**
	 * 关闭数据库连接
	 * @param con
	 * @param st
	 * @param rs
	 */
	public void closeDB(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		} finally {
			closeDB(con, st);
		}
	}

	/**
	 * 关闭数据库连接
	 * @param con
	 * @param st
	 */
	public void closeDB(Connection con, Statement st) {

		try {
			if (st != null)
				st.close();
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		} finally {
				if (con != null)
					db.free("oracle", con);
		}
	}

}
