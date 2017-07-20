package com.enterprise_sss.dao.inter;

import java.util.Vector;

public interface ReportFormInter {

	public Vector find(int type,String str);
	
	public void close();
	
	public Vector findAll();
	
}
