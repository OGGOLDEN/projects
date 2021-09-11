/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * Parent class of the TaskList object. Has a SwapList of tasks, a name, and a running total of completed tasks
 * @author owenloker
 * @author magolden
 */
public abstract class AbstractTaskList {
	
	/** SwapList of Tasks */
	private ISwapList<Task> tasks;
	
	/** Name of TaskList */
	private String taskListName;
	
	/** Running tally of total tasks completed */
	private int completedCount;
	
	/**
	 * AbstractTaskList Constructor
	 * @param taskListName name of TaskList
	 * @param completedCount tally of complete tasks
	 * @throws IllegalArgumentException with the message "Invalid completed count." if the completed count is negative
	 */
	public AbstractTaskList(String taskListName, int completedCount) {
		if (completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		this.tasks = new SwapList<Task>();
		this.completedCount = completedCount;
		setTaskListName(taskListName);
	}

	/**
	 * Gets the name of the TaskList
	 * @return taskListName name of TaskList
	 */
	public String getTaskListName() {
		return this.taskListName;
	}

	/**
	 * Sets the name of the TaskList
	 * @param taskListName the taskListName to set
	 * @throws IllegalArgumentException with the message "Invalid name." if the task list name is null or empty
	 */
	public void setTaskListName(String taskListName) {
		if (taskListName == null || "".equals(taskListName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.taskListName = taskListName;
	}

	/**
	 * Gets the SwapList of Tasks
	 * @return tasks SwapList of Tasks
	 */
	public ISwapList<Task> getTasks() {
		return this.tasks;
	}

	/**
	 * Gets the completedCount
	 * @return completedCount number of completed tasks
	 */
	public int getCompletedCount() {
		return this.completedCount;
	}
	
	/**
	 * Adds a task to the TaskList
	 * @param task to be added
	 */
	public void addTask(Task task) { 
		this.tasks.add(task);
		task.addTaskList(this);
	}
	
	/**
	 * Removes a task from the TaskList
	 * @param idx index of task to be removed
	 * @return removed task
	 */
	public Task removeTask(int idx) {
		return tasks.remove(idx);
	}
	
	/**
	 * Gets a Task from the TaskList
	 * @param idx index of task
	 * @return task found from list
	 */
	public Task getTask(int idx) {
		return tasks.get(idx);
	}
	
	/**
	 * Marks a task as Complete. (Remove from list and increment completedCount)
	 * @param task task to complete
	 */
	public void completeTask(Task task) {
		for (int i = 0; i < tasks.size(); i++) {
			if (task == tasks.get(i)) {
				tasks.remove(i);
				this.completedCount++;
			}
		}
	}
	
	/**
	 * Gets the tasks as a 2D String array
	 * @return 2D String array of tasks
	 */
	public abstract String[][] getTasksAsArray();
}
