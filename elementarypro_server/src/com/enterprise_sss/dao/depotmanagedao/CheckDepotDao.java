package com.enterprise_sss.dao.depotmanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.enterprise_sss.dao.sql.DepotManageSql;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.MethodUtil;

public class CheckDepotDao {
	
	String string;	
	
	public CheckDepotDao(String string) {
		this.string = string;
	}
	
	
	private ConnectionDB db=new ConnectionDB();     //创建ConnectionDB对象,并实例化
	private Connection con;    //从链接池获得链接
	private PreparedStatement ps=null;          //创建PreparedStatement对象
	private ResultSet rs=null;               //创建ResultSet对象

	
	/**
	 * 检查库存中是否有该商品
	 * @return
	 */
	public boolean checkComm() {
		int amount;
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(DepotManageSql.sql_depotCommQuery);
			ps.setInt(1, Integer.parseInt(string));
			rs = ps.executeQuery();
			rs.next();
			amount = rs.getInt(1);
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), "库存中没有该商品！");
			MethodUtil.LogOper(e);
		}finally{
			db.closeDB(con, ps, rs);
		}	
		
		return false;
	}


	/**
	 * 查找到相应商品的数量
	 * @return
	 */
	public int getCommAmount() {
		int amount = 0;
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(DepotManageSql.sql_depotCommQuery);
			ps.setInt(1, Integer.parseInt(string));
			rs = ps.executeQuery();
			rs.next();
			amount = rs.getInt(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), "查询失败！");
			MethodUtil.LogOper(e);return amount;
		}finally{
			db.closeDB(con, ps, rs);
		}
		return amount;
	}


	
}
