package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.PurchaseDao;
import com.enterprise_sss.dao.inter.PurchaseInter;
import com.enterprise_sss.vo.PurchaseVO;

public class PurchaseDaoFactory {

	public static PurchaseInter getPurchaseDao(PurchaseVO dvo){
		return new PurchaseDao(dvo);
	}
	
	public static PurchaseInter getPurchaseDao(){
		return new PurchaseDao();
	}
	
}
