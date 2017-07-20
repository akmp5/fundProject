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
import com.enterprise_sss.view.dialog.accountPAndR.PaymentGather;
import com.enterprise_sss.view.dialog.accountPAndR.PaymentManage;


/**
 * Ӧ�����ѯDialog�¼�
 * @author yiguo
 *		��Ӧ�����account_payable���еļ�¼���в�ѯ��
 *		��Ӧ��ÿһ�������̣�suppliers_bill��
 */
public class PaymentGatherAction extends MouseAdapter implements ActionListener {
	private PaymentGather pg;
	private PaymentServer ps=new PaymentServer();
	private Vector nowTable1Date=ps.getAccountPayable();//��ǰtable1������
	
	public PaymentGatherAction(PaymentGather pg){
		this.pg=pg;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("����".equals(command)){
			//������ǰtable1������
			new ExportToExcel(pg,"Ӧ����",ps.getAccountPayableTitle(),nowTable1Date);
		
		}else if("��ӡ".equals(command)){
			try {
				pg.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		
		}else if("�˳�".equals(command)){
			pg.dispose();
		
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
			pg.getTable1().setModel(new DefaultTableModel(nowTable1Date,ps.getAccountPayableTitle()));
			pg.getTable1().revalidate();//֧���Ƴٵ��Զ����֡�
		}
	}

//	@Override
	public void mouseClicked(MouseEvent e) {
		//���ݹ��˰�ť��
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==pg.getButtons().get(4)){
			pg.getJpp().show(e.getComponent(), e.getX(), e.getY());
			pg.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,supp_id=1;
		
		//�õ�����Ϊ"�����̱��"���к�
			for(int ci=0;ci<pg.getTable1().getColumnCount();ci++){
				if("�����̱��".equals(pg.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=pg.getTable1().getSelectedRow();//�õ�ѡ����
			if(nowRow!=-1){//�õ�ѡ����,����Ϊ�����̱�ŵ���,�ĵ�Ԫ��supp_id��ֵ
			supp_id=new Integer(pg.getTable1().getValueAt(nowRow, columnIndex).toString());
			ps.getSuppliersBill(supp_id);
			pg.getTable2().setModel(new DefaultTableModel(ps.getSuppliersBill(supp_id),ps.getSuppliersBillTitle()));
			pg.getTable2().revalidate();
		}
		
	}
}
