package com.enterprise_sss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.enterprise_sss.dao.inter.LoginInter;
import com.enterprise_sss.dao.sql.LoginDaoSqls;
import com.enterprise_sss.datasource.ConnectionDB;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.UserVO;

/**
 * ��¼������
 * @author Administrator
 *
 */
public class LoginDao implements LoginInter {
	
	private ConnectionDB db=new ConnectionDB();     //����ConnectionDB����,��ʵ����
	private Connection con=db.getConnection();    //�����ӳػ������
	private PreparedStatement ps=null;          //����PreparedStatement����
	private ResultSet rs=null;               //����ResultSet����

	/**
	 * �ж��û��Ƿ����
	 */
	public boolean exist_user(UserVO uvo) {
		boolean b = false;
		try {
			ps = con.prepareStatement(LoginDaoSqls.sql_login);
			ps.setString(1, uvo.getUser());
			ps.setString(2, uvo.getPassword());
			ps.setInt(3, uvo.getLevel());
			rs = ps.executeQuery();
			if (rs.next()) {
				b = true;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return b;
	}

	public void close() {
		db.closeDB(con, ps, rs);
	}

	public boolean modifyPw(UserVO uvo) {
		boolean b = false;
		try {
			ps = con.prepareStatement(LoginDaoSqls.sql_modifyPw);
			ps.setString(1, uvo.getPassword());
			ps.setString(2, uvo.getUser());
			ps.setInt(3, uvo.getLevel());
			rs = ps.executeQuery();
			if (rs.next()) {
				b = true;
			}
		} catch (SQLException e) {
			MethodUtil.LogOper(e);
		}
		return b;
	}

}
