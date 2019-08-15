package net.franckbenault.checkdb.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import net.franckbenault.checkdb.output.CheckOutput;
import net.franckbenault.checkdb.output.ResultCode;

public class ThenStage {
	
    @ExpectedScenarioState
    CheckOutput output;
    

	public void the_output_is_$_with_$_message(String outputResult, String nbMessages) {

		if("OK".equals(outputResult))
			assertEquals(output.getResultCode(), ResultCode.OK);
		
		if("WARN".equals(outputResult))
			assertEquals(output.getResultCode(), ResultCode.WARN);
		
		if("ERROR".equals(outputResult))
			assertEquals(output.getResultCode(), ResultCode.ERROR);	
		
		if("no".equals(nbMessages))
			assertEquals(output.getMessages().size(), 0);
		
		if("one".equals(nbMessages))
			assertEquals(output.getMessages().size(), 1);
		
	}

}
