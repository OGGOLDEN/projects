package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Tests NotebookWriter
 * @author magolden
 * @author owenloker
 */
public class NotebookWriterTest {
	
	/**
	 * Test NotebookWriter
	 */
	@Test
	public void testNotebookWriter() {
		Notebook n = new Notebook("Test Notebook");
		
		TaskList tl1 = new TaskList("Test TaskList 1", 0);
		TaskList tl2 = new TaskList("Test TaskList 2", 0);
		
		Task t1 = new Task("Test Task 1", "Test description 1", false, true);
		Task t2 = new Task("Test Task 2", "Test description 2", true, true);
		
		n.addTaskList(tl1);
		n.addTask(t1);
		
		n.addTaskList(tl2);
		n.addTask(t2);
		
		File filename = new File("test-files/test_output1.txt");
		n.saveNotebook(filename);
		
		Notebook book = NotebookReader.readNodebookFile(filename);
		
		assertEquals("Test Notebook", book.getNotebookName());
		assertEquals(3, book.getTaskListsNames().length);
		assertEquals("Test TaskList 1", book.getTaskListsNames()[1]);
		assertEquals("Test TaskList 2", book.getTaskListsNames()[2]);
		
		book.setCurrentTaskList("Test TaskList 1");
		assertEquals(1, book.getCurrentTaskList().getTasks().size());
		assertEquals("Test Task 1", book.getCurrentTaskList().getTasks().get(0).getTaskName());
		assertEquals("Test description 1\n", book.getCurrentTaskList().getTasks().get(0).getTaskDescription());
		assertFalse(book.getCurrentTaskList().getTasks().get(0).isRecurring());
		assertTrue(book.getCurrentTaskList().getTasks().get(0).isActive());
		
		book.setCurrentTaskList("Test TaskList 2");
		assertEquals(1, book.getCurrentTaskList().getTasks().size());
		assertEquals("Test Task 2", book.getCurrentTaskList().getTasks().get(0).getTaskName());
		assertEquals("Test description 2\n", book.getCurrentTaskList().getTasks().get(0).getTaskDescription());
		assertTrue(book.getCurrentTaskList().getTasks().get(0).isRecurring());
		assertTrue(book.getCurrentTaskList().getTasks().get(0).isActive());
		
		book.setCurrentTaskList(ActiveTaskList.ACTIVE_TASKS_NAME);
		assertEquals(ActiveTaskList.ACTIVE_TASKS_NAME, book.getCurrentTaskList().getTaskListName());
		assertEquals(2, book.getCurrentTaskList().getTasks().size());
		assertEquals("Test Task 1", book.getCurrentTaskList().getTasks().get(0).getTaskName());
		assertEquals("Test description 1\n", book.getCurrentTaskList().getTasks().get(0).getTaskDescription());
		assertFalse(book.getCurrentTaskList().getTasks().get(0).isRecurring());
		assertTrue(book.getCurrentTaskList().getTasks().get(0).isActive());
		assertEquals("Test Task 2", book.getCurrentTaskList().getTasks().get(1).getTaskName());
		assertEquals("Test description 2\n", book.getCurrentTaskList().getTasks().get(1).getTaskDescription());
		assertTrue(book.getCurrentTaskList().getTasks().get(1).isRecurring());
		assertTrue(book.getCurrentTaskList().getTasks().get(1).isActive());
		
	}
	
	
}
