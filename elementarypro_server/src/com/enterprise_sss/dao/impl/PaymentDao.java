package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Vector;

import com.enterprise_sss.dao.inter.PaymentInter;
import com.enterprise_sss.dao.sql.AccountSql;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.vo.AccountPayableVO;
import com.enterprise_sss.vo.DepositPaymentVO;

/**
 * 应付款操作类
 * @author yiguo
 *
 */
public class PaymentDao implements PaymentInter {
	private ConnectionDB db=new ConnectionDB();     //创建ConnectionDB对象,并实例化
	private Connection con=db.getConnection();    //从链接池获得链接
	private PreparedStatement ps=null;          //创建PreparedStatement对象
	private ResultSet rs=null;               //创建ResultSet对象

	/**
	 * 查询预付款表SQL指定记录
	 * @return
	 */
	public Vector findDepositPayment(String sql){
		Vector dps=new Vector();
		Vector row=null;
		
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			int i=0;
			while(rs.next()){
				row=new Vector();
				row.add(rs.getInt("dp_id"));
				row.add(rs.getInt("dp_inv"));
				row.add(rs.getString("dp_inv_date"));
				row.add(rs.getInt("supp_id"));
				row.add(rs.getDouble("dp_money"));
				row.add(rs.getString("dp_date"));
				dps.add(row);
			}
			db.closeDB(con, ps, rs);
		} catch (SQLException e) {
			LogOperator.writeErrorLog("预付款表(DepositPayment)查询异常！");
		}
		return dps;
	}
	
	/**
	 *  更新预付款表SQL指定记录
	 * @return
	 */
	public boolean updateDepositPayment(DepositPaymentVO vo,String sql){
		boolean flag=false;
		int exe=0;
		try {
			ps=con.prepareStatement(sql);
			if(sql.startsWith("delete")|sql.startsWith("insert")){
				ps.setInt(1, vo.getDp_id());
				
			}else if(sql.startsWith("update")){
				ps.setInt(6, vo.getDp_id());
				ps.setInt(1, vo.getDp_inv());
				//将从JTable里得到的字符型值-->util.Date-->sql.Date转换后再传递到数据库进行操作
				ps.setDate(2, new java.sql.Date(DateFormat.getDateInstance().parse(vo.getDp_inv_date()).getTime()));
//				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//				Date d=sf.parse(vo.getDp_inv_date());
//				ps.setDate(2, new java.sql.Date(d.getTime()));
				ps.setInt(3, vo.getSupp_id());
				ps.setDouble(4, vo.getDp_money());
				ps.setDate(5,new java.sql.Date(DateFormat.getDateInstance().parse(vo.getDp_date()).getTime()));
			
			}
			exe=ps.executeUpdate();
//			System.out.println(exe);
			if(exe>0){
				flag=true;
				LogOperator.writeMessageLog("预付款ID：" + vo.getDp_id(), DataUtil.name, "修改预付款信息");
			}else
				flag=false;
			db.closeDB(con, ps);
		} catch (SQLException e) {
			LogOperator.writeErrorLog("预付款表(DepositPayment)更新异常！");
			flag=false;
		} catch (ParseException e) {
			LogOperator.writeErrorLog("预付款表(DepositPayment)数据传递更新异常！");
			flag=false;
		}
		return flag;
	}
	
	/**
	 * 查询供货商清单表SQL指定记录
	 * @return
	 */
	public Vector findSuppliersBill(int supp_id){
		Vector sbs=new Vector();
		Vector row=null;
		try {
			ps=con.prepareStatement(AccountSql.SELE_SB);
			ps.setInt(1, supp_id);
			rs=ps.executeQuery();
			while(rs.next()){
				row=new Vector();
				row.add(rs.getInt("supp_id"));
				row.add(rs.getString("supp_spell_code"));
				row.add(rs.getString("supp_shortname"));
				row.add(rs.getString("supp_name"));
				row.add(rs.getString("supp_address"));
				row.add(rs.getString("supp_postcode"));
				row.add(rs.getString("supp_sort"));
				row.add(rs.getString("supp_tel"));
				row.add(rs.getString("supp_fax"));
				row.add(rs.getString("supp_bank"));
				row.add(rs.getString("supp_bank_account"));
				row.add(rs.getString("supp_storage_address"));
				row.add(rs.getString("supp_storage_tel"));
				row.add(rs.getInt("oper_id"));
				sbs.add(row);
				
			}
			db.closeDB(con, ps, rs);
		} catch (SQLException e) {
			LogOperator.writeErrorLog("供货商清单表(SuppliersBill)查询异常！");
		}
		return sbs;
	}
	
	/**
	 *  查询应付款表SQL指定记录
	 * @return
	 */
	public Vector findAccountPayable(String sql){
		Vector aps=new Vector();
		Vector row=null;
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				row=new Vector();
				row.add(rs.getInt("ap_id"));
				row.add(rs.getInt("ap_inv"));
				row.add(rs.getString("ap_inv_date"));
				row.add(rs.getInt("pib_id"));
				row.add(rs.getInt("comm_id"));
				row.add(rs.getInt("supp_id"));
				row.add(rs.getInt("ap_comm_amount"));
				row.add(rs.getDouble("ap_purchase_price"));
				row.add(rs.getDouble("ap_money"));
				row.add(rs.getString("ap_date"));
				row.add(rs.getString("ap_desc"));
				row.add(rs.getString("state"));
				row.add(rs.getInt("dp_id"));
				aps.add(row);
			}
			db.closeDB(con, ps, rs);
		} catch (SQLException e) {
			LogOperator.writeErrorLog("应付款表(SuppliersBill)查询异常！");
		}
		return aps;
	}
	
	/**
	 *  更新应付款表SQL指定记录
	 * @return
	 */
	public boolean updateAccountPayable(AccountPayableVO vo,String sql){
		boolean flag=false;
		int exe=0;
		try {
			ps=con.prepareStatement(sql);
			if(sql.startsWith("delete")|sql.startsWith("insert")){
				ps.setInt(1, vo.getAp_id());
				
			}else if(sql.startsWith("update")){
				ps.setInt(13, vo.getAp_id());
				ps.setInt(1, vo.getAp_inv());
				//将从JTable里得到的字符型值-->util.Date-->sql.Date转换后再传递到数据库进行操作
				ps.setDate(2, new java.sql.Date(DateFormat.getDateInstance().parse(vo.getAp_inv_date()).getTime()));
				ps.setInt(3, vo.getPib_id());
				ps.setInt(4, vo.getComm_id());
				ps.setInt(5, vo.getSupp_id());
				ps.setInt(6, vo.getAp_comm_amount());
				ps.setDouble(7, vo.getAp_purchase_price());
				ps.setDouble(8, vo.getAp_money());
				ps.setDate(9,new java.sql.Date(DateFormat.getDateInstance().parse(vo.getAp_date()).getTime()));
				ps.setString(10, vo.getAp_desc());
				ps.setString(11, vo.getState());
				ps.setInt(12, vo.getDp_id());
			
			}
			
			exe=ps.executeUpdate();
			if(exe>0){
				flag=true;
				LogOperator.writeMessageLog("应付款ID：" + vo.getAp_id(), DataUtil.name, "修改应付款信息");
			}else
				flag=false;
			db.closeDB(con, ps);
		} catch (SQLException e) {
			LogOperator.writeErrorLog("应付款表(AccountPayable)更新异常！");
			flag=false;
		} catch (ParseException e) {
			LogOperator.writeErrorLog("应付款表(AccountPayable)数据传递更新异常！");
			flag=false;
		}
		return flag;
	}

	/**
	 * 查询进货单表SQL指定记录
	 * @return
	 */
	public Vector findPurchaseInBill(int pib_id ) {
		Vector pids=new Vector();
		Vector row=null;
		try {
			ps=con.prepareStatement(AccountSql.SELE_PIB);
			ps.setInt(1, pib_id);
			rs=ps.executeQuery();
			while(rs.next()){
				row=new Vector();
				row.add(rs.getInt("pib_id"));
				row.add(rs.getInt("supp_id"));
				row.add(rs.getString("pib_date"));
				row.add(rs.getInt("oper_id"));
				row.add(rs.getString("cbill"));
				row.add(rs.getString("inspector"));
				row.add(rs.getString("keeper"));
				row.add(rs.getInt("po_id"));
				pids.add(row);
			}
			db.closeDB(con, ps, rs);
		} catch (SQLException e) {
			LogOperator.writeErrorLog("进货单表(SuppliersBill)查询异常！");
		}
		return pids;
	}
}
