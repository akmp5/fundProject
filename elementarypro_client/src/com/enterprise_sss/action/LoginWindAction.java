package com.enterprise_sss.action;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginWindAction extends WindowAdapter {
	
	private JFrame frame;
	
	public LoginWindAction(JFrame frame){
		this.frame = frame;
	}

	public void windowClosing(WindowEvent e) {
		int i=JOptionPane.showConfirmDialog(null, "�Ƿ�ر�", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION);
		if(i==JOptionPane.YES_OPTION){
			System.exit(0);
		}

	}

}
