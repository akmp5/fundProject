package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.CommodityDao;
import com.enterprise_sss.dao.inter.CommodityInter;
import com.enterprise_sss.vo.CommodityVO;

public class CommodityDaoFactory {

	public static CommodityInter getCommodityDao(CommodityVO cvo){
		return new CommodityDao(cvo);
	}
	
	public static CommodityInter getCommodityDao(){
		return new CommodityDao();
	}
	
}
