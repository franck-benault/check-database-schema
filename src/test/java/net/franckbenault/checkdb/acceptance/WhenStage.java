package net.franckbenault.checkdb.acceptance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;

import net.franckbenault.checkdb.Check;
import net.franckbenault.checkdb.input.DatabaseConnection;
import net.franckbenault.checkdb.input.Rule;
import net.franckbenault.checkdb.output.CheckOutput;
import net.franckbenault.hsqldb.DBServer;


public class WhenStage extends Stage<WhenStage>{
	
    @ExpectedScenarioState
    private DatabaseConnection databaseConnection;
    
    @ExpectedScenarioState
    private DBServer server;
       
    @ProvidedScenarioState
    private CheckOutput output;
    
    @ExpectedScenarioState
    private List<String> tables = new ArrayList<>();
    
    private Set<Rule> rules = new HashSet<>();	

    private void stopDb() {
    	if(server!= null) {
    		for(String table: tables)
    			server.dropTable(table);
			server.stop();
    	}
    }

	@As( "I check" )
	public void i_check() {
		
		output =Check.check(databaseConnection, rules);
		stopDb();
		
	}


	@As( "I add a rule $" )
	public WhenStage i_add_the_rule_$(@Quoted String ruleTxt) {
		
		
		rules.add(new Rule(ruleTxt));
		
		return self();

	}

	public WhenStage i_add_the_rule_$_$(@Quoted String ruleTxt) {
		rules.add(new Rule(ruleTxt));
		
		return self();
	}

}
