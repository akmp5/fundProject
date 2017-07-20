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
		if("确定".equals(command)){
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
					JOptionPane.showMessageDialog(sd, "所设日期无效，请重新设定（最大日期不得超过今天，也不得小于上一次结帐日期）！");
				}else{
//					System.out.println("---------------");
					if(cs.setDate(vo))
						JOptionPane.showMessageDialog(sd, "操作成功!");	
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
		}
	}

}
