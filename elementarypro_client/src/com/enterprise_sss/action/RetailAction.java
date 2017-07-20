package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.RetailServer;
import com.enterprise_sss.socket.Receive;
import com.enterprise_sss.socket.Send;
import com.enterprise_sss.view.dialog.DailyStat;
import com.enterprise_sss.view.dialog.FreeBill;
import com.enterprise_sss.view.dialog.MoveApply;
import com.enterprise_sss.view.dialog.MoveConfirm;
import com.enterprise_sss.view.dialog.Pay;
import com.enterprise_sss.view.dialog.Relief;
import com.enterprise_sss.view.frame.RetailFrame;

public class RetailAction implements ActionListener {
	private RetailFrame rf;
	private RetailServer rs=new RetailServer();//���۷�����
	private int index=0;//��ǰ��ִ����¼��
	private int in=0;//ָ����ŵ���Ʒ��Ϣ���ڼ��ϵ��±�
	private Vector row;//��ʱ��ŵ�����¼
	
	public RetailAction(RetailFrame rf){
		this.rf=rf;
	}
	
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if("ȷ��".equals(command)){
			in=rs.getComm(rf.getComms(), rf.getCommId().getText());
			/*
			 * ����Ʒ��Ϣ������������Чʱ
			 * ����ִ����Ӽ�¼��������ʾ
			 */
			System.out.println(in);
			if(in>=0 & rs.isDouble(rf.getNum().getText())){
				System.out.println("aaa");
				row=new Vector();
				row.add(++index);//���
				row.add(rf.getCommId().getText());//��Ʒ���
				row.add(((Vector)rf.getComms().get(in)).get(2));//��Ʒ����
				row.add(((Vector)rf.getComms().get(in)).get(5));//��λ
				row.add(((Vector)rf.getComms().get(in)).get(9));//����
				row.add(rf.getNum().getText());//����
				row.add(Integer.parseInt(rf.getNum().getText())*Double.parseDouble
						(((Vector)rf.getComms().get(in)).get(9).toString()));//���=����*����
				rf.getData().add(row);

			}
		}else if("��ν���".equals(command)){
			//����Dialog
			new Relief(rf);
			
		}else if("ÿ��ͳ��".equals(command)){
			//ͳ��Dialog
			if(rf.getSaleItems().size()>0){
				new DailyStat(rf);
			}else
				JOptionPane.showMessageDialog(rf, "��ͳ�ƣ�");
			
		}else if("��������".equals(command)){
			//��������Dialog
			new MoveApply(rf);
			
		}else if("�������".equals(command)){
			//�����������ȷ�����󣬲������������
			Vector client=new Vector();
			client.add(rf.getClient().getText());
			new Send(client);
			Vector applied=new Receive().getData();
			System.out.println(applied);
			if(applied.size()>=0){
				//�������Dialog
				new MoveConfirm(rf,applied);
			}else
				JOptionPane.showMessageDialog(rf, "�����������ݣ�");
		}else if("�ҵ�".equals(command)){
			/*
			 * ����ִ����Ϊ��ʱ���ɹҵ���
			 * 1 ����ִ������ӵ�rf.getHangBill()����
			 * 2 ����ִ����ϸ��Ϣ��ӵ�rf.getHangItems()����
			 * 3 ��ջ�ִ��
			 */
			if(rf.getData().size()>0){
				Vector row=new Vector();
				row.add(rf.getRetailId().getText());//����
				String names="";
				for(int i=0;i<rf.getData().size();i++){
					names=names+" "+((Vector)rf.getData().get(i)).get(2);
				}
				row.add(names);//��Ʒ���ַ���
				rf.getHangBill().add(row);
				rf.getHangItems().add(rf.getData());
				rf.setData(new Vector());
				index=0;
				
				JOptionPane.showMessageDialog(rf, "�ҵ��ɹ���");
			}
			
		}else if("���".equals(command)){
			/*
			 * �йҵ�����ת�����Dialog
			 * ������ʾ�޹ҵ�
			 */
			if(rf.getHangBill().size()>0){
				
				//���Dialog
				new FreeBill(rf);
			}else
				JOptionPane.showMessageDialog(rf, "�޹ҵ���");
			
		}else if("����".equals(command)){
			//����Dialog
			if(rf.getData().size()>0){
				new Pay(rf);
				if(rf.getData().size()<=0){
					index=0;
				}
			}else{
				JOptionPane.showMessageDialog(rf, "�޵��ɸ���");
			}
		}else if("ȫ��".equals(command)){
			/*
			 * ��ջ�ִ�б�
			 */
			rf.setData(new Vector());
			index=0;
			
		}else if("��ӡ".equals(command)){
			if(rf.getTable1().getModel().getRowCount()>0){
				try {
					rf.getTable1().print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
			
		}else if("�˳�".equals(command)){
			int i=JOptionPane.showConfirmDialog(null, "�Ƿ�ر�", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION);
			if(i==JOptionPane.YES_OPTION){
				//����������Ϣ�ύ������
				new Send(rf.getSaleBill());
				Vector data = rf.getSaleItems();
				for (int n = 0; n < data.size(); n++) {
					new Send((Vector)data.get(n));
				}
			System.exit(0);
			}
		}
		/*
		 * ���»�ִ�����ܼƽ��rf.getAmount()��ֵ
		 */
		((DefaultTableModel)rf.getTable1().getModel()).setDataVector(rf.getData(), rs.getReceiptTitle());
		rf.getTable1().revalidate();
		if(rf.getData().size()>0){
			
			Double money=0d;
			for(int i=0;i<rf.getData().size();i++)
				money=money+Double.parseDouble(((Vector)rf.getData().get(i)).get(6).toString());
			rf.getAmount().setText(money.toString());
		}else
			rf.getAmount().setText("0");
	}

}
