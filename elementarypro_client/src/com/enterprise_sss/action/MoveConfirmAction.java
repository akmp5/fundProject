package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.socket.Receive;
import com.enterprise_sss.socket.Send;
import com.enterprise_sss.view.dialog.MoveConfirm;

public class MoveConfirmAction implements ActionListener {
	private MoveConfirm mc;
	private int nowRow=-1;//ѡ���е��±꣬δѡ����Ϊ-1
	
	public MoveConfirmAction(MoveConfirm mc){
		this.mc=mc;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		nowRow=mc.getTable().getSelectedRow();
		if("ȷ��".equals(command)){
			if(nowRow>=0){
				//��������˷���ȷ��,��ɾ���Ѿ��ɹ�ȷ�ϵļ�¼��������ʾ
				new Send((Vector)mc.getData().get(nowRow));
				if(new Receive().getData()!=null){
					System.out.println(nowRow);
					mc.getData().remove(nowRow);
					
					((DefaultTableModel)mc.getTable().getModel()).setDataVector(mc.getData(), new RetailServer().getAppliedTitle());
					mc.getTable().revalidate();
					
					JOptionPane.showMessageDialog(mc, "��ȷ�ϣ�");
				}else
					JOptionPane.showMessageDialog(mc, "ȷ��ʧ�ܣ�");
			}
		}else if("����".equals(command)){
			mc.dispose();
		}
	}

}
