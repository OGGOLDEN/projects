package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Provides conflict checking functionality to classes in the Activity hierarchy
 * @author magolden
 */
public interface Conflict {

	/**
	 * Checks to see if the given activity's meeting days and times overlap with an
	 * existing scheduled activity
	 * @param possibleConflictingActivity activity object with potential to cause conflict
	 * @throws ConflictException with the message "Schedule conflict." if two activities occur on the same day and time
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
