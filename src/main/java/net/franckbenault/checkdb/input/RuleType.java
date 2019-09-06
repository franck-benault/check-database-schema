package net.franckbenault.checkdb.input;

import java.util.regex.Pattern;

public enum RuleType {

	
	COMMENT,
	EMPTY_LINE,
	DATABASE_EXISTS,
	TABLE_EXISTS,
	TABLES_EXIST,
	TABLE_DOES_NOT_EXIST,
	TABLES_DO_NOT_EXIST,
	UNKNOWN_RULE;
	
	public static RuleType getType(String ruleOrderInput) {
		
		String ruleOrder = ruleOrderInput.trim();

		boolean bComment = Pattern.compile("^#.*$").matcher(ruleOrder).find();		
		boolean bDatabaseExists = Pattern.compile("^[Dd]atabase exists$").matcher(ruleOrder).find();	
		boolean b2 = Pattern.compile("^[Tt]ables (.)* do not exist$").matcher(ruleOrder).find();
		

		if(bComment) {
			return RuleType.COMMENT;		
		} else if(ruleOrder.equals("")) {
			return RuleType.EMPTY_LINE;
		} else if(bDatabaseExists) {
			return RuleType.DATABASE_EXISTS;
		} else if(b2) {
		//} else if(ruleOrder.startsWith("tables") && ruleOrder.endsWith("do not exist")) {	
			return RuleType.TABLES_DO_NOT_EXIST;		
		} else if(ruleOrder.startsWith("tables") && ruleOrder.endsWith(" exist")) {
			return RuleType.TABLES_EXIST;			
		} else if(ruleOrder.startsWith("table") && ruleOrder.endsWith(" exists")) {
			return RuleType.TABLE_EXISTS;	
		} else if(ruleOrder.startsWith("table") && ruleOrder.endsWith("does not exist")) {
			return RuleType.TABLE_DOES_NOT_EXIST;				
		} else {
			return RuleType.UNKNOWN_RULE;
		}
	}

}
