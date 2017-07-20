package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.DepotVO;

public interface DepotInter {

	public void Depot_add();

	public void Depot_del();

	public void Depot_modify();

	public DepotVO Depot_find();

	public int Depot_findMaxID();
	
	public Vector Depot_findAll();

	public void close();
	
	public Vector Depot_findType(int type,String str);
}
