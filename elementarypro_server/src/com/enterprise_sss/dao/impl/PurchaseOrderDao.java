package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.PurchaseOrderInter;
import com.enterprise_sss.dao.sql.PurchaseOrderDaoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.PurchaseOrderVO;

public class PurchaseOrderDao implements PurchaseOrderInter {

	private PurchaseOrderVO vo;

	private ConnectionDB db = new ConnectionDB(); // 创建ConnectionDB对象,并实例化

	private Connection con = db.getConnection(); // 从链接池获得链接

	private PreparedStatement ps = null; // 创建PreparedStatement对象

	private ResultSet rs = null; // 创建ResultSet对象

	public PurchaseOrderDao() {

	}

	public PurchaseOrderDao(PurchaseOrderVO vo) {
		this.vo = vo;
	}

	public boolean PurchaseOrder_add() {
		if (!PurchaseOrder_findId(vo.getPo_id())) {
			try {
				ps = con.prepareStatement(PurchaseOrderDaoSqls.sql_add);
				ps.setInt(1, vo.getPo_id());
				ps.setInt(2, vo.getSupp_id());
				ps.setDate(3, vo.getPo_date());
				ps.setDate(4, vo.getPo_begin_date());
				ps.setDate(5, vo.getPo_end_date());
				ps.setInt(6, vo.getOper_id());
				ps.setString(7, "" + vo.getCbill());
				ps.executeQuery();
				LogOperator.writeMessageLog("采购订单ID：" + vo.getPo_id(), DataUtil.name, "添加采购订单信息");
			} catch (SQLException e) {
				MethodUtil.LogOper(e);
			}
			return true;
		} else {
			return false;
		}
	}

	public void PurchaseOrder_del() {
		try {
			ps = con.prepareStatement(PurchaseOrderDaoSqls.sql_del);
			ps.setInt(1, vo.getPo_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("采购订单ID：" + vo.getPo_id(), DataUtil.name, "删除采购订单信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public PurchaseOrderVO PurchaseOrder_find() {

		return null;
	}

	private boolean PurchaseOrder_findId(int id) {
		boolean b = false;
		try {
			ps = con.prepareStatement(PurchaseOrderDaoSqls.sql_find);
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

	public Vector PurchaseOrder_findAll() {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(PurchaseOrderDaoSqls.sql_findAll);
			rs = ps.executeQuery();
			data = dataParse(rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	public int PurchaseOrder_findMaxID() {

		return 0;
	}

	public Vector PurchaseOrder_findType(int type, String str) {

		return null;
	}

	public void PurchaseOrder_modify() {
		try {
			ps = con.prepareStatement(PurchaseOrderDaoSqls.sql_modify);
			ps.setInt(1, vo.getSupp_id());
			ps.setDate(2, vo.getPo_date());
			ps.setDate(3, vo.getPo_begin_date());
			ps.setDate(4, vo.getPo_end_date());
			ps.setInt(5, vo.getOper_id());
			ps.setString(6, "" + vo.getCbill());
			ps.setInt(7, vo.getPo_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("采购订单ID：" + vo.getPo_id(), DataUtil.name, "修改采购订单信息");
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
				v.add(rs.getDate(4));
				v.add(rs.getDate(5));
				v.add(rs.getInt(6));
				v.add("" + rs.getString(7));
				data.add(v);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

}
