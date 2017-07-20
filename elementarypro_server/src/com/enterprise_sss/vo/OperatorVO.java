package com.enterprise_sss.vo;

public class OperatorVO {

	private int oper_id; // 业务员编号文本框

	private String oper_spell_code; // 拼音编码文本框

	private String oper_name; // 姓名文本框

	private String sex; // 性别选择框

	private String oper_tel; // 电话文本框

	private String oper_mobile_tel; // 手机文本框

	private String oper_address; // 地址文本框

	private String oper_postcode; // 邮编文本框

	private String oper_ID_number; // 身份证号文本框

	private String oper_sort; // 类别文本框

	public OperatorVO() {

	}

	public OperatorVO(int oper_id, String oper_spell_code, String oper_name,
			String sex, String oper_tel, String oper_mobile_tel,
			String oper_address, String oper_postcode, String oper_ID_number,
			String oper_sort) {
		
		this.oper_id = oper_id;
		
		this.oper_spell_code = oper_spell_code;
		
		this.oper_name = oper_name;
		
		this.sex = sex;
		
		this.oper_tel = oper_tel;
		
		this.oper_mobile_tel = oper_mobile_tel;
		
		this.oper_address = oper_address;
		
		this.oper_postcode = oper_postcode;
		
		this.oper_ID_number = oper_ID_number;
		
		this.oper_sort = oper_sort;
		

	}

	public String getOper_address() {
		return oper_address;
	}

	public void setOper_address(String oper_address) {
		this.oper_address = oper_address;
	}

	public int getOper_id() {
		return oper_id;
	}

	public void setOper_id(int oper_id) {
		this.oper_id = oper_id;
	}

	public String getOper_ID_number() {
		return oper_ID_number;
	}

	public void setOper_ID_number(String oper_ID_number) {
		this.oper_ID_number = oper_ID_number;
	}

	public String getOper_mobile_tel() {
		return oper_mobile_tel;
	}

	public void setOper_mobile_tel(String oper_mobile_tel) {
		this.oper_mobile_tel = oper_mobile_tel;
	}

	public String getOper_name() {
		return oper_name;
	}

	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}

	public String getOper_postcode() {
		return oper_postcode;
	}

	public void setOper_postcode(String oper_postcode) {
		this.oper_postcode = oper_postcode;
	}

	public String getOper_sort() {
		return oper_sort;
	}

	public void setOper_sort(String oper_sort) {
		this.oper_sort = oper_sort;
	}

	public String getOper_spell_code() {
		return oper_spell_code;
	}

	public void setOper_spell_code(String oper_spell_code) {
		this.oper_spell_code = oper_spell_code;
	}

	public String getOper_tel() {
		return oper_tel;
	}

	public void setOper_tel(String oper_tel) {
		this.oper_tel = oper_tel;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
