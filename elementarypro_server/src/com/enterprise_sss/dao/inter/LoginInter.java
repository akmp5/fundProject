package com.enterprise_sss.dao.inter;

import com.enterprise_sss.vo.UserVO;

/**
 * ��¼�ӿ���
 * @author Administrator
 *
 */
public interface LoginInter {
	
	//�ж��û��Ƿ����
	public boolean exist_user(UserVO uvo);
	
	public void close();
	
	public boolean modifyPw(UserVO uvo);
	
}
