package net.franckbenault.checkdb.input;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private DatabaseType databaseType;
	private String url;
	private String user;
	private String password;
	
	public DatabaseConnection(DatabaseType databaseType, String url, String user, String password) {
		this.databaseType = databaseType;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Connection connection = null;
		
		if(DatabaseType.HSQLDB.equals(databaseType))
			Class.forName("org.hsqldb.jdbcDriver");
		else
			return null;
		
		connection = DriverManager.getConnection(url, user , password);
		
		return connection;
		
	}
}
