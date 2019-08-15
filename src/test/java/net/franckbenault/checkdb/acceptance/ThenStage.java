package net.franckbenault.checkdb.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import net.franckbenault.checkdb.output.CheckOutput;
import net.franckbenault.checkdb.output.ResultCode;

public class ThenStage {
	
    @ExpectedScenarioState
    CheckOutput output;
    

	public void the_output_is_OK_with_no_message() {
		
		assertEquals(output.getResultCode(), ResultCode.OK);
		assertTrue(output.getMessages().isEmpty());
			
	}


	public void the_output_is_WARN_with_one_message() {
		
		assertEquals(output.getResultCode(), ResultCode.WARN);
		assertEquals(output.getMessages().size(), 1);
		
	}


	public void the_output_is_ERROR_with_one_message() {

		assertEquals(output.getResultCode(), ResultCode.ERROR);
		assertEquals(output.getMessages().size(), 1);
		
	}

}
