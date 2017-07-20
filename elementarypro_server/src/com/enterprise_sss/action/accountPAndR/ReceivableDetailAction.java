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
import com.enterprise_sss.view.dialog.accountPAndR.AccountPaid;
import com.enterprise_sss.view.dialog.accountPAndR.ReceivableDetail;


/**
 * Ӧ�տ��ѯDialog�¼�
 * @author yiguo
 *		��Ӧ�տ��account_payable���еļ�¼���в�ѯ��
 *		��Ӧ��ÿһ�����ۣ�sale_order������ϸ
 */
public class ReceivableDetailAction extends MouseAdapter implements ActionListener {
	private ReceivableDetail rd;
	private ReceivableServer rs=new ReceivableServer();//���ݴ�����
	private Vector nowTable1Date=rs.getAccountReceivable();//��ǰtable1������
	
	public ReceivableDetailAction(ReceivableDetail rd){
		this.rd=rd;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("����".equals(command)){
//			������ǰtable1������
			new ExportToExcel(rd,"Ӧ�տ�",rs.getAccountReceivableTitle(),nowTable1Date);
		}else if("��ӡ".equals(command)){
//			��ӡ��ǰtable1������
			try {
				rd.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}else if("�˳�".equals(command)){
			rd.dispose();
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
			rd.getTable1().setModel(new DefaultTableModel(nowTable1Date,rs.getAccountReceivableTitle()));
			rd.getTable1().revalidate();//֧���Ƴٵ��Զ����֡�
		}
	}

	
//	@Override
	public void mouseClicked(MouseEvent e) {
		//���ݹ��˰�ť��
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==rd.getButtons().get(4)){
			rd.getJpp().show(e.getComponent(), e.getX(), e.getY());
			rd.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=3,sb_id=1;
		
		//�õ�����Ϊ"���۵����"���к�
			for(int ci=0;ci<rd.getTable1().getColumnCount();ci++){
				if("���۵����".equals(rd.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=rd.getTable1().getSelectedRow();//�õ�ѡ����
			if(nowRow!=-1){//�õ�ѡ����,����Ϊ���۵���ŵ���,�ĵ�Ԫ��supp_id��ֵ
				sb_id=new Integer(rd.getTable1().getValueAt(nowRow, columnIndex).toString());
				rd.getTable2().setModel(new DefaultTableModel(rs.getSaleBill(sb_id),rs.getSaleBillTitle()));
				rd.getTable2().revalidate();
		}
		
	}
}
