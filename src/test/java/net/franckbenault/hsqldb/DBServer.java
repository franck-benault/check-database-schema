package net.franckbenault.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.hsqldb.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBServer {
	
	private static final Logger logger = LoggerFactory.getLogger("SampleLogger");
	
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
        logger.info("DB started");
	}
	
	public void stop() {

        hsqlServer.stop();
        logger.info("DB stopped");
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

	public void dropTable(String tableName) {
		Connection connection = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection(
			        "jdbc:hsqldb:hsql://localhost/xdb", "sa", "");
			
			connection.prepareStatement(
			        "drop table "+ tableName+";")
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

	public void createTableWithFields(String tableName, List<String> fields) {
		Connection connection = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection(
			        "jdbc:hsqldb:hsql://localhost/xdb", "sa", "");
			
			String sql = 
			        "create table "+ tableName+" (";
			for(String field : fields) {	
				sql +=field+" VARCHAR(15),";
			}
			sql += "id Integer );";
			connection.prepareStatement(sql).execute();
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
