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
		} else if(ruleOrder.trim().equals("")) {
			return new OutputLine();
		} else if(ruleOrder.equals("database exists")) {
			
			String request ="";
			if(dbConnection != null) {
				if(DatabaseType.HSQLDB.equals(dbConnection.getDatabaseType()))
					request ="SELECT 1 from (VALUES(0))";
				else if(DatabaseType.ORACLE.equals(dbConnection.getDatabaseType()))
					request ="SELECT 1 from dual";
			}

			
			
			try {
				Connection connection = dbConnection.getConnection();
				statement = connection.createStatement();
				statement
						.executeQuery(request);
			} catch (Exception e) {
				return new OutputLine(ResultCode.ERROR, "Database does not exist");
			}
			
			return new OutputLine();
		} else if(ruleOrder.startsWith("table") && ruleOrder.endsWith(" exists")) {
			String[] tabs =ruleOrder.split(" ");
			String table = tabs[1];
			try {
				Connection connection = dbConnection.getConnection();
				statement = connection.createStatement();
				statement
						.executeQuery("SELECT * from "+table);
			} catch (Exception e) {
				return new OutputLine(ResultCode.ERROR, "Table "+table+" does not exist");
			}
			
			return new OutputLine();
		} else if(ruleOrder.startsWith("table") && ruleOrder.endsWith("does not exist")) {
			String[] tabs =ruleOrder.split(" ");
			String table = tabs[1];
			try {
				Connection connection = dbConnection.getConnection();
				statement = connection.createStatement();
				statement
						.executeQuery("SELECT * from "+table);
			} catch (Exception e) {
				return new OutputLine();
			}
			
			return new OutputLine(ResultCode.ERROR, "Table "+table+" exists");
		} else {
			//unknown rule
			return new OutputLine(ResultCode.WARN, "Unknown rule "+ruleOrder);
		}
		
	
	}

}
