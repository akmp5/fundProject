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

public class UpLowDao {
	private Vector textData = new Vector();	
	private ConnectionDB db=new ConnectionDB();     //����ConnectionDB����,��ʵ����
	private Connection con;    //�����ӳػ������
	private PreparedStatement ps=null;          //����PreparedStatement����
	private ResultSet rs=null;               //����ResultSet����
		
	
	public UpLowDao(){
		
	}
	
	public UpLowDao(Vector textData) {
			this.textData = textData;
		}
			
		/**
		 * ���������޵��е����м�¼
		 * @return
		 */
		public Vector upLowSelect() {
			
			try {			
				con=db.getConnection();
				ps = con.prepareStatement(DepotManageSql.sql_UpLowQuery);
				rs = ps.executeQuery();
				while(rs.next()){
					Vector row = new Vector();
					for(int i = 1;i<8;i++){
						row.add(rs.getObject(i));
					}
					textData.add(row);				
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(), "û�м�¼��");
				MethodUtil.LogOper(e);
			}finally{
				db.closeDB(con, ps, rs);
			}	
			
			return textData;
		}
	
	
		/**
		 * �������޵����������
		 * @return
		 */
		public boolean upLowInsert() {
			try {			
				con=db.getConnection();
				ps = con.prepareStatement(DepotManageSql.sql_upLowInsert);
				con.setAutoCommit(false);
//				for(int i = 0;i<6;i++){
//					ps.setObject(i+1, textData.get(i));
//				}	
//System.out.println(textData.get(0)+" "+textData.get(1)+" "+textData.get(2)+" "+textData.get(3)+" "+textData.get(4)+" "+textData.get(5));
				ps.setObject(1, textData.get(0));
				ps.setObject(2, textData.get(1));
				ps.setObject(3, textData.get(2));
				ps.setObject(4, textData.get(3));
				ps.setObject(5, textData.get(4));
				ps.setObject(6, textData.get(5));
				ps.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(), "�����ʽ����");
				MethodUtil.LogOper(e);
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
		 * ���������ޱ��е�������
		 * @return
		 */
		public boolean upLowUpdate() {
			
			try {			
				con=db.getConnection();
				ps = con.prepareStatement(DepotManageSql.sql_upLowUpdate);
				con.setAutoCommit(false);

				ps.setObject(1, textData.get(3));
				ps.setObject(2, textData.get(4));
				ps.setObject(3, textData.get(5));
				ps.setObject(4, textData.get(0));
	
				ps.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(), "����ʧ��!");
				MethodUtil.LogOper(e);
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
		 * ɾ�������ޱ�����Ӧ������
		 * @return
		 */
		public boolean upLowDelete() {
			try {			
				con=db.getConnection();
				ps = con.prepareStatement(DepotManageSql.sql_upLowDelete);
				con.setAutoCommit(false);

				ps.setObject(1, textData.get(0));
	
				ps.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(), "ɾ��ʧ��!");
				MethodUtil.LogOper(e);
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
				
}
