package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.OperatorDaoFactory;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.OperatorVO;

public class OperatorServer {

	public boolean validateDate(String str){
		if (MethodUtil.validateDate(str) && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void add(OperatorVO ovo){
		OperatorDaoFactory.getOperatorDao(ovo).Operator_add();
	}
	
	public void del(OperatorVO ovo){
		OperatorDaoFactory.getOperatorDao(ovo).Operator_del();
	}
	
	public void modify(OperatorVO ovo){
		OperatorDaoFactory.getOperatorDao(ovo).Operator_modify();
	}
	
	public OperatorVO find(OperatorVO ovo){
		return OperatorDaoFactory.getOperatorDao(ovo).Operator_find();
	}
	
	public int findMaxID(){
		return OperatorDaoFactory.getOperatorDao().Operator_findMaxID();
	}
	
	public void close(){
		OperatorDaoFactory.getOperatorDao().close();
	}
	
	public Vector findAll(){
		return OperatorDaoFactory.getOperatorDao().Operator_findAll();
	}
	
	public Vector findType(int type,String str){
		return OperatorDaoFactory.getOperatorDao().Operator_findType(type, str);
	}
}
