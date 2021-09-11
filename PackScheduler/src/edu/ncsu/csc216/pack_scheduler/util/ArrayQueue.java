package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * The ArrayList implementation of the Queue Class.
 * 
 * @author Lennox Latta
 *
 * @param <E> the type parameter for this class.
 */
public class ArrayQueue<E> implements Queue<E> {

	/** Internal actual list */
	private ArrayList<E> list;
	/** Capacity of Stack */
	private int capacity;

	/**
	 * Constructor for the ArrayQueue class.
	 * 
	 * @param capacity capacity of the queue
	 */
	public ArrayQueue(int capacity) {
		this.list = new ArrayList<E>();
		setCapacity(capacity);
	}

	/**
	 * Adds the element to the back of the Queue.
	 * 
	 * @param element the element being added to the back of the Queue.
	 * @throws IllegalArgumentException if the list is at capacity
	 */
	@Override
	public void enqueue(E element) {
		if (capacity == list.size()) {
			throw new IllegalArgumentException();
		}
		list.add(list.size(), element);
	}

	/**
	 * Removes and returns the element at the front of the Queue.
	 * 
	 * @return the item that is at the front of the Queue
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E dequeue() {
		if (list.size() == 0) {
			throw new NoSuchElementException();
		}
		E dequeued = list.remove(0);
		return dequeued;
	}

	/**
	 * Returns true if the Queue is empty.
	 * 
	 * @return true if the Queue is empty and false if the Queue is not empty.
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns the number of elements in the Queue.
	 * 
	 * @return the number of elements in the Queue.
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the Queues capacity
	 * 
	 * @param capacity capacity of the queue
	 * @throws IllegalArgumentException if the new capacity is less than the current siz of the list
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
