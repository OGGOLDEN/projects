package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Parent class of Course and Event that knows commonalities such as
 * title, meetingDays, startTime, and endTime
 * 
 * @author magolden
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Activity constructor. Sets the title, meetingDays, and times
	 * @param title title of activity
	 * @param meetingDays days activity meets
	 * @param startTime start time of activity
	 * @param endTime end time of activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the course title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the course title
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException with the message "Invalid course title" if the title is null or empty
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid course title");
		}
		this.title = title;
	}

	/**
	 * Returns the days that the course meets
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the start time of the course
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the end time of the course
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the meeting days, start time, and end time for a course
	 * 
	 * @param meetingDays days of the week in which the course meets
	 * @param startTime   time the course starts
	 * @param endTime     time the course ends
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		this.meetingDays = meetingDays;
		if ("A".equals(meetingDays)) {
			this.startTime = 0;
			this.endTime = 0;
		} else {
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}

	/**
	 * Creates a string of capital letters that correspond to the days of the week
	 * with time start and end time as a string that each course meets
	 * 
	 * @return "Arranged" if the course is asynchronous days of week + hour:minAM/PM
	 *         if the course meets at a specified time (for example "MW
	 *         9:30AM-10:45AM" or "TH 3:00PM-4:00PM")
	 */
	public String getMeetingString() {
		if ("A".equals(this.meetingDays)) {
			return "Arranged";
		} else {
			return this.meetingDays + " " + getTimeString(this.startTime) + "-" + getTimeString(this.endTime);
		}
	}

	/**
	 * Returns a time as a string is standard time given an integer in military time
	 * 
	 * @param time time in military format
	 * @return hour:minAM/PM if the course meets at a specified time (for example
	 *         "9:30AM" or "3:00PM")
	 * @throws IllegalArgumentException with the message "Time is invalid."  if the time is negative or greater than 2400
	 */
	private String getTimeString(int time) {
		int hour = 0;
		int min = 0;
		String minString = "";
		
	
		// to deal with leading zeroes in military time, separate 4 digit numbers from 3
		// digit numbers
		if (time >= 1000 && time <= 2400) {
			hour = time / 100;
			min = time % 100;
			minString = "" + min;
			// if minute is between 0 and 9, add a leading zero
			if (min >= 0 && min <= 9) {
				minString = "0" + min;
			}
			if (hour <= 11) {
				return hour + ":" + minString + "AM";
			} else if (hour == 12) {
				return hour + ":" + minString + "PM";
			} else {
				// PM military time hours need to be converted by subtracting 12
				hour = hour - 12;
				return hour + ":" + minString + "PM";
			}
		} else if (time >= 0 && time <= 959) {
			hour = time / 100;
			min = time % 100;
			minString = "" + min;
			// if minute is between 0 and 9, add a leading zero
			if (min >= 0 && min <= 9) {
				minString = "0" + minString;
			}
			if (hour == 0) {
				// midnight military time hours need to be converted by adding 12
				hour = hour + 12;
			}
			return hour + ":" + minString + "AM";
		} else {
			// throw exception if time is negative or greater than 2400
			throw new IllegalArgumentException("Time is invalid.");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	/**
	 * Abstract method implemented by Course and Event
	 * Creates a brief string array of an activity's name, section, title, and meeting info
	 * @return shortDisplay string array of activity info
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Abstract method implemented by Course and Event
	 * Creates a full string array of an activity's name, section, title, credits, instructorId,
	 * meeting info, and event details
	 * @return longDisplay string array of activity info
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Determines if two activities are of the same type and have the same name
	 * @param activity activity object being compared
	 * @return true if the two activities duplicates
	 * 		   false if the two activities are not duplicates
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Checks to see if the given activity's meeting days and times overlap with an
	 * existing scheduled activity
	 * @param possibleConflictingActivity activity object with potential to cause conflict
	 * @throws ConflictException with the message "Schedule conflict." if two activities occur on the same day and time
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		boolean sameDay;
		
		if (this.getMeetingDays().contains("M") && possibleConflictingActivity.getMeetingDays().contains("M")) {
			sameDay = true;
		} else if (this.getMeetingDays().contains("T") && possibleConflictingActivity.getMeetingDays().contains("T")) {
			sameDay = true;
		} else if (this.getMeetingDays().contains("W") && possibleConflictingActivity.getMeetingDays().contains("W")) {
			sameDay = true;
		} else if (this.getMeetingDays().contains("H") && possibleConflictingActivity.getMeetingDays().contains("H")) {
			sameDay = true;
		} else if (this.getMeetingDays().contains("F") && possibleConflictingActivity.getMeetingDays().contains("F")) {
			sameDay = true;
		} else if (this.getMeetingDays().contains("S") && possibleConflictingActivity.getMeetingDays().contains("S")) {
			sameDay = true;
		} else if (this.getMeetingDays().contains("U") && possibleConflictingActivity.getMeetingDays().contains("U")) {
			sameDay = true;
		} else {
			sameDay = false;
		}
		
		if (sameDay) {
			if (this.getStartTime() >= possibleConflictingActivity.getStartTime() && 
					this.getStartTime() <= possibleConflictingActivity.getEndTime()) {
				throw new ConflictException();
			} else if (possibleConflictingActivity.getStartTime() >= this.getStartTime() && 
					possibleConflictingActivity.getStartTime() <= this.getEndTime()) {
				throw new ConflictException();
			} else if (this.getEndTime() >= possibleConflictingActivity.getStartTime() &&
					this.getEndTime() <= possibleConflictingActivity.getEndTime()) {
				throw new ConflictException();
			} else if (possibleConflictingActivity.getEndTime() >= this.getStartTime() &&
					possibleConflictingActivity.getEndTime() <= this.getEndTime()) {
				throw new ConflictException();
			}
		}
	}
}
