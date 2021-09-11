/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.incident;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command;

/**
 * Tests Incident
 * @author magolden
 */
public class IncidentTest {

	/**
	 * Resets Incident counter to zero
	 * @throws Exception set up Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Reset the counter at the beginning of every test.
		Incident.setCounter(0);
	}
	
	/**
	 * Tests Incident Constructor
	 */
	@Test
	public void testValidIncidentConstructor() {
		Incident i = new Incident("title", "caller", "message");
		assertEquals(i.getTitle(), "title");
		assertEquals(i.getCaller(), "caller");
		assertEquals(i.getId(), 0);
		assertEquals(i.getState(), Incident.NEW_NAME);
		assertEquals(i.getOwner(), Incident.UNOWNED);
		assertEquals(i.getStatusDetails(), Incident.NO_STATUS);
		assertEquals(i.getIncidentLogMessages(), "- message\n");
		assertEquals(i.getReopenCount(), 0);
		
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident j = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		assertEquals(j.getTitle(), "title");
		assertEquals(j.getCaller(), "caller");
		assertEquals(j.getId(), 1);
		assertEquals(j.getState(), Incident.NEW_NAME);
		assertEquals(j.getOwner(), Incident.UNOWNED);
		assertEquals(j.getStatusDetails(), Incident.NO_STATUS);
		assertEquals(j.getIncidentLogMessages(), "- message 1\n");
		assertEquals(j.getReopenCount(), 0);
		
		Incident k = new Incident(1, Incident.CANCELED_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.CANCELLATION_DUPLICATE, log);
		assertEquals(k.getTitle(), "title");
		assertEquals(k.getCaller(), "caller");
		assertEquals(k.getId(), 1);
		assertEquals(k.getState(), Incident.CANCELED_NAME);
		assertEquals(k.getOwner(), Incident.UNOWNED);
		assertEquals(k.getStatusDetails(), Incident.CANCELLATION_DUPLICATE);
		assertEquals(k.getIncidentLogMessages(), "- message 1\n");
		assertEquals(k.getReopenCount(), 0);

	}
	
	/**
	 * Tests Incident Constructor with invalid parameters
	 */
	@Test
	public void testInvalidIncidentConstructor() {
		Incident i = new Incident("title", "caller", "message");
		assertEquals(i.getState(), Incident.NEW_NAME);
		
		try {
			i = new Incident(null, "caller", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			i = new Incident("", "caller", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			i = new Incident("title", null, "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			i = new Incident("title", "", "message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			i = new Incident("title", "caller", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			i = new Incident("title", "caller", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident j = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		assertEquals(j.getState(), Incident.NEW_NAME);
		
		try {
			j = new Incident(-1, Incident.NEW_NAME, "title", "caller", 0, "owner", Incident.NO_STATUS, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, Incident.NEW_NAME, null, "caller", 0, "owner", Incident.NO_STATUS, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, Incident.NEW_NAME, "", "caller", 0, "owner", Incident.NO_STATUS, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, Incident.NEW_NAME, "title", null, 0, "owner", Incident.NO_STATUS, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, Incident.NEW_NAME, "title", "", 0, "owner", Incident.NO_STATUS, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, Incident.NEW_NAME, "title", "caller", -1, "owner", Incident.NO_STATUS, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, null, Incident.NO_STATUS, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, "", Incident.NO_STATUS, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, "owner", null, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, "owner", "", log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, "owner", Incident.NO_STATUS, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, "invalid state", "title", "caller", 0, "owner", Incident.NO_STATUS, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid state name");
		}
		
		try {
			j = new Incident(1, null, "title", "caller", 0, "owner", Incident.NO_STATUS, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
		
		try {
			j = new Incident(1, "", "title", "caller", 0, "owner", Incident.NO_STATUS, log);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Incident cannot be created.");
		}
	}
	
	/**
	 * Test the transition from New to In Progress
	 */
	@Test
	public void testNewA() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		assertEquals(i.getState(), Incident.NEW_NAME);
		Command c = new Command(Command.CommandValue.ASSIGN, "info", "message");
		i.update(c);
		assertEquals(i.getState(), Incident.IN_PROGRESS_NAME);
	}
	
	/**
	 * Test the transition from New to Canceled
	 */
	@Test
	public void testNewB() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		assertEquals(i.getState(), Incident.NEW_NAME);
		Command c = new Command(Command.CommandValue.CANCEL, "info", "message");
		i.update(c);
		assertEquals(i.getState(), Incident.CANCELED_NAME);
	}
	
	/**
	 * Test the transition from In Progress to On Hold
	 */
	@Test
	public void testInProgressA() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.IN_PROGRESS_NAME, "title", "caller", 0, "owner", Incident.NO_STATUS, log);
		assertEquals(i.getState(), Incident.IN_PROGRESS_NAME);
		Command c = new Command(Command.CommandValue.HOLD, "info", "message");
		i.update(c);
		assertEquals(i.getState(), Incident.ON_HOLD_NAME);
	}
	
	/**
	 * Test the transition from In Progress to Resolved
	 */
	@Test
	public void testInProgressB() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.IN_PROGRESS_NAME, "title", "caller", 0, "owner", Incident.NO_STATUS, log);
		assertEquals(i.getState(), Incident.IN_PROGRESS_NAME);
		Command c = new Command(Command.CommandValue.RESOLVE, "info", "message");
		i.update(c);
		assertEquals(i.getState(), Incident.RESOLVED_NAME);
	}
	
	/**
	 * Test the transition from In Progress to In Progress
	 */
	@Test
	public void testInProgressC() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.IN_PROGRESS_NAME, "title", "caller", 0, "owner", Incident.NO_STATUS, log);
		assertEquals(i.getState(), Incident.IN_PROGRESS_NAME);
		Command c = new Command(Command.CommandValue.ASSIGN, "info", "message");
		i.update(c);
		assertEquals(i.getState(), Incident.IN_PROGRESS_NAME);
	}
	
	/**
	 * Test the transition from In Progress to Canceled
	 */
	@Test
	public void testInProgressD() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.IN_PROGRESS_NAME, "title", "caller", 0, "owner", Incident.NO_STATUS, log);
		assertEquals(i.getState(), Incident.IN_PROGRESS_NAME);
		Command c = new Command(Command.CommandValue.CANCEL, "info", "message");
		i.update(c);
		assertEquals(i.getState(), Incident.CANCELED_NAME);
	}
	
	/**
	 * Test the transition from On Hold to In Progress
	 */
	@Test
	public void testOnHoldA() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.ON_HOLD_NAME, "title", "caller", 0, "owner", Incident.HOLD_AWAITING_CALLER, log);
		assertEquals(i.getState(), Incident.ON_HOLD_NAME);
		Command c = new Command(Command.CommandValue.INVESTIGATE, null, "message");
		i.update(c);
		assertEquals(i.getState(), Incident.IN_PROGRESS_NAME);
	}
	
	/**
	 * Test the transition from Resolved to In Progress
	 */
	@Test
	public void testResolvedA() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.RESOLVED_NAME, "title", "caller", 0, "owner", Incident.RESOLUTION_CALLER_CLOSED, log);
		assertEquals(i.getState(), Incident.RESOLVED_NAME);
		Command c = new Command(Command.CommandValue.REOPEN, null, "message");
		i.update(c);
		assertEquals(i.getState(), Incident.IN_PROGRESS_NAME);
	}
	
	/**
	 * Test the transition from Resolved to Canceled
	 */
	@Test
	public void testResolvedB() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.RESOLVED_NAME, "title", "caller", 0, "owner", Incident.RESOLUTION_CALLER_CLOSED, log);
		assertEquals(i.getState(), Incident.RESOLVED_NAME);
		Command c = new Command(Command.CommandValue.CANCEL, "info", "message");
		i.update(c);
		assertEquals(i.getState(), Incident.CANCELED_NAME);
	}
	
	/**
	 * Test invalid commands for New State
	 */
	@Test
	public void testInvalidNewCommand() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		assertEquals(i.getState(), Incident.NEW_NAME);
		Command c = new Command(Command.CommandValue.HOLD, "info", "message");
		try {
			i.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(e.getMessage(), "Invalid command for New state");
		}
	}
	
	/**
	 * Test invalid commands for Resolved State
	 */
	@Test
	public void testInvalidResolvedCommand() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.RESOLVED_NAME, "title", "caller", 0, "owner", Incident.RESOLUTION_CALLER_CLOSED, log);
		assertEquals(i.getState(), Incident.RESOLVED_NAME);
		Command c = new Command(Command.CommandValue.HOLD, "info", "message");
		try {
			i.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(e.getMessage(), "Invalid command for Resolved state");
		}
	}
	
	/**
	 * Test invalid commands for On Hold State
	 */
	@Test
	public void testInvalidOnHoldCommand() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.ON_HOLD_NAME, "title", "caller", 0, "owner", Incident.HOLD_AWAITING_CALLER, log);
		assertEquals(i.getState(), Incident.ON_HOLD_NAME);
		Command c = new Command(Command.CommandValue.HOLD, "info", "message");
		try {
			i.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(e.getMessage(), "Invalid command for On Hold state");
		}
	}
	
	/**
	 * Test invalid commands for In Progress State
	 */
	@Test
	public void testInvalidInProgressCommand() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.IN_PROGRESS_NAME, "title", "caller", 0, "owner", Incident.NO_STATUS, log);
		assertEquals(i.getState(), Incident.IN_PROGRESS_NAME);
		Command c = new Command(Command.CommandValue.REOPEN, null, "message");
		try {
			i.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(e.getMessage(), "Invalid command for In Progress state");
		}
	}
	
	/**
	 * Test invalid commands for Canceled State
	 */
	@Test
	public void testInvalidCanceledCommand() {
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.CANCELED_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.CANCELLATION_CALLER_CANCELLED, log);
		assertEquals(i.getState(), Incident.CANCELED_NAME);
		Command c = new Command(Command.CommandValue.REOPEN, null, "message");
		try {
			i.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(e.getMessage(), "Invalid command for Canceled state");
		}
	}
}
