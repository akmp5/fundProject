package com.enterprise_sss.action;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.enterprise_sss.socket.Receive;
import com.enterprise_sss.socket.Send;
import com.enterprise_sss.view.frame.RetailFrame;

/**
 * 零售管理窗体事件
 * @author yiguo
 *
 */
public class RetailWindAction extends WindowAdapter {
	private RetailFrame rf;//零售管理窗体
	
	public RetailWindAction(RetailFrame rf){
		this.rf=rf;
	}
	
	/**
	 * 窗口首次变为可见时调用。
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		rf.getClient().setText(rf.getUserVO().getUser());
		rf.getCashier().setText(rf.getUserVO().getUser());
	}
	
	/**
	 * 用户试图从窗口的系统菜单中关闭窗口时调用。
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		
		int i=JOptionPane.showConfirmDialog(null, "是否关闭", "系统提示", JOptionPane.YES_NO_OPTION);
		if(i==JOptionPane.YES_OPTION){
			//将日销售信息提交服务器
			new Send(rf.getSaleBill());
//			new Send(rf.getSaleItems());
			Vector data = rf.getSaleItems();
			for (int n = 0; n < data.size(); n++) {
				new Send((Vector)data.get(n));
			}
			//退出系统
			System.exit(0);
		}
	}
	
	/**
	 * 将 Window 设置为活动 Window 时调用。
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		rf.getOper().setText("销售状态");
	}

	/**
	 * 窗体调用dispose关闭事件
	 */
	@Override
	public void windowClosed(WindowEvent e) {
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		rf.getOper().setText("待销售状态");
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}


}
