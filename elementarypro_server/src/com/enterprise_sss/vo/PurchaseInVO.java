package com.enterprise_sss.vo;

import java.sql.Date;

import com.enterprise_sss.util.data.DataPicker;

public class PurchaseInVO {
	
	private int pib_id; // ���������

	private int supp_id; // �����̱��

	private Date pib_date; // ��������

	private int oper_id; // ҵ��Ա���

	private String cbill; // �Ƶ���

	private DataPicker dataPicker = new DataPicker();

	private String inspector; // ����Ա

	private String keeper; // ����Ա
	
	private int po_id;   //�ɹ��������
	
	public PurchaseInVO(){
		
	}
	
	public PurchaseInVO(int pib_id,int supp_id,Date pib_date,int oper_id,String cbill,String inspector,String keeper,int po_id){
		this.pib_id = pib_id;
		this.supp_id = supp_id;
		this.pib_date = pib_date;
		this.oper_id = oper_id;
		this.cbill = cbill;
		this.inspector = inspector;
		this.keeper = keeper;
		this.po_id = po_id;
	}

	public String getCbill() {
		return cbill;
	}

	public void setCbill(String cbill) {
		this.cbill = cbill;
	}

	public DataPicker getDataPicker() {
		return dataPicker;
	}

	public void setDataPicker(DataPicker dataPicker) {
		this.dataPicker = dataPicker;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getKeeper() {
		return keeper;
	}

	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}

	public int getOper_id() {
		return oper_id;
	}

	public void setOper_id(int oper_id) {
		this.oper_id = oper_id;
	}

	public Date getPib_date() {
		return pib_date;
	}

	public void setPib_date(Date pib_date) {
		this.pib_date = pib_date;
	}

	public int getPib_id() {
		return pib_id;
	}

	public void setPib_id(int pib_id) {
		this.pib_id = pib_id;
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
