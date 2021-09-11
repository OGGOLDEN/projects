package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tests LinkedStack
 * @author magolden
 */
public class LinkedStackTest {

	/**
	 * test ArrayStack
	 */
	@Test
	public void testArrayStack() {
		LinkedStack<String> ls = new LinkedStack<String>(5);
		assertEquals(0, ls.size()); 
		ls.push("1");
		assertEquals(1, ls.size());
		assertFalse(ls.isEmpty());
		ls.push("2");
		assertEquals(2, ls.size());
		ls.push("3");
		assertEquals(3, ls.size());
		
		assertEquals("3", ls.pop());
		assertEquals(2, ls.size());
		assertEquals("2", ls.pop());
		assertEquals(1, ls.size());
		assertEquals("1", ls.pop());
		assertEquals(0, ls.size());
		assertTrue(ls.isEmpty());
	}
	
	/**
	 * Test Exception Paths
	 */
	@Test
	public void testArrayStackExceptions() {
		LinkedStack<String> ls = new LinkedStack<String>(5);
		
		try {
			ls.pop();
		} catch (EmptyStackException e) {
			assertEquals(0, ls.size());
		}
		
		try {
			ls.setCapacity(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(0, ls.size());
		}
		
		ls.push("1");
		ls.push("2");
		ls.push("3");
		ls.push("4");
		ls.push("5");
		
		try {
			ls.setCapacity(4);
		} catch (IllegalArgumentException e) {
			assertEquals(5, ls.size());
		}
		
		try {
			ls.push("6");
		} catch (IllegalArgumentException e) {
			assertEquals(5, ls.size());
		}
	}

}
