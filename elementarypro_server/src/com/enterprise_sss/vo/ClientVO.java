package com.enterprise_sss.vo;

public class ClientVO {

	private int clie_id; // 客户编号文本框

	private String clie_spell_code; // 拼音编码文本框

	private String clie_shortname; // 简称文本框

	private String clie_name; // 名称文本框

	private String clie_linkman; // 联系人文本框

	private String clie_address; // 地址文本框

	private String Clie_postcode; // 邮编文本框

	private String Clie_tel; // 电话文本框

	private String Clie_fax; // 传真文本框

	private String Clie_bank; // 开户行文本框

	private String Clie_iban; // 银行帐号文本框

	private String Clie_sort; // 性质文本框

	private int oper_id; // 业务员ＩＤ文本框

	private String Clie_CreditLimt; // 授信额度文本框

	public ClientVO() {

	}

	public ClientVO(int clie_id, String clie_spell_code, String clie_shortname,
			String clie_name, String clie_linkman, String clie_address,
			String Clie_postcode, String Clie_tel, String Clie_fax,
			String Clie_bank, String Clie_iban, String Clie_sort, int oper_id,
			String Clie_CreditLimt) {

		this.clie_id = clie_id;
		this.clie_spell_code = clie_spell_code;
		this.clie_shortname = clie_shortname;
		this.clie_name = clie_name;
		this.clie_linkman = clie_linkman;
		this.clie_address = clie_address;
		this.Clie_postcode = Clie_postcode;
		this.Clie_tel = Clie_tel;
		this.Clie_fax = Clie_fax;
		this.Clie_bank = Clie_bank;
		this.Clie_iban = Clie_iban;
		this.Clie_sort = Clie_sort;
		this.oper_id = oper_id;
		this.Clie_CreditLimt = Clie_CreditLimt;
	}

	public String getClie_address() {
		return clie_address;
	}

	public void setClie_address(String clie_address) {
		this.clie_address = clie_address;
	}

	public String getClie_bank() {
		return Clie_bank;
	}

	public void setClie_bank(String clie_bank) {
		Clie_bank = clie_bank;
	}

	public String getClie_CreditLimt() {
		return Clie_CreditLimt;
	}

	public void setClie_CreditLimt(String clie_CreditLimt) {
		Clie_CreditLimt = clie_CreditLimt;
	}

	public String getClie_fax() {
		return Clie_fax;
	}

	public void setClie_fax(String clie_fax) {
		Clie_fax = clie_fax;
	}

	public String getClie_iban() {
		return Clie_iban;
	}

	public void setClie_iban(String clie_iban) {
		Clie_iban = clie_iban;
	}

	public int getClie_id() {
		return clie_id;
	}

	public void setClie_id(int clie_id) {
		this.clie_id = clie_id;
	}

	public String getClie_linkman() {
		return clie_linkman;
	}

	public void setClie_linkman(String clie_linkman) {
		this.clie_linkman = clie_linkman;
	}

	public String getClie_name() {
		return clie_name;
	}

	public void setClie_name(String clie_name) {
		this.clie_name = clie_name;
	}

	public String getClie_postcode() {
		return Clie_postcode;
	}

	public void setClie_postcode(String clie_postcode) {
		Clie_postcode = clie_postcode;
	}

	public String getClie_shortname() {
		return clie_shortname;
	}

	public void setClie_shortname(String clie_shortname) {
		this.clie_shortname = clie_shortname;
	}

	public String getClie_sort() {
		return Clie_sort;
	}

	public void setClie_sort(String clie_sort) {
		Clie_sort = clie_sort;
	}

	public String getClie_spell_code() {
		return clie_spell_code;
	}

	public void setClie_spell_code(String clie_spell_code) {
		this.clie_spell_code = clie_spell_code;
	}

	public String getClie_tel() {
		return Clie_tel;
	}

	public void setClie_tel(String clie_tel) {
		Clie_tel = clie_tel;
	}

	public int getOper_id() {
		return oper_id;
	}

	public void setOper_id(int oper_id) {
		this.oper_id = oper_id;
	}

}
