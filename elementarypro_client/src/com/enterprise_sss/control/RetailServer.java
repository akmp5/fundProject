package com.enterprise_sss.control;

import java.util.Vector;

public class RetailServer{
	
	/**
	 * 查询商品信息集合中指定编号的商品信息所在集合的下标
	 * @param comms 商品信息集合
	 * @param commId 商品编号
	 * @return 若没找到返回-1
	 */
	public int getComm(Vector comms,String commId){
		int index=-1;
		for(int i=0;i<comms.size();i++){
			if(commId.trim().equals(((Vector)comms.get(i)).get(0).toString())){
				index=i;
			}
		}
		return index;
	}
	
	/**
	 * 查询商品信息集合中包含指定字符的商品
	 * @param comms 所有商品信息集合
	 * @param commId 指定字符
	 * @return 若没找到返回空集合
	 */
	public Vector getBeComm(Vector comms,String commId){
		Vector beComm=new Vector();
		Vector row;
		for(int i=0;i<comms.size();i++){
			if(((Vector)comms.get(i)).get(0).toString().indexOf(commId.trim())>=0){
				row=new Vector();
				row.add(((Vector)comms.get(i)).get(0));//商品编号
				row.add(((Vector)comms.get(i)).get(2));//商品名称
				row.add(((Vector)comms.get(i)).get(4));//规格
				row.add(((Vector)comms.get(i)).get(5));//单位
				row.add(((Vector)comms.get(i)).get(6));//产地
				row.add(((Vector)comms.get(i)).get(8));//进价
				row.add(((Vector)comms.get(i)).get(12));//库存数量
				beComm.add(row);
			}
		}
		return beComm;
	}
	
	/**
	 * 总计数量和金额
	 * @return
	 */
	public Vector getSum(Vector statData){
		Vector ns=new Vector();
		double num=0;//总计数量
		double sum=0;//总计金额
		for(int i=0;i<statData.size();i++){
			num=num+Double.parseDouble(((Vector)statData.get(i)).get(3).toString());
			sum=sum+Double.parseDouble(((Vector)statData.get(i)).get(4).toString());
		}
		ns.add(num);
		ns.add(sum);
		return ns;
	}
	
	/**
	 * 根据销售明细得到销售统计表数据
	 * @return
	 */
	public Vector getStatData(Vector comms,Vector saleItems){
		Vector data=new Vector();
		Vector row;
		for(int i=0;i<saleItems.size();i++){
			row=new Vector();
			row.add(((Vector)saleItems.get(i)).get(0));//商品编号
			int j=getComm(comms,((Vector)saleItems.get(i)).get(0).toString());
			row.add(((Vector)comms.get(j)).get(2));//商品名称
			double price=Double.parseDouble(((Vector)saleItems.get(i)).get(2).toString());
			double num=Double.parseDouble(((Vector)saleItems.get(i)).get(1).toString());
			row.add(price);//单价
			row.add(num);//数量
			row.add(price*num);//金额
			data.add(row);
		}
		return data;
	}
	
	/**
	 * 货物申请依据表数据
	 * @param comms
	 * @return
	 */
	public Vector getApplyData(Vector comms){
		Vector data=new Vector();
		Vector row;
		for(int i=0;i<comms.size();i++){
			row=new Vector();
			row.add(((Vector)comms.get(i)).get(0));//商品编号
			row.add(((Vector)comms.get(i)).get(2));//商品名称
			row.add(((Vector)comms.get(i)).get(4));//规格
			row.add(((Vector)comms.get(i)).get(5));//单位
			row.add(((Vector)comms.get(i)).get(6));//产地
			row.add(((Vector)comms.get(i)).get(8));//进价
			row.add(((Vector)comms.get(i)).get(12));//库存数量
			data.add(row);
		}
		return data;
	}
	
	/**
	 *  判断字符串是否可转化为double型数据
	 * @param text
	 * @return
	 */
	public boolean isDouble(String text){
		boolean flag=false;
		if(text.trim().matches("[0-9]+\56?[0-9]+")
				|text.matches("[0-9]+")
				|text.matches("[0-9]+\56?")
				|text.matches("\56?[0-9]+")){
			flag=true;
		}
		return flag;
	}
	/**
	 *  判断字符串是否可转化为int型数据
	 * @param text
	 * @return
	 */
	public boolean isInt(String text){
		boolean flag=false;
		if(text.trim().matches("[0-9]+")){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 货物申请表列名
	 * @return
	 */
	public Vector<String> getAppliedTitle(){
		Vector<String> title=new Vector<String>();
		title.add("申请编号");
		title.add("门店编号");
		title.add("货物编号");
		title.add("数量");
		title.add("日期");
		title.add("申请人");
		title.add("申请说明");
		title.add("回复状态");
		return title;
	}
	
	/**
	 * 货物申请依据表列名
	 * @return
	 */
	public Vector<String> getApplyTitle(){
		Vector<String> title=new Vector<String>();
		title.add("商品编号");
		title.add("商品名称");
		title.add("规格");
		title.add("单位");
		title.add("产地");
		title.add("进价");
		title.add("库存数量");
		return title;
	}
	
	/**
	 * 销售统计表列名
	 * @return
	 */
	public Vector<String> getStatTitle(){
		Vector<String> title=new Vector<String>();
		title.add("商品编号");
		title.add("商品名称");
		title.add("单价");
		title.add("数量");
		title.add("金额");
		return title;
	}
	
	/**
	 * 回执单表列名
	 * @return
	 */
	public Vector<String> getReceiptTitle(){
		Vector<String> title=new Vector<String>();
		title.add("序号");
		title.add("商品编号");
		title.add("商品名称");
		title.add("单位");
		title.add("单价");
		title.add("数量");
		title.add("金额");
		return title;
	}
	
	/**
	 * 挂单表列名
	 * @return
	 */
	public Vector<String> getHangBillTitle(){
		Vector<String> title=new Vector<String>();
		title.add("单号");
		title.add("商品名称");
		return title;
	}
}
