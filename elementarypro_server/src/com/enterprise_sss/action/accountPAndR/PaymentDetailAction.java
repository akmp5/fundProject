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
 * 应付款查询Dialog事件
 * @author yiguo
 *		对应付款表（account_payable）中的记录进行查询，
 *		对应到每一笔进货(purchase_in_bill)的明细
 */
public class PaymentDetailAction extends MouseAdapter implements ActionListener {
	private PaymentDetail pd;
	private PaymentServer ps=new PaymentServer();//数据处理类
	private Vector nowTable1Date=ps.getAccountPayable();//当前table1的数据
	
	public PaymentDetailAction(PaymentDetail pd){
		this.pd=pd;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("导出".equals(command)){
			//导出当前table1的数据
			new ExportToExcel(pd,"应付款",ps.getAccountPayableTitle(),nowTable1Date);
		
		}else if("打印".equals(command)){
			try {
				pd.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		
		}else if("退出".equals(command)){
			pd.dispose();
		
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
			pd.getTable1().setModel(new DefaultTableModel(nowTable1Date,ps.getAccountPayableTitle()));
			pd.getTable1().revalidate();//支持推迟的自动布局。
		}
	}

//	@Override
	public void mouseClicked(MouseEvent e) {
		//单据过滤按钮单
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==pd.getButtons().get(4)){
			pd.getJpp().show(e.getComponent(), e.getX(), e.getY());
			pd.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,pib_id =1;
		
		//得到列名为"进货单编号"的列号
			for(int ci=0;ci<pd.getTable1().getColumnCount();ci++){
				if("进货单编号".equals(pd.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=pd.getTable1().getSelectedRow();//得到选定行
			if(nowRow!=-1){//得到选定行,列名为进货单编号的列,的单元格pib_id的值
				pib_id=new Integer(pd.getTable1().getValueAt(nowRow, columnIndex).toString());
			System.out.println(pib_id);
			ps.getSuppliersBill(pib_id);
			pd.getTable2().setModel(new DefaultTableModel(ps.getPurchaseInBill(pib_id),ps.getPurchaseInBillTitle()));
			pd.getTable2().revalidate();
		}
		
	}
}
