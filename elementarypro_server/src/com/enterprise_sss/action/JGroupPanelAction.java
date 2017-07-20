package com.enterprise_sss.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import com.enterprise_sss.control.PaymentServer;
import com.enterprise_sss.control.ReceivableServer;
import com.enterprise_sss.control.ReportFormDaoServer;
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
import com.enterprise_sss.view.dialog.checkout.Checkout;
import com.enterprise_sss.view.dialog.salemanage.OrderSaleDialog;
import com.enterprise_sss.view.dialog.salemanage.SaleBackDialog;
import com.enterprise_sss.view.dialog.salemanage.SaleDialog;
import com.enterprise_sss.view.dialog.testJFreeChar.ReportFormDialog;
import com.enterprise_sss.view.frame.MainFrame;
import com.enterprise_sss.view.panel.DataFindPanel;
import com.enterprise_sss.view.panel.depotmanage.CheckDepotPanel;
import com.enterprise_sss.view.panel.depotmanage.ProfitLossPanel;
import com.enterprise_sss.view.panel.depotmanage.QueryDepotPanel;
import com.enterprise_sss.view.panel.depotmanage.TransDepotBasePanel;
import com.enterprise_sss.view.panel.depotmanage.UpLowAlarmPanel;
import com.enterprise_sss.view.panel.depotmanage.UperLowerPanel;
import com.enterprise_sss.view.panel.maintenance.ClientMaintenancePanel;
import com.enterprise_sss.view.panel.maintenance.CommodityMaintenancePanel;
import com.enterprise_sss.view.panel.maintenance.DepotMaintenancePanel;
import com.enterprise_sss.view.panel.maintenance.OperatorMaintenancePanel;
import com.enterprise_sss.view.panel.maintenance.PurchaseInPanel;
import com.enterprise_sss.view.panel.maintenance.PurchaseMaintenancePanel;
import com.enterprise_sss.view.panel.maintenance.PurchaseOrderPanel;
import com.enterprise_sss.view.panel.maintenance.SupplierMaintenancePanel;
import com.enterprise_sss.view.panel.salemanage.SaleTabbedPane;
import com.enterprise_sss.view.panel.salemanage.UpdateSalePanel;
import com.enterprise_sss.vo.TableVO;

public class JGroupPanelAction implements ActionListener {

	public MainFrame frame;
	private PaymentServer ps=new PaymentServer();
	private ReceivableServer rs=new ReceivableServer();
	
	public JGroupPanelAction(MainFrame frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		TableVO tvo = new TableVO();
		Vector title = new Vector();
		Vector data = new Vector();
		if ("��Ʒ����ά��".equals(str)) {
			title.add("������");
			title.add("������");
			title.add("��Ʒ��");
			title.add("ƴ������");
			title.add("���");
			title.add("��λ");
			title.add("����");
			title.add("���");
			title.add("������");
			title.add("���ۼ�");
			title.add("��Ա��");
			title.add("����ۼ�");
			tvo.setTitle(title);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new CommodityMaintenancePanel(str,tvo));
		} else if ("����������ά��".equals(str)) {
			title.add("�����̱��");
			title.add("ƴ������");
			title.add("���");
			title.add("����");
			title.add("��ַ");
			title.add("�ʱ�");
			title.add("����");
			title.add("�绰");
			title.add("����");
			title.add("������");
			title.add("�����ʺ�");
			title.add("�ⷿ��ַ");
			title.add("�ⷿ�绰");
			title.add("ҵ��Ա���");
			tvo.setTitle(title);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new SupplierMaintenancePanel(str,tvo));
		} else if ("�ͻ�����ά��".equals(str)) {
			title.add("�ͻ����");
			title.add("ƴ������");
			title.add("���");
			title.add("����");
			title.add("��ϵ��");
			title.add("��ַ");
			title.add("�ʱ�");
			title.add("�绰");
			title.add("����");
			title.add("������");
			title.add("�����ʺ�");
			title.add("����");
			title.add("ҵ��Ա");
			title.add("���Ŷ��");
			tvo.setTitle(title);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new ClientMaintenancePanel(str,tvo));
		} else if ("ҵ��Ա��Ϣά��".equals(str)) {
			title.add("ҵ��Ա���");
			title.add("ƴ������");
			title.add("����");
			title.add("Ů");
			title.add("�绰");
			title.add("�ֻ�");
			title.add("��ַ");
			title.add("�ʱ�");
			title.add("���֤��");
			title.add("���");
			tvo.setTitle(title);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new OperatorMaintenancePanel(str,tvo));
		} else if ("�ֿ���Ϣά��".equals(str)) {
			title.add("�ֿ���");
			title.add("�ֿ���");
			title.add("���");
			title.add("��ע");
			tvo.setTitle(title);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new DepotMaintenancePanel(str,tvo));
		} else if ("�ɹ���ͬ����".equals(str)) {
			title.add("��ͬ���");
			title.add("�����̱��");
			title.add("������");
			title.add("����");
			title.add("���ʽ");
			title.add("����");
			title.add("ǩ������");
			title.add("��ͬ����");
			tvo.setTitle(title);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new PurchaseMaintenancePanel(str,tvo));
		} else if ("���ϲ�ѯ".equals(str)) {
			String[] items = new String[]{"","��ѯ��Ʒ����","��ѯ�ֿ�����","��ѯ�ɹ���ͬ����","��ѯ�ͻ�����","��ѯҵ��Ա����","��ѯ����������"};
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new DataFindPanel("���ϲ�ѯ", items, tvo));
		} else if ("������".equals(str) || "�˻���".equals(str)) {
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
			frame.getPane().setRightComponent(new PurchaseInPanel(str,tvo));
		} else if ("�ɹ�����".equals(str)) {
			title.add("�������");
			title.add("�����̱��");
			title.add("��������");
			title.add("��Ч����");
			title.add("��Чֹ��");
			title.add("ҵ��Ա���");
			title.add("�Ƶ���");
			tvo.setTitle(title);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new PurchaseOrderPanel(str,tvo));
		} else if ("�ɹ�ͳ��".equals(str) || "����ͳ��".equals(str)) {
			int n = 1;
			if ("����ͳ��".equals(str)){
				n = 2;
			}
			ReportFormDaoServer rs = new ReportFormDaoServer();
			Vector datas = rs.find(n,str);
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new ReportFormDialog(str,datas));
			rs.close();
		} else if ("���ͳ��".equals(str)) {
			ReportFormDaoServer rs = new ReportFormDaoServer();
			Vector datas = rs.findAll();
			Vector d = rs.find(3, datas.get(0).toString());
			frame.getPane().remove(frame.getPane().getRightComponent());
			frame.getPane().setRightComponent(new ReportFormDialog(3,str,datas,d));
			rs.close();
		} else if ("Ԥ�������".equals(str)) {
			TableVO dptable=new TableVO(0,ps.getDespositPayment(),ps.getDespositPaymentTitle());
			TableVO sbtable=new TableVO(0,new Vector(),ps.getSuppliersBillTitle());
			DepositPayment dp=new DepositPayment("Ԥ�������","Ԥ�����б�",dptable,"��������ϸ��Ϣ",sbtable);
		} else if ("Ӧ�տ����".equals(str)) {
			TableVO artable=new TableVO(0,rs.getAccountReceivable(),rs.getAccountReceivableTitle());
			TableVO cbtable=new TableVO(0,new Vector(),rs.getClientBillTitle());
			ReceivableGather dp=new ReceivableGather("Ӧ�տ����","Ӧ�տ��б�",artable,"�ͻ���ϸ��Ϣ",cbtable);
		} else if ("���տ���ϸ".equals(str)) {
			TableVO artable=new TableVO(0,rs.getAccountReceived(),rs.getAccountReceivableTitle());
			TableVO cbtable=new TableVO(0,new Vector(),rs.getClientBillTitle());
			AccountReceived dp=new AccountReceived("���տ��ѯ","���տ��б�",artable,"�ͻ���ϸ��Ϣ",cbtable);
		} else if ("���".equals(str)) {
			TableVO aptable=new TableVO(0,ps.getAccountPayable(),ps.getAccountPayableTitle());
			TableVO pibtable=new TableVO(0,new Vector(),ps.getPurchaseInBillTitle());
			PaymentManage dp=new PaymentManage("�������","����б�",aptable,"��������ϸ��Ϣ",pibtable);
		} else if ("Ӧ�������".equals(str)) {
			TableVO aptable=new TableVO(0,ps.getAccountPayable(),ps.getAccountPayableTitle());
			TableVO sbtable=new TableVO(0,new Vector(),ps.getSuppliersBillTitle());
			PaymentGather dp=new PaymentGather("Ӧ�������","Ӧ�����б�",aptable,"��������ϸ��Ϣ",sbtable);
		} else if ("Ӧ�����ѯ".equals(str)) {
			TableVO aptable=new TableVO(0,ps.getAccountPayable(),ps.getAccountPayableTitle());
			TableVO pibtable=new TableVO(0,new Vector(),ps.getPurchaseInBillTitle());
			PaymentDetail dp=new PaymentDetail("Ӧ�����ѯ","Ӧ�����б�",aptable,"��������ϸ��Ϣ",pibtable);
		} else if ("�Ѹ�����ϸ".equals(str)) {
			TableVO aptable=new TableVO(0,ps.getAccountPaid(),ps.getAccountPayableTitle());
			TableVO sbtable=new TableVO(0,new Vector(),ps.getSuppliersBillTitle());
			AccountPaid dp=new AccountPaid("�Ѹ����ѯ","�Ѹ����б�",aptable,"��������ϸ��Ϣ",sbtable);
		} else if ("Ԥ�տ����".equals(str)) {
			TableVO drtable=new TableVO(0,rs.getDespositReceivable(),rs.getDespositReceivableTitle());
			TableVO cbtable=new TableVO(0,new Vector(),rs.getClientBillTitle());
			DepositReceivable dp=new DepositReceivable("Ԥ�տ����","Ԥ�տ��б�",drtable,"�ͻ���ϸ��Ϣ",cbtable);
		} else if ("�տ".equals(str)) {
			TableVO artable=new TableVO(0,rs.getAccountReceivable(),rs.getAccountReceivableTitle());
			TableVO aotable=new TableVO(0,new Vector(),rs.getSaleBillTitle());
			ReceivableManage dp=new ReceivableManage("�տ����","Ӧ�տ��б�",artable,"���۵���ϸ��Ϣ",aotable);
		} else if ("Ӧ�տ��ѯ".equals(str)) {
			TableVO artable=new TableVO(0,rs.getAccountReceivable(),rs.getAccountReceivableTitle());
			TableVO aotable=new TableVO(0,new Vector(),rs.getSaleBillTitle());
			ReceivableDetail dp=new ReceivableDetail("Ӧ�տ��ѯ","Ӧ�տ��б�",artable,"���۵���ϸ��Ϣ",aotable);
		}else if("���������趨".equals(str)){
			new Checkout(0);
		}else if("����".equals(str)){
			new Checkout(3);
		}else if("���������".equals(str)){
			new Checkout(1);
		}else if("��ʷ��ѯ".equals(str)){
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
		}
	}

}
