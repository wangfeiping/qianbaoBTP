package QianBao.BillTradingPlatform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import QianBao.BillTradingPlatform.Entity.Account;
import QianBao.BillTradingPlatform.Entity.Guaranteed;
import QianBao.BillTradingPlatform.Entity.Payment;
import QianBao.BillTradingPlatform.Entity.User;
import QianBao.BillTradingPlatform.Service.*;

/**
 * 平台Rest接口
 * 
 * @author 胥月
 * @create 2016.11.1
 * 
 * 
 */
@RestController
@RequestMapping("/demo")
public class Controller {
	@Autowired
	private RestService RestService;

	/* 会员注册 */
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

	/* 分页查看会员信息 */
	@RequestMapping("/getUsersBySize")
	public List<User> getUsersBySize(int size) {
		return null;
	}

	/* 查看指定会员信息 */
	@RequestMapping("/getUserByID")
	public Object getUserByID(@RequestParam("User_ID") long id) {
		return RestService.getByID(id, "user");
	}

	/* 充值 */
	@RequestMapping("/pay")
	public Object pay(@RequestParam("User_ID") long User_ID,
			@RequestParam("Payment_Sum") double Payment_Sum) {
		Payment payment = new Payment();
		payment.setPayment_UserID(User_ID);
		payment.setPayment_Type("1");
		payment.setPayment_Sum(Payment_Sum);
		payment.setPayment_State("1");
		return RestService.initPayment(payment);
	}

	/* 接收支付结果通知 */
	@RequestMapping("/payResult")
	public String payResult(@RequestParam("result") boolean result,
			@RequestParam("Payment_ID") long Payment_ID) {
		if (result) {
			if (RestService.payResponse(Payment_ID))
				// 操作区块链;
				return "success";
			else
				return "failure";
		} else
			return "failure";
	}

	/* 查看余额 */
	@RequestMapping("/checkBalance")
	public Object checkBalance(@RequestParam("User_ID") long User_ID) {
		return RestService.checkBalance(User_ID);
	}

	/* 提现 */
	@RequestMapping("/withdrawDeposit")
	public Object withdrawDeposit(@RequestParam("User_ID") long User_ID,
			@RequestParam("Payment_Sum") double sum) {
		Payment payment = new Payment();
		payment.setPayment_UserID(User_ID);
		payment.setPayment_Type("2");
		payment.setPayment_Sum(sum);
		payment.setPayment_State("1");
		// 调用支付接口
		return RestService.initPayment(payment);
	}
	
	/*授信*/
}
