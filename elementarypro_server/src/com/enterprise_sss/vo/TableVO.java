package com.enterprise_sss.vo;

import java.util.Vector;

/**
 * ±Ì∏ÒVO¿‡
 * @author Administrator
 *
 */
public class TableVO {
	
	private int type;
	
	private Vector data;
	
	private Vector title;
	
	public TableVO(){
		
	}
	
	public TableVO(int type,Vector data,Vector title){
		this.type = type;
		this.data = data;
		this.title = title;
	}

	public Vector getData() {
		return data;
	}

	public void setData(Vector data) {
		this.data = data;
	}

	public Vector getTitle() {
		return title;
	}

	public void setTitle(Vector title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
