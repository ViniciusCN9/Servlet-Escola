package connections;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDbConnection {	
	public ResultSet query(String sql) throws SQLException;
	public ResultSet query(String sql, String filter) throws SQLException;
	public void execute(String sql, String[] parameters) throws SQLException;
	public void execute(String sql, String parameter) throws SQLException;
}
