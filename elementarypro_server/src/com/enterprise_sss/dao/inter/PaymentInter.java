package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.AccountPayableVO;
import com.enterprise_sss.vo.DepositPaymentVO;

public interface PaymentInter {
	Vector findDepositPayment(String sql);
	boolean updateDepositPayment(DepositPaymentVO vo,String sql);
	Vector findSuppliersBill(int supp_id);
	Vector findAccountPayable(String sql);
	boolean updateAccountPayable(AccountPayableVO vo,String sql);
	Vector findPurchaseInBill(int pib_id );
}
