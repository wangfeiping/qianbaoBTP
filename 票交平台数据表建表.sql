/*
DROP TABLE IF EXISTS TB_User;
DROP TABLE TB_Bill;
DROP TABLE TB_Account;
DROP TABLE TB_Guaranteed;
DROP TABLE TB_Credit;
DROP TABLE TB_Payment;
DROP TABLE TB_Deal;
DROP TABLE TB_CreditOrganization;
*/

-- 会员表
create table TB_User(
	User_ID bigint primary key AUTO_INCREMENT,--  会员编号
	User_AccountID bigint,-- 会员对应的账户编号
	User_GuaranteedID bigint,-- 会员对应的担保账户编号
	User_CreditID bigint,-- 会员对应的授信账户编号
	User_Name varchar(20),-- 会员登录用户名
	User_Password varchar(32),-- 会员登录密码
	User_State varchar(100),-- 会员状态
	User_EnterpriseName varchar(100),-- 会员公司名称
	User_EnterpriseRegistrID varchar(100),-- 会员公司注册号
	User_EnterprisetType varchar(100),-- 会员公司类型
	User_EnterprisetAddress varchar(100)-- 会员公司地址

);

-- 票据表
create table TB_Bill(
	Bill_ID bigint primary key AUTO_INCREMENT,-- 票据编号
	Bill_UserID bigint,-- 票据所有者用户编号
	Bill_Denomination decimal(20,2),-- 票据面额
	Bill_Price decimal(20,2),-- 票据出售价格
	Bill_AcceptingBank varchar(100),-- 票据承兑行
	Bill_State varchar(100)-- 票据状态
);

-- 用户账户表
create table TB_Account(
	Account_ID bigint primary key AUTO_INCREMENT,-- 用户账户编号
	Account_Sum decimal(20,2)-- 会员账户资金
);

-- 担保账户表
create table TB_Guaranteed(
	Guaranteed_ID bigint primary key AUTO_INCREMENT,-- 担保账户编号
	Guaranteed_Limit decimal(20,2),-- 担保额度
	Guaranteed_State varchar(100)-- 担保账户状态
);

-- 授信账户表
create table TB_Credit(
	Credit_ID bigint primary key AUTO_INCREMENT,-- 授信账户编号
	Credit_Limit decimal(20,2),-- 授信额度
	Credit_State varchar(100),-- 授信账户状态
	Credit_CreditOrganizationID bigint-- 授信机构编号
);

-- 授信机构表
create table TB_CreditOrganization(
	CreditOrganization_ID bigint primary key AUTO_INCREMENT,-- 授信机构编号
	CreditOrganization_Name varchar(100)-- 授信机构名称
);

-- 授信记录表
create table TB_CreditRecord(
	CreditRecord_ID bigint primary key AUTO_INCREMENT,-- 授信记录编号
	CreditRecord_CreditOrganizationID bigint,-- 授信机构编号
	CreditRecord_UserID bigint,-- 被授信会员编号
	CreditRecord_Repeal varchar(100),-- 是否是撤销授信
	CreditRecord_Limit decimal(20,2),-- 授信额度
	CreditRecord_Timestamp timestamp-- 授信时间
);

-- 支付订单表
create table TB_Payment(
	Payment_ID bigint primary key AUTO_INCREMENT,-- 支付订单编号
	Payment_UserID bigint,-- 支付人用户编号
	Payment_Type varchar(100),-- 订单类型（1：充值订单，2：体现订单）
	Payment_ApplyTime timestamp,-- 支付订单生成时间
	Payment_State varchar(100),-- 支付订单状态
	Payment_Sum decimal(20,2)-- 支付金额
);

-- 交易信息表
create table TB_Deal(
	Deal_ID bigint primary key AUTO_INCREMENT,-- 交易信息编号
	Deal_SellerID bigint,-- 卖方会员编号
	Deal_BuyerID bigint,-- 买方会员编号
	Deal_BillID bigint,-- 交易票据编号
	Deal_Time timestamp-- 交易时间
);


