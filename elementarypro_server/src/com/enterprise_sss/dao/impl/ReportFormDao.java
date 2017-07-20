package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.ReportFormInter;
import com.enterprise_sss.dao.sql.ReportFormSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.MethodUtil;

public class ReportFormDao implements ReportFormInter {
	
	private ConnectionDB db = new ConnectionDB(); // 创建ConnectionDB对象,并实例化

	private Connection con = db.getConnection(); // 从链接池获得链接

	private PreparedStatement ps = null; // 创建PreparedStatement对象

	private ResultSet rs = null; // 创建ResultSet对象

	public Vector find(int type,String str) {
		Vector data = null;
		String sql = null;
		if (type == 1) {
			sql = ReportFormSqls.sql_purchase;
		} else if (type == 2) {
			sql = ReportFormSqls.sql_sale;
		} else if (type == 3) {
			sql = ReportFormSqls.sql_depot;
		}
		try {
			ps = con.prepareStatement(sql);
			if (type == 3) {
				ps.setString(1, str);
			}
			rs = ps.executeQuery();
			data = dataParse(rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}
	
	private Vector dataParse(ResultSet rs){
		Vector data = new Vector();
		try {
			while(rs.next()){
				Vector v = new Vector();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				data.add(v);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	public void close() {
		db.closeDB(con, ps, rs);
	}

	public Vector findAll() {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(ReportFormSqls.sql_depot_findAll);
			rs = ps.executeQuery();
			while(rs.next()){
				data.add(rs.getString(1));
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

}
