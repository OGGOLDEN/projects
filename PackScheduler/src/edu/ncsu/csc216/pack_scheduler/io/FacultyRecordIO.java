/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Handles File IO for Faculty
 * @author magolden
 */
public class FacultyRecordIO {
	
	/**
	 * Reads a text file and converts its contents into a LinkedList of faculty
	 * @param filename name of file to read
	 * @return faculty a LinkedList of faculty members
	 * @throws FileNotFoundException if the file is not found
	 */
	public static LinkedList<Faculty> readFacultyRecords(String filename) throws FileNotFoundException {
		FileInputStream fis;
		Scanner scnr;
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		try {
			fis = new FileInputStream(filename);
			scnr = new Scanner(fis);
			while(scnr.hasNextLine()){
				try {
					String facultyLine = scnr.nextLine();
					faculty.add(processFaculty(facultyLine));
				} catch (IllegalArgumentException e) {
					//skips invalid faculty
				}
			}
			scnr.close();
		} catch (FileNotFoundException e){
			throw new FileNotFoundException("File not found.");
		}
		return faculty;
	}
	
	/**
	 * Private helper method that processes a single faculty
	 * @param facultyString string representation of faculty
	 * @return new Faculty object created from the contents of facultyString
	 */
	private static Faculty processFaculty(String facultyString) {
		try {
			String line = facultyString.replaceAll(",", " ");
			Scanner in = new Scanner(line);
			String firstName = in.next();
			String lastName = in.next();
			String id = in.next();
			String email = in.next();
			String password = in.next();
			int maxCourses = in.nextInt();
			in.close();
			return new Faculty(firstName, lastName, id, email, password, maxCourses);
		} catch (InputMismatchException e) {
			throw new IllegalArgumentException();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Writes a LinkedList of faculty to a text file
	 * @param filename name of file to write to
	 * @param list list of faculty
	 * @throws IOException if there is an IO error
	 */
	public static void writeFacultyRecords(String filename, LinkedList<Faculty> list) throws IOException {
		FileOutputStream fos = null;
		PrintWriter out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new PrintWriter(fos);
			for (int i = 0; i < list.size(); i++) {
				out.write(list.get(i).toString() + "\n");
			}
			out.close();
		} catch (IOException e){
			throw new IOException(filename + " (Permission denied)");
		}
	}
}
