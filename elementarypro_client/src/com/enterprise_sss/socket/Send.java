package com.enterprise_sss.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.enterprise_sss.vo.UserVO;

public class Send {
	private Object obj;
	public Send(Object obj){
		this.obj=obj;
		init();
	}
	
	public Send(){
		init();
	}
	
	
	public void init(){
		Socket s = null;
		try {
			s = new Socket("192.168.1.18",8088);
			
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			
			out.writeObject(obj); 
			out.flush();
			
		    out.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "无法连接服务器!");
		}finally{
			if(!s.isClosed() )
				try{s.close();}catch(Exception e){
				}
		}
	}
	
}
