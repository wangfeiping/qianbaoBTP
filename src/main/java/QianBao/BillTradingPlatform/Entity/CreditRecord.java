package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class CreditRecord implements Serializable{
	private static final long serialVersionUID = 84928651461078070L;

	private long CreditRecord_ID;
	private long CreditRecord_CreditOrganizationID;
	private long CreditRecord_UserID;
	private String CreditRecord_Repeal;
	private double CreditRecord_Limit;
	private Timestamp CreditRecord_Timestamp;
	public long getCreditRecord_ID() {
		return CreditRecord_ID;
	}
	public void setCreditRecord_ID(long creditRecord_ID) {
		CreditRecord_ID = creditRecord_ID;
	}
	public long getCreditRecord_CreditOrganizationID() {
		return CreditRecord_CreditOrganizationID;
	}
	public void setCreditRecord_CreditOrganizationID(
			long creditRecord_CreditOrganizationID) {
		CreditRecord_CreditOrganizationID = creditRecord_CreditOrganizationID;
	}
	public long getCreditRecord_UserID() {
		return CreditRecord_UserID;
	}
	public void setCreditRecord_UserID(long creditRecord_UserID) {
		CreditRecord_UserID = creditRecord_UserID;
	}
	public String getCreditRecord_Repeal() {
		return CreditRecord_Repeal;
	}
	public void setCreditRecord_Repeal(String creditRecord_Repeal) {
		CreditRecord_Repeal = creditRecord_Repeal;
	}
	public double getCreditRecord_Limit() {
		return CreditRecord_Limit;
	}
	public void setCreditRecord_Limit(double creditRecord_Limit) {
		CreditRecord_Limit = creditRecord_Limit;
	}
	public Timestamp getCreditRecord_Timestamp() {
		return CreditRecord_Timestamp;
	}
	public void setCreditRecord_Timestamp(Timestamp creditRecord_Timestamp) {
		CreditRecord_Timestamp = creditRecord_Timestamp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
