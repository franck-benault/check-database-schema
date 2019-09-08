package net.franckbenault.checkdb.input;

import java.sql.Connection;
import java.sql.Statement;

import net.franckbenault.checkdb.output.CheckOutput;
import net.franckbenault.checkdb.output.OutputLine;
import net.franckbenault.checkdb.output.ResultCode;

public class Rule {

	private String ruleOrder;
	private RuleType ruleType;

	public Rule(String ruleOrder) {
		this.ruleOrder = ruleOrder.trim();
		this.ruleType = RuleType.getType(ruleOrder);
	}
	
	public CheckOutput apply(DatabaseConnection dbConnection)  {
			
		Statement statement = null;
		CheckOutput output = new CheckOutput();
		
	    switch (ruleType)
	    {    
	      	case COMMENT:
	      	case EMPTY_LINE:
	      		return output;
	      	case DATABASE_EXISTS:
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
	      	case TABLES_DO_NOT_EXIST:
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
	      	case TABLES_EXIST:
	      		tabs =ruleOrder.split(" ");
	      		tables = tabs[1];
	      		tables2 = tables.split(",");
			
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
	      	case TABLE_EXISTS:
	      		tabs =ruleOrder.split(" ");
	      		String table = tabs[1];
	      		try {
	      			Connection connection = dbConnection.getConnection();
	      			statement = connection.createStatement();
	      			statement
						.executeQuery("SELECT * from "+table);
	      		} catch (Exception e) {
	      			OutputLine line =  new OutputLine(ResultCode.ERROR, "Table "+table+" does not exist");
	      			output.addLine(line);
	      		}
	      		return output;
	      	case TABLE_DOES_NOT_EXIST:
	      		tabs =ruleOrder.split(" ");
	      		table = tabs[1];
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
	      	case TABLE_WITH_FIELDS_EXISTS :
	      		tabs =ruleOrder.split(" ");
	      		table = tabs[1];
	      		String fieldsTab = tabs[4];
	      		String[] fields = fieldsTab.split(",");
	      		for(String field : fields) {
	      			try {
	      				Connection connection = dbConnection.getConnection();
	      				statement = connection.createStatement();
	      				statement
	      					.executeQuery("SELECT "+field+" from "+table);
	      			} catch (Exception e) {
	      				line =  new OutputLine(ResultCode.ERROR, "Table "+table+" does not contain the field "+field);
	      				output.addLine(line);
	      			}
	      		}  		
	      		return output; 
	      		
	      	default:
				//unknown rule
				line =  new OutputLine(ResultCode.WARN, "Unknown rule "+ruleOrder);
				output.addLine(line);
				return output;
	    }	
		
	}

}
