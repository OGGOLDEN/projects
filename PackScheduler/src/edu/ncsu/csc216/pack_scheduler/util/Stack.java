package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Interface that provides methods for ArrayStack and LinkedStack
 * @author magolden
 * @param <E> type parameter for this class
 */
public interface Stack<E> {

	/**
	 * Adds an element to the top of the stack
	 * @param element element to push
	 * @throws IllegalArgumentException if the list is at capacity
	 */
	void push(E element);
	
	/**
	 * Removes an element from the top of the stack
	 * @return popped element that was removed
	 * @throws EmptyStackException if the list is empty
	 */
	E pop();
	
	/**
	 * Determines if the list is empty or not
	 * @return true is list size is 0, false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * Returns the size of the list
	 * @return list size
	 */
	int size();
	
	/**
	 * Sets the capacity of the list
	 * @param capacity new capacity to set
	 * @throws IllegalArgumentException if the new capacity is less than the size of the list
	 */
	void setCapacity(int capacity);
}
