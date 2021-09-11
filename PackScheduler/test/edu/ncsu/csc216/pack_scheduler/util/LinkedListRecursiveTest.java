package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the LinkedListRecursive Class
 * @author Lennox Latta
 *
 */
public class LinkedListRecursiveTest {

	/**
	 * Test method for size
	 */
	@Test
	public void testSize() {
		LinkedListRecursive<String> linkList = new LinkedListRecursive<String>();
		assertEquals(0, linkList.size());
		linkList.add(0, "Hello,");
		assertEquals(1, linkList.size());
		linkList.add(1, "how");
		linkList.add(2, "are");
		linkList.add(3, "you?");
		assertEquals(4, linkList.size());
	}

	/**
	 * Test method for LinkedLst constructor
	 */
	@Test
	public void testLinkedList() {
		LinkedListRecursive<String> linkList = new LinkedListRecursive<String>();
		assertEquals(0, linkList.size());
	}

	/**
	 * Test method for add
	 */
	@Test
	public void testAdd() {
		LinkedListRecursive<String> linkList = new LinkedListRecursive<String>();
		assertEquals(0, linkList.size());

		// Test simple add
		linkList.add(0, "Hello,");
		assertEquals(1, linkList.size());
		assertEquals("Hello,", linkList.get(0));
		// Test add to beginning
		linkList.add(0, "how");
		assertEquals(2, linkList.size());
		assertEquals("how", linkList.get(0));
		assertEquals("Hello,", linkList.get(1));
		// Test add to middle
		linkList.add(1, "are");
		assertEquals(3, linkList.size());
		assertEquals("how", linkList.get(0));
		assertEquals("are", linkList.get(1));
		assertEquals("Hello,", linkList.get(2));
		// Test add to end
		linkList.add(3, "you?");
		assertEquals(4, linkList.size());
		assertEquals("how", linkList.get(0));
		assertEquals("are", linkList.get(1));
		assertEquals("Hello,", linkList.get(2));
		assertEquals("you?", linkList.get(3));
		
		// Test add null
		try {
			linkList.add(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, linkList.size());
		}
		
		// Test add duplicate
		try {
			linkList.add(0, "how");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, linkList.size());
		}
		
		// Test negative index
		try {
			linkList.add(-1, "blah");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, linkList.size());
		}
		
		// Test index outside list size
		try {
			linkList.add(6, "blah");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, linkList.size());
		}
	}

	/**
	 * Test method for remove(idx)
	 */
	@Test
	public void testRemoveInt() {
		LinkedListRecursive<String> linkList = new LinkedListRecursive<String>();
		assertEquals(0, linkList.size());
		linkList.add(0, "Hello,");
		linkList.add(1, "how");
		linkList.add(2, "are");
		linkList.add(3, "you?");
		assertEquals(4, linkList.size());
		
		// Test removing from middle
		assertEquals("are", linkList.remove(2));
		assertEquals(3, linkList.size());
		assertEquals("how", linkList.get(1));
		assertEquals("Hello,", linkList.get(0));
		assertEquals("you?", linkList.get(2));
		// Test removing from end
		assertEquals("you?", linkList.remove(2));
		assertEquals(2, linkList.size());
		assertEquals("how", linkList.get(1));
		assertEquals("Hello,", linkList.get(0));
		// Test removing from beginning
		assertEquals("Hello,", linkList.remove(0));
		assertEquals(1, linkList.size());
		assertEquals("how", linkList.get(0));
		
		// Test invalid index outside of list
		try {
			linkList.remove(9999);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, linkList.size());
		}
		
		// Test negative index
		try {
			linkList.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, linkList.size());
		}
	}
	
	/**
	 * Test method for remove(element)
	 */
	@Test
	public void testRemoveE() {
		LinkedListRecursive<String> linkList = new LinkedListRecursive<String>();
		assertEquals(0, linkList.size());
		linkList.add(0, "Hello,");
		linkList.add(1, "how");
		linkList.add(2, "are");
		linkList.add(3, "you?");
		assertEquals(4, linkList.size());
		
		// Test removing from middle
		assertTrue(linkList.remove("are"));
		assertEquals(3, linkList.size());
		assertEquals("how", linkList.get(1));
		assertEquals("Hello,", linkList.get(0));
		assertEquals("you?", linkList.get(2));
		// Test removing from end
		assertTrue(linkList.remove("you?"));
		assertEquals(2, linkList.size());
		assertEquals("how", linkList.get(1));
		assertEquals("Hello,", linkList.get(0));
		// Test removing from beginning
		assertTrue(linkList.remove("Hello,"));
		assertEquals(1, linkList.size());
		assertEquals("how", linkList.get(0));
		// Test element not in the list
		try {
			linkList.remove("element");
		} catch (IllegalArgumentException e) {
			assertEquals(1, linkList.size());
		}
		// Test removing last element
		assertTrue(linkList.remove("how"));
		assertEquals(0, linkList.size());
		
		// Test empty list
		try {
			linkList.remove("element");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, linkList.size());
		}
		
		// Test negative index
		try {
			linkList.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, linkList.size());
		}
		
		
	}

	/**
	 * Test method for set
	 */
	@Test
	public void testSetIntE() {
		LinkedListRecursive<String> linkList = new LinkedListRecursive<String>();
		assertEquals(0, linkList.size());
		linkList.add(0, "Hello,");
		linkList.add(1, "how");
		linkList.add(2, "are");
		linkList.add(3, "you?");
		
		// Test set null
		try {
			linkList.set(0, null);
			fail();
		} catch (IllegalStateException | NullPointerException e) {
			assertEquals(4, linkList.size());
		}
		
		// Test set duplicate
		try {
			linkList.set(1, "you?");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, linkList.size());
		}
		
		// Test invalid index outside of list
		try {
			linkList.set(9999, "nope");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, linkList.size());
		}
		
		// Test negative index
		try {
			linkList.set(-1, "nope");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, linkList.size());
		}
		
		// Test actual setting
		linkList.set(2, "set");
		assertEquals("set", linkList.get(2));
	}

	/**
	 * Test method for get
	 */
	@Test
	public void testGetInt() {
		LinkedListRecursive<String> linkList = new LinkedListRecursive<String>();
		assertEquals(0, linkList.size());
		linkList.add("Hello,");
		linkList.add("how");
		linkList.add("are");
		linkList.add("you?");
		// Test invalid index outside of list
		try {
			linkList.get(9999);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, linkList.size());
		}
		
		// Test negative index
		try {
			linkList.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, linkList.size());
		}
		
		assertEquals("Hello,", linkList.get(0));
		assertEquals("how", linkList.get(1));
		assertEquals("are", linkList.get(2));
		assertEquals("you?", linkList.get(3));
	}
	
	/**
	 * Tests the set method.
	 */
	@Test
	public void testSet() {
		LinkedListRecursive<String> linkList = new LinkedListRecursive<String>();
		linkList.add("0");
		linkList.add("1");
		linkList.add("2");
		
		assertEquals("0", linkList.set(0, "set"));
		assertEquals("1", linkList.set(1, "set1"));
		assertEquals("2", linkList.set(2, "set2"));
	}

}
