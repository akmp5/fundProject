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

public class UpLowAlarmDao {
	private ConnectionDB db=new ConnectionDB();     //创建ConnectionDB对象,并实例化
	private Connection con;    //从链接池获得链接
	private PreparedStatement ps=null;          //创建PreparedStatement对象
	private ResultSet rs=null;               //创建ResultSet对象
	private Vector rowData = new Vector();	
	private Vector row;
	
	public Vector alarmSelect() {
		try {			
			con=db.getConnection();
			ps = con.prepareStatement(DepotManageSql.sql_AlarmQuery);
			rs = ps.executeQuery();

			while(rs.next()){
				row= new Vector();
				for(int i=1 ;i<7 ;i++){
					row.add(rs.getInt(i));	
				}
				int num = rs.getInt(3);
				int num1 = rs.getInt(4);
				int num2 = rs.getInt(5);
				if(num<=num2){
					row.add("低于下限报警！");
				}
				if(num>=num1){
					row.add("高于上限报警！");
				}
				else
					row.add("库存正常");
				
				rowData.add(row);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}catch(NumberFormatException e){
			MethodUtil.LogOper(e);
			JOptionPane.showMessageDialog(new JFrame(),"库存或上下限表中没有记录,不能生成报警!");
		}finally{
			db.closeDB(con, ps, rs);
		}
		
		return rowData;
		
	}
	
}
