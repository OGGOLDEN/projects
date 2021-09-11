package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests the InvalidTransitionException
 * @author magolden
 * @author kbmille6
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Tests InvalidTransitionException with a custom error message
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException ite = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", ite.getMessage());
	}

	/**
	 * Tests InvalidTransitionException with a default error message
	 */
	@Test
	public void testInvalidTransitionException() {
		InvalidTransitionException ite = new InvalidTransitionException();
	    assertEquals("Invalid FSM Transition.", ite.getMessage());
	}

}

