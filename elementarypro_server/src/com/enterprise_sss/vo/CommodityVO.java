package com.enterprise_sss.vo;

public class CommodityVO {

	private int comm_id; // 货物编号文

	private String comm_bar_code; // 条形码

	private String comm_name; // 商品名

	private String comm_spell_code; // 拼音编码

	private String comm_standard; // 规格

	private String comm_unit; // 单位

	private String comm_producing_area; // 产地

	private String comm_sort; // 类别

	private double purchase_price; // 进货价

	private double sale_price1; // 零售价

	private double sale_price2; // 会员价

	private double lowest_sale_price; // 最低售价

	public CommodityVO() {

	}

	public CommodityVO(int comm_id, String comm_bar_code, String comm_name,
			String comm_spell_code, String comm_standard, String comm_unit,
			String comm_producing_area, String comm_sort,
			double purchase_price, double sale_price1, double sale_price2,
			double lowest_sale_price) {
		
		this.comm_id = comm_id;
		
		this.comm_bar_code = comm_bar_code;
		
		this.comm_name = comm_name;
		
		this.comm_spell_code = comm_spell_code;
		
		this.comm_standard = comm_standard;
		
		this.comm_unit = comm_unit;
		
		this.comm_producing_area = comm_producing_area;
		
		this.comm_sort = comm_sort;
		
		this.purchase_price = purchase_price;
		
		this.sale_price1 = sale_price1;
		
		this.sale_price2 = sale_price2;
		
		this.lowest_sale_price = lowest_sale_price;
	}

	public String getComm_bar_code() {
		return comm_bar_code;
	}

	public void setComm_bar_code(String comm_bar_code) {
		this.comm_bar_code = comm_bar_code;
	}

	public int getComm_id() {
		return comm_id;
	}

	public void setComm_id(int comm_id) {
		this.comm_id = comm_id;
	}

	public String getComm_name() {
		return comm_name;
	}

	public void setComm_name(String comm_name) {
		this.comm_name = comm_name;
	}

	public String getComm_producing_area() {
		return comm_producing_area;
	}

	public void setComm_producing_area(String comm_producing_area) {
		this.comm_producing_area = comm_producing_area;
	}

	public String getComm_sort() {
		return comm_sort;
	}

	public void setComm_sort(String comm_sort) {
		this.comm_sort = comm_sort;
	}

	public String getComm_spell_code() {
		return comm_spell_code;
	}

	public void setComm_spell_code(String comm_spell_code) {
		this.comm_spell_code = comm_spell_code;
	}

	public String getComm_standard() {
		return comm_standard;
	}

	public void setComm_standard(String comm_standard) {
		this.comm_standard = comm_standard;
	}

	public String getComm_unit() {
		return comm_unit;
	}

	public void setComm_unit(String comm_unit) {
		this.comm_unit = comm_unit;
	}

	public double getLowest_sale_price() {
		return lowest_sale_price;
	}

	public void setLowest_sale_price(double lowest_sale_price) {
		this.lowest_sale_price = lowest_sale_price;
	}

	public double getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(double purchase_price) {
		this.purchase_price = purchase_price;
	}

	public double getSale_price1() {
		return sale_price1;
	}

	public void setSale_price1(double sale_price1) {
		this.sale_price1 = sale_price1;
	}

	public double getSale_price2() {
		return sale_price2;
	}

	public void setSale_price2(double sale_price2) {
		this.sale_price2 = sale_price2;
	}
}
