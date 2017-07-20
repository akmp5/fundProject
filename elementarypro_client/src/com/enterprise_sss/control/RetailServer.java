package com.enterprise_sss.control;

import java.util.Vector;

public class RetailServer{
	
	/**
	 * ��ѯ��Ʒ��Ϣ������ָ����ŵ���Ʒ��Ϣ���ڼ��ϵ��±�
	 * @param comms ��Ʒ��Ϣ����
	 * @param commId ��Ʒ���
	 * @return ��û�ҵ�����-1
	 */
	public int getComm(Vector comms,String commId){
		int index=-1;
		for(int i=0;i<comms.size();i++){
			if(commId.trim().equals(((Vector)comms.get(i)).get(0).toString())){
				index=i;
			}
		}
		return index;
	}
	
	/**
	 * ��ѯ��Ʒ��Ϣ�����а���ָ���ַ�����Ʒ
	 * @param comms ������Ʒ��Ϣ����
	 * @param commId ָ���ַ�
	 * @return ��û�ҵ����ؿռ���
	 */
	public Vector getBeComm(Vector comms,String commId){
		Vector beComm=new Vector();
		Vector row;
		for(int i=0;i<comms.size();i++){
			if(((Vector)comms.get(i)).get(0).toString().indexOf(commId.trim())>=0){
				row=new Vector();
				row.add(((Vector)comms.get(i)).get(0));//��Ʒ���
				row.add(((Vector)comms.get(i)).get(2));//��Ʒ����
				row.add(((Vector)comms.get(i)).get(4));//���
				row.add(((Vector)comms.get(i)).get(5));//��λ
				row.add(((Vector)comms.get(i)).get(6));//����
				row.add(((Vector)comms.get(i)).get(8));//����
				row.add(((Vector)comms.get(i)).get(12));//�������
				beComm.add(row);
			}
		}
		return beComm;
	}
	
	/**
	 * �ܼ������ͽ��
	 * @return
	 */
	public Vector getSum(Vector statData){
		Vector ns=new Vector();
		double num=0;//�ܼ�����
		double sum=0;//�ܼƽ��
		for(int i=0;i<statData.size();i++){
			num=num+Double.parseDouble(((Vector)statData.get(i)).get(3).toString());
			sum=sum+Double.parseDouble(((Vector)statData.get(i)).get(4).toString());
		}
		ns.add(num);
		ns.add(sum);
		return ns;
	}
	
	/**
	 * ����������ϸ�õ�����ͳ�Ʊ�����
	 * @return
	 */
	public Vector getStatData(Vector comms,Vector saleItems){
		Vector data=new Vector();
		Vector row;
		for(int i=0;i<saleItems.size();i++){
			row=new Vector();
			row.add(((Vector)saleItems.get(i)).get(0));//��Ʒ���
			int j=getComm(comms,((Vector)saleItems.get(i)).get(0).toString());
			row.add(((Vector)comms.get(j)).get(2));//��Ʒ����
			double price=Double.parseDouble(((Vector)saleItems.get(i)).get(2).toString());
			double num=Double.parseDouble(((Vector)saleItems.get(i)).get(1).toString());
			row.add(price);//����
			row.add(num);//����
			row.add(price*num);//���
			data.add(row);
		}
		return data;
	}
	
	/**
	 * �����������ݱ�����
	 * @param comms
	 * @return
	 */
	public Vector getApplyData(Vector comms){
		Vector data=new Vector();
		Vector row;
		for(int i=0;i<comms.size();i++){
			row=new Vector();
			row.add(((Vector)comms.get(i)).get(0));//��Ʒ���
			row.add(((Vector)comms.get(i)).get(2));//��Ʒ����
			row.add(((Vector)comms.get(i)).get(4));//���
			row.add(((Vector)comms.get(i)).get(5));//��λ
			row.add(((Vector)comms.get(i)).get(6));//����
			row.add(((Vector)comms.get(i)).get(8));//����
			row.add(((Vector)comms.get(i)).get(12));//�������
			data.add(row);
		}
		return data;
	}
	
	/**
	 *  �ж��ַ����Ƿ��ת��Ϊdouble������
	 * @param text
	 * @return
	 */
	public boolean isDouble(String text){
		boolean flag=false;
		if(text.trim().matches("[0-9]+\56?[0-9]+")
				|text.matches("[0-9]+")
				|text.matches("[0-9]+\56?")
				|text.matches("\56?[0-9]+")){
			flag=true;
		}
		return flag;
	}
	/**
	 *  �ж��ַ����Ƿ��ת��Ϊint������
	 * @param text
	 * @return
	 */
	public boolean isInt(String text){
		boolean flag=false;
		if(text.trim().matches("[0-9]+")){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * �������������
	 * @return
	 */
	public Vector<String> getAppliedTitle(){
		Vector<String> title=new Vector<String>();
		title.add("������");
		title.add("�ŵ���");
		title.add("������");
		title.add("����");
		title.add("����");
		title.add("������");
		title.add("����˵��");
		title.add("�ظ�״̬");
		return title;
	}
	
	/**
	 * �����������ݱ�����
	 * @return
	 */
	public Vector<String> getApplyTitle(){
		Vector<String> title=new Vector<String>();
		title.add("��Ʒ���");
		title.add("��Ʒ����");
		title.add("���");
		title.add("��λ");
		title.add("����");
		title.add("����");
		title.add("�������");
		return title;
	}
	
	/**
	 * ����ͳ�Ʊ�����
	 * @return
	 */
	public Vector<String> getStatTitle(){
		Vector<String> title=new Vector<String>();
		title.add("��Ʒ���");
		title.add("��Ʒ����");
		title.add("����");
		title.add("����");
		title.add("���");
		return title;
	}
	
	/**
	 * ��ִ��������
	 * @return
	 */
	public Vector<String> getReceiptTitle(){
		Vector<String> title=new Vector<String>();
		title.add("���");
		title.add("��Ʒ���");
		title.add("��Ʒ����");
		title.add("��λ");
		title.add("����");
		title.add("����");
		title.add("���");
		return title;
	}
	
	/**
	 * �ҵ�������
	 * @return
	 */
	public Vector<String> getHangBillTitle(){
		Vector<String> title=new Vector<String>();
		title.add("����");
		title.add("��Ʒ����");
		return title;
	}
}
