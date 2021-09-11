/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.io;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * Tests ServiceGroupWriter
 * @author magolden
 */
public class ServiceGroupWriterTest {

	/**
	 * Resets the Singleton instance before each test
	 */
	@Before
	public void setUp() {
//		ServiceWolfManager swm = ServiceWolfManager.getInstance();
//		swm.resetManager();
	}
	
	/**
	 * Test writing service groups/incidents to a file
	 */
	@Test
	public void testWriteServiceGroupsToFile() {
		ArrayList<ServiceGroup> sgList;
		
		sgList = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents1.txt");
		assertEquals(3, sgList.size());
		ServiceGroupWriter.writeServiceGroupsToFile("test-files/incidents1_output.txt", sgList);
		checkFiles("test-files/incidents1.txt", "test-files/incidents1_output.txt");
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
}
