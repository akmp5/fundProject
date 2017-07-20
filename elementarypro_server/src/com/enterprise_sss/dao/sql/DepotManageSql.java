package com.enterprise_sss.dao.sql;

public interface DepotManageSql {
	String sql_depotQueryAll ="select db.depo_id,db.depo_name,db.stor_id,db.depo_desc,sb.stor_name,sb.stor_address,sb.stor_desc,s.comm_id,s.amount,s.money "
					           +"from depot_bill db,store_bill sb,stock s "
					           +"where db.depo_id = s.depo_id and "
					           +"db.stor_id = sb.stor_id";
	
	String sql_DepotIdQuery = "select db.depo_id,db.depo_name,db.stor_id,db.depo_desc,sb.stor_name,sb.stor_address,sb.stor_desc,s.comm_id,s.amount,s.money "
					        	+"from depot_bill db,store_bill sb,stock s "
					        	+"where db.depo_id = s.depo_id and "
					        	+"db.stor_id = sb.stor_id and "
					        	+"db.depo_id = ?";

	String sql_CommIdQuery = "select db.depo_id,db.depo_name,db.stor_id,db.depo_desc,sb.stor_name,sb.stor_address,sb.stor_desc,s.comm_id,s.amount,s.money "
						    	+"from depot_bill db,store_bill sb,stock s "
						    	+"where db.depo_id = s.depo_id and "
						    	+"db.stor_id = sb.stor_id and "
						    	+"s.comm_id = ?";

	String sql_TransQuery = "select * from cargo_transfer_bill";
	
	String sql_TransApply = "select * from cargo_transfer_apply";

	String sql_StorIDQuery ="select * from depot_bill db,store_bill sb where  db.stor_id = sb.stor_id and db.stor_id = ?";
	
	String sql_DepotCommQuery = "select s.amount "
						    	+"from depot_bill db,store_bill sb,stock s "
						    	+"where db.depo_id = s.depo_id and "
						    	+"db.stor_id = sb.stor_id and "
						    	+"db.depo_id =1 and s.comm_id = ?";

	String sql_TransDepotInsert = "insert into cargo_transfer_bill values (q_cargo_transfer_bill.nextval,?,?,?,?,?,?)";

	String sql_StockUpdate1 = "update stock set amount = amount - ? where comm_id = ? and depo_id = 1";

	String sql_StockUpdate2 = "update stock set amount = amount + ? where comm_id = ? and depo_id = (select db.depo_id "
								+"from depot_bill db,store_bill sb "
								+"where db.stor_id = sb.stor_id " 
								+"and sb.stor_id = ?)"; 
	
	String sql_StockInsert = "insert into stock values (?,?,(select purchase_price from commodity_bill where comm_id = ?)*?,?)";
	
	String sql_CheckStock = "select amount from depot_bill db,stock s where db.depo_id = s.depo_id and comm_id = ? and db.stor_id = ?";

	String sql_depotCommQuery = "select sum(amount) from stock group by comm_id having comm_id = ?";

	String sql_profitLossQuery = "select * from report_profit_loss";

	String sql_proLossInsert = "insert into report_profit_loss values (q_report_profit_loss.nextval,?,?,?,?,?,?,?)";

	String sql_UpLowQuery = "select * from upper_lower_limit";

	String sql_upLowInsert = "insert into upper_lower_limit values (q_upper_lower_limit.nextval,?,?,?,?,?,?)";

	String sql_AlarmQuery = "select ull.depo_id,ull.comm_id,s.amount,ull.ull_upper,ull.ull_lower,ull.ull_optimal from upper_lower_limit ull,stock s where ull.comm_id = s.comm_id and ull.depo_id = s.depo_id";

	String sql_ApplyUpdate = "update cargo_transfer_apply set cta_isreply = 'ря╣В' where cta_id = ?";

	String sql_upLowUpdate = "update upper_lower_limit set ull_upper = ?,ull_lower = ?,ull_optimal = ? where ull_id = ?";

	String sql_upLowDelete = "delete upper_lower_limit where ull_id = ?";
	
	
	
}
