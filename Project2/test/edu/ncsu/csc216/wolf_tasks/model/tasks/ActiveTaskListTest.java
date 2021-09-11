package edu.ncsu.csc216.wolf_tasks.model.tasks;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests ActiveTaskList
 * @author magolden
 * @author owenloker
 */
public class ActiveTaskListTest {
	
	/** Tests ActiveTaskList Constructor
	 * 
	 */
	@Test
	public void testActiveTaskList() {
		
		AbstractTaskList list = new ActiveTaskList();
		assertEquals("Active Tasks", list.getTaskListName());
		assertEquals(0, list.getCompletedCount());
		
		try {
			list.setTaskListName("Name");
		}
		catch(IllegalArgumentException e) {
			assertEquals("The Active Tasks list may not be edited.", e.getMessage());
		}
		
		Task task = new Task("CSC", "HW", false, false);
		
		try {
			list.addTask(task);
		}
		catch(IllegalArgumentException e) {
			assertEquals("Cannot add task to Active Tasks.", e.getMessage());
		}
	}
	
	/** Tests getTasksAsArray method
	 * 
	 */
	@Test
	public void testGetTasksAsArray() {
		
		AbstractTaskList list = new ActiveTaskList();
		assertEquals("Active Tasks", list.getTaskListName());
		assertEquals(0, list.getCompletedCount());
		
		Task task = new Task("CSC", "HW", false, true);
		task.addTaskList(list);
		list.addTask(task);
		assertEquals("Active Tasks", task.getTaskListName());
		assertEquals(1, list.getTasks().size());
		
		task = new Task("Math", "Studying", true, true);
		task.addTaskList(list);
		list.addTask(task);
		assertEquals(2, list.getTasks().size());
		
		task = new Task("English", "Tutoring", false, true);
		task.addTaskList(list);
		list.addTask(task);
		assertEquals(3, list.getTasks().size());
		
		String[][] taskArr = list.getTasksAsArray();
		assertEquals("Active Tasks", taskArr[0][0]);
		assertEquals("CSC", taskArr[0][1]);
		assertEquals("Active Tasks", taskArr[1][0]);
		assertEquals("Math", taskArr[1][1]);
		assertEquals("Active Tasks", taskArr[2][0]);
		assertEquals("English", taskArr[2][1]);
	}
	
	/** Tests clearTasks method
	 * 
	 */
	@Test
	public void testClearTasks() {
		ActiveTaskList list = new ActiveTaskList();
		assertEquals("Active Tasks", list.getTaskListName());
		assertEquals(0, list.getCompletedCount());
		
		Task task = new Task("CSC", "HW", false, true);
		task.addTaskList(list);
		list.addTask(task);
		assertEquals("Active Tasks", task.getTaskListName());
		assertEquals(1, list.getTasks().size());
		
		task = new Task("Math", "Studying", true, true);
		task.addTaskList(list);
		list.addTask(task);
		assertEquals(2, list.getTasks().size());
		
		task = new Task("English", "Tutoring", false, true);
		task.addTaskList(list);
		list.addTask(task);
		assertEquals(3, list.getTasks().size());
		
		list.clearTasks();
		assertEquals(0, list.getTasks().size());
	}
}
