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
import com.enterprise_sss.view.dialog.accountPAndR.ReceivableGather;


/**
 * Ӧ�տ����Dialog
 * @author yiguo
 *		���ڲ�ѯ��ҵ���е�Ӧ�տ�(account_payable)��
 *		��Ӧ��ÿһ���ͻ���client_bill��
 */
public class ReceivableGatherAction extends MouseAdapter implements ActionListener {
	private ReceivableGather rg;
	private ReceivableServer rs=new ReceivableServer();//���ݴ�����
	private Vector nowTable1Date=rs.getAccountReceivable();//��ǰtable1������
	
	public ReceivableGatherAction(ReceivableGather rg){
		this.rg=rg;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();

		if("����".equals(command)){
//			������ǰtable1������
			new ExportToExcel(rg,"Ӧ�տ�",rs.getAccountReceivableTitle(),nowTable1Date);
		}else if("��ӡ".equals(command)){
//			��ӡ��ǰtable1������
			try {
				rg.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}else if("�˳�".equals(command)){
			rg.dispose();
		}else{
			if("��ѯȫ��".equals(command)){
				nowTable1Date=rs.getAccountReceivable();
			}else if("����ȼ�¼".equals(command)){
				nowTable1Date=rs.getYearAccountReceivable();
			}else if("�ϸ��¼�¼".equals(command)){
				nowTable1Date=rs.getLastMonthAccountReceivable();
			}else if("���¼�¼".equals(command)){
				nowTable1Date=rs.getThisMonthAccountReceivable();
			}
			rg.getTable1().setModel(new DefaultTableModel(nowTable1Date,rs.getAccountReceivableTitle()));
			rg.getTable1().revalidate();//֧���Ƴٵ��Զ����֡�
		}
	}

	
//	@Override
	public void mouseClicked(MouseEvent e) {
		//���ݹ��˰�ť��
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==rg.getButtons().get(4)){
			rg.getJpp().show(e.getComponent(), e.getX(), e.getY());
			rg.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,clie_id=1;
		
		//�õ�����Ϊ"�ͻ����"���к�
			for(int ci=0;ci<rg.getTable1().getColumnCount();ci++){
				if("�ͻ����".equals(rg.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=rg.getTable1().getSelectedRow();//�õ�ѡ����
			if(nowRow!=-1){//�õ�ѡ����,����Ϊ�ͻ���ŵ���,�ĵ�Ԫ��supp_id��ֵ
				clie_id=new Integer(rg.getTable1().getValueAt(nowRow, columnIndex).toString());
				rg.getTable2().setModel(new DefaultTableModel(rs.getClientBill(clie_id),rs.getClientBillTitle()));
				rg.getTable2().revalidate();
		}
		
	}
}
