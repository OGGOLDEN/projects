package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tests ArrayStack
 * @author magolden
 * @param <E> Type of object for the list to contain
 *
 */
public class ArrayStackTest<E> {

	/**
	 * test ArrayStack
	 */
	@Test
	public void testArrayStack() {
		ArrayStack<String> as = new ArrayStack<String>(5);
		assertEquals(0, as.size());
		as.push("1");
		assertEquals(1, as.size());
		assertFalse(as.isEmpty());
		as.push("2");
		assertEquals(2, as.size());
		as.push("3");
		assertEquals(3, as.size());
		
		assertEquals("3", as.pop());
		assertEquals(2, as.size());
		assertEquals("2", as.pop());
		assertEquals(1, as.size());
		assertEquals("1", as.pop());
		assertEquals(0, as.size());
		assertTrue(as.isEmpty());
	}
	
	/**
	 * Test Exception Paths
	 */
	@Test
	public void testArrayStackExceptions() {
		ArrayStack<String> as = new ArrayStack<String>(5);
		
		try {
			as.pop();
		} catch (EmptyStackException e) {
			assertEquals(0, as.size());
		}
		
		try {
			as.setCapacity(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(0, as.size());
		}
		
		as.push("1");
		as.push("2");
		as.push("3");
		as.push("4");
		as.push("5");
		
		try {
			as.setCapacity(4);
		} catch (IllegalArgumentException e) {
			assertEquals(5, as.size());
		}
		
		try {
			as.push("6");
		} catch (IllegalArgumentException e) {
			assertEquals(5, as.size());
		}
	}
}
