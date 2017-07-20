package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.ReportFormDao;
import com.enterprise_sss.dao.inter.ReportFormInter;

public class ReportFormDaoFactory {

	public static ReportFormInter getReportFormDao(){
		return new ReportFormDao();
	}
	
}
