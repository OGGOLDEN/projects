/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests CourseNameValidatorFSM
 * @author kbmille6
 * @author magolden
 *
 */
public class CourseNameValidatorFSMTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM#isValid(java.lang.String)}.
	 */
	@Test
	public void testIsValid() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		// Test invalid course name (empty)
		try {
			assertFalse(fsm.isValid(""));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		// Test invalid course name (non alphanumeric characters)
		try {
			fsm.isValid("???");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		// Test invalid course name (starts with digit)
		try {
			fsm.isValid("0");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		// Test invalid course name (too many letters)
		try {
			fsm.isValid("AAAAA");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		// Test invalid course name (too many digits)
		try {
			fsm.isValid("A0000");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		// Test invalid course name (too few digits (1))
		try {
			fsm.isValid("A0A");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		// Test invalid course name (too few digits (2))
		try {
			fsm.isValid("A00A");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		// Test invalid course name (too long of a suffix)
		try {
			fsm.isValid("A000AA");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		// Test invalid course name (digits after suffix)
		try {
			fsm.isValid("A000A0");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		
		// Test incomplete course name (no digits (1))
		try {
			assertFalse(fsm.isValid("A"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		// Test incomplete course name (no digits (2))
		try {
			assertFalse(fsm.isValid("AA"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		// Test incomplete course name (no digits (3))
		try {
			assertFalse(fsm.isValid("AAA"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		// Test incomplete course name (no digits (4))
		try {
			assertFalse(fsm.isValid("AAAA"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		// Test incomplete course name (too few digits (1))
		try {
			assertFalse(fsm.isValid("AAA0"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		// Test incomplete course name (too few digits (2))
		try {
			assertFalse(fsm.isValid("AAA00"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		// Test valid course names
		try {
			// No suffix
			assertTrue(fsm.isValid("A000"));
			assertTrue(fsm.isValid("AA000"));
			assertTrue(fsm.isValid("AAA000"));
			assertTrue(fsm.isValid("AAAA000"));
			// With suffix
			assertTrue(fsm.isValid("A000A"));
			assertTrue(fsm.isValid("AA000A"));
			assertTrue(fsm.isValid("AAA000A"));
			assertTrue(fsm.isValid("AAAA000A"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
	}

}
