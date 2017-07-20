package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.view.dialog.FreeBill;

public class FreeBillAction implements ActionListener {
	private FreeBill fb;
	private RetailServer rs=new RetailServer();//���۷�����
	private int nowRow=-1;//ѡ��������(��һ������Ϊ0)
	
	public FreeBillAction(FreeBill fb){
		this.fb=fb;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		nowRow=fb.getTable().getSelectedRow();//�õ�ѡ����
		if("���".equals(command)){
			/*
			 *1,����Ӧ�Ĺҵ���ϸ�˻ػ�ִ��
			 *2,�Ƴ�ѡ��Ĺҵ��Ͷ�Ӧ�Ĺҵ���ϸ������
			 */
			
			if(nowRow>=0){
//				Vector data=(Vector)fb.getRf().getHangItems().get(nowRow);
			fb.getRf().setData((Vector)fb.getRf().getHangItems().get(nowRow));
			
			fb.getRf().getHangBill().remove(nowRow);
			fb.getRf().getHangItems().remove(nowRow);
			fb.dispose();
			}
			
		}else if("ɾ��".equals(command)){
			/*
			 *�Ƴ�ѡ��Ĺҵ��Ͷ�����ʾӦ�Ĺҵ���ϸ����
			 */
			if(nowRow>=0){
				fb.getRf().getHangBill().remove(nowRow);
				fb.getRf().getHangItems().remove(nowRow);
				((DefaultTableModel)fb.getTable().getModel()).setDataVector(fb.getRf().getHangBill(), rs.getHangBillTitle());
				fb.getTable().revalidate();
			}
		}else if("����".equals(command)){
			
			fb.dispose();
		}
	}

}
