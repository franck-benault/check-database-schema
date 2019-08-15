package net.franckbenault.checkdb.acceptance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;

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
    
    @ExpectedScenarioState
    List<String> tables = new ArrayList<>();

    private void stopDb() {
    	if(server!= null) {
    		for(String table: tables)
    			server.dropTable(table);
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

	public void i_check_with_rule_table_$_exists(@Quoted String table) {
		Set<Rule> rules = new HashSet<>();
		
		rules.add(new Rule("table "+table+" exists"));
		output =Check.check(databaseConnection, rules);
		stopDb();
		
	}

}
