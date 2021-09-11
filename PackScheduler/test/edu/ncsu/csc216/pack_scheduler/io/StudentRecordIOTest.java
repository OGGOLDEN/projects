package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Test file for StudentRecordIO
 * @author tcgelman
 * @author magolden
 * @author khanser
 *
 */
public class StudentRecordIOTest {
	/**valid student to test for*/
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/**valid student to test for*/
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/**valid student to test for*/
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/**valid student to test for*/
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/**valid student to test for*/
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/**valid student to test for*/
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/**valid student to test for*/
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/**valid student to test for*/
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/**valid student to test for*/
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/**valid student to test for*/
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/**array of all of the valid students from earlier*/
	private String [] validStudents = {validStudent3, validStudent6, validStudent4, validStudent5, validStudent2, 
			validStudent8, validStudent0, validStudent9, validStudent1, validStudent7};

	/** hash password instance*/
	private String hashPW;
	/**string for hash algorithm*/
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**file location for valid student records file*/
	private String validStudentRecords = "test-files/student_records.txt";
	/**file location for invalid student records file*/
	private String invalidStudentRecords = "test-files/invalid_student_records.txt";
	
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
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * test case for valid student records
	 */
	@Test
	public void testValidReadStudentRecords() {
		try {
			SortedList<Student> student = StudentRecordIO.readStudentRecords(validStudentRecords);
			assertEquals(validStudents.length, student.size());
			
			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], student.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validStudentRecords);
		}
	}
	
	/**
	 * test case for invalid student records
	 */
	@Test
	public void testInvalidReadStudentRecords() {
		SortedList<Student> student = null;
		
		try {
			student = StudentRecordIO.readStudentRecords(invalidStudentRecords);
			assertEquals(0, student.size());
		} catch (FileNotFoundException e) {
			fail();
		}
		
		student = null;
		try {
			student = StudentRecordIO.readStudentRecords("asdfg");
			fail();
		} catch (FileNotFoundException e) {
			// do nothing
		}
	}
	
	/**
	 * test cases for writeStudentRecords
	 */
	@Test
	public void testWriteStudentRecords() {

		try {
			SortedList<Student> student = new SortedList<Student>();
			student.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
			StudentRecordIO.writeStudentRecords("test-files/testWriteStudentRecords", student);
			checkFiles("test-files/expected_student_records.txt", "test-files/testWriteStudentRecords");
		} catch (IOException e) {
			fail();
		}
	}
	
	/**
	 * test cases for WriteStudentRecords without permissions
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    
	    try {
	        StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
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
