package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.PurchaseInVO;

public interface PurchaseInInter {

	public boolean PurchaseIn_add();
	
	public void PurchaseIn_del();
	
	public void PurchaseIn_modify();
	
	public PurchaseInVO PurchaseIn_find();
	
	public int PurchaseIn_findMaxID();
	
	public Vector PurchaseIn_findAll();
	
	public void close();
	
	public Vector PurchaseIn_findType(int type,String str);
	
}
