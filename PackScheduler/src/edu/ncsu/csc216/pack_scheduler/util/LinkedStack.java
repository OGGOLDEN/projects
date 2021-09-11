package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * LinkedList based Stack data structure
 * @author magolden
 *
 * @param <E> the type parameter for this class.
 */
public class LinkedStack<E> implements Stack<E> {

	/** Internal actual list */
	private LinkedAbstractList<E> list;
	
	/**
	 * LinkedStack Constructor
	 * @param capacity capacity of List
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity); 
	}
	
	/**
	 * Adds an element to the top of the stack
	 * @param element element to push
	 * @throws IllegalArgumentException if the list is at capacity
	 */
	@Override
	public void push(E element) {
		list.add(list.size(), element);
	}

	/**
	 * Removes an element from the top of the stack
	 * @return popped element that was removed
	 * @throws EmptyStackException if the list is empty
	 */
	@Override
	public E pop() {
		if (list.size() == 0) {
			throw new EmptyStackException();
		}
		E popped = list.remove(list.size() - 1);
		return popped;
	}

	/**
	 * Determines if the list is empty or not
	 * @return true is list size is 0, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns the size of the list
	 * @return list size
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity of the list
	 * @param capacity new capacity to set
	 * @throws IllegalArgumentException if the new capacity is less than the size of the list
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		}
		
		list.setCapacity(capacity);
	}

}
