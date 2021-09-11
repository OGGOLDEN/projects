/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Handles File input for the WolfTasks system. Processes a string representation of a Notebook
 * and uses it to create a Notebook object by breaking the text down into TaskLists and Tasks
 * @author owenloker
 * @author magolden
 */
public class NotebookReader {
	
	/**
	 * NotebookReader Constructor
	 */
	public NotebookReader() {
		// useless
	}
	
	/**
	 * Processes a Notebook from a text file
	 * NOTE: Notebook is spelled wrong in the method signature as per Dr. Heckman on Piazza.
	 * @param fileName name of file to be processed
	 * @return notebook object built from file
	 * @throws IllegalArgumentException with the message "Unable to load file." if the file cannot be loaded
	 */
	public static Notebook readNodebookFile(File fileName) {
		Scanner scan = null;
		Notebook notebook = null;
		String file = "";
		try {
			scan = new Scanner(new FileInputStream(fileName));
			String firstLine = scan.nextLine();
			if (firstLine.charAt(0) != '!') {
				throw new IllegalArgumentException("Unable to load file.");
			}
			String notebookName = firstLine.substring(2);
			notebook = new Notebook(notebookName);
			while (scan.hasNextLine()) {
				file += scan.nextLine() + "\n";
			}
			
			Scanner line = new Scanner(file);
			Scanner listToken = null;
			listToken = line.useDelimiter("\\r?\\n?[#]");
			
			while (listToken.hasNext()) {
				String listString = listToken.next().trim();
				TaskList taskList = processTaskList(listString);
				if (taskList != null) {
					notebook.addTaskList(taskList);
				}
			}
			line.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		scan.close();
		
		notebook.setCurrentTaskList(ActiveTaskList.ACTIVE_TASKS_NAME);
		
		return notebook;
	}
	
	/**
	 * Processes a string representation of a TaskList and uses it to create a TaskList object
	 * Any invalid TaskLists are skipped
	 * @param taskList string representation of a TaskList
	 * @return TaskList object
	 */
	private static TaskList processTaskList(String taskList) {
		Scanner scan = new Scanner(taskList);
		String firstLine = scan.nextLine();
		Scanner scanFL = new Scanner(firstLine);
		Scanner listToken = scanFL.useDelimiter(",");
		String listName = listToken.next();
		int cc = -1;
		try {
			cc = listToken.nextInt();
		} catch (NoSuchElementException e) {
//			do nothing
		}
		listToken.close();
		
		TaskList tl = null;
		try {
			tl = new TaskList(listName, cc);
		} catch (IllegalArgumentException e) {
			scan.close();
			return tl;
		}
			
		scan.useDelimiter("\\r?\\n?[*]");
		while (scan.hasNextLine()) {
			String taskString = scan.next().trim();
			Task task = processTask(tl, taskString);
			listToken.close();
			if (task != null) {
				try {
					task.addTaskList(tl);
					tl.addTask(task);
					listToken.close();
				} catch (IllegalArgumentException e) {
					listToken.close();
					scan.close();
					return null;
				}
			}
		}
		scan.close();
		listToken.close();
		return tl;
	}
	
	/**
	 * Processes a string representation of a Task and uses it to create a Task object
	 * Any invalid Tasks will be skipped
	 * @param taskList lists of tasks that the current task will be associated with
	 * @param task string representation of a Task
	 * @return Task object
	 */
	private static Task processTask(AbstractTaskList taskList, String task) {
		Task newTask = null;
		Scanner scan = new Scanner(task);
		String firstLine = scan.nextLine();
		if (firstLine.charAt(0) == ',') {
			scan.close();
			return newTask;
		}
		Scanner fl = new Scanner(firstLine);
		Scanner taskToken = fl.useDelimiter(",");
		String name = taskToken.next();
		taskToken.close();
		String description = "";
		while (scan.hasNextLine()) {
			description += scan.nextLine() + "\n";
		}
		boolean recurring = false;
		boolean active = false;
		
		if (firstLine.contains("recurring")) {
			recurring = true;
		}
		
		if (firstLine.contains("active")) {
			active = true;
		}
		
		try {
			newTask = new Task(name, description, recurring, active);
		} catch (IllegalArgumentException e) {
			taskToken.close();
			scan.close();
			return newTask;
		}
		taskToken.close();
		scan.close();
		return newTask;
	}
}
