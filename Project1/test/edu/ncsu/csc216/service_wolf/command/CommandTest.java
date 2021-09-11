/**
 * 
 */
package edu.ncsu.csc216.service_wolf.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command;

/**
 * Tests Command
 * @author magolden
 */
public class CommandTest {

	/**
	 * Tests Command Constructor With Valid Inputs
	 */
	@Test
	public void testValidCommand() {
		Command c = new Command(Command.CommandValue.ASSIGN, "info", "message");
		assertEquals(Command.CommandValue.ASSIGN, c.getCommand());
		assertEquals("info", c.getCommandInformation());
		assertEquals("message", c.getCommandMessage());
		
		c = new Command(Command.CommandValue.HOLD, "info", "message");
		assertEquals(Command.CommandValue.HOLD, c.getCommand());
		
		c = new Command(Command.CommandValue.CANCEL, "info", "message");
		assertEquals(Command.CommandValue.CANCEL, c.getCommand());
		
		c = new Command(Command.CommandValue.RESOLVE, "info", "message");
		assertEquals(Command.CommandValue.RESOLVE, c.getCommand());
		
		c = new Command(Command.CommandValue.INVESTIGATE, null, "message");
		assertEquals(Command.CommandValue.INVESTIGATE, c.getCommand());
		assertNull(c.getCommandInformation());
		
		c = new Command(Command.CommandValue.REOPEN, null, "message");
		assertEquals(Command.CommandValue.REOPEN, c.getCommand());
		assertNull(c.getCommandInformation());
	}
	
	/**
	 * Tests Command Constructor With Null Inputs
	 */
	@Test
	public void testInvalidCommandWithNull() {
		Command c = new Command(Command.CommandValue.ASSIGN, "info", "message");
		assertEquals(Command.CommandValue.ASSIGN, c.getCommand());
		
		try {
			c = new Command(null, "info", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "CommandValue cannot be null");
		}
		
		try {
			c = new Command(Command.CommandValue.ASSIGN, null, "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.ASSIGN, "info", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.CANCEL, null, "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.CANCEL, "info", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.HOLD, null, "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.HOLD, "info", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.RESOLVE, null, "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.RESOLVE, "info", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.INVESTIGATE, "info", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation should be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.INVESTIGATE, null, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.REOPEN, "info", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation should be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.REOPEN, null, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
	}
	
	/**
	 * Tests Command Constructor With Empty String Inputs
	 */
	@Test
	public void testInvalidCommandWithEmptyString() {
		Command c = new Command(Command.CommandValue.ASSIGN, "info", "message");
		assertEquals(Command.CommandValue.ASSIGN, c.getCommand());
		
		try {
			c = new Command(Command.CommandValue.ASSIGN, "", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.ASSIGN, "info", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.CANCEL, "", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.CANCEL, "info", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.HOLD, "", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.HOLD, "info", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.RESOLVE, "", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.RESOLVE, "info", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.INVESTIGATE, "info", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation should be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.INVESTIGATE, null, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.REOPEN, "info", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandInformation should be null or empty");
		}
		
		try {
			c = new Command(Command.CommandValue.REOPEN, null, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "commandMessage cannot be null or empty");
		}
	}
	
}
