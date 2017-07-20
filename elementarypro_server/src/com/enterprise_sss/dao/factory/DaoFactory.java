package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.CheckoutDao;
import com.enterprise_sss.dao.impl.LoginDao;
import com.enterprise_sss.dao.impl.PaymentDao;
import com.enterprise_sss.dao.impl.ReceivableDao;
import com.enterprise_sss.dao.inter.CheckoutInter;
import com.enterprise_sss.dao.inter.LoginInter;
import com.enterprise_sss.dao.inter.PaymentInter;
import com.enterprise_sss.dao.inter.ReceivableInter;

/**
 * 数据库操作工厂
 * @author Administrator
 *
 */
public class DaoFactory {
	
	public static LoginInter getLoginDao(){
		return new LoginDao();
	}
	
	/**
	 * 应付款模块数据库操作
	 * @return
	 */
	public static PaymentInter getPaymentDao(){
		return new PaymentDao();
	}
	
	/**
	 * 应收款模块数据库操作
	 * @return
	 */
	public static ReceivableInter getReceivableDao(){
		return new ReceivableDao();
	}
	
	/**
	 * 帐务管理模块数据库操作
	 * @return
	 */
	public static CheckoutInter getCheckoutDao(){
		return new CheckoutDao();
	}
	
}
