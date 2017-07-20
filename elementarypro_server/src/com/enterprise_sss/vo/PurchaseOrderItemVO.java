package com.enterprise_sss.vo;

public class PurchaseOrderItemVO {

	private int poi_id; // 订单项编号

	private int po_id; // 订单编号

	private int comm_id; // 货物编号

	private int poi_amount; // 订货数量

	private double purc_price; // 进价

	public PurchaseOrderItemVO() {

	}

	public PurchaseOrderItemVO(int poi_id, int po_id, int comm_id,
			int poi_amount, double purc_price) {
		this.poi_id = poi_id;
		this.po_id = po_id;
		this.comm_id = comm_id;
		this.poi_amount = poi_amount;
		this.purc_price = purc_price;
	}

	public int getComm_id() {
		return comm_id;
	}

	public void setComm_id(int comm_id) {
		this.comm_id = comm_id;
	}

	public int getPo_id() {
		return po_id;
	}

	public void setPo_id(int po_id) {
		this.po_id = po_id;
	}

	public int getPoi_amount() {
		return poi_amount;
	}

	public void setPoi_amount(int poi_amount) {
		this.poi_amount = poi_amount;
	}

	public int getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(int poi_id) {
		this.poi_id = poi_id;
	}

	public double getPurc_price() {
		return purc_price;
	}

	public void setPurc_price(double purc_price) {
		this.purc_price = purc_price;
	}
}
