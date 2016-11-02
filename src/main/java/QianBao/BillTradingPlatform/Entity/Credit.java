package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;

public class Credit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8492865149961078070L;

	private long Credit_ID;
	private double Credit_Limit;
	private String Credit_State;
	private long Credit_CreditOrganizationID;

	public long getCredit_ID() {
		return Credit_ID;
	}

	public void setCredit_ID(long credit_ID) {
		Credit_ID = credit_ID;
	}

	public double getCredit_Limit() {
		return Credit_Limit;
	}

	public void setCredit_Limit(double credit_Limit) {
		Credit_Limit = credit_Limit;
	}

	public String getCredit_State() {
		return Credit_State;
	}

	public void setCredit_State(String credit_State) {
		Credit_State = credit_State;
	}

	public long getCredit_CreditOrganizationID() {
		return Credit_CreditOrganizationID;
	}

	public void setCredit_CreditOrganizationID(long credit_CreditOrganizationID) {
		Credit_CreditOrganizationID = credit_CreditOrganizationID;
	}

}
