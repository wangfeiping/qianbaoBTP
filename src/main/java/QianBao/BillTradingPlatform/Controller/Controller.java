package QianBao.BillTradingPlatform.Controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import QianBao.BillTradingPlatform.Entity.Account;
import QianBao.BillTradingPlatform.Entity.Bill;
import QianBao.BillTradingPlatform.Entity.CreditRecord;
import QianBao.BillTradingPlatform.Entity.Guaranteed;
import QianBao.BillTradingPlatform.Entity.Payment;
import QianBao.BillTradingPlatform.Entity.User;
import QianBao.BillTradingPlatform.Service.*;

/**
 * 
 * 
 * @author Yue.Xu
 * @create 2016.11.1
 * 
 * 
 */
@RestController
@RequestMapping("/demo")
public class Controller {
	@Autowired
	private RestService RestService;

	@RequestMapping("/UserRegister")
	public String UserRegister(
			@RequestParam("User_Name") String User_Name,
			@RequestParam("User_Password") String User_Password,
			@RequestParam("User_State") String User_State,
			@RequestParam("User_EnterpriseName") String User_EnterpriseName,
			@RequestParam("User_EnterpriseRegistrID") String User_EnterpriseRegistrID,
			@RequestParam("User_EnterprisetType") String User_EnterprisetType,
			@RequestParam("User_EnterprisetAddress") String User_EnterprisetAddress) {
		User new_user = new User();
		new_user.setUser_Name(User_Name);
		new_user.setUser_Password(User_Password);
		new_user.setUser_State(User_State);
		new_user.setUser_EnterpriseName(User_EnterpriseName);
		new_user.setUser_EnterpriseRegistrID(User_EnterpriseRegistrID);
		new_user.setUser_EnterpriseType(User_EnterprisetType);
		new_user.setUser_EnterpriseAddress(User_EnterprisetAddress);
		return RestService.initUser(new_user) != 0 ? "success" : "failure";
	}

	@RequestMapping("/getUsersBySize")
	public List<User> getUsersBySize(int size) {
		return null;
	}

	@RequestMapping("/getUserByID")
	public Object getUserByID(@RequestParam("User_ID") long id) {
		return RestService.getByID(id, "user");
	}

	@RequestMapping("/pay")
	public Object pay(@RequestParam("User_ID") long User_ID,
			@RequestParam("Payment_Sum") double Payment_Sum) {
		Payment payment = new Payment();
		payment.setPayment_UserID(User_ID);
		payment.setPayment_Type("2");
		payment.setPayment_Sum(Payment_Sum);
		payment.setPayment_State("1");
		return RestService.initPayment(payment);
	}

	@RequestMapping("/payResult")
	public String payResult(@RequestParam("result") boolean result,
			@RequestParam("Payment_ID") long Payment_ID) {
		if (result) {
			if (RestService.payResponse(Payment_ID))
				return "success";
			else
				return "failure";
		} else
			return "failure";
	}

	@RequestMapping("/BillInput")
	public String BillInput(@RequestParam("Bill_UserID") long Bill_UserID,
			@RequestParam("Bill_Denomination") double Bill_Denomination,
			@RequestParam("Bill_Price") double Bill_Price,
			@RequestParam("Bill_AcceptingBank") String Bill_AcceptingBank,
			@RequestParam("Bill_State") String Bill_State) {
		Bill new_bill = new Bill();
		new_bill.setBill_UserID(Bill_UserID);
		new_bill.setBill_Denomination(Bill_Denomination);
		new_bill.setBill_price(Bill_Price);
		new_bill.setBill_AcceptingBank(Bill_AcceptingBank);
		new_bill.setBill_State(Bill_State);
		return RestService.initBill(new_bill) != 0 ? "success" : "failure";
	}

	@RequestMapping("/getBillByID")
	public Object getBillByID(@RequestParam("Bill_ID") long id) {
		return RestService.getByID(id, "bill");
	}

	@RequestMapping("/checkBalance")
	public Object checkBalance(@RequestParam("User_ID") long User_ID) {
		return RestService.checkBalance(User_ID);
	}

	@RequestMapping("/withdrawDeposit")
	public Object withdrawDeposit(@RequestParam("User_ID") long User_ID,
			@RequestParam("Payment_Sum") double Payment_Sum) {
		Payment payment = new Payment();
		payment.setPayment_UserID(User_ID);
		payment.setPayment_Type("2");
		payment.setPayment_Sum(Payment_Sum);
		payment.setPayment_State("1");

		return RestService.initPayment(payment);
	}

	@RequestMapping("/credit")
	public Object Credit(
			@RequestParam("CreditOrganization_ID") long CreditOrganization_ID,
			@RequestParam("User_ID") long User_ID,
			@RequestParam("Credit_Limit") double Credit_Limit) {
		return RestService.Credit(CreditOrganization_ID, User_ID, Credit_Limit);
	}

	@RequestMapping("/withdrawCredit")
	public Object withdrawCredit(
			@RequestParam("CreditOrganization_ID") long CreditOrganization_ID,
			@RequestParam("User_ID") long User_ID,
			@RequestParam("Credit_Limit") double Credit_Limit) {
		return RestService.withdrawCredit(CreditOrganization_ID, User_ID,
				Credit_Limit);
	}

	@RequestMapping("/getUsersByPageIndex")
	public List<User> getUsersBySize(@RequestParam("pageIndex") int pageIndex,
			@RequestParam("pageSize") int pageSize) {
		return (List<User>) RestService.getByPage(pageIndex, pageSize, "User");
	}

	@RequestMapping("/getCreditRecordByPageIndex")
	public List<CreditRecord> getCreditRecordByPageIndex(
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("pageSize") int pageSize) {
		return (List<CreditRecord>) RestService.getByPage(pageIndex, pageSize,
				"CreditRecord");
	}

	@RequestMapping("/getCreditRecordByPageTime")
	public List<CreditRecord> getCreditRecordByPageTime(
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("startTime") Timestamp startTime,
			@RequestParam("endTime") Timestamp endTime) {
		return (List<CreditRecord>) RestService.getByPageTime(pageIndex,
				pageSize, startTime, endTime, "CreditRecord");
	}

	@RequestMapping("/getCreditRecordByPageUser")
	public List<CreditRecord> getCreditRecordByPageUser(
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("userID") long userID) {
		return (List<CreditRecord>) RestService.getByPage1(pageIndex, pageSize,
				userID, "userID", "CreditRecord");
	}

	@RequestMapping("/getCreditRecordByPageOrg")
	public List<CreditRecord> getCreditRecordByPageOrg(
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("CreditRecord_CreditOrganizationID") long CreditRecord_CreditOrganizationID) {
		return (List<CreditRecord>) RestService.getByPage1(pageIndex, pageSize,
				CreditRecord_CreditOrganizationID, "CreditOrganizationID",
				"CreditRecord");
	}

	@RequestMapping("/getCreditRecordByPageUO")
	public List<CreditRecord> getCreditRecordByPageUO(
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("userID") long userID,
			@RequestParam("CreditRecord_CreditOrganizationID") long CreditRecord_CreditOrganizationID) {
		return (List<CreditRecord>) RestService.getByPage2(pageIndex, pageSize,
				userID, "userID", CreditRecord_CreditOrganizationID,
				"CreditOrganizationID", "CreditRecord");
	}

	@RequestMapping("/getCreditRecordByID")
	public Object getCreditRecordByID(@RequestParam("CreditRecord_ID") long id) {
		return RestService.getByID(id, "CreditRecord");
	}

	@RequestMapping("/getBillByPageIndex")
	public List<Bill> getBillByPageIndex(
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("pageSize") int pageSize) {
		return (List<Bill>) RestService.getByPage(pageIndex, pageSize, "Bill");
	}

	@RequestMapping("/deal")
	public Object deal(@RequestParam("Deal_SellerID") long Deal_SellerID,
			@RequestParam("Deal_BuyerId") long Deal_BuyerId,
			@RequestParam("Bill_ID") long Bill_ID) {
		return RestService.deal(Deal_SellerID, Deal_BuyerId, Bill_ID);
	}

	@RequestMapping("/getDealByID")
	public Object getDealByID(@RequestParam("Deal_ID") long id) {
		return RestService.getByID(id, "Deal");
	}
}
