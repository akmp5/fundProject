package com.enterprise_sss.dao.inter;

import com.enterprise_sss.vo.UserVO;

/**
 * 登录接口类
 * @author Administrator
 *
 */
public interface LoginInter {
	
	//判断用户是否存在
	public boolean exist_user(UserVO uvo);
	
	public void close();
	
	public boolean modifyPw(UserVO uvo);
	
}
