package com.enterprise_sss.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Receive {

	private ServerSocket ss = null;
	private Vector data = null;
	
	public Receive(){
		try {
			ss = new ServerSocket(8088);
		} catch (IOException e) {
			System.out.println("Receive IOException");
			e.printStackTrace();
		}
		init();
	}
	
	public void init(){

		Socket s = null;

		try {
			s = ss.accept();

			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			Object obj = in.readObject();
			if (obj instanceof Vector) {
				data = (Vector)obj;
			}

			in.close();
			s.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "数据无法提交服务器!");
		} finally {
			if (!ss.isClosed()) {
				try {
					ss.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public Vector getData() {
		return data;
	}
	
}
