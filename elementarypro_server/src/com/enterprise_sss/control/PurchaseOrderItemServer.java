package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.PurchaseOrderItemDaoFactory;
import com.enterprise_sss.vo.PurchaseOrderItemVO;

public class PurchaseOrderItemServer {
	
	public void add(PurchaseOrderItemVO pvo){
		PurchaseOrderItemDaoFactory.getPurchaseOrderItemDao(pvo).PurchaseOrderItem_add();
	}
	
	public void del(PurchaseOrderItemVO pvo){
		PurchaseOrderItemDaoFactory.getPurchaseOrderItemDao(pvo).PurchaseOrderItem_del();
	}
	
	public void del(int id){
		PurchaseOrderItemDaoFactory.getPurchaseOrderItemDao().PurchaseOrderItem_del(id);
	}
	
	public void modify(PurchaseOrderItemVO pvo){
		PurchaseOrderItemDaoFactory.getPurchaseOrderItemDao(pvo).PurchaseOrderItem_modify();
	}
	
	public PurchaseOrderItemVO find(PurchaseOrderItemVO pvo){
		return PurchaseOrderItemDaoFactory.getPurchaseOrderItemDao(pvo).PurchaseOrderItem_find();
	}
	
	public int findMaxID(){
		return PurchaseOrderItemDaoFactory.getPurchaseOrderItemDao().PurchaseOrderItem_findMaxID();
	}
	
	public void close(){
		PurchaseOrderItemDaoFactory.getPurchaseOrderItemDao().close();
	}
	
	public Vector findAll(int id){
		return PurchaseOrderItemDaoFactory.getPurchaseOrderItemDao().PurchaseOrderItem_findAll(id);
	}
	
	public Vector findType(int type,String str){
		return PurchaseOrderItemDaoFactory.getPurchaseOrderItemDao().PurchaseOrderItem_findType(type, str);
	}
	
}
