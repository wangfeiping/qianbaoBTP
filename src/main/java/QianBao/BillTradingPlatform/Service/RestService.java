package QianBao.BillTradingPlatform.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import QianBao.BillTradingPlatform.Entity.Account;
import QianBao.BillTradingPlatform.Entity.Credit;
import QianBao.BillTradingPlatform.Entity.Guaranteed;
import QianBao.BillTradingPlatform.Entity.Payment;
import QianBao.BillTradingPlatform.Entity.User;
import QianBao.BillTradingPlatform.Entity.testEntity;

/**
 * 测试案例
 * 
 * @author 胥月
 * @create 2016.11.1
 */
@Service
public class RestService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Logger logger;

	public long initUser(User user) {
		try {
			jdbcTemplate
					.update("insert into tb_user(User_Name,User_Password,User_State,User_AccountID,User_EnterpriseName,User_EnterpriseRegistrID,User_EnterprisetType,User_EnterprisetAddress) values(?,?,?,?,?,?,?,?)",
							new Object[] { user.getUser_Name(),
									user.getUser_Password(),
									user.getUser_State(),
									user.getUser_AccountID(),
									user.getUser_EnterpriseName(),
									user.getUser_EnterpriseRegistrID(),
									user.getUser_EnterpriseType(),
									user.getUser_EnterpriseAddress() });
			return getSequence("tb_user");
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}

	}

	public long initAccount(Account account) {
		try {
			jdbcTemplate
					.update("insert into tb_account(Account_GuaranteedID,Account_CreditID) values(?,?)",
							new Object[] { account.init(),
									account.getAccount_CreditID() });
			return getSequence("tb_account");
			;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}

	}

	public long initGuaranteed(Guaranteed Guaranteed) {
		try {
			jdbcTemplate
					.update("insert into tb_guaranteed(Guaranteed_UserID,Guaranteed_Limit) values(?,?)",
							new Object[] { Guaranteed.getGuaranteed_UserID(),
									Guaranteed.getGuaranteed_Limit() });
			return getSequence("tb_guaranteed");
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}

	};

	public long initCredit(Credit Credit) {
		return 0;
	};

	public int getSequence(String table) {
		List<Integer> list = jdbcTemplate.query("select last_insert_id() from "
				+ table, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		});
		if (list.isEmpty())
			return 0;
		return list.get(0);
	}

	public Object getByID(long id, String table) {
		String sql = "select * from " + table;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	public Object pay(long User_ID, double Payment_Sum) {
		try {
			jdbcTemplate
					.update("insert into tb_payment(Payment_UserID,Payment_State,Payment_Sum) values(?,?,?)",
							new Object[] { User_ID, "1", Payment_Sum });
			List<Map<String, Object>> list = jdbcTemplate
					.queryForList("select * from tb_payment where payment_id= "
							+ getSequence("tb_payment"));
			return list.isEmpty() ? null : list.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean payResponse(long Payment_ID) {
		try {
			jdbcTemplate.update("update tb_payment(Payment_State) values(2)");
			// 操作区块链;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
