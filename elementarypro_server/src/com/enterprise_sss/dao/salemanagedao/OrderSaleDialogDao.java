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

public class OrderSaleDialogDao {
	private ConnectionDB db=new ConnectionDB();     //����ConnectionDB����,��ʵ����
	private Connection con;    					//�����ӳػ������
	private PreparedStatement ps=null;          //����PreparedStatement����
	private ResultSet rs=null;               //����ResultSet����
	private Vector textData= new Vector();
	private Vector rowData= new Vector();
	
	public OrderSaleDialogDao( ){
	
	}
	
	public OrderSaleDialogDao(Vector vector){
		this.textData = vector;
		this.rowData = vector;
		
	}
	public OrderSaleDialogDao(Vector textData,Vector rowData){
		this.textData = textData;
		this.rowData = rowData;
		
	}

	
	/**
	 * ��Jtextfield��������
	 * @return
	 */
	public boolean updateTextData(){
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SaleManageSql.sql_OrderSaleInsert);
			for(int i = 0;i<textData.size();i++)
			{	
				ps.setObject(i+1, textData.get(i));
			}
			ps.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "�����ʽ����");
			MethodUtil.LogOper(e);
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
	 * ��table��������
	 *
	 */
	public boolean updateRowData(){
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
		ps = con.prepareStatement(SaleManageSql.sql_OrderSaleItemInsert);
		for(int i = 0;i<rowData.size();i++)
		{	
			ps.setObject(i+1, textData.get(i));
		}
		ps.executeUpdate();
		con.commit();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "�����ʽ����");
			MethodUtil.LogOper(e);
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
