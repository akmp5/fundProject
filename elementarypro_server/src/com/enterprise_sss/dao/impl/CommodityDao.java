package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.enterprise_sss.dao.inter.CommodityInter;
import com.enterprise_sss.dao.sql.CommodityDaoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.CommodityVO;

public class CommodityDao implements CommodityInter {

	private CommodityVO vo;

	private ConnectionDB db = new ConnectionDB(); // 创建ConnectionDB对象,并实例化

	private Connection con = db.getConnection(); // 从链接池获得链接

	private PreparedStatement ps = null; // 创建PreparedStatement对象

	private ResultSet rs = null; // 创建ResultSet对象

	public CommodityDao() {

	}

	public CommodityDao(CommodityVO vo) {
		this.vo = vo;
	}

	public void Commodity_add() {
		if (!Commodity_findBar_code()) {
			try {
				ps = con.prepareStatement(CommodityDaoSqls.sql_add);
				ps.setInt(1, vo.getComm_id());
				ps.setString(2, "" + vo.getComm_bar_code());
				ps.setString(3, "" + vo.getComm_name());
				ps.setString(4, "" + vo.getComm_spell_code());
				ps.setString(5, "" + vo.getComm_standard());
				ps.setString(6, "" + vo.getComm_unit());
				ps.setString(7, "" + vo.getComm_producing_area());
				ps.setString(8, "" + vo.getComm_sort());
				ps.setDouble(9, vo.getPurchase_price());
				ps.setDouble(10, vo.getSale_price1());
				ps.setDouble(11, vo.getSale_price2());
				ps.setDouble(12, vo.getLowest_sale_price());
				ps.executeQuery();
				LogOperator.writeMessageLog("商品名称：" + vo.getComm_name(), DataUtil.name, "添加商品信息");
			} catch (SQLException e) {
				MethodUtil.LogOper(e);
			}
		} else {
			JOptionPane.showMessageDialog(null, "对不起，已存在该条形码！");
		}
	}

	public void Commodity_del() {
		try {
			ps = con.prepareStatement(CommodityDaoSqls.sql_del);
			ps.setInt(1, vo.getComm_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("商品ID：" + vo.getComm_id(), DataUtil.name, "删除商品信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public CommodityVO Commodity_find() {
		CommodityVO v = null;
		try {
			ps = con.prepareStatement(CommodityDaoSqls.sql_find);
			ps.setInt(1, vo.getComm_id());
			rs = ps.executeQuery();
			if (rs.next()) {
				v = new CommodityVO();
				v.setComm_id(rs.getInt(1));
				v.setComm_bar_code(rs.getString(2));
				v.setComm_name(rs.getString(3));
				v.setComm_spell_code(rs.getString(4));
				v.setComm_standard(rs.getString(5));
				v.setComm_unit(rs.getString(6));
				v.setComm_producing_area(rs.getString(7));
				v.setComm_sort(rs.getString(8));
				v.setPurchase_price(rs.getDouble(9));
				v.setSale_price1(rs.getDouble(10));
				v.setSale_price2(rs.getDouble(11));
				v.setLowest_sale_price(rs.getDouble(12));
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return v;
	}

	public void Commodity_modify() {
		if (!Commodity_findBar_code()) {
			try {
				ps = con.prepareStatement(CommodityDaoSqls.sql_modify);
				ps.setString(1, "" + vo.getComm_bar_code());
				ps.setString(2, "" + vo.getComm_name());
				ps.setString(3, "" + vo.getComm_spell_code());
				ps.setString(4, "" + vo.getComm_standard());
				ps.setString(5, "" + vo.getComm_unit());
				ps.setString(6, "" + vo.getComm_producing_area());
				ps.setString(7, "" + vo.getComm_sort());
				ps.setDouble(8, vo.getPurchase_price());
				ps.setDouble(9, vo.getSale_price1());
				ps.setDouble(10, vo.getSale_price2());
				ps.setDouble(11, vo.getLowest_sale_price());
				ps.setInt(12, vo.getComm_id());
				ps.executeUpdate();
				LogOperator.writeMessageLog("商品ID：" + vo.getComm_id(), DataUtil.name, "修改商品信息");
			} catch (SQLException e) {
				MethodUtil.LogOper(e);
			}
		} else {
			JOptionPane.showMessageDialog(null, "对不起，已存在该条形码！");
		}
	}

	public int Commodity_findMaxID() {
		int id = -1;
		try {
			ps = con.prepareStatement(CommodityDaoSqls.sql_findMaxID);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return id;
	}

	public boolean Commodity_findBar_code() {
		boolean f = false;
		try {
			ps = con.prepareStatement(CommodityDaoSqls.sql_findBar_code);
			ps.setString(1, "" + vo.getComm_bar_code());
			ps.setInt(2, vo.getComm_id());
			rs = ps.executeQuery();
			if (rs.next()) {
				f = true;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return f;
	}

	public void close() {
		db.closeDB(con, ps, rs);
	}

	public Vector Commodity_findAll() {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(CommodityDaoSqls.sql_findAll);
			rs = ps.executeQuery();
			data = dataParse(rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	public String[] Commodity_findSort(){
		int n = 10;
		try {
			ps = con.prepareStatement("select count(*) from (select comm_sort from commodity_bill group by comm_sort)");
			rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e1) {
			MethodUtil.LogOper(e1);
		}
		String[] items = new String[n];
		int i = 0;
		try {
			ps = con.prepareStatement(CommodityDaoSqls.sql_find_sort);
			rs = ps.executeQuery();
			while (rs.next()) {
				items[i++] = rs.getString(1);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return items;
	}
	
	public Vector Commodity_findType(int type, String str1, String str2) {
		Vector data = new Vector();
		String sql = null;
		if (type == 1) {
			return Commodity_findAll();
		} else {
			if (type == 2) {
				sql = CommodityDaoSqls.sql_findType_one;
			} else if (type == 3) {
				sql = CommodityDaoSqls.sql_findType_two;
			} else if (type == 4) {
				sql = CommodityDaoSqls.sql_findType_three;
			} else if (type == 5) {
				sql = CommodityDaoSqls.sql_findType_five;
			} else if (type == 6) {
				sql = CommodityDaoSqls.sql_findType_four;
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
				v.add("" + rs.getString(2));
				v.add("" + rs.getString(3));
				v.add("" + rs.getString(4));
				v.add("" + rs.getString(5));
				v.add("" + rs.getString(6));
				v.add("" + rs.getString(7));
				v.add("" + rs.getString(8));
				v.add(rs.getDouble(9));
				v.add(rs.getDouble(10));
				v.add(rs.getDouble(11));
				v.add(rs.getDouble(12));
				data.add(v);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}
}
