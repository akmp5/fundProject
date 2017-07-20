package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.PurchaseOrderDao;
import com.enterprise_sss.dao.inter.PurchaseOrderInter;
import com.enterprise_sss.vo.PurchaseOrderVO;

public class PurchaseOrderDaoFactory {

	public static PurchaseOrderInter getPurchaseOrderDao(PurchaseOrderVO pvo){
		return new PurchaseOrderDao(pvo);
	}
	
	public static PurchaseOrderInter getPurchaseOrderDao(){
		return new PurchaseOrderDao();
	}
	
}
