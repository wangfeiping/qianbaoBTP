package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;

public class Guaranteed implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8803640950480394454L;

	private long Guaranteed_ID;
	private long Guaranteed_UserID;
	private double Guaranteed_Limit;

	public long getGuaranteed_ID() {
		return Guaranteed_ID;
	}

	public void setGuaranteed_ID(long guaranteed_ID) {
		Guaranteed_ID = guaranteed_ID;
	}

	public long getGuaranteed_UserID() {
		return Guaranteed_UserID;
	}

	public void setGuaranteed_UserID(long guaranteed_UserID) {
		Guaranteed_UserID = guaranteed_UserID;
	}

	public double getGuaranteed_Limit() {
		return Guaranteed_Limit;
	}

	public void setGuaranteed_Limit(double guaranteed_Limit) {
		Guaranteed_Limit = guaranteed_Limit;
	}

}
