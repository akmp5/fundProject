package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.PurchaseInItemVO;

public interface PurchaseInItemInter {

	public void PurchaseInItem_add();
	
	public void PurchaseInItem_del();
	
	public void PurchaseInItem_del(int id);
	
	public void PurchaseInItem_modify();
	
	public PurchaseInItemVO PurchaseInItem_find();
	
	public int PurchaseInItem_findMaxID();
	
	public Vector PurchaseInItem_findAll(int id);
	
	public void close();
	
	public Vector PurchaseInItem_findType(int type,String str);
	
}
