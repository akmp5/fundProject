package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.DepotInter;
import com.enterprise_sss.dao.sql.DepotDaoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.DepotVO;

public class DepotDao implements DepotInter {

	private DepotVO vo;
	
	private ConnectionDB db = new ConnectionDB(); // 创建ConnectionDB对象,并实例化

	private Connection con = db.getConnection(); // 从链接池获得链接

	private PreparedStatement ps = null; // 创建PreparedStatement对象

	private ResultSet rs = null; // 创建ResultSet对象
	
	public DepotDao(){
		
	}
	
	public DepotDao(DepotVO vo){
		this.vo = vo;
	}
	
	public void Depot_add() {
		try {
			ps = con.prepareStatement(DepotDaoSqls.sql_add);
			ps.setInt(1, vo.getDepo_id());
			ps.setString(2, "" + vo.getDepo_name());
			ps.setInt(3, vo.getDepo_sort());
			ps.setString(4, "" + vo.getDepo_desc());
			ps.executeQuery();
			LogOperator.writeMessageLog("库存ID：" + vo.getDepo_id(), DataUtil.name, "添加库存信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void Depot_del() {
		try {
			ps = con.prepareStatement(DepotDaoSqls.sql_del);
			ps.setInt(1, vo.getDepo_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("库存ID：" + vo.getDepo_id(), DataUtil.name, "删除库存信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public DepotVO Depot_find() {
		DepotVO v = null;
		try {
			ps = con.prepareStatement(DepotDaoSqls.sql_find);
			ps.setInt(1, vo.getDepo_id());
			rs = ps.executeQuery();
			if (rs.next()) {
				v = new DepotVO();
				v.setDepo_id(rs.getInt(1));
				v.setDepo_name(rs.getString(2));
				v.setDepo_sort(rs.getInt(3));
				v.setDepo_desc(rs.getString(4));
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return v;
	}

	public int Depot_findMaxID() {
		int id = -1;
		try {
			ps = con.prepareStatement(DepotDaoSqls.sql_findMaxID);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return id;
	}

	public void Depot_modify() {
		try {
			ps = con.prepareStatement(DepotDaoSqls.sql_modify);
			ps.setString(1, "" + vo.getDepo_name());
			ps.setInt(2, vo.getDepo_sort());
			ps.setString(3, "" + vo.getDepo_desc());
			ps.setInt(4, vo.getDepo_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("库存ID：" + vo.getDepo_id(), DataUtil.name, "修改库存信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void close() {
		db.closeDB(con, ps, rs);
	}

	public Vector Depot_findAll() {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(DepotDaoSqls.sql_findAll);
			rs = ps.executeQuery();
			data = dataParse(rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	public Vector Depot_findType(int type, String str) {
		Vector data = new Vector();
		String sql = null;
		if (type == 1) {
			return Depot_findAll();
		} else {
			if (type == 2) {
				sql = DepotDaoSqls.sql_findType_one;
			} else if (type == 3) {
				sql = DepotDaoSqls.sql_findType_two;
			}
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, str);
				rs = ps.executeQuery();
				data = dataParse(rs);
			} catch (SQLException e) {
				MethodUtil.LogOper(e);
			}
		}
		return data;
	}

	private Vector dataParse(ResultSet rs){
		Vector data = new Vector();
		try {
			while(rs.next()){
				Vector v = new Vector();
				v.add(rs.getInt(1));
				v.add("" + rs.getString(2));
				v.add(rs.getInt(3));
				v.add("" + rs.getString(4));
				data.add(v);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}
}
