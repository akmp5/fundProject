package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.ReportFormDaoFactory;

public class ReportFormDaoServer {

	public Vector find(int type,String str){
		return ReportFormDaoFactory.getReportFormDao().find(type,str);
	}
	
	public void close(){
		ReportFormDaoFactory.getReportFormDao().close();
	}
	
	public Vector findAll(){
		return ReportFormDaoFactory.getReportFormDao().findAll();
	}
	
}
