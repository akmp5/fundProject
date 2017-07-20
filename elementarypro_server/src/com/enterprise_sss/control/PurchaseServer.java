package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.PurchaseDaoFactory;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.PurchaseVO;

public class PurchaseServer {

	public boolean validateDate(String str){
		if (MethodUtil.validateDate(str) && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void add(PurchaseVO pvo){
		PurchaseDaoFactory.getPurchaseDao(pvo).Purchase_add();
	}
	
	public void del(PurchaseVO pvo){
		PurchaseDaoFactory.getPurchaseDao(pvo).Purchase_del();
	}
	
	public void modify(PurchaseVO pvo){
		PurchaseDaoFactory.getPurchaseDao(pvo).Purchase_modify();
	}
	
	public PurchaseVO find(PurchaseVO pvo){
		return PurchaseDaoFactory.getPurchaseDao(pvo).Purchase_find();
	}
	
	public int findMaxID(){
		return PurchaseDaoFactory.getPurchaseDao().Purchase_findMaxID();
	}
	
	public void close(){
		PurchaseDaoFactory.getPurchaseDao().close();
	}
	
	public Vector findAll(){
		return PurchaseDaoFactory.getPurchaseDao().Purchase_findAll();
	}
	
	public Vector findType(int type, String str1, String str2){
		return PurchaseDaoFactory.getPurchaseDao().Purchase_findType(type, str1, str2);
	}
}
