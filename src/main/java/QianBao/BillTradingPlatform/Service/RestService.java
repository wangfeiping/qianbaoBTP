package QianBao.BillTradingPlatform.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import QianBao.BillTradingPlatform.Entity.Account;
import QianBao.BillTradingPlatform.Entity.Bill;
import QianBao.BillTradingPlatform.Entity.Credit;
import QianBao.BillTradingPlatform.Entity.Guaranteed;
import QianBao.BillTradingPlatform.Entity.Payment;
import QianBao.BillTradingPlatform.Entity.User;
import QianBao.BillTradingPlatform.Entity.testEntity;

/**
 * 
 * @author Yue.Xu
 * @create 2016.11.1
 */
@Service
public class RestService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Logger logger;

	public long initUser(User user) {
		try {
			jdbcTemplate.update(
					"insert into tb_user(User_Name,User_Password,User_State,"
							+ "User_AccountID,User_GuaranteedID,User_CreditID,"
							+ "User_EnterpriseName,User_EnterpriseRegistrID,"
							+ "User_EnterprisetType,User_EnterprisetAddress) "
							+ "values(?,?,?,?,?,?,?,?,?,?)",
					new Object[] { user.getUser_Name(),
							user.getUser_Password(), user.getUser_State(),
							initAccount(new Account()),
							initGuaranteed(new Guaranteed()),
							initCredit(new Credit()),
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
			jdbcTemplate.update(
					"insert into tb_account(Account_Sum) values(?)",
					new Object[] { account.getAccount_Sum() });
			return getSequence("tb_account");
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}

	}

	public long initGuaranteed(Guaranteed Guaranteed) {
		try {
			jdbcTemplate
					.update("insert into tb_guaranteed(Guaranteed_Limit,Guaranteed_State) values(?,?)",
							new Object[] { Guaranteed.getGuaranteed_Limit(),
									Guaranteed.getGuaranteed_State() });
			return getSequence("tb_guaranteed");
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}

	}

	public long initCredit(Credit Credit) {
		try {
			jdbcTemplate
					.update("insert into tb_credit(Credit_Limit,Credit_State,Credit_CreditOrganizationID) values(?,?,?)",
							new Object[] { Credit.getCredit_Limit(),
									Credit.getCredit_State(),
									Credit.getCredit_CreditOrganizationID() });
			return getSequence("tb_guaranteed");
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
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
		String sql = "select * from tb_" + table + " where " + table + "_id="
				+ id;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	public boolean payResponse(long Payment_ID) {
		try {
			jdbcTemplate
					.update("update tb_payment(Payment_State) values(2) where Payment_ID="
							+ Payment_ID);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public long initBill(Bill bill) {
		try {
			jdbcTemplate
					.update("insert into tb_bill(Bill_UserID,Bill_Denomination,Bill_price,bill_AcceptingBank,bill_State) values(?,?,?,?,?)",
							new Object[] { bill.getBill_UserID(),
									bill.getBill_Denomination(),
									bill.getBill_price(),
									bill.getBill_AcceptingBank(),
									bill.getBill_State() });
			return getSequence("tb_bill");
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}

	}

	public Object initPayment(Payment Payment) {
		try {
			jdbcTemplate.update(
					"insert into tb_payment(Payment_UserID,Payment_Type,Payment_State"
							+ ",Payment_Sum) values(?,?,?,?)",
					new Object[] { Payment.getPayment_UserID(),
							Payment.getPayment_Type(),
							Payment.getPayment_State(),
							Payment.getPayment_Sum() });
			List<Map<String, Object>> list = jdbcTemplate
					.queryForList("select * from tb_payment where payment_id= "
							+ getSequence("tb_payment"));
			return list.isEmpty() ? null : list.get(0);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Object checkBalance(long User_ID) {
		try {
			String sql = "select User_AccountID,User_GuaranteedID,User_CreditID from tb_user "
					+ "where User_ID= " + User_ID;
			Map<String, Object> map = jdbcTemplate.queryForMap(sql);
			String sql_account = "select * from tb_account where Account_ID= "
					+ map.get("User_AccountID").toString();
			String sql_guaranteed = "select * from tb_guaranteed where Guaranteed_ID= "
					+ map.get("User_GuaranteedID").toString();
			String sql_credit = "select * from tb_credit where Credit_ID= "
					+ map.get("User_CreditID").toString();
			Map<String, Object> output = new HashMap<String, Object>();
			output.putAll(jdbcTemplate.queryForMap(sql_account));
			output.putAll(jdbcTemplate.queryForMap(sql_guaranteed));
			output.putAll(jdbcTemplate.queryForMap(sql_credit));
			return output;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Object Credit(long CreditOrganization_ID, long User_ID,
			double Credit_Limit) {
		try {
			String sql = "select User_CreditID from tb_user where User_ID="
					+ User_ID;
			List<Credit> list = jdbcTemplate.query(sql,
					new RowMapper<Credit>() {

						@Override
						public Credit mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Credit credit = new Credit();
							credit.setCredit_ID(rs.getLong(1));
							return credit;
						}
					});
			long Credit_ID = list.get(0).getCredit_ID();
			jdbcTemplate.update(
					"update tb_credit set Credit_CreditOrganizationID=?,Credit_Limit=?"
							+ " where Credit_ID=" + Credit_ID, new Object[] {
							CreditOrganization_ID, Credit_Limit });
			return getByID(Credit_ID, "credit");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public Object getByPage(int pageIndex, int pageSize,String table) {
		String sql = "select * from tb_" + table + " where " + table + "_id>"
	+(pageSize*(pageIndex-1)) +" and "+ table + "_id<="+(pageSize*pageIndex);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if (list.isEmpty())
			return null;
		return list;
	}
	
	public Object getByPageTime(int pageIndex, int pageSize,Timestamp startTime,Timestamp endTime,String table) {
		String sql = "select * from tb_" + table + " where " + table + "_id>"
	+(pageSize*(pageIndex-1)) +" and "+ table + "_id<="+(pageSize*pageIndex)
	+" and "+ table + "_Timestamp>='"+startTime+"' and "+ table + "_Timestamp<='"+endTime+"'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if (list.isEmpty())
			return null;
		return list;
	}
	public Object getByPage1(int pageIndex, int pageSize,long conditionvalue,String condition,String table) {
		String sql = "select * from tb_" + table + " where " + table + "_id>"
	+(pageSize*(pageIndex-1)) +" and "+ table + "_id<="+(pageSize*pageIndex)
	+" and "+ table + "_"+condition+" = "+ conditionvalue ;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if (list.isEmpty())
			return null;
		return list;
	}
	public Object getByPage2(int pageIndex, int pageSize,long conditionvalue1,String condition1,long conditionvalue2,String condition2,String table) {
		String sql = "select * from tb_" + table + " where " + table + "_id>"
	+(pageSize*(pageIndex-1)) +" and "+ table + "_id<="+(pageSize*pageIndex)
	+" and "+ table + "_"+condition1+" = "+ conditionvalue1
	+" and "+ table + "_"+condition2+" = "+ conditionvalue2;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if (list.isEmpty())
			return null;
		return list;
	}
	
}
