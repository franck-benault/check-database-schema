package net.franckbenault.checkdb.acceptance;

import java.util.ArrayList;
import java.util.List;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;

import net.franckbenault.checkdb.input.DatabaseConnection;
import net.franckbenault.checkdb.input.DatabaseType;
import net.franckbenault.hsqldb.DBServer;

public class GivenStage extends Stage<GivenStage>{

    @ProvidedScenarioState
    DBServer server;
    
    @ProvidedScenarioState
    DatabaseConnection databaseConnection;
    
    @ProvidedScenarioState
    List<String> tables = new ArrayList<>();


	public GivenStage an_dbhsql_database() {
		
		server = new DBServer();
		server.start();

		databaseConnection = new DatabaseConnection(DatabaseType.HSQLDB,
				"jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
		
		return self();
		
	}


	public GivenStage no_database() {
		
		return self();
	}


	public GivenStage an_dbhsql_database_containing_the_table_$(@Quoted String table) {
		server = new DBServer();
		server.start();
		server.createTable(table);
		
		tables.add(table);

		databaseConnection = new DatabaseConnection(DatabaseType.HSQLDB,
				"jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
		
		return self();
		
	}

}
