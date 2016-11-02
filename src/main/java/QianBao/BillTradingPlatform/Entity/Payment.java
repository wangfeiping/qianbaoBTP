package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8135337641027044194L;
	private long Payment_UserID;
	private Timestamp Payment_ApplyTime;
	private String Payment_Type;
	private String Payment_State;
	private double Payment_Sum;

	public long getPayment_UserID() {
		return Payment_UserID;
	}

	public void setPayment_UserID(long payment_UserID) {
		Payment_UserID = payment_UserID;
	}

	public Timestamp getPayment_ApplyTime() {
		return Payment_ApplyTime;
	}

	public void setPayment_ApplyTime(Timestamp payment_ApplyTime) {
		Payment_ApplyTime = payment_ApplyTime;
	}

	public String getPayment_Type() {
		return Payment_Type;
	}

	public void setPayment_Type(String payment_Type) {
		Payment_Type = payment_Type;
	}

	public String getPayment_State() {
		return Payment_State;
	}

	public void setPayment_State(String payment_State) {
		Payment_State = payment_State;
	}

	public double getPayment_Sum() {
		return Payment_Sum;
	}

	public void setPayment_Sum(double payment_Sum) {
		Payment_Sum = payment_Sum;
	}

}
