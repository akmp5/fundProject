package com.enterprise_sss.action;

import java.awt.BorderLayout;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.Vector;

import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.view.frame.RetailFrame;

/**
 * �ı����¼�
 * @author yiguo
 *
 */
public class RetailTextAction implements TextListener {
	private RetailFrame rf;
	private RetailServer rs=new RetailServer();
	
	public RetailTextAction(RetailFrame rf){
		this.rf=rf;
	}
	
	/**
	 * �ı���ֵ�Ѹı�ʱ���á�
	 */
	public void textValueChanged(TextEvent e) {
	}

}
