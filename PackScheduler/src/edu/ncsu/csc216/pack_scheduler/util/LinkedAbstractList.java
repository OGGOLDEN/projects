package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Reimplementation of LinkedAbstractList from Java
 * @author kbmille6
 * @author magolden
 *
 * @param <E> Type of object for the list to contain
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	
	/** Node at the front of the list */
	private ListNode front;
	/** Current size of the list */
	private int size;
	/** Limit on size of the list */
	private int capacity;
	/** Node at back of list */
	private ListNode back;
	
	/**
	 * Construct a new LinkedAbstractList
	 * @param capacity the maximum size for the list.
	 * @throws IllegalArgumentException if the capacity is less than 0
	 */
	public LinkedAbstractList(int capacity) {
		size = 0;
		if (capacity < size) {
			throw new IllegalArgumentException();
		}
		front = null;
		setCapacity(capacity);
	}
	
	/**
	 * Adds an element to a LinkedList
	 * @param index index to add element at
	 * @param data element to add
	 * @throws NullPointerException if the element is null
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if the element is already in the list
	 */
	@Override
	public void add(int index, E data) {
		int oldSize = size();
		if (data == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (size() == capacity) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < size(); i++) {
			if (data.equals(get(i))) {
				throw new IllegalArgumentException();
			}
		}
		
		ListNode insertionNode = front;
		for (int i = 0; i < index; i++) {
			insertionNode = insertionNode.next;
		}
		
		ListNode newNode = new ListNode(data, insertionNode);
		if (insertionNode == front) {
			back = front;
			front = newNode;
		}
		else {
			ListNode priorNode = front;
			for (int i = 0; i < index - 1; i++) {
				priorNode = priorNode.next;
			}
			priorNode.next = newNode;
			if (index == oldSize) {
				back = newNode;
				if (back == null) {
					// do nothing and gets rid of pmd
					back = newNode;
				}
			} else {
				ListNode current = front;
				ListNode next = current.next;
				while (next != null) {
				    current = next;
				    next = current.next;
				}
				this.back = current;
			}
		}
		
		this.size++;
	}
	
	/**
	 * Removes an element from the list and returns what was removed
	 * @param index index of element to remove
	 * @return removed element that was removed
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode toBeRemoved = front;
		for (int i = 0; i < index; i++) {
			toBeRemoved = toBeRemoved.next;
		}
		E removedData = toBeRemoved.data;
		
		if (index == 0) {
			front = front.next;
		}
		else {
			ListNode priorNode = front;
			for (int i = 0; i < index - 1; i++) {
				priorNode = priorNode.next;
			}
			ListNode afterNode = toBeRemoved.next;
			priorNode.next = afterNode;
		}
		
		if (size == 0) {
			ListNode current = front;
			ListNode next = current.next;
			while (next != null) {
			    current = next;
			    next = current.next;
			}
			this.back = current;
		}

		
		size--;
		return removedData;
	}

	/**
	 * Returns the element at a given index
	 * @param index location of target element
	 * @return element at the index of the list
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode nodeAtIndex = front;
		for (int i = 0; i < index; i++) {
			nodeAtIndex = nodeAtIndex.next;
		}
		return nodeAtIndex.data;
	}
	
	/**
	 * Replaces an index in the list with a given element
	 * @param index index of element to set
	 * @param data element to set
	 * @return original element that was replaced
	 * @throws NullPointerException if the element is null
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if the element is already in the list
	 */
	@Override
	public E set(int index, E data) {
		if (data == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size(); i++) {
			if (data.equals(get(i))) {
				throw new IllegalArgumentException();
			}
		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode nodeToSet = front;
		for (int i = 0; i < index; i++) {
			nodeToSet = nodeToSet.next;
		}
		E oldData = nodeToSet.data;
		nodeToSet.data = data;
		return oldData;
	}

	/**
	 * Returns the size of the list
	 * @return size size of list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Increases the capacity of the List
	 * @param capacity new list capacity
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < this.size()) {
			throw new IllegalArgumentException();
		}
		
		this.capacity = capacity;
	}
	
	/**
	 * Gets the capacity
	 * @return capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * A single listnode in a LinkedList. Has an element and a reference to the next ListNode
	 * @author magolden
	 */
	private class ListNode {
		/** The data that the node holds */
		private E data;
		/** The next node in the list */
		private ListNode next;
		
		/**
		 * ListNode Constructor
		 * @param data element in listnode
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
		}
		
		/**
		 * ListNode Constructor
		 * @param data element in listnode
		 * @param next reference to next listnode
		 */
		public ListNode(E data, ListNode next) {
			this(data);
			this.next = next;
		}
	}

}
