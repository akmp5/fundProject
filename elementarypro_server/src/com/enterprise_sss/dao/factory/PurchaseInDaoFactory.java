package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.PurchaseInDao;
import com.enterprise_sss.dao.inter.PurchaseInInter;
import com.enterprise_sss.vo.PurchaseInVO;

public class PurchaseInDaoFactory {

	public static PurchaseInInter getPurchaseInDao(PurchaseInVO pvo){
		return new PurchaseInDao(pvo);
	}
	
	public static PurchaseInInter getPurchaseInDao(){
		return new PurchaseInDao();
	}
	
}
