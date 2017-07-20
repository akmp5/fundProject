package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.ClientDaoFactory;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.ClientVO;

public class ClientServer {
	
	public boolean validateDate(String str){
		if (MethodUtil.validateDate(str) && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void add(ClientVO cvo){
		ClientDaoFactory.getClientDao(cvo).Client_add();
	}
	
	public void del(ClientVO cvo){
		ClientDaoFactory.getClientDao(cvo).Client_del();
	}
	
	public void modify(ClientVO cvo){
		ClientDaoFactory.getClientDao(cvo).Client_modify();
	}
	
	public ClientVO find(ClientVO cvo){
		return ClientDaoFactory.getClientDao(cvo).Client_find();
	}
	
	public int findMaxID(){
		return ClientDaoFactory.getClientDao().Client_findMaxID();
	}
	
	public void close(){
		ClientDaoFactory.getClientDao().close();
	}
	
	public Vector findAll(){
		return ClientDaoFactory.getClientDao().Client_findAll();
	}
	
	public Vector findType(int type,String str){
		return ClientDaoFactory.getClientDao().Client_findType(type, str);
	}
}
