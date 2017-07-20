package com.enterprise_sss.vo;


/**
 * 应付款表VO
 * @author yiguo
 * @version 1.0
 */
public class AccountPayableVO {
	private int ap_id;//应付款编号
	private int ap_inv;//发票号
	private String ap_inv_date ;//填票日期
	private int pib_id ;//进货单编号
	private int comm_id ;//货物编号
	private int supp_id ;//供货商编号
	private int ap_comm_amount ;//数量
	private double ap_purchase_price ;//进货单价
	private double ap_money ;//应付款金额
	private String ap_date ;//付款日期
	private String ap_desc ;//详细说明
	private String state ;//状态
	private int dp_id ;//(减)预付
	
	public AccountPayableVO(){
		
	}

	public int getAp_comm_amount() {
		return ap_comm_amount;
	}

	public void setAp_comm_amount(int ap_comm_amount) {
		this.ap_comm_amount = ap_comm_amount;
	}

	public String getAp_date() {
		return ap_date;
	}

	public void setAp_date(String ap_date) {
		this.ap_date = ap_date;
	}

	public String getAp_desc() {
		return ap_desc;
	}

	public void setAp_desc(String ap_desc) {
		this.ap_desc = ap_desc;
	}

	public int getAp_id() {
		return ap_id;
	}

	public void setAp_id(int ap_id) {
		this.ap_id = ap_id;
	}

	public int getAp_inv() {
		return ap_inv;
	}

	public void setAp_inv(int ap_inv) {
		this.ap_inv = ap_inv;
	}

	public String getAp_inv_date() {
		return ap_inv_date;
	}

	public void setAp_inv_date(String ap_inv_date) {
		this.ap_inv_date = ap_inv_date;
	}

	public double getAp_money() {
		return ap_money;
	}

	public void setAp_money(double ap_money) {
		this.ap_money = ap_money;
	}

	public double getAp_purchase_price() {
		return ap_purchase_price;
	}

	public void setAp_purchase_price(double ap_purchase_price) {
		this.ap_purchase_price = ap_purchase_price;
	}

	public int getComm_id() {
		return comm_id;
	}

	public void setComm_id(int comm_id) {
		this.comm_id = comm_id;
	}

	public int getDp_id() {
		return dp_id;
	}

	public void setDp_id(int dp_id) {
		this.dp_id = dp_id;
	}

	public int getPib_id() {
		return pib_id;
	}

	public void setPib_id(int pib_id) {
		this.pib_id = pib_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getSupp_id() {
		return supp_id;
	}

	public void setSupp_id(int supp_id) {
		this.supp_id = supp_id;
	}
}
