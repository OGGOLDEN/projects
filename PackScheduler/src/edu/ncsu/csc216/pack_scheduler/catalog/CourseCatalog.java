/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Course Catalog class. Handles the creation and manipulation of catalogs of courses
 * @author magolden
 * @author khanser
 * @author tcgelman
 *
 */
public class CourseCatalog {
	/** catalog instance for CourseCatalog*/
	private SortedList<Course> catalog;
	/** size of catalog array*/
	private final int shortDisplayColumns = 4;
	
	/**
	 * Constructor for Course Catalog.
	 * Makes a new Sorted List that is blank, to be filled later using other methods.
	 */
	public CourseCatalog() {
		this.catalog = new SortedList<Course>();
	}
	
	/**
	 * Creates a new Course Catalog.
	 * Expressly for the purpose of resetting the catalog to a blank state.
	 */
	public void newCourseCatalog() {
		this.catalog = new SortedList<Course>();
	}
	
	/**
	 * Loads courses from a file
	 * throws a FileNotFound exception if the file does not exist
	 * @param fileName name of the file to be loaded from
	 * @throws IllegalArgumentException if the file does not exist
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a specified course to the catalog, using the parameters described below
	 * @param name name of the course
	 * @param title title of the course
	 * @param section section of the course
	 * @param credits credits the course offers
	 * @param instructorId the ID of the instructor for the course
	 * @param enrollmentCap maximum number of students that can enroll in a course
	 * @param meetingDays the meeting days of the course per week
	 * @param startTime the start time of the course every meeting day
	 * @param endTime the end time of the course every meeting day
	 * @return returns true if the course is added, false if it isn't
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap,  String meetingDays, int startTime, int endTime) {
		Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		for(int i = 0; i < this.catalog.size(); i++) {
			if(c.isDuplicate(this.catalog.get(i)) && c.getSection().equals(this.catalog.get(i).getSection())) {
				return false;
			}
		}
		this.catalog.add(c);
		return true;
	}
	
	/**
	 * Removes a specified course from the catalog, using the parameters described below
	 * @param name name of course to be removed
	 * @param section section of course to be removed
	 * @return returns true if the course is removed, false otherwise
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		Course c = getCourseFromCatalog(name, section);
		if(c == null) {
			return false;
		}
		for(int i = 0; i < this.catalog.size(); i++) {
			if(name.equals(this.catalog.get(i).getName()) && section.equals(this.catalog.get(i).getSection())) {
				this.catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * gets a specified course from the catalog, using the following parameters
	 * @param name name of course to be received
	 * @param section section of course to be acquired 
	 * @return returns the course if it exists in the catalog, and null if it doesn't exist
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(int i = 0; i < this.catalog.size(); i++) {
			Course c = this.catalog.get(i);
			if(name.equals(c.getName()) && section.equals(c.getSection())) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Gets a 2D array of Strings representing the entire course catalog.
	 * The 2d array consists of each of the following per row: name, section, title, meeting string
	 * @return returns the course catalog with all of it's information as specified above.
	 */
	public String[][] getCourseCatalog(){
		String[][] courseCatalog = new String[this.catalog.size()][shortDisplayColumns];
		if(this.catalog.size() == 0) {
			return courseCatalog;
		}
		for(int i = 0; i < courseCatalog.length; i++) {
			Course c = this.catalog.get(i);
			courseCatalog[i] = c.getShortDisplayArray();
		}
		return courseCatalog;
	}
	
	/**
	 * Saves a course catalog to a specified file.
	 * The method will throw an IllegalArgumentException if it is unable to write to the file.
	 * @param fileName name of the file to be saved to.
	 * @throws IllegalArgumentException if the file is unable to be written to.
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, this.catalog);
		} catch(IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

}
