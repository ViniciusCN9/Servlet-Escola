package connections;

import java.sql.*;

public class MySqlDbConnection implements IDbConnection {
	private static final String url = "jdbc:mysql://localhost:3306/escola";
	private static final String user = "root";
	private static final String pwd = "root";
	private Connection connection;
	
	public MySqlDbConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(url, user, pwd);
	}
	
	public ResultSet query(String sql) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement(sql);
		return statement.executeQuery();
	}
	
	public ResultSet query(String sql, String filter) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, filter);
		return statement.executeQuery();
	}
	
	public void execute(String sql, String[] parameters) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement(sql);
		int index = 1;
		for (String parameter : parameters) {
			statement.setString(index, parameter); index++;
		}
		statement.executeUpdate();
	}
	
	public void execute(String sql, String parameter) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, parameter);
		statement.executeUpdate();
	}	
}
