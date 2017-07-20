package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.CheckoutInter;
import com.enterprise_sss.dao.sql.CheckoutSql;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.GatherVO;

public class CheckoutDao implements CheckoutInter {
	private ConnectionDB db=new ConnectionDB();     //����ConnectionDB����,��ʵ����
	private Connection con=db.getConnection();    //�����ӳػ������
	private PreparedStatement ps=null;          //����PreparedStatement����
	private ResultSet rs=null;               //����ResultSet����
	
	
	/**
	 * ��ѯ��Ʒ�嵥�����л�����
	 * @return
	 */
	public Vector findCommodityBill(String sql){
		Vector comms=new Vector();
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			int i=0;
			while(rs.next()){
				comms.add(rs.getInt("comm_id"));
			}
			db.closeDB(con, ps, rs);
		} catch (SQLException e) {
			LogOperator.writeErrorLog("��Ʒ�嵥��(commodity_bill)��ѯ�쳣��");
		}
		return comms;
	}
	/**
	 * ��ѯ��������ܱ�SQLָ����¼
	 * @return
	 */
	public Vector findJxcGather(String sql){
		Vector dps=new Vector();
		Vector row=null;
		
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				row=new Vector();
				row.add(rs.getInt("jg_id"));
				row.add(rs.getString("jg_date"));
				row.add(rs.getInt("comm_id"));
				row.add(rs.getInt("last_checkout_amount"));
				row.add(rs.getDouble("last_checkout_money"));
				row.add(rs.getInt("out_amount"));
				row.add(rs.getDouble("out_money"));
				row.add(rs.getInt("int_amount"));
				row.add(rs.getDouble("int_money"));
				row.add(rs.getInt("spill_loss_amount"));
				row.add(rs.getDouble("spill_loss_money"));
				row.add(rs.getInt("now_checkout_amount"));
				row.add(rs.getDouble("now_checkout_money"));
				row.add(rs.getString("jg_desc"));
				dps.add(row);
			}
			
			db.closeDB(con, ps, rs);
		} catch (SQLException e) {
			LogOperator.writeErrorLog("��������ܱ�(jxc_gather)��ѯ�쳣��");
		}
		return dps;
	}
	
	/**
	 * ��ѯ����
	 */
	public String findGatherDate(String sql){
		String date=null;
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				date=rs.getString(1);
			}
			db.closeDB(con, ps, rs);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		
		return date;
	}
	
	/**
	 * ��������Ʒ��������л���
	 * @param comms ������Ʒ��ż���
	 * @param minDate ��С����
	 * @param maxDate �������
	 * @return
	 */
	public Vector findCheckout(Vector comms,String minDate,String maxDate){
		Vector dps=new Vector();
		Vector row=null;
		int comm_id;//��Ʒ���
		java.sql.Date minD,maxD;//��С����(�ϴν������ڵ���һ����),�������
		try {
			for(int i=0;i<comms.size();i++){
				row=new Vector();
				comm_id=Integer.parseInt(comms.get(i).toString());
				minD=new java.sql.Date(DateFormat.getDateInstance().parse(minDate).getTime()+1);
				maxD=new java.sql.Date(DateFormat.getDateInstance().parse(maxDate).getTime());	
				row.add(0,comm_id);
				row.add(1,minD);
				row.add(2,maxD);	
				/*
				 * ��ѯ��������ܱ�ָ����Ʒ�����һ�ν��˵���Ʒ�����ͽ����Ϊ�ϴν��������ͽ��
				 */
				ps=con.prepareStatement(CheckoutSql.LAST_GATHER);
				ps.setInt(1, comm_id);
				rs=ps.executeQuery();
				//���û�������Ʒ��Ӧ�����ݣ�����Ϊ0
				if(!rs.next()){
					row.add(3,0);
					row.add(4,0.0);
				}else{
					row.add(3,rs.getInt("now_checkout_amount"));
					row.add(4,rs.getDouble("now_checkout_money"));
				}
				rs.close();
				ps.close();
				/*
				 * ָ��ʱ�����(���ڽ��˽׶�)ָ����Ʒ�����������ͽ��
				 */
				ps=con.prepareStatement(CheckoutSql.SALE_GATHER);
				ps.setInt(1, comm_id);
				ps.setDate(2, minD);
				ps.setDate(3, maxD);
				rs=ps.executeQuery();
				if(!rs.next()){
					row.add(5,0);
					row.add(6,0.0);
				}else{
					row.add(5,rs.getInt(1));
					row.add(6,rs.getDouble(2));
				}
				rs.close();
				ps.close();
				/*
				 * ָ��ʱ�����(���ڽ��˽׶�)ָ����Ʒ�Ľ��������ͽ��
				 */
				ps=con.prepareStatement(CheckoutSql.PIN_GATHER);
				ps.setInt(1, comm_id);
				ps.setDate(2, minD);
				ps.setDate(3, maxD);
				rs=ps.executeQuery();
				if(!rs.next()){
					row.add(7,0);
					row.add(8,0.0);
				}else{
					row.add(7,rs.getInt(1));
					row.add(8,rs.getDouble(2));
				}
				rs.close();
				ps.close();
				
				/*
				 * ָ����Ʒ��ʵʱ��������ͽ��
				 */
				ps=con.prepareStatement(CheckoutSql.STOCK_GATHER);
				ps.setInt(1, comm_id);
				rs=ps.executeQuery();
				if(!rs.next()){
					row.add(9,0);
					row.add(10,0.0);
				}else{
					row.add(9,rs.getInt(1));
					row.add(10,rs.getDouble(2));
				}
				rs.close();
				ps.close();
				
				/*
				 * ָ��ʱ�����(���ڽ��˽׶�)ָ����Ʒ�����������ͽ��
				 */
				ps=con.prepareStatement(CheckoutSql.LOSS_GATHER);
				ps.setInt(1, comm_id);
				ps.setDate(2, minD);
				ps.setDate(3, maxD);
				rs=ps.executeQuery();
				if(!rs.next()){
					row.add(11,0);
					row.add(12,0.0);
				}else{
					row.add(11,rs.getInt(1));
					row.add(12, rs.getDouble(2));
				}
				rs.close();
				ps.close();
				/*
				 * ������Ʒ���������ͽ��(=���ڽ���-����+����+����)
				 */
				row.add(13, Integer.parseInt(row.get(3).toString())
						-Integer.parseInt(row.get(5).toString())
						+Integer.parseInt(row.get(7).toString())
						+Integer.parseInt(row.get(11).toString()));//3-5+7+11
				row.add(14, Double.parseDouble(row.get(4).toString())
						-Double.parseDouble(row.get(6).toString())
						+Double.parseDouble(row.get(8).toString())
						+Double.parseDouble(row.get(12).toString()));//4-6+8+12
				System.out.println("fff"+row.get(13));
				System.out.println("rrr"+row.get(14));
				dps.add(row);
			}
			db.closeDB(con, ps, rs);
		} catch (Exception e) {
			MethodUtil.LogOper(e);
		}
		return dps;
	}
	
	/**
	 * ���������¼
	 * @return ������ȫ��ִ�гɹ�����true,���򷵻�false
	 */
	public boolean insertJxcGhter(Vector comms,GatherVO vo){
		boolean flag=false;
		try {
			ps=con.prepareStatement(CheckoutSql.INSERT_JG);
			int jg_id,comm_id;//���
			java.sql.Date jg_date=new java.sql.Date(
					DateFormat.getDateInstance().parse(vo.getJg_date()).getTime());//����
			//�������� SQL ������ӵ��� Statement ����ĵ�ǰ�����б��С�
			for(int i=0;i<comms.size();i++){
				jg_id=vo.getJg_id()+i;
				System.out.println(jg_id);
				comm_id=Integer.parseInt(comms.get(i).toString());
				ps.setInt(1,jg_id);
				ps.setDate(2,jg_date);
				ps.setInt(3,comm_id);
				ps.addBatch();
			}
			// ��һ�������ύ�����ݿ���ִ�У����ȫ������ִ�гɹ����򷵻ظ��¼�����ɵ����顣
			int[] exe=ps.executeBatch();
//			System.out.println(exe);
//			for(int i:exe){
//				if(i==Statement.EXECUTE_FAILED){
//					flag=false;
//					break;
//				}else flag=true;
//			}
			if (exe.length != 0) {
//				System.out.println("*******");
				flag = true;
			}
			db.closeDB(con, ps);
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		} catch (ParseException e) {
			LogOperator.writeErrorLog("��������ܱ�(jxc_gather)���ݴ��ݸ����쳣��");
			MethodUtil.LogOper(e);
		}
		return flag;
	}
	
	
	/**
	 *  ���½�������ܱ�SQLָ����¼
	 * @return
	 */
	public boolean updateJxcGather(Vector lastGather,Vector data){
		boolean flag=false;
		try {
			ps=con.prepareStatement(CheckoutSql.UPDATE_JG);
				for(int i=0;i<lastGather.size();i++){
					ps.setInt(1, Integer.parseInt(((Vector)data.get(i)).get(3).toString()));
					ps.setDouble(2, Double.parseDouble(((Vector)data.get(i)).get(4).toString()));
					ps.setInt(3, Integer.parseInt(((Vector)data.get(i)).get(5).toString()));
					ps.setDouble(4, Double.parseDouble(((Vector)data.get(i)).get(6).toString()));
					ps.setInt(5, Integer.parseInt(((Vector)data.get(i)).get(7).toString()));
					ps.setDouble(6, Double.parseDouble(((Vector)data.get(i)).get(8).toString()));
					ps.setInt(7, Integer.parseInt(((Vector)data.get(i)).get(11).toString()));
					ps.setDouble(8, Double.parseDouble(((Vector)data.get(i)).get(12).toString()));
					ps.setInt(9, Integer.parseInt(((Vector)data.get(i)).get(13).toString()));
					ps.setDouble(10, Double.parseDouble(((Vector)data.get(i)).get(14).toString()));
					ps.setInt(11, Integer.parseInt(((Vector)lastGather.get(i)).get(0).toString()));
					ps.addBatch();
				}
				int[] exe=ps.executeBatch();
				for(int i:exe){
					if(i==Statement.EXECUTE_FAILED){
						flag=false;
						break;
					}else flag=true;
				}
			db.closeDB(con, ps);
		} catch (SQLException e) {
			e.printStackTrace();
			LogOperator.writeErrorLog("��������ܱ�(jxc_gather)�����쳣��");
			flag=false;
		}
		return flag;
	}
}
