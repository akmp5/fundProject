package com.enterprise_sss.action.checkout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.CheckoutServer;
import com.enterprise_sss.view.panel.checkout.SetDate;
import com.enterprise_sss.vo.GatherVO;

public class SetDateAction implements ActionListener {
	private SetDate sd;
	private CheckoutServer cs=new CheckoutServer();
	
	public SetDateAction(SetDate sd){
		this.sd=sd;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if("ȷ��".equals(command)){
			GatherVO vo=new GatherVO();
			vo.setJg_date(sd.getDate().getSelectedItem().toString());
			Date date,dateLow;
			try {
				date = DateFormat.getDateInstance().parse(vo.getJg_date());
				dateLow = DateFormat.getDateInstance().parse(cs.getMinDate());
//				System.out.println("vvvvvvv"+date);
//				System.out.println("vvvvvvv"+dateLow);
//				System.out.println(date);
				if(date.after(new Date()) | date.before(dateLow)){
					JOptionPane.showMessageDialog(sd, "����������Ч���������趨��������ڲ��ó������죬Ҳ����С����һ�ν������ڣ���");
				}else{
//					System.out.println("---------------");
					if(cs.setDate(vo))
						JOptionPane.showMessageDialog(sd, "�����ɹ�!");	
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
		}
	}

}
