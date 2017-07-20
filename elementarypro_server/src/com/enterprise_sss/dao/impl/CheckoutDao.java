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
	private ConnectionDB db=new ConnectionDB();     //创建ConnectionDB对象,并实例化
	private Connection con=db.getConnection();    //从链接池获得链接
	private PreparedStatement ps=null;          //创建PreparedStatement对象
	private ResultSet rs=null;               //创建ResultSet对象
	
	
	/**
	 * 查询商品清单表所有货物编号
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
			LogOperator.writeErrorLog("商品清单表(commodity_bill)查询异常！");
		}
		return comms;
	}
	/**
	 * 查询进销存汇总表SQL指定记录
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
			LogOperator.writeErrorLog("进销存汇总表(jxc_gather)查询异常！");
		}
		return dps;
	}
	
	/**
	 * 查询日期
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
	 * 对所有商品进销存进行汇总
	 * @param comms 所有商品编号集合
	 * @param minDate 最小日期
	 * @param maxDate 最大日期
	 * @return
	 */
	public Vector findCheckout(Vector comms,String minDate,String maxDate){
		Vector dps=new Vector();
		Vector row=null;
		int comm_id;//商品编号
		java.sql.Date minD,maxD;//最小日期(上次结账日期的下一毫秒),最大日期
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
				 * 查询进销存汇总表指定商品的最后一次结账的商品数量和金额作为上次结帐数量和金额
				 */
				ps=con.prepareStatement(CheckoutSql.LAST_GATHER);
				ps.setInt(1, comm_id);
				rs=ps.executeQuery();
				//如果没有这个商品对应的数据，补充为0
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
				 * 指定时间段内(本期结账阶段)指定商品的销售数量和金额
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
				 * 指定时间段内(本期结账阶段)指定商品的进货数量和金额
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
				 * 指定商品的实时库存数量和金额
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
				 * 指定时间段内(本期结账阶段)指定商品的损溢数量和金额
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
				 * 本期商品结帐数量和金额(=上期结余-已售+进货+损溢)
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
	 * 批量插入记录
	 * @return 当批量全部执行成功返回true,否则返回false
	 */
	public boolean insertJxcGhter(Vector comms,GatherVO vo){
		boolean flag=false;
		try {
			ps=con.prepareStatement(CheckoutSql.INSERT_JG);
			int jg_id,comm_id;//编号
			java.sql.Date jg_date=new java.sql.Date(
					DateFormat.getDateInstance().parse(vo.getJg_date()).getTime());//日期
			//将给定的 SQL 命令添加到此 Statement 对象的当前命令列表中。
			for(int i=0;i<comms.size();i++){
				jg_id=vo.getJg_id()+i;
				System.out.println(jg_id);
				comm_id=Integer.parseInt(comms.get(i).toString());
				ps.setInt(1,jg_id);
				ps.setDate(2,jg_date);
				ps.setInt(3,comm_id);
				ps.addBatch();
			}
			// 将一批命令提交给数据库来执行，如果全部命令执行成功，则返回更新计数组成的数组。
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
			LogOperator.writeErrorLog("进销存汇总表(jxc_gather)数据传递更新异常！");
			MethodUtil.LogOper(e);
		}
		return flag;
	}
	
	
	/**
	 *  更新进销存汇总表SQL指定记录
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
			LogOperator.writeErrorLog("进销存汇总表(jxc_gather)更新异常！");
			flag=false;
		}
		return flag;
	}
}
