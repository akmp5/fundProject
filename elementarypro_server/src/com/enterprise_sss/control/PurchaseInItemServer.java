package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.PurchaseInItemDaoFactory;
import com.enterprise_sss.vo.PurchaseInItemVO;

public class PurchaseInItemServer {

	public void add(PurchaseInItemVO pvo){
		PurchaseInItemDaoFactory.getPurchaseInItemDao(pvo).PurchaseInItem_add();
	}
	
	public void del(PurchaseInItemVO pvo){
		PurchaseInItemDaoFactory.getPurchaseInItemDao(pvo).PurchaseInItem_del();
	}
	
	public void del(int id){
		PurchaseInItemDaoFactory.getPurchaseInItemDao().PurchaseInItem_del(id);
	}
	
	public void modify(PurchaseInItemVO pvo){
		PurchaseInItemDaoFactory.getPurchaseInItemDao(pvo).PurchaseInItem_modify();
	}
	
	public PurchaseInItemVO find(PurchaseInItemVO pvo){
		return PurchaseInItemDaoFactory.getPurchaseInItemDao(pvo).PurchaseInItem_find();
	}
	
	public int findMaxID(){
		return PurchaseInItemDaoFactory.getPurchaseInItemDao().PurchaseInItem_findMaxID();
	}
	
	public void close(){
		PurchaseInItemDaoFactory.getPurchaseInItemDao().close();
	}
	
	public Vector findAll(int id){
		return PurchaseInItemDaoFactory.getPurchaseInItemDao().PurchaseInItem_findAll(id);
	}
	
	public Vector findType(int type,String str){
		return PurchaseInItemDaoFactory.getPurchaseInItemDao().PurchaseInItem_findType(type, str);
	}
	
}
