/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Manages the addition and removal of a course from a student's schedule
 * @author magolden
 * @author kbmille6
 * @author gmalbarr
 */
public class Schedule {

	/** Title of Schedule */
	private String title;
	
	/** List of courses is the student's schedule */
	private ArrayList<Course> schedule;
	
	/**
	 * Schedule Constructor
	 * Creates a new list of courses and sets the title to the default "My Schedule"
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}
	
	/**
	 * If the course to be added is not a duplicate of a course already in the schedule and it does not
	 * conflict with any other activities in the schedule, it is added to the schedule
	 * @param c Course to be added
	 * @return true if the course was successfully added to the schedule
	 * @throws IllegalArgumentException with the message "You are already enrolled in course name if the course is already in the schedule
	 * @throws IllegalArgumentException with the message "The course cannot be added due to a conflict." if there is a scheduling conflict
	 */
	public boolean addCourseToSchedule(Course c) {
		for (int i = 0; i < this.schedule.size(); i++) {
			if (c.isDuplicate(this.schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
			try {
				c.checkConflict(this.schedule.get(i));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		
		this.schedule.add(c);
		
		return true;
	}
	
	/**
	 * Removes the given course from the schedule
	 * @param c Course to be removed
	 * @return true if the course was successfully removed
	 * 		   false if the course did not previously exist in the schedule and therefore could not be removed
	 */
	public boolean removeCourseFromSchedule(Course c) {
		if (c == null) {
			return false;
		}
		
		for (int i = 0; i < this.schedule.size(); i++) {
			if (c.isDuplicate(this.schedule.get(i))) {
				this.schedule.remove(c);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Resets the schedule to the default title and empty list of courses
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}
	
	/**
	 * Gets the name, section, title, and meeting string of each course is the schedule and adds it to a 2D array
	 * @return courseSchedule 2D String array of course info
	 */
	public String[][] getScheduledCourses() {
//		int scheduleSize = this.schedule.size();
//		String[][] courseSchedule = new String[scheduleSize][3];
//
//		// if nothing has been scheduled, return the empty array
//		if (scheduleSize == 0) {
//			return courseSchedule;
//		} else {
//			// for each course in the schedule, get its name, section, and title to enter into
//			// the courseSchedule array
//			for (int i = 0; i < scheduleSize; i++) {
//				Course course = this.schedule.get(i);
//				String name = course.getName();
//				String section = course.getSection();
//				String courseTitle = course.getTitle();
//				String meetingString = course.getMeetingString();
//				// enter the course info into its desired index in the array
//				for (int j = 0; j < 3; j++) {
//					if (j == 0) {
//						courseSchedule[i][j] = name;
//					} else if (j == 1) {
//						courseSchedule[i][j] = section;
//					} else if (j == 2 ){
//						courseSchedule[i][j] = courseTitle;
//					} else {
//						courseSchedule[i][j] = meetingString;
//					}
//				}
//			}
//			return courseSchedule;
//		}
//	}
		String[][] scheduledCourses = new String[schedule.size()][5];
		for (int r = 0; r < scheduledCourses.length; r++) {
			for (int c = 0; c < 5; c++) {
				scheduledCourses[r][c] = schedule.get(r).getShortDisplayArray()[c];
			}
		}
		return scheduledCourses;
	}

	/**
	 * Gets the schedule title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the schedule
	 * @param title the title to set
	 * @throws IllegalArgumentException with the message "Title cannot be null." if the title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		
		this.title = title;
	}
	
	/**
	 * Returns the sum of the total credits in the schedule
	 * @return the sum of the total credits in the schedule
	 */
	public int getScheduleCredits() {
		int sum = 0;
		for (int i = 0; i < schedule.size(); i++) {
			sum += schedule.get(i).getCredits();
		}
		return sum;
	}
	
	/**
	 * Determines whether a course can be added to the schedule or not
	 * @param checkCourse the course to check with
	 * @return false if the course is null, already in the schedule, or would create a
	 * conflict.  true otherwise
	 */
	public boolean canAdd(Course checkCourse) {
		if (checkCourse == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (checkCourse.equals(schedule.get(i))) {
				return false;
			}
			if (checkCourse.isDuplicate(schedule.get(i))) {
				return false;
			}
			try {
				checkCourse.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				return false;
			}
		}
		
		return true;
	}
}
