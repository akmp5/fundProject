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
	
	
	private ConnectionDB db=new ConnectionDB();     //����ConnectionDB����,��ʵ����
	private Connection con;    //�����ӳػ������
	private PreparedStatement ps=null;          //����PreparedStatement����
	private ResultSet rs=null;               //����ResultSet����

	
	/**
	 * ��������Ƿ��и���Ʒ
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
			JOptionPane.showMessageDialog(new JFrame(), "�����û�и���Ʒ��");
			MethodUtil.LogOper(e);
		}finally{
			db.closeDB(con, ps, rs);
		}	
		
		return false;
	}


	/**
	 * ���ҵ���Ӧ��Ʒ������
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
			JOptionPane.showMessageDialog(new JFrame(), "��ѯʧ�ܣ�");
			MethodUtil.LogOper(e);return amount;
		}finally{
			db.closeDB(con, ps, rs);
		}
		return amount;
	}


	
}
