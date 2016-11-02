package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;

public class Credit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8492865149961078070L;

	private long Credit_ID;
	private long Credit_UserID;
	private double Credit_Limit;
	private long Credit_CreditOrganizationID;

	public long getCredit_ID() {
		return Credit_ID;
	}

	public void setCredit_ID(long credit_ID) {
		Credit_ID = credit_ID;
	}

	public long getCredit_UserID() {
		return Credit_UserID;
	}

	public void setCredit_UserID(long credit_UserID) {
		Credit_UserID = credit_UserID;
	}

	public double getCredit_Limit() {
		return Credit_Limit;
	}

	public void setCredit_Limit(double credit_Limit) {
		Credit_Limit = credit_Limit;
	}

	public long getCredit_CreditOrganizationID() {
		return Credit_CreditOrganizationID;
	}

	public void setCredit_CreditOrganizationID(long credit_CreditOrganizationID) {
		Credit_CreditOrganizationID = credit_CreditOrganizationID;
	}

}
