package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.LoginServer;
import com.enterprise_sss.view.frame.Login;
import com.enterprise_sss.view.frame.MainFrame;
import com.enterprise_sss.vo.UserVO;

public class LoginAction implements ActionListener {
	
	private Login login;
	
	public LoginAction(Login login){
		this.login = login;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		UserVO uvo = new UserVO();
		uvo.setUser(login.getUserText().getText().trim());
		uvo.setPassword(login.getPwText().getText().trim());
		uvo.setLevel(login.getComboBox().getSelectedIndex());
		LoginServer ls = new LoginServer();
		if ("确定".equals(str)) {
			if (ls.isEmpty(uvo)) {
				if (ls.login(uvo)){
					ls.close();
					login.dispose();
					new MainFrame(uvo);
				} else {
					JOptionPane.showMessageDialog(null, "密码错误或者用户名不存在！");
				}
			} else {
				JOptionPane.showMessageDialog(null, "数据格式错误！！！！");
			}
		} else if ("取消".equals(str)) {
			System.exit(0);
		}
	}

}
