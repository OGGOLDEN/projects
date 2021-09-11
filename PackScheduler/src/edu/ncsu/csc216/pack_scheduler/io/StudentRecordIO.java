package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads an input file of student info and outputs the info
 * in a form that is readable by the student directory.
 * @author magolden
 * @author khanser
 * @author tcgelman
 */
public class StudentRecordIO {

	/**
	 * Reads a text file of student information
	 * @param fileName name of student info file
	 * @return students Sorted List of student info
	 * @throws FileNotFoundException with the message "File not found" if the input file is not found
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		
		FileInputStream fis;
		Scanner scnr;
		SortedList<Student> students = new SortedList<Student>();
		try {
			fis = new FileInputStream(fileName);
			scnr = new Scanner(fis);
			while(scnr.hasNextLine()){
				try {
					String studentLine = scnr.nextLine();
					students.add(processStudent(studentLine));
				} catch (IllegalArgumentException e) {
					//skips invalid student
				}
			}
			scnr.close();
		} catch (FileNotFoundException e){
			throw new FileNotFoundException("File not found.");
		}
		return students;
	}

	/**
	 * Outputs the student info as a text file that is separated by
	 * whitespace instead of commas.
	 * @param fileName name of input file
	 * @param studentDirectory SortedList of student info
	 * @throws IOException throw IOException with the message "File not found" if the input file is not found
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		
		FileOutputStream fos = null;
		PrintWriter out = null;
		try {
			fos = new FileOutputStream(fileName);
			out = new PrintWriter(fos);
			for (int i = 0; i < studentDirectory.size(); i++) {
				out.write(studentDirectory.get(i).toString() + "\n");
			}
			out.close();
		} catch (IOException e){
			throw new IOException(fileName + " (Permission denied)");
		}
		
	}
	
	/**
	 * Processes each line of student info by replacing the commas with whitespace
	 * and passing each piece of student info to the Student method
	 * @param studentline String of student info
	 * @return Student object
	 */
	private static Student processStudent(String studentline) {
		
		try {
			String line = studentline.replaceAll(",", " ");
			Scanner in = new Scanner(line);
			String firstName = in.next();
			String lastName = in.next();
			String id = in.next();
			String email = in.next();
			String password = in.next();
			int maxCredits = in.nextInt();
			in.close();
			return new Student(firstName, lastName, id, email, password, maxCredits);
		} catch (InputMismatchException e) {
			throw new IllegalArgumentException();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		
	}

}
