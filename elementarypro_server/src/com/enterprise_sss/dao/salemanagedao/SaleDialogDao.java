package com.enterprise_sss.dao.salemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.enterprise_sss.dao.sql.SaleManageSql;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.MethodUtil;

public class SaleDialogDao {
	private ConnectionDB db=new ConnectionDB();     //创建ConnectionDB对象,并实例化
	private Connection con;    					//从链接池获得链接
	private PreparedStatement ps=null;          //创建PreparedStatement对象
	private ResultSet rs=null;               //创建ResultSet对象
	private Vector textData= new Vector();
	private Vector rowData= new Vector();
	
	public SaleDialogDao( ){
	
	}
	
	public SaleDialogDao(Vector vector){
		this.textData = vector;
		this.rowData = vector;
		
	}
	public SaleDialogDao(Vector textData,Vector rowData){
		this.textData = textData;
		this.rowData = rowData;
		
	}
	
	/**
	 * 查看库存是否有相应的商品，和数量是否充足
	 */
	public boolean checkRowData(){
		int amount;
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SaleManageSql.sql_SaleCommQuery);
			ps.setObject(1, Integer.parseInt((String) rowData.get(0)));//System.out.println(Integer.parseInt((String) rowData.get(0)));
			rs = ps.executeQuery();
			rs.next();
			amount = rs.getInt(2);
			if(Integer.parseInt((String) rowData.get(1))>amount){
				JOptionPane.showMessageDialog(new JFrame(), "销售商品数量超过库存！");
				return false;
			}else
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
			JOptionPane.showMessageDialog(new JFrame(), "在库"+rowData.get(3)+"中没有该商品！");		
			return false;
			
		}finally{
			db.closeDB(con, ps, rs);
		}	
		
	}
	
	/**
	 * 按Jtextfield插入数据
	 * @return
	 */
	public boolean updateTextData(){
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SaleManageSql.sql_SaleInsert);
			for(int i = 0;i<textData.size();i++)
			{	
				ps.setObject(i+1, textData.get(i));
			}
			ps.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "输入格式有误！");
			
			try {
				con.rollback();
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				MethodUtil.LogOper(e1);
			}
			
		}finally{
			db.closeDB(con, ps, rs);
		}	
		return true;

	}
	
	
	/**
	 * 按table插入数据
	 *
	 */
	public boolean updateRowData(){
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SaleManageSql.sql_SaleItemInsert);
			for(int i = 0;i<rowData.size();i++)
			{	
				ps.setObject(i+1, textData.get(i));
			}
			ps.executeUpdate();
			con.commit();
		}catch (SQLException e) {
				// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "输入格式有误！");
			try {
				con.rollback();
				return false;
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
				MethodUtil.LogOper(e1);
				}
		}finally{
				db.closeDB(con, ps, rs);
		}return true;
	}
	
	/**
	 * 更新库存商品的数量
	 */
	public boolean updateStock(){
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SaleManageSql.sql_stockInsert);
			ps.setObject(1, textData.get(1));
			ps.setObject(2, textData.get(0));
			ps.executeUpdate();
			con.commit();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "输入格式有误！");
			try {
				con.rollback();
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				MethodUtil.LogOper(e1);
			}
		}finally{
			db.closeDB(con, ps, rs);
		}return true;
	}
	
	
}
