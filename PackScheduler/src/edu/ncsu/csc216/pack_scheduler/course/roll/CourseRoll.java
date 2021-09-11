package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.ArrayQueue;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * Tracks what students are in a class and manages adding/removing them
 * @author kbmille6
 * @author magolden
 *
 */
public class CourseRoll {
	/** The current maximum number of students in the roll */
	private int enrollmentCap;
	/** The minimum value for enrollmentCap */
	private static final int MIN_ENROLLMENT = 10;
	/** The maximum value for enrollmentCap */
	private static final int MAX_ENROLLMENT = 250;
	/** The list of all students currently enrolled */
	private LinkedAbstractList<Student> roll;
	/** The list of students in the waitlist */
	private ArrayQueue<Student> waitlist;
	/** Course that a student can be enrolled in */
	private Course course;
	
	/**
	 * Constructs a new CourseRoll
	 * @param course the course associated with the CourseRoll
	 * @param enrollmentCap the maximum number of students in the roll, cannot be less
	 * than 10 or more than 250
	 * @throws IllegalArgumentException with the message "Course cannot be null" if the course is null
	 */
	public CourseRoll(Course course, int enrollmentCap) {
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(getEnrollmentCap());
		waitlist = new ArrayQueue<Student>(MIN_ENROLLMENT);
		if (course == null) {
			throw new IllegalArgumentException("Course cannot be null");
		}
		this.course = course;
	}
	
	/**
	 * Returns the current maximum number of students in the roll
	 * @return the current maximum number of students in the roll
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * Sets the enrollmentCap for the CourseRoll
	 * @param newCap the maximum number of students in the roll, cannot be less
	 * than 10 or more than 250
	 * @throws IllegalArgumentException if roll or newCap is invalid
	 */
	public void setEnrollmentCap(int newCap) {
		if (newCap < MIN_ENROLLMENT || newCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		
		if (this.roll != null && newCap < this.roll.size()) {
			throw new IllegalArgumentException();
		}
		
		if (newCap > this.enrollmentCap && this.roll != null) {
			LinkedAbstractList<Student> newRoll = new LinkedAbstractList<Student>(newCap);
			for (int i = 0; i < roll.size(); i++) {
				newRoll.add(roll.get(i));
			}
			newRoll.setCapacity(newCap);
			this.roll = newRoll;
		}
		
		this.enrollmentCap = newCap;
	}
	
	/**
	 * Enrolls a student in the course
	 * @param student the student to enroll.
	 * @throws IllegalArgumentException if the student cannot be enrolled or the student
	 * is null
	 */
	public void enroll(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		if (!canEnroll(student)) {
			throw new IllegalArgumentException();
		}
		if (roll.getCapacity() == roll.size()) {
			try {
				waitlist.enqueue(student);	
				return;
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
		}
		try {
			roll.add(roll.size(), student);
			student.getSchedule().addCourseToSchedule(this.course);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Drops a student from the course
	 * @param student the student to drop
	 * @throws IllegalArgumentException if the student cannot be dropped or the student
	 * is null
	 */
	public void drop(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(student)) {
				roll.remove(i);
				if (waitlist.size() != 0) {
					enroll(waitlist.dequeue());
				}
				
			}
		}
		
		try {
			for (int i = 0; i < waitlist.size(); i++) {
				Student removed = waitlist.dequeue();
				if (removed.equals(student)) {
					//Removed student
					continue;
				}
				waitlist.enqueue(removed);
			}			
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

	}
	
	/**
	 * Returns how many seats are open in the course
	 * @return how many seats are open in the course
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Returns whether a student can enroll in the course, it will be true unless there
	 * are no open seats or the student is already enrolled in the course
	 * @param student the student to check with
	 * @return whether a student can enroll in the course
	 */
	public boolean canEnroll(Student student) {
		if (waitlist.size() == MIN_ENROLLMENT) {
			return false;
		}
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(student)) {
				return false;
			}
		}
		for (int i = 0; i < waitlist.size(); i++) {
			Student removed = waitlist.dequeue();
			if (removed.equals(student)) {
				return false;
			}
			waitlist.enqueue(removed);
		}
		return true;
	}
	
	/**
	 * Returns the number of students on the waitlist. 
	 * @return number of students in waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}
