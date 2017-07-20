package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.PurchaseOrderItemInter;
import com.enterprise_sss.dao.sql.PurchaseOrderItemDaoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.PurchaseOrderItemVO;

public class PurchaseOrderItemDao implements PurchaseOrderItemInter {

	private PurchaseOrderItemVO vo;
	
	private ConnectionDB db = new ConnectionDB(); // ����ConnectionDB����,��ʵ����

	private Connection con = db.getConnection(); // �����ӳػ������

	private PreparedStatement ps = null; // ����PreparedStatement����

	private ResultSet rs = null; // ����ResultSet����
	
	public PurchaseOrderItemDao(){
		
	}
	
	public PurchaseOrderItemDao(PurchaseOrderItemVO vo){
		this.vo = vo;
	}
	
	public void PurchaseOrderItem_add() {
		if (PurchaseOrderItem_findID(vo.getPoi_id())) {
			PurchaseOrderItem_modify();
		} else {
			try {
				ps = con.prepareStatement(PurchaseOrderItemDaoSqls.sql_add);
				ps.setInt(1, vo.getPoi_id());
				ps.setInt(2, vo.getPo_id());
				ps.setInt(3, vo.getComm_id());
				ps.setInt(4, vo.getPoi_amount());
				ps.setDouble(5, vo.getPurc_price());
				ps.executeQuery();
				LogOperator.writeMessageLog("�ɹ�������ϸID��" + vo.getPoi_id(), DataUtil.name, "��Ӳɹ�������ϸ��Ϣ");
			} catch (SQLException e) {
				MethodUtil.LogOper(e);
			}
		}
	}

	private boolean PurchaseOrderItem_findID(int id){
		boolean b = false;
		try {
			ps = con.prepareStatement(PurchaseOrderItemDaoSqls.sql_find);
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
	
	public void PurchaseOrderItem_del() {
		try {
			ps = con.prepareStatement(PurchaseOrderItemDaoSqls.sql_del);
			ps.setInt(1, vo.getPoi_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("�ɹ�������ϸID��" + vo.getPoi_id(), DataUtil.name, "ɾ���ɹ�������ϸ��Ϣ");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}
	
	public void PurchaseOrderItem_del(int id){
		try {
			ps = con.prepareStatement(PurchaseOrderItemDaoSqls.sql_delType);
			ps.setInt(1, id);
			ps.executeQuery();
			LogOperator.writeMessageLog("�ɹ�������ϸID��" + id, DataUtil.name, "ɾ���ɹ�������ϸ��Ϣ");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public PurchaseOrderItemVO PurchaseOrderItem_find() {
		return null;
	}

	public Vector PurchaseOrderItem_findAll(int id) {
		Vector data = new Vector();
		try {
			ps = con.prepareStatement(PurchaseOrderItemDaoSqls.sql_findType);
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

	public int PurchaseOrderItem_findMaxID() {
		int id = -1;
		try {
			ps = con.prepareStatement(PurchaseOrderItemDaoSqls.sql_findMaxID);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return id;
	}

	public Vector PurchaseOrderItem_findType(int type, String str) {
		// TODO �Զ����ɷ������
		return null;
	}

	public void PurchaseOrderItem_modify() {
		try {
			ps = con.prepareStatement(PurchaseOrderItemDaoSqls.sql_modify);
			ps.setInt(1, vo.getPo_id());
			ps.setInt(2, vo.getComm_id());
			ps.setInt(3, vo.getPoi_amount());
			ps.setDouble(4, vo.getPurc_price());
			ps.setInt(5, vo.getPoi_id());
			ps.executeQuery();
			LogOperator.writeMessageLog("�ɹ�������ϸID��" + vo.getPoi_id(), DataUtil.name, "�޸Ĳɹ�������ϸ��Ϣ");
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
	}

	public void close() {
		db.closeDB(con, ps, rs);
	}

}
