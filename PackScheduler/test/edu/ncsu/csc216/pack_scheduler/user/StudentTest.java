package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;


import org.junit.Test;

/**
 * Test file for Student
 * @author tcgelman
 * @author magolden
 * @author khanser
 *
 */
public class StudentTest {
	
	/**
	 * Test for student's constructor method
	 */
	@Test
	public void testStudentConstructor() {
		Student s = null; //Initialize a student reference to null
		
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		    assertEquals(18, s.getMaxCredits());
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		} catch (IllegalArgumentException e) {
		    fail();
		}
		
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		    assertEquals(3, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
		    fail();
		}
		
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 4);
		    assertEquals(4, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
		    fail();
		}
		
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 17);
		    assertEquals(17, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
		    fail();
		}
		
		s = null;
		
		try {
		    s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		try {
		    s = new Student("", "last", "id", "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		try {
		    s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		try {
		    s = new Student("first", "", "id", "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		try {
		    s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		try {
		    s = new Student("first", "last", "", "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		try {
		    s = new Student("first", "last", "id", null, "hashedpassword");    
		    fail();
		} catch (IllegalArgumentException e) {	    
		    assertNull(s);
		}
		
		try {
		    s = new Student("first", "last", "id", "", "hashedpassword");    
		    fail();
		} catch (IllegalArgumentException e) {	    
		    assertNull(s);
		}
		
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", null);	    
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "");	    
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 19);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
	}
	
	/**
	 * test case for first name instance
	 */
	@Test
	public void testSetFirstName() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		try {
		    s.setFirstName("fred");
		    assertEquals("fred", s.getFirstName());
		} catch (IllegalArgumentException e) {
		    fail();
		}
		
		s.setFirstName("first");
		
		try {
		    s.setFirstName(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		}
		
		try {
		    s.setFirstName("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		}
	}
	
	/**
	 * test case for last name instance
	 */
	@Test
	public void testSetLastName() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		try {
		    s.setLastName("fred");
		    assertEquals("fred", s.getLastName());
		} catch (IllegalArgumentException e) {
		    fail();
		}
		
		s.setLastName("last");
		
		try {
		    s.setLastName(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("last", s.getLastName());
		}
		
		try {
		    s.setLastName("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("last", s.getLastName());
		}
	}
	
	/**
	 * test case for password instance
	 */
	@Test
	public void testSetPassword() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		try {
			s.setPassword("password");
			assertEquals("password", s.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		s.setPassword("hashedpassword");
		
		try {
			s.setPassword(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("hashedpassword", s.getPassword());
		}
		
		try {
			s.setPassword("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("hashedpassword", s.getPassword());
		}
	}
	
	/**
	 * test case for email instance
	 */
	@Test
	public void testSetEmail() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		try {
			s.setEmail(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("email@ncsu.edu", s.getEmail());
		}
		
		try {
			s.setEmail("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("email@ncsu.edu", s.getEmail());
		}
		
		try {
			s.setEmail("email.com");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("email@ncsu.edu", s.getEmail());
		}
		
		try {
			s.setEmail("email@com");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("email@ncsu.edu", s.getEmail());
		}
		
		try {
			s.setEmail("email.com@org");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("email@ncsu.edu", s.getEmail());
		}
		
	}
	
	/**
	 * test case for max credits instance
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		try {
			s.setMaxCredits(2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(18, s.getMaxCredits());
		}
		
		try {
			s.setMaxCredits(19);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(18, s.getMaxCredits());
		}
		
		try {
			s.setMaxCredits(5);
			assertEquals(5, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * tests toString method
	 */
	@Test
	public void testToString() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		assertEquals("first,last,id,email@ncsu.edu,hashedpassword,18", s.toString());
	}
	
	/**
	 * tests equals method
	 */
	@Test
    public void testEqualsObject() {
        User c1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        User c2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        User c3 = new Student("first", "haha", "id", "email@ncsu.edu", "hashedpassword");
        User c4 = new Student("first", "last", "ooo", "email@ncsu.edu", "hashedpassword");
        User c5 = new Student("first", "last", "id", "something@fu.nny", "hashedpassword");
        User c6 = new Student("first", "last", "id", "email@ncsu.edu", "oopzies");
        User c7 = new Student("first", "idk", "id", "email@ncsu.edu", "hashedpassword");
        User c8 = new Student("sure", "last", "id", "email@ncsu.edu", "hashedpassword");
        User c9 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);

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
    	User c1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        User c2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        User c3 = new Student("first", "haha", "id", "email@ncsu.edu", "hashedpassword");
        User c4 = new Student("first", "last", "ooo", "email@ncsu.edu", "hashedpassword");
        User c5 = new Student("first", "last", "id", "something@idk.io", "hashedpassword");
        User c6 = new Student("first", "last", "id", "email@ncsu.edu", "oopzies");
        User c7 = new Student("first", "idk", "id", "email@ncsu.edu", "hashedpassword");
        User c8 = new Student("sure", "last", "id", "email@ncsu.edu", "hashedpassword");
        User c9 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
        
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
    
    /**
     * Test for compareTo method in Student.
     */
    @Test
    public void testCompareTo() {
    	Student c1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
    	Student c2 = new Student("first", "haha", "id", "email@ncsu.edu", "hashedpassword");
    	Student c3 = new Student("sure", "last", "id", "email@ncsu.edu", "hashedpassword");
    	Student c4 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
    	
    	assertTrue(c1.compareTo(c2) > 0);
    	assertTrue(c2.compareTo(c1) < 0);
    	assertTrue(c1.compareTo(c3) < 0);
    	assertTrue(c3.compareTo(c1) > 0);
    	assertTrue(c2.compareTo(c3) < 0);
    	assertTrue(c3.compareTo(c2) > 0);
    	assertSame(c1.compareTo(c4), 0);
    }
}
