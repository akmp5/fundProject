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
import com.enterprise_sss.view.dialog.accountPAndR.ReceivableGather;


/**
 * 应收款汇总Dialog
 * @author yiguo
 *		用于查询企业所有的应收款(account_payable)，
 *		对应到每一个客户（client_bill）
 */
public class ReceivableGatherAction extends MouseAdapter implements ActionListener {
	private ReceivableGather rg;
	private ReceivableServer rs=new ReceivableServer();//数据处理类
	private Vector nowTable1Date=rs.getAccountReceivable();//当前table1的数据
	
	public ReceivableGatherAction(ReceivableGather rg){
		this.rg=rg;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();

		if("导出".equals(command)){
//			导出当前table1的数据
			new ExportToExcel(rg,"应收款",rs.getAccountReceivableTitle(),nowTable1Date);
		}else if("打印".equals(command)){
//			打印当前table1的数据
			try {
				rg.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}else if("退出".equals(command)){
			rg.dispose();
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
			rg.getTable1().setModel(new DefaultTableModel(nowTable1Date,rs.getAccountReceivableTitle()));
			rg.getTable1().revalidate();//支持推迟的自动布局。
		}
	}

	
//	@Override
	public void mouseClicked(MouseEvent e) {
		//单据过滤按钮单
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==rg.getButtons().get(4)){
			rg.getJpp().show(e.getComponent(), e.getX(), e.getY());
			rg.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,clie_id=1;
		
		//得到列名为"客户编号"的列号
			for(int ci=0;ci<rg.getTable1().getColumnCount();ci++){
				if("客户编号".equals(rg.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=rg.getTable1().getSelectedRow();//得到选定行
			if(nowRow!=-1){//得到选定行,列名为客户编号的列,的单元格supp_id的值
				clie_id=new Integer(rg.getTable1().getValueAt(nowRow, columnIndex).toString());
				rg.getTable2().setModel(new DefaultTableModel(rs.getClientBill(clie_id),rs.getClientBillTitle()));
				rg.getTable2().revalidate();
		}
		
	}
}
