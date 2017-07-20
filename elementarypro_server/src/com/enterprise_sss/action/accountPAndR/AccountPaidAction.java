package com.enterprise_sss.action.accountPAndR;

import java.awt.Color;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.table.DefaultTableModel;

import com.enterprise_sss.control.ExportToExcel;
import com.enterprise_sss.control.PaymentServer;
import com.enterprise_sss.view.dialog.accountPAndR.AccountPaid;
import com.enterprise_sss.view.dialog.accountPAndR.AccountReceived;
import com.enterprise_sss.view.dialog.accountPAndR.BaseAccount;


/**
 * 查询已付款明细dialog事件
 * @author yiguo
 *		对应付款表(account_payable)中状态为“已付款”的记录进行查询
 */
public class AccountPaidAction extends MouseAdapter implements ActionListener{
	private AccountPaid ap;
	private PaymentServer ps=new PaymentServer();//数据处理类
	private Vector nowTable1Date=ps.getAccountPaid();//当前table1的数据
	
	public AccountPaidAction(AccountPaid ap){
		this.ap=ap;
	}

//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("导出".equals(command)){
			//导出当前table1的数据
			new ExportToExcel(ap,"已付款",ps.getAccountPayableTitle(),nowTable1Date);
		
		}else if("打印".equals(command)){
//			打印当前table1的数据
			try {
				ap.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}	
		
		}else if("退出".equals(command)){
			ap.dispose();
		
		}else{
			if("查询全部".equals(command)){
				nowTable1Date=ps.getAccountPaid();
			}else if("本年度记录".equals(command)){
				nowTable1Date=ps.getYearAccountPaid();
			}else if("上个月记录".equals(command)){
				nowTable1Date=ps.getLastMonthAccountPaid();
			}else if("本月记录".equals(command)){
				nowTable1Date=ps.getThisMonthAccountPaid();
			}
			ap.getTable1().setModel(new DefaultTableModel(nowTable1Date,ps.getAccountPayableTitle()));
			ap.getTable1().revalidate();//支持推迟的自动布局。
		}
	}
	

	
//	@Override
	public void mouseClicked(MouseEvent e) {
		//单据过滤按钮单
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==ap.getButtons().get(4)){
			ap.getJpp().show(e.getComponent(), e.getX(), e.getY());
			ap.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,supp_id=1;
		
		//得到列名为"供货商编号"的列号
		for(int ci=0;ci<ap.getTable1().getColumnCount();ci++){
			if("供货商编号".equals(ap.getTable1().getColumnName(ci))){
				columnIndex=ci;
				break;
			}
		}
		int nowRow=ap.getTable1().getSelectedRow();//得到选定行
		if(nowRow!=-1){//得到选定行,列名为供货商编号的列,的单元格supp_id的值
			supp_id=new Integer(ap.getTable1().getValueAt(nowRow, columnIndex).toString());
			System.out.println(supp_id);
			ps.getSuppliersBill(supp_id);
			ap.getTable2().setModel(new DefaultTableModel(ps.getSuppliersBill(supp_id),ps.getSuppliersBillTitle()));
			ap.getTable2().revalidate();
		}	
	}
	
}




	

