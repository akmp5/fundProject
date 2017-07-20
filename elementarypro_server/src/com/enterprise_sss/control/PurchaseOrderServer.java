package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.PurchaseOrderDaoFactory;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.PurchaseOrderVO;

public class PurchaseOrderServer {
	
	public boolean validateDate(String str){
		if (MethodUtil.validateDate(str) && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean add(PurchaseOrderVO pvo){
		return PurchaseOrderDaoFactory.getPurchaseOrderDao(pvo).PurchaseOrder_add();
	}
	
	public void del(PurchaseOrderVO pvo){
		PurchaseOrderDaoFactory.getPurchaseOrderDao(pvo).PurchaseOrder_del();
	}
	
	public void modify(PurchaseOrderVO pvo){
		PurchaseOrderDaoFactory.getPurchaseOrderDao(pvo).PurchaseOrder_modify();
	}
	
	public PurchaseOrderVO find(PurchaseOrderVO pvo){
		return null;
	}
	
	public int findMaxID(){
		return 0;
	}
	
	public void close(){
		PurchaseOrderDaoFactory.getPurchaseOrderDao().close();
	}
	
	public Vector findAll(){
		return PurchaseOrderDaoFactory.getPurchaseOrderDao().PurchaseOrder_findAll();
	}
	
	public Vector findType(int type,String str){
		return null;
	}
	
}
