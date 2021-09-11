package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * The Test Cases for CourseCatalog.java
 * @author tcgelman
 * @author khanser
 * @author magolden
 *
 */
public class CourseCatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** enrollment Cap*/
	private static final int ENROLLMENT_CAP = 10;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if files are not accessible.
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests CourseCatalog() and newCourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		//Test with invalid file.  Should have an empty catalog and schedule. 
		CourseCatalog cc1 = new CourseCatalog();
		assertEquals(0, cc1.getCourseCatalog().length);
		cc1.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
		//Test with valid file containing 13 courses.  Will test other methods in other tests.
		cc1.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc1.getCourseCatalog().length);	
		cc1.newCourseCatalog();
		assertEquals(0, cc1.getCourseCatalog().length);
		
	}
	
	/**
	 * Test CourseCatalog.addCourseToCatalog().
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();
		Activity c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		//Attempt to add a course that doesn't exist
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(1, cc.getCourseCatalog().length);
		String [] course = cc.getCourseCatalog()[0];
		assertEquals(NAME, course[0]);
		assertEquals(SECTION, course[1]);
		assertEquals(TITLE, course[2]);
		assertEquals(c.getMeetingString(), course[3]);
		
		//Attempt to add a course that already exists
		try {
			assertFalse(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		} catch (IllegalArgumentException e) {
			fail();
		}
		
	}
	
	/**
	 * Tests addDuplicateCourseToCatalog
	 * please don't work
	 */
	@Test
	public void testAddDuplicateCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		cc.addCourseToCatalog(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		cc.addCourseToCatalog("CSC116", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		cc.addCourseToCatalog("CSC226", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		cc.addCourseToCatalog("CSC116", TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		assertFalse(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		
	}
	
	/**
	 * Test CourseCatalog.removeCourseFromCatalog()
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		
		assertTrue(cc.removeCourseFromCatalog(NAME, SECTION));
		assertEquals(0, cc.getCourseCatalog().length);
		assertFalse(cc.removeCourseFromCatalog(NAME, SECTION));
	}
	
	/**
	 * Test CourseCatalog.getCourseFromCatalog()
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, null, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		assertEquals(c1, cc.getCourseFromCatalog(NAME, SECTION));
		assertNull(cc.getCourseFromCatalog("uyhhhh", "hee hoo"));
	}
	
	/**
	 * Test CourseCatalog.getCourseCatalog()
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		assertEquals(cc.getCourseCatalog().length, 0);
		
		cc.loadCoursesFromFile(validTestFile);
		
		assertEquals(cc.getCourseCatalog().length, 13);
	}
	
	/**
	 * Test CourseCatalog.saveCourseCatalog()
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", ENROLLMENT_CAP, "MW", 1250, 1440);
		cc.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1330, 1445);
		cc.addCourseToCatalog("CSC216", "Software Development Fundamentals", "601", 3, "jctetter", ENROLLMENT_CAP, "A", 0, 0);
		
		try {
			cc.saveCourseCatalog("test-files/actual_course_records.txt");
			checkFiles("test-files/expected_course_records.txt", "test-files/actual_course_records.txt");
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			cc.saveCourseCatalog("/home/sesmith5/actual_student_records.txt");
			fail();
		} catch (IllegalArgumentException e) {
			//should fail here, if fail we gucci
		}
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
