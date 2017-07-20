package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.DepotDao;
import com.enterprise_sss.dao.inter.DepotInter;
import com.enterprise_sss.vo.DepotVO;

public class DepotDaoFactory {
	
	public static DepotInter getCommodityDao(DepotVO dvo){
		return new DepotDao(dvo);
	}
	
	public static DepotInter getCommodityDao(){
		return new DepotDao();
	}
	
}
