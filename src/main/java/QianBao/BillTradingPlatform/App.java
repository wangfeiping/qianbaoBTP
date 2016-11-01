package QianBao.BillTradingPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * application
 * 
 * @author 胥月
 * @create 2016.11.1
 * 
 * 
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}
}