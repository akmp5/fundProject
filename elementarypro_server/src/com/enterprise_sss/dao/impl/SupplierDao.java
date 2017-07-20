package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.SupplierInter;
import com.enterprise_sss.dao.sql.SupplierDaoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.SupplierVO;

public class SupplierDao implements SupplierInter {
	
	private SupplierVO vo;
	
	private ConnectionDB db = new ConnectionDB(); // 创建ConnectionDB对象,并实例化

	private Connection con = db.getConnection(); // 从链接池获得链接

	private PreparedStatement ps = null; // 创建PreparedStatement对象

	private ResultSet rs = null; // 创建ResultSet对象
	
	public SupplierDao(){
		
	}
	
	public SupplierDao(SupplierVO vo){
		this.vo = vo;
	}

	public void Supplier_add() {
		try {
			ps = con.prepareStatement(SupplierDaoSqls.sql_add);
			ps.setInt(1, vo.getSupp_id());
			ps.setString(2, "" + vo.getSupp_spell_code());
			ps.setString(3, "" + vo.getSupp_shortname());
			ps.setString(4, "" + vo.getSupp_name());
			ps.setString(5, "" + vo.getSupp_address());
			ps.setString(6, "" + vo.getSupp_postcode());
			ps.setString(7, "" + vo.getSupp_sort());
			ps.setString(8, "" + vo.getSupp_tel());
			ps.setString(9, "" + vo.getSupp_fax());
			ps.setString(10, "" + vo.getSupp_bank());
			ps.setString(11, "" + vo.getSupp_iban());
			ps.setString(12, "" + vo.getSupp_storage_address());
			ps.setString(13, "" + vo.getSupp_storage_tel());
			ps.setInt(14, vo.getOper_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("供应商ID：" + vo.getSupp_id(), DataUtil.name, "添加供应商信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void Supplier_del() {
		try {
			ps = con.prepareStatement(SupplierDaoSqls.sql_del);
			ps.setInt(1, vo.getSupp_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("供应商ID：" + vo.getSupp_id(), DataUtil.name, "删除供应商信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public SupplierVO Supplier_find() {
		SupplierVO v = null;
		try {
			ps = con.prepareStatement(SupplierDaoSqls.sql_find);
			ps.setInt(1, vo.getSupp_id());
			rs = ps.executeQuery();
			if (rs.next()) {
				v = new SupplierVO();
				v.setSupp_id(rs.getInt(1));
				v.setSupp_spell_code(rs.getString(2));
				v.setSupp_shortname(rs.getString(3));
				v.setSupp_name(rs.getString(4));
				v.setSupp_address(rs.getString(5));
				v.setSupp_postcode(rs.getString(6));
				v.setSupp_sort(rs.getString(7));
				v.setSupp_tel(rs.getString(8));
				v.setSupp_fax(rs.getString(9));
				v.setSupp_bank(rs.getString(10));
				v.setSupp_iban(rs.getString(11));
				v.setSupp_storage_address(rs.getString(12));
				v.setSupp_storage_tel(rs.getString(13));
				v.setOper_id(rs.getInt(14));
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return v;
	}

	public int Supplier_findMaxID() {
		int id = -1;
		try {
			ps = con.prepareStatement(SupplierDaoSqls.sql_findMaxID);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return id;
	}

	public void Supplier_modify() {
		try {
			ps = con.prepareStatement(SupplierDaoSqls.sql_modify);
			ps.setString(1, "" + vo.getSupp_spell_code());
			ps.setString(2, "" + vo.getSupp_shortname());
			ps.setString(3, "" + vo.getSupp_name());
			ps.setString(4, "" + vo.getSupp_address());
			ps.setString(5, "" + vo.getSupp_postcode());
			ps.setString(6, "" + vo.getSupp_sort());
			ps.setString(7, "" + vo.getSupp_tel());
			ps.setString(8, "" + vo.getSupp_fax());
			ps.setString(9, "" + vo.getSupp_bank());
			ps.setString(10, "" + vo.getSupp_iban());
			ps.setString(11, "" + vo.getSupp_storage_address());
			ps.setString(12, "" + vo.getSupp_storage_tel());
			ps.setInt(13, vo.getOper_id());
			ps.setInt(14, vo.getSupp_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("供应商ID：" + vo.getSupp_id(), DataUtil.name, "修改供应商信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void close() {
		db.closeDB(con, ps, rs);
	}

	public Vector Supplier_findAll() {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(SupplierDaoSqls.sql_findAll);
			rs = ps.executeQuery();
			data = dataParse(rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	public Vector Supplier_findType(int type, String str) {
		Vector data = new Vector();
		String sql = null;
		if (type == 1) {
			return Supplier_findAll();
		} else {
			if (type == 2) {
				sql = SupplierDaoSqls.sql_findType_one;
			} else if (type == 3) {
				sql = SupplierDaoSqls.sql_findType_two;
			} else if (type == 4) {
				sql = SupplierDaoSqls.sql_findType_three;
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
				v.add("" + rs.getString(11));
				v.add("" + rs.getString(12));
				v.add("" + rs.getString(13));
				v.add(rs.getInt(14));
				data.add(v);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}
}
