package com.enterprise_sss.action.accountPAndR;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.control.ReceivableServer;
import com.enterprise_sss.view.dialog.accountPAndR.AccountReceived;
import com.enterprise_sss.view.dialog.accountPAndR.PaymentDetail;


/**
 * 
 * ��ѯ���տ���ϸdialog�¼�
 * @author yiguo
 *		��Ӧ�տ��(account_receivable)�е�״̬Ϊ�����ա��ļ�¼���в�ѯ
 */

public class AccountReceivedAction extends MouseAdapter implements ActionListener {
	private AccountReceived ar;
	private ReceivableServer rs=new ReceivableServer();//���ݴ�����
	private Vector nowTable1Date=rs.getAccountReceived();//��ǰtable1������
	
	public AccountReceivedAction(AccountReceived ar){
		this.ar=ar;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("����".equals(command)){
//			������ǰtable1������
			new ExportToExcel(ar,"���տ�",rs.getAccountReceivableTitle(),nowTable1Date);
			
		}else if("��ӡ".equals(command)){
//			��ӡ��ǰtable1������
			try {
				ar.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}	
			
		}else if("�˳�".equals(command)){
			ar.dispose();
			
		}else{
			if("��ѯȫ��".equals(command)){
				nowTable1Date=rs.getAccountReceived();
			}else if("����ȼ�¼".equals(command)){
				nowTable1Date=rs.getYearAccountReceived();
			}else if("�ϸ��¼�¼".equals(command)){
				nowTable1Date=rs.getLastMonthAccountReceived();
			}else if("���¼�¼".equals(command)){
				nowTable1Date=rs.getThisMonthAccountReceived();
			}
			ar.getTable1().setModel(new DefaultTableModel(nowTable1Date,rs.getAccountReceivableTitle()));
			ar.getTable1().revalidate();//֧���Ƴٵ��Զ����֡�
		}
	}
	
//	@Override
	public void mouseClicked(MouseEvent e) {
//		���ݹ��˰�ť��
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==ar.getButtons().get(4)){
			ar.getJpp().show(e.getComponent(), e.getX(), e.getY());
			ar.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,clie_id=1;
		
		//�õ�����Ϊ"�ͻ����"���к�
			for(int ci=0;ci<ar.getTable1().getColumnCount();ci++){
				if("�ͻ����".equals(ar.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=ar.getTable1().getSelectedRow();//�õ�ѡ����
			if(nowRow!=-1){//�õ�ѡ����,����Ϊ�ͻ���ŵ���,�ĵ�Ԫ��supp_id��ֵ
				clie_id=new Integer(ar.getTable1().getValueAt(nowRow, columnIndex).toString());
			ar.getTable2().setModel(new DefaultTableModel(rs.getClientBill(clie_id),rs.getClientBillTitle()));
			ar.getTable2().revalidate();
		}
		
	}

}
