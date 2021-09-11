/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests Schedule
 * 
 * @author magolden
 * @author kbmille6
 * @author gmalbarr
 */
public class ScheduleTest {

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "002";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course enrollment cap */
    private static final int ENROLLMENT_CAP = 50;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TW";
	/** Course start time */
	private static final int START_TIME = 1300;
	/** Course end time */
	private static final int END_TIME = 1430;

	/**
	 * test Schedule constructor
	 */
	@Test
	public void testSchedule() {
		Schedule a = new Schedule();
		assertEquals("My Schedule", a.getTitle());
	}

	/**
	 * Test addCourseToSchedule
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule a = new Schedule();
		Course s = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course s1 = new Course(NAME, TITLE, "004", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		a.addCourseToSchedule(s);
		try {
			a.addCourseToSchedule(s1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC216", e.getMessage());
		}

		assertArrayEquals(s.getShortDisplayArray(), a.getScheduledCourses()[0]);
	}

	/**
	 * test removeCourseFromSchedule
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule a = new Schedule();
		Course s = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals("My Schedule", a.getTitle());
		assertFalse(a.removeCourseFromSchedule(s));
		a.addCourseToSchedule(s);
		assertTrue(a.removeCourseFromSchedule(s));
		Course s1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course s2 = new Course("CSC226", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 1500, 1600);
		a.addCourseToSchedule(s1);
		a.addCourseToSchedule(s2);
		assertTrue(a.removeCourseFromSchedule(s1));
		// assertArrayEquals(s2.getShortDisplayArray(), a.getScheduledCourses()[0]);

	}

	/**
	 * Test resetSchedule
	 */
	@Test
	public void testResetSchedule() {
		Schedule a = new Schedule();
		Course s = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(0, a.getScheduledCourses().length);
		a.addCourseToSchedule(s);
		assertEquals(1, a.getScheduledCourses().length);
		a.resetSchedule();
		assertEquals(0, a.getScheduledCourses().length);

	}
	
	/**
	 * Test getScheduleCredits
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule a = new Schedule();
		Course s = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(0, a.getScheduleCredits());
		a.addCourseToSchedule(s);
		assertEquals(4, a.getScheduleCredits());
	}
	
	/**
	 * Test canAdd
	 */
	@Test
	public void testCanAdd() {
		Schedule a = new Schedule();
		Course s = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertTrue(a.canAdd(s));
		assertFalse(a.canAdd(null));
		a.addCourseToSchedule(s);
		assertFalse(a.canAdd(s));
	}

}
