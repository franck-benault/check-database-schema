package net.franckbenault.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hsqldb.Server;

public class DBServer {
	
	private Server hsqlServer;
	
	public DBServer() {
		 
		    
         hsqlServer = new Server();

         // HSQLDB prints out a lot of informations when
         // starting and closing, which we don't need now.
         // Normally you should point the setLogWriter
         // to some Writer object that could store the logs.
         hsqlServer.setLogWriter(null);
         hsqlServer.setSilent(true);

         // The actual database will be named 'xdb' and its
         // settings and data will be stored in files
         // testdb.properties and testdb.script
         hsqlServer.setDatabaseName(0, "xdb");
         hsqlServer.setDatabasePath(0, "file:testdb");


	}
	
	public void start() {

        hsqlServer.start();
	}
	
	public void stop() {

        hsqlServer.stop();
	}
	
	public void createTable(String tableName) {
		Connection connection = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection(
			        "jdbc:hsqldb:hsql://localhost/xdb", "sa", "");
			
			connection.prepareStatement(
			        "create table "+ tableName+" ( id INTEGER, "+
			        "name VARCHAR(15));")
			        .execute();
		} catch (ClassNotFoundException | SQLException e ) {
			e.printStackTrace();
		} finally {
			if(connection!= null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}
