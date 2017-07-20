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
import com.enterprise_sss.view.dialog.accountPAndR.ReceivableManage;
import com.enterprise_sss.vo.AccountPayableVO;
import com.enterprise_sss.vo.AccountReceivableVO;


/**
 * �տDialog�¼�
 * @author yiguo
 *
 */
public class ReceivableManageAction extends MouseAdapter implements ActionListener {
	private ReceivableManage rm;
	private ReceivableServer rs=new ReceivableServer();//���ݴ�����
	private int nowRow=-1;//���ڱ�ʶ���µ��кţ����޸��£���Ϊ-1
	private Vector nowTable1Date=rs.getAccountReceivable();//��ǰtable1������
	
	public ReceivableManageAction(ReceivableManage rm){
		this.rm=rm;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		nowRow=rm.getTable1().getSelectedRow();//�õ�ѡ����
		
		if("ɾ������".equals(command)){
			if(nowRow!=-1){
				//����ѡ���еĵ�һ��(���)�����ݿ���ɾ����¼
				AccountReceivableVO arvo=new AccountReceivableVO();
				arvo.setAr_id(Integer.parseInt(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow, 0).toString()));
				rs.delAccountReceivable(arvo);
				nowTable1Date=rs.getAccountReceivable();
			}
			
		}else if("��ӿյ���".equals(command)){
			//Ϊ��ģ��׷��һ��Ĭ��ֵ���У�������һ�еı���ֶ���Ϊ���Ѵ��ڵ�����ż�1��
			AccountReceivableVO arvo=new AccountReceivableVO();
			int max=0,id=1;
			for(int i=0;i<rs.getAccountReceivable().size();i++){
				id=Integer.parseInt(((Vector)rs.getAccountReceivable().get(i)).get(0).toString());
				if(id>max)
					max=id;
			}
			arvo.setAr_id(max+1);
			rs.addAccountReceivable(arvo);
			nowTable1Date=rs.getAccountReceivable();
		
		}else if("�ύ����".equals(command)){
			if(nowRow!=-1){
//				ͨ��AccountReceivableVO��ֵ�������ݿ������,�����´����ݿ��еõ������Խ��б����ݵĸ���
				AccountReceivableVO arvo=new AccountReceivableVO();
				arvo.setAr_id(Integer.parseInt(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow, 0).toString()));
				arvo.setAr_inv(Integer.parseInt(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow, 1).toString()));
				arvo.setAr_inv_date(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow, 2).toString());
				arvo.setSb_id(Integer.parseInt(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow, 3).toString()));
				arvo.setComm_id(Integer.parseInt(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow, 4).toString()));
				arvo.setClie_id(Integer.parseInt(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow, 5).toString()));
				arvo.setAr_comm_amount(Integer.parseInt(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow,6).toString()));
				arvo.setAr_sale_price(new Double(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow,7).toString()));
				arvo.setAr_money(new Double(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow,8).toString()));
				arvo.setAr_date(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow,9).toString());
				arvo.setAr_desc(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow, 10).toString());
				arvo.setState(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow,11).toString());
				arvo.setDr_id(Integer.parseInt(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow, 12).toString()));
				if(rs.updateAccountReceivable(arvo)){
					JOptionPane.showMessageDialog(rm, "�ύ�ɹ���");
				}
					nowTable1Date=rs.getAccountReceivable();
			}
		}else if("����".equals(command)){
			//������ǰtable1������
			new ExportToExcel(rm,"Ӧ�տ�",rs.getAccountReceivableTitle(),nowTable1Date);
			
		}else if("��ӡ".equals(command)){
			//��ӡ��ǰtable1������
			try {
				rm.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		
		}else if("�˳�".equals(command)){
			rm.dispose();
		
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
		}
			rm.getTable1().setModel(new DefaultTableModel(nowTable1Date,rs.getAccountReceivableTitle()));
			rm.especialStyle();
			rm.getTable1().revalidate();//֧���Ƴٵ��Զ����֡�
	}
	
//	@Override
	public void mouseClicked(MouseEvent e) {
		//���ݹ��˰�ť��
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==rm.getButtons().get(4)){
			rm.getJpp().show(e.getComponent(), e.getX(), e.getY());
			rm.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=3,sb_id=1;
		
		//�õ�����Ϊ"���۵����"���к�
			for(int ci=0;ci<rm.getTable1().getColumnCount();ci++){
				if("���۵����".equals(rm.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=rm.getTable1().getSelectedRow();//�õ�ѡ����
			if(nowRow!=-1){//�õ�ѡ����,����Ϊ���۵���ŵ���,�ĵ�Ԫ��supp_id��ֵ
				sb_id=new Integer(rm.getTable1().getValueAt(nowRow, columnIndex).toString());
				rm.getTable2().setModel(new DefaultTableModel(rs.getSaleBill(sb_id),rs.getSaleBillTitle()));
				rm.getTable2().revalidate();
		}
		
	}
}
