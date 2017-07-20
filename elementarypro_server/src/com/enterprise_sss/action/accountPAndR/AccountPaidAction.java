package com.enterprise_sss.action.accountPAndR;

import java.awt.Color;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.control.PaymentServer;
import com.enterprise_sss.view.dialog.accountPAndR.AccountPaid;
import com.enterprise_sss.view.dialog.accountPAndR.AccountReceived;
import com.enterprise_sss.view.dialog.accountPAndR.BaseAccount;


/**
 * ��ѯ�Ѹ�����ϸdialog�¼�
 * @author yiguo
 *		��Ӧ�����(account_payable)��״̬Ϊ���Ѹ���ļ�¼���в�ѯ
 */
public class AccountPaidAction extends MouseAdapter implements ActionListener{
	private AccountPaid ap;
	private PaymentServer ps=new PaymentServer();//���ݴ�����
	private Vector nowTable1Date=ps.getAccountPaid();//��ǰtable1������
	
	public AccountPaidAction(AccountPaid ap){
		this.ap=ap;
	}

//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("����".equals(command)){
			//������ǰtable1������
			new ExportToExcel(ap,"�Ѹ���",ps.getAccountPayableTitle(),nowTable1Date);
		
		}else if("��ӡ".equals(command)){
//			��ӡ��ǰtable1������
			try {
				ap.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}	
		
		}else if("�˳�".equals(command)){
			ap.dispose();
		
		}else{
			if("��ѯȫ��".equals(command)){
				nowTable1Date=ps.getAccountPaid();
			}else if("����ȼ�¼".equals(command)){
				nowTable1Date=ps.getYearAccountPaid();
			}else if("�ϸ��¼�¼".equals(command)){
				nowTable1Date=ps.getLastMonthAccountPaid();
			}else if("���¼�¼".equals(command)){
				nowTable1Date=ps.getThisMonthAccountPaid();
			}
			ap.getTable1().setModel(new DefaultTableModel(nowTable1Date,ps.getAccountPayableTitle()));
			ap.getTable1().revalidate();//֧���Ƴٵ��Զ����֡�
		}
	}
	

	
//	@Override
	public void mouseClicked(MouseEvent e) {
		//���ݹ��˰�ť��
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==ap.getButtons().get(4)){
			ap.getJpp().show(e.getComponent(), e.getX(), e.getY());
			ap.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,supp_id=1;
		
		//�õ�����Ϊ"�����̱��"���к�
		for(int ci=0;ci<ap.getTable1().getColumnCount();ci++){
			if("�����̱��".equals(ap.getTable1().getColumnName(ci))){
				columnIndex=ci;
				break;
			}
		}
		int nowRow=ap.getTable1().getSelectedRow();//�õ�ѡ����
		if(nowRow!=-1){//�õ�ѡ����,����Ϊ�����̱�ŵ���,�ĵ�Ԫ��supp_id��ֵ
			supp_id=new Integer(ap.getTable1().getValueAt(nowRow, columnIndex).toString());
			System.out.println(supp_id);
			ps.getSuppliersBill(supp_id);
			ap.getTable2().setModel(new DefaultTableModel(ps.getSuppliersBill(supp_id),ps.getSuppliersBillTitle()));
			ap.getTable2().revalidate();
		}	
	}
	
}




	

