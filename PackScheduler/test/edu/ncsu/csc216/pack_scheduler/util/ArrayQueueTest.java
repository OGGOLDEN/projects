package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests for the ArrayQueue Class
 * 
 * @author Lennox Latta
 *
 */
public class ArrayQueueTest {

	/**
	 * tests the valid cases of ArrayQueue Class.
	 */
	@Test
	public void testArrayQueue() {
		ArrayQueue<String> aq = new ArrayQueue<String>(5);
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
	 * tests the invalid cases of the ArrayQueue Class.
	 */
	@Test
	public void testInvalidArrayQueue() {
		ArrayQueue<String> aq = new ArrayQueue<String>(1);
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

		ArrayQueue<String> aq2 = new ArrayQueue<String>(5);
		try {
			aq2 = new ArrayQueue<String>(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(0, aq2.size());
		}

	}

}
