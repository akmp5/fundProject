package com.enterprise_sss.action.accountPAndR;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.control.PaymentServer;
import com.enterprise_sss.view.dialog.accountPAndR.PaymentManage;
import com.enterprise_sss.view.dialog.accountPAndR.ReceivableGather;
import com.enterprise_sss.vo.AccountPayableVO;
import com.enterprise_sss.vo.DepositPaymentVO;


/**
 * ���Dialog�¼�
 * @author yiguo
 *
 */
public class PaymentManageAction extends MouseAdapter implements ActionListener {
	private PaymentManage pm;
	private PaymentServer ps=new PaymentServer();//���ݴ�����
	private int nowRow=-1;//���ڱ�ʶ���µ��кţ����޸��£���Ϊ-1
	private Vector nowTable1Date=ps.getAccountPayable();//��ǰtable1������
	
	public PaymentManageAction(PaymentManage pm){
		this.pm=pm;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		nowRow=pm.getTable1().getSelectedRow();//�õ�ѡ����
		if("ɾ������".equals(command)){
			if(nowRow!=-1){
				//����ѡ���еĵ�һ��(���)�����ݿ���ɾ����¼
				AccountPayableVO apvo=new AccountPayableVO();
				apvo.setAp_id(Integer.parseInt(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow, 0).toString()));
				ps.delAccountPayable(apvo);
				nowTable1Date=ps.getAccountPayable();
//				pm.getModel1().removeRow(nowRow);
			}
			
		}else if("��ӿյ���".equals(command)){
			//Ϊ��ģ��׷��һ��Ĭ��ֵ���У�������һ�еı���ֶ���Ϊ���Ѵ��ڵ�����ż�1��
			AccountPayableVO apvo=new AccountPayableVO();
			int max=0,id=1;
			for(int i=0;i<ps.getAccountPayable().size();i++){
				id=Integer.parseInt(((Vector)ps.getAccountPayable().get(i)).get(0).toString());
				if(id>max)
					max=id;
			}
			apvo.setAp_id(max+1);
			ps.addAccountPayable(apvo);
			
			nowTable1Date=ps.getAccountPayable();
		
		}else if("�ύ����".equals(command)){
			if(nowRow!=-1){
//				ͨ��AccountPayableVO��ֵ�������ݿ������,�����´����ݿ��еõ������Խ��б����ݵĸ���
				AccountPayableVO apvo=new AccountPayableVO();
				apvo.setAp_id(Integer.parseInt(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow, 0).toString()));
				apvo.setAp_inv(Integer.parseInt(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow, 1).toString()));
				apvo.setAp_inv_date(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow, 2).toString());
				apvo.setPib_id(Integer.parseInt(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow, 3).toString()));
				apvo.setComm_id(Integer.parseInt(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow, 4).toString()));
				apvo.setSupp_id(Integer.parseInt(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow, 5).toString()));
				apvo.setAp_comm_amount(Integer.parseInt(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow,6).toString()));
				apvo.setAp_purchase_price(new Double(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow,7).toString()));
				apvo.setAp_money(new Double(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow,8).toString()));
				apvo.setAp_date(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow,9).toString());
				apvo.setAp_desc(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow, 10).toString());
				apvo.setState(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow,11).toString());
				apvo.setDp_id(Integer.parseInt(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow, 12).toString()));
				if(ps.updateAccountPayable(apvo)){
					JOptionPane.showMessageDialog(pm, "�ύ�ɹ���");
				}
					nowTable1Date=ps.getAccountPayable();
			}
		}else if("����".equals(command)){
			//������ǰtable1������
			new ExportToExcel(pm,"Ӧ�տ�",ps.getAccountPayableTitle(),nowTable1Date);
			
		}else if("��ӡ".equals(command)){
			//��ӡ��ǰtable1������
			try {
				pm.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		
		}else if("�˳�".equals(command)){
			pm.dispose();
		
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
		}
			pm.getTable1().setModel(new DefaultTableModel(nowTable1Date,ps.getAccountPayableTitle()));
			pm.especialStyle();
			pm.getTable1().revalidate();//֧���Ƴٵ��Զ����֡�
	}
	
//	@Override
	public void mouseClicked(MouseEvent e) {
		//���ݹ��˰�ť��
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==pm.getButtons().get(4)){
			pm.getJpp().show(e.getComponent(), e.getX(), e.getY());
			pm.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=3,pib_id =1;
		
		//�õ�����Ϊ"���������"���к�
			for(int ci=0;ci<pm.getTable1().getColumnCount();ci++){
				if("���������".equals(pm.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=pm.getTable1().getSelectedRow();//�õ�ѡ����
			if(nowRow!=-1){//�õ�ѡ����,����Ϊ��������ŵ���,�ĵ�Ԫ��pib_id��ֵ
				pib_id=new Integer(pm.getTable1().getValueAt(nowRow, columnIndex).toString());
			ps.getSuppliersBill(pib_id);
			pm.getTable2().setModel(new DefaultTableModel(ps.getPurchaseInBill(pib_id),ps.getPurchaseInBillTitle()));
			pm.getTable2().revalidate();
		}
		
	}
}
