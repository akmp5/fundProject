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
 * ���ݿ����ӹ�����
 * @author Wang ming 2009-09-22
 * @version 1.0
 */
public class DBConnectionManager {

	private static DBConnectionManager dm=null;

	private Vector drivers = new Vector();

	private Hashtable pools = new Hashtable();

	/**
	 * ���캯��
	 */
	private DBConnectionManager() {
		init();  //��ʼ������
	}

	/**
	 * ��ȡ�������
	 * @return
	 */
	static synchronized public DBConnectionManager getDM() {
		if (dm == null) {
			dm = new DBConnectionManager();
		}
		return dm;
	}

	/**
	 * ��ʼ���ݷ���
	 *
	 */
	private void init() {
		Properties pro = new Properties();
		try {
			//���������ļ�
			pro.load(new FileInputStream(new File("config/db.properties")));
		} catch (Exception e) {
			MethodUtil.LogOper(e);
		}
		loadDrivers(pro);
		createPools(pro);
	}

	/**
	 * �ͷ����ݿ�����
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
	 * �����ӳ��з������ݿ�����
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
	 * ��������
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
	 * �����ӳ��л�ȡ���ݿ�����
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
	 * �������������ļ��������ӳ�
	 * @param props
	 */
	public void createPools(Properties props) {
		Enumeration propNames = props.propertyNames();
		while (propNames.hasMoreElements()) {
			// ����ļ��е�������������Ϣ
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
						password, max);    //�������ӳ�
				pools.put(poolName, pool);
			}
		}
	}

	/**
	 * ���ݿ����ӳ���
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
