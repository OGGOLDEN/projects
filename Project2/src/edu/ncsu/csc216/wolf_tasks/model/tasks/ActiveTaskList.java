/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;

/**
 * Child class of AbstractTaskList. A TaskList of all tasks in the Notebook that are marked as Active
 * @author owenloker
 * @author magolden
 */
public class ActiveTaskList extends AbstractTaskList {
	
	/** Name of the Active Task List */
	public static final String ACTIVE_TASKS_NAME = "Active Tasks";
	
	/**
	 * ActiveTaskList Constructor
	 */
	public ActiveTaskList() {
		super(ACTIVE_TASKS_NAME, 0);
		
	}
	
	/**
	 * Adds a task to the Active TaskList
	 * @throws IllegalArgumentException if the task is null
	 * @throws IllegalArgumentException with the message "Cannot add task to Active Tasks." if the task is not active
	 */
	@Override
	public void addTask(Task task) { 
		if (task == null) {
			throw new IllegalArgumentException();
		}
		if(task.isActive()) {
			super.addTask(task);
		}
		else {
			throw new IllegalArgumentException("Cannot add task to Active Tasks.");
		}
	}
	
	/**
	 * Sets the name of the tasklist
	 * @param taskListName name of tasklist
	 * @throws IllegalArgumentException with the message "The Active Tasks list may not be edited." if the task list name is set to anything other than "Active Tasks"
	 */
	@Override
	public void setTaskListName(String taskListName) {
		if (taskListName.equals(ACTIVE_TASKS_NAME)) {
			super.setTaskListName(taskListName);
		}
		else {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
	}
	
	/**
	 * Gets the tasks in the list as a 2D String array. Holds info for taskList name and task name
	 * @return taskArray 2D string array of tasks
	 */
	public String[][] getTasksAsArray() {
		ISwapList<Task> tasks = this.getTasks();
		String[][] taskArray = new String[tasks.size()][2]; 
		for (int i = 0; i < tasks.size(); i++) {
			taskArray[i][0] = tasks.get(i).getTaskListName();
			taskArray[i][1] = tasks.get(i).getTaskName();
		}
		return taskArray;
	}
	
	/**
	 * Clears the tasks from the list
	 */
	public void clearTasks() {
		ISwapList<Task> tasks = this.getTasks();
		for(int i = tasks.size() - 1; i >= 0; i--) {
			super.removeTask(i);
		}
	}
}
