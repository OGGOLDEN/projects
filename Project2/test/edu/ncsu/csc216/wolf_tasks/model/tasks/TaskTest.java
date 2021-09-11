	package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests Task
 * @author magolden
 * @author owenloker
 */
public class TaskTest {
	
	/** 
	 * Tests Task constructor
	 */
	@Test
	public void testConstructor() {
		Task t = new Task("HW", "CSC", false, false);
		assertEquals("HW", t.getTaskName());
		assertEquals("CSC", t.getTaskDescription());
		assertFalse(t.isRecurring());
		assertFalse(t.isActive());
		
		try {
			t = new Task(null, "CSC", false, false);
		}
		catch (IllegalArgumentException e) {
			assertEquals("Incomplete task information.", e.getMessage());
		}
		
		try {
			t = new Task("HW", null, false, false);
		}
		catch (IllegalArgumentException e) {
			assertEquals("Incomplete task information.", e.getMessage());
		}
		
		t = new Task("HW", "CSC", true, true);
		assertEquals("HW", t.getTaskName());
		assertEquals("CSC", t.getTaskDescription());
		assertTrue(t.isRecurring());
		assertTrue(t.isActive());
		
		t.setTaskDescription("new");
		assertEquals("new", t.getTaskDescription());
	}
	
	/** 
	 * Tests setTaskListName method
	 */
	@Test
	public void testSetTaskListName() {
		Task t = new Task("HW", "CSC", false, false);
		assertEquals("HW", t.getTaskName());
		AbstractTaskList list = new TaskList("List", 0);
		t.addTaskList(list);
		assertEquals("List", t.getTaskListName());
		t.setTaskName("new");
		assertEquals("new", t.getTaskName());
	}
	
	/**
	 * Test set recurring and set active
	 */
	@Test
	public void testSetPriority() {
		Task t = new Task("HW", "CSC", false, false);
		assertFalse(t.isRecurring());
		assertFalse(t.isActive());
		
		t.setActive(true);
		t.setRecurring(true);
		assertTrue(t.isRecurring());
		assertTrue(t.isActive());
		
		t.setActive(false);
		t.setRecurring(false);
		assertFalse(t.isRecurring());
		assertFalse(t.isActive());
	}
	
	/**
	 * Test toString for Task
	 */
	@Test
	public void testToString() {
		Task t1 = new Task("HW", "CSC", false, false);
		assertEquals("* HW\nCSC", t1.toString());
		
		Task t2 = new Task("HW", "CSC", true, false);
		assertEquals("* HW,recurring\nCSC", t2.toString());
		
		Task t3 = new Task("HW", "CSC", true, true);
		assertEquals("* HW,recurring,active\nCSC", t3.toString());
		
		Task t4 = new Task("HW", "CSC", false, true);
		assertEquals("* HW,active\nCSC", t4.toString());
	}
	
	/** Tests completeTask method
	 * 
	 */
	@Test
	public void testCompleteTask() {
		Task t = new Task("HW", "CSC", false, false);
		AbstractTaskList list = new TaskList("List", 0);
		t.addTaskList(list);
		list.addTask(t);
		assertEquals(0, list.getCompletedCount());
		t.completeTask();
		assertEquals(1, list.getCompletedCount());
		assertEquals(0, list.getTasks().size());
		
		Task t1 = new Task("t1", "description", true, false);
		AbstractTaskList list1 = new TaskList("List1", 0);
		t1.addTaskList(list1);
		list1.addTask(t1);
		assertEquals(0, list1.getCompletedCount());
		t1.completeTask();
		assertEquals(1, list1.getCompletedCount());
		assertEquals(1, list1.getTasks().size());
	}
	
	/**
	 * Test cloning and adding a cloned task
	 */
	@Test
	public void testClone() {
		Task t = new Task("HW", "CSC", true, false);
		AbstractTaskList list = new TaskList("List", 0);
		t.addTaskList(list);
		list.addTask(t);
		try {
			Task clone = (Task) t.clone();
			assertEquals("HW", clone.getTaskName());
			assertEquals("CSC", clone.getTaskDescription());
			assertTrue(clone.isRecurring());
			assertFalse(clone.isActive());
			assertEquals("List", clone.getTaskListName());
		} catch (CloneNotSupportedException e) {
			fail();
		}
	}
}
