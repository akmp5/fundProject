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
	private ConnectionDB db=new ConnectionDB();     //����ConnectionDB����,��ʵ����
	private Connection con;    //�����ӳػ������
	private PreparedStatement ps=null;          //����PreparedStatement����
	private ResultSet rs=null;               //����ResultSet����
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
	 * ��ѯȫ��
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
	 * ���ֿ��Ų�ѯ
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
			JOptionPane.showMessageDialog(new JFrame(),"����ĸ�ʽ����ȷ!");
		}
		finally{
			db.closeDB(con, ps, rs);
		}	
		return rowData;
	}
	

	
	/**
	 * ����Ʒ��Ų�ѯ
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
			JOptionPane.showMessageDialog(new JFrame(),"����ĸ�ʽ����ȷ!");
		}finally{
			db.closeDB(con, ps, rs);
		}	
		return rowData;
	}
	
	
	/**
	 * ��ѯ������
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
			JOptionPane.showMessageDialog(new JFrame(),"û�м�¼!");
		}finally{
			db.closeDB(con, ps, rs);
		}
		
		return rowData;
	
	}

	/**
	 * ��ѯ�������뵥
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
			JOptionPane.showMessageDialog(new JFrame(),"û�м�¼!");
		}finally{
			db.closeDB(con, ps, rs);
		}
		
		return rowData;
	
	}
	
	/**
	 * ���ĵ������뵥�Ļظ�״̬
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
				JOptionPane.showMessageDialog(new JFrame(), "���»ظ�״̬ʧ�ܣ�");				
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
	 * ����������Ӽ�¼
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
			JOptionPane.showMessageDialog(new JFrame(), "�����ʽ����");
			
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
	 * �������޸Ŀ����Ʒ������
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
				JOptionPane.showMessageDialog(new JFrame(), "���·ֿ��ʧ�ܣ�");
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "�����ܿ��ʧ�ܣ�");
			
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
	 * �鿴������Ӧ���ŵ꣬��Ʒ���������Ƿ����
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
					JOptionPane.showMessageDialog(new JFrame(), "��������Ʒ����������棡");
					return false;
				}else
					return true;
			}catch(SQLException e1){
				MethodUtil.LogOper(e1);
				JOptionPane.showMessageDialog(new JFrame(), "�ܿⲻ���ڸ���Ʒ��");		
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
			JOptionPane.showMessageDialog(new JFrame(), "�����ڸ��ŵ꣡");		
			return false;
			
		}finally{
			db.closeDB(con, ps, rs);
		}
		
	}
	
	
	/**
	 * �鿴��������Ƿ�����Ӧ����Ʒ�Ϳ����
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
	 * ����ʹ�������仯��Ϊ��������¼�¼
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
