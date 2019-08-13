package net.franckbenault.checkdb.jgiven;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.tngtech.jgiven.junit5.ScenarioTest;

@DisplayName("Example tests with jgiven")
@RunWith(JUnitPlatform.class)
public class JGivenTest
extends ScenarioTest<GivenStage, WhenStage, ThenStage> 
{

	@Test
	public void a_dummy_jgiven_test() {
        given().some_state();
        when().some_action();
        then().some_outcome();
	}
}
