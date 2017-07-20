package com.enterprise_sss.vo;

import java.sql.Date;

public class PurchaseVO {

	private int pc_id; // 合同编号

	private int supp_id; // 供货商编号

	private int comm_id; // 货物编号

	private double purc_price; // 进价

	private String pc_pay_method; // 付款方式

	private String pc_pay_period; // 帐期

	private Date pc_date; // 签订日期

	private String pc_period; // 合同期限

	public PurchaseVO() {

	}

	public PurchaseVO(int pc_id, int supp_id, int comm_id, double purc_price,
			String pc_pay_method, String pc_pay_period, Date pc_date,
			String pc_period) {
		
		this.pc_id = pc_id;
		this.supp_id = supp_id;
		this.comm_id = comm_id;
		this.purc_price = purc_price;
		this.pc_pay_method = pc_pay_method;
		this.pc_pay_period = pc_pay_period;
		this.pc_date = pc_date;
		this.pc_period = pc_period;
	}

	public int getComm_id() {
		return comm_id;
	}

	public void setComm_id(int comm_id) {
		this.comm_id = comm_id;
	}

	public Date getPc_date() {
		return pc_date;
	}

	public void setPc_date(Date pc_date) {
		this.pc_date = pc_date;
	}

	public int getPc_id() {
		return pc_id;
	}

	public void setPc_id(int pc_id) {
		this.pc_id = pc_id;
	}

	public String getPc_pay_method() {
		return pc_pay_method;
	}

	public void setPc_pay_method(String pc_pay_method) {
		this.pc_pay_method = pc_pay_method;
	}

	public String getPc_pay_period() {
		return pc_pay_period;
	}

	public void setPc_pay_period(String pc_pay_period) {
		this.pc_pay_period = pc_pay_period;
	}

	public String getPc_period() {
		return pc_period;
	}

	public void setPc_period(String pc_period) {
		this.pc_period = pc_period;
	}

	public double getPurc_price() {
		return purc_price;
	}

	public void setPurc_price(double purc_price) {
		this.purc_price = purc_price;
	}

	public int getSupp_id() {
		return supp_id;
	}

	public void setSupp_id(int supp_id) {
		this.supp_id = supp_id;
	}

}
