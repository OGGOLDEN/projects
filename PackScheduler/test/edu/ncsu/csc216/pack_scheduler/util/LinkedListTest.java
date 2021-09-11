/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the custom LinkedList
 * @author magolden
 */
public class LinkedListTest {

	/**
	 * Test method for size
	 */
	@Test
	public void testSize() {
		LinkedList<String> linkList = new LinkedList<String>();
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
		LinkedList<String> linkList = new LinkedList<String>();
		assertEquals(0, linkList.size());
	}

	/**
	 * Test method for add
	 */
	@Test
	public void testAddIntE() {
		LinkedList<String> linkList = new LinkedList<String>();
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
	 * Test method for remove
	 */
	@Test
	public void testRemoveInt() {
		LinkedList<String> linkList = new LinkedList<String>();
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
	 * Test method for set
	 */
	@Test
	public void testSetIntE() {
		LinkedList<String> linkList = new LinkedList<String>();
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
		LinkedList<String> linkList = new LinkedList<String>();
		assertEquals(0, linkList.size());
		linkList.add(0, "Hello,");
		linkList.add(1, "how");
		linkList.add(2, "are");
		linkList.add(3, "you?");
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
	 * testing iterator
	 */
	@Test
	public void testIterator() {
		LinkedList<String> linkList = new LinkedList<String>();
//		LinkedListIterator<String> i = linkList.listIterator(0);
		linkList.add(0, "1");
		linkList.add(1, "2");
		try {
			linkList.set(0, null);
			fail();
		} catch (NullPointerException | IllegalStateException e) {
			assertEquals(null, e.getMessage());
		}
		
		assertEquals("1", linkList.listIterator(1).previous());
	}
	
	/**
	 * Tests the set method.
	 */
	@Test
	public void testSet() {
		LinkedList<String> linkList = new LinkedList<String>();
		linkList.add("0");
		linkList.add("1");
		linkList.add("2");
		
		assertEquals("1", linkList.set(1, "set"));
		
	}
}
