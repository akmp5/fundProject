package com.enterprise_sss.control;

import java.util.Vector;

import sun.misc.Cleaner;

import com.enterprise_sss.dao.factory.DaoFactory;
import com.enterprise_sss.dao.sql.AccountSql;
import com.enterprise_sss.vo.AccountPayableVO;
import com.enterprise_sss.vo.AccountReceivableVO;
import com.enterprise_sss.vo.DepositReceivableVO;

/**
 * Ӧ�տ����ݷ�����
 * 
 * @author yiguo
 * @version 1.0
 */
public class ReceivableServer {

	/**
	 * ��ѯԤ�տ�����м�¼
	 * @return
	 */
	public Vector getDespositReceivable(){
		return DaoFactory.getReceivableDao().findDepositReceivable(AccountSql.SELE_DR);
	}
	
	/**
	 * ��ѯ�����Ԥ�տ�����м�¼
	 * @return
	 */
	public Vector getYearDespositReceivable(){
		return DaoFactory.getReceivableDao().findDepositReceivable(AccountSql.SELE_DR_TY);
	}
	/**
	 * ��ѯ�ϸ���Ԥ�տ�����м�¼
	 * @return
	 */
	public Vector getLastMonthDespositReceivable(){
		return DaoFactory.getReceivableDao().findDepositReceivable(AccountSql.SELE_DR_LM);
	}
	/**
	 * ��ѯ����Ԥ�տ�����м�¼
	 * @return
	 */
	public Vector getThisMonthDespositReceivable(){
		return DaoFactory.getReceivableDao().findDepositReceivable(AccountSql.SELE_DR_TM);
	}
	
	/**
	 * ɾ��Ԥ�տ��ָ����ż�¼
	 * @return
	 */
	public boolean delDespositReceivable(DepositReceivableVO vo){
		return DaoFactory.getReceivableDao().updateDepositReceivable(vo, AccountSql.DEL_DR);
	}
	
	/**
	 * ���Ԥ�տ��ָ����ż�¼
	 * @return
	 */
	public boolean addDespositReceivable(DepositReceivableVO vo){
		return DaoFactory.getReceivableDao().updateDepositReceivable(vo, AccountSql.ADD_DR);
	}
	
	/**
	 * �޸�Ԥ�տ��ָ����ż�¼
	 * @return
	 */
	public boolean updateDespositReceivable(DepositReceivableVO vo){
		return DaoFactory.getReceivableDao().updateDepositReceivable(vo, AccountSql.UP_DR);
	}
	
	/**
	 * ��ѯӦ�տ�����м�¼
	 * @return
	 */
	public Vector getAccountReceivable(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_AR);
	}
	
	/**
	 * ��ѯ�����Ӧ�տ���¼
	 * @return
	 */
	public Vector getYearAccountReceivable(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_AR_TY);
	}
	/**
	 * ��ѯ�ϸ���Ӧ�տ�����м�¼
	 * @return
	 */
	public Vector getLastMonthAccountReceivable(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_AR_LM);
	}
	/**
	 * ��ѯ����Ӧ�տ�����м�¼
	 * @return
	 */
	public Vector getThisMonthAccountReceivable(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_AR_TM);
	}
	
	/**
	 * ɾ��Ӧ�տ��ָ����ŵļ�¼
	 * @return
	 */
	public boolean delAccountReceivable(AccountReceivableVO vo){
		return DaoFactory.getReceivableDao().updateAccountReceivable(vo, AccountSql.DEL_AR);
	}
	
	/**
	 * ���Ӧ�տ��¼
	 * @return
	 */
	public boolean addAccountReceivable(AccountReceivableVO vo){
		return DaoFactory.getReceivableDao().updateAccountReceivable(vo,AccountSql.ADD_AR);
		
	}
	
	/**
	 * �޸�Ӧ�տ��ָ����ŵļ�¼
	 * @return
	 */
	public boolean updateAccountReceivable(AccountReceivableVO vo){
		return DaoFactory.getReceivableDao().updateAccountReceivable(vo,AccountSql.UP_AR);
	}
	
	/**
	 * ��ѯ���տ�����տ��¼
	 * @return
	 */
	public Vector getAccountReceived(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_AR_RECEIVED);
	}
	
	/**
	 * ��ѯ��������տ��¼
	 * @return
	 */
	public Vector getYearAccountReceived(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_ARD_TY);
	}
	/**
	 * ��ѯ�ϸ������տ��¼
	 * @return
	 */
	public Vector getLastMonthAccountReceived(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_ARD_LM);
	}
	/**
	 * ��ѯ�������տ��¼
	 * @return
	 */
	public Vector getThisMonthAccountReceived(){
		return DaoFactory.getReceivableDao().findAccountReceivable(AccountSql.SELE_ARD_TM);
	}
	
	/**
	 * ��ѯ�ͻ���ָ���ͻ���ŵļ�¼
	 * @return
	 */
	public Vector getClientBill(int clie_id){
		return DaoFactory.getReceivableDao().findClientBill(clie_id);
	}
	
	/**
	 * ��ѯ���۵���ָ�����۵���ŵļ�¼
	 * @return
	 */
	public Vector getSaleBill(int sb_id){
		return DaoFactory.getReceivableDao().findSaleBill(sb_id);
	}
	
	/**
	 * �õ�Ԥ�տ���ͷ
	 * @return
	 */
	public Vector getDespositReceivableTitle(){
		Vector drtitle=new Vector();
		drtitle.add("Ԥ�տ���");
		drtitle.add("��Ʊ��");
		drtitle.add("��Ʊ����");
		drtitle.add("�ͻ����");
		drtitle.add("Ԥ�տ��ܶ�");
		drtitle.add("�տ�����");
		return drtitle;
	}
	
	/**
	 * �õ��ͻ����ͷ
	 * @return
	 */
	public Vector getClientBillTitle(){
		Vector cbtitle=new Vector();
		cbtitle.add("�ͻ����");
		cbtitle.add("ƴ������");
		cbtitle.add("���");
		cbtitle.add("����");
		cbtitle.add("��ϵ��");
		cbtitle.add("��ַ");
		cbtitle.add("�ʱ�");
		cbtitle.add("�绰");
		cbtitle.add("����");
		cbtitle.add("������");
		cbtitle.add("�����ʺ�");
		cbtitle.add("����");
		cbtitle.add("ҵ��Ա���");
		cbtitle.add("���Ŷ��");
		return cbtitle;
	}
	
	/**
	 * �õ�Ӧ�տ���ͷ
	 * @return
	 */
	public Vector getAccountReceivableTitle(){
		Vector artitle=new Vector();
		artitle.add("Ӧ�տ���");
		artitle.add("��Ʊ��");
		artitle.add("��Ʊ����");
		artitle.add("���۵����");
		artitle.add("������");
		artitle.add("�ͻ����");
		artitle.add("����");
		artitle.add("���ۼ�");
		artitle.add("Ӧ�տ���");
		artitle.add("�տ�����");
		artitle.add("��ϸ˵��");
		artitle.add("״̬");
		artitle.add("(��)Ԥ��");
		return artitle;
	}
	
	/**
	 * �õ����۵����ͷ
	 * @return
	 */
	public Vector getSaleBillTitle(){
		Vector aotitle=new Vector();
		aotitle.add("���۵����");
		aotitle.add("�ͻ����");
		aotitle.add("��������");
		aotitle.add("ҵ��Ա���");
		aotitle.add("�Ʊ���");
		aotitle.add("����Ա");
		aotitle.add("���۶������");
		return aotitle;
	}
	
}
