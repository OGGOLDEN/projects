package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface that provides methods for the LinkedQueue and ArrayQueue Classes.
 * 
 * @author Lennox Latta
 *
 * @param <E> the type parameter for this class.
 */
public interface Queue<E> {

	/**
	 * Adds the element to the back of the Queue.
	 * 
	 * @param element the element being added to the back of the Queue.
	 */
	void enqueue(E element);

	/**
	 * Removes and returns the element at the front of the Queue.
	 * 
	 * @return the item that is at the front of the Queue
	 */
	E dequeue();

	/**
	 * Returns true if the Queue is empty.
	 * 
	 * @return true if the Queue is empty and false if the Queue is not empty.
	 */
	boolean isEmpty();

	/**
	 * Returns the number of elements in the Queue.
	 * 
	 * @return the number of elements in the Queue.
	 */
	int size();

	/**
	 * Sets the Queues capacity
	 * 
	 * @param capacity capacity of the queue
	 */
	void setCapacity(int capacity);

}
