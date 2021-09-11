package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Custom Recursive LinkedList implementation for Faculty Schedule functionality
 * @author Nick Bleuzen
 * @author Lennox Latta
 * @param <E> type parameter for this class
 */
public class LinkedListRecursive<E> {
	
	/** First listnode in the list */
	public ListNode front; 
	
	/** Size of list */
	private int size;

	/**
	 * Linked List Recursive Constructor
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * Returns true is the list is empty.
	 * @return true if list empty
	 *         false is list contains elements
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Gets the size of the list
	 * @return size of list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Adds an element to the list. If the element is already in the list, an 
	 * IllegalArgumentException is thrown. If the element is null, a NullPointerException 
	 * is thrown. 
	 * @param element element to add
	 * @return true if the element was added
	 */
	public boolean add(E element) {
		if (contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		if (isEmpty()) {
			front = new ListNode(element, null);
			size++;
			return true;
		}
		
		return front.add(element);
	}
	
	/**
	 * Adds an element to the list at a specified index. If the element is already in the list, an 
	 * IllegalArgumentException is thrown. If the element is null, a NullPointerException 
	 * is thrown. If the index if out of bounds, an IndexOutOfBoundsException is thrown. 
	 * @param element element to add
	 * @param idx index to add at
	 * @return true if the element was added
	 */
	public boolean add(int idx, E element) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		else if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		else if (contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		else if (idx == 0) {
			if (isEmpty()) {
				return add(element);
			}
			front = new ListNode(element, front);
			size++;
			return true;
		}
		else {
			return front.add(idx, element);
		}
	}
	
	/**
	 * Returns the element at a specified index. If the index is out of bounds, an
	 * IndexOutOfBoundsException is thrown. 
	 * @param idx index of element
	 * @return element at index
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		else {
			return front.get(idx);
		}
	}
	
	/**
	 * Removes an element from the list. If the element is not in the list, a NullPointerException.
	 * is thrown. If the element is null, a NullPointerException is thrown. If the element is not
	 * in the list, an IllegalArgumentException is thrown. 
	 * @param element element to remove
	 * @return removed element 
	 */
	public boolean remove(E element) {
		if (element == null) {
//			throw new NullPointerException("Cannot remove null element.");
			return false;
		}
		else if (isEmpty()) {
//			throw new IndexOutOfBoundsException("Cannot remove from an empty list.");
			return false;
		}
		else if (!contains(element)) {
			throw new IllegalArgumentException("Element not in the list");
		}
 		else if (front.data == element) {
			remove(0);
			return true;
		}
		else {
			return front.remove(element);
		}
	}
	
	/**
	 * Removes the element at a specified index. If the index is out of bounds, an 
	 * IndexOutOfBounds exception is thrown. 
	 * @param idx index of element to remove
	 * @return removed element
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size || isEmpty()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		else if (idx == 0) {
			E removed = front.data;
			front = front.next;
			size--;
			return removed;
		}
		else {
			return front.remove(idx);
		}
	}
	
	/**
	 * Sets an element at a specified index. If the index is out of bounds, an 
	 * IndexOutOfBounds exception is thrown. If the element is already in the list,
	 * an IllegalArgumentException is thrown. 
	 * @param idx index to set
	 * @param element element to set
	 * @return element previously at the index
	 */
	public E set(int idx, E element) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		else if (contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		else if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		else if (idx == 0) {
			E removed = remove(idx);
			add(idx, element);
			return removed;
		}
		else {
			return front.set(idx, element);
		}
	}
	
	/**
	 * Returns true if the element is in the list.
	 * @param element element to search for
	 * @return true if element is found
	 *         false if element is not found
	 */
	public boolean contains(E element) {
		if (isEmpty()) {
			return false;
		}
		else {
			return front.contains(element);
		}
	}
	
	/**
	 * ListNode class for the RecursiveLinkedList
	 * @author Nick Bleuzen
	 */
	private class ListNode {
		
		/** Element contained within a listnode */
		public E data;
		
		/** Reference to the next listnode in the list */
		public ListNode next;
		
		/**
		 * ListNode Constructor
		 * @param data element that the listnode holds
		 * @param next Reference to the next listnode in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;

		}
		
		private boolean contains(E element) {
			if (element.equals(data)) {
				return true;
			}
			if (next == null) {
				return false;
			}
			return next.contains(element);
		}
		
		private boolean add(E element) {
			if (next == null) {
				next = new ListNode(element, null);
				size++;
				return true;
			}
			return next.add(element);
		}
		
		private boolean add(int idx, E element) {
			if (idx == 1) {
				next = new ListNode(element, next);
				size++;
				return true;
			}
			else {
				return next.add(idx - 1, element);
			}
		}
		
		private E get(int idx) {
			if (idx == 0) {
				return data;
			}
			else {
				return next.get(idx - 1);
			}
		}
		
		private E remove(int idx) {
			if (idx == 1) {
				E removed = next.data;
				next = next.next;
				size--;
				return removed;
			}
			else { 
				return next.remove(idx - 1);
			}
		}
		
		private boolean remove(E element) {
			if (next.data == element) {
				next = next.next;
				size--;
				return true;
			}
			else {
				return next.remove(element);
			}
		}
		
		private E set(int idx, E element) {
			E removed = remove(idx);
			add(idx, element);
			return removed;
		}
	}
		
}
