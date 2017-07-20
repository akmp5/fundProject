package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.PurchaseInDaoFactory;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.PurchaseInVO;

public class PurchaseInServer {

	public boolean validateDate(String str){
		if (MethodUtil.validateDate(str) && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean add(PurchaseInVO pvo){
		return PurchaseInDaoFactory.getPurchaseInDao(pvo).PurchaseIn_add();
	}
	
	public void del(PurchaseInVO pvo){
		PurchaseInDaoFactory.getPurchaseInDao(pvo).PurchaseIn_del();
	}
	
	public void modify(PurchaseInVO pvo){
		PurchaseInDaoFactory.getPurchaseInDao(pvo).PurchaseIn_modify();
	}
	
	public PurchaseInVO find(PurchaseInVO pvo){
		return null;
	}
	
	public int findMaxID(){
		return 0;
	}
	
	public void close(){
		PurchaseInDaoFactory.getPurchaseInDao().close();
	}
	
	public Vector findAll(){
		return PurchaseInDaoFactory.getPurchaseInDao().PurchaseIn_findAll();
	}
	
	public Vector findType(int type,String str){
		return null;
	}
	
}
