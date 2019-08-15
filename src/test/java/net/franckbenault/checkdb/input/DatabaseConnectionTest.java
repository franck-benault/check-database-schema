package net.franckbenault.checkdb.input;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import net.franckbenault.hsqldb.DBServer;

@DisplayName("Database connection tests")
@RunWith(JUnitPlatform.class)
public class DatabaseConnectionTest {
	

	@DisplayName("getConnection no database type")
	@Test
	void testGetConnectionNoDatabaseType() throws ClassNotFoundException, SQLException {
		
		DatabaseConnection dbConnection = 
				new DatabaseConnection(
						null,
						"jdbc:hsqldb:mem:mymemdb", "SA", "");
		
		
		Connection connection = dbConnection.getConnection();
		
		assertNull(connection);
		
	}
	

	@DisplayName("getConnection HSQLDB memory")
	@Test
	void testGetConnectionHSQLDB_memory() throws ClassNotFoundException, SQLException {
		
		DatabaseConnection dbConnection = 
				new DatabaseConnection(
						DatabaseType.HSQLDB,
						"jdbc:hsqldb:mem:mymemdb", "SA", "");
		
		
		Connection connection = dbConnection.getConnection();
		
		assertNotNull(connection);
		
	}
	
	@DisplayName("getConnection HSQLDB server")
	@Test
	void testGetConnectionHSQLDB_Server() throws ClassNotFoundException, SQLException {
		
		DBServer server = new DBServer();
		server.start();
		server.createTable("FOO");
		
		DatabaseConnection dbConnection = 
				new DatabaseConnection(
						DatabaseType.HSQLDB,
						"jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
		
		
		Connection connection = dbConnection.getConnection();
		
		assertNotNull(connection);
		server.stop();
		
	}

}
