package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.socket.Receive;
import com.enterprise_sss.socket.Send;
import com.enterprise_sss.view.dialog.MoveApply;

public class MoveApplyAction implements ActionListener {
	private MoveApply ma;
	private int nowRow=-1;//��ǰѡ���е��±�
	
	public MoveApplyAction(MoveApply ma){
		this.ma=ma;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if("ȷ��".equals(command)){
			nowRow=ma.getTable().getSelectedRow();//�õ���ǰѡ���е��±�
			if(nowRow>=0 & ma.getNum().getText().matches("[0-9]+")){
					Vector apply=new Vector();
					apply.add(ma.getTable().getValueAt(nowRow, 0));//������
					apply.add(ma.getNum().getText());//����
					apply.add(new Date(System.currentTimeMillis()));//����
					apply.add(ma.getRf().getClient().getText());//������
					String desc=ma.getDesc().getText();
					if(desc.length()==0)
						desc="��";
					apply.add(desc);//����˵��
					new Send(apply);//��������˷�������
					if(new Receive().getData()!=null){
						JOptionPane.showMessageDialog(ma, "����ɹ���");
					}else
						JOptionPane.showMessageDialog(ma, "����ʧ�ܣ�");
			}else
					JOptionPane.showMessageDialog(ma, "������Ч��");
		}else if("����".equals(command)){
			ma.dispose();
		}
	}

}
