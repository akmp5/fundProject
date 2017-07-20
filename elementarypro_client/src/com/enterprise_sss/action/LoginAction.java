package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.enterprise_sss.socket.Receive;
import com.enterprise_sss.socket.Send;
import com.enterprise_sss.view.frame.Login;
import com.enterprise_sss.view.frame.RetailFrame;
import com.enterprise_sss.vo.UserVO;

public class LoginAction implements ActionListener {
	
	private Login login;
	
	public LoginAction(Login login){
		this.login = login;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if ("ȷ��".equals(str)) {
			UserVO vo=new UserVO();
			vo.setUser(login.getUserText().getText());
			vo.setPassword(login.getPwText().getText());
			vo.setLevel(1);
			new Send(vo);
			Receive receive=new Receive();
			if(receive.getData()!=null){
				login.dispose();
				new RetailFrame(vo,receive.getData());
				
			}else{
				JOptionPane.showMessageDialog(login, "��¼ʧ�ܣ�");
			}
		} else if ("ȡ��".equals(str)) {
			System.exit(0);
		}
	}

}
