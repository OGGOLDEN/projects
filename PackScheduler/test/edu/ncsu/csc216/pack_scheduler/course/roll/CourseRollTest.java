/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
//import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Tests CourseRoll
 * @author kbmille6
 * @author magolden
 */
public class CourseRollTest {

	/**
	 * Test Constructor
	 */
	@Test
	public void testConstructor() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = new CourseRoll(c, 100);
		assertEquals(100, cr.getEnrollmentCap());
		assertEquals(0, cr.getNumberOnWaitlist());
		
		c = null;
		try {
			cr = new CourseRoll(c, 100);
		} catch (IllegalArgumentException e) {
			assertEquals("Course cannot be null", e.getMessage());
		}
	}
	
	/**
	 * Test setEnrollmentCap
	 */
	@Test
	public void testSetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = new CourseRoll(c, 100);
		assertEquals(100, cr.getEnrollmentCap());
		cr.setEnrollmentCap(150);
		assertEquals(150, cr.getEnrollmentCap());
		try {
			cr.setEnrollmentCap(5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(150, cr.getEnrollmentCap());
		}
		
		try {
			cr.setEnrollmentCap(350);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(150, cr.getEnrollmentCap());
		}
	}
	
	/**
	 * Test getOpenSeats
	 */
	@Test
	public void testGetOpenSeats() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = new CourseRoll(c, 100);
		assertEquals(100, cr.getOpenSeats());
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		cr.enroll(s);
		assertEquals(99, cr.getOpenSeats());
		try {
			cr.enroll(s);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(99, cr.getOpenSeats());
		}
		try {
			cr.enroll(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(99, cr.getOpenSeats());
		}
		try {
			cr.drop(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(99, cr.getOpenSeats());
		}
		cr.drop(s);
		assertEquals(100, cr.getOpenSeats());
	}

	/**
	 * Test waitlist
	 */
	@Test
	public void testWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = new CourseRoll(c, 10);
		assertEquals(10, cr.getOpenSeats());
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s1 = new Student("first", "different", "id", "email@ncsu.edu", "hashedpassword");
        Student s2 = new Student("different", "last", "id", "email@ncsu.edu", "hashedpassword");
        Student s3 = new Student("first", "haha", "id", "email@ncsu.edu", "hashedpassword");
        Student s4 = new Student("first", "last", "ooo", "email@ncsu.edu", "hashedpassword");
        Student s5 = new Student("first", "last", "id", "something@idk.io", "hashedpassword");
        Student s6 = new Student("first", "last", "id", "email@ncsu.edu", "oopzies");
        Student s7 = new Student("first", "idk", "id", "email@ncsu.edu", "hashedpassword");
        Student s8 = new Student("sure", "last", "id", "email@ncsu.edu", "hashedpassword");
        Student s9 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
        Student s10 = new Student("first", "last", "different", "email@ncsu.edu", "hashedpassword", 6);
		cr.enroll(s);
		assertEquals(9, cr.getOpenSeats());
		assertEquals(1, s.getSchedule().getScheduledCourses().length);
		cr.enroll(s1);
		assertEquals(8, cr.getOpenSeats());
		cr.enroll(s2);
		assertEquals(7, cr.getOpenSeats());
		cr.enroll(s3);
		assertEquals(6, cr.getOpenSeats());
		cr.enroll(s4);
		assertEquals(5, cr.getOpenSeats());
		cr.enroll(s5);
		assertEquals(4, cr.getOpenSeats());
		cr.enroll(s6);
		assertEquals(3, cr.getOpenSeats());
		cr.enroll(s7);
		assertEquals(2, cr.getOpenSeats());
		cr.enroll(s8);
		assertEquals(1, cr.getOpenSeats());
		cr.canEnroll(s9);
		cr.enroll(s9);
		assertEquals(0, cr.getOpenSeats());
		
		cr.canEnroll(s10);
		
		cr.enroll(s10);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());
		
		cr.canEnroll(s10);
		
		cr.drop(s10);
		assertEquals(0, cr.getNumberOnWaitlist());
		
		cr.canEnroll(s10);
		
		cr.enroll(s10);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());
		
		cr.canEnroll(s6);
		
		cr.drop(s6);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(0, cr.getNumberOnWaitlist());
		assertEquals(1, s10.getSchedule().getScheduledCourses().length);
		
		cr.canEnroll(s6);
		
		Student s11 = new Student("11", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s12 = new Student("12", "different", "id", "email@ncsu.edu", "hashedpassword");
        Student s13 = new Student("13t", "last", "id", "email@ncsu.edu", "hashedpassword");
        Student s14 = new Student("14t", "haha", "id", "email@ncsu.edu", "hashedpassword");
        Student s15 = new Student("15t", "last", "ooo", "email@ncsu.edu", "hashedpassword");
        Student s16 = new Student("f16t", "last", "id", "something@idk.io", "hashedpassword");
        Student s17 = new Student("f17t", "last", "id", "email@ncsu.edu", "oopzies");
        Student s18 = new Student("18", "idk", "id", "email@ncsu.edu", "hashedpassword");
        Student s19 = new Student("s19", "last", "id", "email@ncsu.edu", "hashedpassword");
        Student s20 = new Student("20t", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
        Student s21 = new Student("21t", "last", "different", "email@ncsu.edu", "hashedpassword", 6);
        
        cr.enroll(s11);
        cr.enroll(s12);
        cr.enroll(s13);
        cr.enroll(s14);
        cr.enroll(s15);
        cr.enroll(s16);
        cr.enroll(s17);
        cr.enroll(s18);
        cr.enroll(s19);
        cr.enroll(s20);
        try {
        	cr.enroll(s21);
        } catch (IllegalArgumentException e) {
        	assertFalse(cr.canEnroll(s21));
        }
	}
}
