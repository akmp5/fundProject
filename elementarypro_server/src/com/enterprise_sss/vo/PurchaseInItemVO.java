package com.enterprise_sss.vo;

public class PurchaseInItemVO {

	private int pii_id;	//进货单项编号
	
	private int pib_id;	//进货单编号
	
	private int comm_id;	//货物编号
	
	private int pii_amount;	//进货数量
	
	private double purc_price;	//进价
	
	public PurchaseInItemVO(){
		
	}
	
	public PurchaseInItemVO(int pii_id,int pib_id,int comm_id,int pii_amount,double purc_price){
		this.pii_id = pii_id;
		this.pib_id = pib_id;
		this.comm_id = comm_id;
		this.pii_amount = pii_amount;
		this.purc_price = purc_price;
	}

	public int getComm_id() {
		return comm_id;
	}

	public void setComm_id(int comm_id) {
		this.comm_id = comm_id;
	}

	public int getPib_id() {
		return pib_id;
	}

	public void setPib_id(int pib_id) {
		this.pib_id = pib_id;
	}

	public int getPii_amount() {
		return pii_amount;
	}

	public void setPii_amount(int pii_amount) {
		this.pii_amount = pii_amount;
	}

	public int getPii_id() {
		return pii_id;
	}

	public void setPii_id(int pii_id) {
		this.pii_id = pii_id;
	}

	public double getPurc_price() {
		return purc_price;
	}

	public void setPurc_price(double purc_price) {
		this.purc_price = purc_price;
	}
	
}
