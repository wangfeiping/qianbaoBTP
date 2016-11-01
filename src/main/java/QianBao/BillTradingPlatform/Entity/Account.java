package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;

public class Account implements Serializable{

	/**
	 * 测试样例
	 * 
	 * @author 胥月
	 * @create 2016.11.1
	 * 
	 * 
	 */
	private static final long serialVersionUID = 6105486336631028203L;
	
	private long Account_ID;
	private long Account_GuaranteedID;
	private long Account_CreditID;
	public long getAccount_ID() {
		return Account_ID;
	}
	public void setAccount_ID(long account_ID) {
		Account_ID = account_ID;
	}
	public long getAccount_GuaranteedID() {
		return Account_GuaranteedID;
	}
	public void setAccount_GuaranteedID(long account_GuaranteedID) {
		Account_GuaranteedID = account_GuaranteedID;
	}
	public long getAccount_CreditID() {
		return Account_CreditID;
	}
	public void setAccount_CreditID(long account_CreditID) {
		Account_CreditID = account_CreditID;
	}
	
}
