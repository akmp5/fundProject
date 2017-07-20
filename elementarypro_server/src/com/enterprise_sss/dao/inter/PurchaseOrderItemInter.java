package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.PurchaseOrderItemVO;

public interface PurchaseOrderItemInter {
	
	public void PurchaseOrderItem_add();
	
	public void PurchaseOrderItem_del();
	
	public void PurchaseOrderItem_del(int id);
	
	public void PurchaseOrderItem_modify();
	
	public PurchaseOrderItemVO PurchaseOrderItem_find();
	
	public int PurchaseOrderItem_findMaxID();
	
	public Vector PurchaseOrderItem_findAll(int id);
	
	public void close();
	
	public Vector PurchaseOrderItem_findType(int type,String str);
}
