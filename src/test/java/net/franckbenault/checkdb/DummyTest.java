package net.franckbenault.checkdb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class DummyTest {
	
	  @BeforeAll
	  static void setup() {
	    // Initialize connection to file.
	    System.out.println("@BeforeAll - Execute once before all test methods in this class.");
	  }
	 
	  @BeforeEach
	  void init() {
	    // Insert some sample data before each test
	    System.out.println("@BeforeEach - Executed before each test method in this class.");
	  }
	 
	  @DisplayName("Test add user successfully.")
	  @Test
	  void testAddUserSuccess() {
	    System.out.println("Test add user successfully");
	 
	  }
	 
	  @DisplayName("Test add user with passed argument is null.")
	  @Test
	  void testAddUserNull() {
	    System.out.println("Test add null user.");
	 
	  }

}
