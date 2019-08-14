package net.franckbenault.checkdb;

import java.util.Set;

import net.franckbenault.checkdb.input.DatabaseConnection;
import net.franckbenault.checkdb.input.Rule;
import net.franckbenault.checkdb.output.CheckOutput;

public class Check {
	
	public static CheckOutput check(DatabaseConnection dbConnection, Set<Rule> rules) {
		
		CheckOutput output = new CheckOutput();
		
		for(Rule rule : rules) {
			output.addLine(rule.apply(dbConnection));
		}
		
		return output;
		
	}

}
