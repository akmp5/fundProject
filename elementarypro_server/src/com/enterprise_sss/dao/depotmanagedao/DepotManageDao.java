package com.enterprise_sss.dao.depotmanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.enterprise_sss.dao.sql.DepotManageSql;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.view.panel.depotmanage.TransDepotBasePanel;

public class DepotManageDao {
	private ConnectionDB db=new ConnectionDB();     //创建ConnectionDB对象,并实例化
	private Connection con;    //从链接池获得链接
	private PreparedStatement ps=null;          //创建PreparedStatement对象
	private ResultSet rs=null;               //创建ResultSet对象
	private Vector rowData= new Vector();
	private Vector row;
	private String string;
	private TransDepotBasePanel panel;
	
	public DepotManageDao(){
	
	}

	public DepotManageDao(Vector rowData){
		this.rowData = rowData;
	}
	
	public DepotManageDao(String string){
		this.string = string;
	}

	public DepotManageDao(TransDepotBasePanel panel) {
		this.panel = panel;
		this.rowData.add(panel.tf1.getText());
		this.rowData.add(panel.tf2.getText());
		this.rowData.add(panel.tf3.getText());
		try {
			this.rowData.add(new java.sql.Date(panel.tf4.getSelectedDate().getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}
		this.rowData.add(panel.tf5.getText());
		this.rowData.add(panel.tf6.getText());
	}

	/**
	 * 查询全部
	 * @return
	 */
	public Vector selectAll(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(DepotManageSql.sql_depotQueryAll);
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
	 * 按仓库编号查询
	 * @return
	 */
	public Vector selectClientId(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(DepotManageSql.sql_DepotIdQuery);
			ps.setInt(1, Integer.parseInt(string));
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
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(new JFrame(),"输入的格式不正确!");
		}
		finally{
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
			ps = con.prepareStatement(DepotManageSql.sql_CommIdQuery);
			ps.setInt(1, Integer.parseInt(string));
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
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(new JFrame(),"输入的格式不正确!");
		}finally{
			db.closeDB(con, ps, rs);
		}	
		return rowData;
	}
	
	
	/**
	 * 查询调拨单
	 */
	public Vector selectTrans(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(DepotManageSql.sql_TransQuery);
			rs = ps.executeQuery();

			while(rs.next()){
				row= new Vector();
				for(int i=1 ;i<8 ;i++){
					row.add(rs.getObject(i));	
				}
				rowData.add(row);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}catch(NumberFormatException e){
			MethodUtil.LogOper(e);
			JOptionPane.showMessageDialog(new JFrame(),"没有记录!");
		}finally{
			db.closeDB(con, ps, rs);
		}
		
		return rowData;
	
	}

	/**
	 * 查询调货申请单
	 */
	public Vector selectApply(){
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(DepotManageSql.sql_TransApply);
			rs = ps.executeQuery();

			while(rs.next()){
				row= new Vector();
				for(int i=1 ;i<9 ;i++){
					row.add(rs.getObject(i));	
				}
				rowData.add(row);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}catch(NumberFormatException e){
			MethodUtil.LogOper(e);
			JOptionPane.showMessageDialog(new JFrame(),"没有记录!");
		}finally{
			db.closeDB(con, ps, rs);
		}
		
		return rowData;
	
	}
	
	/**
	 * 更改调货申请单的回复状态
	 */
	public boolean applyUpdate(){
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(DepotManageSql.sql_ApplyUpdate);		
			ps.setObject(1, string);		
			ps.executeUpdate();
			con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(new JFrame(), "更新回复状态失败！");				
				try {
					con.rollback();
					return false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					MethodUtil.LogOper(e);
				}
				
			}finally{
				db.closeDB(con, ps, rs);
			}return true;	
	}
	
	/**
	 * 往调货单里加记录
	 *
	 */
	public boolean InsertTransBill() {
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(DepotManageSql.sql_TransDepotInsert);
			for(int i = 0;i<rowData.size();i++)
			{	
				ps.setObject(i+1, rowData.get(i));
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
				MethodUtil.LogOper(e);
			}
			
		}finally{
			db.closeDB(con, ps, rs);
		}	
		return true;		
				
	}
	
	/**
	 * 调货后修改库存商品的数量
	 * @return
	 */
	public boolean UpdateTransBill() {
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(DepotManageSql.sql_StockUpdate1);
			
			ps.setInt(1, Integer.parseInt((String) rowData.get(2)));
			ps.setInt(2, Integer.parseInt((String) rowData.get(1)));			
			ps.executeUpdate();
			try{
				ps = con.prepareStatement(DepotManageSql.sql_StockUpdate2);
				ps.setInt(1, Integer.parseInt((String) rowData.get(2)));
				ps.setInt(2, Integer.parseInt((String) rowData.get(1)));
				ps.setInt(3, Integer.parseInt((String) rowData.get(0)));
				ps.executeUpdate();

				con.commit();
				
			}catch(SQLException e){
				MethodUtil.LogOper(e);
				JOptionPane.showMessageDialog(new JFrame(), "更新分库存失败！");
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "更新总库存失败！");
			
			try {
				con.rollback();
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				MethodUtil.LogOper(e);
			}
			
		}finally{
			db.closeDB(con, ps, rs);
		}	
		return true;		
				
	}
	
	
	/**
	 * 查看否有相应的门店，商品，和数量是否充足
	 */
	public boolean checkRowData(){
		int amount;
		try {	System.out.println(rowData.get(0)+" "+ rowData.get(1)+"  "+rowData.get(2) +" "+rowData.get(3) +" "+ rowData.get(4)+" "+rowData.get(5));		
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(DepotManageSql.sql_StorIDQuery);
			ps.setInt(1, Integer.parseInt((String) rowData.get(0)));//System.out.println(Integer.parseInt((String) rowData.get(0)));
			rs = ps.executeQuery();
			rs.next();
			amount = rs.getInt(1);
			try{
				ps = con.prepareStatement(DepotManageSql.sql_DepotCommQuery);
				ps.setInt(1, Integer.parseInt((String) rowData.get(1)));//System.out.println(Integer.parseInt((String) rowData.get(0)));
				rs = ps.executeQuery();
				rs.next();
				amount = rs.getInt(1);
				if(Integer.parseInt((String) rowData.get(2))>amount){
					JOptionPane.showMessageDialog(new JFrame(), "调拨的商品数量超过库存！");
					return false;
				}else
					return true;
			}catch(SQLException e1){
				MethodUtil.LogOper(e1);
				JOptionPane.showMessageDialog(new JFrame(), "总库不存在该商品！");		
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
			JOptionPane.showMessageDialog(new JFrame(), "不存在该门店！");		
			return false;
			
		}finally{
			db.closeDB(con, ps, rs);
		}
		
	}
	
	
	/**
	 * 查看库存表里的是否有相应的商品和库存编号
	 */
	public boolean checkStock(){
		int amount;
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(DepotManageSql.sql_CheckStock);
			ps.setInt(1, Integer.parseInt((String) rowData.get(1)));
			ps.setInt(2, Integer.parseInt((String) rowData.get(0)));
			
			rs = ps.executeQuery();
			rs.next();
			amount = rs.getInt(1);
			return true;
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
			return false;
			
		}finally{
			db.closeDB(con, ps, rs);
		}
		
	}
	
	
	/**
	 * 调拨使库存表发生变化，为库存表添加新及录
	 * @return
	 */
	public boolean InsertStock(){
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(DepotManageSql.sql_StockInsert);
			ps.setInt(1, Integer.parseInt((String) rowData.get(1)));
			ps.setInt(2, Integer.parseInt((String) rowData.get(2)));
			ps.setInt(3, Integer.parseInt((String) rowData.get(1)));
			ps.setInt(4, Integer.parseInt((String) rowData.get(2)));
			ps.setInt(5, Integer.parseInt((String) rowData.get(0)));
			
			ps.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				MethodUtil.LogOper(e);
			}
			return false;
			
		}finally{
			db.closeDB(con, ps, rs);
		}
		
		return true;
		
	}
	
	
}
