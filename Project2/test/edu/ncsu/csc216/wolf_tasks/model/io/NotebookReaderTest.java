package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;

/**
 * Tests NotebookReader
 * @author magolden
 * @author owenloker
 */
public class NotebookReaderTest {

	/**
	 * Tests NotebookReader.
	 */
	@Test
	public void testNotebookReader() {
		File filename = new File("test-files/notebook1.txt");
		Notebook n = NotebookReader.readNodebookFile(filename);
		assertEquals("notebook1.txt", filename.getName());
		assertEquals("School", n.getNotebookName());
		
		// Test that all the correct TaskLists have been loaded
		String[] tlNames = n.getTaskListsNames();
		assertEquals(4, tlNames.length);
		assertEquals("CSC 216", tlNames[1]);
		assertEquals("CSC 226", tlNames[2]);
		assertEquals("Habits", tlNames[3]);
		
		// Test that each task in each TaskList is correct
		n.setCurrentTaskList("CSC 216");
		AbstractTaskList tl = n.getCurrentTaskList();
		assertEquals(35, tl.getCompletedCount());
		ISwapList<Task> t = tl.getTasks();
		assertEquals("Read Project 2 Requirements", t.get(0).getTaskName());
		assertFalse(t.get(0).isRecurring());
		assertFalse(t.get(0).isActive());
		
		assertEquals("Create CRC Cards", t.get(1).getTaskName());
		assertFalse(t.get(1).isRecurring());
		assertTrue(t.get(1).isActive());
		
		assertEquals("Transfer CRC Cards to UMLetino", t.get(2).getTaskName());
		assertFalse(t.get(2).isRecurring());
		assertFalse(t.get(2).isActive());
		
		assertEquals("Download design proposal and rational template", t.get(3).getTaskName());
		assertFalse(t.get(3).isRecurring());
		assertFalse(t.get(3).isActive());
		
		assertEquals("Write design proposal and rationale", t.get(4).getTaskName());
		assertFalse(t.get(4).isRecurring());
		assertFalse(t.get(4).isActive());
		
		assertEquals("Identify 5 system tests", t.get(5).getTaskName());
		assertFalse(t.get(5).isRecurring());
		assertFalse(t.get(5).isActive());
		
		assertEquals("Watch lecture video", t.get(6).getTaskName());
		assertTrue(t.get(6).isRecurring());
		assertTrue(t.get(6).isActive());
		
		assertEquals("Complete exercises", t.get(7).getTaskName());
		assertTrue(t.get(7).isRecurring());
		assertFalse(t.get(7).isActive());
		
		assertEquals("Complete quizzes", t.get(8).getTaskName());
		assertTrue(t.get(8).isRecurring());
		assertFalse(t.get(8).isActive());
		
		assertEquals(9, n.getCurrentTaskList().getTasks().size());
		
		n.setCurrentTaskList("CSC 226");
		tl = n.getCurrentTaskList();
		assertEquals(23, tl.getCompletedCount());
		t = tl.getTasks();
		assertEquals("Homework 7", t.get(0).getTaskName());
		assertFalse(t.get(0).isRecurring());
		assertFalse(t.get(0).isActive());
		
		assertEquals("Homework 8", t.get(1).getTaskName());
		assertFalse(t.get(1).isRecurring());
		assertFalse(t.get(1).isActive());
		
		assertEquals("Homework 9", t.get(2).getTaskName());
		assertFalse(t.get(2).isRecurring());
		assertFalse(t.get(2).isActive());
		
		assertEquals("Homework 10", t.get(3).getTaskName());
		assertFalse(t.get(3).isRecurring());
		assertFalse(t.get(3).isActive());
		
		assertEquals(5, n.getCurrentTaskList().getTasks().size());
		
		n.setCurrentTaskList("Habits");
		tl = n.getCurrentTaskList();
		assertEquals(0, tl.getCompletedCount());
		t = tl.getTasks();
		assertEquals("Exercise", t.get(0).getTaskName());
		assertTrue(t.get(0).isRecurring());
		assertTrue(t.get(0).isActive());
		
		assertEquals("Floss", t.get(1).getTaskName());
		assertTrue(t.get(1).isRecurring());
		assertTrue(t.get(1).isActive());
		
		assertEquals(2, n.getCurrentTaskList().getTasks().size());
		
		n.setCurrentTaskList(ActiveTaskList.ACTIVE_TASKS_NAME);
		assertEquals(5, n.getCurrentTaskList().getTasks().size());
	}
	
	/**
	 * Test notebook7
	 */
	@Test
	public void testNotebook7() {
		File filename = new File("test-files/notebook7.txt");
		Notebook n = NotebookReader.readNodebookFile(filename);
		
		assertEquals(1, n.getCurrentTaskList().getTasks().size());
	}
}
