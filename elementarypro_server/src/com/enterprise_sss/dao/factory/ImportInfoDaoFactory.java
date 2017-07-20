package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.ImportInfoDao;
import com.enterprise_sss.dao.inter.ImportInfoInter;

public class ImportInfoDaoFactory {

	public static ImportInfoInter getImportInfoDao(){
		return new ImportInfoDao();
	}
	
}
