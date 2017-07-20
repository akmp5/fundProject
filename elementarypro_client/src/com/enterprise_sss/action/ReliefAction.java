package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.enterprise_sss.view.dialog.Relief;

public class ReliefAction implements ActionListener {
	private Relief re;
	
	public ReliefAction(Relief re){
		this.re=re;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if("����".equals(command)){
			String cashier=JOptionPane.showInputDialog(re, "����Ա��");
			if(cashier!=null){
				re.getRf().getCashier().setText(cashier);
			}
			re.dispose();
		}else if("����".equals(command)){
			re.dispose();
		}else if("�˳�".equals(command)){
			System.exit(0);
		}
	}

}
