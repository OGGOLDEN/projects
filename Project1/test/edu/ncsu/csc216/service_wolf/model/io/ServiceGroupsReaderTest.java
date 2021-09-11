/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * Tests ServiceGroupReader
 * @author magolden
 */
public class ServiceGroupsReaderTest {

	/**
	 * Test ServiceGroupReader
	 */
	@Test
	public void testServiceGroupsReader() {
		ArrayList<ServiceGroup> sgList;
		
		sgList = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents1.txt");
		assertEquals(3, sgList.size());

		try {
			sgList = ServiceGroupsReader.readServiceGroupsFile("file that doesnt exist");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}
	}
	
	/**
	 * Test reading an invalid file
	 */
	@Test
	public void testReadingIncidents5() {
		ArrayList<ServiceGroup> sgList;
		
		sgList = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents5.txt");
		assertEquals(0, sgList.size());
	}
}
