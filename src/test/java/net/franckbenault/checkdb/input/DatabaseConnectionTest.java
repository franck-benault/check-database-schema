package net.franckbenault.checkdb.input;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@DisplayName("Database connection tests")
@RunWith(JUnitPlatform.class)
public class DatabaseConnectionTest {
	

	@DisplayName("get connection HSQLDB")
	@Test
	void testGetConnection() throws ClassNotFoundException, SQLException {
		
		DatabaseConnection dbConnection = 
				new DatabaseConnection(
						DatabaseType.HSQLDB,
						"jdbc:hsqldb:mem:mymemdb", "SA", "");
		
		
		Connection connection = dbConnection.getConnection();
		
		assertNotNull(connection);
		
	}

}
