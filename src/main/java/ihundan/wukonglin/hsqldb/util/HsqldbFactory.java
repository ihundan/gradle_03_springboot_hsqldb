package ihundan.wukonglin.hsqldb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class HsqldbFactory {
	
	/**
	 * 以外提供
	 * @param databaseName
	 * @param username
	 * @param password
	 * @return
	 */
	public static Connection getConnection(String databaseName,String username,String password) {
		Connection connection = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:mem:"+databaseName, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * 供测试使用
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:mem:ihundan", "sa", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 直接运行sql,
	 * @param connection
	 * @param sql
	 */
	public static boolean runSql(Connection connection,String sql) {
		try {
			Statement statement = connection.createStatement();
			return statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 直接运行sql,
	 * @param connection
	 * @param sql
	 */
	public static boolean runSql(Connection connection,String sql,Map<String,Object> params) {
		boolean returnBoolean = false;
		try {
			Statement statement = connection.createStatement();
			returnBoolean = statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnBoolean;
	}
	
	/**
	 * 直接运行sql,
	 * @param connection
	 * @param sql
	 */
	public static void runSql(Connection connection,String sql,List<Object> params) {
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
