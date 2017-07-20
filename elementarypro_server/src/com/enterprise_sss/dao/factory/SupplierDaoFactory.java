package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.SupplierDao;
import com.enterprise_sss.dao.inter.SupplierInter;
import com.enterprise_sss.vo.SupplierVO;

public class SupplierDaoFactory {

	public static SupplierInter getSupplierDao(SupplierVO svo){
		return new SupplierDao(svo);
	}
	
	public static SupplierInter getSupplierDao(){
		return new SupplierDao();
	}
	
}
