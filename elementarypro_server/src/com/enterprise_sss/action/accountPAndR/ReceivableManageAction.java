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
 * 收款单Dialog事件
 * @author yiguo
 *
 */
public class ReceivableManageAction extends MouseAdapter implements ActionListener {
	private ReceivableManage rm;
	private ReceivableServer rs=new ReceivableServer();//数据处理类
	private int nowRow=-1;//用于标识更新的行号，若无更新，则为-1
	private Vector nowTable1Date=rs.getAccountReceivable();//当前table1的数据
	
	public ReceivableManageAction(ReceivableManage rm){
		this.rm=rm;
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		nowRow=rm.getTable1().getSelectedRow();//得到选定行
		
		if("删除单据".equals(command)){
			if(nowRow!=-1){
				//根据选定行的第一列(编号)在数据库中删除记录
				AccountReceivableVO arvo=new AccountReceivableVO();
				arvo.setAr_id(Integer.parseInt(((DefaultTableModel)rm.getTable1().getModel()).getValueAt(nowRow, 0).toString()));
				rs.delAccountReceivable(arvo);
				nowTable1Date=rs.getAccountReceivable();
			}
			
		}else if("添加空单据".equals(command)){
			//为表模型追加一带默认值的行，并将第一行的编号字段设为：已存在的最大编号加1。
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
		
		}else if("提交单据".equals(command)){
			if(nowRow!=-1){
//				通过AccountReceivableVO传值更新数据库的数据,并重新从数据库中得到数据以进行表数据的更新
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
					JOptionPane.showMessageDialog(rm, "提交成功！");
				}
					nowTable1Date=rs.getAccountReceivable();
			}
		}else if("导出".equals(command)){
			//导出当前table1的数据
			new ExportToExcel(rm,"应收款",rs.getAccountReceivableTitle(),nowTable1Date);
			
		}else if("打印".equals(command)){
			//打印当前table1的数据
			try {
				rm.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		
		}else if("退出".equals(command)){
			rm.dispose();
		
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
		}
			rm.getTable1().setModel(new DefaultTableModel(nowTable1Date,rs.getAccountReceivableTitle()));
			rm.especialStyle();
			rm.getTable1().revalidate();//支持推迟的自动布局。
	}
	
//	@Override
	public void mouseClicked(MouseEvent e) {
		//单据过滤按钮单
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==rm.getButtons().get(4)){
			rm.getJpp().show(e.getComponent(), e.getX(), e.getY());
			rm.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=3,sb_id=1;
		
		//得到列名为"销售单编号"的列号
			for(int ci=0;ci<rm.getTable1().getColumnCount();ci++){
				if("销售单编号".equals(rm.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			int nowRow=rm.getTable1().getSelectedRow();//得到选定行
			if(nowRow!=-1){//得到选定行,列名为销售单编号的列,的单元格supp_id的值
				sb_id=new Integer(rm.getTable1().getValueAt(nowRow, columnIndex).toString());
				rm.getTable2().setModel(new DefaultTableModel(rs.getSaleBill(sb_id),rs.getSaleBillTitle()));
				rm.getTable2().revalidate();
		}
		
	}
}
