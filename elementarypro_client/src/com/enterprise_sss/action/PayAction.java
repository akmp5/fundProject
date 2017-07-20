package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.view.dialog.Pay;

public class PayAction implements ActionListener {
	private Pay pay;
	
	public PayAction(Pay pay){
		this.pay=pay;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if("����".equals(command)){
			/*
			 * ����ִ�������ݴ������۵���ϸ
			 * ����ʵʱ���۶�
			 * ��ջ�ִ��
			 * ����
			 */
			if(Double.parseDouble(pay.getSpill().getText())<0){
				JOptionPane.showMessageDialog(pay, "����ʧ�ܣ�");
			}else{
				Vector row;
				for(int i=0;i<pay.getRf().getData().size();i++){
					row=new Vector();
					row.add(((Vector)pay.getRf().getData().get(i)).get(1));//��Ʒ���
					row.add(((Vector)pay.getRf().getData().get(i)).get(5));//����
					row.add(((Vector)pay.getRf().getData().get(i)).get(4));//����
					row.add(((Vector)pay.getRf().getData().get(i)).get(4));//����
					row.add(((Vector)pay.getRf().getComms().get(
							new RetailServer().getComm(pay.getRf().getComms(), 
									((Vector)pay.getRf().getData().get(i)).get(1)
									.toString()))).get(14));//����
					pay.getRf().getSaleItems().add(row);
				}
				pay.getRf().setSum(pay.getRf().getSum()+
						Double.parseDouble(pay.getDue().getText()));
				pay.getRf().setData(new Vector());
				pay.getRf().getRetailId().setText(new SimpleDateFormat("yyMMddHHmmss")
					.format(new Date(System.currentTimeMillis())));
				pay.dispose();
			}
		}else if("����".equals(command)){
			pay.dispose();
		}
	}

}
