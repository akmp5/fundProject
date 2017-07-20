package com.enterprise_sss.vo;

import java.sql.Date;

public class PurchaseOrderVO {
	
	private int po_id;   //�������
	
	private int supp_id;	//�����̱��
	
	private Date po_date;	//��������
	
	private Date po_begin_date;	//��Ч����
	
	private Date po_end_date;	//��Чֹ��
	
	private int oper_id;	//ҵ��Ա���
	
	private String cbill;	//�Ƶ���
	
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
