package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.LoginServer;
import com.enterprise_sss.view.dialog.RefPWDialog;
import com.enterprise_sss.vo.UserVO;

public class RefPWDialogAction implements ActionListener {

	private RefPWDialog dialog;
	
	public RefPWDialogAction (RefPWDialog dialog) {
		this.dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		UserVO uvo = new UserVO();
		uvo.setUser(dialog.getFrame().getUser().getUser());
		uvo.setLevel(dialog.getFrame().getUser().getLevel());
		LoginServer ls = new LoginServer();
		if (str.equals("确定")) {
			if (dialog.getPw().getText().equals(dialog.getPw1().getText())) {
				if (!dialog.getPw().getText().equals("")) {
					uvo.setPassword(dialog.getPw().getText());
					if (ls.modifyPw(uvo)) {
						JOptionPane.showMessageDialog(null, "密码修改成功");
						dialog.dispose();
					}
				}else{
					JOptionPane.showMessageDialog(null, "密码不能为空，请重新输入！");
				}
			} else {
				JOptionPane.showMessageDialog(null, "对不起，第二次密码输入有误，请重新输入！");
				dialog.getPw().setText("");
				dialog.getPw1().setText("");
			}

		}
	}

}
