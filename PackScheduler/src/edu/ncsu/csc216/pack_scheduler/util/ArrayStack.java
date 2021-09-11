package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Creates a Array based Stack data structure
 * @author magolden
 * @param <E> the type parameter for this class.
 */
public class ArrayStack<E> implements Stack<E> {

	/** Internal actual list */
	private ArrayList<E> list;
	/** Capacity of Stack */
	private int capacity;
	
	/**
	 * ArrayStack Constructor
	 * @param capacity capacity of stack
	 */
	public ArrayStack(int capacity) {
		this.list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Adds an element to the top of the stack
	 * @param element element to push
	 * @throws IllegalArgumentException if the list is at capacity
	 */
	@Override
	public void push(E element) {
		if (list.size() == this.capacity) {
			throw new IllegalArgumentException();
		}
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
		
		this.capacity = capacity;
	}

}
