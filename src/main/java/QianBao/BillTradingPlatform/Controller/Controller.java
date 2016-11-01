package QianBao.BillTradingPlatform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		new_user.setUser_AccountID(RestService.getSequence("tb_account")+1);
		return RestService.putUser(new_user) ? "success" : "failure";
	}
}
