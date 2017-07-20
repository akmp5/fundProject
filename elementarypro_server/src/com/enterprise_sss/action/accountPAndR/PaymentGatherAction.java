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
 * 应付款查询Dialog事件
 * @author yiguo
 *		对应付款表（account_payable）中的记录进行查询，
 *		对应到每一个供货商（suppliers_bill）
 */
public class PaymentGatherAction extends MouseAdapter implements ActionListener {
	private PaymentGather pg;
	private PaymentServer ps=new PaymentServer();
	private Vector nowTable1Date=ps.getAccountPayable();//当前table1的数据
	
	public PaymentGatherAction(PaymentGather pg){
		this.pg=pg;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("导出".equals(command)){
			//导出当前table1的数据
			new ExportToExcel(pg,"应付款",ps.getAccountPayableTitle(),nowTable1Date);
		
		}else if("打印".equals(command)){
			try {
				pg.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		
		}else if("退出".equals(command)){
			pg.dispose();
		
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
			pg.getTable1().setModel(new DefaultTableModel(nowTable1Date,ps.getAccountPayableTitle()));
			pg.getTable1().revalidate();//支持推迟的自动布局。
		}
	}

//	@Override
	public void mouseClicked(MouseEvent e) {
		//单据过滤按钮单
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==pg.getButtons().get(4)){
			pg.getJpp().show(e.getComponent(), e.getX(), e.getY());
			pg.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,supp_id=1;
		
		//得到列名为"供货商编号"的列号
			for(int ci=0;ci<pg.getTable1().getColumnCount();ci++){
				if("供货商编号".equals(pg.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=pg.getTable1().getSelectedRow();//得到选定行
			if(nowRow!=-1){//得到选定行,列名为供货商编号的列,的单元格supp_id的值
			supp_id=new Integer(pg.getTable1().getValueAt(nowRow, columnIndex).toString());
			ps.getSuppliersBill(supp_id);
			pg.getTable2().setModel(new DefaultTableModel(ps.getSuppliersBill(supp_id),ps.getSuppliersBillTitle()));
			pg.getTable2().revalidate();
		}
		
	}
}
