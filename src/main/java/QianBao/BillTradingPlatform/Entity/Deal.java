package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Deal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1124754650834117233L;

	private long Deal_ID;
	private long Deal_SellerID;
	private long Deal_BuyerId;
	private long Bill_ID;
	private Timestamp Deal_Time;

	public long getDeal_ID() {
		return Deal_ID;
	}

	public void setDeal_ID(long deal_ID) {
		Deal_ID = deal_ID;
	}

	public long getDeal_SellerID() {
		return Deal_SellerID;
	}

	public void setDeal_SellerID(long deal_SellerID) {
		Deal_SellerID = deal_SellerID;
	}

	public long getDeal_BuyerId() {
		return Deal_BuyerId;
	}

	public void setDeal_BuyerId(long deal_BuyerId) {
		Deal_BuyerId = deal_BuyerId;
	}

	public long getBill_ID() {
		return Bill_ID;
	}

	public void setBill_ID(long bill_ID) {
		Bill_ID = bill_ID;
	}

	public Timestamp getDeal_Time() {
		return Deal_Time;
	}

	public void setDeal_Time(Timestamp deal_Time) {
		Deal_Time = deal_Time;
	}

}
