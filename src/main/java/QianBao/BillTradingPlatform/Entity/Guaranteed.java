package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;

public class Guaranteed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8803640950480394454L;

	private long Guaranteed_ID;
	private double Guaranteed_Limit;
	private String Guaranteed_State;

	public long getGuaranteed_ID() {
		return Guaranteed_ID;
	}

	public void setGuaranteed_ID(long guaranteed_ID) {
		Guaranteed_ID = guaranteed_ID;
	}

	public double getGuaranteed_Limit() {
		return Guaranteed_Limit;
	}

	public void setGuaranteed_Limit(double guaranteed_Limit) {
		Guaranteed_Limit = guaranteed_Limit;
	}

	public String getGuaranteed_State() {
		return Guaranteed_State;
	}

	public void setGuaranteed_State(String guaranteed_State) {
		Guaranteed_State = guaranteed_State;
	}

}
