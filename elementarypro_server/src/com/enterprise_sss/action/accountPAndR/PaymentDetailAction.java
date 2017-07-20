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
import com.enterprise_sss.control.PaymentServer;
import com.enterprise_sss.view.dialog.accountPAndR.PaymentDetail;
import com.enterprise_sss.view.dialog.accountPAndR.PaymentGather;


/**
 * Ӧ�����ѯDialog�¼�
 * @author yiguo
 *		��Ӧ�����account_payable���еļ�¼���в�ѯ��
 *		��Ӧ��ÿһ�ʽ���(purchase_in_bill)����ϸ
 */
public class PaymentDetailAction extends MouseAdapter implements ActionListener {
	private PaymentDetail pd;
	private PaymentServer ps=new PaymentServer();//���ݴ�����
	private Vector nowTable1Date=ps.getAccountPayable();//��ǰtable1������
	
	public PaymentDetailAction(PaymentDetail pd){
		this.pd=pd;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("����".equals(command)){
			//������ǰtable1������
			new ExportToExcel(pd,"Ӧ����",ps.getAccountPayableTitle(),nowTable1Date);
		
		}else if("��ӡ".equals(command)){
			try {
				pd.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		
		}else if("�˳�".equals(command)){
			pd.dispose();
		
		}else{
			if("��ѯȫ��".equals(command)){
				nowTable1Date=ps.getAccountPayable();
			}else if("����ȼ�¼".equals(command)){
				nowTable1Date=ps.getYearAccountPayable();
			}else if("�ϸ��¼�¼".equals(command)){
				nowTable1Date=ps.getLastMonthAccountPayable();
			}else if("���¼�¼".equals(command)){
				nowTable1Date=ps.getThisMonthAccountPayable();
			}
			pd.getTable1().setModel(new DefaultTableModel(nowTable1Date,ps.getAccountPayableTitle()));
			pd.getTable1().revalidate();//֧���Ƴٵ��Զ����֡�
		}
	}

//	@Override
	public void mouseClicked(MouseEvent e) {
		//���ݹ��˰�ť��
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==pd.getButtons().get(4)){
			pd.getJpp().show(e.getComponent(), e.getX(), e.getY());
			pd.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,pib_id =1;
		
		//�õ�����Ϊ"���������"���к�
			for(int ci=0;ci<pd.getTable1().getColumnCount();ci++){
				if("���������".equals(pd.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=pd.getTable1().getSelectedRow();//�õ�ѡ����
			if(nowRow!=-1){//�õ�ѡ����,����Ϊ��������ŵ���,�ĵ�Ԫ��pib_id��ֵ
				pib_id=new Integer(pd.getTable1().getValueAt(nowRow, columnIndex).toString());
			System.out.println(pib_id);
			ps.getSuppliersBill(pib_id);
			pd.getTable2().setModel(new DefaultTableModel(ps.getPurchaseInBill(pib_id),ps.getPurchaseInBillTitle()));
			pd.getTable2().revalidate();
		}
		
	}
}
