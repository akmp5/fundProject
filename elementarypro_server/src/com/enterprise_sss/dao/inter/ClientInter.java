package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.ClientVO;

public interface ClientInter {

	public void Client_add();
	
	public void Client_del();
	
	public void Client_modify();
	
	public ClientVO Client_find();
	
	public int Client_findMaxID();
	
	public Vector Client_findAll();
	
	public void close();
	
	public Vector Client_findType(int type,String str);
	
}
