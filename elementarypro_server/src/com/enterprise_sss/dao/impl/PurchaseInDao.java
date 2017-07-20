package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.PurchaseInInter;
import com.enterprise_sss.dao.sql.PurchaseInDaoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.PurchaseInVO;

public class PurchaseInDao implements PurchaseInInter {

	private PurchaseInVO vo;
	
	private ConnectionDB db = new ConnectionDB(); // 创建ConnectionDB对象,并实例化

	private Connection con = db.getConnection(); // 从链接池获得链接

	private PreparedStatement ps = null; // 创建PreparedStatement对象

	private ResultSet rs = null; // 创建ResultSet对象
	
	public PurchaseInDao(){
		
	}
	
	public PurchaseInDao(PurchaseInVO vo){
		this.vo = vo;
	}
	
	public boolean PurchaseIn_add() {
		if (!PurchaseIn_findId(vo.getPib_id())) {
			try {
				ps = con.prepareStatement(PurchaseInDaoSqls.sql_add);
				ps.setInt(1, vo.getPib_id());
				ps.setInt(2, vo.getSupp_id());
				ps.setDate(3, vo.getPib_date());
				ps.setInt(4, vo.getOper_id());
				ps.setString(5, "" + vo.getCbill());
				ps.setString(6, "" + vo.getInspector());
				ps.setString(7, "" + vo.getKeeper());
				ps.setInt(8, vo.getPo_id());
				ps.executeQuery();
				LogOperator.writeMessageLog("进货单ID：" + vo.getPib_id(), DataUtil.name, "添加进货单信息");
			} catch (SQLException e) {
				MethodUtil.LogOper(e);
			}
			return true;
		} else {
			return false;
		}
	}

	public void PurchaseIn_del() {
		try {
			ps = con.prepareStatement(PurchaseInDaoSqls.sql_del);
			ps.setInt(1, vo.getPib_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("进货单ID：" + vo.getPib_id(), DataUtil.name, "修改进货单信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}
	
	private boolean PurchaseIn_findId(int id) {
		boolean b = false;
		try {
			ps = con.prepareStatement(PurchaseInDaoSqls.sql_find);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				b = true;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return b;
	}

	public PurchaseInVO PurchaseIn_find() {
		
		return null;
	}

	public Vector PurchaseIn_findAll() {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(PurchaseInDaoSqls.sql_findAll);
			rs = ps.executeQuery();
			data = dataParse(rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	public int PurchaseIn_findMaxID() {
		// TODO 自动生成方法存根
		return 0;
	}

	public Vector PurchaseIn_findType(int type, String str) {
		// TODO 自动生成方法存根
		return null;
	}

	public void PurchaseIn_modify() {
		try {
			ps = con.prepareStatement(PurchaseInDaoSqls.sql_modify);
			ps.setInt(1, vo.getSupp_id());
			ps.setDate(2, vo.getPib_date());
			ps.setInt(3, vo.getOper_id());
			ps.setString(4, "" + vo.getCbill());
			ps.setString(5, "" + vo.getInspector());
			ps.setString(6, "" + vo.getKeeper());
			ps.setInt(7, vo.getPo_id());
			ps.setInt(8, vo.getPib_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("进货单ID：" + vo.getPib_id(), DataUtil.name, "修改进货单信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void close() {
		db.closeDB(con, ps, rs);
	}
	
	private Vector dataParse(ResultSet rs) {
		Vector data = new Vector();
		try {
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt(1));
				v.add(rs.getInt(2));
				v.add(rs.getDate(3));
				v.add(rs.getInt(4));
				v.add("" + rs.getString(5));
				v.add("" + rs.getString(6));
				v.add("" + rs.getString(7));
				v.add(rs.getInt(8));
				data.add(v);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

}
