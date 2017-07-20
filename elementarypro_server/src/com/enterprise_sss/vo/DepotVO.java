package com.enterprise_sss.vo;

public class DepotVO {

	private int depo_id; // ²Ö¿â±àºÅ

	private String depo_name; // ²Ö¿âÃû

	private int depo_sort; // Àà±ð

	private String depo_desc; // ±¸×¢

	public DepotVO() {

	}

	public DepotVO(int depo_id, String depo_name, int depo_sort,
			String depo_desc) {

		this.depo_id = depo_id;
		this.depo_name = depo_name;
		this.depo_sort = depo_sort;
		this.depo_desc = depo_desc;
	}

	public String getDepo_desc() {
		return depo_desc;
	}

	public void setDepo_desc(String depo_desc) {
		this.depo_desc = depo_desc;
	}

	public int getDepo_id() {
		return depo_id;
	}

	public void setDepo_id(int depo_id) {
		this.depo_id = depo_id;
	}

	public String getDepo_name() {
		return depo_name;
	}

	public void setDepo_name(String depo_name) {
		this.depo_name = depo_name;
	}

	public int getDepo_sort() {
		return depo_sort;
	}

	public void setDepo_sort(int depo_sort) {
		this.depo_sort = depo_sort;
	}

}
