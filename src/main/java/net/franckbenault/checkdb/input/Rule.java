package net.franckbenault.checkdb.input;

import java.sql.Connection;
import java.sql.Statement;

import net.franckbenault.checkdb.output.CheckOutput;
import net.franckbenault.checkdb.output.OutputLine;
import net.franckbenault.checkdb.output.ResultCode;

public class Rule {

	private String ruleOrder;

	public Rule(String ruleOrder) {
		this.ruleOrder = ruleOrder.trim();
	}

	public CheckOutput apply(DatabaseConnection dbConnection)  {
			
		Statement statement = null;
		CheckOutput output = new CheckOutput();
		
		
		if(ruleOrder.startsWith("#")) {
			//rule commented
			return output;
			
			
		} else if(ruleOrder.trim().equals("")) {
			OutputLine line =
					new OutputLine();
			output.addLine(line);
			return output;
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
				
				OutputLine line = new OutputLine(ResultCode.ERROR, "Database does not exist");
				output.addLine(line);
				return output;

			}
			
			return new CheckOutput();
		} else if(ruleOrder.startsWith("tables") && ruleOrder.endsWith("do not exist")) {	
			String[] tabs =ruleOrder.split(" ");
			String tables = tabs[1];
			String[] tables2 = tables.split(",");
			
			
			for(String table : tables2) {
				try {
					Connection connection = dbConnection.getConnection();
					statement = connection.createStatement();
					statement
						.executeQuery("SELECT * from "+table);
					
					//ERROR
					OutputLine line = new OutputLine(ResultCode.ERROR, "Table "+table+" exists");
					output.addLine(line);
					
				} catch (Exception e) {
					//OK

					
				}
			}
			
			return output;
			
		} else if(ruleOrder.startsWith("tables") && ruleOrder.endsWith(" exist")) {
			String[] tabs =ruleOrder.split(" ");
			String tables = tabs[1];
			String[] tables2 = tables.split(",");
			
			
			for(String table : tables2) {
				try {
					Connection connection = dbConnection.getConnection();
					statement = connection.createStatement();
					statement
						.executeQuery("SELECT * from "+table);
				} catch (Exception e) {
					OutputLine line = new OutputLine(ResultCode.ERROR, "Table "+table+" does not exist");
					output.addLine(line);
					
				}
			}
			
			return output;
			
		} else if(ruleOrder.startsWith("table") && ruleOrder.endsWith(" exists")) {
			String[] tabs =ruleOrder.split(" ");
			String table = tabs[1];
			try {
				Connection connection = dbConnection.getConnection();
				statement = connection.createStatement();
				statement
						.executeQuery("SELECT * from "+table);
			} catch (Exception e) {
				OutputLine line =  new OutputLine(ResultCode.ERROR, "Table "+table+" does not exist");
				output.addLine(line);
				return output;
			}
			
			return new CheckOutput();
		} else if(ruleOrder.startsWith("table") && ruleOrder.endsWith("does not exist")) {
			String[] tabs =ruleOrder.split(" ");
			String table = tabs[1];
			try {
				Connection connection = dbConnection.getConnection();
				statement = connection.createStatement();
				statement
						.executeQuery("SELECT * from "+table);
			} catch (Exception e) {
				return output;
			}
			
			OutputLine line =  new OutputLine(ResultCode.ERROR, "Table "+table+" exists");
			output.addLine(line);
			return output;
			
		} else {
			//unknown rule
			OutputLine line =  new OutputLine(ResultCode.WARN, "Unknown rule "+ruleOrder);
			output.addLine(line);
			return output;
		}
		
		
	}

}
