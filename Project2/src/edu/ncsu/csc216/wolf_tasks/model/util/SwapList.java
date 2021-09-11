/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Array based list that swaps order of its elements
 * @author owenloker
 * @author magolden
 * 
 * @param <E> type parameter for this class
 */
public class SwapList<E> implements ISwapList<E> {
	
	/** Initial capacity of list*/
	private static final int INITIAL_CAPACITY = 10;
	
	/** Generic type array */
	private E[] list;
	
	/** Size of list */
	private int size;
	
	/**
	 * SwapList Constructor
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		this.size = 0;
		this.list = (E[]) new Object[INITIAL_CAPACITY];
	}
	
	/**
	 * Checks that capacity of the list is larger than the length. Increases capacity if needed
	 * @param capacity index to check
	 */
	private void checkCapacity(int capacity) {
		if (size() >= capacity) {
			@SuppressWarnings("unchecked")
			E[] biggerList = (E[]) new Object[capacity * 2];
			for (int i = 0; i < size(); i++) {
				biggerList[i] = get(i);
			}
			
			this.list = biggerList;
		}
	}
	
	/**
	 * Checks the element at a specified index
	 * @param idx index to check
	 * @throws IndexOutOfBoundsException if the index is negative or greater than the size
	 */
	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws IllegalArgumentException if element cannot be added 
	 * @throws NullPointerException if the element to be added is null
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		checkCapacity(this.list.length);
		this.list[this.size++] = element;
	}

	/**
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveUp(int idx) {
		checkIndex(idx);
		
		if (idx != 0) {
			E moveUp = get(idx);
			E moveDown = get(idx - 1);
			this.list[idx - 1] = moveUp;
			this.list[idx] = moveDown;
		}
	}

	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveDown(int idx) {
		checkIndex(idx);
		
		if (idx != this.size - 1) {
			E moveDown = get(idx);
			E moveUp = get(idx + 1);
			this.list[idx] = moveUp;
			this.list[idx + 1] = moveDown;
		}
	}

	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToFront(int idx) {
		checkIndex(idx);
		
		E front = this.list[idx];
		for (int i = idx; i > 0; i--) {
			E temp = get(i);
			this.list[i] = this.list[i - 1];
			this.list[i - 1] = temp;
			
		}
		this.list[0] = front;
	}

	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToBack(int idx) {
		checkIndex(idx);
		
		E back = this.list[idx];
		for (int i = idx; i < this.size - 1; i++) {
			E temp = get(i);
			this.list[i] = this.list[i + 1];
			this.list[i + 1] = temp;
		}
		this.list[this.size - 1] = back;
	}

	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return removed element at given index that was removed
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E remove(int idx) {
		checkIndex(idx);
		
		E removed = this.list[idx];
		this.list[idx] = null;
		for (int i = idx; i < this.size; i++) {
			list[i] = list[i + 1];
		}
		this.list[this.size - 1] = null;
		this.size--;
		return removed;
	}

	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		return this.list[idx];
	}
}
