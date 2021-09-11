/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;

/**
 * Tests ServiceWolfManager
 * @author magolden
 */
public class ServiceWolfManagerTest {

	/**
	 * Resets singleton instance of ServiceWolfManager before each test
	 */
	@Before
	public void setUp() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		swm.resetManager();
	}
	
	/**
	 * Tests ServiceWolfManager Constructor
	 */
	@Test
	public void testConstructor() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		
		try {
			swm.loadServiceGroup("name");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("No service group selected.", e.getMessage());
		}
		
		assertEquals(0, swm.getServiceGroupList().length);
	}
	
	/**
	 * Test adding a service group
	 */
	@Test
	public void testAddServiceGroup() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		
		try {
			swm.addServiceGroup(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		
		try {
			swm.addServiceGroup("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		
		swm.addServiceGroup("name");
		assertEquals(1, swm.getServiceGroupList().length);
		assertEquals("name", swm.getServiceGroupName());
	}
	
	/**
	 * Test editing a service group
	 */
	@Test
	public void testEditServiceGroup() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		
		try {
			swm.editServiceGroup(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		
		try {
			swm.editServiceGroup("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		
		swm.addServiceGroup("name");
		assertEquals("name", swm.getServiceGroupName());
		swm.editServiceGroup("new name");
		assertEquals("new name", swm.getServiceGroupName());
	}
	
	/**
	 * Test deleting a service group
	 */
	@Test
	public void testDeleteServiceGroup() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		
		swm.addServiceGroup("name");
		swm.addServiceGroup("name 2");
		swm.addServiceGroup("name 3");
		assertEquals(3, swm.getServiceGroupList().length);
		String[] sgList = swm.getServiceGroupList();
		assertEquals("name", sgList[0]);
		assertEquals("name 2", sgList[1]);
		assertEquals("name 3", sgList[2]);
		
		swm.deleteServiceGroup();
		sgList = swm.getServiceGroupList();
		assertEquals(2, swm.getServiceGroupList().length);
		assertEquals("name", sgList[0]);
		assertEquals("name 2", sgList[1]);
		
		swm.deleteServiceGroup();
		sgList = swm.getServiceGroupList();
		assertEquals(1, swm.getServiceGroupList().length);
		assertEquals("name 2", sgList[0]);
	}
	
	/**
	 * Tests clearing the service groups
	 */
	@Test
	public void testClearServiceGroups() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		
		swm.addServiceGroup("name");
		swm.addServiceGroup("name 2");
		swm.addServiceGroup("name 3");
		assertEquals(3, swm.getServiceGroupList().length);
		String[] sgList = swm.getServiceGroupList();
		assertEquals("name", sgList[0]);
		assertEquals("name 2", sgList[1]);
		assertEquals("name 3", sgList[2]);
		
		swm.clearServiceGroups();
		assertEquals(0, swm.getServiceGroupList().length);
	}
	
	/**
	 * Test adding a duplicate service group
	 */
	@Test
	public void testCheckDuplicateServiceName() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		
		swm.addServiceGroup("name");
		assertEquals(1, swm.getServiceGroupList().length);
		
		try {
			swm.addServiceGroup("name");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		
		assertEquals(1, swm.getServiceGroupList().length);
	}
	
	/**
	 * Test adding Incidents to service groups
	 */
	@Test
	public void testAddIncidentToServiceGroup() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		
		swm.addServiceGroup("name");
		swm.addIncidentToServiceGroup("title", "caller", "message");
		assertEquals(1, swm.getIncidentsAsArray().length);
		swm.addServiceGroup("name 2");
		swm.addIncidentToServiceGroup("title 2", "caller 2", "message 2");
		swm.addIncidentToServiceGroup("title 3", "caller 3", "message 3");
		assertEquals(2, swm.getServiceGroupList().length);
		assertEquals(2, swm.getIncidentsAsArray().length);
	}
	
	/**
	 * Test loading service groups from a file
	 */
	@Test
	public void testLoadFromFile() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		swm.loadFromFile("test-files/incidents3.txt");
		assertEquals(3, swm.getServiceGroupList().length);
		
		String[] sgList = swm.getServiceGroupList();
		assertEquals("CSC IT", sgList[0]);
		assertEquals("ITECS", sgList[1]);
		assertEquals("OIT", sgList[2]);
	}
	
	/**
	 * Recreating jenkins tests
	 */
	@Test
	public void testAddServiceGroupThenLoadFile() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		swm.addServiceGroup("ServerGroup");
		assertEquals(1, swm.getServiceGroupList().length);
		String[] sgList = swm.getServiceGroupList();
		assertEquals("ServerGroup", sgList[0]);
		
		swm.loadFromFile("test-files/incidents1.txt");
		assertEquals(4, swm.getServiceGroupList().length);
		sgList = swm.getServiceGroupList();
		assertEquals("CSC IT", sgList[0]);
		assertEquals("ITECS", sgList[1]);
		assertEquals("OIT", sgList[2]);
		assertEquals("ServerGroup", sgList[3]);
		
		swm.addServiceGroup("ECE IT");
		assertEquals(5, swm.getServiceGroupList().length);
		sgList = swm.getServiceGroupList();
		assertEquals("CSC IT", sgList[0]);
		assertEquals("ECE IT", sgList[1]);
		assertEquals("ITECS", sgList[2]);
		assertEquals("OIT", sgList[3]);
		assertEquals("ServerGroup", sgList[4]);
	}
	
	/**
	 * Test delete invalid incidents by id
	 */
	@Test
	public void testDeleteIncidentByInvalidId() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		swm.loadFromFile("test-files/incidents1.txt");
		assertEquals(3, swm.getServiceGroupList().length);
		
		swm.deleteIncidentById(-1);
		assertEquals(3, swm.getServiceGroupList().length);
	}
	
	/**
	 * test deleting service groups from incidents1.txt
	 */
	@Test
	public void testDeleteSGs() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		swm.loadFromFile("test-files/incidents1.txt");
		assertEquals(3, swm.getServiceGroupList().length);
		
		String[] sgList = swm.getServiceGroupList();
		assertEquals("CSC IT", sgList[0]);
		assertEquals("ITECS", sgList[1]);
		assertEquals("OIT", sgList[2]);
		
		swm.deleteServiceGroup();
		sgList = swm.getServiceGroupList();
		assertEquals("ITECS", sgList[0]);
		assertEquals("OIT", sgList[1]);
		
		swm.deleteServiceGroup();
		sgList = swm.getServiceGroupList();
		assertEquals("OIT", sgList[0]);
	}
	
	/**
	 * test deleting service groups from incidents1.txt using loadServiceGroup
	 */
	@Test
	public void testDeleteLoadedSGs() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		swm.loadFromFile("test-files/incidents1.txt");
		assertEquals(3, swm.getServiceGroupList().length);
		
		String[] sgList = swm.getServiceGroupList();
		assertEquals("CSC IT", sgList[0]);
		assertEquals("ITECS", sgList[1]);
		assertEquals("OIT", sgList[2]);
		
		swm.deleteServiceGroup();
		sgList = swm.getServiceGroupList();
		assertEquals("ITECS", sgList[0]);
		assertEquals("OIT", sgList[1]);
		
		swm.loadServiceGroup("OIT");
		swm.deleteServiceGroup();
		sgList = swm.getServiceGroupList();
		assertEquals("ITECS", sgList[0]);
	}
	
	/**
	 * Test that loading a service group maintains the correct number of incidents
	 */
	@Test
	public void testLoadITECS() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		swm.loadFromFile("test-files/incidents1.txt");
		swm.loadServiceGroup("ITECS");
		assertEquals(1, swm.getIncidentsAsArray().length);
	}
	
	/**
	 * Test SaveToFile
	 */
	@Test
	public void testSaveToFile() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		swm.loadFromFile("test-files/incidents1.txt");
		assertEquals(3, swm.getServiceGroupList().length);
		swm.saveToFile("test-files/saveincidents1.txt");
		checkFiles("test-files/incidents1.txt", "test-files/saveincidents1.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Test get Incidents by id
	 */
	@Test
	public void testGetIncidentsById() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		swm.loadFromFile("test-files/incidents1.txt");
		assertEquals(2, swm.getIncidentById(2).getId());
		assertEquals(Incident.CANCELED_NAME, swm.getIncidentById(2).getState());
		assertEquals("sesmith5", swm.getIncidentById(2).getCaller());
		assertEquals("Piazza", swm.getIncidentById(2).getTitle());
		assertEquals(0, swm.getIncidentById(2).getReopenCount());
		assertEquals(Incident.UNOWNED, swm.getIncidentById(2).getOwner());
		assertEquals(Incident.CANCELLATION_NOT_AN_INCIDENT, swm.getIncidentById(2).getStatusDetails());
	}
	
	/**
	 * Tests execute command through the manager
	 */
	@Test
	public void testExecuteCommand() {
		ServiceWolfManager swm = ServiceWolfManager.getInstance();
		swm.loadFromFile("test-files/incidents1.txt");
		assertEquals(Incident.NEW_NAME, swm.getIncidentById(3).getState());
		Command c = new Command(Command.CommandValue.ASSIGN, "info", "message");
		swm.executeCommand(3, c);
		assertEquals(Incident.IN_PROGRESS_NAME, swm.getIncidentById(3).getState());
		
	}
}
