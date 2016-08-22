package ihundan.wukonglin.hsqldb;

import ihundan.wukonglin.hsqldb.util.HsqldbFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class TestHsqldbFactory {
	public Connection connection;
	
	@Before
	public void init() {
		connection = HsqldbFactory.getConnection();
	}
	
	@Test
	public void test_01() {
		try {
			String sql = "create table tb_user (objid  TINYINT  primary key, username varchar(50) not null, password varchar(50) not null)";
			Statement statement = connection.createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
