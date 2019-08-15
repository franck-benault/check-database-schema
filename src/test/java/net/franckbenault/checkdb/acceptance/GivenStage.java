package net.franckbenault.checkdb.acceptance;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import net.franckbenault.checkdb.input.DatabaseConnection;
import net.franckbenault.checkdb.input.DatabaseType;
import net.franckbenault.hsqldb.DBServer;

public class GivenStage extends Stage<GivenStage>{

    @ProvidedScenarioState
    DBServer server;
    
    @ProvidedScenarioState
    DatabaseConnection databaseConnection;


	public GivenStage an_dbhsql_database() {
		
		server = new DBServer();
		server.start();
		server.createTable("FOO");

		databaseConnection = new DatabaseConnection(DatabaseType.HSQLDB,
				"jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
		
		return self();
		
	}


	public void no_database() {
		
		
	}

}
