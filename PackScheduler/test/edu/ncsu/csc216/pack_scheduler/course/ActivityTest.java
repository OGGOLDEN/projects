package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Activity
 * @author magolden
 */
public class ActivityTest {

	/**
	 * Test checkConflict with no conflict
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "TH", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	}
	
	/**
	 * Tests checkConflict with conflict
	 */
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "M", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "T", 1330, 1445);
	    Activity a4 = new Course("CSC999", "Really cool coding class", "001", 3, "sesmith5", 50, "T", 1445, 1600);
	    try {
	        a3.checkConflict(a4);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "T 1:30PM-2:45PM", a3.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "T 2:45PM-4:00PM", a4.getMeetingString());
	    }
	    
	    Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "W", 1330, 1445);
	    Activity a6 = new Course("CSC999", "Really cool coding class", "001", 3, "sesmith5", 50, "W", 1445, 1600);
	    try {
	        a5.checkConflict(a6);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "W 1:30PM-2:45PM", a5.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "W 2:45PM-4:00PM", a6.getMeetingString());
	    }
	    
	    Activity a7 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "H", 1330, 1445);
	    Activity a8 = new Course("CSC999", "Really cool coding class", "001", 3, "sesmith5", 50, "H", 1445, 1600);
	    try {
	        a7.checkConflict(a8);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "H 1:30PM-2:45PM", a7.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "H 2:45PM-4:00PM", a8.getMeetingString());
	    }
	    
	    Activity a9 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "F", 1330, 1445);
	    Activity a10 = new Course("CSC999", "Really cool coding class", "001", 3, "sesmith5", 50, "F", 1445, 1600);
	    try {
	        a9.checkConflict(a10);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "F 1:30PM-2:45PM", a9.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "F 2:45PM-4:00PM", a10.getMeetingString());
	    }
	}
	
	/**
	 * Test checkConflict with no conflict and method is commutative
	 */
	@Test
	public void testCheckConflictSwapped() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "TH", 1330, 1445);
	    try {
	        a2.checkConflict(a1);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	}
	
	/**
	 * Test checkConflict with conflicts
	 */
	@Test
	public void testCheckConflictJenkins() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC217", "Software Development Fundamentals Lab", "001", 3, "sesmith5", 50, "W", 1400, 1430);
	    try {
	        a1.checkConflict(a2);
	        fail("A ConflictException was not thrown as expected");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "W 2:00PM-2:30PM", a2.getMeetingString());
	    }
	}
	
//	/**
//	 * Test checkConflict with same day but no time conflict
//	 */
//	@Test
//	public void testCheckConflictSameDay() {
//		
//	}
	
	/**
	 * Tests for various times with no conflict
	 */
	@Test
	public void testActivtyVariousTimes() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "MW", 0, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "TH", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 12:00AM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "MW", 330, 445);
	    Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "TH", 1308, 1405);
	    try {
	        a3.checkConflict(a4);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 3:30AM-4:45AM", a3.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:08PM-2:05PM", a4.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	}
	
	/**
	 * Test Activity for invalid times
	 */
	@Test
	public void testInvalidTimes() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 50, "MW", 0, 1445);
		try {
			a1.setMeetingDaysAndTime("T", -1, 959);
			fail("Meeting days and time should not have changed");
		} catch (IllegalArgumentException e) {
			assertEquals("MW", a1.getMeetingDays());
			assertEquals(0, a1.getStartTime());
			assertEquals(1445, a1.getEndTime());
		}
	}
	
//	/**
//	 * Tests equals comparison
//	 */
//	@Test
//	public void testEquals() {
//
//	}

}

