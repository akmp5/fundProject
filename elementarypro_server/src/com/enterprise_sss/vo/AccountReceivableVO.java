package com.enterprise_sss.vo;


/**
 * Ӧ�տ��VO
 * @author yiguo
 * @version 1.0
 */
public class AccountReceivableVO {
	private int ar_id;//Ӧ�տ���
	private int ar_inv;//��Ʊ��
	private String ar_inv_date ;//��Ʊ����
	private int sb_id  ;//���۵����
	private int comm_id ;//������
	private int clie_id  ;//�ͻ����
	private int ar_comm_amount ;//����
	private double ar_sale_price   ;//���ۼ�
	private double ar_money ;//Ӧ�տ���
	private String ar_date ;//�տ�����
	private String ar_desc ;//��ϸ˵��
	private String state ;//״̬
	private int dr_id ;//(��)Ԥ��
	
	public AccountReceivableVO(){
		
	}
	
	
	
	public int getAr_comm_amount() {
		return ar_comm_amount;
	}
	public void setAr_comm_amount(int ar_comm_amount) {
		this.ar_comm_amount = ar_comm_amount;
	}
	public String getAr_date() {
		return ar_date;
	}
	public void setAr_date(String ar_date) {
		this.ar_date = ar_date;
	}
	public String getAr_desc() {
		return ar_desc;
	}
	public void setAr_desc(String ar_desc) {
		this.ar_desc = ar_desc;
	}
	public int getAr_id() {
		return ar_id;
	}
	public void setAr_id(int ar_id) {
		this.ar_id = ar_id;
	}
	public int getAr_inv() {
		return ar_inv;
	}
	public void setAr_inv(int ar_inv) {
		this.ar_inv = ar_inv;
	}
	public String getAr_inv_date() {
		return ar_inv_date;
	}
	public void setAr_inv_date(String ar_inv_date) {
		this.ar_inv_date = ar_inv_date;
	}
	public double getAr_money() {
		return ar_money;
	}
	public void setAr_money(double ar_money) {
		this.ar_money = ar_money;
	}
	public double getAr_sale_price() {
		return ar_sale_price;
	}
	public void setAr_sale_price(double ar_sale_price) {
		this.ar_sale_price = ar_sale_price;
	}
	public int getClie_id() {
		return clie_id;
	}
	public void setClie_id(int clie_id) {
		this.clie_id = clie_id;
	}
	public int getComm_id() {
		return comm_id;
	}
	public void setComm_id(int comm_id) {
		this.comm_id = comm_id;
	}
	public int getDr_id() {
		return dr_id;
	}
	public void setDr_id(int dr_id) {
		this.dr_id = dr_id;
	}
	public int getSb_id() {
		return sb_id;
	}
	public void setSb_id(int sb_id) {
		this.sb_id = sb_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
