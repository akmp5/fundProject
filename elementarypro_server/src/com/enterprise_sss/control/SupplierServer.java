package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.SupplierDaoFactory;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.SupplierVO;

public class SupplierServer {

	public boolean validateDate(String str){
		if (MethodUtil.validateDate(str) && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void add(SupplierVO svo){
		SupplierDaoFactory.getSupplierDao(svo).Supplier_add();
	}
	
	public void del(SupplierVO svo){
		SupplierDaoFactory.getSupplierDao(svo).Supplier_del();
	}
	
	public void modify(SupplierVO svo){
		SupplierDaoFactory.getSupplierDao(svo).Supplier_modify();
	}
	
	public SupplierVO find(SupplierVO svo){
		return SupplierDaoFactory.getSupplierDao(svo).Supplier_find();
	}
	
	public int findMaxID(){
		return SupplierDaoFactory.getSupplierDao().Supplier_findMaxID();
	}
	
	public void close(){
		SupplierDaoFactory.getSupplierDao().close();
	}
	
	public Vector findAll(){
		return SupplierDaoFactory.getSupplierDao().Supplier_findAll();
	}
	
	public Vector findType(int type, String str){
		return SupplierDaoFactory.getSupplierDao().Supplier_findType(type, str);
	}
}
