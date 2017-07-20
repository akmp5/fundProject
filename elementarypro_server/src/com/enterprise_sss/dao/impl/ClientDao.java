package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.ClientInter;
import com.enterprise_sss.dao.sql.ClientDaoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.ClientVO;

public class ClientDao implements ClientInter {
	
	private ClientVO vo;

	private ConnectionDB db = new ConnectionDB(); // 创建ConnectionDB对象,并实例化

	private Connection con = db.getConnection(); // 从链接池获得链接

	private PreparedStatement ps = null; // 创建PreparedStatement对象

	private ResultSet rs = null; // 创建ResultSet对象
	
	public ClientDao(){
		
	}
	
	public ClientDao(ClientVO vo){
		this.vo = vo;
	}

	public void Client_add() {
		try {
			ps = con.prepareStatement(ClientDaoSqls.sql_add);
			ps.setInt(1, vo.getClie_id());
			ps.setString(2, "" + vo.getClie_spell_code());
			ps.setString(3, "" + vo.getClie_shortname());
			ps.setString(4, "" + vo.getClie_name());
			ps.setString(5, "" + vo.getClie_linkman());
			ps.setString(6, "" + vo.getClie_address());
			ps.setString(7, "" + vo.getClie_postcode());
			ps.setString(8, "" + vo.getClie_tel());
			ps.setString(9, "" + vo.getClie_fax());
			ps.setString(10, "" + vo.getClie_bank());
			ps.setString(11, "" + vo.getClie_iban());
			ps.setString(12, "" + vo.getClie_sort());
			ps.setInt(13, vo.getOper_id());
			ps.setString(14, "" + vo.getClie_CreditLimt());
			ps.executeQuery();
			LogOperator.writeMessageLog("客户名称：" + vo.getClie_name(), DataUtil.name, "添加客户信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void Client_del() {
		try {
			ps = con.prepareStatement(ClientDaoSqls.sql_del);
			ps.setInt(1, vo.getClie_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("客户ID：" + vo.getClie_id(), DataUtil.name, "删除客户信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public ClientVO Client_find() {
		ClientVO v = null;
		try {
			ps = con.prepareStatement(ClientDaoSqls.sql_find);
			ps.setInt(1, vo.getClie_id());
			rs = ps.executeQuery();
			if (rs.next()) {
				v = new ClientVO();
				v.setClie_id(rs.getInt(1));
				v.setClie_spell_code(rs.getString(2));
				v.setClie_shortname(rs.getString(3));
				v.setClie_name(rs.getString(4));
				v.setClie_linkman(rs.getString(5));
				v.setClie_address(rs.getString(6));
				v.setClie_postcode(rs.getString(7));
				v.setClie_tel(rs.getString(8));
				v.setClie_fax(rs.getString(9));
				v.setClie_bank(rs.getString(10));
				v.setClie_iban(rs.getString(11));
				v.setClie_sort(rs.getString(12));
				v.setOper_id(rs.getInt(13));
				v.setClie_CreditLimt(rs.getString(14));
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return v;
	}

	public int Client_findMaxID() {
		int id = -1;
		try {
			ps = con.prepareStatement(ClientDaoSqls.sql_findMaxID);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return id;
	}

	public void Client_modify() {
		try {
			ps = con.prepareStatement(ClientDaoSqls.sql_modify);
			ps.setString(1, "" + vo.getClie_spell_code());
			ps.setString(2, "" + vo.getClie_shortname());
			ps.setString(3, "" + vo.getClie_name());
			ps.setString(4, "" + vo.getClie_linkman());
			ps.setString(5, "" + vo.getClie_address());
			ps.setString(6, "" + vo.getClie_postcode());
			ps.setString(7, "" + vo.getClie_tel());
			ps.setString(8, "" + vo.getClie_fax());
			ps.setString(9, "" + vo.getClie_bank());
			ps.setString(10, "" + vo.getClie_iban());
			ps.setString(11, "" + vo.getClie_sort());
			ps.setInt(12, vo.getOper_id());
			ps.setString(13, "" + vo.getClie_CreditLimt());
			ps.setInt(14, vo.getClie_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("客户ID：" + vo.getClie_id(), DataUtil.name, "修改客户信息");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}
	
	

	public void close() {
		db.closeDB(con, ps, rs);
	}

	public Vector Client_findAll() {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(ClientDaoSqls.sql_findAll);
			rs = ps.executeQuery();
			data = dataParse(rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	public Vector Client_findType(int type, String str) {
		Vector data = new Vector();
		String sql = null;
		if (type == 1) {
			return Client_findAll();
		} else {
			if (type == 2) {
				sql = ClientDaoSqls.sql_findType_one;
			} else if (type == 3) {
				sql = ClientDaoSqls.sql_findType_two;
			} else if (type == 4) {
				sql = ClientDaoSqls.sql_findType_three;
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
				v.add(rs.getInt(13));
				v.add("" + rs.getString(14));
				data.add(v);
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

}
