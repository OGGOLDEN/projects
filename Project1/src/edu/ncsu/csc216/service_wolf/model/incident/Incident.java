/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.incident;

import java.util.ArrayList;

import edu.ncsu.csc216.service_wolf.model.command.Command;

/**
 * Incident represents an incident managed by the system. An incident has an id number,
 * current state, title, caller (person who created the incident) reopen counter, owner,
 * status details, and a log of its resolution history.
 * Incidents use Commands to transition through the ServiceWolfFSM and can be organized into ServiceGroups
 * 
 * @author magolden
 */
public class Incident {
	
	/** Incident id number */
	private int incidentId;
	
	/** Current state of Incident */
	private IncidentState currentState;
	
	/** Title of Incident */
	private String title;
	
	/** Caller of incident */
	private String caller;
	
	/** Count of how many times the incident has been reopened */
	private int reopenCount;
	
	/** Owner of incident */
	private String owner;
	
	/** Status of Incident and details of why an Incident has a particular status */
	private String statusDetails;
	
	/** List of Incident history details */
	private ArrayList<String> incidentLog;
	
	/** Name of the NEW state */
	public static final String NEW_NAME = "New";
	
	/** Name of the INPROGRESS state */
	public static final String IN_PROGRESS_NAME = "In Progress";
	
	/** Name of the ONHOLD state */
	public static final String ON_HOLD_NAME = "On Hold";
	
	/** Name of the RESOLVED state */
	public static final String RESOLVED_NAME = "Resolved";
	
	/** Name of the CANCELED STATE */
	public static final String CANCELED_NAME = "Canceled";
	
	/** ONHOLD reason for Awaiting Caller */
	public static final String HOLD_AWAITING_CALLER = "Awaiting Caller";
	
	/** ONHOLD reason for Awaiting Change */
	public static final String HOLD_AWAITING_CHANGE = "Awaiting Change";
	
	/** ONHOLD reason for Awaiting Vendor */
	public static final String HOLD_AWAITING_VENDOR = "Awaiting Vendor";
	
	/** RESOLUTION reason for Permanently Solved */
	public static final String RESOLUTION_PERMANENTLY_SOLVED = "Permanently Solved";
	
	/** RESOLUTION reason for Workaround */
	public static final String RESOLUTION_WORKAROUND = "Workaround";
	
	/** RESOLUTION reason for Caller Closed */
	public static final String RESOLUTION_CALLER_CLOSED = "Caller Closed";
	
	/** CANCELLATION reason for duplicate */
	public static final String CANCELLATION_DUPLICATE = "Duplicate";
	
	/** CANCELLATION reason for unnecessary */
	public static final String CANCELLATION_UNNECESSARY = "Unnecessary";
	
	/** CANCELLATION reason for not an incident */
	public static final String CANCELLATION_NOT_AN_INCIDENT = "Not an Incident";
	
	/** CANCELLATION reason for caller canceled */
	public static final String CANCELLATION_CALLER_CANCELLED = "Caller Canceled";
	
	/** String for when no owner is assigned */
	public static final String UNOWNED = "Unowned";
	
	/** String for when Incident has no status */
	public static final String NO_STATUS = "No Status";
	
	/** Incident counter */
	private static int counter = 0;
	
	/** Incident's instance of the new state */
	private final IncidentState newState = new NewState();
	
	/** Incident's instance of the in progress state */
	private final IncidentState inProgressState = new InProgressState();
	
	/** Incident's instance of the resolved state */
	private final IncidentState resolvedState = new ResolvedState();
	
	/** Incident's instance of the on hold state */
	private final IncidentState onHoldState = new OnHoldState();
	
	/** Incident's instance of the canceled state */
	private final IncidentState canceledState = new CanceledState();

	/**
	 * Incident Constructor with limited parameters and default values
	 * @param title title of Incident
	 * @param caller caller of Incident
	 * @param message Incident message
	 * @throws IllegalArgumentException with the message "Incident cannot be created." if title is null or empty
	 * @throws IllegalArgumentException with the message "Incident cannot be created." if caller is null or empty
	 * @throws IllegalArgumentException with the message "Incident cannot be created." if message is null or empty
	 */
	public Incident (String title, String caller, String message) {
		
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		if (caller == null || "".equals(caller)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if (message == null || "".equals(message)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		setTitle(title);
		setCaller(caller);
		this.incidentId = Incident.counter;
		incrementCounter();
		currentState = newState;
		this.owner = UNOWNED;
		this.statusDetails = NO_STATUS;
		this.incidentLog = new ArrayList<String>();
		addMessageToIncidentLog(message);
		this.reopenCount = 0;
	}
	
	/**
	 * Full Incident Constructor
	 * @param id Incident's unique id
	 * @param state Incident's current state
	 * @param title Title of Incident
	 * @param caller caller of Incident
	 * @param reopenCount number of times incident has been reopened
	 * @param owner owner of Incident
	 * @param statusDetails Incident's status details
	 * @param incidentLog List of Incident history
	 * @throws IllegalArgumentException with the message "Incident cannot be created." if any of the following are true
	 * 			1. id is less than or equal to 0
	 * 			2. title is null or empty
	 * 			3. caller is null or empty
	 * 			4. owner is null or empty
	 * 			5. statusDetails is null or empty
	 * 			6. incidentLog is null or empty
	 * 			7. reopenCount is less than 0
	 * 			8. state is null or empty
	 * 			9. State is New or Canceled but has an owner
	 * 			10. State is not New or Not Canceled and doesn't have an owner
	 * 			11. State is InProgress but has a status
	 * 			12. State is OnHold but doesn't have a status
	 * 			13. State is Resolved but doesn't have a status
	 * 			14. State is New and has a status
	 * 			15. State is Canceled and doesn't have a status
	 * 			16. State is OnHold but has an inappropriate status
	 * 			17. State is Resolved but has an inappropriate status
	 * 			18. State is Canceled but has an inappropriate status
	 */
	public Incident (int id, String state, String title, String caller, int reopenCount, 
					String owner, String statusDetails, ArrayList<String> incidentLog) {
		if (id <= 0) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		if (caller == null || "".equals(caller)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		if (owner == null || "".equals(owner)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		if (statusDetails == null || "".equals(statusDetails)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		if (incidentLog == null || incidentLog.isEmpty()) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		setTitle(title);
		setCaller(caller);
		
		if (reopenCount < 0) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		setReopenCount(reopenCount);
		
		if (state == null || "".equals(state)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if ((state.equals(NEW_NAME) || state.equals(CANCELED_NAME)) && !owner.equals(UNOWNED)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if (!state.equals(NEW_NAME) && !state.equals(CANCELED_NAME) && owner.equals(UNOWNED)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if (state.equals(IN_PROGRESS_NAME) && !statusDetails.equals(NO_STATUS)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if (state.equals(ON_HOLD_NAME) && statusDetails.equals(NO_STATUS)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if (state.equals(RESOLVED_NAME) && statusDetails.equals(NO_STATUS)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if (state.equals(NEW_NAME) && !statusDetails.equals(NO_STATUS)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if (state.equals(CANCELED_NAME) && statusDetails.equals(NO_STATUS)) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if (state.equals(ON_HOLD_NAME) && !(statusDetails.equals(HOLD_AWAITING_CALLER) || statusDetails.equals(HOLD_AWAITING_CHANGE) || statusDetails.equals(HOLD_AWAITING_VENDOR))) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if (state.equals(RESOLVED_NAME) && !(statusDetails.equals(RESOLUTION_PERMANENTLY_SOLVED) || statusDetails.equals(RESOLUTION_WORKAROUND) || statusDetails.equals(RESOLUTION_CALLER_CLOSED))) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if (state.equals(CANCELED_NAME) && !(statusDetails.equals(CANCELLATION_DUPLICATE) || statusDetails.equals(CANCELLATION_UNNECESSARY) || statusDetails.equals(CANCELLATION_NOT_AN_INCIDENT) || statusDetails.equals(CANCELLATION_CALLER_CANCELLED))) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		setOwner(owner);
		
		setStatusDetails(statusDetails);
		
		setId(id);
		if(id > Incident.counter) {
			setCounter(id + 1);
		}
		
		this.incidentLog = incidentLog;
		
		setState(state);
	}
	
	/**
	 * Returns Incident's unique id number
	 * @return the incidentId
	 */
	public int getId() {
		return this.incidentId;
	}

	/**
	 * Sets the incidentId
	 * @param incidentId the incidentId to set
	 */
	private void setId(int incidentId) {
		this.incidentId = incidentId;
	}
	
	/**
	 * Returns Incident's current state
	 * @return the currentState
	 */
	public String getState() {
		return this.currentState.getStateName();
	}
	
	/**
	 * Sets the Incident's currentState
	 * @param state state of Incident
	 * @throws IllegalArgumentException with the message "Invalid state name" if the state name is invalid
	 */
	private void setState(String state) {
		if (state.equals(NEW_NAME)) {
			currentState = newState;
		} else if (state.equals(RESOLVED_NAME)) {
			currentState = resolvedState;
		} else if (state.equals(CANCELED_NAME)) {
			currentState = canceledState;
		} else if (state.equals(ON_HOLD_NAME)) {
			currentState = onHoldState;
		} else if (state.equals(IN_PROGRESS_NAME)) {
			currentState = inProgressState;
		} else {
			throw new IllegalArgumentException("Invalid state name");
		}
		
	}

	/**
	 * Returns Incident's title
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets Incident's title
	 * @param title the title to set
	 */
	private void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns Incident's caller
	 * @return the caller
	 */
	public String getCaller() {
		return this.caller;
	}

	/**
	 * Sets Incident's caller
	 * @param caller the caller to set
	 */
	private void setCaller(String caller) {
		this.caller = caller;
	}

	/**
	 * Returns Incident's reopenCount
	 * @return the reopenCount
	 */
	public int getReopenCount() {
		return this.reopenCount;
	}

	/**
	 * Sets Incident's reopenCount
	 * @param reopenCount the reopenCount to set
	 */
	private void setReopenCount(int reopenCount) {
		this.reopenCount = reopenCount;
	}

	/**
	 * Returns Incident's owner
	 * @return the owner
	 */
	public String getOwner() {
		return this.owner;
	}

	/**
	 * Sets Incident's owner
	 * @param owner the owner to set
	 */
	private void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Returns Incident's statusDetails
	 * @return the statusDetails
	 */
	public String getStatusDetails() {
		return this.statusDetails;
	}

	/**
	 * Sets Incident's statusDetails
	 * @param statusDetails the statusDetails to set
	 */
	private void setStatusDetails(String statusDetails) {
		this.statusDetails = statusDetails;
	}

	/**
	 * Adds a message to the incident log
	 * @param message to be added
	 * @return index of message that was added
	 */
	private int addMessageToIncidentLog(String message) {
		this.incidentLog.add(message);
		return this.incidentLog.size();
	}
	
	/**
	 * Increments the Incident counter by 1
	 */
	public static void incrementCounter() {
		Incident.counter++;
	}
	
	/**
	 * Sets the Incident counter
	 * @param id id of Incident
	 */
	public static void setCounter(int id) {
		Incident.counter = id;
	}
	
	/**
	 * Returns Incident's Incident log messages
	 * @return log messages
	 */
	public String getIncidentLogMessages() {
		String formattedLog = "";
		String currentMessage = "";
		for (int i = 0; i < this.incidentLog.size(); i++) {
			currentMessage = this.incidentLog.get(i);
			formattedLog += "- " + currentMessage + "\n";
		}
		return formattedLog;
	}
	
	
	/**
	 * Formats an Incident into a comma separated string of its fields
	 * @return string representation of Incident
	 */
	@Override
	public String toString() {
		return "* " + this.getId() + "," + this.getState() + "," + this.getTitle() + "," + 
				this.getCaller()  + "," + this.getReopenCount()  + "," + this.getOwner()  + "," + 
				this.getStatusDetails() + "\n" + this.getIncidentLogMessages();
	}

	/**
	 * Updates the current state using a command
	 * @param command command that updates the state
	 * @throws UnsupportedOperationException if the Command is not a valid action
	 * for the given state.
	 */
	public void update(Command command) {
		currentState.updateState(command);
	}

	/**
	 * Interface for states in the Incident State Pattern.  All 
	 * concrete incident states must implement the IncidentState interface.
	 * The IncidentState interface should be a private interface of the 
	 * Incident class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface IncidentState {
		
		
		/**
		 * Update the Incident based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Incident's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command command);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}
	
	/**
	 * Final instance of the NewState inner class
	 * Implements IncidentState
	 * @author magolden
	 */
	public class NewState implements IncidentState {
		
		/**
		 * NewState Constructor
		 */
		private NewState() {
			setState(NEW_NAME);
		}
		
		/**
		 * Update the Incident based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Incident's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		public void updateState(Command command) {
			if (command.getCommand() == Command.CommandValue.ASSIGN) {
				setOwner(command.getCommandInformation());
				addMessageToIncidentLog(command.getCommandMessage());
				setState(IN_PROGRESS_NAME);
			} else if (command.getCommand() == Command.CommandValue.CANCEL) {
				setStatusDetails(command.getCommandInformation());
				setOwner(UNOWNED);
				addMessageToIncidentLog(command.getCommandMessage());
				setState(CANCELED_NAME);
			} else {
				throw new UnsupportedOperationException("Invalid command for New state");
			}
		}
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		public String getStateName() {
			return Incident.NEW_NAME;
		}
	}
	
	/**
	 * Final instance of the ResolvedState inner class
	 * Implements IncidentState
	 * @author magolden
	 */
	public class ResolvedState implements IncidentState {
		
		/**
		 * ResolvedState Constructor
		 */
		private ResolvedState() {
			setState(RESOLVED_NAME);
		}
		
		/**
		 * Update the Incident based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Incident's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		public void updateState(Command command) {
			if (command.getCommand() == Command.CommandValue.REOPEN) {
				addMessageToIncidentLog(command.getCommandMessage());
				setReopenCount(getReopenCount() + 1);
				setStatusDetails(NO_STATUS);
				setState(IN_PROGRESS_NAME);
			} else if (command.getCommand() == Command.CommandValue.CANCEL) {
				setStatusDetails(command.getCommandInformation());
				setOwner(UNOWNED);
				addMessageToIncidentLog(command.getCommandMessage());
				setState(CANCELED_NAME);
			} else {
				throw new UnsupportedOperationException("Invalid command for Resolved state");
			}
		}
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		public String getStateName() {
			return Incident.RESOLVED_NAME;
		}
	}
	
	/**
	 * Final instance of the OnHoldState inner class
	 * Implements IncidentState
	 * @author magolden
	 */
	public class OnHoldState implements IncidentState {
		
		/**
		 * OnHoldState Constructor
		 */
		private OnHoldState() {
			setState(ON_HOLD_NAME);
		}
		
		/**
		 * Update the Incident based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Incident's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		public void updateState(Command command) {
			if (command.getCommand() == Command.CommandValue.INVESTIGATE) {
				addMessageToIncidentLog(command.getCommandMessage());
				setStatusDetails(NO_STATUS);
				setState(IN_PROGRESS_NAME);
			} else {
				throw new UnsupportedOperationException("Invalid command for On Hold state");
			}
		}
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		public String getStateName() {
			return Incident.ON_HOLD_NAME;
		}
	}
	
	/**
	 * Final instance of the CanceledState inner class
	 * Implements IncidentState
	 * @author magolden
	 */
	public class CanceledState implements IncidentState {
		
		/**
		 * CanceledState Constructor
		 */
		private CanceledState() {
			setState(CANCELED_NAME);
		}
		
		/**
		 * Update the Incident based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Incident's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		public void updateState(Command command) {
			throw new UnsupportedOperationException("Invalid command for Canceled state");
		}
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		public String getStateName() {
			return Incident.CANCELED_NAME;
		}
	}
	
	/**
	 * Final instance of the InProgressState inner class
	 * Implements IncidentState
	 * @author magolden
	 */
	public class InProgressState implements IncidentState {
		
		/**
		 * InProgressState Constructor
		 */
		private InProgressState() {
			setState(IN_PROGRESS_NAME);
		}
		
		/**
		 * Update the Incident based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Incident's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		public void updateState(Command command) {
			if (command.getCommand() == Command.CommandValue.ASSIGN) {
				setOwner(command.getCommandInformation());
				addMessageToIncidentLog(command.getCommandMessage());
				setState(IN_PROGRESS_NAME);
			} else if (command.getCommand() == Command.CommandValue.CANCEL) {
				setStatusDetails(command.getCommandInformation());
				setOwner(UNOWNED);
				addMessageToIncidentLog(command.getCommandMessage());
				setState(CANCELED_NAME);
			} else if (command.getCommand() == Command.CommandValue.HOLD) {
				setStatusDetails(command.getCommandInformation());
				addMessageToIncidentLog(command.getCommandMessage());
				setState(ON_HOLD_NAME);
			} else if (command.getCommand() == Command.CommandValue.RESOLVE) {
				setStatusDetails(command.getCommandInformation());
				addMessageToIncidentLog(command.getCommandMessage());
				setState(RESOLVED_NAME);
			} else {
				throw new UnsupportedOperationException("Invalid command for In Progress state");
			}
		}
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		public String getStateName() {
			return Incident.IN_PROGRESS_NAME;
		}
	}
}
