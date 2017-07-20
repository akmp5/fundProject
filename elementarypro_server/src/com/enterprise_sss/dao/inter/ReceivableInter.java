package com.enterprise_sss.dao.inter;

import java.util.Vector;

import com.enterprise_sss.vo.AccountPayableVO;
import com.enterprise_sss.vo.AccountReceivableVO;
import com.enterprise_sss.vo.DepositReceivableVO;

public interface ReceivableInter {
	Vector findDepositReceivable(String sql);
	boolean updateDepositReceivable(DepositReceivableVO vo,String sql);
	Vector findClientBill(int clien_id);
	Vector findAccountReceivable(String sql);
	boolean updateAccountReceivable(AccountReceivableVO vo,String sql);
	Vector findSaleBill(int sb_id);
}
