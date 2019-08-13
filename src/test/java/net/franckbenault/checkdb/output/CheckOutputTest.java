package net.franckbenault.checkdb.output;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class CheckOutputTest {

	@Test
	void testConstructor() {
		
		CheckOutput output = new CheckOutput();
	
		assertEquals(output.getResultCode(), ResultCode.OK);
		assertTrue(output.getMessages().isEmpty());
		
	}
	
	@Test
	void testAddOneLineOK() {
		
		
		CheckOutput output = new CheckOutput();
		OutputLine line = new OutputLine(ResultCode.OK, null);
		output.addLine(line);
		
		assertEquals(output.getResultCode(), ResultCode.OK);
		assertEquals(output.getMessages().size(), 0);
	}
	
	@Test
	void testAddOneLineWarning() {
		
		
		CheckOutput output = new CheckOutput();
		OutputLine line = new OutputLine(ResultCode.WARN, "[WARN] this is a warning message");
		output.addLine(line);
		
		assertEquals(output.getResultCode(), ResultCode.WARN);
		assertEquals(output.getMessages().size(), 1);
	}
	
	@Test
	void testAddOneLineError() {
		
		
		CheckOutput output = new CheckOutput();
		OutputLine line = new OutputLine(ResultCode.ERROR, "[ERROR] this is an error message");
		output.addLine(line);
		
		assertEquals(output.getResultCode(), ResultCode.ERROR);
		assertEquals(output.getMessages().size(), 1);
	}
	
	@Test
	void testAddOneLineErrorAndThenWarn() {
		
		
		CheckOutput output = new CheckOutput();
		OutputLine line1 = new OutputLine(ResultCode.ERROR, "[ERROR] this is an error message");
		output.addLine(line1);
		
		OutputLine line2 = new OutputLine(ResultCode.WARN, "[ERROR] this is a warning message");
		output.addLine(line2);
		
		assertEquals(output.getResultCode(), ResultCode.ERROR);
		assertEquals(output.getMessages().size(), 2);
	}
	
	@Test
	void testAddOneLineWarnAndThenError() {
		
		
		CheckOutput output = new CheckOutput();
		
		OutputLine line1 = new OutputLine(ResultCode.WARN, "[ERROR] this is a warning message");
		output.addLine(line1);
		
		OutputLine line2 = new OutputLine(ResultCode.ERROR, "[ERROR] this is an error message");
		output.addLine(line2);
		
		assertEquals(output.getResultCode(), ResultCode.ERROR);
		assertEquals(output.getMessages().size(), 2);
	}


}
