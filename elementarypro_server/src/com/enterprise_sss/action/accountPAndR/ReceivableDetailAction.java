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
 * 应收款查询Dialog事件
 * @author yiguo
 *		对应收款表（account_payable）中的记录进行查询，
 *		对应到每一笔销售（sale_order）的明细
 */
public class ReceivableDetailAction extends MouseAdapter implements ActionListener {
	private ReceivableDetail rd;
	private ReceivableServer rs=new ReceivableServer();//数据处理类
	private Vector nowTable1Date=rs.getAccountReceivable();//当前table1的数据
	
	public ReceivableDetailAction(ReceivableDetail rd){
		this.rd=rd;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if("导出".equals(command)){
//			导出当前table1的数据
			new ExportToExcel(rd,"应收款",rs.getAccountReceivableTitle(),nowTable1Date);
		}else if("打印".equals(command)){
//			打印当前table1的数据
			try {
				rd.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}else if("退出".equals(command)){
			rd.dispose();
		}else{
			if("查询全部".equals(command)){
				nowTable1Date=rs.getAccountReceivable();
			}else if("本年度记录".equals(command)){
				nowTable1Date=rs.getYearAccountReceivable();
			}else if("上个月记录".equals(command)){
				nowTable1Date=rs.getLastMonthAccountReceivable();
			}else if("本月记录".equals(command)){
				nowTable1Date=rs.getThisMonthAccountReceivable();
			}
			rd.getTable1().setModel(new DefaultTableModel(nowTable1Date,rs.getAccountReceivableTitle()));
			rd.getTable1().revalidate();//支持推迟的自动布局。
		}
	}

	
//	@Override
	public void mouseClicked(MouseEvent e) {
		//单据过滤按钮单
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==rd.getButtons().get(4)){
			rd.getJpp().show(e.getComponent(), e.getX(), e.getY());
			rd.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=3,sb_id=1;
		
		//得到列名为"销售单编号"的列号
			for(int ci=0;ci<rd.getTable1().getColumnCount();ci++){
				if("销售单编号".equals(rd.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=rd.getTable1().getSelectedRow();//得到选定行
			if(nowRow!=-1){//得到选定行,列名为销售单编号的列,的单元格supp_id的值
				sb_id=new Integer(rd.getTable1().getValueAt(nowRow, columnIndex).toString());
				rd.getTable2().setModel(new DefaultTableModel(rs.getSaleBill(sb_id),rs.getSaleBillTitle()));
				rd.getTable2().revalidate();
		}
		
	}
}
