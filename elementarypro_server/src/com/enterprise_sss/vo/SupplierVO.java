package com.enterprise_sss.vo;

public class SupplierVO {

	private int supp_id;

	private String supp_spell_code;

	private String supp_shortname;

	private String supp_name;

	private String supp_address;

	private String supp_postcode;

	private String supp_sort;

	private String supp_tel;

	private String supp_fax;

	private String supp_bank;

	private String supp_iban;

	private String supp_storage_address;

	private String supp_storage_tel;

	private int oper_id;

	public SupplierVO() {

	}

	public SupplierVO(int supp_id, String supp_spell_code,
			String supp_shortname, String supp_name, String supp_address,
			String supp_postcode, String supp_sort, String supp_tel,
			String supp_fax, String supp_bank, String supp_iban,
			String supp_storage_address, String supp_storage_tel, int oper_id) {
		
		this.supp_id = supp_id;
		this.supp_spell_code = supp_spell_code;
		this.supp_shortname = supp_shortname;
		this.supp_name = supp_name;
		this.supp_address = supp_address;
		this.supp_postcode = supp_postcode;
		this.supp_sort = supp_sort;
		this.supp_tel = supp_tel;
		this.supp_fax = supp_fax;
		this.supp_bank = supp_bank;
		this.supp_iban = supp_iban;
		this.supp_storage_address = supp_storage_address;
		this.supp_storage_tel = supp_storage_tel;
		this.oper_id = oper_id;

	}

	public int getOper_id() {
		return oper_id;
	}

	public void setOper_id(int oper_id) {
		this.oper_id = oper_id;
	}

	public String getSupp_address() {
		return supp_address;
	}

	public void setSupp_address(String supp_address) {
		this.supp_address = supp_address;
	}

	public String getSupp_bank() {
		return supp_bank;
	}

	public void setSupp_bank(String supp_bank) {
		this.supp_bank = supp_bank;
	}

	public String getSupp_fax() {
		return supp_fax;
	}

	public void setSupp_fax(String supp_fax) {
		this.supp_fax = supp_fax;
	}

	public String getSupp_iban() {
		return supp_iban;
	}

	public void setSupp_iban(String supp_iban) {
		this.supp_iban = supp_iban;
	}

	public int getSupp_id() {
		return supp_id;
	}

	public void setSupp_id(int supp_id) {
		this.supp_id = supp_id;
	}

	public String getSupp_name() {
		return supp_name;
	}

	public void setSupp_name(String supp_name) {
		this.supp_name = supp_name;
	}

	public String getSupp_postcode() {
		return supp_postcode;
	}

	public void setSupp_postcode(String supp_postcode) {
		this.supp_postcode = supp_postcode;
	}

	public String getSupp_shortname() {
		return supp_shortname;
	}

	public void setSupp_shortname(String supp_shortname) {
		this.supp_shortname = supp_shortname;
	}

	public String getSupp_sort() {
		return supp_sort;
	}

	public void setSupp_sort(String supp_sort) {
		this.supp_sort = supp_sort;
	}

	public String getSupp_spell_code() {
		return supp_spell_code;
	}

	public void setSupp_spell_code(String supp_spell_code) {
		this.supp_spell_code = supp_spell_code;
	}

	public String getSupp_storage_address() {
		return supp_storage_address;
	}

	public void setSupp_storage_address(String supp_storage_address) {
		this.supp_storage_address = supp_storage_address;
	}

	public String getSupp_storage_tel() {
		return supp_storage_tel;
	}

	public void setSupp_storage_tel(String supp_storage_tel) {
		this.supp_storage_tel = supp_storage_tel;
	}

	public String getSupp_tel() {
		return supp_tel;
	}

	public void setSupp_tel(String supp_tel) {
		this.supp_tel = supp_tel;
	}
}
