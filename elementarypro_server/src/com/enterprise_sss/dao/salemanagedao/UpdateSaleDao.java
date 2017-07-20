package com.enterprise_sss.dao.salemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.enterprise_sss.dao.sql.SaleManageSql;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.MethodUtil;

public class UpdateSaleDao {
	private ConnectionDB db=new ConnectionDB();     //����ConnectionDB����,��ʵ����
	private Connection con;    					//�����ӳػ������
	private PreparedStatement ps=null;          //����PreparedStatement����
	private ResultSet rs=null;               //����ResultSet����
	private Vector updateRow= new Vector();
	private Vector updateKey= new Vector();
	
	public UpdateSaleDao( ){
	
	}

	public UpdateSaleDao(Vector updateRow,Vector updateKey){
		this.updateRow = updateRow;
		this.updateKey = updateKey;
		
	}

	
	/**
	 * ���и�������
	 * @return
	 */
	public void update(){
		try {			
			con=db.getConnection();
			for(int j = 0;j<updateRow.size();j++)
			{				
					ps = con.prepareStatement(SaleManageSql.sql_saleUpdate);
					ps.setObject(1, updateRow.get(j));
					ps.setObject(2, updateKey.get(j));
					ps.executeUpdate();
					con.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MethodUtil.LogOper(e);
		}finally{
			db.closeDB(con, ps, rs);
		}	

	}
	
}
