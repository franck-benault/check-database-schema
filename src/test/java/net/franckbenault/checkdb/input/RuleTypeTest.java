package net.franckbenault.checkdb.input;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RuleTypeTest {

	@Test
	void testRuleTypeComment() {
		
		RuleType t =RuleType.getType("#This is a comment");
		assertEquals(t, RuleType.COMMENT);
		
		t =RuleType.getType(" #This is also a comment");
		assertEquals(t, RuleType.COMMENT);
		
		t =RuleType.getType("This is not a comment");
		assertNotEquals(t, RuleType.COMMENT);
	}
	
	@Test
	void testRuleTypeEmptyLine() {
		
		RuleType t =RuleType.getType("");
		assertEquals(t, RuleType.EMPTY_LINE);
		
		t =RuleType.getType("  ");
		assertEquals(t, RuleType.EMPTY_LINE);
		
		t =RuleType.getType("this is not an empty line");
		assertNotEquals(t, RuleType.EMPTY_LINE);
	}
	
	@Test
	void testRuleTypeTablesDoNotExist() {
		
		RuleType t =RuleType.getType("Tables toto,titi,tata do not exist");
		assertEquals(t, RuleType.TABLES_DO_NOT_EXIST);
		
		t =RuleType.getType(" Tables toto,titi,tata do not exist ");
		assertEquals(t, RuleType.TABLES_DO_NOT_EXIST);
		
		t =RuleType.getType("this is not an tables do not exist rule");
		assertNotEquals(t, RuleType.TABLES_DO_NOT_EXIST);
	}

}
