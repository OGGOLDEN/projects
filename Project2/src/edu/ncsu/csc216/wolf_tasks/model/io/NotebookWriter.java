/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;

/**
 * Handles file output for the WolfTasks system. Creates a string representation of a notebook and writes it to a file.
 * @author owenloker
 * @author magolden
 */
public class NotebookWriter {
	
	/**
	 * NotebookWriter Constructor
	 */
	public NotebookWriter() {
		// useless
	}
	
	/**
	 * Writes a notebook to a text file
	 * @param fileName name of file to be created
	 * @param notebookName name of notebook being converted to text
	 * @param taskList SortedList of TasksLists in the Notebook
	 * @throws IllegalArgumentException with the message "Unable to save file." if th file path is invalid
	 */
	public static void writeNotebookFile(File fileName, String notebookName, ISortedList<TaskList> taskList) {
		FileOutputStream fos = null;
		PrintWriter out = null;
		
		try {
			fos = new FileOutputStream(fileName);
            out = new PrintWriter(fos);
            
            out.write("! " + notebookName + "\n");
            for (int i = 0; i < taskList.size(); i++) {
            	out.write("# " + taskList.get(i).getTaskListName() + "," + taskList.get(i).getCompletedCount() + "\n");
            	
            	for (int j = 0; j < taskList.get(i).getTasks().size(); j++) {
            		out.write(taskList.get(i).getTasks().get(j).toString() + "\n");
            	}
            }
            
            out.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
