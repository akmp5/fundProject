package com.enterprise_sss.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.enterprise_sss.util.MethodUtil;

/**
 * ���ݿ�������
 * @author Wang ming 2009-09-28
 * @version 1.0
 */
public class ConnectionDB {

	private DBConnectionManager db=DBConnectionManager.getDM();
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 */
	public Connection getConnection() {
		Connection con = db.getConnection("oracle");
		return con;
	}

	/**
	 * �ر����ݿ�����
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
	 * �ر����ݿ�����
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
