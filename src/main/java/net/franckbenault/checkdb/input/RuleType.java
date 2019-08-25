package net.franckbenault.checkdb.input;

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
		
		if(ruleOrder.startsWith("#")) {
			return RuleType.COMMENT;		
		} else if(ruleOrder.equals("")) {
			return RuleType.EMPTY_LINE;
		} else if(ruleOrder.equals("database exists")) {
			return RuleType.DATABASE_EXISTS;
		} else if(ruleOrder.startsWith("tables") && ruleOrder.endsWith("do not exist")) {	
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
