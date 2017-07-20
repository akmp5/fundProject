package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.UserVO;

public interface ThreadInter {
	
	public Vector threadLogin(UserVO uvo);
	
	public void close();
	
	public boolean threadApply(Vector v);
	
	public Vector threadApplyConfirm(Vector v);
	
	public boolean threadApplyModify(Vector v);
}
