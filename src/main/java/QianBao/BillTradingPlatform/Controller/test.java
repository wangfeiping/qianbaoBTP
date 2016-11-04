package QianBao.BillTradingPlatform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import QianBao.BillTradingPlatform.Entity.testEntity;
import QianBao.BillTradingPlatform.Service.testService;

/**
 * 测试样例
 * 
 * @author 胥月
 * @create 2016.11.1
 * 
 * 
 */
@RestController
@RequestMapping("/test")
public class test {
	@Autowired
	private testService testService;

	@RequestMapping("/test1")
	public String getScoreList(@RequestBody String s) {
		System.out.println(s);
		return s;
	}
}