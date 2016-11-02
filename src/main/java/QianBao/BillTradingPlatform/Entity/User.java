package QianBao.BillTradingPlatform.Entity;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 测试样例
	 * 
	 * @author 胥月
	 * @create 2016.11.1
	 * 
	 * 
	 */
	private static final long serialVersionUID = 4236265357506873901L;

	private long User_ID;
	private long User_AccountID;
	private long User_GuaranteedID;
	private long User_CreditID;
	private String User_Name;
	private String User_Password;
	private String User_State;
	private String User_EnterpriseName;
	private String User_EnterpriseRegistrID;
	private String User_EnterpriseType;
	private String User_EnterpriseAddress;

	public long getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(long user_ID) {
		User_ID = user_ID;
	}

	public long getUser_AccountID() {
		return User_AccountID;
	}

	public long getUser_GuaranteedID() {
		return User_GuaranteedID;
	}

	public void setUser_GuaranteedID(long user_GuaranteedID) {
		User_GuaranteedID = user_GuaranteedID;
	}

	public long getUser_CreditID() {
		return User_CreditID;
	}

	public void setUser_CreditID(long user_CreditID) {
		User_CreditID = user_CreditID;
	}

	public void setUser_AccountID(long user_AccountID) {
		User_AccountID = user_AccountID;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	public String getUser_Password() {
		return User_Password;
	}

	public void setUser_Password(String user_Password) {
		User_Password = user_Password;
	}

	public String getUser_State() {
		return User_State;
	}

	public void setUser_State(String user_State) {
		User_State = user_State;
	}

	public String getUser_EnterpriseRegistrID() {
		return User_EnterpriseRegistrID;
	}

	public void setUser_EnterpriseRegistrID(String user_EnterpriseRegistrID) {
		User_EnterpriseRegistrID = user_EnterpriseRegistrID;
	}

	public String getUser_EnterpriseType() {
		return User_EnterpriseType;
	}

	public void setUser_EnterpriseType(String user_EnterpriseType) {
		User_EnterpriseType = user_EnterpriseType;
	}

	public String getUser_EnterpriseAddress() {
		return User_EnterpriseAddress;
	}

	public void setUser_EnterpriseAddress(String user_EnterpriseAddress) {
		User_EnterpriseAddress = user_EnterpriseAddress;
	}

	public String getUser_EnterpriseName() {
		return User_EnterpriseName;
	}

	public void setUser_EnterpriseName(String user_EnterpriseName) {
		User_EnterpriseName = user_EnterpriseName;
	}

}
