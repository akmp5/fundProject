package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.PurchaseInItemDao;
import com.enterprise_sss.dao.inter.PurchaseInItemInter;
import com.enterprise_sss.vo.PurchaseInItemVO;

public class PurchaseInItemDaoFactory {

	public static PurchaseInItemInter getPurchaseInItemDao(PurchaseInItemVO pvo){
		return new PurchaseInItemDao(pvo);
	}
	
	public static PurchaseInItemInter getPurchaseInItemDao(){
		return new PurchaseInItemDao();
	}
	
}
