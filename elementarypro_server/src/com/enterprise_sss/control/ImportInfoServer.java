package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.ImportInfoDaoFactory;

public class ImportInfoServer {

	public void add(String table_title, Vector vo){
		ImportInfoDaoFactory.getImportInfoDao().add(table_title, vo);
	}
	
	public void close(){
		ImportInfoDaoFactory.getImportInfoDao().close();
	}
	
}
