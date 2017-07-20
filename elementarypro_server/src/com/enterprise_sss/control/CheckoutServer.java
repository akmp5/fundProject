package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.DaoFactory;
import com.enterprise_sss.dao.sql.CheckoutSql;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.vo.GatherVO;

/**
 * 账务管理服务类
 * 
 * @author yiguo
 * @version 1.0
 */
public class CheckoutServer {
	/**
	 * 设定日期
	 */
	public boolean setDate(GatherVO vo){
		boolean flag=false;
		for(int i=0;i<getCommId().size();i++){
			vo.setJg_id(getGatherId()+1);//编号为存在的最大编号+1
		}
//		System.out.println(getCommId());
		return DaoFactory.getCheckoutDao().insertJxcGhter(getCommId(), vo);
	}
	
	/**
	 * 模拟结帐汇总
	 */
	public Vector getCheckout(String minDate,String maxDate){
		return DaoFactory.getCheckoutDao().findCheckout(getCommId(), minDate, maxDate);
//		LogOperator.writeMessageLog("成功", "", "模拟结帐汇总");
	}
	
	/**
	 * 已结账的最小日期
	 */
	public String getMinDate(){
		String minDate=DaoFactory.getCheckoutDao().findGatherDate(CheckoutSql.LASTDATE);
		if(minDate==null){
			minDate="2009-1-1";
		}
		return minDate;
	}
	
	/**
	 * 已结账的最大日期
	 */
	public String getMaxDate(){
		String maxDate=DaoFactory.getCheckoutDao().findGatherDate(CheckoutSql.THISDATE);
		if(maxDate==null){
			maxDate="";
		}
		return maxDate;
	}
	
	/**
	 * 正式结帐，将汇总数据存档
	 */
	public boolean formalCheckout(Vector lastGather,Vector checkoutData){
		if (lastGather.size()!=checkoutData.size()){
			LogOperator.writeMessageLog("失败", DataUtil.name, "正式结帐");
			return false;
		}else{
			boolean b = DaoFactory.getCheckoutDao().updateJxcGather(lastGather, checkoutData);
			if (b) {
				LogOperator.writeMessageLog("成功", DataUtil.name, "正式结帐");
			} else {
				LogOperator.writeMessageLog("失败", DataUtil.name, "正式结帐");
			}
			return b;
		}
	}
	
	/**
	 *  查询进销存汇总表的最大编号
	 */
	public int getGatherId(){
		Vector ids=DaoFactory.getCheckoutDao().findJxcGather(CheckoutSql.SELE_LID_JG);
		if(ids.size()==0){
			Vector i=new Vector();
			i.add(0);
			ids.add(i);
		}	
		
		return Integer.parseInt(((Vector)ids.get(0)).get(0).toString());
	}
	
	/**
	 * 查询商品清单表所有货物编号
	 */
	public Vector getCommId(){
		return DaoFactory.getCheckoutDao().findCommodityBill(CheckoutSql.SELE_COMM);
	}
	
	
	/**
	 *  查询进销存汇总表本次结帐记录
	 */
	public Vector getLastGather(){
		return DaoFactory.getCheckoutDao().findJxcGather(CheckoutSql.SELE_LAST_JG);
	}
	
	/**
	 *  查询进销存汇总表所有记录
	 */
	public Vector getGather(){
		return DaoFactory.getCheckoutDao().findJxcGather(CheckoutSql.SELE_JG);
	}
	
	/**
	 * 进销存汇总表表头
	 */
	public Vector<String> getImitateTitle(){
		Vector<String> gt=new Vector<String>();
		gt.add("货物编号");
		gt.add("起始日期");
		gt.add("截止日期");
		gt.add("上期结存数量");
		gt.add("上期结存金额");
		gt.add("销售数量");
		gt.add("销售金额");
		gt.add("进货数量");
		gt.add("进货金额");
		gt.add("库存数量");
		gt.add("库存金额");
		gt.add("溢损数量");
		gt.add("溢损金额");
		gt.add("本期结存数量");
		gt.add("本期结存金额");
		return gt;
	}
	/**
	 * 进销存汇总表表头
	 */
	public Vector<String> getGatherTitle(){
		Vector<String> gt=new Vector<String>();
		gt.add("结帐编号");
		gt.add("结帐日期");
		gt.add("货物编号");
		gt.add("上期结存数量");
		gt.add("上期结存金额");
		gt.add("销售数量");
		gt.add("销售金额");
		gt.add("进货数量");
		gt.add("进货金额");
		gt.add("溢损数量");
		gt.add("溢损金额");
		gt.add("本期结存数量");
		gt.add("本期结存金额");
		gt.add("备注");
		return gt;
	}
}
