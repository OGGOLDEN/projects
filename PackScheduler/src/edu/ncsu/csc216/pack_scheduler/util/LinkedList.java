/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Custom LinkedList implementation for Faculty functionality
 * @author magolden
 * @param <E> type parameter for this class
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	
	/** First listnode in the list */
	public ListNode front;
	
	/** Last listnode in the list */
	public ListNode back;
	
	/** Size of list */
	private int size;
	
	/**
	 * Linked List Constructor
	 */
	public LinkedList() {
		this.front = new ListNode(null);
		this.back = new ListNode(null);
		this.front.next = this.back;
		this.back.prev = this.front;
		this.size = 0;
	}

	/**
	 * Creates a listIterator object that the linked list can use
	 * @param index index to place the listIterator
	 * @return new LinkedListIterator
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}

	/**
	 * Inserts a given element at the specified index
	 * @param index index to add the element at
	 * @param element element to add
	 * @throws IllegalArgumentException if the list already contains the element
	 */
	@Override
	public void add(int index, E element) {
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}
		
		ListIterator<E> iterator = listIterator(index);
		iterator.add(element);
	}

	/**
	 * Replaces the element at the specified index with a given element
	 * @param index index to add the element at
	 * @param element element to add
	 * @return replaced the element that was formerly in the given index
	 * @throws IllegalArgumentException if the list already contains the element
	 */
	@Override
	public E set(int index, E element) {
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}
		
		E replaced = this.get(index);
		ListIterator<E> iterator = listIterator(index);
		iterator.next();
		iterator.set(element);
		return replaced;
	}

	/**
	 * Gets the size of the list
	 * @return this.size size of list
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * ListNode class for the LinkedList
	 * @author magolden
	 */
	private class ListNode {
		
		/** Element contained within a listnode */
		public E data;
		
		/** Reference to the next listnode in the list */
		public ListNode next;
		
		/** Reference to the previous listnode in the list */
		public ListNode prev;
		
		/**
		 * Implicit ListNode constructor
		 * @param data element that the listnode holds
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		
		/**
		 * ListNode Constructor
		 * @param data element that the listnode holds
		 * @param prev Reference to the previous listnode in the list
		 * @param next Reference to the next listnode in the list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this(data);
			this.next = next;
			this.prev = prev;
		}
	}
	
	/**
	 * LinkedListIterator class creates a list iterator for the LinkedList
	 * @author magolden
	 */
	private class LinkedListIterator implements ListIterator<E> {
		
		/** ListNode that comes before the ListIterator */
		public ListNode previous;
		
		/** ListNode that comes after the ListIterator */
		public ListNode next;
		
		/** Index of the previous ListNode */
		public int previousIndex;
		
		/** Index of the next ListNode */
		public int nextIndex;
		
		/** ListNode returned by last call to previous() or next() */
		public ListNode lastRetrieved;
		
		/**
		 * LinkedListIterator Constructor
		 * @param index index that will come after the list iterator
		 */
		public LinkedListIterator(int index) { 
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException();
			}
			
			previous = front;
			next = front.next;
			
			for (int i = 0; i < index; i++) {
				previous = next;
				next = previous.next;
			}
			
			this.previousIndex = index - 1;
			this.nextIndex = index;
			this.lastRetrieved = null;
		}

		/**
		 * Determines if the list contains another listnode with non-null data
		 * @return true if there is another listnode, false otherwise
		 */
		@Override
		public boolean hasNext() {
			return this.next.data != null;
		}

		/**
		 * Returns the data of the ListNode after the listIterator
		 * @return this.next.data data of next listnode
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			this.lastRetrieved = this.next;
			E temp = this.next.data;
			previous = next;
			next = previous.next;
			nextIndex++;
			previousIndex++;
			return temp;
		}

		/**
		 * Determines if the list contains a previous listnode with non-null data
		 * @return true if there is a previous listnode, false otherwise
		 */
		@Override
		public boolean hasPrevious() {
			return this.previous.data != null;
		}

		/**
		 * Returns the data of the ListNode before the listIterator
		 * @return this.previous.data data of previous listnode
		 */
		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			this.lastRetrieved = this.previous;
			E temp = this.previous.data;
			next = previous;
			previous = next.prev;
			nextIndex--;
			previousIndex--;
			return temp;
		}

		/**
		 * Gets index of next listnode
		 * @return this.nextIndex index of next listnode 
		 */
		@Override
		public int nextIndex() {
			return this.nextIndex;
		}

		/**
		 * Gets index of previous listnode
		 * @return this.previousIndex index of previous listnode 
		 */
		@Override
		public int previousIndex() {
			return this.previousIndex;
		}

		/**
		 * Removes the listNode held by lastRetrieved
		 * @throws IllegalStateException if lastRetrieved is null
		 */
		@Override
		public void remove() {
			if (this.lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			if (this.lastRetrieved.equals(this.next)) {
				this.previous.next = this.next.next;
				this.next.next.prev = this.previous;
			} else if (this.lastRetrieved.equals(this.previous)) {
				this.previous.prev.next = this.next;
				this.next.prev = this.previous.prev;
			}
			
//			this.previous.next = this.next;
//			this.next.prev = this.previous;
			this.lastRetrieved = null;
			size--;
		}

		/**
		 * Sets the value of of the lastRetrieved listnode with the given element
		 * @param e element to set
		 * @throws IllegalArgumentException if lastRetrieved is null
		 */
		@Override
		public void set(E e) {
			if (this.lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			if (e == null) {
				throw new NullPointerException();
			}
			
			this.lastRetrieved.data = e;
		}

		/**
		 * Adds an element at the listIterator
		 * @param e element to add
		 * @throws NullPointerException if e is null
		 */
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			
			ListNode newNode = new ListNode(e, this.previous, this.next);
//			for (int i = previousIndex(); i < size(); i++) {
//				newNode.next = this.next;
//				newNode.prev = this.previous;
				this.previous.next = newNode;
				this.next.prev = newNode;
//			}
				previous = newNode;
				nextIndex++;
				previousIndex++;
			
			this.lastRetrieved = null;
			size++;
		}
	}
}
