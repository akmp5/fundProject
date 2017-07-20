package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.CommodityDaoFactory;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.CommodityVO;

public class CommodityServer {
	
	public boolean validateDate(String str){
		if (MethodUtil.validateDate(str) && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void add(CommodityVO cvo){
		CommodityDaoFactory.getCommodityDao(cvo).Commodity_add();
	}
	
	public void del(CommodityVO cvo){
		CommodityDaoFactory.getCommodityDao(cvo).Commodity_del();
	}
	
	public void modify(CommodityVO cvo){
		CommodityDaoFactory.getCommodityDao(cvo).Commodity_modify();
	}
	
	public CommodityVO find(CommodityVO cvo){
		return CommodityDaoFactory.getCommodityDao(cvo).Commodity_find();
	}
	
	public int findMaxID(){
		return CommodityDaoFactory.getCommodityDao().Commodity_findMaxID();
	}
	
	public void close(){
		CommodityDaoFactory.getCommodityDao().close();
	}
	
	public Vector findAll(){
		return CommodityDaoFactory.getCommodityDao().Commodity_findAll();
	}
	
	public Vector findType(int type, String str1, String str2){
		return CommodityDaoFactory.getCommodityDao().Commodity_findType(type, str1, str2);
	}
	
	public String[] findSort(){
		return CommodityDaoFactory.getCommodityDao().Commodity_findSort();
	}
}
