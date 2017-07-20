package com.enterprise_sss.action.accountPAndR;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.control.PaymentServer;
import com.enterprise_sss.view.dialog.accountPAndR.DepositPayment;
import com.enterprise_sss.vo.DepositPaymentVO;

/**
 * Ԥ�������Dialog�¼�
 * @author yiguo
 *		��Ԥ�����deposit_payment���еļ�¼���в���
 */
public class DepositPaymentAction extends MouseAdapter implements ActionListener {
	private DepositPayment dp;
	private PaymentServer ps=new PaymentServer();//���ݴ�����
	private Vector nowTable1Date=ps.getDespositPayment();//��ǰtable1������
	private int nowRow=-1;//���ڱ�ʶ���µ��кţ����޸��£���Ϊ-1
	
	public DepositPaymentAction(DepositPayment dp){
		this.dp=dp;
	}
	
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		nowRow=dp.getTable1().getSelectedRow();//�õ�ѡ����
		if("ɾ������".equals(command)){
			if(nowRow!=-1){
				//����ѡ���еĵ�һ��(���)�����ݿ���ɾ����¼
				DepositPaymentVO vo=new DepositPaymentVO();
				vo.setDp_id(Integer.parseInt(dp.getTable1().getValueAt(nowRow, 0).toString()));
				ps.delDespositPayment(vo);
				nowTable1Date=ps.getDespositPayment();
			}
		
		}else if("��ӿյ���".equals(command)){
			//Ϊ��ģ��׷��һ��Ĭ��ֵ���У�������һ�еı���ֶ���Ϊ���Ѵ��ڵ�����ż�1��
			DepositPaymentVO dpvo=new DepositPaymentVO();
			int max=0,id=1;
			for(int i=0;i<ps.getDespositPayment().size();i++){
				id=Integer.parseInt(((Vector)ps.getDespositPayment().get(i)).get(0).toString());
				if(id>max)
					max=id;
			}
			dpvo.setDp_id(max+1);
			ps.addDespositPayment(dpvo);
			nowTable1Date=ps.getDespositPayment();
		
		}else if("�ύ����".equals(command)){
			if(nowRow!=-1){
				 //ͨ��DepositPaymentVO��ֵ�������ݿ������,�����´����ݿ��еõ������Խ��б����ݵĸ���
				DepositPaymentVO dpvo=new DepositPaymentVO();
				dpvo.setDp_id(Integer.parseInt(((DefaultTableModel)dp.getTable1().getModel()).getValueAt(nowRow, 0).toString()));
				dpvo.setDp_id(Integer.parseInt(dp.getTable1().getValueAt(nowRow, 0).toString()));
				dpvo.setDp_inv(Integer.parseInt(dp.getTable1().getValueAt(nowRow, 1).toString()));
				dpvo.setDp_inv_date(dp.getTable1().getValueAt(nowRow, 2).toString());
				dpvo.setSupp_id(Integer.parseInt(dp.getTable1().getValueAt(nowRow, 3).toString()));
				dpvo.setDp_money(new Double(dp.getTable1().getValueAt(nowRow, 4).toString()));
				dpvo.setDp_date(dp.getTable1().getValueAt(nowRow, 5).toString());
				if(ps.updateDespositPayment(dpvo)){
					JOptionPane.showMessageDialog(dp, "�ύ�ɹ���");
				}
					nowTable1Date=ps.getDespositPayment();
			}
		
		}else if("����".equals(command)){
//			������ǰtable1������
			new ExportToExcel(dp,"Ԥ����",ps.getDespositPaymentTitle(),nowTable1Date);
		
		}else if("��ӡ".equals(command)){
			try {
//				��ӡ��ǰtable1������
				dp.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		
		}else if("�˳�".equals(command)){
			dp.dispose();
		
		}else {
			if("��ѯȫ��".equals(command)){
				nowTable1Date=ps.getDespositPayment();
			}else if("����ȼ�¼".equals(command)){
				nowTable1Date=ps.getYearDespositPayment();
			}else if("�ϸ��¼�¼".equals(command)){
				nowTable1Date=ps.getLastMonthDespositPayment();
			}else if("���¼�¼".equals(command)){
				nowTable1Date=ps.getThisMonthDespositPayment();
			}
		}
		dp.getModel1().setDataVector(nowTable1Date, ps.getDespositPaymentTitle());
		dp.tableStyle();
		dp.getTable1().revalidate();//֧���Ƴٵ��Զ����֡�
	}

//	@Override
	public void mouseClicked(MouseEvent e) {
//		���ݹ��˰�ť��
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==dp.getButtons().get(4)){
			dp.getJpp().show(e.getComponent(), e.getX(), e.getY());
			dp.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,supp_id=1;
		
		//�õ�����Ϊ"�����̱��"���к�
			for(int ci=0;ci<dp.getTable1().getColumnCount();ci++){
				if("�����̱��".equals(dp.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			nowRow=dp.getTable1().getSelectedRow();//�õ�ѡ����
			if(nowRow!=-1){//�õ�ѡ����,����Ϊ�����̱�ŵ���,�ĵ�Ԫ��supp_id��ֵ
			supp_id=new Integer(dp.getTable1().getValueAt(nowRow, columnIndex).toString());
			ps.getSuppliersBill(supp_id);
			dp.getTable2().setModel(new DefaultTableModel(ps.getSuppliersBill(supp_id),ps.getSuppliersBillTitle()));
			dp.getTable2().revalidate();
	
			}
	}
}
