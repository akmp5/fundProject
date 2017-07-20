package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.ThreadInter;
import com.enterprise_sss.dao.sql.ThreadDaoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.UserVO;

public class ThreadDao implements ThreadInter {

	private ConnectionDB db=new ConnectionDB();     //创建ConnectionDB对象,并实例化
	private Connection con=db.getConnection();    //从链接池获得链接
	private PreparedStatement ps=null;          //创建PreparedStatement对象
	private ResultSet rs=null;               //创建ResultSet对象
	
	
	public Vector threadLogin(UserVO uvo) {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(ThreadDaoSqls.sql_login);
			ps.setString(1, uvo.getUser());
			rs = ps.executeQuery();
			while(rs.next()){
				Vector v = new Vector();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				v.add(rs.getString(8));
				v.add(rs.getDouble(9));
				v.add(rs.getDouble(10));
				v.add(rs.getDouble(11));
				v.add(rs.getDouble(12));
				v.add(rs.getInt(13));
				v.add(rs.getDouble(14));
				v.add(rs.getInt(15));
				data.add(v);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}
	
	public void close(){
		db.closeDB(con, ps, rs);
	}

	public boolean threadApply(Vector v) {
		boolean b = false;
		try {
			String str = v.get(3).toString();
			int stor_id = getStorID(str);
			ps = con.prepareStatement(ThreadDaoSqls.sql_apply);
			ps.setInt(1, stor_id);
			ps.setInt(2, new Integer(v.get(0).toString()));
			ps.setInt(3, new Integer(v.get(1).toString()));
			ps.setDate(4, (Date)v.get(2));
			ps.setString(5, str);
			ps.setString(6, v.get(4).toString());
			int n = ps.executeUpdate();
			if (n != 0) {
				b = true;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return b;
	}

	private int getStorID(String user_name){
		int id = -1;
		try {
			ps = con.prepareStatement(ThreadDaoSqls.sql_stor_id);
			ps.setString(1, user_name);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return id;
	}

	public Vector threadApplyConfirm(Vector v) {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(ThreadDaoSqls.sql_apply_confirm);
			ps.setString(1, v.get(0).toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector vd = new Vector();
				vd.add(rs.getInt(1));
				vd.add(rs.getInt(2));
				vd.add(rs.getInt(3));
				vd.add(rs.getInt(4));
				vd.add(rs.getDate(5));
				vd.add(rs.getString(6));
				vd.add(rs.getString(7));
				vd.add(rs.getString(8));
				data.add(vd);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	public boolean threadApplyModify(Vector v) {
		boolean b = false;
		try {
			ps = con.prepareStatement(ThreadDaoSqls.sql_apply_modify);
			ps.setInt(1, Integer.parseInt(v.get(0).toString()));
			int n = ps.executeUpdate();
			if (n != 0) {
				b = true;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return b;
	}
	
}
