package com.enterprise_sss.action.checkout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.enterprise_sss.view.dialog.checkout.Checkout;

public class CheckoutAction implements ActionListener {
	private Checkout co;
	
	public CheckoutAction(Checkout co){
		this.co=co;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if("½øÏú´æ»ã×Ü".equals(command)){
			co.tab.setEnabledAt(2, true);
			co.tab.setSelectedIndex(2);
		}
	}

}
