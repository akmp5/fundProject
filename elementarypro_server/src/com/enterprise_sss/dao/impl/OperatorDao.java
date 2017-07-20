package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.OperatorInter;
import com.enterprise_sss.dao.sql.OperatorDaoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.OperatorVO;

public class OperatorDao implements OperatorInter {

	private OperatorVO vo;
	
	private ConnectionDB db = new ConnectionDB(); // 创建ConnectionDB对象,并实例化

	private Connection con = db.getConnection(); // 从链接池获得链接

	private PreparedStatement ps = null; // 创建PreparedStatement对象

	private ResultSet rs = null; // 创建ResultSet对象
	
	public OperatorDao(){
		
	}
	
	public OperatorDao(OperatorVO vo){
		this.vo = vo;
	}
	
	public void Operator_add() {
		try {
			ps = con.prepareStatement(OperatorDaoSqls.sql_add);
			ps.setInt(1, vo.getOper_id());
			ps.setString(2, "" + vo.getOper_spell_code());
			ps.setString(3, "" + vo.getOper_name());
			ps.setString(4, "" + vo.getSex());
			ps.setString(5, "" + vo.getOper_tel());
			ps.setString(6, "" + vo.getOper_mobile_tel());
			ps.setString(7, "" + vo.getOper_address());
			ps.setString(8, "" + vo.getOper_postcode());
			ps.setString(9, "" + vo.getOper_ID_number());
			ps.setString(10, "" + vo.getOper_sort());
			ps.executeQuery();
			LogOperator.writeMessageLog("业务员ID：" + vo.getOper_id(), DataUtil.name, "添加业务员信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void Operator_del() {
		try {
			ps = con.prepareStatement(OperatorDaoSqls.sql_del);
			ps.setInt(1, vo.getOper_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("业务员ID：" + vo.getOper_id(), DataUtil.name, "删除业务员信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public OperatorVO Operator_find() {
		OperatorVO v = null;
		try {
			ps = con.prepareStatement(OperatorDaoSqls.sql_find);
			ps.setInt(1, vo.getOper_id());
			rs = ps.executeQuery();
			if (rs.next()) {
				v = new OperatorVO();
				v.setOper_id(rs.getInt(1));
				v.setOper_spell_code(rs.getString(2));
				v.setOper_name(rs.getString(3));
				v.setSex(rs.getString(4));
				v.setOper_tel(rs.getString(5));
				v.setOper_mobile_tel(rs.getString(6));
				v.setOper_address(rs.getString(7));
				v.setOper_postcode(rs.getString(8));
				v.setOper_ID_number(rs.getString(9));
				v.setOper_sort(rs.getString(10));
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return v;
	}

	public int Operator_findMaxID() {
		int id = -1;
		try {
			ps = con.prepareStatement(OperatorDaoSqls.sql_findMaxID);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return id;
	}

	public void Operator_modify() {
		try {
			ps = con.prepareStatement(OperatorDaoSqls.sql_modify);
			ps.setString(1, "" + vo.getOper_spell_code());
			ps.setString(2, "" + vo.getOper_name());
			ps.setString(3, "" + vo.getSex());
			ps.setString(4, "" + vo.getOper_tel());
			ps.setString(5, "" + vo.getOper_mobile_tel());
			ps.setString(6, "" + vo.getOper_address());
			ps.setString(7, "" + vo.getOper_postcode());
			ps.setString(8, "" + vo.getOper_ID_number());
			ps.setString(9, "" + vo.getOper_sort());
			ps.setInt(10, vo.getOper_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("业务员ID：" + vo.getOper_id(), DataUtil.name, "修改业务员信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void close() {
		db.closeDB(con, ps, rs);
	}

	public Vector Operator_findAll() {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(OperatorDaoSqls.sql_findAll);
			rs = ps.executeQuery();
			data = dataParse(rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	public Vector Operator_findType(int type, String str) {
		Vector data = new Vector();
		String sql = null;
		if (type == 1) {
			return Operator_findAll();
		} else {
			if (type == 2) {
				sql = OperatorDaoSqls.sql_findType_one;
			} else if (type == 3) {
				sql = OperatorDaoSqls.sql_findType_two;
			} else if (type == 4) {
				sql = OperatorDaoSqls.sql_findType_three;
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
				v.add("" + rs.getString(3));
				v.add("" + rs.getString(4));
				v.add("" + rs.getString(5));
				v.add("" + rs.getString(6));
				v.add("" + rs.getString(7));
				v.add("" + rs.getString(8));
				v.add("" + rs.getString(9));
				v.add("" + rs.getString(10));
				data.add(v);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}
	
}
