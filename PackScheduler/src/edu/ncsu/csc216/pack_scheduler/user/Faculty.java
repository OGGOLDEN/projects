package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Faculty class represents a single faculty member in a school's registry. Each
 * faculty member is listed with a first name, last name, id, email, password,
 * and max courses number.
 * 
 * @author Lennox Latta
 *
 */
public class Faculty extends User {

	/** The max amount of course the Faculty member can teach. */
	private int maxCourses;
	/**
	 * The minimum amount of courses a Faculty member can teach in a given semester.
	 */
	public static final int MAX_COURSES = 3;
	/**
	 * The maximum amount of courses a Faculty member can teach in a given semester.
	 */
	public static final int MIN_COURSES = 1;
	
	/** Schedule of courses that a Faculty memeber teaches */
	private FacultySchedule schedule;

	/**
	 * Constructor for the Faculty object.
	 * 
	 * @param firstName  first name of the Faculty member
	 * @param lastName   last name of the Faculty member
	 * @param id         id of the Faculty member
	 * @param email      email of the Faculty member
	 * @param password   password of the Faculty member
	 * @param maxCourses the max amount of course the Faculty member can teach.
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		this.schedule = new FacultySchedule(id);
	}

	/**
	 * Setter method for the maxCourse variable.
	 * 
	 * @param maxCourses the max amount of course the Faculty member can teach.
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}

	/**
	 * Getter method for the maxCourse variable.
	 * 
	 * @return maxCourses the max amount of course the Faculty member can teach.
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Get the hash code for the Faculty member.
	 * @return the hashed result.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Checks to see if two Faculty members are equal to each other.
	 * 
	 * @return true if the two Faculty members are equal to each other and false is
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Faculty other = (Faculty) obj;
		return maxCourses == other.maxCourses;
	}

	/**
	 * Returns the Faculty member's first name, last name, id, email, hashPW, and
	 * maxCredits as a comma separated string.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		String s = "";
		s = this.getFirstName() + "," + this.getLastName() + "," + this.getId() + "," + this.getEmail() + ","
				+ this.getPassword() + "," + this.maxCourses;
		return s;
	}

	/**
	 * Returns true is a faculty member has more courses in their schedule than they are allowed to
	 * @return true if num of courses is greater than maxCourses
	 */
	public boolean isOverloaded() {
		return getSchedule().getNumScheduledCourses() > this.maxCourses;
	}

	/**
	 * Gets the schedule
	 * @return Faculty's schedule
	 */
	public FacultySchedule getSchedule() {
		return this.schedule;
	}
}
