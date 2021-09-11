/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * A Task object in the WolfTasks system. A Task has a taskList, a name, a description, and 
 * boolean values for if the task is recurring or active. Implements the Cloneable interface
 * @author owenloker
 * @author magolden
 */
public class Task implements Cloneable {
	
	/** SwapList of TaskLists */
	private ISwapList<AbstractTaskList> taskLists;
	
	/** Name of task */
	private String taskName;
	
	/** task description */
	private String taskDescription;
	
	/** Boolean value for if the task is recurring */
	private boolean recurring;
	
	/** Boolean value for if the task is active */
	private boolean active;
	
	/**
	 * Task Constructor
	 * @param taskName name of task
	 * @param taskDescription task description 
	 * @param recurring boolean value for if the task is recurring
	 * @param active boolean value for if the task is active
	 */
	public Task(String taskName, String taskDescription, boolean recurring, boolean active) {
		setTaskName(taskName);
		setTaskDescription(taskDescription);
		setRecurring(recurring);
		setActive(active);
		this.taskLists = new SwapList<AbstractTaskList>();
	}

	/**
	 * Gets the task name
	 * @return the taskName
	 */
	public String getTaskName() {
		return this.taskName;
	}

	/**
	 * Sets the task name
	 * @param taskName the taskName to set
	 * @throws IllegalArgumentException with the message "Incomplete task information." if the task name is null or empty
	 */
	public void setTaskName(String taskName) {
		if(taskName == null || "".equals(taskName)) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskName = taskName;
	}

	/**
	 * Gets the task description
	 * @return the taskDescpription
	 */
	public String getTaskDescription() {
		return this.taskDescription;
	}

	/**
	 * Sets the task description
	 * @param taskDescription the taskDescpription to set
	 * @throws IllegalArgumentException with the message "Incomplete task information." if the task description is null
	 */
	public void setTaskDescription(String taskDescription) {
		if(taskDescription == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskDescription = taskDescription;
	}

	/**
	 * Gets the value of recurring
	 * @return the recurring
	 */
	public boolean isRecurring() {
		return this.recurring;
	}

	/**
	 * Sets the value of recurring
	 * @param recurring the recurring to set
	 */
	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	/**
	 * Gets the value of active
	 * @return the active
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * Sets the value of active
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Gets the name of the TaskList that this task is in
	 * @return name of task list
	 */
	public String getTaskListName() {
		if (this.taskLists == null || this.taskLists.size() == 0) {
			return "";
		}
		return this.taskLists.get(0).getTaskListName();
	}
	
	/**
	 * Adds a task list
	 * @param taskList tasklist to be added
	 * @throws IllegalArgumentException with the message "Incomplete task information." if the tasklist is null
	 */
	public void addTaskList(AbstractTaskList taskList) {
		if (taskList == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		
		boolean isRegistered = false;
		for (int i = 0; i < this.taskLists.size(); i++) {
			if (this.taskLists.get(i) == taskList) {
				isRegistered = true;
			}
		}
		
		if (!isRegistered) {
			this.taskLists.add(taskList);
		}
	}
	
	/**
	 * Marks a task as complete. If the task is recurring, add a clone to its same task list
	 */
	public void completeTask() {		
		Task clone = null;
		if (this.isRecurring()) {
			try {
				clone = (Task) this.clone();
				
				for (int i = 0; i < this.taskLists.size(); i++) {
					for (int j = 0; j < this.taskLists.get(i).getTasks().size(); j++) {
						if (this.equals(this.taskLists.get(i).getTask(j))) {
							this.taskLists.get(i).addTask(clone);
						}
					}
				}
			} catch (CloneNotSupportedException e) {
				// do nothing
			}
		}
			
		for (int i = 0; i < this.taskLists.size(); i++) {
			for (int j = 0; j < this.taskLists.get(i).getTasks().size(); j++) {
				if (this.equals(this.taskLists.get(i).getTask(j))) {
					this.taskLists.get(i).completeTask(this);
				}
			}
		}
	}
	
	/**
	 * Returns a clone object of a task
	 * @return cloned object
	 * @throws CloneNotSupportedException thrown when clone cannot be performed
	 */
	public Object clone() throws CloneNotSupportedException {
		boolean isRegistered = false;
		
		for (int i = 0; i < this.taskLists.size(); i++) {
			for (int j = 0; j < this.taskLists.get(i).getTasks().size(); j++) {
				if (this.equals(this.taskLists.get(i).getTask(j))) {
					isRegistered = true;
				}
			}
		}
		
		Task clone = null;
		if (!isRegistered) {
			throw new CloneNotSupportedException("Cannot clone.");
		} else {
			clone = new Task(this.getTaskName(), this.getTaskDescription(), this.isRecurring(), this.isActive());
			ISwapList<AbstractTaskList> list = this.taskLists;
			clone.taskLists = list;
		}
		
		return clone;
	}

	/**
	 * Overridden toString for Task
	 * @return string representation of a Task
	 */
	@Override
	public String toString() {
		String task = getTaskName();
		if (isRecurring()) {
			task += ",recurring";
		}
		
		if (isActive()) {
			task += ",active";
		}
		
		return "* " + task + "\n" + getTaskDescription();
	}
	
	
}
