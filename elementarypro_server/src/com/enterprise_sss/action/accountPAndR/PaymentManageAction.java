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
 * 付款单Dialog事件
 * @author yiguo
 *
 */
public class PaymentManageAction extends MouseAdapter implements ActionListener {
	private PaymentManage pm;
	private PaymentServer ps=new PaymentServer();//数据处理类
	private int nowRow=-1;//用于标识更新的行号，若无更新，则为-1
	private Vector nowTable1Date=ps.getAccountPayable();//当前table1的数据
	
	public PaymentManageAction(PaymentManage pm){
		this.pm=pm;
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		nowRow=pm.getTable1().getSelectedRow();//得到选定行
		if("删除单据".equals(command)){
			if(nowRow!=-1){
				//根据选定行的第一列(编号)在数据库中删除记录
				AccountPayableVO apvo=new AccountPayableVO();
				apvo.setAp_id(Integer.parseInt(((DefaultTableModel)pm.getTable1().getModel()).getValueAt(nowRow, 0).toString()));
				ps.delAccountPayable(apvo);
				nowTable1Date=ps.getAccountPayable();
//				pm.getModel1().removeRow(nowRow);
			}
			
		}else if("添加空单据".equals(command)){
			//为表模型追加一带默认值的行，并将第一行的编号字段设为：已存在的最大编号加1。
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
		
		}else if("提交单据".equals(command)){
			if(nowRow!=-1){
//				通过AccountPayableVO传值更新数据库的数据,并重新从数据库中得到数据以进行表数据的更新
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
					JOptionPane.showMessageDialog(pm, "提交成功！");
				}
					nowTable1Date=ps.getAccountPayable();
			}
		}else if("导出".equals(command)){
			//导出当前table1的数据
			new ExportToExcel(pm,"应收款",ps.getAccountPayableTitle(),nowTable1Date);
			
		}else if("打印".equals(command)){
			//打印当前table1的数据
			try {
				pm.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		
		}else if("退出".equals(command)){
			pm.dispose();
		
		}else{
			if("查询全部".equals(command)){
				nowTable1Date=ps.getAccountPayable();
			}else if("本年度记录".equals(command)){
				nowTable1Date=ps.getYearAccountPayable();
			}else if("上个月记录".equals(command)){
				nowTable1Date=ps.getLastMonthAccountPayable();
			}else if("本月记录".equals(command)){
				nowTable1Date=ps.getThisMonthAccountPayable();
			}
		}
			pm.getTable1().setModel(new DefaultTableModel(nowTable1Date,ps.getAccountPayableTitle()));
			pm.especialStyle();
			pm.getTable1().revalidate();//支持推迟的自动布局。
	}
	
//	@Override
	public void mouseClicked(MouseEvent e) {
		//单据过滤按钮单
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==pm.getButtons().get(4)){
			pm.getJpp().show(e.getComponent(), e.getX(), e.getY());
			pm.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=3,pib_id =1;
		
		//得到列名为"进货单编号"的列号
			for(int ci=0;ci<pm.getTable1().getColumnCount();ci++){
				if("进货单编号".equals(pm.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=pm.getTable1().getSelectedRow();//得到选定行
			if(nowRow!=-1){//得到选定行,列名为进货单编号的列,的单元格pib_id的值
				pib_id=new Integer(pm.getTable1().getValueAt(nowRow, columnIndex).toString());
			ps.getSuppliersBill(pib_id);
			pm.getTable2().setModel(new DefaultTableModel(ps.getPurchaseInBill(pib_id),ps.getPurchaseInBillTitle()));
			pm.getTable2().revalidate();
		}
		
	}
}
