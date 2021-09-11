package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests SortedList
 * @author magolden
 * @author owenloker
 */
public class SortedListTest {
	
	/**
	 * Test add
	 */
	@Test
	public void testAdd() {
		SortedList<String> s = new SortedList<String>();
		assertEquals(0, s.size());
		
		s.add("1");
		assertEquals("1", s.get(0));
		assertEquals(1, s.size());
		
		s.add("2");
		assertEquals("2", s.get(1));
		assertEquals(2, s.size());
		
		s.add("3");
		assertEquals("3", s.get(2));
		assertEquals(3, s.size());
		
		s.add("4");
		assertEquals("4", s.get(3));
		assertEquals(4, s.size());
		
		try {
			s.add(null);
		} catch (NullPointerException e) {
			assertEquals("Cannot add null element.", e.getMessage());
			assertEquals(4, s.size());
		}
		
		s.add("5");
		assertEquals("5", s.get(4));
		assertEquals(5, s.size());
		
		s.add("6");
		assertEquals("6", s.get(5));
		assertEquals(6, s.size());
		
		s.add("7");
		assertEquals("7", s.get(6));
		assertEquals(7, s.size());
		
		s.add("8");
		assertEquals("8", s.get(7));
		assertEquals(8, s.size());
		
		s.add("9");
		assertEquals("9", s.get(8));
		assertEquals(9, s.size());
	}
	
	/**
	 * Test Remove
	 */
	@Test
	public void testRemove() {
		SortedList<String> s = new SortedList<String>();
		assertEquals(0, s.size());
		
		s.add("1");
		assertEquals("1", s.get(0));
		assertEquals(1, s.size());
		
		s.add("2");
		assertEquals("2", s.get(1));
		assertEquals(2, s.size());
		
		s.add("3");
		assertEquals("3", s.get(2));
		assertEquals(3, s.size());
		
		try {
			s.remove(4);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}
		
		try {
			s.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}
		
		s.remove(1);
		assertEquals("3", s.get(1));
		assertEquals("1", s.get(0));
		assertEquals(2, s.size());
		
		s.remove(0);
		assertEquals("3", s.get(0));
		assertEquals(1, s.size());
		
		s.remove(0);
		assertEquals(0, s.size());
	}
	
	/**
	 * Test contains
	 */
	@Test
	public void testContains() {
		SortedList<String> s = new SortedList<String>();
		assertEquals(0, s.size());
		
		s.add("1");
		assertEquals("1", s.get(0));
		assertEquals(1, s.size());
		
		s.add("2");
		assertEquals("2", s.get(1));
		assertEquals(2, s.size());
		
		s.add("3");
		assertEquals("3", s.get(2));
		assertEquals(3, s.size());
		
		s.add("4");
		assertEquals("4", s.get(3));
		assertEquals(4, s.size());
		
		assertFalse(s.contains(null));
		assertTrue(s.contains("1"));
		assertTrue(s.contains("2"));
		assertTrue(s.contains("3"));
		assertTrue(s.contains("4"));
	}
	
	/**
	 * Jenkins errors for sort
	 */
	@Test
	public void sortFruit() {
		SortedList<String> s = new SortedList<String>();
		assertEquals(0, s.size());
		
		s.add("banana");
		s.add("apple");
		s.add("orange");
		s.add("eggplant");
		
		assertEquals("apple", s.get(0));
		assertEquals("banana", s.get(1));
		assertEquals("eggplant", s.get(2));
		assertEquals("orange", s.get(3));
	}
	
	/**
	 * Sanity check for sorting
	 */
	@Test
	public void sortLetters() {
		SortedList<String> s = new SortedList<String>();
		assertEquals(0, s.size());
		
		s.add("d");
		s.add("f");
		s.add("a");
		s.add("e");
		s.add("c");
		s.add("b");
		
		assertEquals("a", s.get(0));
		assertEquals("b", s.get(1));
		assertEquals("c", s.get(2));
		assertEquals("d", s.get(3));
		assertEquals("e", s.get(4));
		assertEquals("f", s.get(5));
		
		try {
			s.add("b");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add duplicate element.", e.getMessage());
		}
	}
}
