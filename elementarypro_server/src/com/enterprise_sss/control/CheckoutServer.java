package com.enterprise_sss.control;

import java.util.Vector;

import com.enterprise_sss.dao.factory.DaoFactory;
import com.enterprise_sss.dao.sql.CheckoutSql;
import com.enterprise_sss.util.DataUtil;
import com.enterprise_sss.util.LogOperator;
import com.enterprise_sss.vo.GatherVO;

/**
 * ������������
 * 
 * @author yiguo
 * @version 1.0
 */
public class CheckoutServer {
	/**
	 * �趨����
	 */
	public boolean setDate(GatherVO vo){
		boolean flag=false;
		for(int i=0;i<getCommId().size();i++){
			vo.setJg_id(getGatherId()+1);//���Ϊ���ڵ������+1
		}
//		System.out.println(getCommId());
		return DaoFactory.getCheckoutDao().insertJxcGhter(getCommId(), vo);
	}
	
	/**
	 * ģ����ʻ���
	 */
	public Vector getCheckout(String minDate,String maxDate){
		return DaoFactory.getCheckoutDao().findCheckout(getCommId(), minDate, maxDate);
//		LogOperator.writeMessageLog("�ɹ�", "", "ģ����ʻ���");
	}
	
	/**
	 * �ѽ��˵���С����
	 */
	public String getMinDate(){
		String minDate=DaoFactory.getCheckoutDao().findGatherDate(CheckoutSql.LASTDATE);
		if(minDate==null){
			minDate="2009-1-1";
		}
		return minDate;
	}
	
	/**
	 * �ѽ��˵��������
	 */
	public String getMaxDate(){
		String maxDate=DaoFactory.getCheckoutDao().findGatherDate(CheckoutSql.THISDATE);
		if(maxDate==null){
			maxDate="";
		}
		return maxDate;
	}
	
	/**
	 * ��ʽ���ʣ����������ݴ浵
	 */
	public boolean formalCheckout(Vector lastGather,Vector checkoutData){
		if (lastGather.size()!=checkoutData.size()){
			LogOperator.writeMessageLog("ʧ��", DataUtil.name, "��ʽ����");
			return false;
		}else{
			boolean b = DaoFactory.getCheckoutDao().updateJxcGather(lastGather, checkoutData);
			if (b) {
				LogOperator.writeMessageLog("�ɹ�", DataUtil.name, "��ʽ����");
			} else {
				LogOperator.writeMessageLog("ʧ��", DataUtil.name, "��ʽ����");
			}
			return b;
		}
	}
	
	/**
	 *  ��ѯ��������ܱ�������
	 */
	public int getGatherId(){
		Vector ids=DaoFactory.getCheckoutDao().findJxcGather(CheckoutSql.SELE_LID_JG);
		if(ids.size()==0){
			Vector i=new Vector();
			i.add(0);
			ids.add(i);
		}	
		
		return Integer.parseInt(((Vector)ids.get(0)).get(0).toString());
	}
	
	/**
	 * ��ѯ��Ʒ�嵥�����л�����
	 */
	public Vector getCommId(){
		return DaoFactory.getCheckoutDao().findCommodityBill(CheckoutSql.SELE_COMM);
	}
	
	
	/**
	 *  ��ѯ��������ܱ��ν��ʼ�¼
	 */
	public Vector getLastGather(){
		return DaoFactory.getCheckoutDao().findJxcGather(CheckoutSql.SELE_LAST_JG);
	}
	
	/**
	 *  ��ѯ��������ܱ����м�¼
	 */
	public Vector getGather(){
		return DaoFactory.getCheckoutDao().findJxcGather(CheckoutSql.SELE_JG);
	}
	
	/**
	 * ��������ܱ��ͷ
	 */
	public Vector<String> getImitateTitle(){
		Vector<String> gt=new Vector<String>();
		gt.add("������");
		gt.add("��ʼ����");
		gt.add("��ֹ����");
		gt.add("���ڽ������");
		gt.add("���ڽ����");
		gt.add("��������");
		gt.add("���۽��");
		gt.add("��������");
		gt.add("�������");
		gt.add("�������");
		gt.add("�����");
		gt.add("��������");
		gt.add("������");
		gt.add("���ڽ������");
		gt.add("���ڽ����");
		return gt;
	}
	/**
	 * ��������ܱ��ͷ
	 */
	public Vector<String> getGatherTitle(){
		Vector<String> gt=new Vector<String>();
		gt.add("���ʱ��");
		gt.add("��������");
		gt.add("������");
		gt.add("���ڽ������");
		gt.add("���ڽ����");
		gt.add("��������");
		gt.add("���۽��");
		gt.add("��������");
		gt.add("�������");
		gt.add("��������");
		gt.add("������");
		gt.add("���ڽ������");
		gt.add("���ڽ����");
		gt.add("��ע");
		return gt;
	}
}
