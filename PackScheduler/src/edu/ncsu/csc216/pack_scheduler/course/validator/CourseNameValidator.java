/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Uses an FSM to determine if the name of a course is valid
 * A course name is valid if it has 1-4 letters followed by exactly 3 digits, followed by an optional letter
 * @author magolden
 * @author kbmille6
 */
public class CourseNameValidator {
	
	/** Boolean value true if course name validator fsm is in a valid final state */
	private boolean validEndState;
	
	/** How many letters have been in the course name so far */
	private int letterCount;
	
	/** How many digits have been in the course name so far */
	private int digitCount;
	
	/** The current state of the FSM */
	private State currentState;
	/** The letter state of the FSM */
	private LetterState letterState;
	/** The suffix state of the FSM */
	private SuffixState suffixState;
	/** The initial state of the FSM */
	private InitialState initialState;
	/** The number state of the FSM */
	private NumberState numberState;
	
	/**
	 * Construct a new CourseNameValidator
	 */
	public CourseNameValidator() {
		letterState = new LetterState();
		suffixState = new SuffixState();
		initialState = new InitialState();
		numberState = new NumberState();
	}
	
	/**
	 * Returns whether the current state of the FSM is a valid end state
	 * @return the validEndState
	 */
	public boolean isValidEndState() {
		this.validEndState = false;
		if (currentState == numberState && digitCount == 3) {
			this.validEndState = true;
			return this.validEndState;
		} else if (currentState == suffixState) {
			this.validEndState = true;
			return this.validEndState;
		}
		return this.validEndState;
	}

	/**
	 * Gets the letter count
	 * @return the letterCount
	 */
	public int getLetterCount() {
		return letterCount;
	}

	/**
	 * Sets the letter count
	 * @param letterCount the letterCount to set
	 */
	public void setLetterCount(int letterCount) {
		this.letterCount = letterCount;
	}

	/**
	 * Gets the digit count
	 * @return the digitCount
	 */
	public int getDigitCount() {
		return digitCount;
	}

	/**
	 * Sets the digit count
	 * @param digitCount the digitCount to set
	 */
	public void setDigitCount(int digitCount) {
		this.digitCount = digitCount;
	}

	/**
	 * Returns true if the course name is valid, based on
	 * a string matching Finite State Machine.
	 * 
	 * The course name must match the following format:
	 *      (1-4 letters)(3 digits)(optionally, a 1 letter suffix)
	 *      
	 * @param courseName the name of the course
	 * @return true if the course name is valid, or false if the course name is invalid
	 * @throws InvalidTransitionException when the FSM attempts an invalid transition
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		if (courseName == null || "".equals(courseName)) {
			throw new IllegalArgumentException("Invalid name");
		}
		
		currentState = initialState;
		letterCount = 0;
		digitCount = 0;
		for (int i = 0; i < courseName.length(); i++)
		{
			if (Character.isDigit(courseName.charAt(i))) {
				currentState.onDigit();
			} else if (Character.isLetter(courseName.charAt(i))) {
				currentState.onLetter();
			} else {
				currentState.onOther();
			}
		}
		return isValidEndState();
	}

	private abstract class State {
		
		public abstract void onLetter() throws InvalidTransitionException;
		
		public abstract void onDigit() throws InvalidTransitionException;
		
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	private class LetterState extends State {
		
		/** Maximum number of letters in the course name prefix */
		private static final int MAX_PREFIX_LETTERS = 4;

		@Override
		public void onLetter() throws InvalidTransitionException {
			if (letterCount < MAX_PREFIX_LETTERS) {
				letterCount++;
				currentState = letterState;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			digitCount++;
			currentState = numberState;
		}
	}
	
	private class SuffixState extends State {

		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}

		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}
		
	}
	
	private class InitialState extends State {
		
		@Override
		public void onLetter() throws InvalidTransitionException {
			letterCount++;
			currentState = letterState;
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
		
	}
	
	private class NumberState extends State {
		
		/** How many digits course names should have */
		private static final int COURSE_NUMBER_LENGTH = 3;
		
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount != COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
			currentState = suffixState;
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount < COURSE_NUMBER_LENGTH) {
				digitCount++;
				currentState = numberState;
			} else {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
		
	}
}

