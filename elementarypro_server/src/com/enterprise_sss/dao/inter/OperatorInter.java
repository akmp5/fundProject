package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.OperatorVO;

public interface OperatorInter {

	public void Operator_add();

	public void Operator_del();

	public void Operator_modify();

	public OperatorVO Operator_find();

	public int Operator_findMaxID();
	
	public Vector Operator_findAll();

	public void close();
	
	public Vector Operator_findType(int type,String str);

}
