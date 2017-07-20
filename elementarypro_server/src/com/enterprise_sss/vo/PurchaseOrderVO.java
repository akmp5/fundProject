package com.enterprise_sss.vo;

import java.sql.Date;

public class PurchaseOrderVO {
	
	private int po_id;   //订单编号
	
	private int supp_id;	//供货商编号
	
	private Date po_date;	//订货日期
	
	private Date po_begin_date;	//有效起日
	
	private Date po_end_date;	//有效止日
	
	private int oper_id;	//业务员编号
	
	private String cbill;	//制单人
	
	public PurchaseOrderVO(){
		
	}
	
	public PurchaseOrderVO(int po_id,int supp_id,Date po_date,Date po_begin_date,Date po_end_date,int oper_id,String cbill){
		this.po_id = po_id;
		this.supp_id = supp_id;
		this.po_date = po_date;
		this.po_begin_date = po_begin_date;
		this.po_end_date = po_end_date;
		this.oper_id = oper_id;
		this.cbill = cbill;
	}

	public String getCbill() {
		return cbill;
	}

	public void setCbill(String cbill) {
		this.cbill = cbill;
	}

	public int getOper_id() {
		return oper_id;
	}

	public void setOper_id(int oper_id) {
		this.oper_id = oper_id;
	}

	public Date getPo_begin_date() {
		return po_begin_date;
	}

	public void setPo_begin_date(Date po_begin_date) {
		this.po_begin_date = po_begin_date;
	}

	public Date getPo_date() {
		return po_date;
	}

	public void setPo_date(Date po_date) {
		this.po_date = po_date;
	}

	public Date getPo_end_date() {
		return po_end_date;
	}

	public void setPo_end_date(Date po_end_date) {
		this.po_end_date = po_end_date;
	}

	public int getPo_id() {
		return po_id;
	}

	public void setPo_id(int po_id) {
		this.po_id = po_id;
	}

	public int getSupp_id() {
		return supp_id;
	}

	public void setSupp_id(int supp_id) {
		this.supp_id = supp_id;
	}
	
}
