package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.ThreadDao;
import com.enterprise_sss.dao.inter.ThreadInter;

public class ThreadDaoFactory {
	
	public static ThreadInter getThreadDao(){
		return new ThreadDao();
	}
	
}
