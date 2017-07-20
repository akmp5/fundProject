package com.enterprise_sss.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import com.enterprise_sss.control.LoginServer;
import com.enterprise_sss.control.ThreadServer;
import com.enterprise_sss.dao.salemanagedao.SaleDialogDao;
import com.enterprise_sss.vo.UserVO;

public class ObjectServer implements Runnable {

	public synchronized void run() {
		while (true) {
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(8088);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			Socket s = null;

			try {
				s = ss.accept();
				// System.out.println("s : " + s);

				ObjectInputStream in = new ObjectInputStream(s.getInputStream());

				Object obj = in.readObject();

				UserVO uvo = null;
				
				s = new Socket(s.getInetAddress(), 8088);
				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

				LoginServer ls = new LoginServer();
				ThreadServer ts = new ThreadServer();
				
				if (obj instanceof UserVO) {
					uvo = (UserVO)obj;
					Vector data = null;
					if (ls.isEmpty(uvo)) {
						if (ls.login(uvo)) {
							ls.close();
							data = ts.threadLogin(uvo);
							out.writeObject(data);
						} else {
							out.writeObject(data);
						}
					} else {
						out.writeObject(data);
					}
				} else if (obj instanceof Vector) {
					Vector data = (Vector)obj;
					if (data.get(0) instanceof Vector) {
						for (int i = 0; i < data.size(); i++) {
							SaleDialogDao dao = new SaleDialogDao(data);
							dao.updateRowData();
						}
					} else{
						if (data.size() == 5) {
							Vector v = null;
							if (ts.threadApply(data)){
								v = new Vector();
								v.add(12452);
								out.writeObject(v);
							} else {
								out.writeObject(v);
							}
						} else if (data.size() == 6) {
							SaleDialogDao dao = new SaleDialogDao(data);
							dao.updateTextData();
						} else if (data.size() ==1) {
							Vector vd = ts.threadApplyConfirm(data);
							System.out.println(vd);
							out.writeObject(vd);
						} else if (data.size() == 8) {
							Vector vd = null;
							if (ts.threadApplyModify(data)){
								vd = new Vector();
								out.writeObject(vd);
							}else {
								out.writeObject(vd);
							}
						}
					}
				}
				ts.close();
				out.flush();
				out.close();
				in.close();
				s.close();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (!ss.isClosed()) {
					try {
						ss.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
