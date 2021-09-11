package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Custom exception called ConflictException that is thrown when an activity's meeting times
 * conflict with a scheduled activity's meeting times
 * @author magolden
 */
public class ConflictException extends Exception {

	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ConflictException constructor with custom message
	 * @param message Custom message for ConflictException
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * ConflictException constructor with default message of "Schedule conflict."
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
