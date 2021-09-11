/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.notebook;

import java.io.File;

import edu.ncsu.csc216.wolf_tasks.model.io.NotebookWriter;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * Represents a Notebook object in the WolfTasks system.
 * A Notebook is a collection of TaskLists
 * It has a name, a field for a SortedList of all TaskLists, a field for a list of all Tasks marked active, and
 * a field for a current TaskList
 * @author owenloker
 * @author magolden
 */
public class Notebook {
	
	/** SortedList of all TaskLists in the Notebook */
	private ISortedList<TaskList> taskLists;
	
	/** List of all tasks marked as active */
	private ActiveTaskList activeTaskList;
	
	/** The current TaskList */
	private AbstractTaskList currentTaskList;
	
	/** Name of Notebook */
	private String notebookName;
	
	/** Holds a boolean value for whether or not this notebook has been altered */
	private boolean isChanged;
	
	/**
	 * Notebook Constructor. Sets all fields and sets isChanged to true
	 * @param notebookName name of Notebook
	 */
	public Notebook(String notebookName) {
		setNotebookName(notebookName);
		this.taskLists = new SortedList<TaskList>();
		this.activeTaskList = new ActiveTaskList();
		this.currentTaskList = this.activeTaskList;
		setChanged(true);
	}
	
	/**
	 * Utilizes NotebookWriter to save a notebook to a given file
	 * @param notebookFile name of file to save to
	 */
	public void saveNotebook(File notebookFile) {
		NotebookWriter.writeNotebookFile(notebookFile, getNotebookName(), this.taskLists);
		setChanged(false);
	}

	/**
	 * Gets the current TaskList
	 * @return the currentTaskList
	 */
	public AbstractTaskList getCurrentTaskList() {
		return this.currentTaskList;
	}

	/**
	 * Sets the current TaskList
	 * @param taskListName name of the currentTaskList to set
	 */
	public void setCurrentTaskList(String taskListName) {
		for (int i = 0; i < this.taskLists.size(); i++) {
			TaskList list = this.taskLists.get(i);
			if (list.getTaskListName().equals(taskListName)) {
				this.currentTaskList = list;
			}
		}
		
		if (!getCurrentTaskList().getTaskListName().equals(taskListName)) {
			getActiveTaskList();
			this.currentTaskList = this.activeTaskList;
		}
	}

	/**
	 * Gets the Notebook name
	 * @return the notebookName
	 */
	public String getNotebookName() {
		return this.notebookName;
	}

	/**
	 * Sets the Notebook name
	 * @param notebookName the notebookName to set
	 * @throws IllegalArgumentException with the message "Invalid name." if the notebook name is null, empty, or the same as the active task list name
	 */
	private void setNotebookName(String notebookName) {
		if (notebookName == null || "".equals(notebookName) || "Active Tasks".equals(notebookName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.notebookName = notebookName;
	}

	/**
	 * Gets the current value of isChanged
	 * @return the isChanged
	 */
	public boolean isChanged() {
		return this.isChanged;
	}

	/**
	 * Sets the value of isChanged
	 * @param isChanged the isChanged to set
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	/**
	 * Refreshes the ActiveTaskList
	 */
	private void getActiveTaskList() { 
		this.activeTaskList.clearTasks();
		
		for (int i = 0; i < this.taskLists.size(); i++) {
			TaskList list = this.taskLists.get(i);
			for (int j = 0; j < list.getTasks().size(); j++) {
				if(list.getTasks().get(j).isActive()) {
					Task temp = list.getTasks().get(j);
					this.activeTaskList.addTask(temp);
				}
			}
		}
	}
	
	/**
	 * Adds a TaskList to the Notebook
	 * @param taskList to be added
	 * @throws IllegalArgumentException with the message "Invalid name." if the tasklist name is the name of the active task list or a duplicate
	 */
	public void addTaskList(TaskList taskList) {
		if (taskList.getTaskListName().equals(ActiveTaskList.ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		for (int i = 0; i < this.taskLists.size(); i++) {
			if (taskList.getTaskListName().equals(this.taskLists.get(i).getTaskListName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		this.taskLists.add(taskList);
		setCurrentTaskList(taskList.getTaskListName());
		setChanged(true);
		getActiveTaskList();
	}
	
	/**
	 * Gets the names of all the TaskLists as a String array
	 * Active tasks is set to the spot in index 0. the rest of the TaskLists are listed in their sorted order
	 * @return names String array of TaskList names
	 */
	public String[] getTaskListsNames() {
		String[] names = new String[this.taskLists.size() + 1];
		names[0] = this.activeTaskList.getTaskListName();
		for(int i = 0; i < this.taskLists.size(); i++) {
			names[i + 1] = this.taskLists.get(i).getTaskListName();
		}
		
		return names;
	}
	
	/**
	 * Changes the name of a TaskList.
	 * @param taskListName new name of TaskList
	 * @throws IllegalArgumentException if the current task list is an active task list
	 * @throws IllegalArgumentException if the current task list has the name of an active task list
	 * @throws IllegalArgumentException if the task list name is already in the list of tasklists
	 * @throws IllegalArgumentException if the task list name is the name of the current task list
	 */
	public void editTaskList(String taskListName) {
		if(getCurrentTaskList() == this.activeTaskList) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
		
		if(getCurrentTaskList().getTaskListName().equals(ActiveTaskList.ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException();
		}
		
		if(taskListName.equals(ActiveTaskList.ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		for (int i = 0; i < this.taskLists.size(); i++) {
			if (taskListName.equals(this.taskLists.get(i).getTaskListName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		for(int i = 0; i < this.taskLists.size(); i++) {
			if(taskListName.equals(this.taskLists.get(i).getTaskListName())) {
				throw new IllegalArgumentException();
			}
		}
		
		if(getCurrentTaskList().getTaskListName().equals(taskListName)) {
			throw new IllegalArgumentException();
		}
		
		TaskList temp = new TaskList(taskListName, getCurrentTaskList().getCompletedCount()); //changes current task list name; temporary task list with completed count of the current task list, just with new name
		removeTaskList();
		this.taskLists.add(temp); //adds the temp task list with new name to keep order
		setCurrentTaskList(temp.getTaskListName());
		setChanged(true);
	}
	
	/**
	 * Removes a TaskList from the Notebook
	 * @throws IllegalArgumentException if the current task list has the name of an active task list
	 */
	public void removeTaskList() {
		if(getCurrentTaskList().getTaskListName().equals(ActiveTaskList.ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("The Active Tasks list may not be deleted.");
		}
		for(int i = 0; i < this.taskLists.size(); i++) {
			if(this.taskLists.get(i).getTaskListName().equals(getCurrentTaskList().getTaskListName())) {
				this.taskLists.remove(i);
			}
		}
		setCurrentTaskList(this.activeTaskList.getTaskListName());
		setChanged(true);
	}
	
	/**
	 * Adds a Task to a TaskList
	 * @param task to be added
	 */
	public void addTask(Task task) {
		if (getCurrentTaskList() instanceof TaskList) {
			getCurrentTaskList().addTask(task);
			if (task.isActive()) {
				getActiveTaskList();
			}
			setChanged(true);
		}
		
		getActiveTaskList();
	}
	
	/**
	 * Edits a task
	 * @param idx Unique index of the task that will be edited
	 * @param taskName name of task
	 * @param taskDescription task description
	 * @param recurring boolean value true if the task is recurring
	 * @param active boolean value true if the task is active
	 */
	public void editTask(int idx, String taskName, String taskDescription, boolean recurring, boolean active) {
		if(!getCurrentTaskList().getTaskListName().equals(ActiveTaskList.ACTIVE_TASKS_NAME)) {
			for(int i = 0; i < getCurrentTaskList().getTasks().size(); i++) {
				if (i == idx) {
					getCurrentTaskList().getTasks().get(i).setTaskName(taskName);
					getCurrentTaskList().getTasks().get(i).setTaskDescription(taskDescription);
					getCurrentTaskList().getTasks().get(i).setRecurring(recurring);
					getCurrentTaskList().getTasks().get(i).setActive(active);
					if (getCurrentTaskList().getTasks().get(i).isActive()) {
						getActiveTaskList();
					}
				}
			}
			setChanged(true);
		}
	}
}
