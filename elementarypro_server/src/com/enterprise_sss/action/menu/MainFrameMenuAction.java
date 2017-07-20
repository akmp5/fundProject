package com.enterprise_sss.action.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import com.enterprise_sss.control.PaymentServer;
import com.enterprise_sss.control.ReceivableServer;
import com.enterprise_sss.control.ReportFormDaoServer;
import com.enterprise_sss.view.dialog.RefPWDialog;
import com.enterprise_sss.view.dialog.accountPAndR.AccountPaid;
import com.enterprise_sss.view.dialog.accountPAndR.AccountReceived;
import com.enterprise_sss.view.dialog.accountPAndR.DepositPayment;
import com.enterprise_sss.view.dialog.accountPAndR.DepositReceivable;
import com.enterprise_sss.view.dialog.accountPAndR.PaymentDetail;
import com.enterprise_sss.view.dialog.accountPAndR.PaymentGather;
import com.enterprise_sss.view.dialog.accountPAndR.PaymentManage;
import com.enterprise_sss.view.dialog.accountPAndR.ReceivableDetail;
import com.enterprise_sss.view.dialog.accountPAndR.ReceivableGather;
import com.enterprise_sss.view.dialog.accountPAndR.ReceivableManage;
import com.enterprise_sss.view.dialog.basic_data.ClientDataDialog;
import com.enterprise_sss.view.dialog.basic_data.CommodityDataDialog;
import com.enterprise_sss.view.dialog.basic_data.DepotDataDialog;
import com.enterprise_sss.view.dialog.basic_data.OperatorDataDialog;
import com.enterprise_sss.view.dialog.basic_data.PurchaseDataDialog;
import com.enterprise_sss.view.dialog.basic_data.SupplierDataDialog;
import com.enterprise_sss.view.dialog.checkout.Checkout;
import com.enterprise_sss.view.dialog.salemanage.OrderSaleDialog;
import com.enterprise_sss.view.dialog.salemanage.SaleBackDialog;
import com.enterprise_sss.view.dialog.salemanage.SaleDialog;
import com.enterprise_sss.view.dialog.testJFreeChar.ReportFormDialog;
import com.enterprise_sss.view.frame.MainFrame;
import com.enterprise_sss.view.panel.DataFindPanel;
import com.enterprise_sss.view.panel.LogsPanel;
import com.enterprise_sss.view.panel.depotmanage.CheckDepotPanel;
import com.enterprise_sss.view.panel.depotmanage.ProfitLossPanel;
import com.enterprise_sss.view.panel.depotmanage.QueryDepotPanel;
import com.enterprise_sss.view.panel.depotmanage.TransDepotBasePanel;
import com.enterprise_sss.view.panel.depotmanage.UpLowAlarmPanel;
import com.enterprise_sss.view.panel.depotmanage.UperLowerPanel;
import com.enterprise_sss.view.panel.maintenance.PurchaseInPanel;
import com.enterprise_sss.view.panel.maintenance.PurchaseOrderPanel;
import com.enterprise_sss.view.panel.salemanage.SaleTabbedPane;
import com.enterprise_sss.view.panel.salemanage.UpdateSalePanel;
import com.enterprise_sss.vo.TableVO;

/**
 * 总管理员界面的菜单事件类
 * 
 * @author Administrator
 * 
 */
public class MainFrameMenuAction implements ActionListener {

	private MainFrame frame;

	private PaymentServer ps = new PaymentServer();

	private ReceivableServer rs = new ReceivableServer();

	public MainFrameMenuAction(MainFrame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		TableVO tvo = new TableVO();
		if ("添加商品信息".equals(str)) {
			new CommodityDataDialog(1, str);
		} else if ("删除商品信息".equals(str)) {
			new CommodityDataDialog(3, str);
		} else if ("修改商品信息".equals(str)) {
			new CommodityDataDialog(4, str);
		} else if ("添加供货商信息".equals(str)) {
			new SupplierDataDialog(1, str);
		} else if ("删除供货商信息".equals(str)) {
			new SupplierDataDialog(3, str);
		} else if ("修改供货商信息".equals(str)) {
			new SupplierDataDialog(4, str);
		} else if ("添加客户信息".equals(str)) {
			new ClientDataDialog(1, str);
		} else if ("删除客户信息".equals(str)) {
			new ClientDataDialog(3, str);
		} else if ("修改客户信息".equals(str)) {
			new ClientDataDialog(4, str);
		} else if ("添加仓库信息".equals(str)) {
			new DepotDataDialog(1, str);
		} else if ("删除仓库信息".equals(str)) {
			new DepotDataDialog(3, str);
		} else if ("修改仓库信息".equals(str)) {
			new DepotDataDialog(4, str);
		} else if ("添加业务员信息".equals(str)) {
			new OperatorDataDialog(1, str);
		} else if ("删除业务员信息".equals(str)) {
			new OperatorDataDialog(3, str);
		} else if ("修改业务员信息".equals(str)) {
			new OperatorDataDialog(4, str);
		} else if ("添加采购合同信息".equals(str)) {
			new PurchaseDataDialog(1, str);
		} else if ("删除采购合同信息".equals(str)) {
			new PurchaseDataDialog(3, str);
		} else if ("修改采购合同信息".equals(str)) {
			new PurchaseDataDialog(4, str);
		} else if ("查询采购合同信息".equals(str) | "查询业务员信息".equals(str)
				| "查询商品信息".equals(str) | "查询供货商信息".equals(str)
				| "查询客户信息".equals(str) | "查询仓库信息".equals(str)
				| "资料查询".equals(str)) {
			String[] items = new String[] { "", "查询商品资料", "查询仓库资料", "查询采购合同资料",
					"查询客户资料", "查询业务员资料", "查询供货商资料" };
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(
					new DataFindPanel("资料查询", items, tvo));
		} else if ("采购订单".equals(str)) {
			Vector title = new Vector();
			title.add("订单编号");
			title.add("供货商编号");
			title.add("订货日期");
			title.add("有效起日");
			title.add("有效止日");
			title.add("业务员编号");
			title.add("制单人");
			tvo.setTitle(title);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new PurchaseOrderPanel(str, tvo));
		} else if ("进货单".equals(str) || "退货单".equals(str)) {
			Vector title = new Vector();
			title.add("进货单编号");
			title.add("供货商编号");
			title.add("进货日期");
			title.add("业务员编号");
			title.add("制单人");
			title.add("验收员");
			title.add("保管员");
			title.add("采购订单编号");
			tvo.setTitle(title);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new PurchaseInPanel(str, tvo));
		} else if ("采购统计".equals(str) || "销售统计".equals(str)) {
			int n = 1;
			if ("销售统计".equals(str)) {
				n = 2;
			}
			ReportFormDaoServer rs = new ReportFormDaoServer();
			Vector datas = rs.find(n, str);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new ReportFormDialog(str, datas));
			rs.close();
		} else if ("库存统计".equals(str)) {
			ReportFormDaoServer rs = new ReportFormDaoServer();
			Vector datas = rs.findAll();
			Vector d = rs.find(3, datas.get(0).toString());
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(
					new ReportFormDialog(3, str, datas, d));
			rs.close();
		} else if ("添加预付款".equals(str) | "修改预付款".equals(str)
				| "删除预付款".equals(str) | "查询预付款".equals(str)) {
			TableVO dptable = new TableVO(0, ps.getDespositPayment(), ps
					.getDespositPaymentTitle());
			TableVO sbtable = new TableVO(0, new Vector(), ps
					.getSuppliersBillTitle());
			DepositPayment dp = new DepositPayment("预付款管理", "预付款列表", dptable,
					"供货商详细信息", sbtable);

		} else if ("应付款查询".equals(str) | "付款单历史查询".equals(str)) {
			TableVO aptable = new TableVO(0, ps.getAccountPayable(), ps
					.getAccountPayableTitle());
			TableVO pibtable = new TableVO(0, new Vector(), ps
					.getPurchaseInBillTitle());
			PaymentDetail dp = new PaymentDetail("应付款查询", "应付款列表", aptable,
					"进货单详细信息", pibtable);

		} else if ("应付款汇总".equals(str)) {
			TableVO aptable = new TableVO(0, ps.getAccountPayable(), ps
					.getAccountPayableTitle());
			TableVO sbtable = new TableVO(0, new Vector(), ps
					.getSuppliersBillTitle());
			PaymentGather dp = new PaymentGather("应付款汇总", "应付款列表", aptable,
					"供货商详细信息", sbtable);

		} else if ("添加付款单".equals(str) | "修改付款单".equals(str)
				| "删除付款单".equals(str) | "查询付款单".equals(str)) {
			TableVO aptable = new TableVO(0, ps.getAccountPayable(), ps
					.getAccountPayableTitle());
			TableVO pibtable = new TableVO(0, new Vector(), ps
					.getPurchaseInBillTitle());
			PaymentManage dp = new PaymentManage("付款单管理", "付款单列表", aptable,
					"进货单详细信息", pibtable);

		} else if ("已付款查询".equals(str)) {
			TableVO aptable = new TableVO(0, ps.getAccountPaid(), ps
					.getAccountPayableTitle());
			TableVO sbtable = new TableVO(0, new Vector(), ps
					.getSuppliersBillTitle());
			AccountPaid dp = new AccountPaid("已付款查询", "已付款列表", aptable,
					"供货商详细信息", sbtable);

			// 第六个菜单:应收款管理
		} else if ("添加预收款".equals(str) | "修改预收款".equals(str)
				| "删除预收款".equals(str) | "查询预收款".equals(str)) {
			TableVO drtable = new TableVO(0, rs.getDespositReceivable(), rs
					.getDespositReceivableTitle());
			TableVO cbtable = new TableVO(0, new Vector(), rs
					.getClientBillTitle());
			DepositReceivable dp = new DepositReceivable("预收款管理", "预收款列表",
					drtable, "客户详细信息", cbtable);

		} else if ("应收款查询".equals(str) | "收款单历史查询".equals(str)) {
			TableVO artable = new TableVO(0, rs.getAccountReceivable(), rs
					.getAccountReceivableTitle());
			TableVO aotable = new TableVO(0, new Vector(), rs
					.getSaleBillTitle());
			ReceivableDetail dp = new ReceivableDetail("应收款查询", "应收款列表",
					artable, "销售单详细信息", aotable);

		} else if ("应收款汇总".equals(str)) {
			TableVO artable = new TableVO(0, rs.getAccountReceivable(), rs
					.getAccountReceivableTitle());
			TableVO cbtable = new TableVO(0, new Vector(), rs
					.getClientBillTitle());
			ReceivableGather dp = new ReceivableGather("应收款汇总", "应收款列表",
					artable, "客户详细信息", cbtable);

		} else if ("添加收款单".equals(str) | "修改收款单".equals(str)
				| "删除收款单".equals(str) | "查询收款单".equals(str)) {
			TableVO artable = new TableVO(0, rs.getAccountReceivable(), rs
					.getAccountReceivableTitle());
			TableVO aotable = new TableVO(0, new Vector(), rs
					.getSaleBillTitle());
			ReceivableManage dp = new ReceivableManage("收款单管理", "应收款列表",
					artable, "销售单详细信息", aotable);

		} else if ("已收款查询".equals(str)) {
			TableVO artable = new TableVO(0, rs.getAccountReceived(), rs
					.getAccountReceivableTitle());
			TableVO cbtable = new TableVO(0, new Vector(), rs
					.getClientBillTitle());
			AccountReceived dp = new AccountReceived("已收款查询", "已收款列表", artable,
					"客户详细信息", cbtable);

			// 第七个菜单:账务管理
		} else if ("结帐日期设定".equals(str)) {
			new Checkout(0);
		} else if ("结帐".equals(str)) {
			new Checkout(3);
		} else if ("进销存汇总".equals(str)) {
			new Checkout(1);
		} else if ("结帐历史查询".equals(str)) {
			new Checkout(2);
		} else if (str.equalsIgnoreCase("生成销售订单")) {
			OrderSaleDialog os = new OrderSaleDialog("销售订单");
		} else if (str.equalsIgnoreCase("生成销售单")) {
			SaleDialog os = new SaleDialog("销售单");
		} else if (str.equalsIgnoreCase("销售退货单")) {
			SaleBackDialog os = new SaleBackDialog("退货单");
		} else if (str.equalsIgnoreCase("调整历史售价")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UpdateSalePanel());
		} else if (str.equalsIgnoreCase("销售查询")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new SaleTabbedPane());
		} else if (str.equalsIgnoreCase("库存查询")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new QueryDepotPanel());
		} else if (str.equalsIgnoreCase("库存转库")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new TransDepotBasePanel());
		} else if (str.equalsIgnoreCase("库存盘点")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new CheckDepotPanel());
		} else if (str.equalsIgnoreCase("报损报溢")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new ProfitLossPanel());
		} else if (str.equalsIgnoreCase("上下限设定")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UperLowerPanel());
		} else if (str.equalsIgnoreCase("上下限报警")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UpLowAlarmPanel());
		} else if ("查看日志".equals(str)) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new LogsPanel());
		} else if ("更改密码".equals(str)) {
			new RefPWDialog(frame);
		}
	}

}
