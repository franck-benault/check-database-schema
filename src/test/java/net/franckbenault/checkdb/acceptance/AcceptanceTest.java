package net.franckbenault.checkdb.acceptance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.tngtech.jgiven.junit5.ScenarioTest;



@DisplayName("Acceptance tests with jgiven")
@RunWith(JUnitPlatform.class)
public class AcceptanceTest 
extends ScenarioTest<GivenStage, WhenStage, ThenStage> 
{

	@Test
	public void check00_no_rule() {
        given().an_dbhsql_database();
        when().$("there is no rule").i_check();
        then().the_output_is_$_with_$_message("OK", "no");
	}
	
	@Test
	public void check01a_empty() {
        given().an_dbhsql_database();
        when().i_add_the_rule_$("").$("An empty rule").
        and().i_check();
        then().the_output_is_$_with_$_message("OK", "no");
	}
	
	@Test
	public void check01b_rule_only_space() {
        given().an_dbhsql_database();
        when().i_add_the_rule_$("   ").$("An empty rule").
        and().i_check();
        then().the_output_is_$_with_$_message("OK", "no");
	}
	

	@Test
	public void check02_commented_rule() {
        given().an_dbhsql_database();
        when().i_add_the_rule_$("# commented rules").
        and().i_check();
        then().the_output_is_$_with_$_message("OK","no");
	}
	
	@Test
	public void check03_unknown_rule() {
		String unknownRule = "Unknown rule";
		
        given().an_dbhsql_database();
        when().i_add_the_rule_$(unknownRule).
        and().i_check();
        then().the_output_is_$_with_$_message("WARN","one")
        .and().the_list_of_messages_in_output_contains_$(unknownRule);
	}
	
	@Test
	public void check04a_rule_database_exists() {
        given().an_dbhsql_database();
        when().i_add_the_rule_$("database exists").
        and().i_check();
        then().the_output_is_$_with_$_message("OK","no");
	}
	
	@Test
	public void check04b_no_db_rule_database_exists() {
        given().no_database();
        when().i_add_the_rule_$("database exists").
        and().i_check();
        then().the_output_is_$_with_$_message("ERROR","one").
        and().the_list_of_messages_in_output_contains_$("Database does not exist");
	}
	
	@Test
	public void check05a_rule_table_exists() {
		String tableName ="FOO";
        given().an_dbhsql_database_containing_the_table_$(tableName);
        when().i_add_the_rule_$("table "+tableName+" exists").
        and().i_check();
        then().the_output_is_$_with_$_message("OK","no");
	}
	
	@Test
	public void check05b_rule_table_exists_ERROR() {
		String tableName ="FOO";
        given().an_dbhsql_database_containing_the_table_$(tableName);
        when().i_add_the_rule_$("table "+tableName+"2 exists").
        and().i_check();
        then().the_output_is_$_with_$_message("ERROR","one").
        and().the_list_of_messages_in_output_contains_$("Table FOO2 does not exist");
	}
	
	@Test
	public void check05c_rule_table_exists_two_errors() {
		String tableName ="FOO";
        given().an_dbhsql_database_containing_the_table_$(tableName);
        when().i_add_the_rule_$("table "+tableName+"2 exists").
        and().i_add_the_rule_$("table "+tableName+"3 exists").
        and().i_check();
        then().the_output_is_$_with_$_message("ERROR","two").and().
        the_list_of_messages_in_output_contains_$("Table FOO2 does not exist");
	}
	
	@Test
	public void check06a_rule_table_does_not_exist() {
		String tableName ="FOO";
        given().an_dbhsql_database_containing_the_table_$(tableName);
        when().i_add_the_rule_$("table "+tableName+"2 does not exist").
        and().i_check();
        then().the_output_is_$_with_$_message("OK","no");
	}
	
	
	@Test
	public void check06b_rule_table_does_not_exist_ERROR() {
		String tableName ="FOO";
        given().an_dbhsql_database_containing_the_table_$(tableName);
        when().i_add_the_rule_$("table "+tableName+" does not exist").
        and().i_check();
        then().the_output_is_$_with_$_message("ERROR","one").and().
        the_list_of_messages_in_output_contains_$("Table FOO exists");
	}
	
	@Test
	public void check07a_rule_table_exists_ok() {
		String tableName1 ="FOO1";
		String tableName2 ="FOO2";
        given().an_dbhsql_database_containing_the_tables_$(tableName1,tableName2);
        when().i_add_the_rule_$("tables "+tableName1+","+tableName2+" exist").
        and().i_check();
        then().the_output_is_$_with_$_message("OK","no");
	}
	
	@Test
	public void check07b_rule_table_exists_error() {
		String tableName1 ="FOO1";
		String tableName2 ="FOO2";
		String tableName3 ="FOO3";
        given().an_dbhsql_database_containing_the_tables_$(tableName1,tableName2);
        when().i_add_the_rule_$("tables "+tableName1+","+tableName2+","+tableName3+" exist").
        and().i_check();
        then().the_output_is_$_with_$_message("ERROR","one").and().
        the_list_of_messages_in_output_contains_$("Table "+tableName3 +" does not exist");
	}
	
	
	@Test
	public void check08a_rule_table_exists_ok() {
		String tableName0 = "FOO0";
		String tableName1 = "FOO1";
		String tableName2 = "FOO2";
        given().an_dbhsql_database_containing_the_tables_$(tableName0);
        when().i_add_the_rule_$("tables "+tableName1+","+tableName2+"do not exist").
        and().i_check();
        then().the_output_is_$_with_$_message("OK","no");
	}
	
	@Test
	public void check08b_rule_table_exists_error() {
		String tableName1 ="FOO1";
		String tableName2 ="FOO2";
		String tableName3 ="FOO3";
        given().an_dbhsql_database_containing_the_tables_$(tableName1,tableName2);
        when().i_add_the_rule_$("tables "+tableName1+","+tableName2+","+tableName3+"do not exist").
        and().i_check();
        then().the_output_is_$_with_$_message("ERROR","two").and().
        the_list_of_messages_in_output_contains_$("Table "+tableName1 +" exists").and().
        the_list_of_messages_in_output_contains_$("Table "+tableName2 +" exists");
	}
	
	
	@Test
	public void check99_trim_rules() {
		String tableName ="FOO";
        given().an_dbhsql_database_containing_the_table_$(tableName);
        when().i_add_the_rule_$(" table "+tableName+" exists ").
        and().i_check();
        then().the_output_is_$_with_$_message("OK","no");
	}
	
}
