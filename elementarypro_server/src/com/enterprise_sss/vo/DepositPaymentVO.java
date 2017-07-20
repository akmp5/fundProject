package com.enterprise_sss.vo;

/**
 * 预付款表VO
 * @author yiguo
 * @version 1.0
 */
public class DepositPaymentVO {
	private int dp_id;//预付款编号
	private int dp_inv;//发票号
	private String dp_inv_date;//填票日期
	private int supp_id;//供货商编号
	private double dp_money;//预付款总额
	private String dp_date;//付款日期
	
	public DepositPaymentVO(){
		
	}
	
	public DepositPaymentVO(int dp_id,int dp_inv,String dp_inv_date,
			int supp_id,double dp_money,String dp_date){
		this.dp_id=dp_id;
		this.dp_inv=dp_inv;
		this.dp_inv_date=dp_inv_date;
		this.supp_id=supp_id;
		this.dp_money=dp_money;
		this.dp_date=dp_date;
	}

	public int getDp_id() {
		return dp_id;
	}

	public void setDp_id(int dp_id) {
		this.dp_id = dp_id;
	}

	public int getDp_inv() {
		return dp_inv;
	}

	public void setDp_inv(int dp_inv) {
		this.dp_inv = dp_inv;
	}

	public String getDp_inv_date() {
		return dp_inv_date;
	}

	public void setDp_inv_date(String dp_inv_date) {
		this.dp_inv_date = dp_inv_date;
	}

	public int getSupp_id() {
		return supp_id;
	}

	public void setSupp_id(int supp_id) {
		this.supp_id = supp_id;
	}

	public double getDp_money() {
		return dp_money;
	}

	public void setDp_money(double dp_money) {
		this.dp_money = dp_money;
	}

	public String getDp_date() {
		return dp_date;
	}

	public void setDp_date(String dp_date) {
		this.dp_date = dp_date;
	}
}
