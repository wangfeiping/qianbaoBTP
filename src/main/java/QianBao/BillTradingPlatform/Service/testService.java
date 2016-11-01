package QianBao.BillTradingPlatform.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import QianBao.BillTradingPlatform.Entity.testEntity;

/**
 * 测试案例
 * 
 * @author 胥月
 * @create 2016.11.1
 */
@Service
public class testService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<testEntity> getList() {
		String sql = "SELECT a,b  FROM test";
		return (List<testEntity>) jdbcTemplate.query(sql,
				new RowMapper<testEntity>() {

					@Override
					public testEntity mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						testEntity p = new testEntity();
						p.setA(rs.getString("a"));
						p.setB(rs.getInt("b"));
						return p;
					}

				});
	}
}