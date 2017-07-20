package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.PurchaseInItemInter;
import com.enterprise_sss.dao.sql.PurchaseInItemSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.PurchaseInItemVO;

public class PurchaseInItemDao implements PurchaseInItemInter{

	private PurchaseInItemVO vo;
	
	private ConnectionDB db = new ConnectionDB(); // 创建ConnectionDB对象,并实例化

	private Connection con = db.getConnection(); // 从链接池获得链接

	private PreparedStatement ps = null; // 创建PreparedStatement对象

	private ResultSet rs = null; // 创建ResultSet对象
	
	public PurchaseInItemDao(){
		
	}
	
	public PurchaseInItemDao(PurchaseInItemVO vo){
		this.vo = vo;
	}

	public void close() {
		db.closeDB(con, ps, rs);
	}

	public void PurchaseInItem_del() {
		try {
			ps = con.prepareStatement(PurchaseInItemSqls.sql_del);
			ps.setInt(1, vo.getPii_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("进货单明细ID：" + vo.getPii_id(), DataUtil.name, "删除进货单明细信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void PurchaseInItem_del(int id) {
		try {
			ps = con.prepareStatement(PurchaseInItemSqls.sql_delType);
			ps.setInt(1, id);
			ps.executeQuery();
			LogOperator.writeMessageLog("进货单明细ID：" + id, DataUtil.name, "删除进货单明细信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		
	}

	public PurchaseInItemVO PurchaseInItem_find() {
		// TODO 自动生成方法存根
		return null;
	}

	public Vector PurchaseInItem_findAll(int id) {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(PurchaseInItemSqls.sql_findType);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector v =new Vector();
				v.add(rs.getInt(1));
				v.add(rs.getInt(2));
				v.add(rs.getInt(3));
				v.add(rs.getInt(4));
				v.add(rs.getDouble(5));
				data.add(v);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	public int PurchaseInItem_findMaxID() {
		int id = -1;
		try {
			ps = con.prepareStatement(PurchaseInItemSqls.sql_findMaxID);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return id;
	}

	public Vector PurchaseInItem_findType(int type, String str) {
		// TODO 自动生成方法存根
		return null;
	}

	public void PurchaseInItem_modify() {
		try {
			ps = con.prepareStatement(PurchaseInItemSqls.sql_modify);
			ps.setInt(1, vo.getPib_id());
			ps.setInt(2, vo.getComm_id());
			ps.setInt(3, vo.getPii_amount());
			ps.setDouble(4, vo.getPurc_price());
			ps.setInt(5, vo.getPii_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("进货单明细ID：" + vo.getPii_id(), DataUtil.name, "修改进货单明细信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void PurchaseInItem_add() {
		if (PurchaseInItem_findID(vo.getPii_id())) {
			PurchaseInItem_modify();
		} else {
			try {
				ps = con.prepareStatement(PurchaseInItemSqls.sql_add);
				ps.setInt(1, vo.getPii_id());
				ps.setInt(2, vo.getPib_id());
				ps.setInt(3, vo.getComm_id());
				ps.setInt(4, vo.getPii_amount());
				ps.setDouble(5, vo.getPurc_price());
				ps.executeQuery();
				LogOperator.writeMessageLog("进货单明细ID：" + vo.getPii_id(), DataUtil.name, "添加进货单明细信息");
			} catch (SQLException e) {
				MethodUtil.LogOper(e);
			}
		}
	}

	private boolean PurchaseInItem_findID(int id){
		boolean b = false;
		try {
			ps = con.prepareStatement(PurchaseInItemSqls.sql_find);
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
	
}
