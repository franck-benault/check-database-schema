package net.franckbenault.checkdb.acceptance;

import java.util.HashSet;
import java.util.Set;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import net.franckbenault.checkdb.Check;
import net.franckbenault.checkdb.input.DatabaseConnection;
import net.franckbenault.checkdb.input.Rule;
import net.franckbenault.checkdb.output.CheckOutput;
import net.franckbenault.hsqldb.DBServer;


public class WhenStage {
	
    @ExpectedScenarioState
    DatabaseConnection databaseConnection;
    
    @ExpectedScenarioState
    DBServer server;
       
    @ProvidedScenarioState
    CheckOutput output;

    private void stopDb() {
    	if(server!= null) {
    		server.dropTable("FOO");
			server.stop();
    	}
    }

	public void i_check_with_no_rule() {
		Set<Rule> rules = new HashSet<>();	
		output =Check.check(databaseConnection, rules);
		stopDb();
		
	}

	public void i_check_with_a_commented_rule() {
		Set<Rule> rules = new HashSet<>();	
		
		rules.add(new Rule("# commented rules"));
		output =Check.check(databaseConnection, rules);
		stopDb();
		
	}

	public void i_check_with_an_unknown_rule() {
		Set<Rule> rules = new HashSet<>();
		
		rules.add(new Rule("unknown rule"));
		output =Check.check(databaseConnection, rules);
		stopDb();
		
	}

	public void i_check_with_rule_database_exists() {
		Set<Rule> rules = new HashSet<>();
		
		rules.add(new Rule("database exists"));
		output =Check.check(databaseConnection, rules);
		stopDb();
		
	}

}
