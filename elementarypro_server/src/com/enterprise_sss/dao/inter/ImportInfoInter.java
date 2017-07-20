package com.enterprise_sss.dao.inter;

import java.util.Vector;

public interface ImportInfoInter {

	public void add(String table_title, Vector vo);
	
	public void close();
	
}
