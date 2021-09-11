package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

//import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Course class represents a single course offered by the Wolf Scheduler. Each
 * Course has a name, title, section, number of credits, instructorId, and some
 * have a start/end time
 * 
 * @author magolden
 */
public class Course extends Activity implements Comparable<Course> {

	/** CourseNameValidator object used to determine if a course name is valid*/
	private CourseNameValidator validator;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
//	/** Maximum length for name is 8 characters */
//	private static final int MAX_NAME_LENGTH = 8;
//	/** Minimum length for name is 5 characters */
//	private static final int MIN_NAME_LENGTH = 5;
//	/** Minimum length for title is 1 character */
//	private static final int MIN_LETTER_COUNT = 1;
//	/** Maximum length for title is 4 characters */
//	private static final int MAX_LETTER_COUNT = 4;
//	/** Number of digits in each title is 3 */
//	private static final int DIGIT_COUNT = 3;
	/** Length of section number is 3 */
	private static final int SECTION_LENGTH = 3;
	/** Minimum number of credits is 1 */
	private static final int MIN_CREDITS = 1;
	/** Maximum number of credits is 5 */
	private static final int MAX_CREDITS = 5;
	/** Maximum hour value is 23 */
	private static final int UPPER_HOUR = 23;
	/** Maximum minute value is 59 */
	private static final int UPPER_MINUTE = 59;
	/** Course enrollment size */
	private int enrollmentCap;
	/** CourseRoll for the class */
	private CourseRoll roll;
	/**
	 * Constructs a Course object with values for all fields
	 * 
	 * @param name          Name of Course
	 * @param title         Title of Course
	 * @param section       Section of Course
	 * @param credits       Credit hours for Course
	 * @param instructorId  Instructor's Unity ID
	 * @param enrollmentCap Max number of students in the course
	 * @param meetingDays   Days that course meets as a series of chars
	 * @param startTime     start time for course
	 * @param endTime       end time for course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		// call each set method for course params
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		this.enrollmentCap = enrollmentCap;
		this.roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Creates a Course object with name, title, section, credits, instructorId, and
	 * meetingDays
	 * 
	 * @param name          name of course
	 * @param title         title of course
	 * @param section       section of course
	 * @param credits       credit hours for course
	 * @param instructorId  instructor's Unity ID
	 * @param enrollmentCap Max number of students in the course
	 * @param meetingDays   days course meets as a series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		// Recursively call first Course constructor with zeros for times
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException with the message "Invalid name" if the name is invalid
	 */
	private void setName(String name) {
		this.validator = new CourseNameValidator();
		try {
			this.validator.isValid(name);
			if(this.validator.isValidEndState()) {
				this.name = name;
			} else {
				throw new IllegalArgumentException("Invalid name");
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid name");
		}
//		// throw exception if name is null
//		if (name == null) {
//			throw new IllegalArgumentException("Name cannot be null.");
//		}
//
//		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
//			// throw exception if name's length is out of bounds
//			throw new IllegalArgumentException("Name's length should be between 5 and 8, inclusive.");
//		}
//
//		int l = 0;
//		int d = 0;
//		boolean foundSpace = false;
//		boolean t = true;
//		boolean f = false;
//
//		// for each char in name, check to see if it is letter, digit, or whitespace
//		// each name should have 1-4 letters followed by 1 space, then followed by 3
//		// digits.
//		// throw exception if otherwise
//		for (int i = 0; i < name.length(); i++) {
//			if (foundSpace == f) {
//				if (Character.isLetter(name.charAt(i))) {
//					l++;
//				} else if (name.charAt(i) == ' ') {
//					foundSpace = true;
//				} else {
//					throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
//				}
//			} else if (foundSpace == t) {
//				if (Character.isDigit(name.charAt(i))) {
//					d++;
//				} else {
//					throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
//				}
//			}
//		}
//
//		// name must have 1-4 letters
//		if (l < MIN_LETTER_COUNT || l > MAX_LETTER_COUNT) {
//			throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
//		}
//
//		// name must have 3 digits
//		if (DIGIT_COUNT != d) {
//			throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
//		}
	}

	/**
	 * Returns the course section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the course section
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException with the message "Invalid section number" if the section length is not 3 or
	 * 	       if section contains anything other than a digit
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		// for each character in the section string, make sure it is a digit
		boolean f = false;
		for (char i : section.toCharArray()) {
			if (Character.isDigit(i) == f) {
				throw new IllegalArgumentException("Invalid section number");
			}
		}
		this.section = section;
	}

	/**
	 * Returns the course credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the course credits
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException with the message "Credits should be between 1 and 5, inclusive." if credits 
	 * 		   is not between 1 and 5
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Credits should be between 1 and 5, inclusive.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Instructor's Unity ID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Instructor's Unity ID
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException with the message "Invalid instructor unity id" if it is null or empty
	 */
	public void setInstructorId(String instructorId) {
		if ("".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor unity id");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + enrollmentCap + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + enrollmentCap + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Course main
	 * 
	 * @param args CL args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Gets the CourseRoll
	 * 
	 * @return the CourseRoll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

	/**
	 * Creates an array of course info that contains name, section, title, and meeting info
	 * @return shortDisplay array of course info
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[5];
		shortDisplay[0] = name;
		shortDisplay[1] = section;
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		shortDisplay[4] = "" + getCourseRoll().getOpenSeats();
		
		return shortDisplay;
	}

	/**
	 * Creates an array of course info that contains name, section, title, credits, instrcutorId, and meeting info
	 * @return longDisplay array of course info
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		String creditString = Integer.toString(credits);
		longDisplay[0] = name;
		longDisplay[1] = section;
		longDisplay[2] = getTitle();
		longDisplay[3] = creditString;
		longDisplay[4] = instructorId;
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";
		
		return longDisplay;
	}

	/**
	 * Sets meeting day, start time, and end time for a course activity
	 * 
	 * @param meetingDays days course meets
	 * @param startTime time course starts
	 * @param endTime time course ends
	 * @throws IllegalArgumentException with the message "Invalid meeting days." if meeting days is null, empty, 
	 *  	   contains an invalid character, or contains duplicate characters
	 * @throws IllegalArgumentException with the message "Invalid start time." if startTime
	 * 		   has an invalid hour or minute
	 * @throws IllegalArgumentException with the message "Invalid end time." if endTime
	 * 		   has an invalid hour or minute
	 * @throws IllegalArgumentException with the message "End time cannot be before start time." if the end time
	 * 		   is before the start time
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days.");
		}
	
		// if meeting days are asynchronous, set meetingDays to "A", and start/end time
		// to 0;
		// if course meets at set time, use counters to determine what days course
		// meets,
		// and eliminate duplicates.
		// throw exception if meetingDays contains a char other than M, T, W, H, F, or A
		if ("A".equals(meetingDays)) {
			super.setMeetingDaysAndTime(meetingDays, 0, 0);
		} else {
			int mon = 0;
			int tues = 0;
			int wed = 0;
			int thurs = 0;
			int fri = 0;
	
			for (int i = 0; i < meetingDays.length(); i++) {
				if (meetingDays.charAt(i) == 'M') {
					mon++;
				} else if (meetingDays.charAt(i) == 'T') {
					tues++;
				} else if (meetingDays.charAt(i) == 'W') {
					wed++;
				} else if (meetingDays.charAt(i) == 'H') {
					thurs++;
				} else if (meetingDays.charAt(i) == 'F') {
					fri++;
				} else {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
	
			if (mon > 1 || tues > 1 || wed > 1 || thurs > 1 || fri > 1) {
				throw new IllegalArgumentException("Invalid meeting days.");
			}
	
			// convert military time to usable standard time
			int startHour = startTime / 100;
			int startMin = startTime % 100;
			int endHour = endTime / 100;
			int endMin = endTime % 100;
	
			// throw exceptions if hour is not between 0 and 23,
			// if minute is not between 0 and 59,
			// and if end time is before start time
			if (startHour < 0 || startHour > UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid start time.");
			} else if (startMin < 0 || startMin > UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid start time.");
			} else if (endHour < 0 || endHour > UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid end time.");
			} else if (endMin < 0 || endMin > UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid end time.");
			} else if (endTime < startTime) {
				throw new IllegalArgumentException("End time cannot be before start time.");
			} else {
				super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
			}
		}
	}

	/**
	 * Returns true if the activity is an instance of course and they both have the same title and false otherwise
	 * @return true/false
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		return activity instanceof Course && this.getName().equals(((Course) activity).getName());
	}
	
	/**
	 * Compares two Course objects and returns 0 if they're equal,
	 * an integer less than 0 if this object is less than o,
	 * an integer greater than 0 if this object is greater than o.
	 * Inherited from Comparable interface.
	 * @param o Course object being compared
	 * @return see above
	 */
	@Override
	public int compareTo(Course o) {
		if (this.equals(o)) {
			return 0;
		}
		
		if (this.name.equals(o.getName())) {
			return this.section.compareTo(o.getSection());
		} else {
			return this.name.compareTo(o.getName());
		}
	}

}

