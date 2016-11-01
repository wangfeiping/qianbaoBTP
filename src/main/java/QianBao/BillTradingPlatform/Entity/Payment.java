package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8135337641027044194L;
	private long Payment_UserID;
	private Timestamp payment_ApplyTime;
	private String payment_StateString;
	private double Payment_Sum;

	public long getPayment_UserID() {
		return Payment_UserID;
	}

	public void setPayment_UserID(long payment_UserID) {
		Payment_UserID = payment_UserID;
	}

	public Timestamp getPayment_ApplyTime() {
		return payment_ApplyTime;
	}

	public void setPayment_ApplyTime(Timestamp payment_ApplyTime) {
		this.payment_ApplyTime = payment_ApplyTime;
	}

	public String getPayment_StateString() {
		return payment_StateString;
	}

	public void setPayment_StateString(String payment_StateString) {
		this.payment_StateString = payment_StateString;
	}

	public double getPayment_Sum() {
		return Payment_Sum;
	}

	public void setPayment_Sum(double payment_Sum) {
		Payment_Sum = payment_Sum;
	}

}
