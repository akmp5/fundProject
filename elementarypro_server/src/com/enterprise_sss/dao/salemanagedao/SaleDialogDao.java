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
	private ConnectionDB db=new ConnectionDB();     //����ConnectionDB����,��ʵ����
	private Connection con;    					//�����ӳػ������
	private PreparedStatement ps=null;          //����PreparedStatement����
	private ResultSet rs=null;               //����ResultSet����
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
	 * �鿴����Ƿ�����Ӧ����Ʒ���������Ƿ����
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
				JOptionPane.showMessageDialog(new JFrame(), "������Ʒ����������棡");
				return false;
			}else
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
			JOptionPane.showMessageDialog(new JFrame(), "�ڿ�"+rowData.get(3)+"��û�и���Ʒ��");		
			return false;
			
		}finally{
			db.closeDB(con, ps, rs);
		}	
		
	}
	
	/**
	 * ��Jtextfield��������
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
			JOptionPane.showMessageDialog(new JFrame(), "�����ʽ����");
			
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
			ps = con.prepareStatement(SaleManageSql.sql_SaleItemInsert);
			for(int i = 0;i<rowData.size();i++)
			{	
				ps.setObject(i+1, textData.get(i));
			}
			ps.executeUpdate();
			con.commit();
		}catch (SQLException e) {
				// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "�����ʽ����");
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
	 * ���¿����Ʒ������
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
			JOptionPane.showMessageDialog(new JFrame(), "�����ʽ����");
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
