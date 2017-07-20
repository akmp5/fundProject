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

public class ProfitLossDao {
	private Vector textData = new Vector();	
	private ConnectionDB db=new ConnectionDB();     //创建ConnectionDB对象,并实例化
	private Connection con;    //从链接池获得链接
	private PreparedStatement ps=null;          //创建PreparedStatement对象
	private ResultSet rs=null;               //创建ResultSet对象
		
	
	public ProfitLossDao(){
		
	}
	
	public ProfitLossDao(Vector textData) {
			this.textData = textData;
		}
			
		/**
		 * 查找损溢单中的所有记录
		 * @return
		 */
		public Vector proLossSelect() {
			
			try {			
				con=db.getConnection();
				ps = con.prepareStatement(DepotManageSql.sql_profitLossQuery);
				rs = ps.executeQuery();
				if(rs.next()){
					Vector row = new Vector();
					for(int i = 1;i<9;i++){
						row.add(rs.getObject(i));
					}
					textData.add(row);				
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(), "没有记录！");
				MethodUtil.LogOper(e);
			}finally{
				db.closeDB(con, ps, rs);
			}	
			
			return textData;
		}
	
	
		/**
		 * 往损溢单里添加数据
		 * @return
		 */
		public boolean profitLossInsert() {
			try {			
				con=db.getConnection();
				ps = con.prepareStatement(DepotManageSql.sql_proLossInsert);
				for(int i = 0;i<7;i++){
					ps.setObject(i+1, textData.get(i));
				}	
//				ps.setObject(1, Math.abs(Integer.parseInt((String) textData.get(0))));
				ps.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(), "输入格式有误");
				e.printStackTrace();
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
