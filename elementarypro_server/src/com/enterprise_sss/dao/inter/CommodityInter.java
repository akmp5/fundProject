package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.CommodityVO;

public interface CommodityInter {
	
	public void Commodity_add();
	
	public void Commodity_del();
	
	public void Commodity_modify();
	
	public CommodityVO Commodity_find();
	
	public int Commodity_findMaxID();
	
	public Vector Commodity_findAll();
	
	public void close();
	
	public String[] Commodity_findSort();
	
	public Vector Commodity_findType(int type, String str1, String str2);
}
