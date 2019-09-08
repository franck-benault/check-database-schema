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
	void testRuleTypeDatabaseExists() {
		
		RuleType t =RuleType.getType("database exists");
		assertEquals(t, RuleType.DATABASE_EXISTS);
		
		t =RuleType.getType(" Database exists ");
		assertEquals(t, RuleType.DATABASE_EXISTS);
		
		t =RuleType.getType("this is not an database exists rule");
		assertNotEquals(t, RuleType.DATABASE_EXISTS);
	}
	
	@Test
	void testRuleTypeTableWithFieldsExists() {
		
		RuleType t =RuleType.getType("table toto with field titi exists");
		assertEquals(t, RuleType.TABLE_WITH_FIELDS_EXISTS);
		
		t =RuleType.getType(" Table toto with fields tata,toto exists ");
		assertEquals(t, RuleType.TABLE_WITH_FIELDS_EXISTS);
		
		t =RuleType.getType("this is not an table with fields exists rule");
		assertNotEquals(t, RuleType.TABLE_WITH_FIELDS_EXISTS);
	}

	
	@Test
	void testRuleTypeTablesDoNotExist() {
		
		RuleType t =RuleType.getType("tables toto,titi,tata do not exist");
		assertEquals(t, RuleType.TABLES_DO_NOT_EXIST);
		
		t =RuleType.getType(" Tables toto,titi,tata do not exist ");
		assertEquals(t, RuleType.TABLES_DO_NOT_EXIST);
		
		t =RuleType.getType("this is not an tables do not exist rule");
		assertNotEquals(t, RuleType.TABLES_DO_NOT_EXIST);
	}
	
	
	@Test
	void testRuleTypeTablesExist() {
		
		RuleType t =RuleType.getType("Tables toto,titi,tata exist");
		assertEquals(t, RuleType.TABLES_EXIST);
		
		t =RuleType.getType(" Tables toto,titi,tata exist ");
		assertEquals(t, RuleType.TABLES_EXIST);
		
		t =RuleType.getType("this is not an tables exist rule");
		assertNotEquals(t, RuleType.TABLES_EXIST);
	}
	
	
	@Test
	void testRuleTypeTableExists() {
		
		RuleType t =RuleType.getType("Table toto exists");
		assertEquals(t, RuleType.TABLE_EXISTS);
		
		t =RuleType.getType(" Table toto exists ");
		assertEquals(t, RuleType.TABLE_EXISTS);
		
		t =RuleType.getType("this is not an table exists rule");
		assertNotEquals(t, RuleType.TABLE_EXISTS);
	}
	
	
	@Test
	void testRuleTypeTableDoesNotExist() {
		
		RuleType t =RuleType.getType("Table toto does not exist");
		assertEquals(t, RuleType.TABLE_DOES_NOT_EXIST);
		
		t =RuleType.getType(" Table toto does not exist ");
		assertEquals(t, RuleType.TABLE_DOES_NOT_EXIST);
		
		t =RuleType.getType("this is not an table does not exist rule");
		assertNotEquals(t, RuleType.TABLE_DOES_NOT_EXIST);
	}

}
