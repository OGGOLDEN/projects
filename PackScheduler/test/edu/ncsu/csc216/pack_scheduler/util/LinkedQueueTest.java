package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests for the LinkedQueue Class.
 * 
 * @author Lennox Latta
 *
 */
public class LinkedQueueTest {

	/**
	 * tests the valid cases of the LinkedQueue Class.
	 */
	@Test
	public void testLinkedQueue() {
		LinkedQueue<String> aq = new LinkedQueue<String>(5);
		assertTrue(aq.isEmpty());
		aq.enqueue("1");
		assertFalse(aq.isEmpty());
		assertEquals(1, aq.size());
		aq.enqueue("2");
		aq.enqueue("3");
		assertEquals(3, aq.size());
		aq.dequeue();
		assertEquals(2, aq.size());
	}

	/**
	 * tests the invalid cases of the LinkedQueue Classes.
	 */
	@Test
	public void testInvalidLinkedQueue() {
		LinkedQueue<String> aq = new LinkedQueue<String>(1);
		aq.enqueue("1");
		try {
			aq.enqueue("2");
		} catch (IllegalArgumentException e) {
			assertEquals(1, aq.size());
		}

		aq.dequeue();
		try {
			aq.dequeue();
		} catch (NoSuchElementException e) {
			assertEquals(0, aq.size());
		}

		LinkedQueue<String> aq2 = new LinkedQueue<String>(5);
		try {
			aq2 = new LinkedQueue<String>(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(0, aq2.size());
		}

	}
	
	/**
	 * Test Set Capacity
	 */
	@Test
	public void testSetCapacity() {
		LinkedQueue<String> arrList = new LinkedQueue<String>(5);
		assertEquals(0, arrList.size());
		arrList.enqueue("Hello,");
		arrList.enqueue("how");
		arrList.enqueue("are");
		arrList.enqueue("you?");
		assertEquals(4, arrList.size());
		arrList.setCapacity(10);
		try {
			arrList.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, arrList.size());
		}
		
		try {
			arrList.setCapacity(3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, arrList.size());
		}
	}

}
