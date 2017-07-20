package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.PurchaseOrderVO;

public interface PurchaseOrderInter {
	
	public boolean PurchaseOrder_add();
	
	public void PurchaseOrder_del();
	
	public void PurchaseOrder_modify();
	
	public PurchaseOrderVO PurchaseOrder_find();
	
	public int PurchaseOrder_findMaxID();
	
	public Vector PurchaseOrder_findAll();
	
	public void close();
	
	public Vector PurchaseOrder_findType(int type,String str);
}
