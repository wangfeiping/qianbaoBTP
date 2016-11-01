package QianBao.BillTradingPlatform.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import QianBao.BillTradingPlatform.Entity.Account;
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

	public boolean putUser(User user) {
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
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean putAccount(Account account) {
		return true;
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
}
