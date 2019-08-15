package net.franckbenault.checkdb.acceptance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.tngtech.jgiven.junit5.ScenarioTest;



@DisplayName("Example tests with jgiven")
@RunWith(JUnitPlatform.class)
public class AcceptanceTest 
extends ScenarioTest<GivenStage, WhenStage, ThenStage> 
{

	@Test
	public void check_no_rule() {
        given().an_dbhsql_database();
        when().i_check_with_no_rule();
        then().the_output_is_OK_with_no_message();
	}
	

	@Test
	public void check_commented_rule() {
        given().an_dbhsql_database();
        when().i_check_with_a_commented_rule();
        then().the_output_is_OK_with_no_message();
	}
	
	@Test
	public void check_unknown_rule() {
        given().an_dbhsql_database();
        when().i_check_with_an_unknown_rule();
        then().the_output_is_WARN_with_one_message();
	}
	
	@Test
	public void check_rule_database_exists() {
        given().an_dbhsql_database();
        when().i_check_with_rule_database_exists();
        then().the_output_is_OK_with_no_message();
	}
	
	@Test
	public void check_no_db_rule_database_exists() {
        given().no_database();
        when().i_check_with_rule_database_exists();
        then().the_output_is_ERROR_with_one_message();
	}
	
}
