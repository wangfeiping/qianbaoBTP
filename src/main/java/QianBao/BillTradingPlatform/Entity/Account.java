package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;

public class Account implements Serializable {

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
	private double Account_Sum;

	public long getAccount_ID() {
		return Account_ID;
	}

	public void setAccount_ID(long account_ID) {
		Account_ID = account_ID;
	}

	public double getAccount_Sum() {
		return Account_Sum;
	}

	public void setAccount_Sum(double account_Sum) {
		Account_Sum = account_Sum;
	}

}
