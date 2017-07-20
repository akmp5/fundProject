package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.PurchaseVO;

public interface PurchaseInter {

	public void Purchase_add();
	
	public void Purchase_del();
	
	public void Purchase_modify();
	
	public PurchaseVO Purchase_find();
	
	public int Purchase_findMaxID();
	
	public Vector Purchase_findAll();
	
	public void close();
	
	public Vector Purchase_findType(int type, String str1, String str2);
	
}
