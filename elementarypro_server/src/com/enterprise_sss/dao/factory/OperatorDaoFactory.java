package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.OperatorDao;
import com.enterprise_sss.dao.inter.OperatorInter;
import com.enterprise_sss.vo.OperatorVO;

public class OperatorDaoFactory {
	
	public static OperatorInter getOperatorDao(OperatorVO ovo){
		return new OperatorDao(ovo);
	}
	
	public static OperatorInter getOperatorDao(){
		return new OperatorDao();
	}
	
}
