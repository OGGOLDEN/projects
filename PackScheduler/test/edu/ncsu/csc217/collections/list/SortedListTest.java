package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test cases for SortedList
 * @author tcgelman
 * @author magolden
 * @author khanser
 *
 */
public class SortedListTest {
	
	/**
	 * Tests the initial conditions for a new SortedList, as well as if it can be larger than 10 items long
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		list.add("k");
		list.add("j");
		list.add("i");
		list.add("h");
		list.add("g");
		list.add("f");
		list.add("e");
		list.add("d");
		list.add("c");
		list.add("b");
		list.add("a");
		
		assertEquals(11, list.size());
		assertTrue(list.contains("a"));
		assertEquals("k", list.get(10));
		assertEquals("c", list.get(2));
	}
	
	/**
	 * Tests adding items to SortedList
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		
		list.add("donut");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("donut", list.get(2));
		
		list.add("carrot");
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("carrot", list.get(2));
		assertEquals("donut", list.get(3));
		
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		
		try {
			list.add("donut");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
	}
	
	/**
	 * Tests getting a thing from a list
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		list.add("banana");
		list.add("apple");
		list.add("donut");
		list.add("carrot");

		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
	}
	
	/**
	 * Tests removing an item
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 0);
		}
		
		list.add("banana");
		list.add("apple");
		list.add("donut");
		list.add("carrot");
		
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 4);
		}
		
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 4);
		}
		
		assertTrue(list.contains("carrot"));
		list.remove(2);
		assertFalse(list.contains("carrot"));
		
		assertTrue(list.contains("donut"));
		list.remove(2);
		assertFalse(list.contains("donut"));
		
		assertTrue(list.contains("apple"));
		list.remove(0);
		assertFalse(list.contains("apple"));
		
		assertTrue(list.contains("banana"));
		list.remove(0);
		assertFalse(list.contains("banana"));
		
		assertEquals(0, list.size());
	}
	
	/**
	 * Test index of for SortedList
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		assertEquals(-1, list.indexOf("happy"));
		
		list.add("banana");
		list.add("apple");
		list.add("donut");
		list.add("carrot");
		
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("carrot"));
		assertEquals(3, list.indexOf("donut"));
		assertEquals(-1, list.indexOf("happy"));
		
		try {
			list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
	}
	
	/**
	 * Tests clearing the list.
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		list.add("apple");
		list.add("donut");
		list.add("carrot");
		
		assertEquals(4, list.size());
		list.clear();
		assertEquals(0, list.size());
	}
	
	/**
	 * Test isEmpty method for SortedList
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		assertTrue(list.isEmpty());
		
		list.add("banana");
		list.add("apple");
		list.add("donut");
		list.add("carrot");
		
		assertFalse(list.isEmpty());
	}
	
	/**
	 * Test contains method
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		assertTrue(list.isEmpty());
		
		list.add("banana");
		list.add("apple");
		list.add("donut");
		list.add("carrot");
		
		assertTrue(list.contains("carrot"));
		assertFalse(list.contains("dcjyxrfghkvjhb,k.n/l"));
	}
	
	/**
	 * Tests equals method
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("apple");
		list2.add("apple");
		list3.add("banana");

		assertTrue(list1.equals(list2));
		assertFalse(list1.equals(list3));
	}
	
	/**
	 * Tests hashcode method
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("apple");
		list2.add("apple");
		list3.add("banana");
		
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotSame(list1.hashCode(), list3.hashCode());
	}

}
 