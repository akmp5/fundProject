package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.ReceivableInter;
import com.enterprise_sss.dao.sql.AccountSql;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.AccountReceivableVO;
import com.enterprise_sss.vo.DepositReceivableVO;

public class ReceivableDao implements ReceivableInter {
	private ConnectionDB db=new ConnectionDB();     //����ConnectionDB����,��ʵ����
	private Connection con=db.getConnection();    //�����ӳػ������
	private PreparedStatement ps=null;          //����PreparedStatement����
	private ResultSet rs=null;               //����ResultSet����

	/**
	 * ��ѯӦ�տ��SQLָ����¼
	 * @return
	 */
//	@Override
	public Vector findAccountReceivable(String sql) {
		Vector data=new Vector();
		Vector row=null;
		
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			int i=0;
			while(rs.next()){
				row=new Vector();
				row.add(rs.getInt("ar_id"));
				row.add(rs.getInt("ar_inv"));
				row.add(rs.getString("ar_inv_date"));
				row.add(rs.getInt("so_id"));
				row.add(rs.getInt("comm_id"));
				row.add(rs.getInt("clie_id"));
				row.add(rs.getInt("ar_comm_amount"));
				row.add(rs.getDouble("ar_sale_price"));
				row.add(rs.getDouble("ar_money"));
				row.add(rs.getString("ar_date"));
				row.add(rs.getString("ar_desc"));
				row.add(rs.getString("state"));
				row.add(rs.getInt("dr_id"));
				data.add(row);
			}
			db.closeDB(con, ps, rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}
	/**
	 * ��ѯ�ͻ��嵥��SQLָ����¼
	 * @return
	 */
//	@Override
	public Vector findClientBill(int clie_id) {
		Vector data=new Vector();
		Vector row=null;
		
		try {
			ps=con.prepareStatement(AccountSql.SELE_CB);
			ps.setInt(1, clie_id);
			rs=ps.executeQuery();
			int i=0;
			while(rs.next()){
				row=new Vector();
				row.add(rs.getInt("clie_id"));
				row.add(rs.getString("clie_spell_code"));
				row.add(rs.getString("clie_shortname"));
				row.add(rs.getString("clie_name"));
				row.add(rs.getString("clie_linkman"));
				row.add(rs.getString("clie_address"));
				row.add(rs.getString("clie_postcode"));
				row.add(rs.getString("clie_tel"));
				row.add(rs.getString("clie_fax"));
				row.add(rs.getString("clie_bank"));
				row.add(rs.getString("clie_bank_account"));
				row.add(rs.getString("clie_sort"));
				row.add(rs.getInt("oper_id"));
				row.add(rs.getString("clie_CreditLimt"));
				data.add(row);
			}
			db.closeDB(con, ps, rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	/**
	 * ��ѯԤ�տ��SQLָ����¼
	 * @return
	 */
//	@Override
	public Vector findDepositReceivable(String sql) {
		Vector data=new Vector();
		Vector row=null;
		
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			int i=0;
			while(rs.next()){
				row=new Vector();
				row.add(rs.getInt("dr_id"));
				row.add(rs.getInt("dr_inv"));
				row.add(rs.getString("dr_inv_date"));
				row.add(rs.getInt("clie_id"));
				row.add(rs.getDouble("cr_money"));
				row.add(rs.getString("dr_date"));
				data.add(row);
			}
			db.closeDB(con, ps, rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	/**
	 * ��ѯ���۵���SQLָ�����۵���ż�¼
	 * @return
	 */
//	@Override
	public Vector findSaleBill(int sb_id) {
		Vector data=new Vector();
		Vector row=null;
		try {
			ps=con.prepareStatement(AccountSql.SELE_SALE);
			ps.setInt(1, sb_id);
			rs=ps.executeQuery();
			while(rs.next()){
				row=new Vector();
				row.add(rs.getInt("sb_id"));
				row.add(rs.getInt("clie_id"));
				row.add(rs.getString("sale_date"));
				row.add(rs.getInt("oper_id"));
				row.add(rs.getString("cbill"));
				row.add(rs.getString("keeper"));
				row.add(rs.getInt("so_id"));
				data.add(row);
			}
			db.closeDB(con, ps, rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return data;
	}

	
	/**
	 * ����Ӧ�տ��SQLָ����¼
	 * @return
	 */
	public boolean updateAccountReceivable(AccountReceivableVO vo, String sql) {
		boolean flag=false;
		int exe=0;
		try {
			ps=con.prepareStatement(sql);
			if(sql.startsWith("delete")|sql.startsWith("insert")){
				ps.setInt(1, vo.getAr_id());
				
			}else if(sql.startsWith("update")){
				ps.setInt(13, vo.getAr_id());
				ps.setInt(1, vo.getAr_inv());
				//����JTable��õ����ַ���ֵ-->util.Date-->sql.Dateת�����ٴ��ݵ����ݿ���в���
				ps.setDate(2, new java.sql.Date(DateFormat.getDateInstance().parse(vo.getAr_inv_date()).getTime()));
				ps.setInt(3, vo.getSb_id());
				ps.setInt(4, vo.getComm_id());
				ps.setInt(5, vo.getClie_id());
				ps.setInt(6, vo.getAr_comm_amount());
				ps.setDouble(7, vo.getAr_sale_price());
				ps.setDouble(8, vo.getAr_money());
				ps.setDate(9,new java.sql.Date(DateFormat.getDateInstance().parse(vo.getAr_date()).getTime()));
				ps.setString(10, vo.getAr_desc());
				ps.setString(11, vo.getState());
				ps.setInt(12, vo.getDr_id());
			}
			
			exe=ps.executeUpdate();
			System.out.println(exe);
			if(exe>0){
				flag=true;
				LogOperator.writeMessageLog("Ӧ�տ�ID��" + vo.getAr_id(), DataUtil.name, "�޸�Ӧ�տ���Ϣ");
			}
			else
				flag=false;
			db.closeDB(con, ps);
		} catch (SQLException e) {
			LogOperator.writeErrorLog("Ӧ�տ��(AccountReceivable)�����쳣��");
			flag=false;
		} catch (ParseException e) {
			LogOperator.writeErrorLog("Ӧ�տ��(AccountReceivable)���ݴ��ݸ����쳣��");
			flag=false;
		}
		return flag;
	}
	
	/**
	 * ����Ԥ�տ��SQLָ����¼
	 * @return
	 */
	public boolean updateDepositReceivable(DepositReceivableVO vo, String sql) {
		boolean flag=false;
		int exe=0;
		try {
			ps=con.prepareStatement(sql);
			if(sql.startsWith("delete")|sql.startsWith("insert")){
				ps.setInt(1, vo.getDr_id());
			}else if(sql.startsWith("update")){
				ps.setInt(6, vo.getDr_id());
				ps.setInt(1, vo.getDr_inv());
				//����JTable��õ����ַ���ֵ-->util.Date-->sql.Dateת�����ٴ��ݵ����ݿ���в���
				ps.setDate(2, new java.sql.Date(DateFormat.getDateInstance().parse(vo.getDr_inv_date()).getTime()));
//				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//				Date d=sf.parse(vo.getDp_inv_date());
//				ps.setDate(2, new java.sql.Date(d.getTime()));
				ps.setInt(3, vo.getClie_id());
				ps.setDouble(4, vo.getCr_money());
				ps.setDate(5,new java.sql.Date(DateFormat.getDateInstance().parse(vo.getDr_date()).getTime()));
			}
			exe=ps.executeUpdate();
			if(exe>0){
				flag=true;
				LogOperator.writeMessageLog("Ԥ�տ�ID��" + vo.getDr_id(), DataUtil.name, "�޸�Ԥ�տ���Ϣ");
			}
			else
				flag=false;
			db.closeDB(con, ps);
		} catch (SQLException e) {
			LogOperator.writeErrorLog("Ԥ�տ��(DepositReceivable)�����쳣��");
			flag=false;
		} catch (ParseException e) {
			LogOperator.writeErrorLog("Ԥ�տ��(DepositReceivable)���ݴ��ݸ����쳣��");
			flag=false;
		}
		return flag;
	}

}
