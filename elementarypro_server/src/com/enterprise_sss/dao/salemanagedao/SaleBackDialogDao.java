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

public class SaleBackDialogDao {
	private ConnectionDB db=new ConnectionDB();     //����ConnectionDB����,��ʵ����
	private Connection con;    					//�����ӳػ������
	private PreparedStatement ps=null;          //����PreparedStatement����
	private ResultSet rs=null;               //����ResultSet����
	private Vector textData= new Vector();
	
	
	public SaleBackDialogDao(Vector textData) {
		this.textData = textData;
	}
	
	/**
	 * �����˻���������
	 * @return
	 */

	public boolean updateTextData() {
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SaleManageSql.sql_SaleBackInsert);
			for(int i = 0;i<textData.size();i++)
			{	
				ps.setObject(i+1, textData.get(i));
			}
			ps.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
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
		}	
		return true;
	}

	/**
	 * ������۵���ź���Ʒ����Ƿ����
	 *
	 */
	public boolean checkTextData() {
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SaleManageSql.sql_SaleBackcheck1);
			ps.setInt(1, Integer.parseInt((String) textData.get(0)));	
			ps.executeQuery();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "û���ҵ���Ӧ�����۵���");
			MethodUtil.LogOper(e);
			return false;
			}
			try {
				ps = con.prepareStatement(SaleManageSql.sql_SaleBackcheck2);
				ps.setInt(1, Integer.parseInt((String) textData.get(1)));	
				ps.executeQuery();	
			} catch (SQLException e1) {					
				JOptionPane.showMessageDialog(new JFrame(), "û���ҵ���Ӧ�ĳ��۵���Ʒ��¼��");
				MethodUtil.LogOper(e1);
				return false;
			}			
		finally{
			db.closeDB(con, ps, rs);
		}	
		return true;
		
	}

	/**
	 * ���¿������
	 *
	 */
	public boolean updateStock() {
		try {			
			con=db.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SaleManageSql.sql_StockUpdate);
			
			ps.setInt(1, Integer.parseInt((String) textData.get(2)));
			ps.setInt(2, Integer.parseInt((String) textData.get(1)));		
			ps.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "���¿������ʧ�ܣ�");
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
	
}
