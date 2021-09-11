/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ArrayList class
 * 
 * @author kbmille6
 * @author magolden
 *
 */
public class ArrayListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#size()}.
	 */
	@Test
	public void testSize() {
		ArrayList<String> arrList = new ArrayList<String>();
		assertEquals(0, arrList.size());
		arrList.add(0, "Hello,");
		assertEquals(1, arrList.size());
		arrList.add(1, "how");
		arrList.add(2, "are");
		arrList.add(3, "you?");
		assertEquals(4, arrList.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#ArrayList()}.
	 */
	@Test
	public void testArrayList() {
		ArrayList<String> arrList = new ArrayList<String>();
		assertEquals(0, arrList.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE() {
		ArrayList<String> arrList = new ArrayList<String>();
		assertEquals(0, arrList.size());
		// Test simple add
		arrList.add(0, "Hello,");
		assertEquals(1, arrList.size());
		assertEquals("Hello,", arrList.get(0));
		// Test add to beginning
		arrList.add(0, "how");
		assertEquals(2, arrList.size());
		assertEquals("how", arrList.get(0));
		assertEquals("Hello,", arrList.get(1));
		// Test add to middle
		arrList.add(1, "are");
		assertEquals(3, arrList.size());
		assertEquals("how", arrList.get(0));
		assertEquals("are", arrList.get(1));
		assertEquals("Hello,", arrList.get(2));
		// Test add to end
		arrList.add(3, "you?");
		assertEquals(4, arrList.size());
		assertEquals("how", arrList.get(0));
		assertEquals("are", arrList.get(1));
		assertEquals("Hello,", arrList.get(2));
		assertEquals("you?", arrList.get(3));
		
		// Test add null
		try {
			arrList.add(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, arrList.size());
		}
		
		// Test add duplicate
		try {
			arrList.add(0, "how");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, arrList.size());
		}
		
		// Test negative index
		try {
			arrList.add(-1, "blah");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, arrList.size());
		}
		
		// Test index outside list size
		try {
			arrList.add(6, "blah");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, arrList.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		ArrayList<String> arrList = new ArrayList<String>();
		assertEquals(0, arrList.size());
		arrList.add(0, "Hello,");
		arrList.add(1, "how");
		arrList.add(2, "are");
		arrList.add(3, "you?");
		assertEquals(4, arrList.size());
		
		// Test removing from middle
		assertEquals("are", arrList.remove(2));
		assertEquals(3, arrList.size());
		assertEquals("how", arrList.get(1));
		assertEquals("Hello,", arrList.get(0));
		assertEquals("you?", arrList.get(2));
		// Test removing from end
		assertEquals("you?", arrList.remove(2));
		assertEquals(2, arrList.size());
		assertEquals("how", arrList.get(1));
		assertEquals("Hello,", arrList.get(0));
		// Test removing from beginning
		assertEquals("Hello,", arrList.remove(0));
		assertEquals(1, arrList.size());
		assertEquals("how", arrList.get(0));
		
		// Test invalid index outside of list
		try {
			arrList.remove(9999);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, arrList.size());
		}
		
		// Test negative index
		try {
			arrList.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, arrList.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSetIntE() {
		ArrayList<String> arrList = new ArrayList<String>();
		assertEquals(0, arrList.size());
		arrList.add(0, "Hello,");
		arrList.add(1, "how");
		arrList.add(2, "are");
		arrList.add(3, "you?");
		
		// Test set null
		try {
			arrList.set(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, arrList.size());
		}
		
		// Test set duplicate
		try {
			arrList.set(1, "you?");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, arrList.size());
		}
		
		// Test invalid index outside of list
		try {
			arrList.set(9999, "nope");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, arrList.size());
		}
		
		// Test negative index
		try {
			arrList.set(-1, "nope");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, arrList.size());
		}
		
		// Test actual setting
		arrList.set(2, "aren't");
		assertEquals("aren't", arrList.get(2));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#get(int)}.
	 */
	@Test
	public void testGetInt() {
		ArrayList<String> arrList = new ArrayList<String>();
		assertEquals(0, arrList.size());
		arrList.add(0, "Hello,");
		arrList.add(1, "how");
		arrList.add(2, "are");
		arrList.add(3, "you?");
		// Test invalid index outside of list
		try {
			arrList.get(9999);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, arrList.size());
		}
		
		// Test negative index
		try {
			arrList.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, arrList.size());
		}
		
		assertEquals("Hello,", arrList.get(0));
		assertEquals("how", arrList.get(1));
		assertEquals("are", arrList.get(2));
		assertEquals("you?", arrList.get(3));
	}

}
