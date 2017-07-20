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
 * 预付款管理Dialog事件
 * @author yiguo
 *		对预付款表（deposit_payment）中的记录进行操作
 */
public class DepositPaymentAction extends MouseAdapter implements ActionListener {
	private DepositPayment dp;
	private PaymentServer ps=new PaymentServer();//数据处理类
	private Vector nowTable1Date=ps.getDespositPayment();//当前table1的数据
	private int nowRow=-1;//用于标识更新的行号，若无更新，则为-1
	
	public DepositPaymentAction(DepositPayment dp){
		this.dp=dp;
	}
	
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		nowRow=dp.getTable1().getSelectedRow();//得到选定行
		if("删除单据".equals(command)){
			if(nowRow!=-1){
				//根据选定行的第一列(编号)在数据库中删除记录
				DepositPaymentVO vo=new DepositPaymentVO();
				vo.setDp_id(Integer.parseInt(dp.getTable1().getValueAt(nowRow, 0).toString()));
				ps.delDespositPayment(vo);
				nowTable1Date=ps.getDespositPayment();
			}
		
		}else if("添加空单据".equals(command)){
			//为表模型追加一带默认值的行，并将第一行的编号字段设为：已存在的最大编号加1。
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
		
		}else if("提交单据".equals(command)){
			if(nowRow!=-1){
				 //通过DepositPaymentVO传值更新数据库的数据,并重新从数据库中得到数据以进行表数据的更新
				DepositPaymentVO dpvo=new DepositPaymentVO();
				dpvo.setDp_id(Integer.parseInt(((DefaultTableModel)dp.getTable1().getModel()).getValueAt(nowRow, 0).toString()));
				dpvo.setDp_id(Integer.parseInt(dp.getTable1().getValueAt(nowRow, 0).toString()));
				dpvo.setDp_inv(Integer.parseInt(dp.getTable1().getValueAt(nowRow, 1).toString()));
				dpvo.setDp_inv_date(dp.getTable1().getValueAt(nowRow, 2).toString());
				dpvo.setSupp_id(Integer.parseInt(dp.getTable1().getValueAt(nowRow, 3).toString()));
				dpvo.setDp_money(new Double(dp.getTable1().getValueAt(nowRow, 4).toString()));
				dpvo.setDp_date(dp.getTable1().getValueAt(nowRow, 5).toString());
				if(ps.updateDespositPayment(dpvo)){
					JOptionPane.showMessageDialog(dp, "提交成功！");
				}
					nowTable1Date=ps.getDespositPayment();
			}
		
		}else if("导出".equals(command)){
//			导出当前table1的数据
			new ExportToExcel(dp,"预付款",ps.getDespositPaymentTitle(),nowTable1Date);
		
		}else if("打印".equals(command)){
			try {
//				打印当前table1的数据
				dp.getTable1().print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		
		}else if("退出".equals(command)){
			dp.dispose();
		
		}else {
			if("查询全部".equals(command)){
				nowTable1Date=ps.getDespositPayment();
			}else if("本年度记录".equals(command)){
				nowTable1Date=ps.getYearDespositPayment();
			}else if("上个月记录".equals(command)){
				nowTable1Date=ps.getLastMonthDespositPayment();
			}else if("本月记录".equals(command)){
				nowTable1Date=ps.getThisMonthDespositPayment();
			}
		}
		dp.getModel1().setDataVector(nowTable1Date, ps.getDespositPaymentTitle());
		dp.tableStyle();
		dp.getTable1().revalidate();//支持推迟的自动布局。
	}

//	@Override
	public void mouseClicked(MouseEvent e) {
//		单据过滤按钮单
		if(e.getButton()==MouseEvent.BUTTON1 & e.getComponent()==dp.getButtons().get(4)){
			dp.getJpp().show(e.getComponent(), e.getX(), e.getY());
			dp.getJpp().setForeground(Color.RED);
		}
		
		int columnIndex=5,supp_id=1;
		
		//得到列名为"供货商编号"的列号
			for(int ci=0;ci<dp.getTable1().getColumnCount();ci++){
				if("供货商编号".equals(dp.getTable1().getColumnName(ci))){
					columnIndex=ci;
					break;
				}
			}
			nowRow=dp.getTable1().getSelectedRow();//得到选定行
			if(nowRow!=-1){//得到选定行,列名为供货商编号的列,的单元格supp_id的值
			supp_id=new Integer(dp.getTable1().getValueAt(nowRow, columnIndex).toString());
			ps.getSuppliersBill(supp_id);
			dp.getTable2().setModel(new DefaultTableModel(ps.getSuppliersBill(supp_id),ps.getSuppliersBillTitle()));
			dp.getTable2().revalidate();
	
			}
	}
}
