package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Faculty Class
 * 
 * @author Lennox Latta
 *
 */
public class FacultyTest {

	/**
	 * Tests for the Faculty constructor.
	 */
	@Test
	public void testFacultyConstructor() {
		Faculty s = null; // Initialize a student reference to null

		try {
			s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
			assertEquals(2, s.getMaxCourses());
			assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * test case for max courses instance.
	 */
	@Test
	public void testSetMaxCourses() {
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);

		try {
			s.setMaxCourses(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, s.getMaxCourses());
		}

		try {
			s.setMaxCourses(19);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, s.getMaxCourses());
		}

		try {
			s.setMaxCourses(3);
			assertEquals(3, s.getMaxCourses());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * tests toString method
	 */
	@Test
	public void testToString() {
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		assertEquals("first,last,id,email@ncsu.edu,hashedpassword,2", s.toString());
	}

	/**
	 * tests equals method
	 */
	@Test
	public void testEqualsObject() {
		Faculty c1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c3 = new Faculty("first", "haha", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c4 = new Faculty("first", "last", "ooo", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c5 = new Faculty("first", "last", "id", "something@fu.nny", "hashedpassword", 2);
		Faculty c6 = new Faculty("first", "last", "id", "email@ncsu.edu", "oopzies", 2);
		Faculty c7 = new Faculty("first", "idk", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c8 = new Faculty("sure", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c9 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);

		// Test for equality in both directions
		assertTrue(c1.equals(c2));
		assertTrue(c2.equals(c1));
		assertTrue(c1.equals(c1));

		// Test for each of the fields
		assertFalse(c1.equals(""));
		assertFalse(c1.equals(c3));
		assertFalse(c1.equals(c4));
		assertFalse(c1.equals(c5));
		assertFalse(c1.equals(c6));
		assertFalse(c1.equals(c7));
		assertFalse(c1.equals(c8));
		assertFalse(c1.equals(c9));
	}

	/**
	 * Tests that hashCode works correctly.
	 */
	@Test
	public void testHashCode() {
		Faculty c1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c3 = new Faculty("first", "haha", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c4 = new Faculty("first", "last", "ooo", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c5 = new Faculty("first", "last", "id", "something@fu.nny", "hashedpassword", 2);
		Faculty c6 = new Faculty("first", "last", "id", "email@ncsu.edu", "oopzies", 2);
		Faculty c7 = new Faculty("first", "idk", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c8 = new Faculty("sure", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty c9 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);

		// Test for the same hash code for the same values
		assertEquals(c1.hashCode(), c2.hashCode());

		// Test for each of the fields
		assertNotEquals(c1.hashCode(), c3.hashCode());
		assertNotEquals(c1.hashCode(), c4.hashCode());
		assertNotEquals(c1.hashCode(), c5.hashCode());
		assertNotEquals(c1.hashCode(), c6.hashCode());
		assertNotEquals(c1.hashCode(), c7.hashCode());
		assertNotEquals(c1.hashCode(), c8.hashCode());
		assertNotEquals(c1.hashCode(), c9.hashCode());
	}

}
