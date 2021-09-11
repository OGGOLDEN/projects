/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Tests FacultyRecordIO
 * @author magolden
 */
public class FacultyRecordIOTest {

	/**valid faculty to test for*/
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	
	/**valid faculty to test for*/
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	
	/**valid faculty to test for*/
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	
	/**valid faculty to test for*/
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	
	/**valid faculty to test for*/
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	
	/**valid faculty to test for*/
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	
	/**valid faculty to test for*/
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	
	/**valid faculty to test for*/
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";
	
	/**array of all of the valid faculty from earlier*/
	private String [] validFaculty = {validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4, 
			validFaculty5, validFaculty6, validFaculty7};
	
	/** hash password instance*/
	private String hashPW;
	/**string for hash algorithm*/
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**file location for valid faculty records file*/
	private String validFacultyRecords = "test-files/faculty_records.txt";
	/**file location for invalid faculty records file*/
	private String invalidFacultyRecords = "test-files/invalid_faculty_records.txt";
	
	/**
	 * setup method for StudentRecordIOTest
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validFaculty.length; i++) {
	            validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * test case for valid faculty records
	 */
	@Test
	public void testValidReadFacultyRecords() {
		try {
			LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords(validFacultyRecords);
			assertEquals(validFaculty.length, faculty.size());
			
			for (int i = 0; i < validFaculty.length; i++) {
				assertEquals(validFaculty[i], faculty.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validFacultyRecords);
		}
	}
	
	/**
	 * test case for invalid faculty records
	 */
	@Test
	public void testInvalidReadFacultyRecords() {
		LinkedList<Faculty> faculty = null;
		
		try {
			faculty = FacultyRecordIO.readFacultyRecords(invalidFacultyRecords);
			assertEquals(0, faculty.size());
		} catch (FileNotFoundException e) {
			fail();
		}
		
		faculty = null;
		try {
			faculty = FacultyRecordIO.readFacultyRecords("asdfg");
			fail();
		} catch (FileNotFoundException e) {
			// do nothing
		}
	}
	
	/**
	 * test cases for writefacultyRecords
	 */
	@Test
	public void testWriteFacultyRecords() {

		try {
			LinkedList<Faculty> faculty = new LinkedList<Faculty>();
			faculty.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
			faculty.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
			faculty.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));
			FacultyRecordIO.writeFacultyRecords("test-files/testWriteFacultyRecords", faculty);
			checkFiles("test-files/expected_faculty_records.txt", "test-files/testWriteFacultyRecords");
		} catch (IOException e) {
			fail();
		}
	}
	
	/**
	 * test cases for WritefacultyRecords without permissions
	 */
	@Test
	public void testWritefacultyRecordsNoPermissions() {
	    LinkedList<Faculty> facultys = new LinkedList<Faculty>();
	    facultys.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    
	    try {
	        FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_faculty_records.txt", facultys);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_faculty_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	}
	
	/**
	 * Checks if two files are the same
	 * @param expFile expected file
	 * @param actFile actual file
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}
}
