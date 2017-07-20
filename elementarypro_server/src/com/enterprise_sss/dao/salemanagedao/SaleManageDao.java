package com.enterprise_sss.dao.salemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.enterprise_sss.dao.sql.SaleManageSql;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.MethodUtil;

public class SaleManageDao {
	private ConnectionDB db=new ConnectionDB();     //创建ConnectionDB对象,并实例化
	private Connection con;    //从链接池获得链接
	private PreparedStatement ps=null;          //创建PreparedStatement对象
	private ResultSet rs=null;               //创建ResultSet对象
	private Vector rowData= new Vector();
	private Vector row;
	private String string;
	
	
	public SaleManageDao( ){
	
	}

	public SaleManageDao(String string){
		this.string = string;
	}
	/**
	 * 查询全部
	 * @return
	 */
	public Vector selectAll(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(SaleManageSql.sql_saleQueryAll);
			rs = ps.executeQuery();
			while(rs.next()){
				row= new Vector();
				for(int i=1 ;i<15 ;i++){
					row.add(rs.getObject(i));	
				}
				rowData.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}finally{
			db.closeDB(con, ps, rs);
		}	
		return rowData;
	}
	
	/**
	 * 按客户编号查询
	 * @return
	 */
	public Vector selectClientId(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(SaleManageSql.sql_ClientIdQuery);
			ps.setInt(1, Integer.parseInt(string));
			rs = ps.executeQuery();

			while(rs.next()){
				row= new Vector();
				for(int i=1 ;i<15 ;i++){
					row.add(rs.getObject(i));	
				}
				rowData.add(row);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(new JFrame(),"输入的格式不正确!");
		}
		finally{
			db.closeDB(con, ps, rs);
		}	
		return rowData;
	}
	
	/**
	 * 按销售日期查询
	 * @return
	 */
	public Vector selectSaleDate(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(SaleManageSql.sql_SaleDateQuery);
//			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//			Date d = sf.parse(string);
//			ps.setDate(1, new java.sql.Date(d.getTime()));
			ps.setString(1, string);
			
			rs = ps.executeQuery();

			while(rs.next()){
				row= new Vector();
				for(int i=1 ;i<15 ;i++){
					row.add(rs.getObject(i));	
				}
				rowData.add(row);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		} finally{
			db.closeDB(con, ps, rs);
		}	
		return rowData;
	}
	
	/**
	 * 按商品编号查询
	 */
	public Vector selectCommId(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(SaleManageSql.sql_CommIdQuery);
			ps.setInt(1, Integer.parseInt(string));
			rs = ps.executeQuery();

			while(rs.next()){
				row= new Vector();
				for(int i=1 ;i<15 ;i++){
					row.add(rs.getObject(i));	
				}
				rowData.add(row);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(new JFrame(),"输入的格式不正确!");
		}finally{
			db.closeDB(con, ps, rs);
		}	
		return rowData;
	}
	
	/**
	 * 按商品拼音查询
	 */
	public Vector selectCommSpell(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(SaleManageSql.sql_CommSpellQuery);
			ps.setString(1, string+'%');
			rs = ps.executeQuery();

			while(rs.next()){
				row= new Vector();
				for(int i=1 ;i<15 ;i++){
					row.add(rs.getObject(i));	
				}
				rowData.add(row);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}finally{
			db.closeDB(con, ps, rs);
		}	
		return rowData;
	}
	
	/**
	 * 按业务员查询
	 */
	public Vector selectOprId(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(SaleManageSql.sql_OperIdQuery );
			ps.setInt(1, Integer.parseInt(string));
			rs = ps.executeQuery();

			while(rs.next()){
				row= new Vector();
				for(int i=1 ;i<15 ;i++){
					row.add(rs.getObject(i));	
				}
				rowData.add(row);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(new JFrame(),"输入的格式不正确!");
		}finally{
			db.closeDB(con, ps, rs);
		}	
		return rowData;
	}
	
	/**
	 * 查询订货单
	 */
	public Vector selectOrderSale(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(SaleManageSql.sql_OrderSaleQuery );
			rs = ps.executeQuery();

			while(rs.next()){
				row= new Vector();
				for(int i=1 ;i<11 ;i++){
					row.add(rs.getObject(i));	
				}
				rowData.add(row);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}finally{
			db.closeDB(con, ps, rs);
		}	
		return rowData;
	}
	
	/**
	 * 查询退货单
	 */
	public Vector selectSaleBack(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(SaleManageSql.sql_SaleBackQuery);
			rs = ps.executeQuery();

			while(rs.next()){
				row= new Vector();
				for(int i=1 ;i<7 ;i++){
					row.add(rs.getObject(i));	
				}
				rowData.add(row);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}finally{
			db.closeDB(con, ps, rs);
		}	
		return rowData;
	}
}
