package com.enterprise_sss.dao.sql;

public class ReportFormSqls {

	public static String sql_purchase = "select sum(p.poi_amount) coun, c.comm_sort sort from Commodity_Bill c,purchase_order_items p where c.comm_id = p.com_id group by c.comm_sort";

	public static String sql_sale = "select sum(si.si_amount)-sum(sm.sm_amount) coun, c.comm_sort from commodity_bill c,sale_mrb sm,sale_items si where c.comm_id = sm.comm_id and c.comm_id = si.comm_id group by c.comm_sort";
	
	public static String sql_depot_findAll = "select depo_name from depot_bill";
	
	public static String sql_depot = "select sum(s.amount) coun,c.comm_sort from commodity_bill c,stock s,depot_bill d where c.comm_id = s.comm_id and d.depo_id = s.depo_id and d.depo_name = ? group by c.comm_sort";
}
