package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.ThreadDaoFactory;
import com.enterprise_sss.vo.UserVO;

public class ThreadServer {
	
	public Vector threadLogin(UserVO uvo){
		return ThreadDaoFactory.getThreadDao().threadLogin(uvo);
	}
	
	public void close(){
		ThreadDaoFactory.getThreadDao().close();
	}
	
	public boolean threadApply(Vector v){
		return ThreadDaoFactory.getThreadDao().threadApply(v);
	}
	
	public Vector threadApplyConfirm(Vector v){
		return ThreadDaoFactory.getThreadDao().threadApplyConfirm(v);
	}
	
	public boolean threadApplyModify(Vector v){
		return ThreadDaoFactory.getThreadDao().threadApplyModify(v);
	}
	
}
