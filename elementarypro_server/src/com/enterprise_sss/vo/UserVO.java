package com.enterprise_sss.vo;

import java.io.Serializable;

/**
 * 用户VO,用于缓存用户信息
 * 
 * @author Wang ming 2009-10-12
 * @version 1.0
 */
public class UserVO implements Serializable{

	private String user;

	private String password;

	private int level;

	public UserVO() {

	}

	public UserVO(String user, String password, int level) {
		this.user = user;
		this.password = password;
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
