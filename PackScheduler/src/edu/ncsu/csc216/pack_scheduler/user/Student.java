package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Student class represents a single student in
 * a school's registry. Each student is listed with a first name,
 * last name, id, email, password, and max credits number.
 * Implements the Comparable Interface
 * 
 * @author magolden
 * @author khanser
 * @author tcgelman
 *
 */
public class Student extends User implements Comparable<Student> {
	/** Student's max credits */
	private int maxCredits;
	/** Maximum number of credits */
	public static final int MAX_CREDITS = 18;
	/** Minimum number of credits */
	public static final int MIN_CREDITS = 3;
	/** Student's schedule */
	private Schedule schedule;

	/**
	 * Student Constructor: contains firstName, lastName, id, email, hashPW, and maxCredits
	 * @param firstName String Student's first name
	 * @param lastName String Student's last name
	 * @param id String Student's id
	 * @param email String Student's email
	 * @param hashPW String Student's hashPW
	 * @param maxCredits int Student's maxCredits
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		this.schedule = new Schedule();
	}
	
	/**
	 * Student Constructor: contains firstName, lastName, id, email, hashPW, and maxCredits as 18
	 * @param firstName String Student's first name
	 * @param lastName String Student's last name
	 * @param id String Student's id
	 * @param email String Student's email
	 * @param hashPW String Student's hashPW
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, 18);
	}
	
	/**
	 * Gets student's max credits
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets student's max credits
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException with the message "Invalid max credits"
	 * if the max credits is null or empty
	 */
	public void setMaxCredits(int maxCredits) {
		if(maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Gets the Student's Schedule
	 * @return schedule
	 */
	public Schedule getSchedule() {
		return this.schedule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

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
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}

	/**
	 * Returns the student's first name, last name, id, email, hashPW,
	 * and maxCredits as a comma separated string.
	 * @return String
	 */
	@Override
	public String toString() {
		String s = "";
		s = this.getFirstName() + "," + this.getLastName() + "," + this.getId() + "," + this.getEmail() + "," + this.getPassword() + "," + this.maxCredits;
		// TODO Auto-generated method stub
		return s;
	}

	/**
	 * Compares two Student objects and returns 0 if they're equal,
	 * an integer less than 0 if this object is less than o,
	 * an integer greater than 0 if this object is greater than 0.
	 * Inherited from Comparable interface.
	 * @param o Student object being compared
	 * @return see above
	 */
	@Override
	public int compareTo(Student o) {
		if(this.equals(o)) {
			return 0;
		}
		if(this.getLastName().equals(o.getLastName())) {
			if(this.getFirstName().equals(o.getFirstName())) {
				return this.getId().compareTo(o.getId());
			} else {
				return this.getFirstName().compareTo(o.getFirstName());
			}
		} else {
			return this.getLastName().compareTo(o.getLastName());
		}
	}
	
	/**
	 * Determines whether a course can be added to a student's schedule or not
	 * @param checkCourse the course to check with
	 * @return false if the course is null, already in the schedule, would create a
	 * conflict, or would make the student have too many credits.  true otherwise
	 */
	public boolean canAdd(Course checkCourse) {
		return schedule.canAdd(checkCourse) && checkCourse.getCredits() + schedule.getScheduleCredits() <= maxCredits;
	}

}
