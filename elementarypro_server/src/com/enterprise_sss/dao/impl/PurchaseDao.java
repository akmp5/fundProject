package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.PurchaseInter;
import com.enterprise_sss.dao.sql.PurchaseDaoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.PurchaseVO;

public class PurchaseDao implements PurchaseInter {

	private PurchaseVO vo;
	
	private ConnectionDB db = new ConnectionDB(); // 创建ConnectionDB对象,并实例化

	private Connection con = db.getConnection(); // 从链接池获得链接

	private PreparedStatement ps = null; // 创建PreparedStatement对象

	private ResultSet rs = null; // 创建ResultSet对象
	
	public PurchaseDao(){
		
	}
	
	public PurchaseDao(PurchaseVO vo){
		this.vo = vo;
	}
	
	public void Purchase_add() {
		try {
			ps = con.prepareStatement(PurchaseDaoSqls.sql_add);
			ps.setInt(1, vo.getPc_id());
			ps.setInt(2, vo.getSupp_id());
			ps.setInt(3, vo.getComm_id());
			ps.setDouble(4, vo.getPurc_price());
			ps.setString(5, "" + vo.getPc_pay_method());
			ps.setString(6, "" + vo.getPc_pay_period());
			ps.setDate(7, vo.getPc_date());
			ps.setString(8, "" + vo.getPc_period());
			ps.executeQuery();
			LogOperator.writeMessageLog("采购合同ID：" + vo.getPc_id(), DataUtil.name, "添加采购合同信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void Purchase_del() {
		try {
			ps = con.prepareStatement(PurchaseDaoSqls.sql_del);
			ps.setInt(1, vo.getPc_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("采购合同ID：" + vo.getPc_id(), DataUtil.name, "删除采购合同信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public PurchaseVO Purchase_find() {
		PurchaseVO v = null;
		try {
			ps = con.prepareStatement(PurchaseDaoSqls.sql_find);
			ps.setInt(1, vo.getPc_id());
			rs = ps.executeQuery();
			if (rs.next()) {
				v = new PurchaseVO();
				v.setPc_id(rs.getInt(1));
				v.setSupp_id(rs.getInt(2));
				v.setComm_id(rs.getInt(3));
				v.setPurc_price(rs.getDouble(4));
				v.setPc_pay_method(rs.getString(5));
				v.setPc_pay_period(rs.getString(6));
				v.setPc_date(rs.getDate(7));
				v.setPc_period(rs.getString(8));
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return v;
	}

	public int Purchase_findMaxID() {
		int id = -1;
		try {
			ps = con.prepareStatement(PurchaseDaoSqls.sql_findMaxID);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return id;
	}

	public void Purchase_modify() {
		try {
			ps = con.prepareStatement(PurchaseDaoSqls.sql_modify);
			ps.setInt(1, vo.getSupp_id());
			ps.setInt(2, vo.getComm_id());
			ps.setDouble(3, vo.getPurc_price());
			ps.setString(4, "" + vo.getPc_pay_method());
			ps.setString(5, "" + vo.getPc_pay_period());
			ps.setDate(6, vo.getPc_date());
			ps.setString(7, "" + vo.getPc_period());
			ps.setInt(8, vo.getPc_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("采购合同ID：" + vo.getPc_id(), DataUtil.name, "修改采购合同信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void close() {
		db.closeDB(con, ps, rs);
	}

	public Vector Purchase_findAll() {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(PurchaseDaoSqls.sql_findAll);
			rs = ps.executeQuery();
			data = dataParse(rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	public Vector Purchase_findType(int type, String str1, String str2) {
		Vector data = new Vector();
		String sql = null;
		if (type == 1) {
			return Purchase_findAll();
		} else {
			if (type == 2) {
				sql = PurchaseDaoSqls.sql_findType_one;
			} else if (type == 3) {
				sql = PurchaseDaoSqls.sql_findType_two;
			} else if (type == 4) {
				sql = PurchaseDaoSqls.sql_findType_three;
			} else if (type == 5) {
				sql = PurchaseDaoSqls.sql_findType_four;
			} 
			try {
				ps = con.prepareStatement(sql);
				if (type == 2 || type == 3 || type == 4) {
					ps.setString(1, str1);
				} else {
					ps.setString(1, str1);
					ps.setString(2, str2);
				}
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
				v.add(rs.getInt(2));
				v.add(rs.getInt(3));
				v.add(rs.getDouble(4));
				v.add("" + rs.getString(5));
				v.add("" + rs.getString(6));
				v.add(rs.getDate(7));
				v.add("" + rs.getString(8));
				data.add(v);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

}
