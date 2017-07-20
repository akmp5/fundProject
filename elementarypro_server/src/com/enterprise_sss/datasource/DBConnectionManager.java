package com.enterprise_sss.datasource;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import com.enterprise_sss.util.MethodUtil;

/**
 * 数据库连接管理类
 * @author Wang ming 2009-09-22
 * @version 1.0
 */
public class DBConnectionManager {

	private static DBConnectionManager dm=null;

	private Vector drivers = new Vector();

	private Hashtable pools = new Hashtable();

	/**
	 * 构造函数
	 */
	private DBConnectionManager() {
		init();  //初始化数据
	}

	/**
	 * 获取本类对象
	 * @return
	 */
	static synchronized public DBConnectionManager getDM() {
		if (dm == null) {
			dm = new DBConnectionManager();
		}
		return dm;
	}

	/**
	 * 初始数据方法
	 *
	 */
	private void init() {
		Properties pro = new Properties();
		try {
			//加载属性文件
			pro.load(new FileInputStream(new File("config/db.properties")));
		} catch (Exception e) {
			MethodUtil.LogOper(e);
		}
		loadDrivers(pro);
		createPools(pro);
	}

	/**
	 * 释放数据库连接
	 * @param poolName
	 * @param con
	 */
	public synchronized void release(String poolName,Connection con) {
		DBConnectionPool pool=(DBConnectionPool)pools.get(poolName);
		if(poolName!=null){
			pool.release(con);
		}
	}
	
	/**
	 * 从链接池中放入数据库连接
	 * @param poolName
	 * @param con
	 */
	public synchronized void free(String poolName,Connection con){
		DBConnectionPool pool=(DBConnectionPool)pools.get(poolName);
		if(poolName!=null){
			pool.free(con);
		}
	}
	
	/**
	 * 加载驱动
	 * @param proTemp
	 */
	private void loadDrivers(Properties proTemp) {
		String driver = proTemp.getProperty("oracle.drivers");
		try {
			Class cla = Class.forName(driver);
			DriverManager.registerDriver((Driver) cla.newInstance());
			drivers.addElement(driver);
		} catch (Exception e) {
			MethodUtil.LogOper(e);
		}
	}
	
	/**
	 * 从链接池中获取数据库连接
	 * @param poolName
	 * @return
	 */
	public Connection getConnection(String poolName){
		DBConnectionPool pool=(DBConnectionPool)pools.get(poolName);
		if(pool!=null){
			return pool.getConnection();
		}else{
			return null;
		}
	}

	/**
	 * 根据属性配置文件创建链接池
	 * @param props
	 */
	public void createPools(Properties props) {
		Enumeration propNames = props.propertyNames();
		while (propNames.hasMoreElements()) {
			// 获得文件中的所有属性名信息
			String name = (String) propNames.nextElement();
			if (name.endsWith(".drivers")) {
				String poolName = name.substring(0, name.lastIndexOf("."));
				String url = props.getProperty(poolName + ".url");

				if (url == null) {
					continue;
				}

				String user = props.getProperty(poolName + ".user");
				String password = props.getProperty(poolName + ".password");
				String maxConn = props.getProperty(poolName + ".maxConn", "0");

				int max;
				try {
					max = Integer.valueOf(maxConn).intValue();
				} catch (NumberFormatException e) {
					max = 0;
					MethodUtil.LogOper(e);
				}
				DBConnectionPool pool = new DBConnectionPool(name, url, user,
						password, max);    //创建链接池
				pools.put(poolName, pool);
			}
		}
	}

	/**
	 * 数据库链接池类
	 * @author Wang ming 2009-09-22
	 * @version 1.0
	 */
	private class DBConnectionPool {

		private int checkedOut;

		private Vector freeConnection = new Vector();

		private int maxConn;

		private String name;

		private String password;

		private String URL;

		private String user;

		public DBConnectionPool(String name, String url, String user,
				String password, int maxConn) {
			this.name = name;
			this.URL = url;
			this.user = user;
			this.password = password;
			this.maxConn = maxConn;
		}

		public synchronized Connection getConnection(){
			Connection con=null;
			if(freeConnection.size()>0){
				con=(Connection)freeConnection.firstElement();
			}else if(checkedOut<maxConn){
				con=buildConnection();
			}
			return con;
		}
		
		public synchronized void free(Connection con){
			freeConnection.addElement(con);
			notifyAll();
		}
		
		public synchronized void release(Connection con){
			try {
				con.close();
				checkedOut--;
			} catch (SQLException e) {
				MethodUtil.LogOper(e);
			}
		}
		
		private Connection buildConnection() {
			Connection con = null;

			try {
				if (user == null) {
					con = DriverManager.getConnection(URL);
				} else {
					con = DriverManager.getConnection(URL, user, password);
				}
				checkedOut++;
			} catch (SQLException e) {
				MethodUtil.LogOper(e);
			}
			return con;
		}
	}

}
