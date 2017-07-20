package com.enterprise_sss.control;

import com.enterprise_sss.dao.factory.DaoFactory;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.util.MethodUtil;
import com.enterprise_sss.vo.UserVO;

/**
 * ��¼���ݷ�����
 * 
 * @author Administrator
 * @version 1.0
 */
public class LoginServer {

	/**
	 * ��֤����
	 * 
	 * @param rvo
	 * @return
	 */
	public boolean isEmpty(UserVO uvo) {
		boolean result = false;
		if (MethodUtil.validateDate(uvo.getUser())) {
			if (MethodUtil.validateDate(uvo.getPassword())) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * �û���¼
	 * 
	 * @param rvo
	 * @return
	 */
	public boolean login(UserVO uvo) {
		boolean result = false;
		result = DaoFactory.getLoginDao().exist_user(uvo);
		if (result) {
			LogOperator.writeMessageLog("�ɹ�", uvo.getUser(), "��¼");
		}
		return result;
	}
	
	public void close(){
		DaoFactory.getLoginDao().close();
	}
	
	public boolean modifyPw(UserVO uvo){
		boolean b = DaoFactory.getLoginDao().modifyPw(uvo);
		if (b) {
			LogOperator.writeMessageLog("�ɹ�", uvo.getUser(), "�޸�����");
		}
		return b;
	}
}
