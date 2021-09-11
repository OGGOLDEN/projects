package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests TaskList
 * @author magolden
 * @author owenloker
 */
public class TaskListTest {
	
	/** Tests getTasksAsArray method
	 * 
	 */
	@Test
	public void testGetTasksAsArray() {
		TaskList list = new TaskList("Name", 0);
		Task t = new Task("CSC", "HW", false, true);
		t.addTaskList(list);
		list.addTask(t);
		Task t1 = new Task("Math", "Studying", true, false);
		t1.addTaskList(list);
		list.addTask(t1);
		Task t2 = new Task("English", "Tutoring", false, false);
		t1.addTaskList(list);
		list.addTask(t2);
		String[][] taskArr = list.getTasksAsArray();
		assertEquals("1", taskArr[0][0]);
		assertEquals("CSC", taskArr[0][1]);
		assertEquals("2", taskArr[1][0]);
		assertEquals("Math", taskArr[1][1]);
		assertEquals("3", taskArr[2][0]);
		assertEquals("English", taskArr[2][1]);
	}
	
	/** Tests compare to method
	 * 
	 */
	@Test
	public void testCompareTo() {
		TaskList listA = new TaskList("Apple", 0);
		TaskList listB = new TaskList("Banana", 0);
		assertEquals(-1, listA.compareTo(listB));
		TaskList listC = new TaskList("Canteloupe", 0);
		assertEquals(1, listC.compareTo(listB));
		assertEquals(0, listC.compareTo(listC));
	}
}
