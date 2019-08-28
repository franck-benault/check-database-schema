package net.franckbenault.checkdb.output;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResultCodeComparatorTest {

	@Test
	void testCompareEquals() {
		
		assertEquals(ResultCode.OK.compareTo(ResultCode.OK),0);
		assertEquals(ResultCode.WARN.compareTo(ResultCode.WARN),0);
		assertEquals(ResultCode.ERROR.compareTo(ResultCode.ERROR),0);
		
	}
	
	@Test
	void testComparelessThan() {
	
		assertEquals(ResultCode.OK.compareTo(ResultCode.WARN),-1);
		assertEquals(ResultCode.OK.compareTo(ResultCode.ERROR),-2);
		assertEquals(ResultCode.WARN.compareTo(ResultCode.ERROR),-1);
		
	}
	
	@Test
	void testCompareGreaterThan() {
			
		assertEquals(ResultCode.ERROR.compareTo(ResultCode.WARN),1);
		assertEquals(ResultCode.ERROR.compareTo(ResultCode.OK),2);
		assertEquals(ResultCode.WARN.compareTo(ResultCode.OK),1);
		
	}

}
