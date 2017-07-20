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
import com.enterprise_sss.control.ReceivableServer;
import com.enterprise_sss.view.dialog.accountPAndR.DepositReceivable;
import com.enterprise_sss.vo.DepositPaymentVO;
import com.enterprise_sss.vo.DepositReceivableVO;


/**
 * Ԥ�տ����Dialog�¼�
 * @author yiguo
 *		��Ԥ�տdeposit_received���еļ�¼���в���
 */
public class DepositReceivableAction extends MouseAdapter implements ActionListener {
	private DepositReceivable dr;
	private ReceivableServer rs=new ReceivableServer();//���ݴ�����
	private Vector nowTable1Date=rs.getDespositReceivable();//��ǰtable1������
	private int nowRow=-1;//���ڱ�ʶ���µ��кţ����޸��£���Ϊ-1
	
	public DepositReceivableAction(DepositReceivable dr){
		this.dr=dr;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		nowRow=dr.getTable1().getSelectedRow();//�õ�ѡ����
			
		if("ɾ������".equals(command)){
			if(nowRow!=-1){
				//����ѡ���еĵ�һ��(���)�����ݿ���ɾ����¼
				DepositReceivableVO drvo=new DepositReceivableVO();
				drvo.setDr_id(Integer.parseInt(dr.getTable1().getValueAt(nowRow, 0).toString()));
				rs.delDespositReceivable(drvo);
				nowTable1Date=rs.getDespositReceivable();
			}
		
		}else if("��ӿյ���".equals(command)){
			//Ϊ��ģ��׷��һ��Ĭ��ֵ���У�������һ�еı���ֶ���Ϊ���Ѵ��ڵ�����ż�1��
			DepositReceivableVO drvo=new DepositReceivableVO();
			int max=0,id=1;
			for(int i=0;i<rs.getDespositReceivable().size();i++){
				id=Integer.parseInt(((Vector)rs.getDespositReceivable().get(i)).get(0).toString());
				if(id>max)
					max=id;
			}
			drvo.setDr_id(max+1);
			rs.addDespositReceivable(drvo);
			nowTable1Date=rs.getDespositReceivable();
		
		}else if("�ύ����".equals(command)){
			if(nowRow!=-1){
				 //ͨ��DepositReceivableVO��ֵ�������ݿ������,�����´����ݿ��еõ������Խ��б����ݵĸ���
				DepositReceivableVO drvo=new DepositReceivableVO();
				drvo.setDr_id(Integer.parseInt(((DefaultTableModel)dr.getTable1().getModel()).getValueAt(nowRow, 0).toString()));
				drvo.setDr_id(Integer.parseInt(dr.getTable1().getValueAt(nowRow, 0).toString()));
				drvo.setDr_inv(Integer.parseInt(dr.getTable1().getValueAt(nowRow, 1).toString()));
				drvo.setDr_inv_date(dr.getTable1().getValueAt(nowRow, 2).toString());
				drvo.setClie_id(Integer.parseInt(dr.getTable1().getValueAt(nowRow, 3).toString()));
				drvo.setCr_money(new Double(dr.getTable1().getValueAt(nowRow, 4).toString()));
				drvo.setDr_date(dr.getTable1().getValueAt(nowRow, 5).toString());
				if(rs.updateDespositReceivable(drvo)){
					JOptionPane.showMessageDialog(dr, "�ύ�ɹ���");
				}
					nowTable1Date=rs.getDespositReceivable();
			}
		
		}else if("����".equals(command)){
//			������ǰtable1������
			new ExportToExcel(dr,"Ԥ�տ�",rs.getDespositReceivableTitle(),nowTable1Date);
		
		}else if("��ӡ".equals(command)){
			try {
				dr.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}else if("�˳�".equals(command)){
			dr.dispose();
		}else {
			if("��ѯȫ��".equals(command)){
				nowTable1Date=rs.getDespositReceivable();
			}else if("����ȼ�¼".equals(command)){
				nowTable1Date=rs.getYearDespositReceivable();
			}else if("�ϸ��¼�¼".equals(command)){
				nowTable1Date=rs.getLastMonthDespositReceivable();
			}else if("���¼�¼".equals(command)){
				nowTable1Date=rs.getThisMonthDespositReceivable();
			}
		}
			dr.getTable1().setModel(new DefaultTableModel(nowTable1Date,rs.getDespositReceivableTitle()));
			dr.getTable1().revalidate();
		
	}

	
//	@Override
	public void mouseClicked(MouseEvent e) {
//		���ݹ��˰�ť��
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==dr.getButtons().get(4)){
			dr.getJpp().show(e.getComponent(), e.getX(), e.getY());
			dr.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,clie_id=1;
		
		//�õ�����Ϊ"�ͻ����"���к�
			for(int ci=0;ci<dr.getTable1().getColumnCount();ci++){
				if("�ͻ����".equals(dr.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=dr.getTable1().getSelectedRow();//�õ�ѡ����
			if(nowRow!=-1){//�õ�ѡ����,����Ϊ�ͻ���ŵ���,�ĵ�Ԫ��supp_id��ֵ
				clie_id=new Integer(dr.getTable1().getValueAt(nowRow, columnIndex).toString());
			dr.getTable2().setModel(new DefaultTableModel(rs.getClientBill(clie_id),rs.getClientBillTitle()));
			dr.getTable2().revalidate();
		}
		
	}
}
