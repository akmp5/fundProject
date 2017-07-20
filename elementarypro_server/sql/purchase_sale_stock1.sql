drop table user_bill;
drop table popedom_bill;
drop table commodity_bill;
drop table suppliers_bill;
drop table client_bill;
drop table operator_bill;
drop table depot_bill;
drop table store_bill;
drop table purchase_contract;
drop table stock;
drop table purchase_order;
drop table purchase_order_items;
drop table purchase_in_bill; 
drop table purchase_in_items;
drop table update_purchase_price;
drop table sale_order;
drop table sale_order_items;
drop table sale_bill;
drop table sale_items;
drop table sale_mrb;
drop table cargo_transfer_bill;
drop table report_profit_loss;
drop table upper_lower_limit;
drop table account_payable;
drop table account_receivable;
drop table deposit_received;
drop table deposit_payment;
drop table jxc_gather;
drop table posa;
drop table cargo_transfer_apply;



--用户清单表
create table user_bill (
user_id number(8) primary key not null,
user_name varchar2(20) not null,
user_password varchar2(20) not null,
stor_name varchar2(20) not null,
id_card varchar2(18) not null,
job varchar2(20) not null,
popedom_id varchar2(8)

);

insert into user_bill values(1,'wangming','wangming',0,'111111111111111111','总经理',0);
insert into user_bill values(2,'mohongfei','mohongfei',0,'111111111111111111','经理',1);
insert into user_bill values(3,'yiguo','yiguo',0,'430624198804065046','经理',2);
insert into user_bill values(4,'libailing','libailing',0,'111111111111111111','经理',3);
insert into user_bill values(5,'liufengjuan','liufengjuan',0,'111111111111111111','经理',4);
insert into user_bill values(6,'wangmengseng','wangmengseng',0,'111111111111111111','经理',5);
insert into user_bill values(7,'ee','ee',0,'111111111111111111','经理',6);
insert into user_bill values(8,'rr','rr',0,'111111111111111111','经理',7);
insert into user_bill values(9,'tt','tt',0,'111111111111111111','经理',8);
insert into user_bill values(10,'yg','yg',1,'111111111111111111','经理',9);
insert into user_bill values(11,'wm','wm',2,'111111111111111111','经理',9);
insert into user_bill values(12,'mhf','mhf',3,'111111111111111111','经理',9);

--用户权限表
create table popedom_bill (
pope_id number(8) primary key not null,
pope_name varchar2(20) 

);

insert into popedom_bill values(0,'超级管理员');
insert into popedom_bill values(1,'资料管理员');
insert into popedom_bill values(2,'采购管理员');
insert into popedom_bill values(3,'销售管理员');
insert into popedom_bill values(4,'库存管理员');
insert into popedom_bill values(5,'应付款管理员');
insert into popedom_bill values(6,'应收款管理员');
insert into popedom_bill values(7,'账务管理员');
insert into popedom_bill values(8,'系统管理员');
insert into popedom_bill values(9,'零售管理员');

--商品清单表
create table commodity_bill (
comm_id number(8) primary key not null,
comm_bar_code varchar2(20) unique not null,
comm_name varchar2(30),
comm_spell_code varchar2(20),
comm_standard varchar2(20),
comm_unit varchar2(20),
comm_producing_area varchar2(30),
comm_sort varchar2(20),
purchase_price numeric(10,2),
sale_price1 numeric(10,2),
sale_price2 numeric(10,2),
lowest_sale_price numeric(10,2) 

);

insert into commodity_bill values(1,'11111','可口可乐','kekoukele','350ml','瓶','湖南','饮料',2.5,3.5,3.20,2.8);
insert into commodity_bill values(2,'10000','青岛啤酒','qingdaopijiu','450ml','瓶','青岛','饮料',3.2,3.7,3.5,3.3);
insert into commodity_bill values(3,'14444','旺旺雪饼','wangwangxuebing','散装','kg','湖南','副食',2.5,3.5,3.20,2.8);

--供货商清单表
create table suppliers_bill(
supp_id number(8) primary key not null,
supp_spell_code varchar2(20),
supp_shortname varchar2(20),
supp_name varchar2(30),
supp_address varchar2(30),
supp_postcode varchar2(20),
supp_sort varchar2(20),
supp_tel varchar2(20),
supp_fax varchar2(20),
supp_bank varchar2(30),
supp_bank_account varchar2(19),
supp_storage_address varchar2(30),
supp_storage_tel varchar2(20),
oper_id number(8)

);

insert into suppliers_bill values(1,'jixingjituan','jixing','金星集团','广州','100000','','02012345678','11111111','中国银行','1235689753265478912','广州天河','02012345678',1);
insert into suppliers_bill values(2,'maotaijiuyejituan','maotai','茅台酒业集团','山东','100000','','52012345678','11117891','中国银行','1235689753265478456','山东','02012345678',2);
insert into suppliers_bill values(3,'wangwangjituan','wangwang','旺旺集团','湖南','414600','','073112345678','11111456','长沙银行','1235689753265478123','湖南望城','073112345678',1);

--客户清单表
create table client_bill (
clie_id number(8) primary key not null,
clie_spell_code varchar2(20),
clie_shortname varchar2(20),
clie_name varchar2(20),
clie_linkman varchar2(20),
clie_address varchar2(30),
clie_postcode varchar2(20),
clie_tel varchar2(20),
clie_fax varchar2(20),
clie_bank varchar2(30),
clie_bank_account varchar2(19),
clie_sort varchar2(20),
oper_id number(8),
clie_CreditLimt varchar2(20) 

);

insert into client_bill values(1,'wangwangchaoshi','wangwang','旺旺超市','刘辉','长沙','414600','073112345678','11111456','长沙银行','1235689753265478123','',1,'3');
insert into client_bill values(2,'woermachaoshi','woerma','沃尔玛超市','刘明','长沙','414600','073112345678','11111456','长沙银行','1235689753265478123','',2,'3');
insert into client_bill values(3,'bubugaochaoshi','bubugao','步步高超市','王汉','长沙','414600','073112345678','11111456','长沙银行','1235689753265478123','',1,'3');

--业务员清单表
create table operator_bill(
oper_id number(8) primary key not null,
oper_spell_code varchar2(20),
oper_name varchar2(20),
oper_sex varchar2(20),
oper_tel varchar2(20),
oper_mobile_tel varchar2(20),
oper_address varchar2(30),
oper_postcode varchar2(20),
oper_ID_number varchar2(20),
oper_sort varchar2(20) 

);

insert into operator_bill values(1,'lilan','李蓝','女','073112345678','13145678945','长沙','345678','123456789963258741','');
insert into operator_bill values(2,'liliang','李亮','男','073112345678','13145678945','长沙','345678','123456789963258741','');
insert into operator_bill values(3,'liulan','刘蓝','女','073112345678','13145678945','长沙','345678','123456789963258741','');


--仓库清单表
create table depot_bill(
depo_id number(8) primary key not null,
depo_name varchar2(20),
stor_id number(8),
depo_desc varchar2(50) 

);

insert into depot_bill values(1,'主仓库',0,'总库');
insert into depot_bill values(2,'仓库1',1,'分库');
insert into depot_bill values(3,'仓库2',2,'分库');
insert into depot_bill values(4,'仓库3',3,'分库');


--门店清单表
create table store_bill(
stor_id number(8) primary key not null,
stor_name varchar2(20),
stor_address varchar2(20),
stor_desc varchar2(50)

);

insert into store_bill values(1,'店铺1','长沙','长沙分店');
insert into store_bill values(2,'店铺2','广州','广州分店');
insert into store_bill values(3,'店铺3','武汉','武汉分店');

--采购合同表
create table purchase_contract(
pc_id number(8),
supp_id number(8),
comm_id number(8),
pur_price numeric(10,2),
pc_pay_method varchar2(20),
pc_pay_period varchar2(20),
pc_date date,
pc_period varchar2(20) 

);

insert into purchase_contract values(1,1,1,2.3,'支票','三年',sysdate,'三年');
insert into purchase_contract values(2,2,2,23,'支票','三年',sysdate,'三年');
insert into purchase_contract values(2,2,2,50,'支票','三年',sysdate,'三年');


--库存表
create table stock(
comm_id number(8),
amount numeric(15),
money numeric(10,2),
depo_id number(8) 

);

insert into stock values(1,500,2000,1);
insert into stock values(2,500,2000,1);
insert into stock values(3,500,2000,1);


--采购订单表
create table purchase_order(
po_id number(8) primary key not null,
supp_id number(8),
po_date date,
po_begin_date date,
po_end_date date,
oper_id number(8),
cbill varchar2(20) 

);

insert into purchase_order values(1,1,sysdate,sysdate,add_months(sysdate,36),1,'李白');
insert into purchase_order values(2,2,sysdate,sysdate,add_months(sysdate,36),2,'李白');
insert into purchase_order values(3,3,sysdate,sysdate,add_months(sysdate,36),3,'李白');

--采购订单明细表
create table purchase_order_items(
poi_id number(8) primary key not null,
po_id number(8),
com_id number(8),
poi_amount number(8),
purc_price numeric(10,2) 

);

insert into purchase_order_items values(1,1,1,1200,2.5);
insert into purchase_order_items values(2,1,2,1200,3.2);
insert into purchase_order_items values(3,1,3,1200,2.5);


--进货单
create table purchase_in_bill(
pib_id number(8) primary key not null,
supp_id number(8),
pib_date date,
oper_id number(8),
cbill varchar2(20),
inspector varchar2(20),
keeper varchar2(20),
po_id number(8) 

);

insert into purchase_in_bill values(1,1,sysdate,1,'李白','李白','李白',1);
insert into purchase_in_bill values(2,1,sysdate,1,'李白','李白','李白',2);
insert into purchase_in_bill values(3,2,sysdate,2,'李白','李白','李白',3);


--进货单明细表
create table purchase_in_items(
pii_id number(8) primary key not null,
pib_id number(8),
comm_id number(8),
pii_amount number(8),
purc_price numeric(10,2)

);

insert into purchase_in_items values(1,1,1,1200,2.5);
insert into purchase_in_items values(2,1,2,1200,3.2);
insert into purchase_in_items values(3,1,3,1200,2.5);

--进价调整单
create table update_purchase_price(
upp_id number(8) primary key not null,
pii_id number(8),
upp_amount number(8),
old_purchase_price numeric(10,2),
new_purchase_price numeric(10,2),
upp_date date,
cbill varchar2(20) 

);

insert into update_purchase_price values(1,1,1200,2.5,2.4,sysdate,'李白');
insert into update_purchase_price values(2,2,1200,3.2,2.3,sysdate,'李白');
insert into update_purchase_price values(3,3,1200,2.5,2.4,sysdate,'李白');


--销售订单表
create table sale_order(
so_id number(8) primary key not null,
clie_id number(8),
so_date date,
so_begin_date date,
so_end_date date,
oper_id number(8),
cbill varchar2(20) 

);

insert into sale_order values(1,1,sysdate,sysdate,add_months(sysdate,36),1,'李白');
insert into sale_order values(2,2,sysdate,sysdate,add_months(sysdate,36),2,'李白');
insert into sale_order values(3,3,sysdate,sysdate,add_months(sysdate,36),1,'李白');


--销售订单明细表
create table sale_order_items(
soi_id number(8) primary key not null,
so_id number(8),
comm_id number(8),
so_amount number(8),
sale_price numeric(10,2) 

);

insert into sale_order_items values(1,1,1,1200,2.5);
insert into sale_order_items values(2,1,2,1200,3.2);
insert into sale_order_items values(3,1,3,1200,2.5);


--销售单表
create table sale_bill(
sb_id number(8) primary key not null,
clie_id number(8),
sale_date date,
oper_id number(8),
cbill varchar2(20),
keeper varchar2(20),
so_id number(8) 

);

insert into sale_bill values(1,1,sysdate,1,'李白','李白',1);
insert into sale_bill values(2,1,sysdate,1,'李白','李白',2);
insert into sale_bill values(3,2,sysdate,2,'李白','李白',3);

--销售单明细
create table sale_items(
si_id number(8) primary key not null,
sb_id number(8),
comm_id number(8),
si_amount number(8),
sale_price numeric(10,2),
depo_id number(8) 

);

insert into sale_items values(1,1,1,1200,2.5,1);
insert into sale_items values(2,1,2,1200,3.2,1);
insert into sale_items values(3,1,3,1200,2.5,1);


--销售退货单
create table sale_mrb(
sm_id number(8) primary key not null,
sb_id number(8),
comm_id number(8),
sm_amount number(8),
sale_price numeric(10,2),
depo_id number(8) 

);

insert into sale_mrb values(1,1,1,1200,2.5,1);
insert into sale_mrb values(2,1,2,1200,3.2,1);
insert into sale_mrb values(3,1,3,1200,2.5,1);


--调拨单
create table cargo_transfer_bill(
ctb_id number(8) primary key not null,
stor_id number(8),
comm_id number(8),
ctb_amount number(8),
ctb_date date,
isctb varchar2(20),
cbill varchar2(20)

);

insert into cargo_transfer_bill values(1,1,1,100,sysdate,'已调拨','李白');
insert into cargo_transfer_bill values(2,2,2,100,sysdate,'已调拨','李白');
insert into cargo_transfer_bill values(3,3,1,100,sysdate,'未调拨','李白');


--报损报溢表
create table report_profit_loss(
rpl_id number(8) primary key not null,
comm_id number(8),
depo_id number(8),
rpl_amount number(8),
rpl_money numeric(10,2),
rpl_date date,
duty_person varchar2(20),
cbill varchar2(20) 

);

insert into report_profit_loss values(1,1,1,2,5,sysdate,'李白','李白');
insert into report_profit_loss values(2,1,1,2,5,sysdate,'李白','李白');
insert into report_profit_loss values(3,3,1,2,5,sysdate,'李白','李白');



--上下限对照表
create table upper_lower_limit(
ull_id number(8) primary key not null,
depo_id number(8),
comm_id number(8),
ull_upper number(8),
ull_lower number(8),
ull_optimal number(8),
u_des varchar2(50) 

);

insert into upper_lower_limit values(1,1,1,10000,10,2000,'建议使用最佳库存');
insert into upper_lower_limit values(2,1,2,10000,10,2000,'建议使用最佳库存');
insert into upper_lower_limit values(3,1,3,10000,10,2000,'建议使用最佳库存');

--应付款表
create table account_payable(
ap_id number(8) primary key not null,
ap_inv number(8),
ap_inv_date date,
pib_id number(8),
comm_id number(8),
supp_id number(8),
ap_comm_amount number(8),
ap_purchase_price numeric(10,2),
ap_money numeric(10,2),
ap_date date,
ap_desc varchar2(50),
state varchar2(20),
dp_id number(8) 

);

insert into account_payable values
(1,1,sysdate,1,1,1,1,33.33,4512.2,sysdate,'waaw','未付款',1);
insert into account_payable values
(2,2,sysdate,2,2,1,1,33.33,88612.2,sysdate,'saaqqw','未付款',1);
insert into account_payable values
(3,2,sysdate,2,2,1,1,33.33,4452.2,sysdate,'qkaqqw','已付款',2);


--应收款表
create table account_receivable(
ar_id number(8) primary key not null,
ar_inv number(8),
ar_inv_date date,
so_id number(8),
comm_id number(8),
clie_id number(8),
ar_comm_amount number(8),
ar_sale_price numeric(10,2),
ar_money numeric(10,2),
ar_date date,
ar_desc varchar2(50),
state varchar2(20),
dr_id number(8)

);

insert into account_receivable values
(1,1,sysdate,1,1,1,1,33.33,4512.2,sysdate,'waaw','未收款',1);
insert into account_receivable values
(2,2,sysdate,2,2,1,1,33.33,88612.2,sysdate,'saaqqw','未收款',1);
insert into account_receivable values
(3,3,sysdate,2,2,1,1,33.33,4452.2,sysdate,'qkaqqw','已收款',2);


--预收款
create table deposit_received(
dr_id number(8),
dr_inv number(8),
dr_inv_date date,
clie_id number(8),
cr_money numeric(10,2),
dr_date date

);

insert into deposit_received values
(1,2,sysdate,1,22.36,sysdate);
insert into deposit_received values
(2,2,sysdate,2,22.36,sysdate);
insert into deposit_received values
(3,2,sysdate,3,22.36,sysdate);


--预付款表
create table deposit_payment(
dp_id number(8),
dp_inv number(8),
dp_inv_date date,
supp_id number(8),
dp_money numeric(10,2),
dp_date date

);

insert into deposit_payment values
(1,5,sysdate,1,22.36,sysdate);
insert into deposit_payment values
(2,6,sysdate,2,22.36,sysdate);
insert into deposit_payment values
(3,7,sysdate,2,22.36,sysdate);

--进销存汇总表
create table jxc_gather(
jg_id number(8) primary key not null,
jg_date date,
comm_id number(8),
last_checkout_amount number(8),
last_checkout_money numeric(10,2),
debit_amount number(8),
debit_money numeric(10,2),
credit_amount number(8),
credit_money numeric(10,2),
now_checkout_amount number(8),
now_checkout_money numeric(10,2),
jg_desc varchar2(50)

);

insert into jxc_gather values(1,sysdate,1,5000,50000,4000,40000,1000,10000,6000,60000,'fffffffff');
insert into jxc_gather values(2,sysdate,2,5000,50000,4000,40000,1000,10000,6000,60000,'fffffffff');
insert into jxc_gather values(3,sysdate,3,5000,50000,4000,40000,1000,10000,6000,60000,'fffffffff');


--销售日报表
create table posa(
posa_id number(8),
stor_id number(8),
posa_pos number(8),
comm_id number(8),
posa_shift number(8),
posa_sale_amount number(8),
posa_receivable_money numeric(10,2),
posa_received_money numeric(10,2),
posa_price numeric(10,2)

);

insert into posa values(1,1,1,1,1,55,422.36,422.36,2.5);
insert into posa values(2,2,2,2,2,55,422.36,422.36,3.2);
insert into posa values(3,3,3,3,3,55,422.36,422.36,2.5);


--调货申请表
create table cargo_transfer_apply(
cta_id number(8),
stor_id number(8),
comm_id number(8),
cta_amount number(8),
cta_date date,
cta_person varchar2(20),
cta_desc varchar2(50),
cta_isReply varchar2(20)

);
insert into cargo_transfer_apply values(1,1,1,55,sysdate,'李白','李白','已调');
insert into cargo_transfer_apply values(2,2,2,55,sysdate,'李白','李白','已调');
insert into cargo_transfer_apply values(3,3,3,55,sysdate,'李白','李白','未调');


--删除所有序列
drop sequence q_user_bill;
drop sequence q_popedom_bill;
drop sequence q_commodity_bill;
drop sequence q_suppliers_bill;
drop sequence q_client_bill;
drop sequence q_operator_bill;
drop sequence q_depot_bill;
drop sequence q_store_bill;
drop sequence q_purchase_contract;
drop sequence q_stock;
drop sequence q_purchase_order;
drop sequence q_purchase_order_items;
drop sequence q_purchase_in_bill;
drop sequence q_purchase_in_items;
drop sequence q_update_purchase_price;
drop sequence q_sale_order;
drop sequence q_sale_order_items;
drop sequence q_sale_bill;
drop sequence q_sale_items;
drop sequence q_sale_mrb;
drop sequence q_cargo_transfer_bill;
drop sequence q_report_profit_loss;
drop sequence q_upper_lower_limit;
drop sequence q_account_payable;
drop sequence q_account_receivable;
drop sequence q_deposit_received;
drop sequence q_deposit_payment;
drop sequence q_jxc_gather;
drop sequence q_posa;
drop sequence q_cargo_transfer_apply;

--每个表的序列，序列名以q_加对应的表名
create sequence q_user_bill
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_popedom_bill
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_commodity_bill
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_suppliers_bill
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_client_bill
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_operator_bill
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_depot_bill
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_store_bill
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_purchase_contract
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_stock
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_purchase_order
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_purchase_order_items
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_purchase_in_bill
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_purchase_in_items
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_update_purchase_price
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_sale_order
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_sale_order_items
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_sale_bill
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_sale_items
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_sale_mrb
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_cargo_transfer_bill
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_report_profit_loss
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_upper_lower_limit
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_account_payable
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_account_receivable
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_deposit_received
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_deposit_payment
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_jxc_gather
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_posa
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

create sequence q_cargo_transfer_apply
start with 100
increment by 1
minvalue 0
nomaxvalue
nocache;

