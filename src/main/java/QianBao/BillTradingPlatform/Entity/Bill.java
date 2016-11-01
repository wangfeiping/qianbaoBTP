package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;

public class Bill implements Serializable {
	/**
	 * 测试样例
	 * 
	 * @author 胥月
	 * @create 2016.11.1
	 * 
	 * 
	 */

	private static final long serialVersionUID = -5617689449383093592L;
	private long Bill_ID;
	private long Bill_UserID;
	private double Bill_Denomination;
	private double Bill_price;
	private String bill_AcceptingBank;
	private String bill_State;

	public long getBill_ID() {
		return Bill_ID;
	}

	public void setBill_ID(long bill_ID) {
		Bill_ID = bill_ID;
	}

	public long getBill_UserID() {
		return Bill_UserID;
	}

	public void setBill_UserID(long bill_UserID) {
		Bill_UserID = bill_UserID;
	}

	public double getBill_Denomination() {
		return Bill_Denomination;
	}

	public void setBill_Denomination(double bill_Denomination) {
		Bill_Denomination = bill_Denomination;
	}

	public double getBill_price() {
		return Bill_price;
	}

	public void setBill_price(double bill_price) {
		Bill_price = bill_price;
	}

	public String getBill_AcceptingBank() {
		return bill_AcceptingBank;
	}

	public void setBill_AcceptingBank(String bill_AcceptingBank) {
		this.bill_AcceptingBank = bill_AcceptingBank;
	}

	public String getBill_State() {
		return bill_State;
	}

	public void setBill_State(String bill_State) {
		this.bill_State = bill_State;
	}

}
