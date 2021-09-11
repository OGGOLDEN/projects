/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.service_group;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;

/**
 * Tests ServiceGroup
 * @author magolden
 */
public class ServiceGroupTest {

	/**
	 * Resets Incident counter before each test
	 * @throws Exception setUp exception
	 */
	@Before
	public void setUp() throws Exception {
		//Reset the counter at the beginning of every test.
		Incident.setCounter(0);
	}
	
	/**
	 * Tests the ServiceGroup constructor with valid inputs
	 */
	@Test
	public void testValidSGConstructor() {
		ServiceGroup sg = new ServiceGroup("name");
		assertEquals(sg.getServiceGroupName(), "name");
		assertEquals(sg.getIncidents().size(), 0);
	}
	
	/**
	 * Tests the ServiceGroup Constructor with invalid inputs
	 */
	@Test
	public void testInvalidSGConstructor() {
		ServiceGroup sg = new ServiceGroup("name");
		assertEquals(sg.getServiceGroupName(), "name");
		
		try {
			sg = new ServiceGroup(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid service group name.");
		}
		
		try {
			sg = new ServiceGroup("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid service group name.");
		}
	}
	
	/**
	 * Tests setting the Incident counter
	 */
	@Test
	public void testSetIncidentCounter() {
		ServiceGroup sg = new ServiceGroup("name");
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i1 = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		sg.addIncident(i1);
		sg.setIncidentCounter();
		Incident i2 = new Incident("title", "caller", "message");
		assertEquals(2, i2.getId());
	}
	
	/**
	 * Tests adding an Incident to a ServiceGroup in a sorted order
	 */
	@Test
	public void testAddIncident() {
		ServiceGroup sg = new ServiceGroup("name");
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i1 = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i2 = new Incident(2, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i3 = new Incident(3, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i5 = new Incident(5, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i6 = new Incident(6, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i7 = new Incident(7, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		
		sg.addIncident(i1);
		sg.addIncident(i2);
		sg.addIncident(i3);
		sg.addIncident(i5);
		sg.addIncident(i6);
		sg.addIncident(i7);
		
		assertEquals(sg.getIncidents().size(), 6);
		assertEquals(sg.getIncidents().get(0), i1);
		assertEquals(sg.getIncidents().get(1), i2);
		assertEquals(sg.getIncidents().get(2), i3);
		assertEquals(sg.getIncidents().get(3), i5);
		assertEquals(sg.getIncidents().get(4), i6);
		assertEquals(sg.getIncidents().get(5), i7);
		
		// test adding incidents that are not in order
		Incident i4 = new Incident(4, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		
		sg.addIncident(i4);
		
		assertEquals(sg.getIncidents().size(), 7);
		assertEquals(sg.getIncidents().get(3), i4);
		assertEquals(sg.getIncidents().get(4), i5);
		assertEquals(sg.getIncidents().get(5), i6);
		assertEquals(sg.getIncidents().get(6), i7);
	}
	
	/**
	 * Test adding scrambled id's
	 */
	@Test
	public void testSortingIds() {
		ServiceGroup sg = new ServiceGroup("name");
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i1 = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i2 = new Incident(2, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i3 = new Incident(3, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i5 = new Incident(5, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i6 = new Incident(6, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i7 = new Incident(7, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		
		sg.addIncident(i5);
		sg.addIncident(i3);
		sg.addIncident(i1);
		sg.addIncident(i7);
		sg.addIncident(i2);
		sg.addIncident(i6);
		
		assertEquals(sg.getIncidents().size(), 6);
		assertEquals(sg.getIncidents().get(0), i1);
		assertEquals(sg.getIncidents().get(1), i2);
		assertEquals(sg.getIncidents().get(2), i3);
		assertEquals(sg.getIncidents().get(3), i5);
		assertEquals(sg.getIncidents().get(4), i6);
		assertEquals(sg.getIncidents().get(5), i7);
	}
	
	/**
	 * Test deleting an Incident
	 */
	@Test
	public void testDeleteIncident() {
		ServiceGroup sg = new ServiceGroup("name");
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i1 = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i2 = new Incident(2, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i3 = new Incident(3, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i5 = new Incident(5, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i6 = new Incident(6, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		Incident i7 = new Incident(7, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		
		sg.addIncident(i5);
		sg.addIncident(i3);
		sg.addIncident(i1);
		sg.addIncident(i7);
		sg.addIncident(i2);
		sg.addIncident(i6);
		
		assertEquals(sg.getIncidents().size(), 6);
		assertEquals(sg.getIncidents().get(0), i1);
		assertEquals(sg.getIncidents().get(1), i2);
		assertEquals(sg.getIncidents().get(2), i3);
		assertEquals(sg.getIncidents().get(3), i5);
		assertEquals(sg.getIncidents().get(4), i6);
		assertEquals(sg.getIncidents().get(5), i7);
		
		sg.deleteIncidentById(5);
		
		assertEquals(sg.getIncidents().size(), 5);
		assertEquals(sg.getIncidents().get(0), i1);
		assertEquals(sg.getIncidents().get(1), i2);
		assertEquals(sg.getIncidents().get(2), i3);
		assertEquals(sg.getIncidents().get(3), i6);
		assertEquals(sg.getIncidents().get(4), i7);
		
		sg.deleteIncidentById(1);
		
		assertEquals(sg.getIncidents().size(), 4);
		assertEquals(sg.getIncidents().get(0), i2);
		assertEquals(sg.getIncidents().get(1), i3);
		assertEquals(sg.getIncidents().get(2), i6);
		assertEquals(sg.getIncidents().get(3), i7);
	}
	
	/**
	 * Tests executing a command on an incident through the service group
	 */
	@Test
	public void testExecuteCommand() {
		ServiceGroup sg = new ServiceGroup("name");
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, Incident.NEW_NAME, "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		sg.addIncident(i);
		
		Command assign = new Command(Command.CommandValue.ASSIGN, "info", "message");
		Command hold = new Command(Command.CommandValue.HOLD, "info", "message");
		Command reopen = new Command(Command.CommandValue.REOPEN, null, "message");
		Command resolve = new Command(Command.CommandValue.RESOLVE, "info", "message");
		Command investigate = new Command(Command.CommandValue.INVESTIGATE, null, "message");
		Command cancel = new Command(Command.CommandValue.CANCEL, "info", "message");
		
		sg.executeCommand(1, assign);
		assertEquals(Incident.IN_PROGRESS_NAME, i.getState());
		
		sg.executeCommand(1, hold);
		assertEquals(Incident.ON_HOLD_NAME, i.getState());
		
		sg.executeCommand(1, investigate);
		assertEquals(Incident.IN_PROGRESS_NAME, i.getState());
		
		sg.executeCommand(1, resolve);
		assertEquals(Incident.RESOLVED_NAME, i.getState());
		
		sg.executeCommand(1, reopen);
		assertEquals(Incident.IN_PROGRESS_NAME, i.getState());
		
		sg.executeCommand(1, cancel);
		assertEquals(Incident.CANCELED_NAME, i.getState());
	}
	
	/**
	 * Test adding an incident with a duplicate id
	 */
	@Test
	public void testDuplicateId() {
		ServiceGroup sg = new ServiceGroup("name");
		ArrayList<String> log = new ArrayList<String>();
		log.add("message 1");
		Incident i = new Incident(1, "New", "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log);
		sg.addIncident(i);
		try {
			Incident j = new Incident(1, "In Progress", "title3", "caller3", 1, "owner", Incident.NO_STATUS, log);
			sg.addIncident(j);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incident cannot be created.", e.getMessage());
		}
	}
}
