/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Custom exception for the CourseNameValidator FSM
 * @author magolden
 * @author kbmille6
 */
public class InvalidTransitionException extends Exception {
	
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * InvalidTransitionException constructor with custom message
	 * @param message Custom message for InvalidTransitionException
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * InvalidTransitionException constructor with default message of "Schedule conflict."
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}

}
