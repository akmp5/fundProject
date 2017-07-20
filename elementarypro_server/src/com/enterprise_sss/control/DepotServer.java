package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.DepotDaoFactory;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.DepotVO;

public class DepotServer {

	public boolean validateDate(String str) {
		if (MethodUtil.validateDate(str) && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	public void add(DepotVO dvo) {
		DepotDaoFactory.getCommodityDao(dvo).Depot_add();
	}

	public void del(DepotVO dvo) {
		DepotDaoFactory.getCommodityDao(dvo).Depot_del();
	}

	public void modify(DepotVO dvo) {
		DepotDaoFactory.getCommodityDao(dvo).Depot_modify();
	}

	public DepotVO find(DepotVO dvo) {
		return DepotDaoFactory.getCommodityDao(dvo).Depot_find();
	}

	public int findMaxID() {
		return DepotDaoFactory.getCommodityDao().Depot_findMaxID();
	}

	public void close() {
		DepotDaoFactory.getCommodityDao().close();
	}

	public Vector findAll() {
		return DepotDaoFactory.getCommodityDao().Depot_findAll();
	}

	public Vector findType(int type, String str) {
		return DepotDaoFactory.getCommodityDao().Depot_findType(type, str);
	}
}
