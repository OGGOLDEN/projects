package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads a list of courses from a given file, generates a list of valid courses,
 * and outputs that list to a given file
 * 
 * @author magolden
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {

		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		SortedList<Course> courses = new SortedList<Course>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try { // Attempt to do the following
					// Read the line, process it in readCourse, and get the object
					// If trying to construct a Course in readCourse() results in an exception, flow
					// of control will transfer to the catch block, below
				Course course = readCourse(fileReader.nextLine());

				// Create a flag to see if the newly created Course is a duplicate of something
				// already in the list
				boolean duplicate = false;
				// Look at all the courses in our list
				for (int i = 0; i < courses.size(); i++) {
					// Get the course at index i
					Course current = courses.get(i);
					// Check if the name and section are the same
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						// It's a duplicate!
						duplicate = true;
						break; // We can break out of the loop, no need to continue searching
					}
				}
				// If the course is NOT a duplicate
				if (!duplicate) {
					courses.add(course); // Add to the SortedList!
				} // Otherwise ignore
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the SortedList with all the courses we read!
		return courses;
	}

	/**
	 * Creates a course object by splitting the nextLine using a ',' delimiter to
	 * break up the course tokens
	 * 
	 * @param nextLine comma separated string with course parameters
	 * @return course Course object with all parameters filled
	 */
	private static Course readCourse(String nextLine) {
		// Break the string into tokens that the scanner can use with a ',' delimiter
		try {
			Scanner scnr = new Scanner(nextLine);
			scnr.useDelimiter(",");
			String name = scnr.next();
			String title = scnr.next();
			String section = scnr.next();
			int credits = scnr.nextInt();
			String instructor = scnr.next();
			int enrollmentCap = scnr.nextInt();
			String meetingDays = scnr.next();

			// if course is arranged, it should not be followed by time parameters
			if ("A".equals(meetingDays)) {
				if (scnr.hasNext()) {
					scnr.close();
					throw new IllegalArgumentException();
				}
				scnr.close();
				// return arranged course
				Course course = new Course(name, title, section, credits, null, enrollmentCap, meetingDays);
				
//				String[][] fd = RegistrationManager.getInstance().getFacultyDirectory().getFacultyDirectory();
				Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor);
				if (f != null) {
//				FacultySchedule fs = f.getSchedule();
//					for (int i = 0; i < fd.length; i++) {
//						if (f.getId().equals(instructor)) {
							f.getSchedule().addCourseToSchedule(course);
//						}
//					}
				}
				
				return course;
			}
			int startTime = scnr.nextInt();
			int endTime = scnr.nextInt();

			scnr.close();

			// return normal course
			Course course = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime, endTime);
			
//			String[][] fd = RegistrationManager.getInstance().getFacultyDirectory().getFacultyDirectory();
			Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor);
			if (f != null) {
//				FacultySchedule fs = f.getSchedule();
//				for (int i = 0; i < fd.length; i++) {
//					if (fd[i][2].equals(instructor)) {
						f.getSchedule().addCourseToSchedule(course);
//					}
//				}
			}
			
			return course;
		
			} catch (InputMismatchException e) {
			throw new IllegalArgumentException();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Writes the given list of Courses to
	 * 
	 * @param fileName file to write schedule of Courses to
	 * @param courses  list of Courses to write
	 * @throws IOException if cannot write to file
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		// output each line of courses
		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

}
