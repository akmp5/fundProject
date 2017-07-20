package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.DaoFactory;
import com.enterprise_sss.dao.sql.AccountSql;
import com.enterprise_sss.vo.AccountPayableVO;
import com.enterprise_sss.vo.DepositPaymentVO;


/**
 * Ӧ�������ݷ�����
 * 
 * @author yiguo
 * @version 1.0
 */
public class PaymentServer {
	/**
	 * ��ѯԤ��������м�¼
	 * @return
	 */
	public Vector getDespositPayment(){
		return DaoFactory.getPaymentDao().findDepositPayment(AccountSql.SELE_DP);
	}
	/**
	 * ��ѯ�����Ԥ��������м�¼
	 * @return
	 */
	public Vector getYearDespositPayment(){
		return DaoFactory.getPaymentDao().findDepositPayment(AccountSql.SELE_DP_TY);
	}
	/**
	 * ��ѯ�ϸ���Ԥ��������м�¼
	 * @return
	 */
	public Vector getLastMonthDespositPayment(){
		return DaoFactory.getPaymentDao().findDepositPayment(AccountSql.SELE_DP_LM);
	}
	/**
	 * ��ѯ����Ԥ��������м�¼
	 * @return
	 */
	public Vector getThisMonthDespositPayment(){
		return DaoFactory.getPaymentDao().findDepositPayment(AccountSql.SELE_DP_TM);
	}
	
	/**
	 * ɾ��Ԥ�����ָ����ŵļ�¼
	 * @return
	 */
	public boolean delDespositPayment(DepositPaymentVO vo){
		return DaoFactory.getPaymentDao().updateDepositPayment(vo,AccountSql.DEL_DP);
	}
	
	/**
	 * ���Ԥ�����¼
	 * @return
	 */
	public boolean addDespositPayment(DepositPaymentVO vo){
		return DaoFactory.getPaymentDao().updateDepositPayment(vo,AccountSql.ADD_DP);
	}
	
	/**
	 * �޸�Ԥ�����ָ����ŵļ�¼
	 * @return
	 */
	public boolean updateDespositPayment(DepositPaymentVO vo){
		return DaoFactory.getPaymentDao().updateDepositPayment(vo,AccountSql.UP_DP);
	}
	
	/**
	 * ��ѯӦ��������м�¼
	 * @return
	 */
	public Vector getAccountPayable(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_AP);
	}
	/**
	 * ��ѯ�����Ӧ������¼
	 * @return
	 */
	public Vector getYearAccountPayable(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_AP_TY);
	}
	/**
	 * ��ѯ�ϸ���Ӧ��������м�¼
	 * @return
	 */
	public Vector getLastMonthAccountPayable(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_AP_LM);
	}
	/**
	 * ��ѯ����Ӧ��������м�¼
	 * @return
	 */
	public Vector getThisMonthAccountPayable(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_AP_TM);
	}
	
	/**
	 * ɾ��Ӧ�����ָ����ŵļ�¼
	 * @return
	 */
	public boolean delAccountPayable(AccountPayableVO vo){
		return DaoFactory.getPaymentDao().updateAccountPayable(vo, AccountSql.DEL_AP);
	}
	
	/**
	 * ���Ӧ�����¼
	 * @return
	 */
	public boolean addAccountPayable(AccountPayableVO vo){
		return DaoFactory.getPaymentDao().updateAccountPayable(vo,AccountSql.ADD_AP);
		
	}
	
	/**
	 * �޸�Ӧ�����ָ����ŵļ�¼
	 * @return
	 */
	public boolean updateAccountPayable(AccountPayableVO vo){
		return DaoFactory.getPaymentDao().updateAccountPayable(vo,AccountSql.UP_AP);
	}
	
	/**
	 * ��ѯ�Ѹ����¼
	 * @return
	 */
	public Vector getAccountPaid(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_AP_PAID);
	}
	/**
	 * ��ѯ������Ѹ����¼
	 * @return
	 */
	public Vector getYearAccountPaid(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_APD_TY);
	}
	/**
	 * ��ѯ�ϸ����Ѹ����¼
	 * @return
	 */
	public Vector getLastMonthAccountPaid(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_APD_LM);
	}
	/**
	 * ��ѯ�����Ѹ����¼
	 * @return
	 */
	public Vector getThisMonthAccountPaid(){
		return DaoFactory.getPaymentDao().findAccountPayable(AccountSql.SELE_APD_TM);
	}
	
	/**
	 * ��ѯ�����̱�ָ�������̱�ż�¼
	 * @return
	 */
	public Vector getSuppliersBill(int supp_id){
		return DaoFactory.getPaymentDao().findSuppliersBill(supp_id);
	}
	
	/**
	 * ��ѯ�����������м�¼
	 * @return
	 */
	public Vector getPurchaseInBill(int pib_id ){
		return DaoFactory.getPaymentDao().findPurchaseInBill(pib_id);
	}
	
	/**
	 * �õ�Ԥ������ͷ
	 * @return
	 */
	public Vector getDespositPaymentTitle(){
		Vector dptitle=new Vector();
		dptitle.add("Ԥ������");
		dptitle.add("��Ʊ��");
		dptitle.add("��Ʊ����");
		dptitle.add("�����̱��");
		dptitle.add("Ԥ�����ܶ�");
		dptitle.add("��������");
		return dptitle;
	}
	
	/**
	 * �õ������̱��ͷ
	 * @return
	 */
	public Vector getSuppliersBillTitle(){
		Vector sbtitle=new Vector();
		sbtitle.add("�����̱��");
		sbtitle.add("ƴ������");
		sbtitle.add("���");
		sbtitle.add("����");
		sbtitle.add("��ַ");
		sbtitle.add("�ʱ�");
		sbtitle.add("����");
		sbtitle.add("�绰");
		sbtitle.add("����");
		sbtitle.add("������");
		sbtitle.add("�����ʺ�");
		sbtitle.add("�ⷿ��ַ");
		sbtitle.add("�ⷿ�绰");
		sbtitle.add("ҵ��Ա���");
		return sbtitle;
	}
	
	/**
	 * �õ�Ӧ������ͷ
	 * @return
	 */
	public Vector getAccountPayableTitle(){
		Vector aptitle=new Vector();
		aptitle.add("Ӧ������");
		aptitle.add("��Ʊ��");
		aptitle.add("��Ʊ����");
		aptitle.add("���������");
		aptitle.add("������");
		aptitle.add("�����̱��");
		aptitle.add("����");
		aptitle.add("��������");
		aptitle.add("Ӧ������");
		aptitle.add("��������");
		aptitle.add("��ϸ˵��");
		aptitle.add("״̬");
		aptitle.add("(��)Ԥ��");
		return aptitle;
	}
	
	/**
	 * �õ����������ͷ
	 * @return
	 */
	public Vector getPurchaseInBillTitle(){
		Vector pibtitle=new Vector();
		pibtitle.add("���������");
		pibtitle.add("�����̱��");
		pibtitle.add("��������");
		pibtitle.add("ҵ��Ա���");
		pibtitle.add("�Ƶ���");
		pibtitle.add("����Ա");
		pibtitle.add("����Ա");
		pibtitle.add("�������");
		return pibtitle;
	}
	
	
}
