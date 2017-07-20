package com.enterprise_sss.dao.sql;

public interface SaleManageSql {
	String sql_saleQueryAll ="select si.si_id,sb.sb_id,sb.clie_id,sb.sale_date,sb.oper_id,sb.cbill,sb.keeper,sb.so_id,si.comm_id,cb.comm_name,cb.comm_spell_code,si.si_amount,si.sale_price,si.depo_id " +
							"from sale_bill sb,sale_items si,commodity_bill cb " +
							"where sb.sb_id = si.sb_id and cb.comm_id = si.comm_id";
	String sql_ClientIdQuery ="select si.si_id,sb.sb_id,sb.clie_id,sb.sale_date,sb.oper_id,sb.cbill,sb.keeper,sb.so_id,si.comm_id,cb.comm_name,cb.comm_spell_code,si.si_amount,si.sale_price,si.depo_id " +
								"from sale_bill sb,sale_items si,commodity_bill cb " +
								"where sb.sb_id = si.sb_id and cb.comm_id = si.comm_id and sb.clie_id = ?";
	String sql_SaleDateQuery ="select si.si_id,sb.sb_id,sb.clie_id,sb.sale_date,sb.oper_id,sb.cbill,sb.keeper,sb.so_id,si.comm_id,cb.comm_name,cb.comm_spell_code,si.si_amount,si.sale_price,si.depo_id " +
								"from sale_bill sb,sale_items si,commodity_bill cb " +
								"where sb.sb_id = si.sb_id and cb.comm_id = si.comm_id and to_char(sb.sale_date,'yyyy-mm-dd') = ?";
	String sql_CommIdQuery ="select si.si_id,sb.sb_id,sb.clie_id,sb.sale_date,sb.oper_id,sb.cbill,sb.keeper,sb.so_id,si.comm_id,cb.comm_name,cb.comm_spell_code,si.si_amount,si.sale_price,si.depo_id " +
								"from sale_bill sb,sale_items si,commodity_bill cb " +
								"where sb.sb_id = si.sb_id and cb.comm_id = si.comm_id and si.comm_id = ?";
	String sql_CommSpellQuery ="select si.si_id,sb.sb_id,sb.clie_id,sb.sale_date,sb.oper_id,sb.cbill,sb.keeper,sb.so_id,si.comm_id,cb.comm_name,cb.comm_spell_code,si.si_amount,si.sale_price,si.depo_id " +
								"from sale_bill sb,sale_items si,commodity_bill cb " +
								"where sb.sb_id = si.sb_id and cb.comm_id = si.comm_id and cb.comm_spell_code like ?";	
	String sql_OperIdQuery ="select si.si_id,sb.sb_id,sb.clie_id,sb.sale_date,sb.oper_id,sb.cbill,sb.keeper,sb.so_id,si.comm_id,cb.comm_name,cb.comm_spell_code,si.si_amount,si.sale_price,si.depo_id " +
								"from sale_bill sb,sale_items si,commodity_bill cb " +
								"where sb.sb_id = si.sb_id and cb.comm_id = si.comm_id and sb.oper_id = ?";
	
	String sql_OrderSaleQuery ="select so.so_id,so.clie_id,so.so_date,so.so_begin_date,so.so_end_date,so.oper_id,so.cbill,soi.comm_id,soi.so_amount,soi.sale_price from sale_order so,sale_order_items soi where so.so_id = soi.so_id";
	
	String sql_SaleBackQuery ="select * from sale_mrb";

	String sql_saleUpdate = "update sale_items set sale_price = ? where si_id = ?";
	
	String sql_OrderSaleInsert ="insert into sale_order values(q_sale_order.nextval,?,?,?,?,?,?)";
	
	String sql_OrderSaleItemInsert ="insert into sale_order_items values(q_sale_order_items.nextval,q_sale_order.currval,?,?,?)";
	
	String sql_SaleInsert ="insert into sale_bill values(q_sale_bill.nextval,?,?,?,?,?,?)";
	
	String sql_SaleItemInsert ="insert into sale_items values(q_sale_items.nextval,q_sale_bill.currval,?,?,?,?)";
	
	String sql_SaleBackInsert = "insert into sale_mrb values (q_sale_mrb.nextval,?,?,?,?,?)";
	
	String sql_SaleCommQuery = "select comm_id ,amount from stock where comm_id = ?";
	
	String sql_stockInsert = "update stock set amount = amount - ? where comm_id = ? and depo_id = 1";
	
	String sql_SaleBackcheck1 = "select * from sale_bill sb,sale_items si where sb.sb_id=si.sb_id and sb.sb_id = ? and si.depo_id =1";
	
	String sql_SaleBackcheck2 = "select * from sale_bill sb,sale_items si where sb.sb_id=si.sb_id and si.comm_id = ? and si.depo_id =1";
	
	String sql_StockUpdate = "update stock set amount = amount + ? where comm_id = ? and depo_id = 1";
	
	
	
	
	
	
	
}
