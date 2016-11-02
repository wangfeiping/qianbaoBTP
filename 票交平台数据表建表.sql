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

-- ��Ա��
create table TB_User(
	User_ID bigint primary key AUTO_INCREMENT,--  ��Ա���
	User_AccountID bigint,-- ��Ա��Ӧ���˻����
	User_GuaranteedID bigint,-- ��Ա��Ӧ�ĵ����˻����
	User_CreditID bigint,-- ��Ա��Ӧ�������˻����
	User_Name varchar(20),-- ��Ա��¼�û���
	User_Password varchar(32),-- ��Ա��¼����
	User_State varchar(100),-- ��Ա״̬
	User_EnterpriseName varchar(100),-- ��Ա��˾����
	User_EnterpriseRegistrID varchar(100),-- ��Ա��˾ע���
	User_EnterprisetType varchar(100),-- ��Ա��˾����
	User_EnterprisetAddress varchar(100)-- ��Ա��˾��ַ

);

-- Ʊ�ݱ�
create table TB_Bill(
	Bill_ID bigint primary key AUTO_INCREMENT,-- Ʊ�ݱ��
	Bill_UserID bigint,-- Ʊ���������û����
	Bill_Denomination decimal(20,2),-- Ʊ�����
	Bill_Price decimal(20,2),-- Ʊ�ݳ��ۼ۸�
	Bill_AcceptingBank varchar(100),-- Ʊ�ݳж���
	Bill_State varchar(100)-- Ʊ��״̬
);

-- �û��˻���
create table TB_Account(
	Account_ID bigint primary key AUTO_INCREMENT,-- �û��˻����
	Account_Sum decimal(20,2)-- ��Ա�˻��ʽ�
);

-- �����˻���
create table TB_Guaranteed(
	Guaranteed_ID bigint primary key AUTO_INCREMENT,-- �����˻����
	Guaranteed_Limit decimal(20,2),-- �������
	Guaranteed_State varchar(100)-- �����˻�״̬
);

-- �����˻���
create table TB_Credit(
	Credit_ID bigint primary key AUTO_INCREMENT,-- �����˻����
	Credit_Limit decimal(20,2),-- ���Ŷ��
	Credit_State varchar(100),-- �����˻�״̬
	Credit_CreditOrganizationID bigint-- ���Ż������
);

-- ���Ż�����
create table TB_CreditOrganization(
	CreditOrganization_ID bigint primary key AUTO_INCREMENT,-- ���Ż������
	CreditOrganization_Name varchar(100)-- ���Ż�������
);

-- ���ż�¼��
create table TB_CreditRecord(
	CreditRecord_ID bigint primary key AUTO_INCREMENT,-- ���ż�¼���
	CreditRecord_CreditOrganizationID bigint,-- ���Ż������
	CreditRecord_UserID bigint,-- �����Ż�Ա���
	CreditRecord_Repeal varchar(100),-- �Ƿ��ǳ�������
	CreditRecord_Limit decimal(20,2),-- ���Ŷ��
	CreditRecord_Timestamp timestamp-- ����ʱ��
);

-- ֧��������
create table TB_Payment(
	Payment_ID bigint primary key AUTO_INCREMENT,-- ֧���������
	Payment_UserID bigint,-- ֧�����û����
	Payment_Type varchar(100),-- �������ͣ�1����ֵ������2�����ֶ�����
	Payment_ApplyTime timestamp,-- ֧����������ʱ��
	Payment_State varchar(100),-- ֧������״̬
	Payment_Sum decimal(20,2)-- ֧�����
);

-- ������Ϣ��
create table TB_Deal(
	Deal_ID bigint primary key AUTO_INCREMENT,-- ������Ϣ���
	Deal_SellerID bigint,-- ������Ա���
	Deal_BuyerID bigint,-- �򷽻�Ա���
	Deal_BillID bigint,-- ����Ʊ�ݱ��
	Deal_Time timestamp-- ����ʱ��
);


