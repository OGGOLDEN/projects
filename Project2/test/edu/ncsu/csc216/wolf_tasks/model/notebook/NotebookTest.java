package edu.ncsu.csc216.wolf_tasks.model.notebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

//
import org.junit.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Tests Notebook
 * @author magolden
 * @author owenloker
 */
public class NotebookTest {
	
	/** Tests Notebook Constructor
	 * 
	 */
	@Test
	public void testNotebookConstructor() {
		Notebook book = new Notebook("Book");
		assertEquals("Book", book.getNotebookName());
		assertEquals("Active Tasks", book.getCurrentTaskList().getTaskListName());
		assertTrue(book.isChanged());
		
		try {
			book = new Notebook(null);
		}
		catch(IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			book = new Notebook("");
		}
		catch(IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			book = new Notebook("Active Tasks");
		}
		catch(IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
	}
	
	/**
	 * Test GetActiveTaskList
	 */
	@Test
	public void testGetActiveTaskList() { //need to figure out of we use book or task/list to add a task or tasklist
		Notebook book = new Notebook("Book");
		TaskList list = new TaskList("List", 0);
		TaskList tst;
		try {
			tst = new TaskList("Active Tasks", 0);
			book.addTaskList(tst);
		}
		catch(IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		Task task = new Task("CSC", "HW", false, true);
		book.addTaskList(list);
		book.addTask(task); 
		assertEquals("List", task.getTaskListName());
		assertEquals(1, list.getTasks().size());
		
		Task task1 = new Task("Math", "Studying", true, true);
//		book.addTaskList(list);
		book.addTask(task1);
		assertEquals(2, list.getTasks().size());
		
		Task task2 = new Task("English", "Tutoring", false, true);
//		book.addTaskList(list);
		book.addTask(task2);
		assertEquals(3, list.getTasks().size());
		
		Task task3 = new Task("Science", "Lab", false, false);
//		book.addTaskList(list);
		book.addTask(task3);
		assertEquals(4, list.getTasks().size());
		
		
	}
	
	/** Tests removeTaskList method
	 * 
	 */
	@Test
	public void testRemoveTaskList() {
		Notebook book = new Notebook("Book");
		TaskList tst = new TaskList("Test", 0);
		
		book.addTaskList(tst);
		assertEquals(2, book.getTaskListsNames().length);
		assertEquals(ActiveTaskList.ACTIVE_TASKS_NAME, book.getTaskListsNames()[0]);
		assertEquals("Test", book.getTaskListsNames()[1]);
		book.removeTaskList();
		
		assertEquals(1, book.getTaskListsNames().length);
		
	}
	
	/** Tests editTaskList method
	 * 
	 */
	@Test
	public void testEditTaskList() {
		Notebook book = new Notebook("Book");
		TaskList list = new TaskList("List", 0);
		book.addTaskList(list);
		book.setCurrentTaskList("List");
		assertEquals("List", book.getCurrentTaskList().getTaskListName());
		book.editTaskList("New List");
		assertEquals("New List", book.getCurrentTaskList().getTaskListName());
	}
	
	/** Tests editTask method
	 * 
	 */
	@Test
	public void testEditTask() {
		Notebook book = new Notebook("Book");
		TaskList list = new TaskList("List", 0);
		
		Task task = new Task("CSC", "HW", false, true);
		book.addTaskList(list);
		book.addTask(task); 
		assertEquals("List", task.getTaskListName());
		assertEquals(1, list.getTasks().size());
		
		Task task1 = new Task("Math", "Studying", true, true);
//		book.addTaskList(list);
		book.addTask(task1);
		assertEquals(2, list.getTasks().size());
		
		assertEquals("List", book.getCurrentTaskList().getTaskListName());
		
		book.editTask(0, "CSC1", "HW1", true, true);
		assertEquals("CSC1", task.getTaskName());
		assertEquals("HW1", task.getTaskDescription());
		assertTrue(task.isRecurring());
		assertTrue(task.isActive());
	}
	
	/**
	 * Test adding duplicate taskLists
	 */
	@Test
	public void testAddDuplicate() {
		Notebook book = new Notebook("Book");
		TaskList list = new TaskList("List", 0);
		TaskList list1 = new TaskList("List", 1);
		TaskList list2 = new TaskList("List", 0);
		
		book.addTaskList(list);
		try {
			book.addTaskList(list1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			book.addTaskList(list2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
	}
}
