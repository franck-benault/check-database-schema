package net.franckbenault.checkdb.input;

import java.util.regex.Pattern;

public enum RuleType {

	
	COMMENT,
	EMPTY_LINE,
	DATABASE_EXISTS,
	TABLE_WITH_FIELDS_EXISTS,
	TABLE_EXISTS,
	TABLES_EXIST,
	TABLE_DOES_NOT_EXIST,
	TABLES_DO_NOT_EXIST,
	UNKNOWN_RULE;
	
	public static RuleType getType(String ruleOrderInput) {
		
		String ruleOrder = ruleOrderInput.trim();

		boolean bComment = Pattern.compile("^#.*$").matcher(ruleOrder).find();		
		boolean bDatabaseExists = Pattern.compile("^[Dd]atabase exists$").matcher(ruleOrder).find();	
		boolean bTablesDoNotExist = Pattern.compile("^[Tt]ables (.)* do not exist$").matcher(ruleOrder).find();
		
		boolean bTableWithFieldsExists = Pattern.compile("^[Tt]able (.)* with field(s)* (.)* exists$").matcher(ruleOrder).find();			
		boolean bTablesExist = Pattern.compile("^[Tt]ables (.)* exist$").matcher(ruleOrder).find();		
		boolean bTableExists = Pattern.compile("^[Tt]able (.)* exists$").matcher(ruleOrder).find();
		boolean bTableDoesNotExist = Pattern.compile("^[Tt]able (.)* does not exist$").matcher(ruleOrder).find();

		if(bComment) {
			return RuleType.COMMENT;		
		} else if(ruleOrder.equals("")) {
			return RuleType.EMPTY_LINE;
		} else if(bDatabaseExists) {
			return RuleType.DATABASE_EXISTS;
		} else if(bTableWithFieldsExists) {
			return RuleType.TABLE_WITH_FIELDS_EXISTS;	
		} else if(bTablesDoNotExist) {	
			return RuleType.TABLES_DO_NOT_EXIST;		
		} else if(bTablesExist) {
			return RuleType.TABLES_EXIST;			
		} else if(bTableExists) {
			return RuleType.TABLE_EXISTS;	
		} else if(bTableDoesNotExist) {
			return RuleType.TABLE_DOES_NOT_EXIST;				
		} else {
			return RuleType.UNKNOWN_RULE;
		}
	}

}
