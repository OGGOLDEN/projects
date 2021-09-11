package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests AbstractTaskList
 * @author magolden
 * @author owenloker
 */
public class AbtractTaskListTest {
	
	//Most of Abstract Test Coverage is tested in other tests like Task
	
	/** Tests AbstractTaskListConstructor
	 * 
	 */
	@Test
	public void testConstructor() {
		AbstractTaskList list;
		
		try {
			list = new TaskList("Name", -1);
		}
		catch(IllegalArgumentException e) {
			assertEquals("Invalid completed count.", e.getMessage());
		}
		
		list = new TaskList("Name", 0);
		list.setTaskListName("New Name");
		
		try {
			list.setTaskListName("");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			list.setTaskListName(null);
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		assertEquals(0, list.getCompletedCount());
		assertEquals("New Name", list.getTaskListName());
	}
	
	/** Tests removeTask method
	 * 
	 */
	@Test
	public void testRemoveTask() {
		AbstractTaskList list = new TaskList("Name", 0);
		assertEquals("Name", list.getTaskListName());
		assertEquals(0, list.getCompletedCount());
		
		Task task = new Task("CSC", "HW", false, true);
		task.addTaskList(list);
		list.addTask(task);
		assertEquals("Name", task.getTaskListName());
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
		assertEquals("1", taskArr[0][0]);
		assertEquals("CSC", taskArr[0][1]);
		assertEquals("2", taskArr[1][0]);
		assertEquals("Math", taskArr[1][1]);
		assertEquals("3", taskArr[2][0]);
		assertEquals("English", taskArr[2][1]);
		
		assertEquals("English", list.removeTask(2).getTaskName());
		assertEquals(2, list.getTasks().size());
		
		taskArr = list.getTasksAsArray();
		
		assertEquals("1", taskArr[0][0]);
		assertEquals("CSC", taskArr[0][1]);
		assertEquals("2", taskArr[1][0]);
		assertEquals("Math", taskArr[1][1]);
		
		assertEquals("CSC", list.removeTask(0).getTaskName());
		assertEquals(1, list.getTasks().size());
		
		taskArr = list.getTasksAsArray();
		
		assertEquals("1", taskArr[0][0]);
		assertEquals("Math", taskArr[0][1]);
	}
}
