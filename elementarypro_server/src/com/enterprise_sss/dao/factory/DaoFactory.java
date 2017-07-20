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
 * ���ݿ��������
 * @author Administrator
 *
 */
public class DaoFactory {
	
	public static LoginInter getLoginDao(){
		return new LoginDao();
	}
	
	/**
	 * Ӧ����ģ�����ݿ����
	 * @return
	 */
	public static PaymentInter getPaymentDao(){
		return new PaymentDao();
	}
	
	/**
	 * Ӧ�տ�ģ�����ݿ����
	 * @return
	 */
	public static ReceivableInter getReceivableDao(){
		return new ReceivableDao();
	}
	
	/**
	 * �������ģ�����ݿ����
	 * @return
	 */
	public static CheckoutInter getCheckoutDao(){
		return new CheckoutDao();
	}
	
}
