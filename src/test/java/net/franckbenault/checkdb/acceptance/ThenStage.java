package net.franckbenault.checkdb.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;

import net.franckbenault.checkdb.output.CheckOutput;
import net.franckbenault.checkdb.output.ResultCode;

public class ThenStage extends Stage<ThenStage>{
	
    @ExpectedScenarioState
    private CheckOutput output;
    

	@As( "the output is $_with $ message(s)" )
	public ThenStage the_output_is_$_with_$_message(@Quoted String outputResult, String nbMessages) {

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
		
		if("one".equals(nbMessages))
			assertEquals(output.getMessages().size(), 1);
		
		return self();
		
	}


	public ThenStage the_list_of_messages_in_output_contains_$(@Quoted String messageInput) {
		
		boolean found = false;
		for(String message : output.getMessages()) {
			if(message.contains(messageInput))
				found = true;
		}
		assertTrue(found);
		return self();
		
	}

}
