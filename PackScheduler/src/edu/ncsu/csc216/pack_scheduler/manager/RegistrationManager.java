package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * A singleton class that manages the program's students, courses, and users
 *
 */
public class RegistrationManager {
	
	/** Keeps track of the singleton */
	private static RegistrationManager instance;
	/** Catalog of courses */
	private CourseCatalog courseCatalog;
	/** Directory of students */
	private StudentDirectory studentDirectory;
	/** The Registrar */
	private User registrar;
	/** The currently logged in user */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Filename of the properties file for this class */
	private static final String PROP_FILE = "registrar.properties";
	/** The Faculty Directory */
	private FacultyDirectory facultyDirectory;

	private RegistrationManager() {
		createRegistrar();
		studentDirectory = new StudentDirectory();
		courseCatalog = new CourseCatalog();
		facultyDirectory = new FacultyDirectory();
		
	}
	
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
		
		studentDirectory = new StudentDirectory();
		courseCatalog = new CourseCatalog();
		facultyDirectory = new FacultyDirectory();
	}
	
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Gets the RegistrationManager singleton instance
	 * @return the instance
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * gets the faculty directory
	 * @return facultyDirectory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
	
	/**
	 * Adds a Faculty member to a course
	 * @param c course to assign a faculty to
	 * @param f faculty to assign a course to
	 * @return true/false if the add was successful or not
	 * @throws IllegalArgumentException if the current user is not the registrar
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
		if (this.currentUser != this.registrar) {
			throw new IllegalArgumentException();
		}
		
		if (this.currentUser != null && this.currentUser == this.registrar) {
			f.getSchedule().addCourseToSchedule(c);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a Faculty member from a course
	 * @param c course to remove a faculty from
	 * @param f faculty to remove
	 * @return true/false if the remove was successful or not
	 * @throws IllegalArgumentException if the current user is not the registrar
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
		if (this.currentUser != this.registrar) {
			throw new IllegalArgumentException();
		}
		
		if (this.currentUser != null && this.currentUser == this.registrar) {
			f.getSchedule().removeCourseFromSchedule(c);
			return true;
		}
		return false;
	}
	
	/**
	 * Resets a given faculty's schedule
	 * @param f faculty to reset assign a course to
	 * @throws IllegalArgumentException if the current user is not the registrar
	 */
	public void resetFacultySchedule(Faculty f) {
		if (this.currentUser != this.registrar) {
			throw new IllegalArgumentException();
		}
		
		if (this.currentUser != null && this.currentUser == this.registrar) {
			f.getSchedule().resetSchedule();
		}
	}
	
	/**
	 * Gets the course catalog
	 * @return the course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Gets the student directory
	 * @return the student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Attempt to login as a certain user
	 * @param id the user's id
	 * @param password the user's password
	 * @throws IllegalArgumentException if there is nobody with the provided id
	 * @return true if the login succeeded, false if it failed
	 */
	public boolean login(String id, String password) {
		// Can't login if somebody else is logged in
		if (getCurrentUser() != null) {
			return false;
		}
		// Id and password should have content 
		if (id == null || password == null) {
			throw new IllegalArgumentException("Null id or password.");
		}
		
		// Hash password
		String localHashPW = "";
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			localHashPW = new String(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException();
		}
		
		// Check if user exists
		Student s = studentDirectory.getStudentById(id);
		Faculty f = facultyDirectory.getFacultyById(id);
		if (s == null && f == null && !registrar.getId().equals(id)) {
			throw new IllegalArgumentException("User doesn't exist.");
		}
		
		// Try to login student
		if (s != null) {
			if (s.getPassword().equals(localHashPW)) {
				currentUser = s;
				return true;
			} else {
				return false;
			}
		}
		
		// Try to login registrar
		if (registrar.getId().equals(id)) {
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			} else {
				return false;
			}
		}
		
		// Try to login faculty
		if (f != null) {
			if (f.getPassword().equals(localHashPW)) {
				currentUser = f;
				return true;
			} else {
				return false;
			}
		}
		
		// No matches
		return false;
	}

	/**
	 * Logs out the currently logged in user
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * Gets the currently logged in user
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Clears the state of the RegistrationManager, removing the existing contents of the
	 * courseCatalog and studentDirectory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
		logout();
	}
	
	private static class Registrar extends User {
		/**
		 * Create a registrar user.
		 * @param firstName the registrar's first name
		 * @param lastName the registrar's last name
		 * @param id the registrar's id
		 * @param email the registrar's email
		 * @param hashPW the registrar's password, hashed
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 * @throws IllegalArgumentException with the message "Illegal Action" if the current user is not a student
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 * @throws IllegalArgumentException with the message "Illegal Action" if the current user is not a student
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 * @throws IllegalArgumentException with the message "Illegal Action" if the current user is not a student
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
}
