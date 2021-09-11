/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * A TaskList object in the WolfTasks system. A list of task objects. Extends AbstractTaskList. Implements Comparable
 * @author owenloker
 * @author magolden
 */
public class TaskList extends AbstractTaskList implements Comparable<TaskList> {
	
	/**
	 * TaskList Constructor
	 * @param taskListName name of task list
	 * @param completedCount number of completed tasks
	 */
	public TaskList(String taskListName, int completedCount) {
		super(taskListName, completedCount);
	}
	
	/**
	 * Gets the tasklist's tasks as a 2D string array. Holds info for priority number and Task name
	 * @return taskAray 2D array of tasks
	 */
	public String[][] getTasksAsArray() {
		SwapList<Task> tasks = (SwapList<Task>) this.getTasks();
		String[][] taskArray = new String[tasks.size()][2];
		int j;
		String jString;
		for (int i = 0; i < tasks.size(); i++) {
			j = i + 1;
			jString = String.valueOf(j);
			taskArray[i][0] = jString;
			taskArray[i][1] = tasks.get(i).getTaskName();
		}
		return taskArray;
	}
	
	/**
	 * Comparable method uses to compare two TaskLists
	 * -1 if any of the other taskList's fields are lexicographically greater than this Task List, returns 1 if the
	 * reverse is true, and 0 if all three fields are equal between the two TaskList
	 * @param taskList taskList to be compared
	 * @return a integer corresponding to the comparison of tasklists
	 * 
	 */
	public int compareTo(TaskList taskList) {
		if (getTaskListName().compareTo(taskList.getTaskListName()) < 0) {
			return -1;
		} else if (getTaskListName().compareTo(taskList.getTaskListName()) > 0) {
			return 1;
		}
		return 0;
	}
}
