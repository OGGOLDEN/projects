package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests SwapList
 * @author magolden
 * @author owenloker
 */
public class SwapListTest {

	/**
	 * Test add
	 */
	@Test
	public void testAdd() {
		SwapList<String> s = new SwapList<String>();
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
		
		s.add("10");
		assertEquals("10", s.get(9));
		assertEquals(10, s.size());
		
		// Test that checkCapacity() made the list bigger to accommodate more indexes
		s.add("11");
		assertEquals("11", s.get(10));
		assertEquals(11, s.size());
	}
	
	/**
	 * Test Remove
	 */
	@Test
	public void testRemove() {
		SwapList<String> s = new SwapList<String>();
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
		
		SwapList<String> s1 = new SwapList<String>();
		
		assertEquals(0, s1.size());
		
		s1.add("1");
		assertEquals("1", s1.get(0));
		assertEquals(1, s1.size());
		
		s1.add("2");
		assertEquals("2", s1.get(1));
		assertEquals(2, s1.size());
		
		s1.add("3");
		assertEquals("3", s1.get(2));
		assertEquals(3, s1.size());
		
		s1.remove(2);
		assertEquals("2", s1.get(1));
		assertEquals("1", s1.get(0));
		assertEquals(2, s1.size());
		
		s1.remove(0);
		assertEquals("2", s1.get(0));
		assertEquals(1, s1.size());
		
		s1.remove(0);
		assertEquals(0, s1.size());
	}
	
	/**
	 * Test MoveUp
	 */
	@Test
	public void testMoveUp() {
		SwapList<String> s = new SwapList<String>();
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
		
		s.moveUp(2);
		
		assertEquals("1", s.get(0));
		assertEquals("3", s.get(1));
		assertEquals("2", s.get(2));
		assertEquals("4", s.get(3));
		
		s.moveUp(1);
		
		assertEquals("3", s.get(0));
		assertEquals("1", s.get(1));
		assertEquals("2", s.get(2));
		assertEquals("4", s.get(3));
		
		s.moveUp(3);
		
		assertEquals("3", s.get(0));
		assertEquals("1", s.get(1));
		assertEquals("4", s.get(2));
		assertEquals("2", s.get(3));
	}
	
	/**
	 * Test MoveDown
	 */
	@Test
	public void moveDown() {
		SwapList<String> s = new SwapList<String>();
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
		
		s.moveDown(0);
		
		assertEquals("2", s.get(0));
		assertEquals("1", s.get(1));
		assertEquals("3", s.get(2));
		assertEquals("4", s.get(3));
		
		s.moveDown(1);
		
		assertEquals("2", s.get(0));
		assertEquals("3", s.get(1));
		assertEquals("1", s.get(2));
		assertEquals("4", s.get(3));
		
		s.moveDown(2);
		
		assertEquals("2", s.get(0));
		assertEquals("3", s.get(1));
		assertEquals("4", s.get(2));
		assertEquals("1", s.get(3));
	}
	
	/**
	 * test MoveToFront
	 */
	@Test
	public void testMoveToFront() {
		SwapList<String> s = new SwapList<String>();
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
		
		s.moveToFront(3);
		
		assertEquals("4", s.get(0));
		assertEquals("1", s.get(1));
		assertEquals("2", s.get(2));
		assertEquals("3", s.get(3));
		
		s.moveToFront(2);
		
		assertEquals("2", s.get(0));
		assertEquals("4", s.get(1));
		assertEquals("1", s.get(2));
		assertEquals("3", s.get(3));
		
		s.moveToFront(1);
		
		assertEquals("4", s.get(0));
		assertEquals("2", s.get(1));
		assertEquals("1", s.get(2));
		assertEquals("3", s.get(3));
		
		s.moveToFront(0);
		
		assertEquals("4", s.get(0));
		assertEquals("2", s.get(1));
		assertEquals("1", s.get(2));
		assertEquals("3", s.get(3));
	}
	
	/**
	 * Test MoveToBack
	 */
	@Test
	public void testMoveToBack() {
		SwapList<String> s = new SwapList<String>();
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
		
		s.moveToBack(0);
		
		assertEquals("2", s.get(0));
		assertEquals("3", s.get(1));
		assertEquals("4", s.get(2));
		assertEquals("1", s.get(3));
		
		s.moveToBack(1);
		
		assertEquals("2", s.get(0));
		assertEquals("4", s.get(1));
		assertEquals("1", s.get(2));
		assertEquals("3", s.get(3));
		
		s.moveToBack(2);
		
		assertEquals("2", s.get(0));
		assertEquals("4", s.get(1));
		assertEquals("3", s.get(2));
		assertEquals("1", s.get(3));
		
		s.moveToBack(3);
		
		assertEquals("2", s.get(0));
		assertEquals("4", s.get(1));
		assertEquals("3", s.get(2));
		assertEquals("1", s.get(3));
	}
}
