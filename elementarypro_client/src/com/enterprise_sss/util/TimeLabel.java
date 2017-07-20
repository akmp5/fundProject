package com.enterprise_sss.util;

import java.awt.Color;
import java.awt.Graphics;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimeLabel extends JLabel implements Runnable{
//	private JFrame frame;
//	public TimeLabel(JFrame frame){
	public TimeLabel(){
//		this.frame=frame;
		paintLabel();
		new Thread(this).start();
	
	}
	
	public void paintLabel(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		Date time = null;
		String strTime = null;
		try {
			time = sdf.parse("0000-00-00  00:00:00");
			time.setTime(System.currentTimeMillis());
			strTime = sdf.format(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.setForeground(Color.white);
		this.setForeground(Color.blue);
		this.setText(strTime);
	}

	
//	@Override
	public void run() {
//		while(frame.isEnabled())
		while(true)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			paintLabel();
		}
	}
}
