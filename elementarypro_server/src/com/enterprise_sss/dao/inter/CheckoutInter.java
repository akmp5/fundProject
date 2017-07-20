package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.GatherVO;

public interface CheckoutInter {
	Vector findCommodityBill(String sql);
	Vector findJxcGather(String sql);
	String findGatherDate(String sql);
	Vector findCheckout(Vector comms,String minDate,String maxDate);
	boolean insertJxcGhter(Vector comms,GatherVO vo);
	boolean updateJxcGather(Vector ids,Vector data);
}
