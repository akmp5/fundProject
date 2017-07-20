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
 * 预收款管理Dialog事件
 * @author yiguo
 *		对预收款（deposit_received）中的记录进行操作
 */
public class DepositReceivableAction extends MouseAdapter implements ActionListener {
	private DepositReceivable dr;
	private ReceivableServer rs=new ReceivableServer();//数据处理类
	private Vector nowTable1Date=rs.getDespositReceivable();//当前table1的数据
	private int nowRow=-1;//用于标识更新的行号，若无更新，则为-1
	
	public DepositReceivableAction(DepositReceivable dr){
		this.dr=dr;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		nowRow=dr.getTable1().getSelectedRow();//得到选定行
			
		if("删除单据".equals(command)){
			if(nowRow!=-1){
				//根据选定行的第一列(编号)在数据库中删除记录
				DepositReceivableVO drvo=new DepositReceivableVO();
				drvo.setDr_id(Integer.parseInt(dr.getTable1().getValueAt(nowRow, 0).toString()));
				rs.delDespositReceivable(drvo);
				nowTable1Date=rs.getDespositReceivable();
			}
		
		}else if("添加空单据".equals(command)){
			//为表模型追加一带默认值的行，并将第一行的编号字段设为：已存在的最大编号加1。
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
		
		}else if("提交单据".equals(command)){
			if(nowRow!=-1){
				 //通过DepositReceivableVO传值更新数据库的数据,并重新从数据库中得到数据以进行表数据的更新
				DepositReceivableVO drvo=new DepositReceivableVO();
				drvo.setDr_id(Integer.parseInt(((DefaultTableModel)dr.getTable1().getModel()).getValueAt(nowRow, 0).toString()));
				drvo.setDr_id(Integer.parseInt(dr.getTable1().getValueAt(nowRow, 0).toString()));
				drvo.setDr_inv(Integer.parseInt(dr.getTable1().getValueAt(nowRow, 1).toString()));
				drvo.setDr_inv_date(dr.getTable1().getValueAt(nowRow, 2).toString());
				drvo.setClie_id(Integer.parseInt(dr.getTable1().getValueAt(nowRow, 3).toString()));
				drvo.setCr_money(new Double(dr.getTable1().getValueAt(nowRow, 4).toString()));
				drvo.setDr_date(dr.getTable1().getValueAt(nowRow, 5).toString());
				if(rs.updateDespositReceivable(drvo)){
					JOptionPane.showMessageDialog(dr, "提交成功！");
				}
					nowTable1Date=rs.getDespositReceivable();
			}
		
		}else if("导出".equals(command)){
//			导出当前table1的数据
			new ExportToExcel(dr,"预收款",rs.getDespositReceivableTitle(),nowTable1Date);
		
		}else if("打印".equals(command)){
			try {
				dr.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}else if("退出".equals(command)){
			dr.dispose();
		}else {
			if("查询全部".equals(command)){
				nowTable1Date=rs.getDespositReceivable();
			}else if("本年度记录".equals(command)){
				nowTable1Date=rs.getYearDespositReceivable();
			}else if("上个月记录".equals(command)){
				nowTable1Date=rs.getLastMonthDespositReceivable();
			}else if("本月记录".equals(command)){
				nowTable1Date=rs.getThisMonthDespositReceivable();
			}
		}
			dr.getTable1().setModel(new DefaultTableModel(nowTable1Date,rs.getDespositReceivableTitle()));
			dr.getTable1().revalidate();
		
	}

	
//	@Override
	public void mouseClicked(MouseEvent e) {
//		单据过滤按钮单
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==dr.getButtons().get(4)){
			dr.getJpp().show(e.getComponent(), e.getX(), e.getY());
			dr.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,clie_id=1;
		
		//得到列名为"客户编号"的列号
			for(int ci=0;ci<dr.getTable1().getColumnCount();ci++){
				if("客户编号".equals(dr.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=dr.getTable1().getSelectedRow();//得到选定行
			if(nowRow!=-1){//得到选定行,列名为客户编号的列,的单元格supp_id的值
				clie_id=new Integer(dr.getTable1().getValueAt(nowRow, columnIndex).toString());
			dr.getTable2().setModel(new DefaultTableModel(rs.getClientBill(clie_id),rs.getClientBillTitle()));
			dr.getTable2().revalidate();
		}
		
	}
}
