package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.SupplierVO;

public interface SupplierInter {
	
	public void Supplier_add();
	
	public void Supplier_del();
	
	public void Supplier_modify();
	
	public SupplierVO Supplier_find();
	
	public int Supplier_findMaxID();
	
	public Vector Supplier_findAll();
	
	public void close();
	
	public Vector Supplier_findType(int type, String str);
	
}
