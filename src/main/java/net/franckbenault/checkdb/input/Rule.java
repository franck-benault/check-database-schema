package net.franckbenault.checkdb.input;

import java.sql.Connection;
import java.sql.Statement;

import net.franckbenault.checkdb.output.OutputLine;
import net.franckbenault.checkdb.output.ResultCode;

public class Rule {

	private String ruleOrder;

	public Rule(String ruleOrder) {
		this.ruleOrder = ruleOrder;
	}

	public OutputLine apply(DatabaseConnection dbConnection)  {
		

		Statement statement = null;
		
		if(ruleOrder.startsWith("#")) {
			//rule commented
			return new OutputLine();
		} if(ruleOrder.equals("database exists")) {
			try {
				Connection connection = dbConnection.getConnection();
				statement = connection.createStatement();
				statement
						.executeQuery("SELECT 1 from (VALUES(0))");
			} catch (Exception e) {
				return new OutputLine(ResultCode.ERROR, "database does not exist");
			}
			
			return new OutputLine();
		} if(ruleOrder.startsWith("table") && !ruleOrder.endsWith("does not exists")) {
			String[] tabs =ruleOrder.split(" ");
			String table = tabs[1];
			try {
				Connection connection = dbConnection.getConnection();
				statement = connection.createStatement();
				statement
						.executeQuery("SELECT * from "+table);
			} catch (Exception e) {
				return new OutputLine(ResultCode.ERROR, "table "+table+" does not exist");
			}
			
			return new OutputLine();
		} else {
			//unknown rule
			return new OutputLine(ResultCode.WARN, "unknown rule "+ruleOrder);
		}
		
	
	}

}
