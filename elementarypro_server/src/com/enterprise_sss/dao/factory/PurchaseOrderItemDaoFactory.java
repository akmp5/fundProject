package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.PurchaseOrderItemDao;
import com.enterprise_sss.dao.inter.PurchaseOrderItemInter;
import com.enterprise_sss.vo.PurchaseOrderItemVO;

public class PurchaseOrderItemDaoFactory {
	
	public static PurchaseOrderItemInter getPurchaseOrderItemDao(PurchaseOrderItemVO pvo){
		return new PurchaseOrderItemDao(pvo);
	}
	
	public static PurchaseOrderItemInter getPurchaseOrderItemDao(){
		return new PurchaseOrderItemDao();
	}
}
