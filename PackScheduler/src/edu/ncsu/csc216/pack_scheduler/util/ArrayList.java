package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Reimplementation of ArrayList from Java
 * @author kbmille6
 * @author magolden
 *
 * @param <E> Type of object for the list to contain
 */
@SuppressWarnings("unchecked")
public class ArrayList<E> extends AbstractList<E> {
	
	/** Initial size of list */
	private static final int INIT_SIZE = 10;
	/** Internal actual list */
	private E[] list;
	/** Current size of the list */
	private int size;
	
	/**
	 * Construct a new ArrayList
	 */
	public ArrayList() {
		size = 0;
		list = (E[]) new Object[INIT_SIZE];
	}
	
	private void growArray() {
		E[] newList = (E[]) new Object[list.length * 2];
		for (int i = 0; i < list.length; i++) {
			newList[i] = list[i];
		}
		list = newList;
	}
	
	/**
	 * Adds an element to an ArrayList
	 * @param index index to add element at
	 * @param o element to add
	 * @throws NullPointerException if the element is null
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if the element is already in the list
	 */
	@Override
	public void add(int index, E o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size(); i++) {
			if (o.equals(list[i])) {
				throw new IllegalArgumentException();
			}
		}
		
		size++;
		if (size() == list.length) {
			growArray();
		}
		// Shift elements over by one that should be behind the new object
		for (int i = size(); i > index; i--) {
			list[i] = list[i - 1];
		}
		list[index] = o;
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
		E removed = list[index];
		list[index] = null;
		size--;
		for (int i = index; i < size; i++) {
			list[i] = list[i + 1];
		}
		return removed;
	}
	
	/**
	 * Replaces an index in the list with a given element
	 * @param index index of element to set
	 * @param o element to set
	 * @return original element that was replaced
	 * @throws NullPointerException if the element is null
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if the element is already in the list
	 */
	@Override
	public E set(int index, E o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size(); i++) {
			if (o.equals(list[i])) {
				throw new IllegalArgumentException();
			}
		}
		
		E original = list[index];
		list[index] = o;
		return original;
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
		return list[index];
	}

	/**
	 * Returns the size of the list
	 * @return size size of list
	 */
	@Override
	public int size() {
		return size;
	}

}
