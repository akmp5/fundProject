package com.enterprise_sss.vo;

/**
 * 进销存汇总表VO
 * @author yiguo
 *
 */
public class GatherVO {

	private int jg_id;//结帐编号
	private String jg_date;//结帐日期
	private int comm_id;//货物编号
	private int last_checkout_amount;//上期结存数量
	private double last_checkout_money;//上期结存金额
	private int out_amount;//售出数量
	private double out_money;//售出金额
	private int in_amount;//进货数量
	private double in_money;//进货金额
	private int spill_loss_amount;//溢损数量
	private double spill_loss_money;//溢损金额
	private int now_checkout_amount;//本期结存数量
	private double now_checkout_money;//本期结存金额
	private String jg_desc;//备注
	
	
	public int getJg_id() {
		return jg_id;
	}
	public void setJg_id(int jg_id) {
		this.jg_id = jg_id;
	}
	public String getJg_date() {
		return jg_date;
	}
	public void setJg_date(String jg_date) {
		this.jg_date = jg_date;
	}
	public int getComm_id() {
		return comm_id;
	}
	public void setComm_id(int comm_id) {
		this.comm_id = comm_id;
	}
	public int getLast_checkout_amount() {
		return last_checkout_amount;
	}
	public void setLast_checkout_amount(int last_checkout_amount) {
		this.last_checkout_amount = last_checkout_amount;
	}
	public double getLast_checkout_money() {
		return last_checkout_money;
	}
	public void setLast_checkout_money(double last_checkout_money) {
		this.last_checkout_money = last_checkout_money;
	}
	public int getOut_amount() {
		return out_amount;
	}
	public void setOut_amount(int out_amount) {
		this.out_amount = out_amount;
	}
	public double getOut_money() {
		return out_money;
	}
	public void setOut_money(double out_money) {
		this.out_money = out_money;
	}
	public int getIn_amount() {
		return in_amount;
	}
	public void setIn_amount(int in_amount) {
		this.in_amount = in_amount;
	}
	public double getIn_money() {
		return in_money;
	}
	public void setIn_money(double in_money) {
		this.in_money = in_money;
	}
	public int getSpill_loss_amount() {
		return spill_loss_amount;
	}
	public void setSpill_loss_amount(int spill_loss_amount) {
		this.spill_loss_amount = spill_loss_amount;
	}
	public double getSpill_loss_money() {
		return spill_loss_money;
	}
	public void setSpill_loss_money(double spill_loss_money) {
		this.spill_loss_money = spill_loss_money;
	}
	public int getNow_checkout_amount() {
		return now_checkout_amount;
	}
	public void setNow_checkout_amount(int now_checkout_amount) {
		this.now_checkout_amount = now_checkout_amount;
	}
	public double getNow_checkout_money() {
		return now_checkout_money;
	}
	public void setNow_checkout_money(double now_checkout_money) {
		this.now_checkout_money = now_checkout_money;
	}
	public String getJg_desc() {
		return jg_desc;
	}
	public void setJg_desc(String jg_desc) {
		this.jg_desc = jg_desc;
	}
	
	

}
