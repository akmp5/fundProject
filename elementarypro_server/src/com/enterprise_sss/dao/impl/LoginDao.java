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
 * 登录操作类
 * @author Administrator
 *
 */
public class LoginDao implements LoginInter {
	
	private ConnectionDB db=new ConnectionDB();     //创建ConnectionDB对象,并实例化
	private Connection con=db.getConnection();    //从链接池获得链接
	private PreparedStatement ps=null;          //创建PreparedStatement对象
	private ResultSet rs=null;               //创建ResultSet对象

	/**
	 * 判断用户是否存在
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
