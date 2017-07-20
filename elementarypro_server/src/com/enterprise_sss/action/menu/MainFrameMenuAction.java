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
 * �ܹ���Ա����Ĳ˵��¼���
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
		if ("�����Ʒ��Ϣ".equals(str)) {
			new CommodityDataDialog(1, str);
		} else if ("ɾ����Ʒ��Ϣ".equals(str)) {
			new CommodityDataDialog(3, str);
		} else if ("�޸���Ʒ��Ϣ".equals(str)) {
			new CommodityDataDialog(4, str);
		} else if ("��ӹ�������Ϣ".equals(str)) {
			new SupplierDataDialog(1, str);
		} else if ("ɾ����������Ϣ".equals(str)) {
			new SupplierDataDialog(3, str);
		} else if ("�޸Ĺ�������Ϣ".equals(str)) {
			new SupplierDataDialog(4, str);
		} else if ("��ӿͻ���Ϣ".equals(str)) {
			new ClientDataDialog(1, str);
		} else if ("ɾ���ͻ���Ϣ".equals(str)) {
			new ClientDataDialog(3, str);
		} else if ("�޸Ŀͻ���Ϣ".equals(str)) {
			new ClientDataDialog(4, str);
		} else if ("��Ӳֿ���Ϣ".equals(str)) {
			new DepotDataDialog(1, str);
		} else if ("ɾ���ֿ���Ϣ".equals(str)) {
			new DepotDataDialog(3, str);
		} else if ("�޸Ĳֿ���Ϣ".equals(str)) {
			new DepotDataDialog(4, str);
		} else if ("���ҵ��Ա��Ϣ".equals(str)) {
			new OperatorDataDialog(1, str);
		} else if ("ɾ��ҵ��Ա��Ϣ".equals(str)) {
			new OperatorDataDialog(3, str);
		} else if ("�޸�ҵ��Ա��Ϣ".equals(str)) {
			new OperatorDataDialog(4, str);
		} else if ("��Ӳɹ���ͬ��Ϣ".equals(str)) {
			new PurchaseDataDialog(1, str);
		} else if ("ɾ���ɹ���ͬ��Ϣ".equals(str)) {
			new PurchaseDataDialog(3, str);
		} else if ("�޸Ĳɹ���ͬ��Ϣ".equals(str)) {
			new PurchaseDataDialog(4, str);
		} else if ("��ѯ�ɹ���ͬ��Ϣ".equals(str) | "��ѯҵ��Ա��Ϣ".equals(str)
				| "��ѯ��Ʒ��Ϣ".equals(str) | "��ѯ��������Ϣ".equals(str)
				| "��ѯ�ͻ���Ϣ".equals(str) | "��ѯ�ֿ���Ϣ".equals(str)
				| "���ϲ�ѯ".equals(str)) {
			String[] items = new String[] { "", "��ѯ��Ʒ����", "��ѯ�ֿ�����", "��ѯ�ɹ���ͬ����",
					"��ѯ�ͻ�����", "��ѯҵ��Ա����", "��ѯ����������" };
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(
					new DataFindPanel("���ϲ�ѯ", items, tvo));
		} else if ("�ɹ�����".equals(str)) {
			Vector title = new Vector();
			title.add("�������");
			title.add("�����̱��");
			title.add("��������");
			title.add("��Ч����");
			title.add("��Чֹ��");
			title.add("ҵ��Ա���");
			title.add("�Ƶ���");
			tvo.setTitle(title);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new PurchaseOrderPanel(str, tvo));
		} else if ("������".equals(str) || "�˻���".equals(str)) {
			Vector title = new Vector();
			title.add("���������");
			title.add("�����̱��");
			title.add("��������");
			title.add("ҵ��Ա���");
			title.add("�Ƶ���");
			title.add("����Ա");
			title.add("����Ա");
			title.add("�ɹ��������");
			tvo.setTitle(title);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new PurchaseInPanel(str, tvo));
		} else if ("�ɹ�ͳ��".equals(str) || "����ͳ��".equals(str)) {
			int n = 1;
			if ("����ͳ��".equals(str)) {
				n = 2;
			}
			ReportFormDaoServer rs = new ReportFormDaoServer();
			Vector datas = rs.find(n, str);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new ReportFormDialog(str, datas));
			rs.close();
		} else if ("���ͳ��".equals(str)) {
			ReportFormDaoServer rs = new ReportFormDaoServer();
			Vector datas = rs.findAll();
			Vector d = rs.find(3, datas.get(0).toString());
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(
					new ReportFormDialog(3, str, datas, d));
			rs.close();
		} else if ("���Ԥ����".equals(str) | "�޸�Ԥ����".equals(str)
				| "ɾ��Ԥ����".equals(str) | "��ѯԤ����".equals(str)) {
			TableVO dptable = new TableVO(0, ps.getDespositPayment(), ps
					.getDespositPaymentTitle());
			TableVO sbtable = new TableVO(0, new Vector(), ps
					.getSuppliersBillTitle());
			DepositPayment dp = new DepositPayment("Ԥ�������", "Ԥ�����б�", dptable,
					"��������ϸ��Ϣ", sbtable);

		} else if ("Ӧ�����ѯ".equals(str) | "�����ʷ��ѯ".equals(str)) {
			TableVO aptable = new TableVO(0, ps.getAccountPayable(), ps
					.getAccountPayableTitle());
			TableVO pibtable = new TableVO(0, new Vector(), ps
					.getPurchaseInBillTitle());
			PaymentDetail dp = new PaymentDetail("Ӧ�����ѯ", "Ӧ�����б�", aptable,
					"��������ϸ��Ϣ", pibtable);

		} else if ("Ӧ�������".equals(str)) {
			TableVO aptable = new TableVO(0, ps.getAccountPayable(), ps
					.getAccountPayableTitle());
			TableVO sbtable = new TableVO(0, new Vector(), ps
					.getSuppliersBillTitle());
			PaymentGather dp = new PaymentGather("Ӧ�������", "Ӧ�����б�", aptable,
					"��������ϸ��Ϣ", sbtable);

		} else if ("��Ӹ��".equals(str) | "�޸ĸ��".equals(str)
				| "ɾ�����".equals(str) | "��ѯ���".equals(str)) {
			TableVO aptable = new TableVO(0, ps.getAccountPayable(), ps
					.getAccountPayableTitle());
			TableVO pibtable = new TableVO(0, new Vector(), ps
					.getPurchaseInBillTitle());
			PaymentManage dp = new PaymentManage("�������", "����б�", aptable,
					"��������ϸ��Ϣ", pibtable);

		} else if ("�Ѹ����ѯ".equals(str)) {
			TableVO aptable = new TableVO(0, ps.getAccountPaid(), ps
					.getAccountPayableTitle());
			TableVO sbtable = new TableVO(0, new Vector(), ps
					.getSuppliersBillTitle());
			AccountPaid dp = new AccountPaid("�Ѹ����ѯ", "�Ѹ����б�", aptable,
					"��������ϸ��Ϣ", sbtable);

			// �������˵�:Ӧ�տ����
		} else if ("���Ԥ�տ�".equals(str) | "�޸�Ԥ�տ�".equals(str)
				| "ɾ��Ԥ�տ�".equals(str) | "��ѯԤ�տ�".equals(str)) {
			TableVO drtable = new TableVO(0, rs.getDespositReceivable(), rs
					.getDespositReceivableTitle());
			TableVO cbtable = new TableVO(0, new Vector(), rs
					.getClientBillTitle());
			DepositReceivable dp = new DepositReceivable("Ԥ�տ����", "Ԥ�տ��б�",
					drtable, "�ͻ���ϸ��Ϣ", cbtable);

		} else if ("Ӧ�տ��ѯ".equals(str) | "�տ��ʷ��ѯ".equals(str)) {
			TableVO artable = new TableVO(0, rs.getAccountReceivable(), rs
					.getAccountReceivableTitle());
			TableVO aotable = new TableVO(0, new Vector(), rs
					.getSaleBillTitle());
			ReceivableDetail dp = new ReceivableDetail("Ӧ�տ��ѯ", "Ӧ�տ��б�",
					artable, "���۵���ϸ��Ϣ", aotable);

		} else if ("Ӧ�տ����".equals(str)) {
			TableVO artable = new TableVO(0, rs.getAccountReceivable(), rs
					.getAccountReceivableTitle());
			TableVO cbtable = new TableVO(0, new Vector(), rs
					.getClientBillTitle());
			ReceivableGather dp = new ReceivableGather("Ӧ�տ����", "Ӧ�տ��б�",
					artable, "�ͻ���ϸ��Ϣ", cbtable);

		} else if ("����տ".equals(str) | "�޸��տ".equals(str)
				| "ɾ���տ".equals(str) | "��ѯ�տ".equals(str)) {
			TableVO artable = new TableVO(0, rs.getAccountReceivable(), rs
					.getAccountReceivableTitle());
			TableVO aotable = new TableVO(0, new Vector(), rs
					.getSaleBillTitle());
			ReceivableManage dp = new ReceivableManage("�տ����", "Ӧ�տ��б�",
					artable, "���۵���ϸ��Ϣ", aotable);

		} else if ("���տ��ѯ".equals(str)) {
			TableVO artable = new TableVO(0, rs.getAccountReceived(), rs
					.getAccountReceivableTitle());
			TableVO cbtable = new TableVO(0, new Vector(), rs
					.getClientBillTitle());
			AccountReceived dp = new AccountReceived("���տ��ѯ", "���տ��б�", artable,
					"�ͻ���ϸ��Ϣ", cbtable);

			// ���߸��˵�:�������
		} else if ("���������趨".equals(str)) {
			new Checkout(0);
		} else if ("����".equals(str)) {
			new Checkout(3);
		} else if ("���������".equals(str)) {
			new Checkout(1);
		} else if ("������ʷ��ѯ".equals(str)) {
			new Checkout(2);
		} else if (str.equalsIgnoreCase("�������۶���")) {
			OrderSaleDialog os = new OrderSaleDialog("���۶���");
		} else if (str.equalsIgnoreCase("�������۵�")) {
			SaleDialog os = new SaleDialog("���۵�");
		} else if (str.equalsIgnoreCase("�����˻���")) {
			SaleBackDialog os = new SaleBackDialog("�˻���");
		} else if (str.equalsIgnoreCase("������ʷ�ۼ�")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UpdateSalePanel());
		} else if (str.equalsIgnoreCase("���۲�ѯ")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new SaleTabbedPane());
		} else if (str.equalsIgnoreCase("����ѯ")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new QueryDepotPanel());
		} else if (str.equalsIgnoreCase("���ת��")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new TransDepotBasePanel());
		} else if (str.equalsIgnoreCase("����̵�")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new CheckDepotPanel());
		} else if (str.equalsIgnoreCase("������")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new ProfitLossPanel());
		} else if (str.equalsIgnoreCase("�������趨")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UperLowerPanel());
		} else if (str.equalsIgnoreCase("�����ޱ���")) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new UpLowAlarmPanel());
		} else if ("�鿴��־".equals(str)) {
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new LogsPanel());
		} else if ("��������".equals(str)) {
			new RefPWDialog(frame);
		}
	}

}
