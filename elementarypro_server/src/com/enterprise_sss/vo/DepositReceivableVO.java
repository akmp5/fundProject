package com.enterprise_sss.vo;


/**
 * Ԥ�տ��VO
 * @author yiguo
 * @version 1.0
 */
public class DepositReceivableVO {
	private int dr_id;//Ԥ�տ���
	private int dr_inv;//��Ʊ��
	private String dr_inv_date;//��Ʊ����
	private int clie_id;//�ͻ����
	private double cr_money;//Ԥ�տ��ܶ�
	private String dr_date;//�տ�����
	
	public DepositReceivableVO(){
		
	}
	
	public DepositReceivableVO(int dr_id,int dr_inv,String dr_inv_date,
			int clie_id,double cr_money,String dr_date){
		this.dr_id=dr_id;
		this.dr_inv=dr_inv;
		this.dr_inv_date=dr_inv_date;
		this.clie_id=clie_id;
		this.cr_money=cr_money;
		this.dr_date=dr_date;
	}

	public int getDr_id() {
		return dr_id;
	}

	public void setDr_id(int dr_id) {
		this.dr_id = dr_id;
	}

	public int getDr_inv() {
		return dr_inv;
	}

	public void setDr_inv(int dr_inv) {
		this.dr_inv = dr_inv;
	}

	public String getDr_inv_date() {
		return dr_inv_date;
	}

	public void setDr_inv_date(String dr_inv_date) {
		this.dr_inv_date = dr_inv_date;
	}

	public int getClie_id() {
		return clie_id;
	}

	public void setClie_id(int clie_id) {
		this.clie_id = clie_id;
	}

	public double getCr_money() {
		return cr_money;
	}

	public void setCr_money(double cr_money) {
		this.cr_money = cr_money;
	}

	public String getDr_date() {
		return dr_date;
	}

	public void setDr_date(String dr_date) {
		this.dr_date = dr_date;
	}
}
